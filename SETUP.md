# 🚀 Quick Setup Guide for MoodTracker

## Prerequisites Installation

### 1. Install Java 17

**Download and Install Java 17:**

1. Go to [Adoptium Eclipse Temurin](https://adoptium.net/temurin/releases/?version=17)
2. Download "Windows x64 MSI Installer" for Java 17
3. Run the installer and follow the setup wizard
4. **Important:** Make sure to check "Add to PATH" during installation

**Verify Java Installation:**

```powershell
java --version
```

You should see something like:

```
openjdk 17.0.x 2023-xx-xx
OpenJDK Runtime Environment Temurin-17.0.x+x (build 17.0.x+x)
OpenJDK 64-Bit Server VM Temurin-17.0.x+x (build 17.0.x+x, mixed mode, sharing)
```

### 2. Set JAVA_HOME Environment Variable

**Using PowerShell (Run as Administrator):**

```powershell
# Find Java installation path (usually C:\Program Files\Eclipse Adoptium\jdk-17.x.x.x-hotspot)
$javaPath = (Get-Command java).Path
$javaHome = Split-Path (Split-Path $javaPath)
[Environment]::SetEnvironmentVariable("JAVA_HOME", $javaHome, "Machine")
```

**Or manually:**

1. Open System Properties → Advanced → Environment Variables
2. Add new System Variable:
   - Variable name: `JAVA_HOME`
   - Variable value: `C:\Program Files\Eclipse Adoptium\jdk-17.x.x.x-hotspot`
3. Add `%JAVA_HOME%\bin` to your PATH variable

### 3. Install MariaDB (Optional - for full functionality)

**Download MariaDB:**

1. Go to [MariaDB Downloads](https://mariadb.org/download/)
2. Download MariaDB Community Server for Windows
3. Run the installer
4. Set root password (remember this!)

**Or use XAMPP (Easier):**

1. Download [XAMPP](https://www.apachefriends.org/download.html)
2. Install with MariaDB option
3. Start MariaDB from XAMPP Control Panel

## 🎯 Running the Application

### Option 1: Using Maven Wrapper (Recommended)

The project includes a Maven wrapper, so you don't need to install Maven globally:

```powershell
# Build the project
.\mvnw.cmd clean install

# Run the application
.\mvnw.cmd spring-boot:run
```

### Option 2: Install Maven Globally

If you prefer to install Maven globally:

1. Download Maven from [https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi)
2. Extract to `C:\Program Files\Apache\maven`
3. Add `C:\Program Files\Apache\maven\bin` to your PATH
4. Run: `mvn spring-boot:run`

## 🔧 Database Configuration

### Option 1: Use H2 (In-Memory Database) - Easiest

The application can run with H2 database for testing. Edit `src/main/resources/application.properties`:

```properties
# Comment out MariaDB settings and use H2
# spring.datasource.url=${DB_URL:jdbc:mariadb://localhost:3306/moodtracker}
# spring.datasource.username=${DB_USERNAME:root}
# spring.datasource.password=${DB_PASSWORD:password}
# spring.datasource.driver-class-name=org.mariadb.jdbc.Driver

# Use H2 instead
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
```

### Option 2: Use MariaDB (Production)

1. Create database:

```sql
CREATE DATABASE moodtracker;
```

2. Set environment variables:

```powershell
$env:DB_URL="jdbc:mariadb://localhost:3306/moodtracker"
$env:DB_USERNAME="root"
$env:DB_PASSWORD="your_password"
```

## 🎉 Access the Application

Once running, open your browser and go to:

- **Web Interface:** http://localhost:8080
- **H2 Console (if using H2):** http://localhost:8080/h2-console

## 🐛 Troubleshooting

### "Java not found" error:

- Make sure Java 17+ is installed
- Verify JAVA_HOME is set correctly
- Restart your terminal after setting environment variables

### "Port 8080 already in use":

- Change port in `application.properties`: `server.port=8081`
- Or kill the process using port 8080

### "Database connection failed":

- Check if MariaDB is running
- Verify database credentials
- Try using H2 database for testing

### "Maven wrapper not working":

- Make sure you're in the project directory
- Try: `.\mvnw.cmd --version` to test

## 📝 Quick Test

After setup, you should be able to:

1. Add mood entries
2. View all entries
3. Edit and delete entries
4. Use the REST API at `/api/moods`

## 🆘 Need Help?

If you encounter issues:

1. Check that Java 17+ is installed: `java --version`
2. Verify JAVA_HOME is set: `echo $env:JAVA_HOME`
3. Try the H2 database option for quick testing
4. Check the application logs for error messages
