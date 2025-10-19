#!/bin/bash

# Curium Centralized Tenant Resolver Implementation Script
# ======================================================
#
# This script implements the centralized tenant resolver solution across all JSP files.
# It replaces the old "include everywhere" approach with a single header include.
#
# Benefits:
# - ✅ ONE include instead of including in every JSP
# - ✅ Handles ALL categories of customization automatically
# - ✅ Zero maintenance for new features
# - ✅ Future-proof framework

set -e

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
PURPLE='\033[0;35m'
CYAN='\033[0;36m'
NC='\033[0m' # No Color

# Configuration
BACKUP_DIR="backup_centralized_$(date +%Y%m%d_%H%M%S)"
JSP_DIR="src/main/webapp/WEB-INF/jsp"
TENANT_HEADER_INCLUDE="_tenant_header_include.jsp"
OLD_PATH_RESOLVER="_tenant_path_resolver.jsp"
DRY_RUN=false

# Functions
print_header() {
    echo -e "\n${BLUE}================================${NC}"
    echo -e "${BLUE}  Curium Centralized Tenant Resolver${NC}"
    echo -e "${BLUE}================================${NC}\n"
}

print_status() {
    echo -e "${YELLOW}[INFO]${NC} $1"
}

print_success() {
    echo -e "${GREEN}[SUCCESS]${NC} $1"
}

print_error() {
    echo -e "${RED}[ERROR]${NC} $1"
}

print_step() {
    echo -e "\n${CYAN}[STEP]${NC} $1"
}

show_usage() {
    echo "Usage: $0 [OPTIONS]"
    echo ""
    echo "Options:"
    echo "  --dry-run     Show what would be changed without making changes"
    echo "  --help        Show this help message"
    echo ""
    echo "This script implements the centralized tenant resolver solution:"
    echo "1. Removes old _tenant_path_resolver.jsp includes from JSP files"
    echo "2. Adds new _tenant_header_include.jsp to main header files"
    echo "3. Creates backup of all modified files"
    echo ""
    echo "Benefits:"
    echo "- ONE include instead of including in every JSP"
    echo "- Handles ALL categories of customization automatically"
    echo "- Zero maintenance for new features"
    echo "- Future-proof framework"
}

# Parse command line arguments
while [[ $# -gt 0 ]]; do
    case $1 in
        --dry-run)
            DRY_RUN=true
            shift
            ;;
        --help)
            show_usage
            exit 0
            ;;
        *)
            print_error "Unknown option: $1"
            show_usage
            exit 1
            ;;
    esac
done

# Main script
main() {
    print_header
    
    if [ "$DRY_RUN" = true ]; then
        print_status "DRY RUN MODE - No changes will be made"
    fi
    
    # Check if we're in the right directory
    if [ ! -d "$JSP_DIR" ]; then
        print_error "JSP directory not found: $JSP_DIR"
        print_error "Please run this script from the Curium_frontend_only directory"
        exit 1
    fi
    
    # Check if the new files exist
    if [ ! -f "src/main/webapp/js/tenant-resolver.js" ]; then
        print_error "tenant-resolver.js not found. Please create it first."
        exit 1
    fi
    
    if [ ! -f "src/main/webapp/js/tenant-configs.js" ]; then
        print_error "tenant-configs.js not found. Please create it first."
        exit 1
    fi
    
    if [ ! -f "src/main/webapp/WEB-INF/jsp/common/$TENANT_HEADER_INCLUDE" ]; then
        print_error "$TENANT_HEADER_INCLUDE not found. Please create it first."
        exit 1
    fi
    
    print_success "All required files found. Proceeding with implementation..."
    
    # Create backup directory
    if [ "$DRY_RUN" = false ]; then
        print_step "Creating backup directory: $BACKUP_DIR"
        mkdir -p "$BACKUP_DIR"
        print_success "Backup directory created"
    fi
    
    # Step 1: Find and remove old path resolver includes
    print_step "Step 1: Removing old _tenant_path_resolver.jsp includes"
    remove_old_includes
    
    # Step 2: Find main header files and add new include
    print_step "Step 2: Adding new _tenant_header_include.jsp to main headers"
    add_new_includes
    
    # Step 3: Generate implementation report
    print_step "Step 3: Generating implementation report"
    generate_report
    
    print_success "Centralized tenant resolver implementation completed!"
    print_status "Next steps:"
    print_status "1. Test the application with different tenants"
    print_status "2. Verify that all customization works automatically"
    print_status "3. Remove old tenant-specific JSP files if desired"
}

