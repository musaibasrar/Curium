/**
 * SMART IMAGE LOADER 🖼️
 * ===================
 * 
 * Provides intelligent image loading with tenant-specific fallbacks.
 * Automatically handles missing tenant images by falling back to defaults.
 */

// Global function for smart image loading
window.loadSmartImage = function(img, primaryUrl, fallbackUrl) {
    if (!img || !primaryUrl) return;
    
    // Try primary URL first
    img.src = primaryUrl;
    
    // If primary fails, use fallback
    img.onerror = function() {
        if (fallbackUrl && img.src !== fallbackUrl) {
            console.log('🖼️ Fallback: ' + primaryUrl + ' → ' + fallbackUrl);
            img.src = fallbackUrl;
            
            // If fallback also fails, use a placeholder
            img.onerror = function() {
                console.warn('⚠️ Both primary and fallback images failed:', primaryUrl, fallbackUrl);
                img.style.background = '#f0f0f0';
                img.style.border = '2px dashed #ccc';
                img.alt = 'Image not found';
            };
        }
    };
};

// Auto-initialize smart images on page load
document.addEventListener('DOMContentLoaded', function() {
    // Find all images with data-smart-image attribute
    const smartImages = document.querySelectorAll('img[data-smart-image]');
    
    smartImages.forEach(function(img) {
        const imageType = img.getAttribute('data-smart-image');
        let primaryUrl = '';
        let fallbackUrl = '';
        
        // Determine URLs based on image type
        switch(imageType) {
            case 'logo':
                primaryUrl = window.LOGO_URL;
                fallbackUrl = window.LOGO_FALLBACK;
                break;
            case 'background':
                primaryUrl = window.BACKGROUND_URL;
                fallbackUrl = window.BACKGROUND_FALLBACK;
                break;
            case 'signature':
                primaryUrl = window.SIGNATURE_URL;
                fallbackUrl = window.SIGNATURE_FALLBACK;
                break;
            default:
                console.warn('Unknown smart image type:', imageType);
                return;
        }
        
        loadSmartImage(img, primaryUrl, fallbackUrl);
    });
    
    console.log('🖼️ Smart Image Loader: Initialized ' + smartImages.length + ' images');
});

// CSS Background Image Helper
window.setSmartBackground = function(element, imageType) {
    if (!element) return;
    
    let primaryUrl = '';
    let fallbackUrl = '';
    
    switch(imageType) {
        case 'logo':
            primaryUrl = window.LOGO_URL;
            fallbackUrl = window.LOGO_FALLBACK;
            break;
        case 'background':
            primaryUrl = window.BACKGROUND_URL;
            fallbackUrl = window.BACKGROUND_FALLBACK;
            break;
        case 'signature':
            primaryUrl = window.SIGNATURE_URL;
            fallbackUrl = window.SIGNATURE_FALLBACK;
            break;
    }
    
    if (primaryUrl) {
        // Test if primary image exists
        const testImg = new Image();
        testImg.onload = function() {
            element.style.backgroundImage = 'url(' + primaryUrl + ')';
        };
        testImg.onerror = function() {
            console.log('🖼️ Background fallback: ' + primaryUrl + ' → ' + fallbackUrl);
            element.style.backgroundImage = 'url(' + fallbackUrl + ')';
        };
        testImg.src = primaryUrl;
    }
};

