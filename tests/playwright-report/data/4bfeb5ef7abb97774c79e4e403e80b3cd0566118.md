# Instructions

- Following Playwright test failed.
- Explain why, be concise, respect Playwright best practices.
- Provide a snippet of code with the fix, if possible.

# Test info

- Name: admin-dashboard.test.js >> Admin Dashboard Management Pages >> Movie Management displays movies in grid
- Location: admin-dashboard.test.js:130:7

# Error details

```
Error: expect(locator).toContainText(expected) failed

Locator: locator('.movie-card h3')
Expected pattern: /./
Error: strict mode violation: locator('.movie-card h3') resolved to 4 elements:
    1) <h3>Gadar 2</h3> aka getByRole('heading', { name: 'Gadar' })
    2) <h3>Jawan</h3> aka getByRole('heading', { name: 'Jawan' })
    3) <h3>Pathaan</h3> aka getByRole('heading', { name: 'Pathaan' })
    4) <h3>Bhool Bhulaiyaa 3</h3> aka getByRole('heading', { name: 'Bhool Bhulaiyaa' })

Call log:
  - Expect "toContainText" with timeout 5000ms
  - waiting for locator('.movie-card h3')

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
      - heading "🎬 Movie Management" [level=1] [ref=e15]
      - link "← Back to Dashboard" [ref=e16] [cursor=pointer]:
        - /url: admin-dashboard.html
    - generic [ref=e17]:
      - generic [ref=e18]:
        - heading "➕ Add New Movie" [level=2] [ref=e19]
        - generic [ref=e20]:
          - generic [ref=e21]:
            - generic [ref=e22]: Title *
            - textbox "Title *" [ref=e23]
          - generic [ref=e24]:
            - generic [ref=e25]: Description *
            - textbox "Description *" [ref=e26]
          - generic [ref=e27]:
            - generic [ref=e28]: Genre *
            - textbox "Genre *" [ref=e29]:
              - /placeholder: Action, Drama, etc.
          - generic [ref=e30]:
            - generic [ref=e31]: Language *
            - textbox "Language *" [ref=e32]
          - generic [ref=e33]:
            - generic [ref=e34]: Director *
            - textbox "Director *" [ref=e35]
          - generic [ref=e36]:
            - generic [ref=e37]: Duration (minutes) *
            - spinbutton "Duration (minutes) *" [ref=e38]
          - generic [ref=e39]:
            - generic [ref=e40]: Rating *
            - combobox "Rating *" [ref=e41]:
              - option "Select Rating" [selected]
              - option "G"
              - option "PG"
              - option "PG-13"
              - option "R"
              - option "NC-17"
          - button "Save Movie" [ref=e42] [cursor=pointer]
          - button "Clear Form" [ref=e43] [cursor=pointer]
      - generic [ref=e44]:
        - heading "📽️ All Movies" [level=2] [ref=e45]
        - generic [ref=e46]:
          - textbox "Search movies..." [ref=e47]
          - button "Search" [ref=e48] [cursor=pointer]
        - generic [ref=e49]:
          - generic [ref=e50]:
            - heading "Gadar 2" [level=3] [ref=e51]
            - paragraph [ref=e52]:
              - strong [ref=e53]: "Genre:"
              - text: Action/Drama
            - paragraph [ref=e54]:
              - strong [ref=e55]: "Language:"
              - text: Hindi
            - generic [ref=e56]:
              - paragraph [ref=e57]:
                - strong [ref=e58]: "Director:"
                - text: Anil Sharma
              - paragraph [ref=e59]:
                - strong [ref=e60]: "Duration:"
                - text: 162 mins
              - paragraph [ref=e61]:
                - strong [ref=e62]: "Rating:"
                - text: PG
            - generic [ref=e63]:
              - button "Edit" [ref=e64] [cursor=pointer]
              - button "Delete" [ref=e65] [cursor=pointer]
          - generic [ref=e66]:
            - heading "Jawan" [level=3] [ref=e67]
            - paragraph [ref=e68]:
              - strong [ref=e69]: "Genre:"
              - text: Action/Thriller
            - paragraph [ref=e70]:
              - strong [ref=e71]: "Language:"
              - text: Hindi
            - generic [ref=e72]:
              - paragraph [ref=e73]:
                - strong [ref=e74]: "Director:"
                - text: Atlee
              - paragraph [ref=e75]:
                - strong [ref=e76]: "Duration:"
                - text: 169 mins
              - paragraph [ref=e77]:
                - strong [ref=e78]: "Rating:"
                - text: PG13
            - generic [ref=e79]:
              - button "Edit" [ref=e80] [cursor=pointer]
              - button "Delete" [ref=e81] [cursor=pointer]
          - generic [ref=e82]:
            - heading "Pathaan" [level=3] [ref=e83]
            - paragraph [ref=e84]:
              - strong [ref=e85]: "Genre:"
              - text: Action/Spy Thriller
            - paragraph [ref=e86]:
              - strong [ref=e87]: "Language:"
              - text: Hindi
            - generic [ref=e88]:
              - paragraph [ref=e89]:
                - strong [ref=e90]: "Director:"
                - text: Siddharth Anand
              - paragraph [ref=e91]:
                - strong [ref=e92]: "Duration:"
                - text: 146 mins
              - paragraph [ref=e93]:
                - strong [ref=e94]: "Rating:"
                - text: PG13
            - generic [ref=e95]:
              - button "Edit" [ref=e96] [cursor=pointer]
              - button "Delete" [ref=e97] [cursor=pointer]
          - generic [ref=e98]:
            - heading "Bhool Bhulaiyaa 3" [level=3] [ref=e99]
            - paragraph [ref=e100]:
              - strong [ref=e101]: "Genre:"
              - text: Horror/Comedy
            - paragraph [ref=e102]:
              - strong [ref=e103]: "Language:"
              - text: Hindi
            - generic [ref=e104]:
              - paragraph [ref=e105]:
                - strong [ref=e106]: "Director:"
                - text: Anees Bazmee
              - paragraph [ref=e107]:
                - strong [ref=e108]: "Duration:"
                - text: 152 mins
              - paragraph [ref=e109]:
                - strong [ref=e110]: "Rating:"
                - text: PG
            - generic [ref=e111]:
              - button "Edit" [ref=e112] [cursor=pointer]
              - button "Delete" [ref=e113] [cursor=pointer]
```

