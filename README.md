# MoodTracker - Spring Boot Application

A simple web application to track daily moods and emotions built with Spring Boot, MariaDB, and Bootstrap 5.

## Features

- ✅ Track daily moods with notes and emojis
- ✅ RESTful API for CRUD operations
- ✅ Beautiful Bootstrap 5 UI
- ✅ MariaDB database integration
- ✅ Environment variable configuration
- ✅ Responsive design

## Project Structure

```
MoodTracker/
├── src/main/java/com/example/moodtracker/
│   ├── MoodTrackerApplication.java          # Main Spring Boot application
│   ├── entity/
│   │   └── MoodEntry.java                  # JPA entity
│   ├── repository/
│   │   └── MoodEntryRepository.java        # JPA repository
│   └── controller/
│       ├── MoodEntryController.java        # REST API controller
│       └── WebController.java              # Web controller for Thymeleaf
├── src/main/resources/
│   ├── application.properties              # Database configuration
│   └── templates/
│       ├── index.html                      # Main page template
│       └── form.html                       # Form template
├── pom.xml                                 # Maven dependencies
└── README.md                               # This file
```

## Prerequisites

- Java 17 or higher
- Maven 3.6+
- MariaDB 10.5+ (or MySQL 8.0+)

## Setup Instructions

### 1. Database Setup

Create a MariaDB database:

```sql
CREATE DATABASE moodtracker;
CREATE USER 'mooduser'@'localhost' IDENTIFIED BY 'your_password';
GRANT ALL PRIVILEGES ON moodtracker.* TO 'mooduser'@'localhost';
FLUSH PRIVILEGES;
```

### 2. Environment Variables

Set the following environment variables:

**Windows (PowerShell):**
```powershell
$env:DB_URL="jdbc:mariadb://localhost:3306/moodtracker"
$env:DB_USERNAME="mooduser"
$env:DB_PASSWORD="your_password"
```

**Windows (Command Prompt):**
```cmd
set DB_URL=jdbc:mariadb://localhost:3306/moodtracker
set DB_USERNAME=mooduser
set DB_PASSWORD=your_password
```

**Linux/Mac:**
```bash
export DB_URL=jdbc:mariadb://localhost:3306/moodtracker
export DB_USERNAME=mooduser
export DB_PASSWORD=your_password
```

### 3. Build and Run

```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## API Endpoints

### REST API (`/api/moods`)

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/moods` | Get all mood entries |
| GET | `/api/moods/{id}` | Get mood entry by ID |
| POST | `/api/moods` | Create new mood entry |
| PUT | `/api/moods/{id}` | Update mood entry |
| DELETE | `/api/moods/{id}` | Delete mood entry |
| GET | `/api/moods/date/{date}` | Get entries by date |
| GET | `/api/moods/mood/{mood}` | Get entries by mood type |

### Web Interface

| URL | Description |
|-----|-------------|
| `/` | Home page with mood list |
| `/add` | Add new mood form |
| `/edit/{id}` | Edit mood form |
| `/delete/{id}` | Delete mood entry |

## MoodEntry Entity

```java
public class MoodEntry {
    private Long id;           // Primary key
    private LocalDate date;    // Required date
    private String mood;       // Required mood (Happy, Sad, etc.)
    private String note;       // Optional note
    private String emoji;      // Optional emoji
}
```

## Database Schema

The application automatically creates the following table:

```sql
CREATE TABLE mood_entries (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    date DATE NOT NULL,
    mood VARCHAR(255) NOT NULL,
    note TEXT,
    emoji VARCHAR(255)
);
```

## Technologies Used

- **Spring Boot 3.2.0** - Main framework
- **Spring Data JPA** - Database access
- **MariaDB** - Database
- **Thymeleaf** - Template engine
- **Bootstrap 5** - CSS framework
- **Font Awesome** - Icons
- **Maven** - Build tool

## Development

### Running in Development Mode

```bash
# Run with hot reload (if you have spring-boot-devtools)
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dspring.devtools.restart.enabled=true"
```

### Database Configuration

The application uses environment variables for database configuration:

- `DB_URL` - Database connection URL
- `DB_USERNAME` - Database username
- `DB_PASSWORD` - Database password

If environment variables are not set, it defaults to:
- URL: `jdbc:mariadb://localhost:3306/moodtracker`
- Username: `root`
- Password: `password`

## Features

### Frontend Features
- 📱 Responsive design for mobile and desktop
- 🎨 Modern Bootstrap 5 UI
- 📝 Quick add form on home page
- ✏️ Edit and delete functionality
- 📅 Date picker with today's date default
- 😊 Predefined mood options with emojis
- 💡 Helpful tips and guidance

### Backend Features
- 🔒 Input validation
- 📊 RESTful API
- 🗄️ JPA/Hibernate ORM
- 🔄 Automatic table creation
- 📝 Comprehensive logging
- ⚡ Fast startup and response times

## Troubleshooting

### Common Issues

1. **Database Connection Error**
   - Verify MariaDB is running
   - Check database credentials
   - Ensure database exists

2. **Port Already in Use**
   - Change port in `application.properties`
   - Kill existing process on port 8080

3. **Build Errors**
   - Ensure Java 17+ is installed
   - Run `mvn clean install`

## License

This project is open source and available under the MIT License. 