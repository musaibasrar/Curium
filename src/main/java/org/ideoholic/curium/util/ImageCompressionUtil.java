package org.ideoholic.curium.util;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.MemoryCacheImageOutputStream;

import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
import org.apache.pdfbox.pdmodel.graphics.image.JPEGFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

/**
 * FileCompressionUtil (kept as {@code ImageCompressionUtil} for backward-compatibility) —
 * compresses uploaded files to a maximum file size (default 100 KB) while
 * keeping the best possible visual quality.
 *
 * <h3>Supported file types</h3>
 * <ul>
 *   <li><strong>Images</strong> (JPEG, PNG, and any format supported by
 *       {@link javax.imageio.ImageIO}) — uses a binary search over JPEG
 *       quality to find the highest quality that fits the target size, then
 *       gradually reduces dimensions if quality search is insufficient.</li>
 *   <li><strong>PDF</strong> — strips optional metadata, then iteratively
 *       re-encodes all embedded raster images at progressively lower JPEG
 *       quality levels using Apache PDFBox.  Text and vector content are
 *       never modified or rasterised.</li>
 * </ul>
 *
 * <h3>Image compression algorithm</h3>
 * <ol>
 *   <li>If already ≤ target → copy/return as-is.</li>
 *   <li>Flatten transparency to white (required for JPEG).</li>
 *   <li>Binary-search JPEG quality at the original dimensions (≈ 13 probes).</li>
 *   <li>If still too large, scale dimensions down by {@value #SCALE_STEP} and
 *       repeat — up to {@value #MAX_RESIZE_ITERATIONS} times.</li>
 * </ol>
 *
 * <h3>PDF compression algorithm</h3>
 * <ol>
 *   <li>If already ≤ target → return as-is.</li>
 *   <li>Strip XMP metadata stream and document-info dictionary entries.</li>
 *   <li>Re-encode every raster image XObject as JPEG at decreasing quality
 *       steps ({@value #PDF_QUALITY_STEPS_DESC}) until the result fits.</li>
 *   <li>Form XObjects (reusable content streams) are recursed into so that
 *       images used on multiple pages are also recompressed.</li>
 *   <li>If no quality level meets the target, the smallest result is
 *       returned as a best effort.</li>
 * </ol>
 *
 * <p>Only the JDK ({@code javax.imageio}, {@code java.awt}, {@code java.io})
 * and Apache PDFBox 2.x (for PDF support) are used.</p>
 *
 * @author Musaib
 */
public final class ImageCompressionUtil {

    // -----------------------------------------------------------------------
    // Configuration constants
    // -----------------------------------------------------------------------

    /** Default target file size in bytes (100 KB). */
    public static final long DEFAULT_TARGET_SIZE_BYTES = 100L * 1024L; // 102 400

    /**
     * Minimum JPEG quality that is still considered "acceptable".
     * Below this threshold we prefer to reduce dimensions instead of continuing
     * to degrade quality at the same size.
     */
    private static final float MIN_QUALITY = 0.10f;

    /**
     * Maximum JPEG quality (1.0 = lossless as far as the JPEG encoder allows).
     */
    private static final float MAX_QUALITY = 1.00f;

    /**
     * Each resize step shrinks the image to this fraction of its current size.
     * 0.90 means 10 % dimension reduction per step.
     */
    private static final double SCALE_STEP = 0.90;

    /**
     * Hard limit on the number of dimension-reduction iterations.
     * Prevents an infinite loop if even tiny images exceed the target size.
     */
    private static final int MAX_RESIZE_ITERATIONS = 20;

    /**
     * Binary-search convergence threshold.
     * The search stops when the quality range is narrower than this value.
     */
    private static final float QUALITY_PRECISION = 0.005f;

    /** JPEG format name used with ImageIO. */
    private static final String FORMAT_JPEG = "jpeg";

    // -----------------------------------------------------------------------
    // PDF-specific constants
    // -----------------------------------------------------------------------

    /**
     * JPEG quality levels tried in order for embedded PDF images.
     * Each level is applied to the metadata-cleaned PDF until the result fits
     * within the target size.  Applied independently from the cleaned source
     * (not accumulated), so quality never degrades more than necessary.
     */
    private static final float[] PDF_QUALITY_STEPS = { 0.80f, 0.65f, 0.50f, 0.35f, 0.20f };

    /**
     * Human-readable description of {@link #PDF_QUALITY_STEPS} — used in the
     * class Javadoc {@code @value} reference.
     */
    private static final String PDF_QUALITY_STEPS_DESC = "80 % → 65 % → 50 % → 35 % → 20 %";

    /**
     * Minimum pixel dimension (width or height) for a PDF-embedded image to be
     * eligible for recompression.  Tiny images (icons, decorations) are skipped
     * to avoid introducing visible artefacts on small graphics.
     */
    private static final int PDF_IMAGE_MIN_DIMENSION = 32;

