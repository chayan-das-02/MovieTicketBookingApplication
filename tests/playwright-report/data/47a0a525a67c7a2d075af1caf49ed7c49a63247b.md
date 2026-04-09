# Instructions

- Following Playwright test failed.
- Explain why, be concise, respect Playwright best practices.
- Provide a snippet of code with the fix, if possible.

# Test info

- Name: admin-dashboard.test.js >> Admin Dashboard Management Pages >> User Management page should load
- Location: admin-dashboard.test.js:67:7

# Error details

```
Error: expect(locator).toBeVisible() failed

Locator: locator('h3:has-text("Add New User")')
Expected: visible
Timeout: 5000ms
Error: element(s) not found

Call log:
  - Expect "toBeVisible" with timeout 5000ms
  - waiting for locator('h3:has-text("Add New User")')

```

# Page snapshot

```yaml
- generic [active] [ref=e1]:
  - navigation [ref=e2]:
    - generic [ref=e3]:
      - generic [ref=e4]: 🎬 MovieTickets
      - list [ref=e5]:
        - listitem [ref=e6]:
          - link "Home" [ref=e7] [cursor=pointer]:
            - /url: index.html
        - listitem [ref=e8]:
          - link "Admin" [ref=e9] [cursor=pointer]:
            - /url: admin-dashboard.html
        - listitem [ref=e10]:
          - link "User" [ref=e11] [cursor=pointer]:
            - /url: user-dashboard.html
  - generic [ref=e13]:
    - generic [ref=e14]:
      - heading "👥 User Management" [level=1] [ref=e15]
      - link "← Back to Dashboard" [ref=e16] [cursor=pointer]:
        - /url: admin-dashboard.html
    - generic [ref=e17]:
      - generic [ref=e18]:
        - heading "➕ Add New User" [level=2] [ref=e19]
        - generic [ref=e20]:
          - generic [ref=e21]:
            - generic [ref=e22]: First Name *
            - textbox "First Name *" [ref=e23]
          - generic [ref=e24]:
            - generic [ref=e25]: Last Name *
            - textbox "Last Name *" [ref=e26]
          - generic [ref=e27]:
            - generic [ref=e28]: Email *
            - textbox "Email *" [ref=e29]
          - generic [ref=e30]:
            - generic [ref=e31]: Password *
            - textbox "Password *" [ref=e32]
          - generic [ref=e33]:
            - generic [ref=e34]: Role *
            - combobox "Role *" [ref=e35]:
              - option "Select Role" [selected]
              - option "User"
              - option "Admin"
          - button "Save User" [ref=e36] [cursor=pointer]
          - button "Clear Form" [ref=e37] [cursor=pointer]
      - generic [ref=e38]:
        - heading "📋 All Users" [level=2] [ref=e39]
        - generic [ref=e40]:
          - textbox "Search by name or email..." [ref=e41]
          - button "Search" [ref=e42] [cursor=pointer]
        - table [ref=e44]:
          - rowgroup [ref=e45]:
            - row "Name Email Role Actions" [ref=e46]:
              - columnheader "Name" [ref=e47]
              - columnheader "Email" [ref=e48]
              - columnheader "Role" [ref=e49]
              - columnheader "Actions" [ref=e50]
          - rowgroup [ref=e51]:
            - row "Loading users..." [ref=e52]:
              - cell "Loading users..." [ref=e53]
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
  13  |   await page.waitForURL(`${BASE_URL}/admin-dashboard.html`);
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
> 71  |     await expect(page.locator('h3:has-text("Add New User")')).toBeVisible();
      |                                                               ^ Error: expect(locator).toBeVisible() failed
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
  114 |     await expect(page.locator('text=All Movies')).toBeVisible();
  115 |   });
  116 | 
  117 |   test('Movie Management form has all required fields', async ({ page }) => {
  118 |     await page.goto(`${BASE_URL}/movie-management.html`);
  119 |     
  120 |     // Check form fields
  121 |     await expect(page.locator('input[name="title"]')).toBeVisible();
  122 |     await expect(page.locator('textarea[name="description"]')).toBeVisible();
  123 |     await expect(page.locator('input[name="genre"]')).toBeVisible();
  124 |     await expect(page.locator('input[name="language"]')).toBeVisible();
  125 |     await expect(page.locator('input[name="director"]')).toBeVisible();
  126 |     await expect(page.locator('input[name="durationMinutes"]')).toBeVisible();
  127 |     await expect(page.locator('select[name="rating"]')).toBeVisible();
  128 |   });
  129 | 
  130 |   test('Movie Management displays movies in grid', async ({ page }) => {
  131 |     await page.goto(`${BASE_URL}/movie-management.html`);
  132 |     
  133 |     // Wait for movies to load
  134 |     await page.waitForTimeout(1500);
  135 |     
  136 |     // Check if movie cards are visible
  137 |     const movieCards = await page.locator('.movie-card').count();
  138 |     expect(movieCards).toBeGreaterThanOrEqual(0);
  139 |     
  140 |     if (movieCards > 0) {
  141 |       await expect(page.locator('.movie-card h3')).toContainText(/./);
  142 |     }
  143 |   });
  144 | 
  145 |   test('Movie Management search works', async ({ page }) => {
  146 |     await page.goto(`${BASE_URL}/movie-management.html`);
  147 |     
  148 |     await page.waitForTimeout(1000);
  149 |     
  150 |     // Search for a movie
  151 |     await page.fill('#searchInput', 'action');
  152 |     await page.click('button:has-text("Search")');
  153 |     
  154 |     await page.waitForTimeout(500);
  155 |     // Verify search is working
  156 |     const movieCards = await page.locator('.movie-card').count();
  157 |     // Should either show results or show 0 if no matches (which is valid)
  158 |     expect(movieCards).toBeGreaterThanOrEqual(0);
  159 |   });
  160 | 
  161 |   // ============ THEATER MANAGEMENT TESTS ============
  162 |   test('Theater Management page should load', async ({ page }) => {
  163 |     await page.goto(`${BASE_URL}/theater-management.html`);
  164 |     
  165 |     await expect(page.locator('h1')).toContainText('Theater Management');
  166 |     await expect(page.locator('text=Add New Theater')).toBeVisible();
  167 |     await expect(page.locator('text=All Theaters')).toBeVisible();
  168 |     await expect(page.locator('table')).toBeVisible();
  169 |   });
  170 | 
  171 |   test('Theater Management form has all required fields', async ({ page }) => {
```