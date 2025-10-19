# 🏢 TENANT ORGANIZATION STRUCTURE

## 📂 Directory Structure

Our multi-tenant system uses a **security-conscious split structure** for tenant-related files:

```
src/main/webapp/
├── tenant-assets/              ← 🌐 PUBLIC ASSETS (web-accessible)
│   ├── shkam/
│   │   ├── images/            ← Logos, backgrounds, icons
│   │   ├── css/               ← Tenant-specific stylesheets  
│   │   └── js/                ← Tenant-specific JavaScript
│   ├── fathima/
│   │   ├── images/
│   │   ├── css/
│   │   └── js/
│   └── [35+ other tenants]/
│
└── WEB-INF/
    └── tenant-config/          ← 🔒 SECURE CONFIGURATION (protected)
        ├── default.properties  ← Default tenant settings
        ├── shkam.properties    ← Shkam-specific configuration
        ├── fathima.properties  ← Fathima-specific configuration
        └── [35+ other tenants].properties
```

## 🎯 Why This Split Structure?

### 🌐 **PUBLIC ASSETS** (`webapp/tenant-assets/`)
- **Purpose**: Images, CSS, JS that need to be served to browsers
- **Access**: Publicly accessible via URLs
- **Examples**: 
  - `http://localhost:8081/shkam/tenant-assets/shkam/images/logo.png`
  - `http://localhost:8081/shkam/tenant-assets/fathima/css/style.css`

### 🔒 **SECURE CONFIGURATION** (`WEB-INF/tenant-config/`)
- **Purpose**: Sensitive configuration, business rules, feature flags
- **Access**: Protected by servlet container, NOT accessible via URLs
- **Security**: Cannot be downloaded by users
- **Examples**: Database settings, API keys, business logic

## 🚀 How It Works

### Smart Image Resolution
```jsp
<!-- In JSP templates -->
<img src="${logoUrl}" onerror="this.src='${logoFallback}'" alt="Logo" />

/* Resolves to: */
Primary:  /shkam/tenant-assets/shkam/images/shkam.png
Fallback: /shkam/images/logo.png
```

### Property-Driven Configuration
```java
// _tenant_globals.jsp loads from:
/WEB-INF/tenant-config/shkam.properties

// Makes available as JSP variables:
${tenantName}, ${primaryColor}, ${hasTransport}, etc.
```

## ✅ Benefits

1. **🔒 Security**: Sensitive config protected in WEB-INF
2. **🌐 Performance**: Static assets served directly by web server
3. **📁 Clear Organization**: Purpose-driven directory names
4. **🎯 Maintainability**: Easy to understand what goes where
5. **⚡ Scalability**: Add new tenants by adding folders + properties

## 📝 Adding New Tenants

1. **Create asset directory**: `webapp/tenant-assets/newtenant/`
2. **Add images**: `webapp/tenant-assets/newtenant/images/logo.png`
3. **Create config**: `WEB-INF/tenant-config/newtenant.properties`
4. **Deploy**: New tenant automatically works!

---
*This structure balances security, performance, and maintainability for enterprise multi-tenancy.*



