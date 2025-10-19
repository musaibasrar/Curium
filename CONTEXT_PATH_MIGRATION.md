# 🔄 Context Path Migration Strategy

## 🎯 Problem
Currently, JSP files use `${pageContext.request.contextPath}` 7,376 times across 373 files. This is:
- ❌ **Verbose** - Long to type
- ❌ **Inconsistent** - Some files might forget to use it
- ❌ **Not integrated** - Separate from tenant properties

## ✅ Solution
Our `_tenant_globals.jsp` now provides **simpler context path variables**:

### **Available Variables:**
```jsp
${ctx}           <!-- Short version: /fathima -->
${contextPath}   <!-- Full version: /fathima -->
${baseUrl}       <!-- Alias: /fathima -->
${basePath}      <!-- Same as contextPath: /fathima -->

<!-- Pre-built resource paths -->
${cssPath}       <!-- /fathima/css -->
${jsPath}        <!-- /fathima/js -->
${imagesPath}    <!-- /fathima/images -->
${assetsPath}    <!-- /fathima/assets -->
```

## 🚀 Migration Options

### **Option 1: Gradual Migration (Recommended)**
Replace `${pageContext.request.contextPath}` **gradually** as you work on files:

```jsp
<!-- Add tenant globals include -->
<%@ include file="/WEB-INF/jsp/common/_tenant_globals.jsp" %>

<!-- Then replace gradually -->
<!-- OLD -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<a href="${pageContext.request.contextPath}/students">Students</a>

<!-- NEW -->
<link rel="stylesheet" href="${cssPath}/bootstrap.css">
<script src="${jsPath}/jquery.js"></script>
<a href="${ctx}/students">Students</a>
```

### **Option 2: Hybrid Approach (Safe)**
Keep both approaches working together:

```jsp
<%@ include file="/WEB-INF/jsp/common/_tenant_globals.jsp" %>

<!-- Old code continues to work -->
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">

<!-- New code uses shorter variables -->
<link href="${cssPath}/custom.css" rel="stylesheet">
<a href="${ctx}/dashboard">Dashboard</a>
```

### **Option 3: Mass Replace (Advanced)**
Replace all at once using find-and-replace:

```bash
# Replace common patterns
find . -name "*.jsp" -exec sed -i 's/${pageContext.request.contextPath}/css/${cssPath}/g' {} \;
find . -name "*.jsp" -exec sed -i 's/${pageContext.request.contextPath}/js/${jsPath}/g' {} \;
find . -name "*.jsp" -exec sed -i 's/${pageContext.request.contextPath}/images/${imagesPath}/g' {} \;
find . -name "*.jsp" -exec sed -i 's/${pageContext.request.contextPath}/${ctx}/g' {} \;
```

## 📋 Common Replacement Patterns

### **Resource Links:**
```jsp
<!-- CSS -->
<!-- OLD --> href="${pageContext.request.contextPath}/css/style.css"
<!-- NEW --> href="${cssPath}/style.css"

<!-- JavaScript -->
<!-- OLD --> src="${pageContext.request.contextPath}/js/script.js"
<!-- NEW --> src="${jsPath}/script.js"

<!-- Images -->
<!-- OLD --> src="${pageContext.request.contextPath}/images/logo.png"
<!-- NEW --> src="${imagesPath}/logo.png"
```

### **Navigation & Forms:**
```jsp
<!-- Links -->
<!-- OLD --> href="${pageContext.request.contextPath}/students"
<!-- NEW --> href="${ctx}/students"

<!-- Form Actions -->
<!-- OLD --> action="${pageContext.request.contextPath}/StudentProcess/save"
<!-- NEW --> action="${ctx}/StudentProcess/save"

<!-- AJAX URLs -->
<!-- OLD --> url: '${pageContext.request.contextPath}/api/students'
<!-- NEW --> url: '${ctx}/api/students'
```

### **Special Cases:**
```jsp
<!-- If you need the raw context path -->
<!-- OLD --> var contextPath = '${pageContext.request.contextPath}';
<!-- NEW --> var contextPath = '${ctx}';

<!-- Complex concatenation -->
<!-- OLD --> '${pageContext.request.contextPath}' + '/some/path'
<!-- NEW --> '${ctx}' + '/some/path'  OR  '${ctx}/some/path'
```

## 💡 Benefits of Migration

### **Before:**
```jsp
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<form action="${pageContext.request.contextPath}/StudentProcess/save">
    <input type="hidden" value="${pageContext.request.contextPath}/success">
</form>
```

### **After:**
```jsp
<%@ include file="/WEB-INF/jsp/common/_tenant_globals.jsp" %>

<link rel="stylesheet" href="${cssPath}/bootstrap.css">
<script src="${jsPath}/jquery.js"></script>
<form action="${ctx}/StudentProcess/save">
    <input type="hidden" value="${ctx}/success">
</form>
```

### **Advantages:**
- ✅ **Shorter** - `${ctx}` vs `${pageContext.request.contextPath}`
- ✅ **Cleaner** - `${cssPath}` vs `${pageContext.request.contextPath}/css`
- ✅ **Consistent** - All files use the same approach
- ✅ **Integrated** - Context path + tenant properties in one include
- ✅ **JavaScript Ready** - `window.CTX_PATH` automatically available

## 🔄 Migration Steps

### **Step 1: Update Key Files First**
Start with frequently used files:
- `header_admin.jsp`
- `login.jsp`
- `index.jsp`
- Navigation files (`left_*.jsp`)

### **Step 2: Update by Category**
- Forms (`add*.jsp`, `*_update.jsp`)
- Reports (`print*.jsp`, `*report.jsp`) 
- Views (`*_details.jsp`, `view*.jsp`)

### **Step 3: Batch Replace Common Patterns**
Use find-and-replace for repetitive patterns:
- CSS includes
- JavaScript includes
- Common form actions

### **Step 4: Test Thoroughly**
- Test with different tenants (`/fathima`, `/school`, etc.)
- Check all resource loading
- Verify all links work correctly

## 🚦 Compatibility

### **Backward Compatibility:**
- ✅ **Old JSPs continue to work** - No breaking changes
- ✅ **Gradual migration possible** - Mix old and new approaches
- ✅ **No backend changes** - Pure frontend solution

### **JavaScript Compatibility:**
```javascript
// Old approach still works
var oldPath = '${pageContext.request.contextPath}';

// New approach available
var newPath = window.CTX_PATH;  // Automatically set by tenant globals
```

## 🎯 Recommendation

**Start with Option 1 (Gradual Migration)**:

1. Add `<%@ include file="/WEB-INF/jsp/common/_tenant_globals.jsp" %>` to new JSPs
2. Use `${ctx}` and `${cssPath}` in new code
3. Replace `${pageContext.request.contextPath}` when editing existing files
4. Eventually, all files will use the cleaner approach

This gives you:
- **Immediate benefits** in new code
- **No disruption** to existing code  
- **Gradual improvement** over time
- **Full tenant integration** when ready