    // Utility class — no instances.
    private ImageCompressionUtil() {}

    // -----------------------------------------------------------------------
    // Public API
    // -----------------------------------------------------------------------

    /**
     * Compresses {@code inputFile} so that the output does not exceed
     * {@link #DEFAULT_TARGET_SIZE_BYTES} (100 KB) and writes the result to
     * {@code outputFile}.
     *
     * @param inputFile  source image (JPEG, PNG, BMP, GIF, WBMP, …)
     * @param outputFile destination file (will be created or overwritten)
     * @throws IOException if the image cannot be read or written
     */
    public static void compressToTargetSize(File inputFile, File outputFile)
            throws IOException {
        compressToTargetSize(inputFile, outputFile, DEFAULT_TARGET_SIZE_BYTES);
    }

    /**
     * Compresses {@code inputFile} so that the output does not exceed
     * {@code targetSizeBytes} and writes the result to {@code outputFile}.
     *
     * <p>If the source file is already within the size limit it is copied
     * unchanged (no quality loss).</p>
     *
     * @param inputFile       source image (JPEG, PNG, BMP, GIF, WBMP, …)
     * @param outputFile      destination file (will be created or overwritten)
     * @param targetSizeBytes maximum allowed output size in bytes
     * @throws IOException              if the image cannot be read or written
     * @throws IllegalArgumentException if targetSizeBytes ≤ 0
     */
    public static void compressToTargetSize(File inputFile,
                                             File outputFile,
                                             long targetSizeBytes)
            throws IOException {

        if (targetSizeBytes <= 0) {
            throw new IllegalArgumentException(
                    "targetSizeBytes must be positive, got: " + targetSizeBytes);
        }

        // ------------------------------------------------------------------
        // Step 1 — Early exit if the file already fits
        // ------------------------------------------------------------------
        long originalSize = inputFile.length();
        System.out.printf("[ImageCompressionUtil] Source: %s  (%.1f KB)%n",
                inputFile.getName(), originalSize / 1024.0);

        if (originalSize <= targetSizeBytes) {
            System.out.println("[ImageCompressionUtil] Already within target — copying unchanged.");
            copyFile(inputFile, outputFile);
            return;
        }

        // ------------------------------------------------------------------
        // Step 2 — Decode source image
        // ------------------------------------------------------------------
        BufferedImage sourceImage = ImageIO.read(inputFile);
        if (sourceImage == null) {
            throw new IOException("ImageIO could not decode: " + inputFile.getAbsolutePath());
        }

        // ------------------------------------------------------------------
        // Step 3 — Flatten transparency (required before JPEG encoding)
        // ------------------------------------------------------------------
        BufferedImage workingImage = flattenTransparency(sourceImage);

        // ------------------------------------------------------------------
        // Step 4 — Binary-search loop with optional dimension reduction
        // ------------------------------------------------------------------
        /*
         * We keep track of the best result seen across ALL dimension levels so
         * we can write whichever byte[] fits within the target — favouring the
         * one that is closest to (but not over) the limit, which means the
         * highest quality.
         */
        byte[] bestBytes  = null;
        int    iterations = 0;

        while (iterations < MAX_RESIZE_ITERATIONS) {
            iterations++;

            int currentWidth  = workingImage.getWidth();
            int currentHeight = workingImage.getHeight();

            System.out.printf("[ImageCompressionUtil] Iteration %d — dimensions %dx%d%n",
                    iterations, currentWidth, currentHeight);

            // Binary-search for the best quality at these dimensions.
            byte[] candidate = binarySearchQuality(workingImage, targetSizeBytes);

            if (candidate != null) {
                // Found a result that fits — this is automatically the best
                // quality at these dimensions; stop immediately because further
                // dimension reductions would only make quality worse.
                bestBytes = candidate;
                System.out.printf("[ImageCompressionUtil] ✓ Fits at %dx%d — %.1f KB%n",
                        currentWidth, currentHeight, candidate.length / 1024.0);
                break;
            }

            // The image couldn't be made small enough at MIN_QUALITY either.
            // Scale down and try again.
            System.out.printf("[ImageCompressionUtil] Cannot fit at %dx%d — scaling down.%n",
                    currentWidth, currentHeight);

            int newWidth  = Math.max(1, (int) Math.round(currentWidth  * SCALE_STEP));
            int newHeight = Math.max(1, (int) Math.round(currentHeight * SCALE_STEP));

            // Safety: stop if dimensions can no longer be reduced.
            if (newWidth == currentWidth && newHeight == currentHeight) {
                System.out.println("[ImageCompressionUtil] Cannot reduce dimensions further — stopping.");
                break;
            }

            workingImage = resizeImage(workingImage, newWidth, newHeight);
        }

        // ------------------------------------------------------------------
        // Step 5 — Write result
        // ------------------------------------------------------------------
        if (bestBytes == null) {
            // Edge case: even the smallest image at MIN_QUALITY exceeded the
            // target.  Write what we have (lowest quality, smallest size) and
            // warn the caller.
            System.out.println("[ImageCompressionUtil] ⚠ Could not reach target size — writing best attempt.");
            bestBytes = encodeJpeg(workingImage, MIN_QUALITY);
        }

        // Ensure parent directory exists.
        File parentDir = outputFile.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        try (FileOutputStream fos = new FileOutputStream(outputFile)) {
            fos.write(bestBytes);
        }

        System.out.printf("[ImageCompressionUtil] Output: %s  (%.1f KB)%n",
                outputFile.getName(), bestBytes.length / 1024.0);
    }

