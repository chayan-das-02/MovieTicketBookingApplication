# Instructions

- Following Playwright test failed.
- Explain why, be concise, respect Playwright best practices.
- Provide a snippet of code with the fix, if possible.

# Test info

- Name: admin-dashboard.test.js >> Admin Dashboard Management Pages >> Admin Dashboard should load with all management cards
- Location: admin-dashboard.test.js:24:7

# Error details

```
Test timeout of 30000ms exceeded while running "beforeEach" hook.
```

```
Error: page.waitForURL: Test timeout of 30000ms exceeded.
=========================== logs ===========================
waiting for navigation to "http://localhost:8080/admin-dashboard.html" until "load"
============================================================
```

# Page snapshot

```yaml
- generic [ref=e1]:
  - navigation [ref=e2]:
    - generic [ref=e3]:
      - generic [ref=e4]: 🎬 MovieTickets
      - list [ref=e5]:
        - listitem [ref=e6]:
          - link "Home" [ref=e7] [cursor=pointer]:
            - /url: index.html
        - listitem [ref=e8]:
          - link "Login" [ref=e9] [cursor=pointer]:
            - /url: login.html
        - listitem [ref=e10]:
          - link "Register" [ref=e11] [cursor=pointer]:
            - /url: register.html
  - generic [ref=e12]:
    - heading "🎬 Login" [level=1] [ref=e14]
    - generic [ref=e15]:
      - heading "Demo Credentials:" [level=4] [ref=e16]
      - paragraph [ref=e17]:
        - strong [ref=e18]: "Admin:"
        - text: admin@example.com / admin123
      - paragraph [ref=e19]:
        - strong [ref=e20]: "User:"
        - text: john@example.com / password123
    - generic [ref=e21]:
      - generic [ref=e22]:
        - generic [ref=e23]: Email
        - textbox "Email" [ref=e24]:
          - /placeholder: Enter your email
          - text: admin@example.com
      - generic [ref=e25]:
        - generic [ref=e26]: Password
        - textbox "Password" [ref=e27]:
          - /placeholder: Enter your password
          - text: admin123
      - button "Login" [active] [ref=e28] [cursor=pointer]
    - generic [ref=e29]: "Login failed: could not prepare statement [[SQLITE_ERROR] SQL error or missing database (no such table: users)] [select u1_0.user_id,u1_0.created_at,u1_0.email,u1_0.first_name,u1_0.last_name,u1_0.password,u1_0.phone_number,u1_0.role,u1_0.updated_at from users u1_0 where u1_0.email=?]"
    - generic [ref=e30]:
      - text: Don't have an account?
      - link "Register here" [ref=e31] [cursor=pointer]:
        - /url: register.html
```

# Test source