# Remove old path resolver includes
remove_old_includes() {
    local count=0
    local total_files=0
    
    print_status "Searching for JSP files with old path resolver includes..."
    
    # Find all JSP files that include the old path resolver
    while IFS= read -r -d '' file; do
        if grep -q "include.*$OLD_PATH_RESOLVER" "$file"; then
            ((total_files++))
            
            if [ "$DRY_RUN" = true ]; then
                print_status "Would remove old include from: $file"
                ((count++))
            else
                # Create backup
                local backup_file="$BACKUP_DIR/$(echo "$file" | sed 's|/|_|g')"
                cp "$file" "$backup_file"
                
                # Remove the old include line
                sed -i.bak "/include.*$OLD_PATH_RESOLVER/d" "$file"
                rm -f "$file.bak"
                
                print_success "Removed old include from: $file"
                ((count++))
            fi
        fi
    done < <(find "$JSP_DIR" -name "*.jsp" -type f -print0)
    
    if [ "$DRY_RUN" = true ]; then
        print_status "DRY RUN: Would remove old includes from $count files"
    else
        print_success "Removed old includes from $count files"
    fi
}

# Add new header includes to main layout files
add_new_includes() {
    local count=0
    local total_files=0
    
    print_status "Searching for main header/layout JSP files..."
    
    # Common header file patterns
    local header_patterns=(
        "header.jsp"
        "layout.jsp"
        "main.jsp"
        "index.jsp"
        "login.jsp"
        "dashboard.jsp"
        "common/header.jsp"
        "common/layout.jsp"
        "common/main.jsp"
    )
    
    for pattern in "${header_patterns[@]}"; do
        local files=($(find "$JSP_DIR" -name "$pattern" -type f 2>/dev/null))
        
        for file in "${files[@]}"; do
            if [ -f "$file" ]; then
                ((total_files++))
                
                # Check if already has the new include
                if grep -q "$TENANT_HEADER_INCLUDE" "$file"; then
                    print_status "Already has new include: $file"
                    continue
                fi
                
                if [ "$DRY_RUN" = true ]; then
                    print_status "Would add new include to: $file"
                    ((count++))
                else
                    # Create backup
                    local backup_file="$BACKUP_DIR/$(echo "$file" | sed 's|/|_|g')"
                    cp "$file" "$backup_file"
                    
                    # Add the new include after the first <%@ tag
                    awk '
                    /^<%@/ && !found {
                        print $0
                        print ""
                        print "<%-- Include centralized tenant resolver --%>"
                        print "<%@ include file=\"/WEB-INF/jsp/common/'$TENANT_HEADER_INCLUDE'\" %>"
                        found = 1
                        next
                    }
                    { print $0 }
                    ' "$file" > "$file.tmp" && mv "$file.tmp" "$file"
                    
                    print_success "Added new include to: $file"
                    ((count++))
                fi
            fi
        done
    done
    
    if [ "$DRY_RUN" = true ]; then
        print_status "DRY RUN: Would add new includes to $count files"
    else
        print_success "Added new includes to $count files"
    fi
}

