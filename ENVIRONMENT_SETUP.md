# Environment-Specific Configuration Setup

This project now supports multiple environments with different database configurations.

## Available Profiles

### 1. Default (Local Development)

- **Database**: H2 (in-memory)
- **Usage**: `./mvnw spring-boot:run`
- **Features**:
  - H2 console enabled at `/h2-console`
  - SQL logging enabled
  - Thymeleaf cache disabled for development

### 2. Development Profile

- **Database**: H2 (in-memory)
- **Usage**: `./mvnw spring-boot:run -Dspring.profiles.active=dev`
- **Features**: Same as default, but explicitly configured

### 3. Production Profile

- **Database**: MariaDB
- **Usage**: `./mvnw spring-boot:run -Dspring.profiles.active=prod`
- **Features**:
  - MariaDB dialect
  - SQL logging disabled for performance
  - Thymeleaf cache enabled
  - H2 console disabled

### 4. H2 Profile (Testing)

- **Database**: H2 (in-memory)
- **Usage**: `./mvnw spring-boot:run -Dspring.profiles.active=h2`
- **Features**: Specific H2 configuration for testing

## Environment Variables

You can also override settings using environment variables:

```bash
# For VPS deployment
export DB_URL=jdbc:mariadb://localhost:3306/moodtracker
export DB_USERNAME=mooduser
export DB_PASSWORD=yourpassword
export DB_DRIVER=org.mariadb.jdbc.Driver
export DDL_AUTO=update
export H2_CONSOLE_ENABLED=false
export SERVER_PORT=8080

# Then run
./mvnw spring-boot:run
```

## VPS Deployment

For your VPS, you have two options:

### Option 1: Use Production Profile

```bash
./mvnw spring-boot:run -Dspring.profiles.active=prod
```

### Option 2: Use Environment Variables

```bash
export DB_URL=jdbc:mariadb://localhost:3306/moodtracker
export DB_USERNAME=mooduser
export DB_PASSWORD=yourpassword
export DB_DRIVER=org.mariadb.jdbc.Driver
export DDL_AUTO=update
export H2_CONSOLE_ENABLED=false
./mvnw spring-boot:run
```

## Database Setup

### Local Development

- No setup required - H2 runs in-memory
- Access H2 console at `http://localhost:8080/h2-console`

### VPS Production

- Ensure MariaDB is installed and running
- Create database: `CREATE DATABASE moodtracker;`
- Create user: `CREATE USER 'mooduser'@'localhost' IDENTIFIED BY 'yourpassword';`
- Grant permissions: `GRANT ALL PRIVILEGES ON moodtracker.* TO 'mooduser'@'localhost';`
- Flush privileges: `FLUSH PRIVILEGES;`
