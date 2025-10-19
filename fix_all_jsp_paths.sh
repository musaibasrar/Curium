#!/bin/bash

echo "🔧 Fixing all hardcoded paths in JSP files..."

# Find all JSP files
find src/main/webapp/WEB-INF/jsp -name "*.jsp" -type f | while read -r jsp_file; do
    echo "Processing: $jsp_file"
    
    # Create backup
    cp "$jsp_file" "$jsp_file.backup"
    
    # Fix /school/ paths to use ${pageContext.request.contextPath}
    sed -i '' 's|/school/|${pageContext.request.contextPath}/|g' "$jsp_file"
    
    # Fix ${pageContext.request.contextPath}/ paths to use ${pageContext.request.contextPath}
    sed -i '' 's|${pageContext.request.contextPath}/|${pageContext.request.contextPath}/|g' "$jsp_file"
    
    # Fix other hardcoded tenant paths
    sed -i '' 's|/abc/|${pageContext.request.contextPath}/|g' "$jsp_file"
    sed -i '' 's|/hira/|${pageContext.request.contextPath}/|g' "$jsp_file"
    sed -i '' 's|/oasis/|${pageContext.request.contextPath}/|g' "$jsp_file"
    sed -i '' 's|/peace/|${pageContext.request.contextPath}/|g' "$jsp_file"
    sed -i '' 's|/shkam/|${pageContext.request.contextPath}/|g' "$jsp_file"
    sed -i '' 's|/brightschool/|${pageContext.request.contextPath}/|g' "$jsp_file"
    
    echo "✅ Fixed paths in: $jsp_file"
done

echo "🎯 All JSP files have been updated!"
echo "📁 Backups created with .backup extension"
echo "🔄 Rebuild and deploy your application to test the changes"

