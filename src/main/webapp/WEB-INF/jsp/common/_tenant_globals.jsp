<%-- 
TENANT GLOBALS - Single include for all JSP files
Include this file in every JSP to get tenant properties as global variables
Usage: <%@ include file="/WEB-INF/jsp/common/_tenant_globals.jsp" %>
--%>
<%@ page import="java.util.Properties" %>
<%@ page import="java.io.InputStream" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
// Only load once per request
if (request.getAttribute("tenantGlobalsLoaded") == null) {
    
    // Extract tenant from context path
    String contextPath = request.getContextPath();
    String currentTenant = contextPath.startsWith("/") ? contextPath.substring(1) : contextPath;
    if (currentTenant.isEmpty()) currentTenant = "school";
    
    // Load tenant properties with fallback to default
    Properties tenantProps = new Properties();
    Properties defaultProps = new Properties();
    
    // Load default properties first
    try (InputStream defaultStream = application.getResourceAsStream("/WEB-INF/tenant-config/default.properties")) {
        if (defaultStream != null) {
            defaultProps.load(defaultStream);
        }
    } catch (Exception e) {
        // Silent fallback
    }
    
    // Load tenant-specific properties
    try (InputStream tenantStream = application.getResourceAsStream("/WEB-INF/tenant-config/" + currentTenant + ".properties")) {
        if (tenantStream != null) {
            tenantProps.load(tenantStream);
        }
    } catch (Exception e) {
        // Silent fallback
    }
    
    // Helper function to get property with fallback
    java.util.function.Function<String, String> getProperty = key -> {
        String value = tenantProps.getProperty(key);
        if (value == null || value.trim().isEmpty()) {
            value = defaultProps.getProperty(key, "");
        }
        return value != null ? value : "";
    };
    
    // Helper for boolean properties with default
    java.util.function.BiFunction<String, Boolean, Boolean> getBooleanProperty = (key, defaultValue) -> {
        String value = getProperty.apply(key);
        if (value.isEmpty()) return defaultValue;
        return Boolean.parseBoolean(value);
    };
    
    // === BASIC TENANT INFO (with defaults) ===
    request.setAttribute("tenant", currentTenant);
    request.setAttribute("basePath", contextPath);
    
    String tenantName = getProperty.apply("tenant.name");
    request.setAttribute("tenantName", tenantName.isEmpty() ? "School Management System" : tenantName);
    
    String tenantDesc = getProperty.apply("tenant.description");
    request.setAttribute("tenantDesc", tenantDesc.isEmpty() ? "School ERP System" : tenantDesc);
    
    // === CONTEXT PATH VARIABLES (Replacement for ${pageContext.request.contextPath}) ===
    request.setAttribute("ctx", contextPath);                    // Short version: ${ctx}
    request.setAttribute("contextPath", contextPath);            // Full version: ${contextPath}
    request.setAttribute("baseUrl", contextPath);                // Alias: ${baseUrl}
    
    // === RESOURCE PATH VARIABLES ===
    request.setAttribute("cssPath", contextPath + "/css");       // ${cssPath}
    request.setAttribute("jsPath", contextPath + "/js");         // ${jsPath}
    request.setAttribute("imagesPath", contextPath + "/images"); // ${imagesPath}
    request.setAttribute("assetsPath", contextPath + "/assets"); // ${assetsPath}
    
    // === SMART IMAGE RESOLUTION 🖼️ ===
    // Tenant-specific image path with fallback to common images  
    String tenantImagesPath = contextPath + "/tenant-assets/" + currentTenant + "/images";
    String commonImagesPath = contextPath + "/images";
    
    // Image file names from properties (with smart defaults)
    String logoFile = getProperty.apply("tenant.branding.logo");
    logoFile = logoFile.isEmpty() ? "logo.png" : logoFile;
    
    String faviconFile = getProperty.apply("tenant.branding.favicon");
    faviconFile = faviconFile.isEmpty() ? "favicon.ico" : faviconFile;
    
    String backgroundFile = getProperty.apply("tenant.branding.background");
    backgroundFile = backgroundFile.isEmpty() ? "backgroundfront.png" : backgroundFile;
    
    String signatureFile = getProperty.apply("tenant.branding.signature");
    signatureFile = signatureFile.isEmpty() ? "principalsignature.png" : signatureFile;
    
    String idSignFile = getProperty.apply("tenant.branding.id-signature");
    idSignFile = idSignFile.isEmpty() ? "idsign.jpeg" : idSignFile;
    
    // === SMART IMAGE URLS (Primary + Fallback) ===
    request.setAttribute("logoUrl", tenantImagesPath + "/" + logoFile);
    request.setAttribute("logoFallback", commonImagesPath + "/" + logoFile);
    
    request.setAttribute("faviconUrl", tenantImagesPath + "/" + faviconFile);
    request.setAttribute("faviconFallback", commonImagesPath + "/" + faviconFile);
    
    request.setAttribute("backgroundUrl", tenantImagesPath + "/" + backgroundFile);
    request.setAttribute("backgroundFallback", commonImagesPath + "/" + backgroundFile);
    
    request.setAttribute("signatureUrl", tenantImagesPath + "/" + signatureFile);
    request.setAttribute("signatureFallback", commonImagesPath + "/" + signatureFile);
    
    request.setAttribute("idSignUrl", tenantImagesPath + "/" + idSignFile);
    request.setAttribute("idSignFallback", commonImagesPath + "/" + idSignFile);
    
    // === LEGACY BRANDING VARIABLES (for backward compatibility) ===
    request.setAttribute("logo", logoFile);  // Just the filename
    request.setAttribute("favicon", faviconFile);  // Just the filename
    
    String primaryColor = getProperty.apply("tenant.branding.primary-color");
    request.setAttribute("primaryColor", primaryColor.isEmpty() ? "#2E5C8A" : primaryColor);
    
    String secondaryColor = getProperty.apply("tenant.branding.secondary-color");
    request.setAttribute("secondaryColor", secondaryColor.isEmpty() ? "#B8860B" : secondaryColor);
    
    String accentColor = getProperty.apply("tenant.branding.accent-color");
    request.setAttribute("accentColor", accentColor.isEmpty() ? "#FFD700" : accentColor);
    
    String fontFamily = getProperty.apply("tenant.branding.font-family");
    request.setAttribute("fontFamily", fontFamily.isEmpty() ? "Arial, sans-serif" : fontFamily);
    
    String theme = getProperty.apply("tenant.branding.theme");
    request.setAttribute("theme", theme.isEmpty() ? "default" : theme);
    
    // === FEATURES (as boolean with defaults) ===
    request.setAttribute("hasTransport", getBooleanProperty.apply("tenant.features.transport", true));
    request.setAttribute("hasHostel", getBooleanProperty.apply("tenant.features.hostel", true));
    request.setAttribute("hasLibrary", getBooleanProperty.apply("tenant.features.library", true));
    request.setAttribute("hasCanteen", getBooleanProperty.apply("tenant.features.canteen", true));
    request.setAttribute("hasExamination", getBooleanProperty.apply("tenant.features.examination", true));
    request.setAttribute("hasAccounting", getBooleanProperty.apply("tenant.features.accounting", true));
    request.setAttribute("hasSMS", getBooleanProperty.apply("tenant.features.sms", true));
    request.setAttribute("hasEmail", getBooleanProperty.apply("tenant.features.email", true));
    
    // === BUSINESS RULES (with defaults) ===
    String feeStructure = getProperty.apply("tenant.business.fee-structure");
    request.setAttribute("feeStructure", feeStructure.isEmpty() ? "standard" : feeStructure);
    
    String attendancePolicy = getProperty.apply("tenant.business.attendance-policy");
    request.setAttribute("attendancePolicy", attendancePolicy.isEmpty() ? "strict" : attendancePolicy);
    
    String examPattern = getProperty.apply("tenant.business.exam-pattern");
    request.setAttribute("examPattern", examPattern.isEmpty() ? "traditional" : examPattern);
    
    String gradingSystem = getProperty.apply("tenant.business.grading-system");
    request.setAttribute("gradingSystem", gradingSystem.isEmpty() ? "percentage" : gradingSystem);
    
    String academicYear = getProperty.apply("tenant.business.academic-year");
    request.setAttribute("academicYear", academicYear.isEmpty() ? "2024-2025" : academicYear);
    
    String currency = getProperty.apply("tenant.business.currency");
    request.setAttribute("currency", currency.isEmpty() ? "INR" : currency);
    
    String dateFormat = getProperty.apply("tenant.business.date-format");
    request.setAttribute("dateFormat", dateFormat.isEmpty() ? "dd/MM/yyyy" : dateFormat);
    
    // === VALIDATION PATTERNS (with defaults) ===
    String studentIdPattern = getProperty.apply("tenant.validation.student-id-pattern");
    request.setAttribute("studentIdPattern", studentIdPattern.isEmpty() ? "STU\\\\d{4}" : studentIdPattern.replace("\\", "\\\\"));
    
    String phonePattern = getProperty.apply("tenant.validation.phone-pattern");
    request.setAttribute("phonePattern", phonePattern.isEmpty() ? "^\\\\d{10}$" : phonePattern.replace("\\", "\\\\"));
    
    String emailDomain = getProperty.apply("tenant.validation.email-domain");
    request.setAttribute("emailDomain", emailDomain); // Can be empty
    
    String minAge = getProperty.apply("tenant.validation.admission-age-min");
    request.setAttribute("minAge", minAge.isEmpty() ? "5" : minAge);
    
    String maxAge = getProperty.apply("tenant.validation.admission-age-max");
    request.setAttribute("maxAge", maxAge.isEmpty() ? "18" : maxAge);
    
    // === CUSTOM FIELDS ===
    request.setAttribute("showPreviousSchool", Boolean.parseBoolean(getProperty.apply("tenant.custom-fields.student.previous-school")));
    request.setAttribute("parentOccupationReq", "required".equals(getProperty.apply("tenant.custom-fields.student.parent-occupation")));
    request.setAttribute("showTransportReq", Boolean.parseBoolean(getProperty.apply("tenant.custom-fields.student.transport-required")));
    request.setAttribute("emergencyContactReq", "required".equals(getProperty.apply("tenant.custom-fields.employee.emergency-contact")));
    request.setAttribute("showBloodGroup", Boolean.parseBoolean(getProperty.apply("tenant.custom-fields.employee.blood-group")));
    
    // === RESOURCE OVERRIDES ===
    request.setAttribute("customCSS", getProperty.apply("tenant.resources.css.style"));
    request.setAttribute("customLoginCSS", getProperty.apply("tenant.resources.css.login"));
    request.setAttribute("customJS", getProperty.apply("tenant.resources.js.custom"));
    
    // === NAVIGATION ===
    request.setAttribute("hideNavItems", getProperty.apply("tenant.navigation.hide-items"));
    request.setAttribute("addNavItems", getProperty.apply("tenant.navigation.add-items"));
    
    // === INTEGRATIONS (with defaults) ===
    String smsProvider = getProperty.apply("tenant.integrations.sms-provider");
    request.setAttribute("smsProvider", smsProvider.isEmpty() ? "default" : smsProvider);
    
    String emailProvider = getProperty.apply("tenant.integrations.email-provider");
    request.setAttribute("emailProvider", emailProvider.isEmpty() ? "default" : emailProvider);
    
    String paymentGateway = getProperty.apply("tenant.integrations.payment-gateway");
    request.setAttribute("paymentGateway", paymentGateway.isEmpty() ? "default" : paymentGateway);
    
    // === URLS ===
    request.setAttribute("paymentUrl", getProperty.apply("tenant.urls.payment"));
    request.setAttribute("resultsUrl", getProperty.apply("tenant.urls.results"));
    request.setAttribute("parentPortalUrl", getProperty.apply("tenant.urls.parent-portal"));
    request.setAttribute("helpdeskUrl", getProperty.apply("tenant.urls.helpdesk"));
    
    // Mark as loaded
    request.setAttribute("tenantGlobalsLoaded", true);
    
    // Store properties for direct access if needed
    request.setAttribute("allTenantProps", tenantProps);
    request.setAttribute("allDefaultProps", defaultProps);
}
%>