    // -----------------------------------------------------------------------
    // In-memory API (no temp files — suited for servlet/service usage)
    // -----------------------------------------------------------------------

    /**
     * Compresses raw image bytes so the result does not exceed
     * {@link #DEFAULT_TARGET_SIZE_BYTES} (100 KB).
     *
     * <p>This is the preferred entry point when the image arrives as a
     * {@code MultipartFile} (or any {@code byte[]}) and there is no need to
     * write a temporary file to disk.</p>
     *
     * <p>If the input bytes are already within the target size they are
     * returned <em>unchanged</em> — no quality loss is applied.</p>
     *
     * <p>If the byte array does not represent a decodable image (e.g. a PDF)
     * it is returned unchanged so the caller can handle it normally.</p>
     *
     * @param rawBytes  raw bytes of the uploaded file (any format
     *                  supported by {@link javax.imageio.ImageIO})
     * @return compressed JPEG bytes, or the original {@code rawBytes} if the
     *         input is already small enough / not an image
     * @throws IOException if JPEG encoding unexpectedly fails
     */
    public static byte[] compressImageBytes(byte[] rawBytes) throws IOException {
        return compressImageBytes(rawBytes, DEFAULT_TARGET_SIZE_BYTES);
    }

    /**
     * Compresses raw image bytes so the result does not exceed
     * {@code targetSizeBytes}.
     *
     * <p>Uses the same binary-search-over-quality / gradual-dimension-reduction
     * strategy as {@link #compressToTargetSize(File, File, long)}, but
     * operates entirely in memory — no temporary files are created.</p>
     *
     * <h4>Strategy</h4>
     * <ol>
     *   <li>If {@code rawBytes.length <= targetSizeBytes} → return as-is.</li>
     *   <li>Decode with {@link javax.imageio.ImageIO#read}.
     *       If the bytes are not a recognised image (e.g. a PDF) → return
     *       as-is.</li>
     *   <li>Flatten transparency onto a white background.</li>
     *   <li>Binary-search JPEG quality at the original dimensions.</li>
     *   <li>If still too large, reduce dimensions by {@value #SCALE_STEP}
     *       each step and repeat — up to {@value #MAX_RESIZE_ITERATIONS}
     *       times.</li>
     * </ol>
     *
     * @param rawBytes        raw bytes of the uploaded file
     * @param targetSizeBytes maximum allowed output size in bytes (must be &gt; 0)
     * @return compressed JPEG bytes, or the original bytes if already within
     *         target or not an image format
     * @throws IOException              if JPEG encoding fails
     * @throws IllegalArgumentException if {@code targetSizeBytes} ≤ 0
     */
    public static byte[] compressImageBytes(byte[] rawBytes, long targetSizeBytes)
            throws IOException {

        if (targetSizeBytes <= 0) {
            throw new IllegalArgumentException(
                    "targetSizeBytes must be positive, got: " + targetSizeBytes);
        }
        if (rawBytes == null || rawBytes.length == 0) {
            return rawBytes;
        }

        // Already small enough — nothing to do.
        if (rawBytes.length <= targetSizeBytes) {
            return rawBytes;
        }

        // Decode image from the in-memory bytes.
        BufferedImage sourceImage = ImageIO.read(new ByteArrayInputStream(rawBytes));
        if (sourceImage == null) {
            // Not a decodable image (e.g. PDF) — return unchanged.
            return rawBytes;
        }

        System.out.printf("[ImageCompressionUtil] In-memory compress: %.1f KB → target %.1f KB%n",
                rawBytes.length / 1024.0, targetSizeBytes / 1024.0);

        // Flatten transparency before JPEG encoding.
        BufferedImage workingImage = flattenTransparency(sourceImage);

        // Binary-search quality + optional dimension-reduction loop.
        byte[] bestBytes  = null;
        int    iterations = 0;

        while (iterations < MAX_RESIZE_ITERATIONS) {
            iterations++;

            int w = workingImage.getWidth();
            int h = workingImage.getHeight();
            System.out.printf("[ImageCompressionUtil] Iteration %d — %dx%d%n", iterations, w, h);

            byte[] candidate = binarySearchQuality(workingImage, targetSizeBytes);

            if (candidate != null) {
                bestBytes = candidate;
                System.out.printf("[ImageCompressionUtil] ✓ %.1f KB fits at %dx%d%n",
                        candidate.length / 1024.0, w, h);
                break;
            }

            // Cannot fit at MIN_QUALITY — scale down by SCALE_STEP.
            int newW = Math.max(1, (int) Math.round(w * SCALE_STEP));
            int newH = Math.max(1, (int) Math.round(h * SCALE_STEP));
            if (newW == w && newH == h) {
                System.out.println("[ImageCompressionUtil] Dimensions cannot shrink further — stopping.");
                break;
            }
            workingImage = resizeImage(workingImage, newW, newH);
        }

        if (bestBytes == null) {
            // Fallback: encode at minimum quality as a last resort.
            System.out.println("[ImageCompressionUtil] ⚠ Could not reach target — using MIN_QUALITY.");
            bestBytes = encodeJpeg(workingImage, MIN_QUALITY);
        }

        System.out.printf("[ImageCompressionUtil] Result: %.1f KB%n", bestBytes.length / 1024.0);
        return bestBytes;
    }

