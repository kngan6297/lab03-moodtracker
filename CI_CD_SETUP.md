# CI/CD Pipeline Setup Guide

This guide will help you set up automated CI/CD pipelines for your MoodTracker application.

## 🚀 Pipeline Overview

The CI/CD pipeline includes:

- **CI Pipeline**: Build, test, and create artifacts
- **Auto Deploy**: Automatic deployment on successful builds
- **Manual Deploy**: Manual deployment with environment selection
- **Rollback**: Emergency rollback to previous versions

## 📋 Prerequisites

### 1. GitHub Repository

- Push your code to a GitHub repository
- Ensure you have admin access to the repository

### 2. VPS Access

- SSH access to your VPS
- SSH key pair for secure authentication

## 🔧 Setup Steps

### Step 1: Generate SSH Key Pair

```bash
# Generate SSH key pair
ssh-keygen -t rsa -b 4096 -C "github-actions@your-domain.com"

# Copy public key to VPS
ssh-copy-id -i ~/.ssh/id_rsa.pub root@your-vps-ip

# Copy private key content (you'll need this for GitHub secrets)
cat ~/.ssh/id_rsa
```

### Step 2: Configure GitHub Secrets

Go to your GitHub repository → Settings → Secrets and variables → Actions

Add these secrets:

| Secret Name    | Value                                    | Description           |
| -------------- | ---------------------------------------- | --------------------- |
| `VPS_HOST`     | `your-vps-ip`                            | Your VPS IP address   |
| `VPS_USERNAME` | `root`                                   | SSH username          |
| `VPS_SSH_KEY`  | `-----BEGIN OPENSSH PRIVATE KEY-----...` | Your private SSH key  |
| `VPS_PORT`     | `22`                                     | SSH port (usually 22) |

### Step 3: Database Setup on VPS

```bash
# Connect to your VPS
ssh root@your-vps-ip

# Install MariaDB (if not already installed)
sudo apt update
sudo apt install mariadb-server mariadb-client

# Secure MariaDB
sudo mysql_secure_installation

# Create database and user
sudo mysql -u root -p
```

In MariaDB:

```sql
CREATE DATABASE moodtracker;
CREATE USER 'mooduser'@'localhost' IDENTIFIED BY 'yourpassword';
GRANT ALL PRIVILEGES ON moodtracker.* TO 'mooduser'@'localhost';
FLUSH PRIVILEGES;
EXIT;
```

### Step 4: Prepare VPS Directory

```bash
# Create application directory
mkdir -p /var/www/lab03-moodtracker
cd /var/www/lab03-moodtracker

# Set proper permissions
chown -R root:root /var/www/lab03-moodtracker
chmod -R 755 /var/www/lab03-moodtracker
```

## 🔄 Pipeline Workflows

### 1. CI/CD Pipeline (`ci.yml`)

**Triggers**: Push to main/develop, Pull requests
**Actions**:

- Build application with Maven
- Run tests
- Create JAR artifact
- Upload configuration files

### 2. Auto Deploy (`deploy.yml`)

**Triggers**: After successful CI pipeline
**Actions**:

- Download artifacts
- Deploy to VPS via SSH
- Start application
- Verify deployment

### 3. Manual Deploy (`manual-deploy.yml`)

**Triggers**: Manual trigger
**Actions**:

- Build and deploy on demand
- Environment selection
- Immediate deployment

### 4. Rollback (`rollback.yml`)

**Triggers**: Manual trigger
**Actions**:

- Rollback to previous version
- Backup current version
- Restore from backup

## 🚀 Usage

### Automatic Deployment

1. Push code to `main` branch
2. CI pipeline runs automatically
3. If successful, auto-deploy triggers
4. Application deploys to VPS

### Manual Deployment

1. Go to GitHub → Actions
2. Select "Manual Deploy" workflow
3. Click "Run workflow"
4. Select environment and run

### Emergency Rollback

1. Go to GitHub → Actions
2. Select "Rollback Deployment" workflow
3. Enter backup date (YYYYMMDD_HHMMSS)
4. Click "Run workflow"

## 📊 Monitoring

### Check Deployment Status

```bash
# On VPS
ps aux | grep mood-tracker
netstat -tulpn | grep :8080
tail -f /var/www/lab03-moodtracker/moodtracker.log
```

### View Available Backups

```bash
ls -la /var/www/lab03-moodtracker.backup.*
```

## 🔧 Troubleshooting

### Common Issues

1. **SSH Connection Failed**

   - Verify SSH key in GitHub secrets
   - Check VPS firewall settings
   - Ensure SSH service is running

2. **Application Won't Start**

   - Check MariaDB connection
   - Verify port availability
   - Review application logs

3. **Deployment Fails**
   - Check VPS disk space
   - Verify file permissions
   - Review GitHub Actions logs

### Debug Commands

```bash
# Check application status
sudo systemctl status moodtracker

# View recent logs
tail -50 /var/www/lab03-moodtracker/moodtracker.log

# Check database connection
mysql -u mooduser -p moodtracker

# Test port availability
netstat -tulpn | grep :8080
```

## 🔒 Security Considerations

1. **SSH Keys**: Use dedicated SSH keys for CI/CD
2. **Database**: Use strong passwords for MariaDB
3. **Firewall**: Configure firewall rules properly
4. **Backups**: Regular backups of application and database
5. **Logs**: Monitor application logs for security issues

## 📈 Advanced Features

### Environment-Specific Deployments

- Production: `main` branch → VPS
- Staging: `develop` branch → Staging server
- Development: Local development with H2

### Database Migrations

- Automatic schema updates with `spring.jpa.hibernate.ddl-auto=update`
- Backup before deployments
- Rollback capability

### Health Checks

- Application health endpoint
- Database connectivity checks
- Port availability verification

## 🎯 Next Steps

1. **Set up monitoring**: Add application monitoring
2. **SSL/TLS**: Configure HTTPS for production
3. **Load balancing**: Add load balancer for high availability
4. **Backup strategy**: Implement automated database backups
5. **Alerting**: Set up alerts for deployment failures

Your CI/CD pipeline is now ready! 🚀
