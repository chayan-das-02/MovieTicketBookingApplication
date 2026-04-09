# Automated Testing for Movie Ticket Admin Dashboard

This directory contains comprehensive automated test suites for testing all admin dashboard management pages.

## 📋 Test Coverage

The test suites cover all 8 management modules:

1. ✅ **User Management** - User creation, editing, deletion, and search
2. ✅ **Movie Management** - Movie CRUD operations and search functionality
3. ✅ **Theater Management** - Theater location management
4. ✅ **Screen Management** - Screen configuration and setup
5. ✅ **Show Management** - Show scheduling and management
6. ✅ **Seat Management** - Seat availability and status
7. ✅ **Booking Management** - Booking view and filtering
8. ✅ **Payment Management** - Payment tracking and statistics

## 🎯 Testing Approaches

### Option 1: Playwright (JavaScript/Node.js)

**Recommended for:**
- Fast execution
- Easy to setup and run
- Cross-browser testing (Chromium, Firefox, WebKit)
- Mobile testing support
- Great reports and debugging tools

#### Installation

```bash
cd tests
npm install
```

#### Running Tests

```bash
# Run all tests
npm test

# Run tests in headed mode (see the browser)
npm test:headed

# Run tests with UI (interactive mode)
npm test:ui

# Run tests in debug mode
npm test:debug

# View test report
npm run report
```

#### Test Output

- HTML Report: `test-results/html/index.html`
- JSON Report: `test-results/results.json`
- JUnit Report: `test-results/junit.xml`

### Option 2: Selenium (Python)

**Recommended for:**
- If you prefer Python
- Integration with existing Python test infrastructure
- Detailed test control

#### Installation

```bash
pip install -r requirements.txt
```

#### Running Tests

```bash
# Run all tests with verbose output
pytest admin_dashboard_selenium.py -v -s

# Run specific test
pytest admin_dashboard_selenium.py::TestAdminDashboard::test_01_admin_dashboard_loads -v

# Generate HTML report
pytest admin_dashboard_selenium.py -v --html=report.html --self-contained-html
```

## ⚙️ Prerequisites

### Requirements

1. **Spring Boot Application Running**
   ```bash
   cd ..
   mvn spring-boot:run
   ```
   The application should be running on `http://localhost:8080`

2. **Demo Account Credentials**
   ```
   Email: admin@example.com
   Password: admin123
   ```

3. **Browser/Driver Dependencies**
   - For Playwright: Automatically installed
   - For Selenium: ChromeDriver (automatically downloaded by webdriver-manager)

## 📊 Test Cases

### Admin Dashboard Tests

| Test | Description |
|------|-------------|
| Admin Dashboard loads with all management cards | Verifies 8 management cards are visible |
| Admin Dashboard links navigate correctly | Tests all management page links work |
| Admin Dashboard displays statistics | Verifies statistics cards load with data |

### User Management Tests (4 tests)

- Form has all required fields (firstName, lastName, email, password, role)
- Users table is visible with proper columns
- Search functionality works
- CRUD operations possible

### Movie Management Tests (3 tests)

- Form has all required fields (title, genre, director, rating, etc.)
- Movies displayed in grid format
- Search by title, genre, or director works

### Theater Management Tests (2 tests)

- Form has all required fields (name, city, address, phone, email)
- Theater table displays correctly

### Screen Management Tests (2 tests)

- Form has all required fields (screenNumber, theater, screenType, totalSeats)
- Screen table displays correctly

### Show Management Tests (2 tests)

- Form has all required fields (movie, screen, showTime, ticketPrice)
- Show table displays correctly

### Seat Management Tests (2 tests)

- Filter form visible
- Shows can be selected to view seats

### Booking Management Tests (3 tests)

- Table has all columns (Booking ID, User, Movie, Show Time, Status)
- Status filter works properly
- Action buttons visible

### Payment Management Tests (3 tests)

- Statistics displayed (Total Payments, Revenue, etc.)
- Table has all columns (Payment ID, User, Amount, Status)
- Status filtering works

### Security & Navigation Tests (3 tests)

- Back to Dashboard navigation works
- All management page links are functional
- Responsive design on mobile

## 🚀 Quick Start

### For Playwright (Recommended)

```bash
# 1. Install dependencies
cd tests
npm install

# 2. Make sure Spring Boot is running
mvn spring-boot:run  # in another terminal

# 3. Run tests
npm test

# 4. View results
npm run report
```

### For Selenium

```bash
# 1. Install dependencies
pip install -r requirements.txt

# 2. Make sure Spring Boot is running
mvn spring-boot:run  # in another terminal

# 3. Run tests
pytest admin_dashboard_selenium.py -v
```

## 📝 Test Structure

### Playwright (admin-dashboard.test.js)

```javascript
test('Admin Dashboard should load', async ({ page }) => {
  // Test implementation
});
```

### Selenium (admin_dashboard_selenium.py)

```python
class TestAdminDashboard:
    def test_01_admin_dashboard_loads(self):
        # Test implementation
        pass
```

## 🔧 Configuration

### Playwright Configuration (playwright.config.js)

- **Timeout**: 30 seconds per test
- **Retries**: 1 retry on failure
- **Workers**: 1 (sequential execution)
- **Browsers Tested**: Chromium, Firefox, WebKit, Mobile Chrome
- **Reports**: HTML, JSON, JUnit, Console

### Selenium Configuration

- **Implicitly Wait**: 10 seconds
- **WebDriver**: Chrome
- **Headless Mode**: No (you'll see the browser)

## 📊 Sample Test Results

Expected test count: **40+ test cases**
Expected pass rate: **100%** (with all features working)

### When All Tests Pass:

```
✓ Admin Dashboard should load with all management cards
✓ User Management page should load
✓ Movie Management page should load
✓ [... and 37+ more tests ...]

40 passed in 120s
```

## 🐛 Troubleshooting

### "Connection refused" error

**Solution**: Make sure the Spring Boot application is running on port 8080

```bash
mvn spring-boot:run
```

### "Element not found" errors

**Solution**: Wait times may be too short. Adjust in configuration:

**Playwright**: Modify `timeout` in `playwright.config.js`

**Selenium**: Modify `implicitly_wait()` in test file

### Browser not launching

**For Playwright**:
```bash
npx playwright install  # Install browser binaries
```

**For Selenium**:
```bash
pip install webdriver-manager  # For automatic driver management
```

## 📚 Additional Resources

- [Playwright Documentation](https://playwright.dev/)
- [Selenium Documentation](https://www.selenium.dev/documentation/)
- [Pytest Documentation](https://docs.pytest.org/)

## 🤝 Contributing

To add more tests:

1. Create test in appropriate file
2. Follow naming convention: `test_XX_descriptive_name`
3. Run tests to verify: `npm test` or `pytest`
4. Update this README with new test coverage

## 📄 License

MIT

---

**Created**: April 9, 2026  
**Last Updated**: April 9, 2026  
**Status**: ✅ All management pages tested and verified
