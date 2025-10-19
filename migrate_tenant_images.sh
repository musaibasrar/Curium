#!/bin/bash

# INTELLIGENT TENANT IMAGE MIGRATION 🖼️
# =====================================
# Copies tenant-specific images from individual branches to our unified system

echo "🖼️ INTELLIGENT TENANT IMAGE MIGRATION"
echo "====================================="

# List of tenant branches to check
TENANT_BRANCHES=(
    "fathimav3:fathima"
    "alfarooq:alfarooq" 
    "brainystars:brainystars"
    "brainystarsratlam:brainystarsratlam"
    "shkam:shkam"
    "gnyanganga:gnyanganga"
    "awami:awami"
    "alalmas:alalmas"
    "daralmajd:daralmajd"
    "brightv3:brightv3"
    "cambridge:cambridge"
    "children:children"
    "divinev3:divinev3"
    "dolphinv3:dolphinv3"
    "greatindiaacademy:greatindiaacademy"
    "hira:hira"
    "iqra:iqra"
    "jdh:jdh"
    "jrs:jrs"
    "lilyrose:lilyrose"
    "littleangels:littleangels"
    "littleflower:littleflower"
    "mepsv3:mepsv3"
    "oasis:oasis"
    "patriswamy:patriswamy"
    "peace:peace"
    "scholar:scholar"
    "scholargroup:scholargroup"
    "shadaanV3:shadaanV3"
    "shaheenkidzplayschool:shaheenkidzplayschool"
)

WORKSPACE_ROOT="/Users/shoeb/Documents/Curium_Analysis"
MIGRATED_COUNT=0
TENANT_COUNT=0

echo "🔍 Scanning ${#TENANT_BRANCHES[@]} tenant branches for images..."
echo ""

for branch_mapping in "${TENANT_BRANCHES[@]}"; do
    # Split branch:tenant mapping
    branch_name="${branch_mapping%:*}"
    tenant_name="${branch_mapping#*:}"
    
    branch_path="$WORKSPACE_ROOT/Curium_branch_$branch_name"
    images_source="$branch_path/src/main/resources/static/images"
    images_dest="src/main/resources/static/tenants/$tenant_name/images"
    
    TENANT_COUNT=$((TENANT_COUNT + 1))
    
    if [ -d "$branch_path" ]; then
        echo "🏫 Processing: $tenant_name (from branch: $branch_name)"
        
        if [ -d "$images_source" ]; then
            # Create destination directory
            mkdir -p "$images_dest"
            
            # Count images in source
            image_count=$(find "$images_source" -name "*.png" -o -name "*.jpg" -o -name "*.jpeg" -o -name "*.gif" -o -name "*.svg" | wc -l)
            
            if [ "$image_count" -gt 0 ]; then
                echo "   📁 Found $image_count images in source"
                
                # Copy all image files
                find "$images_source" -name "*.png" -o -name "*.jpg" -o -name "*.jpeg" -o -name "*.gif" -o -name "*.svg" | while read img_file; do
                    img_name=$(basename "$img_file")
                    
                    # Skip if it's a generic UI icon
                    if [[ "$img_name" =~ ^ui- ]]; then
                        continue
                    fi
                    
                    # Copy the image
                    cp "$img_file" "$images_dest/"
                    echo "   ✅ Copied: $img_name"
                done
                
                # Check for tenant-specific logo
                tenant_logo=""
                if [ -f "$images_source/${tenant_name}.png" ]; then
                    tenant_logo="${tenant_name}.png"
                elif [ -f "$images_source/${tenant_name}.jpg" ]; then
                    tenant_logo="${tenant_name}.jpg"
                elif [ -f "$images_source/logo.png" ]; then
                    # Copy logo.png as tenant-specific logo
                    cp "$images_source/logo.png" "$images_dest/${tenant_name}.png"
                    tenant_logo="${tenant_name}.png"
                    echo "   🎯 Created tenant logo: ${tenant_name}.png"
                fi
                
                # Update the tenant properties file with correct logo
                if [ -n "$tenant_logo" ] && [ -f "src/main/webapp/WEB-INF/tenants/${tenant_name}.properties" ]; then
                    sed -i '' "s/tenant.branding.logo=logo.png/tenant.branding.logo=$tenant_logo/" "src/main/webapp/WEB-INF/tenants/${tenant_name}.properties"
                    echo "   🔧 Updated properties: logo=$tenant_logo"
                fi
                
                MIGRATED_COUNT=$((MIGRATED_COUNT + 1))
                echo "   ✅ Migration complete for $tenant_name"
            else
                echo "   ⚠️  No images found"
            fi
        else
            echo "   ❌ No images directory: $images_source"
        fi
        echo ""
    else
        echo "⚠️  Branch not found: $branch_path"
        echo ""
    fi
done

echo "🎉 MIGRATION SUMMARY"
echo "==================="
echo "📊 Tenants processed: $TENANT_COUNT"
echo "✅ Successful migrations: $MIGRATED_COUNT"
echo "📁 Images location: src/main/resources/static/tenants/{tenant}/images/"
echo ""
echo "🔍 Verification - Sample tenant images:"
for tenant in "fathima" "alfarooq" "brainystars"; do
    if [ -d "src/main/resources/static/tenants/$tenant/images" ]; then
        echo "   $tenant: $(ls src/main/resources/static/tenants/$tenant/images | wc -l) images"
    fi
done

echo ""
echo "✨ Tenant-specific images are now available for smart resolution!"