    // -----------------------------------------------------------------------
    // Unified dispatcher — image OR PDF (suited for MultipartFile uploads)
    // -----------------------------------------------------------------------

    /**
     * Compresses {@code rawBytes} to the default 100 KB target, automatically
     * choosing image or PDF compression based on {@code contentType}.
     *
     * <ul>
     *   <li>{@code image/*}             → {@link #compressImageBytes(byte[])}</li>
     *   <li>{@code application/pdf}     → {@link #compressPdfBytes(byte[])}</li>
     *   <li>anything else / {@code null} → returned unchanged</li>
     * </ul>
     *
     * <p>This is the recommended call-site for servlet/service code where the
     * MIME type is already known (e.g. from {@code MultipartFile.getContentType()}).</p>
     *
     * @param rawBytes    raw bytes of the uploaded file
     * @param contentType MIME type of the file (may be {@code null})
     * @return compressed bytes, or the original bytes if already within target
     *         / type is unsupported
     * @throws IOException if compression fails
     */
    public static byte[] compressFileBytes(byte[] rawBytes, String contentType)
            throws IOException {
        return compressFileBytes(rawBytes, contentType, DEFAULT_TARGET_SIZE_BYTES);
    }

    /**
     * Compresses {@code rawBytes} to {@code targetSizeBytes}, automatically
     * choosing image or PDF compression based on {@code contentType}.
     *
     * @param rawBytes        raw bytes of the uploaded file
     * @param contentType     MIME type of the file (may be {@code null})
     * @param targetSizeBytes maximum allowed output size in bytes (must be &gt; 0)
     * @return compressed bytes, or the original bytes if already within target
     *         / type is unsupported
     * @throws IOException              if compression fails
     * @throws IllegalArgumentException if {@code targetSizeBytes} ≤ 0
     */
    public static byte[] compressFileBytes(byte[] rawBytes,
                                            String contentType,
                                            long targetSizeBytes)
            throws IOException {

        if (rawBytes == null || rawBytes.length == 0) {
            return rawBytes;
        }
        if (contentType == null) {
            return rawBytes;
        }

        String ct = contentType.toLowerCase();
        if (ct.contains("pdf")) {
            return compressPdfBytes(rawBytes, targetSizeBytes);
        } else if (ct.startsWith("image/")) {
            return compressImageBytes(rawBytes, targetSizeBytes);
        }

        // Unsupported type — return unchanged.
        return rawBytes;
    }

    // -----------------------------------------------------------------------
    // PDF compression — public API
    // -----------------------------------------------------------------------

    /**
     * Compresses the raw bytes of a PDF document so the result does not exceed
     * {@link #DEFAULT_TARGET_SIZE_BYTES} (100 KB).
     *
     * <p>If the input is already within the target size it is returned
     * unchanged.  If the bytes do not represent a valid/decodable PDF they
     * are returned unchanged.</p>
     *
     * @param rawBytes raw bytes of a PDF file
     * @return compressed PDF bytes, or the original bytes if already within target
     * @throws IOException if PDF processing fails
     */
    public static byte[] compressPdfBytes(byte[] rawBytes) throws IOException {
        return compressPdfBytes(rawBytes, DEFAULT_TARGET_SIZE_BYTES);
    }