<%-- CSS Variables for immediate use --%>
<style>
:root {
    --tenant-primary: ${primaryColor};
    --tenant-secondary: ${secondaryColor};
    --tenant-accent: ${accentColor};
    --tenant-font: ${fontFamily};
}

/* Feature visibility */
<c:if test="${!hasTransport}">.feature-transport { display: none !important; }</c:if>
<c:if test="${!hasHostel}">.feature-hostel { display: none !important; }</c:if>
<c:if test="${!hasLibrary}">.feature-library { display: none !important; }</c:if>
<c:if test="${!hasCanteen}">.feature-canteen { display: none !important; }</c:if>
<c:if test="${!hasExamination}">.feature-examination { display: none !important; }</c:if>
<c:if test="${!hasAccounting}">.feature-accounting { display: none !important; }</c:if>
<c:if test="${!hasSMS}">.feature-sms { display: none !important; }</c:if>
<c:if test="${!hasEmail}">.feature-email { display: none !important; }</c:if>

/* Hide navigation items */
<c:if test="${not empty hideNavItems}">
    <c:forTokens items="${hideNavItems}" delims="," var="item">
        .nav-${fn:trim(item)} { display: none !important; }
    </c:forTokens>
</c:if>

/* Fee structure specific styles */
.fee-${feeStructure} { display: block; }
.fee:not(.fee-${feeStructure}) { display: none; }

