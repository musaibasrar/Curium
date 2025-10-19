#!/bin/bash

# INTELLIGENT IMAGE DEDUPLICATION 🎯
# ===================================
# Removes duplicate images from tenant folders that already exist in common /static/images

echo "🎯 INTELLIGENT IMAGE DEDUPLICATION"
echo "=================================="

COMMON_IMAGES_DIR="src/main/resources/static/images"
TENANTS_DIR="src/main/resources/static/tenants"
DUPLICATES_REMOVED=0
SPACE_SAVED=0
TENANTS_PROCESSED=0

# Function to get file size in bytes
get_file_size() {
    if [[ "$OSTYPE" == "darwin"* ]]; then
        stat -f%z "$1" 2>/dev/null || echo 0
    else
        stat -c%s "$1" 2>/dev/null || echo 0
    fi
}

# Function to check if two files are identical
files_identical() {
    local file1="$1"
    local file2="$2"
    
    if [ ! -f "$file1" ] || [ ! -f "$file2" ]; then
        return 1
    fi
    
    # Compare file sizes first (faster)
    local size1=$(get_file_size "$file1")
    local size2=$(get_file_size "$file2")
    
    if [ "$size1" != "$size2" ]; then
        return 1
    fi
    
    # If sizes match, compare checksums
    local hash1=$(md5 -q "$file1" 2>/dev/null || md5sum "$file1" | cut -d' ' -f1)
    local hash2=$(md5 -q "$file2" 2>/dev/null || md5sum "$file2" | cut -d' ' -f1)
    
    [ "$hash1" = "$hash2" ]
}

echo "🔍 Scanning for duplicate images..."
echo ""

# Get list of common images to check against
if [ ! -d "$COMMON_IMAGES_DIR" ]; then
    echo "❌ Common images directory not found: $COMMON_IMAGES_DIR"
    exit 1
fi

# Process each tenant folder
for tenant_dir in "$TENANTS_DIR"/*; do
    if [ -d "$tenant_dir" ]; then
        tenant_name=$(basename "$tenant_dir")
        tenant_images_dir="$tenant_dir/images"
        
        if [ -d "$tenant_images_dir" ]; then
            TENANTS_PROCESSED=$((TENANTS_PROCESSED + 1))
            echo "🏫 Processing: $tenant_name"
            
            tenant_duplicates=0
            tenant_unique=0
            
            # Check each image in tenant folder
            for tenant_image in "$tenant_images_dir"/*; do
                if [ -f "$tenant_image" ]; then
                    image_name=$(basename "$tenant_image")
                    common_image="$COMMON_IMAGES_DIR/$image_name"
                    
                    # Skip tenant-specific logo files
                    if [[ "$image_name" == "$tenant_name".* ]]; then
                        echo "   🎯 KEEP (tenant logo): $image_name"
                        tenant_unique=$((tenant_unique + 1))
                        continue
                    fi
                    
                    # Skip favicon files
                    if [[ "$image_name" == "favicon"* ]]; then
                        echo "   🎯 KEEP (favicon): $image_name"
                        tenant_unique=$((tenant_unique + 1))
                        continue
                    fi
                    
                    # Skip signature files that might be tenant-specific
                    if [[ "$image_name" == *"signature"* ]] || [[ "$image_name" == *"sign"* ]]; then
                        echo "   🎯 KEEP (signature): $image_name"
                        tenant_unique=$((tenant_unique + 1))
                        continue
                    fi
                    
                    # Check if identical file exists in common images
                    if files_identical "$tenant_image" "$common_image"; then
                        file_size=$(get_file_size "$tenant_image")
                        echo "   ❌ REMOVE (duplicate): $image_name ($(($file_size / 1024))KB)"
                        rm "$tenant_image"
                        DUPLICATES_REMOVED=$((DUPLICATES_REMOVED + 1))
                        SPACE_SAVED=$((SPACE_SAVED + file_size))
                        tenant_duplicates=$((tenant_duplicates + 1))
                    else
                        echo "   ✅ KEEP (unique): $image_name"
                        tenant_unique=$((tenant_unique + 1))
                    fi
                fi
            done
            
            echo "   📊 Removed: $tenant_duplicates, Kept: $tenant_unique"
            echo ""
        else
            echo "⚠️  No images folder: $tenant_name"
            echo ""
        fi
    fi
done

# Convert bytes to MB for display
SPACE_SAVED_MB=$((SPACE_SAVED / 1024 / 1024))

echo "🎉 DEDUPLICATION SUMMARY"
echo "======================="
echo "📊 Tenants processed: $TENANTS_PROCESSED"
echo "🗑️  Duplicates removed: $DUPLICATES_REMOVED files"
echo "💾 Space saved: ${SPACE_SAVED_MB}MB"
echo ""
echo "✨ BENEFITS:"
echo "   ✅ Reduced storage overhead"
echo "   ✅ Faster deployments"
echo "   ✅ Easier maintenance"
echo "   ✅ Smart image resolution still works!"
echo ""
echo "🔍 Verification - Remaining tenant-specific images:"
for tenant in "fathima" "alfarooq" "scholargroup"; do
    if [ -d "src/main/resources/static/tenants/$tenant/images" ]; then
        count=$(ls "src/main/resources/static/tenants/$tenant/images" 2>/dev/null | wc -l)
        echo "   $tenant: $count unique images"
    fi
done

echo ""
echo "🎯 Your smart image resolution automatically falls back to common images!"
echo "   Primary: /static/tenants/{tenant}/images/{image}"
echo "   Fallback: /static/images/{image} ← Now used for all common images"