    /**
     * Compresses the raw bytes of a PDF document so the result does not exceed
     * {@code targetSizeBytes}.
     *
     * <h4>Strategy</h4>
     * <ol>
     *   <li>Early exit if already ≤ {@code targetSizeBytes}.</li>
     *   <li>Strip XMP metadata stream and document-information dictionary entries
     *       (non-destructive — text and images are untouched).</li>
     *   <li>Re-encode all embedded raster images (including those nested inside
     *       Form XObjects) as JPEG at decreasing quality levels
     *       ({@value #PDF_QUALITY_STEPS_DESC}) until the result fits.
     *       Each level is applied independently from the cleaned source so
     *       quality degradation is minimised.</li>
     *   <li>If no level reaches the target the smallest result obtained is
     *       returned as a best effort.</li>
     * </ol>
     *
     * <p>Text, fonts, and vector graphics are <strong>never modified</strong>.</p>
     *
     * <p>Encrypted PDFs are detected and returned unchanged to avoid
     * corruption.</p>
     *
     * @param rawBytes        raw bytes of a PDF file
     * @param targetSizeBytes maximum allowed output size in bytes (must be &gt; 0)
     * @return compressed PDF bytes, or the original bytes if already within
     *         target / not a valid PDF / encrypted
     * @throws IOException              if PDF processing fails
     * @throws IllegalArgumentException if {@code targetSizeBytes} ≤ 0
     */
    public static byte[] compressPdfBytes(byte[] rawBytes, long targetSizeBytes)
            throws IOException {

        if (targetSizeBytes <= 0) {
            throw new IllegalArgumentException(
                    "targetSizeBytes must be positive, got: " + targetSizeBytes);
        }
        if (rawBytes == null || rawBytes.length == 0) {
            return rawBytes;
        }

        // Early exit — already within target.
        if (rawBytes.length <= targetSizeBytes) {
            return rawBytes;
        }

        System.out.printf("[ImageCompressionUtil] PDF compress: %.1f KB → target %.1f KB%n",
                rawBytes.length / 1024.0, targetSizeBytes / 1024.0);

        // ------------------------------------------------------------------
        // Step 1 — Remove metadata (cheap, never degrades visible content)
        // ------------------------------------------------------------------
        byte[] cleaned;
        try {
            cleaned = stripPdfMetadata(rawBytes);
            System.out.printf("[ImageCompressionUtil] After metadata strip: %.1f KB%n",
                    cleaned.length / 1024.0);
        } catch (IOException e) {
            System.err.println("[ImageCompressionUtil] Metadata strip failed — using original: "
                    + e.getMessage());
            cleaned = rawBytes;
        }

        if (cleaned.length <= targetSizeBytes) {
            System.out.println("[ImageCompressionUtil] ✓ PDF fits after metadata strip.");
            return cleaned;
        }

        // ------------------------------------------------------------------
        // Step 2 — Re-encode embedded images at decreasing quality levels.
        //          Each attempt is made on `cleaned` (not on the previous
        //          attempt's output) to avoid cascading quality loss.
        // ------------------------------------------------------------------
        byte[] bestResult = cleaned; // fallback: cleaned but not image-compressed

        for (float quality : PDF_QUALITY_STEPS) {
            System.out.printf("[ImageCompressionUtil] PDF image re-encode @ %.0f%%%n",
                    quality * 100);
            try {
                byte[] candidate = recompressPdfImages(cleaned, quality);
                System.out.printf("[ImageCompressionUtil] Size @ %.0f%%: %.1f KB%n",
                        quality * 100, candidate.length / 1024.0);

                // Track the smallest result seen so far.
                if (candidate.length < bestResult.length) {
                    bestResult = candidate;
                }

                if (candidate.length <= targetSizeBytes) {
                    System.out.printf(
                            "[ImageCompressionUtil] ✓ PDF fits @ quality %.0f%%%n",
                            quality * 100);
                    return candidate;
                }
            } catch (IOException e) {
                System.err.printf(
                        "[ImageCompressionUtil] PDF re-encode @ %.0f%% failed: %s%n",
                        quality * 100, e.getMessage());
            }
        }

        // Best effort — return whatever is smallest.
        System.out.printf(
                "[ImageCompressionUtil] ⚠ PDF best effort: %.1f KB (target: %.1f KB)%n",
                bestResult.length / 1024.0, targetSizeBytes / 1024.0);
        return bestResult;
    }

    // -----------------------------------------------------------------------
    // PDF compression — private helpers
    // -----------------------------------------------------------------------