/* Grading system specific styles */
.grade-${gradingSystem} { display: block; }
.grade:not(.grade-${gradingSystem}) { display: none; }
</style>

<%-- Load custom CSS if specified --%>
<c:if test="${not empty customCSS}">
    <link rel="stylesheet" href="${basePath}/static/tenants/${tenant}/css/${customCSS}"
          onerror="console.log('Custom CSS not found')">
</c:if>

<%-- Load custom JS if specified --%>
<c:if test="${not empty customJS}">
    <script src="${basePath}/static/tenants/${tenant}/js/${customJS}"
            onerror="console.log('Custom JS not found')"></script>
</c:if>

<%-- Favicon override --%>
<c:if test="${not empty favicon}">
    <link rel="icon" href="${basePath}/static/tenants/${tenant}/images/${favicon}">
</c:if>

<%-- Make tenant globals available to JavaScript --%>
<script>
// Context path variables for JavaScript (replaces window.CTX_PATH)
window.CTX_PATH = '${ctx}';
window.CONTEXT_PATH = '${contextPath}';
window.BASE_URL = '${baseUrl}';
window.CSS_PATH = '${cssPath}';
window.JS_PATH = '${jsPath}';
window.IMAGES_PATH = '${imagesPath}';
window.ASSETS_PATH = '${assetsPath}';

