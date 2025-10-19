#!/bin/bash

# Batch JSP Update Script for Tenant Globals
# This script adds tenant globals include and replaces context paths

echo "🚀 Starting batch JSP update for tenant globals..."

# Counter for tracking updates
updated_count=0

# Function to update a single JSP file
update_jsp_file() {
    local file="$1"
    local temp_file="/tmp/jsp_update_temp.jsp"
    
    echo "📝 Processing: $file"
    
    # Check if file already has tenant globals include
    if grep -q "_tenant_globals.jsp" "$file"; then
        echo "   ✅ Already has tenant globals include"
        return 0
    fi
    
    # Create updated file
    {
        # Add JSP headers and includes
        head -n 20 "$file" | grep -E "^<%--|^<%@"
        
        # Add tenant globals include after existing includes
        echo ""
        echo "<%-- Include tenant globals for property-driven behavior --%>"
        echo "<%@ include file=\"/WEB-INF/jsp/common/_tenant_globals.jsp\" %>"
        echo ""
        
        # Add rest of file, skipping initial headers
        tail -n +21 "$file"
        
    } > "$temp_file"
    
    # Replace the original file
    mv "$temp_file" "$file"
    
    echo "   ✅ Added tenant globals include"
    ((updated_count++))
}

# Function to replace context paths in a file
replace_context_paths() {
    local file="$1"
    
    echo "🔄 Replacing context paths in: $file"
    
    # Replace common patterns
    sed -i.bak \
        -e 's/${pageContext\.request\.contextPath}\/css/${cssPath}/g' \
        -e 's/${pageContext\.request\.contextPath}\/js/${jsPath}/g' \
        -e 's/${pageContext\.request\.contextPath}\/images/${imagesPath}/g' \
        -e 's/${pageContext\.request\.contextPath}\/assets/${assetsPath}/g' \
        -e 's/${pageContext\.request\.contextPath}/${ctx}/g' \
        "$file"
    
    # Remove backup file
    rm -f "${file}.bak"
    
    echo "   ✅ Context paths replaced"
}

# Update core navigation files first
echo "📂 Updating core navigation files..."
core_files=(
    "Curium_branch_develop/src/main/webapp/WEB-INF/jsp/header_admin.jsp"
    "Curium_branch_develop/src/main/webapp/WEB-INF/jsp/left_admin.jsp"
    "Curium_branch_develop/src/main/webapp/WEB-INF/jsp/left_teacher.jsp"
    "Curium_branch_develop/src/main/webapp/WEB-INF/jsp/left_feescollector.jsp"
    "Curium_branch_develop/src/main/webapp/WEB-INF/jsp/header_teacher.jsp"
    "Curium_branch_develop/src/main/webapp/WEB-INF/jsp/header_parent.jsp"
)

for file in "${core_files[@]}"; do
    if [[ -f "$file" ]]; then
        update_jsp_file "$file"
        replace_context_paths "$file"
    fi
done

# Update index files
echo "📂 Updating index files..."
find Curium_branch_develop/src/main/webapp/WEB-INF/jsp -name "index*.jsp" | while read -r file; do
    update_jsp_file "$file"
    replace_context_paths "$file"
done

echo ""
echo "✅ Batch update completed!"
echo "📊 Updated $updated_count files"
echo ""
echo "🔧 Next steps:"
echo "1. Test login and navigation"
echo "2. Check a few updated files manually"
echo "3. Run the application to verify changes"
echo ""
echo "🚨 Note: Always backup before running this script!"