    /**
     * Loads the PDF, clears its XMP metadata stream and document-information
     * dictionary, then saves it back to a byte array.
     *
     * <p>This step is cheap (no image decoding) and can recover several KB
     * on PDFs produced by applications that embed verbose metadata.</p>
     *
     * @param pdfBytes raw PDF bytes
     * @return PDF bytes with metadata removed
     * @throws IOException if PDFBox cannot load or save the document
     */
    private static byte[] stripPdfMetadata(byte[] pdfBytes) throws IOException {
        try (PDDocument doc = PDDocument.load(new ByteArrayInputStream(pdfBytes))) {

            if (doc.isEncrypted()) {
                // Cannot safely modify an encrypted PDF — return as-is.
                System.out.println("[ImageCompressionUtil] PDF is encrypted — skipping metadata strip.");
                return pdfBytes;
            }

            // Replace the document-information dictionary with an empty one.
            doc.setDocumentInformation(new PDDocumentInformation());

            // Remove the XMP metadata stream (often several KB of XML).
            doc.getDocumentCatalog().setMetadata(null);

            return savePdfToBytes(doc);
        }
    }

    /**
     * Loads the PDF, walks every page's resource dictionary (recursing into
     * Form XObjects), re-encodes every raster image XObject as JPEG at
     * {@code quality}, and saves the result.
     *
     * <p>Text, fonts, and vector graphics are left completely untouched.</p>
     *
     * <p>Images smaller than {@value #PDF_IMAGE_MIN_DIMENSION} px in either
     * dimension are skipped to avoid artefacts on icons and small decorations.</p>
     *
     * @param pdfBytes raw PDF bytes (should already have metadata stripped)
     * @param quality  JPEG quality 0.0–1.0
     * @return modified PDF bytes
     * @throws IOException if PDFBox cannot load or save the document
     */
    private static byte[] recompressPdfImages(byte[] pdfBytes, float quality)
            throws IOException {

        try (PDDocument doc = PDDocument.load(new ByteArrayInputStream(pdfBytes))) {

            if (doc.isEncrypted()) {
                return pdfBytes;
            }

            for (PDPage page : doc.getPages()) {
                PDResources resources = page.getResources();
                if (resources != null) {
                    recompressImagesInResources(doc, resources, quality);
                }
            }

            return savePdfToBytes(doc);
        }
    }

    /**
     * Recursively walks {@code resources}, re-encoding each
     * {@link PDImageXObject} as JPEG and replacing it in the resource
     * dictionary.  {@link PDFormXObject}s are recursed into so that images
     * shared across pages are also recompressed.
     *
     * @param doc       the owning {@link PDDocument} (needed by
     *                  {@link JPEGFactory})
     * @param resources the resource dictionary to walk
     * @param quality   JPEG quality 0.0–1.0
     */
    private static void recompressImagesInResources(PDDocument doc,
                                                     PDResources resources,
                                                     float quality) {
        Iterable<COSName> xObjectNames;
        try {
            xObjectNames = resources.getXObjectNames();
        } catch (Exception e) {
            // Cannot read XObject names — skip this resource dictionary.
            return;
        }

        for (COSName name : xObjectNames) {
            PDXObject xobject;
            try {
                xobject = resources.getXObject(name);
            } catch (IOException e) {
                // Cannot load this XObject — skip it.
                continue;
            }

            if (xobject instanceof PDImageXObject) {
                // ---- Raster image — recompress as JPEG ----
                PDImageXObject pdfImage = (PDImageXObject) xobject;

                // Skip tiny images (icons, watermarks, decorations).
                if (pdfImage.getWidth()  < PDF_IMAGE_MIN_DIMENSION
                        || pdfImage.getHeight() < PDF_IMAGE_MIN_DIMENSION) {
                    continue;
                }

                try {
                    // Decode the image.  May be large for scanned pages.
                    BufferedImage buffered = pdfImage.getImage();
                    if (buffered == null) {
                        continue;
                    }

                    // JPEG has no alpha channel — flatten any transparency.
                    buffered = flattenTransparency(buffered);

                    // Re-encode using PDFBox's JPEGFactory so the image stream
                    // is correctly wrapped with the required PDF metadata.
                    PDImageXObject newImage =
                            JPEGFactory.createFromImage(doc, buffered, quality);

                    // Replace the old image in the XObjects sub-dictionary.
                    COSBase xobjectsBase =
                            resources.getCOSObject().getDictionaryObject(COSName.XOBJECT);
                    if (xobjectsBase instanceof COSDictionary) {
                        ((COSDictionary) xobjectsBase).setItem(name, newImage);
                    }

                } catch (Exception e) {
                    // Log and skip — never abort the whole PDF for one bad image.
                    System.err.printf(
                            "[ImageCompressionUtil] Skipping PDF image '%s': %s%n",
                            name.getName(), e.getMessage());
                }

            } else if (xobject instanceof PDFormXObject) {
                // ---- Form XObject — recurse so shared images are processed ----
                PDFormXObject form = (PDFormXObject) xobject;
                PDResources formResources = form.getResources();
                if (formResources != null) {
                    recompressImagesInResources(doc, formResources, quality);
                }
            }
        }
    }

