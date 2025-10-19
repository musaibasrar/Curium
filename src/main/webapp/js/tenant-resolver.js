/**
 * SIMPLE PATH FIXER - Only fixes remaining hardcoded paths
 * 
 * Most tenant customization is now handled by _tenant_globals.jsp
 * This script only handles dynamic content that can't be fixed in JSP
 */

(function() {
    'use strict';
    
    // Get tenant info from context path
    const contextPath = window.location.pathname.split('/')[1] || 'school';
    const basePath = '/' + contextPath;
    
    console.log('🔧 Simple path fixer loaded for:', contextPath);
    
    /**
     * Fix any remaining hardcoded paths in dynamic content
     */
    function fixHardcodedPaths() {
        // Fix links with hardcoded paths
        document.querySelectorAll('a[href*="/school/"], a[href*="/fathima/"], a[href*="/dolphin/"]').forEach(link => {
            const href = link.getAttribute('href');
            if (href.startsWith('/school/')) {
                link.setAttribute('href', href.replace('/school/', basePath + '/'));
            } else if (href.startsWith('/fathima/')) {
                link.setAttribute('href', href.replace('/fathima/', basePath + '/'));
            } else if (href.startsWith('/dolphin/')) {
                link.setAttribute('href', href.replace('/dolphin/', basePath + '/'));
            }
        });
        
        // Fix form actions with hardcoded paths
        document.querySelectorAll('form[action*="/school/"], form[action*="/fathima/"], form[action*="/dolphin/"]').forEach(form => {
            const action = form.getAttribute('action');
            if (action.startsWith('/school/')) {
                form.setAttribute('action', action.replace('/school/', basePath + '/'));
            } else if (action.startsWith('/fathima/')) {
                form.setAttribute('action', action.replace('/fathima/', basePath + '/'));
            } else if (action.startsWith('/dolphin/')) {
                form.setAttribute('action', action.replace('/dolphin/', basePath + '/'));
            }
        });
    }
    
    // Initialize when DOM is ready
    if (document.readyState === 'loading') {
        document.addEventListener('DOMContentLoaded', fixHardcodedPaths);
    } else {
        fixHardcodedPaths();
    }
    
    // Watch for dynamic content
    const observer = new MutationObserver(function(mutations) {
        mutations.forEach(function(mutation) {
            if (mutation.type === 'childList' && mutation.addedNodes.length > 0) {
                setTimeout(fixHardcodedPaths, 100);
            }
        });
    });
    
    observer.observe(document.body, {
        childList: true,
        subtree: true
    });
    
})();