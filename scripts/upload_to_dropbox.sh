#!/bin/bash

# ---------------- CONFIG ----------------
CONFIG_FILE="dropbox_config.conf"

if [[ ! -f "$CONFIG_FILE" ]]; then
    echo "Error: Configuration file not found"
    exit 1
fi
source "$CONFIG_FILE"

# ---------------- INPUT ----------------
FILE_PATH="$1"
DROPBOX_FOLDER="$2"

if [[ -z "$FILE_PATH" ]] || [[ ! -f "$FILE_PATH" ]] || [[ -z "$DROPBOX_FOLDER" ]]; then
    echo "Usage: $0 <local_file> <dropbox_folder>"
    echo "Example: $0 /db.zip /backup"
    exit 1
fi

FILE_NAME=$(basename "$FILE_PATH")

# Ensure folder starts with /
[[ "$DROPBOX_FOLDER" != /* ]] && DROPBOX_FOLDER="/$DROPBOX_FOLDER"

DROPBOX_DEST_PATH="$DROPBOX_FOLDER/$FILE_NAME"
FILE_SIZE=$(stat -c%s "$FILE_PATH")

CHUNK_SIZE=$((8 * 1024 * 1024))   # 8MB

echo "File: $FILE_NAME"
echo "Size: $FILE_SIZE bytes"
echo "Dropbox destination: $DROPBOX_DEST_PATH"

# ---------------- AUTH ----------------
echo "Fetching access token..."

RESPONSE=$(curl -s -X POST https://api.dropbox.com/oauth2/token \
    -u "$APP_KEY:$APP_SECRET" \
    -d grant_type=refresh_token \
    -d refresh_token="$REFRESH_TOKEN")

ACCESS_TOKEN=$(echo "$RESPONSE" | grep -oP '(?<="access_token": ")[^"]*')

if [[ -z "$ACCESS_TOKEN" ]]; then
    echo "Error: Failed to fetch access token"
    exit 1
fi

echo "Token OK"

# ---------------- SMALL FILE ----------------
if [[ "$FILE_SIZE" -le $((150 * 1024 * 1024)) ]]; then
    echo "Using simple upload..."

    curl -s -X POST https://content.dropboxapi.com/2/files/upload \
        --header "Authorization: Bearer $ACCESS_TOKEN" \
        --header "Dropbox-API-Arg: {\"path\": \"$DROPBOX_DEST_PATH\", \"mode\": \"overwrite\"}" \
        --header "Content-Type: application/octet-stream" \
        --data-binary @"$FILE_PATH"

    echo "Upload complete"
    exit 0
fi

# ---------------- CHUNKED UPLOAD ----------------
echo "Using chunked upload..."

OFFSET=0

SESSION_ID=$(dd if="$FILE_PATH" bs=1 count=$CHUNK_SIZE 2>/dev/null | \
curl -s -X POST https://content.dropboxapi.com/2/files/upload_session/start \
    --header "Authorization: Bearer $ACCESS_TOKEN" \
    --header "Dropbox-API-Arg: {\"close\": false}" \
    --header "Content-Type: application/octet-stream" \
    --data-binary @- | grep -oP '(?<="session_id": ")[^"]*')

if [[ -z "$SESSION_ID" ]]; then
    echo "Error: Failed to start upload session"
    exit 1
fi

OFFSET=$CHUNK_SIZE
echo "Session started: $SESSION_ID"

while [[ $OFFSET -lt $FILE_SIZE ]]; do
    BYTES_LEFT=$((FILE_SIZE - OFFSET))
    THIS_CHUNK=$CHUNK_SIZE
    [[ $BYTES_LEFT -lt $CHUNK_SIZE ]] && THIS_CHUNK=$BYTES_LEFT

    dd if="$FILE_PATH" bs=1 skip=$OFFSET count=$THIS_CHUNK 2>/dev/null | \
    curl -s -X POST https://content.dropboxapi.com/2/files/upload_session/append_v2 \
        --header "Authorization: Bearer $ACCESS_TOKEN" \
        --header "Dropbox-API-Arg: {\"cursor\": {\"session_id\": \"$SESSION_ID\", \"offset\": $OFFSET}, \"close\": false}" \
        --header "Content-Type: application/octet-stream" \
        --data-binary @-

    OFFSET=$((OFFSET + THIS_CHUNK))
    echo "Uploaded $OFFSET / $FILE_SIZE"
done

# ---------------- FINISH ----------------
curl -s -X POST https://content.dropboxapi.com/2/files/upload_session/finish \
    --header "Authorization: Bearer $ACCESS_TOKEN" \
    --header "Dropbox-API-Arg: {\"cursor\": {\"session_id\": \"$SESSION_ID\", \"offset\": $FILE_SIZE}, \"commit\": {\"path\": \"$DROPBOX_DEST_PATH\", \"mode\": \"overwrite\"}}" \
    --header "Content-Type: application/octet-stream"

echo "Upload completed successfully to $DROPBOX_DEST_PATH"

