#!/bin/bash

# Enhanced deployment script for MoodTracker
set -e  # Exit on any error

echo "🚀 Starting MoodTracker deployment..."

# Stop existing application
echo "🛑 Stopping existing application..."
pkill -f mood-tracker || echo "No existing application found"
sleep 5

# Check if process is still running
if pgrep -f mood-tracker > /dev/null; then
    echo "⚠️  Force killing existing application..."
    pkill -9 -f mood-tracker
    sleep 3
fi

# Backup existing application
echo "💾 Creating backup..."
if [ -d "/var/www/lab03-moodtracker" ]; then
    BACKUP_DIR="/var/www/lab03-moodtracker.backup.$(date +%Y%m%d_%H%M%S)"
    cp -r /var/www/lab03-moodtracker "$BACKUP_DIR"
    echo "✅ Backup created: $BACKUP_DIR"
else
    echo "⚠️  No existing application directory found"
fi

# Create target directory if it doesn't exist
echo "📁 Creating directories..."
mkdir -p /var/www/lab03-moodtracker/target
cd /var/www/lab03-moodtracker

# Check if JAR file exists
if [ ! -f "target/mood-tracker-0.0.1-SNAPSHOT.jar" ]; then
    echo "❌ JAR file not found: target/mood-tracker-0.0.1-SNAPSHOT.jar"
    echo "📋 Available files in target/:"
    ls -la target/ || echo "Target directory is empty"
    exit 1
fi

# Set proper permissions
echo "🔐 Setting permissions..."
chmod +x target/mood-tracker-0.0.1-SNAPSHOT.jar

# Check Java installation
echo "☕ Checking Java installation..."
java -version || {
    echo "❌ Java is not installed or not in PATH"
    exit 1
}

# Start application
echo "🚀 Starting application..."
nohup java -jar target/mood-tracker-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod > moodtracker.log 2>&1 &

# Get the PID
APP_PID=$!
echo "📝 Application PID: $APP_PID"

# Wait for application to start
echo "⏳ Waiting for application to start..."
sleep 15

# Check if application is running
if pgrep -f mood-tracker > /dev/null; then
    echo "✅ Application deployed successfully!"
    echo "🌐 Access at: http://$(hostname -I | awk '{print $1}'):8080"
    echo "📊 Process info:"
    ps aux | grep mood-tracker | grep -v grep
    echo "📝 Recent logs:"
    tail -10 moodtracker.log
else
    echo "❌ Application failed to start"
    echo "📝 Full application log:"
    cat moodtracker.log
    echo "🔍 Checking for errors..."
    grep -i error moodtracker.log || echo "No explicit errors found in log"
    exit 1
fi

echo "🎉 Deployment completed successfully!" 