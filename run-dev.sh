#!/bin/bash

# Development script for MoodTracker
echo "🚀 Starting MoodTracker in development mode..."

# Build the application
echo "🔨 Building application..."
./mvnw clean package -DskipTests

# Run with development profile
echo "🌱 Starting with development profile (port 8081)..."
java -jar target/mood-tracker-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev

echo "✅ Development server started at http://localhost:8081"
echo "🔧 H2 Console available at http://localhost:8081/h2-console" 