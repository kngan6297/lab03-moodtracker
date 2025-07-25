#!/bin/bash

# Production script for MoodTracker
echo "🚀 Starting MoodTracker in production mode..."

# Build the application
echo "🔨 Building application..."
./mvnw clean package -DskipTests

# Run with production profile
echo "🏭 Starting with production profile (port 8080)..."
java -jar target/mood-tracker-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod

echo "✅ Production server started at http://localhost:8080" 