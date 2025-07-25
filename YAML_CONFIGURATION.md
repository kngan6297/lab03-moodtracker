# YAML Configuration Setup

This project now uses YAML configuration files for better environment management.

## 📁 Configuration Files

### 1. `application-prod.yml` - Production Configuration

- **Port**: 8080
- **Database**: MariaDB
- **Features**:
  - SQL logging disabled
  - Thymeleaf cache enabled
  - H2 console disabled
  - Optimized for production

### 2. `application-dev.yml` - Development Configuration

- **Port**: 8081
- **Database**: H2 (in-memory)
- **Features**:
  - SQL logging enabled
  - Thymeleaf cache disabled
  - H2 console enabled
  - Debug logging

## 🚀 How to Use

### Local Development

```bash
# Run with development profile (port 8081)
./run-dev.sh

# Or manually
java -jar target/mood-tracker-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev
```

### Production Deployment

```bash
# Run with production profile (port 8080)
./run-prod.sh

# Or manually
java -jar target/mood-tracker-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
```

### CI/CD Pipeline

The GitHub Actions workflow automatically uses:

```bash
java -jar target/mood-tracker-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
```

## 🌐 Access URLs

### Development

- **Application**: http://localhost:8081
- **H2 Console**: http://localhost:8081/h2-console

### Production

- **Application**: http://your-vps-ip:8080
- **Live Site**: https://pawfectfriends.xyz/

## 🔧 Benefits

### ✅ **Automatic Port Management**

- No need to manually change ports
- Just specify the profile: `--spring.profiles.active=dev` or `--spring.profiles.active=prod`

### ✅ **Environment-Specific Settings**

- Different databases per environment
- Different logging levels
- Different caching settings

### ✅ **Clean Configuration**

- YAML is more readable than properties
- Better structure and organization
- Environment-specific overrides

## 📋 Configuration Comparison

| Setting             | Development    | Production |
| ------------------- | -------------- | ---------- |
| **Port**            | 8081           | 8080       |
| **Database**        | H2 (in-memory) | MariaDB    |
| **SQL Logging**     | Enabled        | Disabled   |
| **H2 Console**      | Enabled        | Disabled   |
| **Thymeleaf Cache** | Disabled       | Enabled    |
| **Logging Level**   | DEBUG          | INFO       |

## 🎯 Quick Commands

```bash
# Development
./run-dev.sh

# Production
./run-prod.sh

# Manual with specific profile
java -jar target/mood-tracker-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev
java -jar target/mood-tracker-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
```

## 🔄 Migration from Properties

The old `.properties` files are still supported, but YAML provides:

- Better readability
- Hierarchical structure
- Environment-specific configurations
- Cleaner profile management

Your application now has clean, environment-specific configurations! 🚀
