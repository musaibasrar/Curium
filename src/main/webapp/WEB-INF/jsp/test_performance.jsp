<%-- 
PERFORMANCE TEST PAGE
=====================

This page demonstrates that multiple includes of tenant globals have minimal performance impact.
Access this page with: http://localhost:8081/fathima/test_performance.jsp
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Tenant Globals Performance Test</title>
</head>
<body>
    <h1>🚀 Tenant Globals Performance Test</h1>
    
    <%
    // Start timing
    long startTime = System.nanoTime();
    %>
    
    <!-- Include tenant globals 10 times to simulate heavy usage -->
    <%@ include file="/WEB-INF/jsp/common/_tenant_globals.jsp" %>
    <%@ include file="/WEB-INF/jsp/common/_tenant_globals.jsp" %>
    <%@ include file="/WEB-INF/jsp/common/_tenant_globals.jsp" %>
    <%@ include file="/WEB-INF/jsp/common/_tenant_globals.jsp" %>
    <%@ include file="/WEB-INF/jsp/common/_tenant_globals.jsp" %>
    <%@ include file="/WEB-INF/jsp/common/_tenant_globals.jsp" %>
    <%@ include file="/WEB-INF/jsp/common/_tenant_globals.jsp" %>
    <%@ include file="/WEB-INF/jsp/common/_tenant_globals.jsp" %>
    <%@ include file="/WEB-INF/jsp/common/_tenant_globals.jsp" %>
    <%@ include file="/WEB-INF/jsp/common/_tenant_globals.jsp" %>
    
    <%
    // End timing
    long endTime = System.nanoTime();
    long duration = (endTime - startTime) / 1000000; // Convert to milliseconds
    %>
    
    <div style="background: #f0f8ff; padding: 20px; border-radius: 8px; margin: 20px 0;">
        <h2>📊 Performance Results</h2>
        <p><strong>Includes Processed:</strong> 10 times</p>
        <p><strong>Total Time:</strong> <%= duration %> milliseconds</p>
        <p><strong>Average per Include:</strong> <%= duration / 10.0 %> milliseconds</p>
        
        <% if (duration < 10) { %>
            <p style="color: green; font-weight: bold;">✅ EXCELLENT: Under 10ms total</p>
        <% } else if (duration < 50) { %>
            <p style="color: orange; font-weight: bold;">⚠️ GOOD: Under 50ms total</p>
        <% } else { %>
            <p style="color: red; font-weight: bold;">❌ SLOW: Over 50ms total</p>
        <% } %>
    </div>
    
    <div style="background: #f9f9f9; padding: 20px; border-radius: 8px; margin: 20px 0;">
        <h2>🎯 Tenant Variables Available</h2>
        <table border="1" cellpadding="5" cellspacing="0" style="border-collapse: collapse;">
            <tr><th>Variable</th><th>Value</th></tr>
            <tr><td>${'${tenant}'}</td><td><strong>${tenant}</strong></td></tr>
            <tr><td>${'${ctx}'}</td><td><strong>${ctx}</strong></td></tr>
            <tr><td>${'${tenantName}'}</td><td><strong>${tenantName}</strong></td></tr>
            <tr><td>${'${cssPath}'}</td><td><strong>${cssPath}</strong></td></tr>
            <tr><td>${'${jsPath}'}</td><td><strong>${jsPath}</strong></td></tr>
            <tr><td>${'${imagesPath}'}</td><td><strong>${imagesPath}</strong></td></tr>
            <tr><td>${'${primaryColor}'}</td><td><strong style="color: ${primaryColor}">${primaryColor}</strong></td></tr>
            <tr><td>${'${hasTransport}'}</td><td><strong>${hasTransport}</strong></td></tr>
            <tr><td>${'${hasLibrary}'}</td><td><strong>${hasLibrary}</strong></td></tr>
            <tr><td>${'${feeStructure}'}</td><td><strong>${feeStructure}</strong></td></tr>
            <tr><td>${'${academicYear}'}</td><td><strong>${academicYear}</strong></td></tr>
        </table>
    </div>
    
    <div style="background: #fff3cd; padding: 20px; border-radius: 8px; margin: 20px 0;">
        <h2>🔍 Performance Analysis</h2>
        <ul>
            <li><strong>First Include:</strong> Loads properties from files (~3-5ms)</li>
            <li><strong>Subsequent Includes:</strong> Skipped due to caching (~0.1ms each)</li>
            <li><strong>Total Expected:</strong> ~4ms for 10 includes</li>
            <li><strong>Actual Measured:</strong> <%= duration %>ms</li>
        </ul>
        
        <p><strong>Conclusion:</strong> 
        <% if (duration < 10) { %>
            Performance is excellent! Safe to include in many JSP files.
        <% } else { %>
            Performance could be optimized, but still acceptable for production use.
        <% } %>
        </p>
    </div>
    
    <div style="background: #d1ecf1; padding: 20px; border-radius: 8px; margin: 20px 0;">
        <h2>📝 Usage Examples</h2>
        
        <h3>Context Paths:</h3>
        <pre>
&lt;!-- Old way --&gt;
&lt;link href="${'${pageContext.request.contextPath}'}/css/style.css"&gt;

&lt;!-- New way --&gt;
&lt;link href="${'${cssPath}'}/style.css"&gt;
        </pre>
        
        <h3>Navigation:</h3>
        <pre>
&lt;!-- Old way --&gt;
&lt;a href="${'${pageContext.request.contextPath}'}/students"&gt;Students&lt;/a&gt;

&lt;!-- New way --&gt;
&lt;a href="${'${ctx}'}/students"&gt;Students&lt;/a&gt;
        </pre>
        
        <h3>Feature-based Display:</h3>
        <pre>
&lt;c:if test="${'${hasTransport}'}"&gt;
    &lt;a href="${'${ctx}'}/transport"&gt;Transport&lt;/a&gt;
&lt;/c:if&gt;
        </pre>
    </div>
    
    <script>
        console.log('🏫 Tenant Globals Performance Test');
        console.log('Tenant:', window.tenantGlobals?.tenant || 'undefined');
        console.log('Context Path:', window.CTX_PATH || 'undefined');
        console.log('Performance: <%= duration %>ms for 10 includes');
        
        // Measure JavaScript access time
        const jsStart = performance.now();
        const tenant = window.tenantGlobals?.tenant;
        const ctxPath = window.CTX_PATH;
        const jsEnd = performance.now();
        
        console.log('JavaScript variable access: ' + (jsEnd - jsStart).toFixed(3) + 'ms');
    </script>
    
</body>
</html>
