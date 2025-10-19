<%--
    SMART IMAGE RESOLUTION DEMO 🖼️
    ===============================
    
    This demonstrates how to use the new smart image resolution system.
    Images will automatically try tenant-specific versions first, then fallback to defaults.
--%>

<%@ include file="/WEB-INF/jsp/common/_tenant_globals.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <title>Smart Image Resolution Demo - ${tenantName}</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        .demo-section { margin: 20px 0; padding: 15px; border: 1px solid #ccc; }
        .image-demo img { max-width: 200px; margin: 10px; border: 2px solid #ddd; }
        .code-example { background: #f5f5f5; padding: 10px; margin: 10px 0; font-family: monospace; }
    </style>
</head>
<body style="background-image: url('${backgroundUrl}'); background-image: url('${backgroundFallback}') /* fallback */;">

    <h1>🖼️ Smart Image Resolution Demo</h1>
    <p><strong>Current Tenant:</strong> ${tenant}</p>
    <p><strong>Tenant Name:</strong> ${tenantName}</p>

    <div class="demo-section">
        <h2>Method 1: Using Smart Image URLs (Recommended)</h2>
        <div class="image-demo">
            <div>
                <h3>Logo with Automatic Fallback</h3>
                <img src="${logoUrl}" onerror="this.src='${logoFallback}'" alt="Logo" />
                <div class="code-example">
                    &lt;img src="${logoUrl}" onerror="this.src='${logoFallback}'" alt="Logo" /&gt;
                </div>
            </div>
            
            <div>
                <h3>Signature with Automatic Fallback</h3>
                <img src="${signatureUrl}" onerror="this.src='${signatureFallback}'" alt="Signature" />
                <div class="code-example">
                    &lt;img src="${signatureUrl}" onerror="this.src='${signatureFallback}'" alt="Signature" /&gt;
                </div>
            </div>
        </div>
    </div>

    <div class="demo-section">
        <h2>Method 2: Using JavaScript Smart Loader</h2>
        <div class="image-demo">
            <div>
                <h3>Logo (Auto-initialized)</h3>
                <img data-smart-image="logo" alt="Auto Logo" />
                <div class="code-example">
                    &lt;img data-smart-image="logo" alt="Auto Logo" /&gt;
                </div>
            </div>
            
            <div>
                <h3>Background (Auto-initialized)</h3>
                <img data-smart-image="background" alt="Auto Background" style="max-height: 100px;" />
                <div class="code-example">
                    &lt;img data-smart-image="background" alt="Auto Background" /&gt;
                </div>
            </div>
        </div>
    </div>

    <div class="demo-section">
        <h2>Method 3: CSS Background Images</h2>
        <div id="css-background-demo" style="width: 200px; height: 100px; border: 2px solid #ddd; margin: 10px;">
            <p style="text-align: center; line-height: 100px; color: white; text-shadow: 1px 1px 2px black;">Logo Background</p>
        </div>
        <div class="code-example">
            &lt;div id="css-background-demo"&gt;...&lt;/div&gt;<br/>
            &lt;script&gt;setSmartBackground(document.getElementById('css-background-demo'), 'logo');&lt;/script&gt;
        </div>
    </div>

    <div class="demo-section">
        <h2>URL Information</h2>
        <table border="1" cellpadding="5" style="border-collapse: collapse;">
            <tr><th>Image Type</th><th>Primary URL (Tenant-specific)</th><th>Fallback URL (Default)</th></tr>
            <tr><td>Logo</td><td>${logoUrl}</td><td>${logoFallback}</td></tr>
            <tr><td>Background</td><td>${backgroundUrl}</td><td>${backgroundFallback}</td></tr>
            <tr><td>Signature</td><td>${signatureUrl}</td><td>${signatureFallback}</td></tr>
            <tr><td>ID Signature</td><td>${idSignUrl}</td><td>${idSignFallback}</td></tr>
            <tr><td>Favicon</td><td>${faviconUrl}</td><td>${faviconFallback}</td></tr>
        </table>
    </div>

    <div class="demo-section">
        <h2>JavaScript Variables Available</h2>
        <div class="code-example" id="js-variables">
            Loading JavaScript variables...
        </div>
    </div>

    <script>
        // Set CSS background using smart loader
        setSmartBackground(document.getElementById('css-background-demo'), 'logo');
        
        // Display available JavaScript variables
        document.getElementById('js-variables').innerHTML = 
            'window.LOGO_URL = "' + window.LOGO_URL + '"<br/>' +
            'window.LOGO_FALLBACK = "' + window.LOGO_FALLBACK + '"<br/>' +
            'window.BACKGROUND_URL = "' + window.BACKGROUND_URL + '"<br/>' +
            'window.BACKGROUND_FALLBACK = "' + window.BACKGROUND_FALLBACK + '"<br/>' +
            'window.SIGNATURE_URL = "' + window.SIGNATURE_URL + '"<br/>' +
            'window.SIGNATURE_FALLBACK = "' + window.SIGNATURE_FALLBACK + '"';
    </script>

</body>
</html>