    /**
     * Saves {@code doc} to a {@link ByteArrayOutputStream} and returns the
     * bytes.  PDFBox 2.x saves with object-stream compression (PDF 1.5+)
     * enabled by default, which provides additional size savings over PDF 1.4.
     *
     * @param doc the document to save
     * @return the serialised PDF bytes
     * @throws IOException if saving fails
     */
    private static byte[] savePdfToBytes(PDDocument doc) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        doc.save(baos);
        return baos.toByteArray();
    }

    // -----------------------------------------------------------------------
    // Private helpers
    // -----------------------------------------------------------------------

    /**
     * Performs a binary search over the JPEG quality range
     * [{@link #MIN_QUALITY}, {@link #MAX_QUALITY}] to find the highest quality
     * whose encoded output still fits within {@code targetBytes}.
     *
     * <p>The binary search converges in ≈ log₂((MAX-MIN)/PRECISION) ≈ 13
     * iterations, making it much faster than a linear scan.</p>
     *
     * @param img         the image to encode (must be TYPE_INT_RGB)
     * @param targetBytes maximum allowed byte count for the encoded output
     * @return the encoded JPEG bytes at the best fitting quality, or
     *         {@code null} if even {@link #MIN_QUALITY} exceeds the target
     * @throws IOException if JPEG encoding fails
     */
    private static byte[] binarySearchQuality(BufferedImage img, long targetBytes)
            throws IOException {

        // Quick check: is the image encodable at MIN_QUALITY within the limit?
        byte[] loBytes = encodeJpeg(img, MIN_QUALITY);
        if (loBytes.length > targetBytes) {
            // Even the lowest quality doesn't fit — signal to caller to resize.
            return null;
        }

        // Quick check: is MAX_QUALITY already within the limit?
        byte[] hiBytes = encodeJpeg(img, MAX_QUALITY);
        if (hiBytes.length <= targetBytes) {
            // Perfect — no quality loss needed at all.
            return hiBytes;
        }

        // Binary search between MIN_QUALITY and MAX_QUALITY.
        float lo      = MIN_QUALITY;
        float hi      = MAX_QUALITY;
        byte[] bestFit = loBytes; // loBytes is guaranteed to fit

        while ((hi - lo) > QUALITY_PRECISION) {
            float mid = (lo + hi) / 2.0f;
            byte[] midBytes = encodeJpeg(img, mid);

            if (midBytes.length <= targetBytes) {
                // midBytes fits — record it and search higher quality.
                bestFit = midBytes;
                lo = mid;
            } else {
                // midBytes too large — search lower quality.
                hi = mid;
            }
        }

        return bestFit;
    }

    /**
     * Encodes {@code img} as a JPEG byte array at the given quality.
     *
     * @param img     source image (should be TYPE_INT_RGB for correct output)
     * @param quality JPEG quality in the range [0.0, 1.0]
     * @return encoded bytes
     * @throws IOException if no JPEG writer is available or encoding fails
     */
    private static byte[] encodeJpeg(BufferedImage img, float quality)
            throws IOException {

        // Locate the built-in JPEG ImageWriter.
        Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName(FORMAT_JPEG);
        if (!writers.hasNext()) {
            throw new IOException("No JPEG ImageWriter available on this JVM.");
        }
        ImageWriter writer = writers.next();

        try {
            ImageWriteParam params = writer.getDefaultWriteParam();
            // Enable explicit quality control.
            params.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            params.setCompressionQuality(quality);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // MemoryCacheImageOutputStream is fully in-memory — no temp files.
            try (MemoryCacheImageOutputStream imos = new MemoryCacheImageOutputStream(baos)) {
                writer.setOutput(imos);
                writer.write(null, new IIOImage(img, null, null), params);
                // Flush so all bytes are in baos before we read its size.
                imos.flush();
            }
            return baos.toByteArray();

        } finally {
            // Always release native resources held by the writer.
            writer.dispose();
        }
    }

    /**
     * Converts any image type that contains an alpha channel (or uses a
     * palette) into a plain {@code TYPE_INT_RGB} image with a white
     * background.
     *
     * <p>This is necessary because the JPEG format has no alpha channel.
     * Without this step, transparent pixels would be rendered as black by
     * many JPEG encoders.</p>
     *
     * @param src source image (may be any {@link BufferedImage} type)
     * @return a new {@code TYPE_INT_RGB} image; the original is returned
     *         unchanged if it is already {@code TYPE_INT_RGB}
     */
    private static BufferedImage flattenTransparency(BufferedImage src) {
        if (src.getType() == BufferedImage.TYPE_INT_RGB) {
            // Already the correct type — no conversion needed.
            return src;
        }

        int w = src.getWidth();
        int h = src.getHeight();

        BufferedImage rgb = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = rgb.createGraphics();
        try {
            // Fill with white so transparent areas become white, not black.
            g.setColor(java.awt.Color.WHITE);
            g.fillRect(0, 0, w, h);
            // Apply high-quality rendering hints even for the flatten pass.
            g.setRenderingHint(RenderingHints.KEY_RENDERING,
                               RenderingHints.VALUE_RENDER_QUALITY);
            g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,
                               RenderingHints.VALUE_COLOR_RENDER_QUALITY);
            g.drawImage(src, 0, 0, null);
        } finally {
            g.dispose();
        }
        return rgb;
    }

    /**
     * Scales {@code src} to exactly {@code targetWidth × targetHeight} using
     * bicubic interpolation, which preserves sharpness better than nearest-
     * neighbour or bilinear when reducing dimensions.
     *
     * <p>The aspect ratio is NOT enforced here — the caller is responsible for
     * maintaining it (see the dimension-reduction loop in
     * {@link #compressToTargetSize}).</p>
     *
     * @param src          source image
     * @param targetWidth  desired width in pixels (≥ 1)
     * @param targetHeight desired height in pixels (≥ 1)
     * @return a new {@code TYPE_INT_RGB} image at the requested size
     */
    private static BufferedImage resizeImage(BufferedImage src,
                                              int targetWidth,
                                              int targetHeight) {
        BufferedImage resized = new BufferedImage(
                targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resized.createGraphics();
        try {
            // Bicubic interpolation — best quality for down-scaling.
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                               RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g.setRenderingHint(RenderingHints.KEY_RENDERING,
                               RenderingHints.VALUE_RENDER_QUALITY);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                               RenderingHints.VALUE_ANTIALIAS_ON);
            g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,
                               RenderingHints.VALUE_COLOR_RENDER_QUALITY);
            g.drawImage(src, 0, 0, targetWidth, targetHeight, null);
        } finally {
            g.dispose();
        }
        return resized;
    }

    /**
     * Copies {@code src} byte-for-byte to {@code dst} using NIO.
     *
     * @param src source file
     * @param dst destination file (created or overwritten)
     * @throws IOException if a read/write error occurs
     */
    private static void copyFile(File src, File dst) throws IOException {
        File parentDir = dst.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
        Files.copy(src.toPath(), dst.toPath(),
                   java.nio.file.StandardCopyOption.REPLACE_EXISTING);
    }

    // -----------------------------------------------------------------------
    // Demo entry point
    // -----------------------------------------------------------------------

    /**
     * Demonstrates the utility.
     *
     * <p>Usage: {@code java ImageCompressionUtil <inputFile> <outputFile> [targetKB]}</p>
     *
     * <p>Example (compresses to 100 KB default):
     * <pre>java -cp . org.ideoholic.curium.util.ImageCompressionUtil photo.jpg compressed.jpg</pre>
     * </p>
     *
     * <p>Example (custom target of 50 KB):
     * <pre>java -cp . org.ideoholic.curium.util.ImageCompressionUtil photo.jpg compressed.jpg 50</pre>
     * </p>
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Usage: ImageCompressionUtil <inputFile> <outputFile> [targetKB]");
            System.err.println("  targetKB  — optional, defaults to 100 (= 100 KB)");
            System.exit(1);
        }

        File   input      = new File(args[0]);
        File   output     = new File(args[1]);
        long   targetBytes = DEFAULT_TARGET_SIZE_BYTES;

        if (args.length >= 3) {
            try {
                targetBytes = Long.parseLong(args[2]) * 1024L;
            } catch (NumberFormatException e) {
                System.err.println("Invalid targetKB value: " + args[2]);
                System.exit(1);
            }
        }

        if (!input.exists() || !input.isFile()) {
            System.err.println("Input file not found: " + input.getAbsolutePath());
            System.exit(1);
        }

        try {
            long before = input.length();
            compressToTargetSize(input, output, targetBytes);
            long after  = output.length();

            System.out.println();
            System.out.println("=== Summary ===");
            System.out.printf("  Input  : %s  (%.1f KB)%n", input.getName(),  before / 1024.0);
            System.out.printf("  Output : %s  (%.1f KB)%n", output.getName(), after  / 1024.0);
            System.out.printf("  Target : %.1f KB%n", targetBytes / 1024.0);
            System.out.printf("  Saved  : %.1f KB (%.0f%% reduction)%n",
                    (before - after) / 1024.0,
                    before > 0 ? (before - after) * 100.0 / before : 0);

        } catch (IOException e) {
            System.err.println("Compression failed: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}