// Smart image URLs (tenant-specific with fallbacks)
window.LOGO_URL = '${logoUrl}';
window.LOGO_FALLBACK = '${logoFallback}';
window.BACKGROUND_URL = '${backgroundUrl}';
window.BACKGROUND_FALLBACK = '${backgroundFallback}';
window.SIGNATURE_URL = '${signatureUrl}';
window.SIGNATURE_FALLBACK = '${signatureFallback}';

// Tenant-specific globals (with safe defaults)
window.tenantGlobals = {
    tenant: '<c:out value="${tenant}" default="school"/>',
    basePath: '<c:out value="${basePath}" default="/school"/>',
    name: '<c:out value="${tenantName}" default="School Management System"/>',
    theme: '<c:out value="${theme}" default="default"/>',
    features: {
        transport: <c:out value="${hasTransport}" default="true"/>,
        hostel: <c:out value="${hasHostel}" default="true"/>,
        library: <c:out value="${hasLibrary}" default="true"/>,
        canteen: <c:out value="${hasCanteen}" default="true"/>,
        examination: <c:out value="${hasExamination}" default="true"/>,
        accounting: <c:out value="${hasAccounting}" default="true"/>,
        sms: <c:out value="${hasSMS}" default="true"/>,
        email: <c:out value="${hasEmail}" default="true"/>
    },
    business: {
        feeStructure: '<c:out value="${feeStructure}" default="standard"/>',
        attendancePolicy: '<c:out value="${attendancePolicy}" default="strict"/>',
        examPattern: '<c:out value="${examPattern}" default="traditional"/>',
        gradingSystem: '<c:out value="${gradingSystem}" default="percentage"/>',
        academicYear: '<c:out value="${academicYear}" default="2024-2025"/>',
        currency: '<c:out value="${currency}" default="INR"/>',
        dateFormat: '<c:out value="${dateFormat}" default="dd/MM/yyyy"/>'
    },
    validation: {
        studentIdPattern: '<c:out value="${studentIdPattern}" default="STU\\d{4}"/>',
        phonePattern: '<c:out value="${phonePattern}" default="^\\d{10}$"/>',
        emailDomain: '<c:out value="${emailDomain}" default=""/>',
        minAge: '<c:out value="${minAge}" default="5"/>',
        maxAge: '<c:out value="${maxAge}" default="18"/>'
    },
    integrations: {
        smsProvider: '<c:out value="${smsProvider}" default="default"/>',
        emailProvider: '<c:out value="${emailProvider}" default="default"/>',
        paymentGateway: '<c:out value="${paymentGateway}" default="default"/>'
    }
};

console.log('🌍 Tenant Globals Loaded:', window.tenantGlobals);
</script>

<%-- Smart Image Loader for tenant-specific images with fallbacks --%>
<script src="${jsPath}/smart-image-loader.js?v=1.0"></script>