```ts
  1   | import { test, expect } from '@playwright/test';
  2   | 
  3   | const BASE_URL = 'http://localhost:8080';
  4   | const ADMIN_EMAIL = 'admin@example.com';
  5   | const ADMIN_PASSWORD = 'admin123';
  6   | 
  7   | // Login helper function
  8   | async function loginAsAdmin(page) {
  9   |   await page.goto(`${BASE_URL}/login.html`);
  10  |   await page.fill('input[name="email"]', ADMIN_EMAIL);
  11  |   await page.fill('input[name="password"]', ADMIN_PASSWORD);
  12  |   await page.click('button[type="submit"]');
> 13  |   await page.waitForURL(`${BASE_URL}/admin-dashboard.html`);
      |              ^ Error: page.waitForURL: Test timeout of 30000ms exceeded.
  14  | }
  15  | 
  16  | test.describe('Admin Dashboard Management Pages', () => {
  17  |   
  18  |   test.beforeEach(async ({ page }) => {
  19  |     // Login before each test
  20  |     await loginAsAdmin(page);
  21  |   });
  22  | 
  23  |   // ============ ADMIN DASHBOARD TESTS ============
  24  |   test('Admin Dashboard should load with all management cards', async ({ page }) => {
  25  |     await page.goto(`${BASE_URL}/admin-dashboard.html`);
  26  |     
  27  |     // Check if dashboard title exists
  28  |     await expect(page.locator('h1')).toContainText('Admin Dashboard');
  29  |     
  30  |     // Check for all 8 management cards
  31  |     const management_modules = [
  32  |       'User Management',
  33  |       'Movie Management',
  34  |       'Theater Management',
  35  |       'Screen Management',
  36  |       'Show Management',
  37  |       'Seat Management',
  38  |       'Booking Management',
  39  |       'Payment Management'
  40  |     ];
  41  |     
  42  |     for (const module of management_modules) {
  43  |       await expect(page.locator('h3')).toContainText(module);
  44  |     }
  45  |     
  46  |     // Check for statistics cards
  47  |     await expect(page.locator('text=Total Users')).toBeVisible();
  48  |     await expect(page.locator('text=Total Movies')).toBeVisible();
  49  |     await expect(page.locator('text=Total Theaters')).toBeVisible();
  50  |     await expect(page.locator('text=Total Bookings')).toBeVisible();
  51  |   });
  52  | 
  53  |   test('Admin Dashboard links navigate correctly', async ({ page }) => {
  54  |     await page.goto(`${BASE_URL}/admin-dashboard.html`);
  55  |     
  56  |     // Test User Management link
  57  |     await page.click('a:has-text("Manage Users")');
  58  |     await page.waitForURL(`**/user-management.html`);
  59  |     await expect(page.locator('h1')).toContainText('User Management');
  60  |     
  61  |     // Go back to dashboard
  62  |     await page.click('a:has-text("Back to Dashboard")');
  63  |     await page.waitForURL(`**/admin-dashboard.html`);
  64  |   });
  65  | 
  66  |   // ============ USER MANAGEMENT TESTS ============
  67  |   test('User Management page should load', async ({ page }) => {
  68  |     await page.goto(`${BASE_URL}/user-management.html`);
  69  |     
  70  |     await expect(page.locator('h1')).toContainText('User Management');
  71  |     await expect(page.locator('h3:has-text("Add New User")')).toBeVisible();
  72  |     await expect(page.locator('h3:has-text("All Users")')).toBeVisible();
  73  |     await expect(page.locator('table')).toBeVisible();
  74  |   });
  75  | 
  76  |   test('User Management form has all required fields', async ({ page }) => {
  77  |     await page.goto(`${BASE_URL}/user-management.html`);
  78  |     
  79  |     // Check form fields
  80  |     await expect(page.locator('input[name="firstName"]')).toBeVisible();
  81  |     await expect(page.locator('input[name="lastName"]')).toBeVisible();
  82  |     await expect(page.locator('input[name="email"]')).toBeVisible();
  83  |     await expect(page.locator('input[name="password"]')).toBeVisible();
  84  |     await expect(page.locator('select[name="role"]')).toBeVisible();
  85  |     
  86  |     // Check table headers
  87  |     await expect(page.locator('th:has-text("Name")')).toBeVisible();
  88  |     await expect(page.locator('th:has-text("Email")')).toBeVisible();
  89  |     await expect(page.locator('th:has-text("Role")')).toBeVisible();
  90  |   });
  91  | 
  92  |   test('User Management search functionality works', async ({ page }) => {
  93  |     await page.goto(`${BASE_URL}/user-management.html`);
  94  |     
  95  |     // Wait for users to load
  96  |     await page.waitForTimeout(1000);
  97  |     
  98  |     // Search for admin user
  99  |     await page.fill('#searchInput', 'admin');
  100 |     await page.click('button:has-text("Search")');
  101 |     
  102 |     // Verify search results - should still see at least the admin user
  103 |     await page.waitForTimeout(500);
  104 |     const visibleRows = await page.locator('tbody tr:visible').count();
  105 |     expect(visibleRows).toBeGreaterThan(0);
  106 |   });
  107 | 
  108 |   // ============ MOVIE MANAGEMENT TESTS ============
  109 |   test('Movie Management page should load', async ({ page }) => {
  110 |     await page.goto(`${BASE_URL}/movie-management.html`);
  111 |     
  112 |     await expect(page.locator('h1')).toContainText('Movie Management');
  113 |     await expect(page.locator('text=Add New Movie')).toBeVisible();
```