#!/bin/bash

# Process Remaining JSP Files - Tenant Globals
# Use this script to update any remaining JSP files

echo "🔄 Processing remaining JSP files..."

count=0
processed=0

# Find files that don't have tenant globals yet
while IFS= read -r file; do
  if ! grep -q "_tenant_globals.jsp" "$file"; then
    echo "📝 Processing: $(basename $file)"
    
    # Add tenant globals include
    sed -i.bak '/<%@page/a\
\
<%-- Include tenant globals for property-driven behavior --%>\
<%@ include file="/WEB-INF/jsp/common/_tenant_globals.jsp" %>\
' "$file"
    
    # Replace context paths
    sed -i.bak2 \
      -e 's/${pageContext\.request\.contextPath}\/css/${cssPath}/g' \
      -e 's/${pageContext\.request\.contextPath}\/js/${jsPath}/g' \
      -e 's/${pageContext\.request\.contextPath}\/images/${imagesPath}/g' \
      -e 's/${pageContext\.request\.contextPath}/${ctx}/g' \
      "$file"
    
    # Clean up
    rm -f "$file".bak*
    
    ((processed++))
    echo "  ✅ Updated"
    
    # Limit to 20 files per batch to avoid overwhelming
    if [ $processed -ge 20 ]; then
      echo ""
      echo "🛑 Processed 20 files. Run script again to continue with next batch."
      break
    fi
  fi
  ((count++))
done < <(find src/main/webapp/WEB-INF/jsp -name "*.jsp")

echo ""
echo "✅ Batch completed!"
echo "📊 Processed: $processed files"
echo "📊 Checked: $count files"
echo ""
if [ $processed -eq 20 ]; then
  echo "🔄 Run this script again to process more files"
else
  echo "🎉 All files processed!"
fi