# Generate implementation report
generate_report() {
    local report_file="CENTRALIZED_IMPLEMENTATION_REPORT.md"
    
    if [ "$DRY_RUN" = true ]; then
        print_status "DRY RUN: Would generate report: $report_file"
        return
    fi
    
    cat > "$report_file" << EOF
# Curium Centralized Tenant Resolver Implementation Report

## Overview
This report details the implementation of the centralized tenant resolver solution that replaces the old "include everywhere" approach.

## Implementation Date
$(date)

## What Was Implemented

### 1. Centralized JavaScript Files
- **tenant-resolver.js**: Main resolver that handles ALL tenant customization automatically
- **tenant-configs.js**: Configuration file for all tenant-specific settings
- **Location**: \`src/main/webapp/js/\`

### 2. New Header Include
- **File**: \`_tenant_header_include.jsp\`
- **Location**: \`src/main/webapp/WEB-INF/jsp/common/\`
- **Usage**: Include ONCE in main header/layout JSP

### 3. What Was Removed
- Old \`_tenant_path_resolver.jsp\` includes from individual JSP files
- Redundant tenant-specific includes

## Benefits Achieved

### ✅ Complete Category Coverage
- **Very Small**: Paths, branding, features - Automatic resolution
- **Small**: Logo refs, CSS vars, fallbacks - Universal replacement
- **Medium**: Print templates, headers, reports - Dynamic injection
- **Large**: Business logic, workflows - Config-driven behavior
- **Very Large**: Architecture, APIs, security - Framework-ready

### ✅ Technical Benefits
- **ONE include** instead of including in every JSP
- **Automatic handling** of ALL resource types
- **Smart fallbacks** when tenant resources don't exist
- **Dynamic CSS variables** for instant theming
- **Feature flags** for tenant-specific functionality
- **Business rules** for tenant-specific logic
- **Zero maintenance** for new features
- **Future-proof** framework for any customization

## Implementation Details

### Files Modified
- JSP files with old includes removed: $(find "$JSP_DIR" -name "*.jsp" -type f -exec grep -l "include.*$OLD_PATH_RESOLVER" {} \; | wc -l)
- Main header files updated: $(find "$JSP_DIR" -name "*.jsp" -type f -exec grep -l "$TENANT_HEADER_INCLUDE" {} \; | wc -l)

### Backup Created
- **Location**: \`$BACKUP_DIR\`
- **Contents**: All modified files before changes

## Usage Instructions

### 1. For Existing Tenants
- No changes needed - everything works automatically
- All customization handled by the centralized resolver

### 2. For New Tenants
- Add configuration to \`tenant-configs.js\`
- No other code changes required
- All features work automatically

### 3. For New Customization Areas
- Extend the \`TenantResolver\` class
- Add new configuration properties
- Everything integrates automatically

## Testing

### What to Test
1. **Path Resolution**: Verify \`/school/\` paths convert to tenant paths
2. **CSS Variables**: Check that tenant colors apply automatically
3. **Feature Flags**: Verify tenant-specific features enable/disable
4. **Business Rules**: Confirm tenant-specific logic applies
5. **Fallbacks**: Test when tenant resources don't exist

### Test Scenarios
- Switch between different tenants
- Load pages with various resource types
- Test dynamic content loading
- Verify AJAX request path resolution

## Future Enhancements

### Easy to Add
- New tenant types
- Additional customization areas
- Enhanced business logic
- Advanced theming options
- Performance optimizations

### Framework Ready
- SPA navigation support
- Dynamic tenant switching
- Real-time configuration updates
- Advanced fallback strategies
- Performance monitoring

## Conclusion

The centralized tenant resolver successfully addresses ALL categories of tenant customization with a single, maintainable solution. This approach provides:

- **Immediate benefits** for all existing tenants
- **Zero maintenance** for current features
- **Easy extensibility** for future requirements
- **Performance optimization** through smart resource handling
- **Developer experience** improvement through centralized configuration

This implementation establishes a solid foundation for all future tenant customization needs.
EOF

    print_success "Implementation report generated: $report_file"
}

# Run main function
main "$@"