# Test source

```ts
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
> 141 |       await expect(page.locator('.movie-card h3')).toContainText(/./);
      |                                                    ^ Error: expect(locator).toContainText(expected) failed
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
  172 |     await page.goto(`${BASE_URL}/theater-management.html`);
  173 |     
  174 |     // Check form fields
  175 |     await expect(page.locator('input[name="name"]')).toBeVisible();
  176 |     await expect(page.locator('input[name="city"]')).toBeVisible();
  177 |     await expect(page.locator('textarea[name="address"]')).toBeVisible();
  178 |     await expect(page.locator('input[name="phone"]')).toBeVisible();
  179 |     await expect(page.locator('input[name="email"]')).toBeVisible();
  180 |   });
  181 | 
  182 |   // ============ SCREEN MANAGEMENT TESTS ============
  183 |   test('Screen Management page should load', async ({ page }) => {
  184 |     await page.goto(`${BASE_URL}/screen-management.html`);
  185 |     
  186 |     await expect(page.locator('h1')).toContainText('Screen Management');
  187 |     await expect(page.locator('text=Add New Screen')).toBeVisible();
  188 |     await expect(page.locator('text=All Screens')).toBeVisible();
  189 |     await expect(page.locator('table')).toBeVisible();
  190 |   });
  191 | 
  192 |   test('Screen Management form has all required fields', async ({ page }) => {
  193 |     await page.goto(`${BASE_URL}/screen-management.html`);
  194 |     
  195 |     // Check form fields
  196 |     await expect(page.locator('input[name="screenNumber"]')).toBeVisible();
  197 |     await expect(page.locator('select[name="theater"]')).toBeVisible();
  198 |     await expect(page.locator('select[name="screenType"]')).toBeVisible();
  199 |     await expect(page.locator('input[name="totalSeats"]')).toBeVisible();
  200 |   });
  201 | 
  202 |   // ============ SHOW MANAGEMENT TESTS ============
  203 |   test('Show Management page should load', async ({ page }) => {
  204 |     await page.goto(`${BASE_URL}/show-management.html`);
  205 |     
  206 |     await expect(page.locator('h1')).toContainText('Show Management');
  207 |     await expect(page.locator('text=Add New Show')).toBeVisible();
  208 |     await expect(page.locator('text=All Shows')).toBeVisible();
  209 |     await expect(page.locator('table')).toBeVisible();
  210 |   });
  211 | 
  212 |   test('Show Management form has all required fields', async ({ page }) => {
  213 |     await page.goto(`${BASE_URL}/show-management.html`);
  214 |     
  215 |     // Check form fields
  216 |     await expect(page.locator('select[name="movie"]')).toBeVisible();
  217 |     await expect(page.locator('select[name="screen"]')).toBeVisible();
  218 |     await expect(page.locator('input[name="showTime"]')).toBeVisible();
  219 |     await expect(page.locator('input[name="ticketPrice"]')).toBeVisible();
  220 |   });
  221 | 
  222 |   // ============ SEAT MANAGEMENT TESTS ============
  223 |   test('Seat Management page should load', async ({ page }) => {
  224 |     await page.goto(`${BASE_URL}/seat-management.html`);
  225 |     
  226 |     await expect(page.locator('h1')).toContainText('Seat Management');
  227 |     await expect(page.locator('text=Filter Seats')).toBeVisible();
  228 |     await expect(page.locator('select[name="showFilter"]')).toBeVisible();
  229 |   });
  230 | 
  231 |   // ============ BOOKING MANAGEMENT TESTS ============
  232 |   test('Booking Management page should load', async ({ page }) => {
  233 |     await page.goto(`${BASE_URL}/booking-management.html`);
  234 |     
  235 |     await expect(page.locator('h1')).toContainText('Booking Management');
  236 |     await expect(page.locator('text=Filter Bookings')).toBeVisible();
  237 |     await expect(page.locator('select#statusFilter')).toBeVisible();
  238 |     await expect(page.locator('table')).toBeVisible();
  239 |   });
  240 | 
  241 |   test('Booking Management table has all columns', async ({ page }) => {
```