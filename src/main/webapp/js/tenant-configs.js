/**
 * Legacy Tenant Configurations (Optional)
 * 
 * This file provides backward compatibility for existing JavaScript-based configurations.
 * Most tenant customization is now handled by tenant properties files and _tenant_globals.jsp
 * 
 * You can remove this file if you don't need the legacy JavaScript configurations.
 */

window.tenantConfigs = {
    // Legacy configurations for backward compatibility
    'fathima': {
        name: 'Fathima School',
        primaryColor: '#2E5C8A',
        secondaryColor: '#B8860B',
        accentColor: '#FFD700',
        logo: 'fathima.png'
    },
    
    'school': {
        name: 'School Management System',
        primaryColor: '#2E5C8A',
        secondaryColor: '#B8860B',
        accentColor: '#FFD700',
        logo: 'logo.png'
    }
};

// Make available globally for backward compatibility
window.tenantFeatures = {
    'fathima': {
        transport: false,
        hostel: false,
        library: true,
        canteen: true
    },
    
    'school': {
        transport: true,
        hostel: true,
        library: true,
        canteen: true
    }
};

console.log('📋 Legacy tenant configs loaded (consider migrating to tenant properties files)');