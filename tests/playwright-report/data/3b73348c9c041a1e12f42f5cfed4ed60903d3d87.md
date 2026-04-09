# Instructions

- Following Playwright test failed.
- Explain why, be concise, respect Playwright best practices.
- Provide a snippet of code with the fix, if possible.

# Test info

- Name: admin-dashboard.test.js >> Admin Dashboard Management Pages >> Admin Dashboard should load with all management cards
- Location: admin-dashboard.test.js:24:7

# Error details

```
Error: expect(locator).toContainText(expected) failed

Locator: locator('h3')
Expected substring: "User Management"
Error: strict mode violation: locator('h3') resolved to 4 elements:
    1) <h3>Bhool Bhulaiyaa 3</h3> aka getByRole('heading', { name: 'Bhool Bhulaiyaa' })
    2) <h3>Pathaan</h3> aka getByRole('heading', { name: 'Pathaan' })
    3) <h3>Jawan</h3> aka getByRole('heading', { name: 'Jawan' })
    4) <h3>Gadar 2</h3> aka getByRole('heading', { name: 'Gadar' })

Call log:
  - Expect "toContainText" with timeout 5000ms
  - waiting for locator('h3')

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
  - generic [ref=e12]:
    - generic [ref=e13]:
      - heading "👨‍💼 Admin Dashboard" [level=1] [ref=e14]
      - generic [ref=e15]:
        - paragraph [ref=e16]:
          - text: "Logged in as:"
          - strong [ref=e17]: Admin User
        - paragraph [ref=e18]:
          - text: "Role:"
          - strong [ref=e19]: Admin
        - button "Logout" [ref=e20] [cursor=pointer]
    - generic [ref=e22]:
      - heading "➕ Add New Movie" [level=2] [ref=e23]
      - generic [ref=e24]:
        - generic [ref=e25]:
          - generic [ref=e26]: Movie Title *
          - textbox "Movie Title *" [ref=e27]
        - generic [ref=e28]:
          - generic [ref=e29]: Description *
          - textbox "Description *" [ref=e30]
        - generic [ref=e31]:
          - generic [ref=e32]: Genre *
          - textbox "Genre *" [ref=e33]:
            - /placeholder: Drama, Action, etc.
        - generic [ref=e34]:
          - generic [ref=e35]: Language *
          - textbox "Language *" [ref=e36]:
            - /placeholder: English, Hindi, etc.
        - generic [ref=e37]:
          - generic [ref=e38]: Director *
          - textbox "Director *" [ref=e39]
        - generic [ref=e40]:
          - generic [ref=e41]: Duration (minutes) *
          - spinbutton "Duration (minutes) *" [ref=e42]
        - generic [ref=e43]:
          - generic [ref=e44]: Rating *
          - combobox "Rating *" [ref=e45]:
            - option "Select Rating" [selected]
            - option "G"
            - option "PG"
            - option "PG-13"
            - option "R"
            - option "NC-17"
        - button "Add Movie" [ref=e46] [cursor=pointer]
    - generic [ref=e47]:
      - heading "📽️ Recently Added Movies" [level=2] [ref=e48]
      - generic [ref=e49]:
        - generic [ref=e50]:
          - heading "Bhool Bhulaiyaa 3" [level=3] [ref=e51]
          - paragraph [ref=e52]:
            - strong [ref=e53]: "Genre:"
            - text: Horror/Comedy
          - paragraph [ref=e54]:
            - strong [ref=e55]: "Director:"
            - text: Anees Bazmee
          - paragraph [ref=e56]:
            - strong [ref=e57]: "Duration:"
            - text: 152 mins
          - paragraph [ref=e58]:
            - strong [ref=e59]: "Rating:"
            - text: PG |
            - strong [ref=e60]: "Language:"
            - text: Hindi
        - generic [ref=e61]:
          - heading "Pathaan" [level=3] [ref=e62]
          - paragraph [ref=e63]:
            - strong [ref=e64]: "Genre:"
            - text: Action/Spy Thriller
          - paragraph [ref=e65]:
            - strong [ref=e66]: "Director:"
            - text: Siddharth Anand
          - paragraph [ref=e67]:
            - strong [ref=e68]: "Duration:"
            - text: 146 mins
          - paragraph [ref=e69]:
            - strong [ref=e70]: "Rating:"
            - text: PG13 |
            - strong [ref=e71]: "Language:"
            - text: Hindi
        - generic [ref=e72]:
          - heading "Jawan" [level=3] [ref=e73]
          - paragraph [ref=e74]:
            - strong [ref=e75]: "Genre:"
            - text: Action/Thriller
          - paragraph [ref=e76]:
            - strong [ref=e77]: "Director:"
            - text: Atlee
          - paragraph [ref=e78]:
            - strong [ref=e79]: "Duration:"
            - text: 169 mins
          - paragraph [ref=e80]:
            - strong [ref=e81]: "Rating:"
            - text: PG13 |
            - strong [ref=e82]: "Language:"
            - text: Hindi
        - generic [ref=e83]:
          - heading "Gadar 2" [level=3] [ref=e84]
          - paragraph [ref=e85]:
            - strong [ref=e86]: "Genre:"
            - text: Action/Drama
          - paragraph [ref=e87]:
            - strong [ref=e88]: "Director:"
            - text: Anil Sharma
          - paragraph [ref=e89]:
            - strong [ref=e90]: "Duration:"
            - text: 162 mins
          - paragraph [ref=e91]:
            - strong [ref=e92]: "Rating:"
            - text: PG |
            - strong [ref=e93]: "Language:"
            - text: Hindi
    - generic [ref=e94]:
      - heading "📊 Booking Statistics by User" [level=2] [ref=e95]
      - table [ref=e97]:
        - rowgroup [ref=e98]:
          - row "User Name Email Total Bookings Confirmed Pending Cancelled" [ref=e99]:
            - columnheader "User Name" [ref=e100]
            - columnheader "Email" [ref=e101]
            - columnheader "Total Bookings" [ref=e102]
            - columnheader "Confirmed" [ref=e103]
            - columnheader "Pending" [ref=e104]
            - columnheader "Cancelled" [ref=e105]
        - rowgroup [ref=e106]:
          - row "Admin User admin@example.com 0 0 0 0" [ref=e107]:
            - cell "Admin User" [ref=e108]
            - cell "admin@example.com" [ref=e109]
            - cell "0" [ref=e110]
            - cell "0" [ref=e111]
            - cell "0" [ref=e112]
            - cell "0" [ref=e113]
          - row "John Doe john@example.com 0 0 0 0" [ref=e114]:
            - cell "John Doe" [ref=e115]
            - cell "john@example.com" [ref=e116]
            - cell "0" [ref=e117]
            - cell "0" [ref=e118]
            - cell "0" [ref=e119]
            - cell "0" [ref=e120]
          - row "Jane Smith jane@example.com 0 0 0 0" [ref=e121]:
            - cell "Jane Smith" [ref=e122]
            - cell "jane@example.com" [ref=e123]
            - cell "0" [ref=e124]
            - cell "0" [ref=e125]
            - cell "0" [ref=e126]
            - cell "0" [ref=e127]
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
> 43  |       await expect(page.locator('h3')).toContainText(module);
      |                                        ^ Error: expect(locator).toContainText(expected) failed
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
```