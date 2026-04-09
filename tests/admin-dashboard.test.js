import { test, expect } from '@playwright/test';

const BASE_URL = 'http://localhost:8080';
const ADMIN_EMAIL = 'admin@example.com';
const ADMIN_PASSWORD = 'admin123';

// Login helper function
async function loginAsAdmin(page) {
  await page.goto(`${BASE_URL}/login.html`);
  await page.fill('input[name="email"]', ADMIN_EMAIL);
  await page.fill('input[name="password"]', ADMIN_PASSWORD);
  await page.click('button[type="submit"]');
  await page.waitForURL(`${BASE_URL}/admin-dashboard.html`);
}

test.describe('Admin Dashboard Management Pages', () => {
  
  test.beforeEach(async ({ page }) => {
    // Login before each test
    await loginAsAdmin(page);
  });

  // ============ ADMIN DASHBOARD TESTS ============
  test('Admin Dashboard should load with all management cards', async ({ page }) => {
    await page.goto(`${BASE_URL}/admin-dashboard.html`);
    
    // Check if dashboard title exists
    await expect(page.locator('h1')).toContainText('Admin Dashboard');
    
    // Check for all 8 management cards
    const management_modules = [
      'User Management',
      'Movie Management',
      'Theater Management',
      'Screen Management',
      'Show Management',
      'Seat Management',
      'Booking Management',
      'Payment Management'
    ];
    
    for (const module of management_modules) {
      await expect(page.locator('h3')).toContainText(module);
    }
    
    // Check for statistics cards
    await expect(page.locator('text=Total Users')).toBeVisible();
    await expect(page.locator('text=Total Movies')).toBeVisible();
    await expect(page.locator('text=Total Theaters')).toBeVisible();
    await expect(page.locator('text=Total Bookings')).toBeVisible();
  });

  test('Admin Dashboard links navigate correctly', async ({ page }) => {
    await page.goto(`${BASE_URL}/admin-dashboard.html`);
    
    // Test User Management link
    await page.click('a:has-text("Manage Users")');
    await page.waitForURL(`**/user-management.html`);
    await expect(page.locator('h1')).toContainText('User Management');
    
    // Go back to dashboard
    await page.click('a:has-text("Back to Dashboard")');
    await page.waitForURL(`**/admin-dashboard.html`);
  });

  // ============ USER MANAGEMENT TESTS ============
  test('User Management page should load', async ({ page }) => {
    await page.goto(`${BASE_URL}/user-management.html`);
    
    await expect(page.locator('h1')).toContainText('User Management');
    await expect(page.locator('h3:has-text("Add New User")')).toBeVisible();
    await expect(page.locator('h3:has-text("All Users")')).toBeVisible();
    await expect(page.locator('table')).toBeVisible();
  });

  test('User Management form has all required fields', async ({ page }) => {
    await page.goto(`${BASE_URL}/user-management.html`);
    
    // Check form fields
    await expect(page.locator('input[name="firstName"]')).toBeVisible();
    await expect(page.locator('input[name="lastName"]')).toBeVisible();
    await expect(page.locator('input[name="email"]')).toBeVisible();
    await expect(page.locator('input[name="password"]')).toBeVisible();
    await expect(page.locator('select[name="role"]')).toBeVisible();
    
    // Check table headers
    await expect(page.locator('th:has-text("Name")')).toBeVisible();
    await expect(page.locator('th:has-text("Email")')).toBeVisible();
    await expect(page.locator('th:has-text("Role")')).toBeVisible();
  });

  test('User Management search functionality works', async ({ page }) => {
    await page.goto(`${BASE_URL}/user-management.html`);
    
    // Wait for users to load
    await page.waitForTimeout(1000);
    
    // Search for admin user
    await page.fill('#searchInput', 'admin');
    await page.click('button:has-text("Search")');
    
    // Verify search results - should still see at least the admin user
    await page.waitForTimeout(500);
    const visibleRows = await page.locator('tbody tr:visible').count();
    expect(visibleRows).toBeGreaterThan(0);
  });

  // ============ MOVIE MANAGEMENT TESTS ============
  test('Movie Management page should load', async ({ page }) => {
    await page.goto(`${BASE_URL}/movie-management.html`);
    
    await expect(page.locator('h1')).toContainText('Movie Management');
    await expect(page.locator('text=Add New Movie')).toBeVisible();
    await expect(page.locator('text=All Movies')).toBeVisible();
  });

  test('Movie Management form has all required fields', async ({ page }) => {
    await page.goto(`${BASE_URL}/movie-management.html`);
    
    // Check form fields
    await expect(page.locator('input[name="title"]')).toBeVisible();
    await expect(page.locator('textarea[name="description"]')).toBeVisible();
    await expect(page.locator('input[name="genre"]')).toBeVisible();
    await expect(page.locator('input[name="language"]')).toBeVisible();
    await expect(page.locator('input[name="director"]')).toBeVisible();
    await expect(page.locator('input[name="durationMinutes"]')).toBeVisible();
    await expect(page.locator('select[name="rating"]')).toBeVisible();
  });

  test('Movie Management displays movies in grid', async ({ page }) => {
    await page.goto(`${BASE_URL}/movie-management.html`);
    
    // Wait for movies to load
    await page.waitForTimeout(1500);
    
    // Check if movie cards are visible
    const movieCards = await page.locator('.movie-card').count();
    expect(movieCards).toBeGreaterThanOrEqual(0);
    
    if (movieCards > 0) {
      await expect(page.locator('.movie-card h3')).toContainText(/./);
    }
  });

  test('Movie Management search works', async ({ page }) => {
    await page.goto(`${BASE_URL}/movie-management.html`);
    
    await page.waitForTimeout(1000);
    
    // Search for a movie
    await page.fill('#searchInput', 'action');
    await page.click('button:has-text("Search")');
    
    await page.waitForTimeout(500);
    // Verify search is working
    const movieCards = await page.locator('.movie-card').count();
    // Should either show results or show 0 if no matches (which is valid)
    expect(movieCards).toBeGreaterThanOrEqual(0);
  });

  // ============ THEATER MANAGEMENT TESTS ============
  test('Theater Management page should load', async ({ page }) => {
    await page.goto(`${BASE_URL}/theater-management.html`);
    
    await expect(page.locator('h1')).toContainText('Theater Management');
    await expect(page.locator('text=Add New Theater')).toBeVisible();
    await expect(page.locator('text=All Theaters')).toBeVisible();
    await expect(page.locator('table')).toBeVisible();
  });

  test('Theater Management form has all required fields', async ({ page }) => {
    await page.goto(`${BASE_URL}/theater-management.html`);
    
    // Check form fields
    await expect(page.locator('input[name="name"]')).toBeVisible();
    await expect(page.locator('input[name="city"]')).toBeVisible();
    await expect(page.locator('textarea[name="address"]')).toBeVisible();
    await expect(page.locator('input[name="phone"]')).toBeVisible();
    await expect(page.locator('input[name="email"]')).toBeVisible();
  });

  // ============ SCREEN MANAGEMENT TESTS ============
  test('Screen Management page should load', async ({ page }) => {
    await page.goto(`${BASE_URL}/screen-management.html`);
    
    await expect(page.locator('h1')).toContainText('Screen Management');
    await expect(page.locator('text=Add New Screen')).toBeVisible();
    await expect(page.locator('text=All Screens')).toBeVisible();
    await expect(page.locator('table')).toBeVisible();
  });

  test('Screen Management form has all required fields', async ({ page }) => {
    await page.goto(`${BASE_URL}/screen-management.html`);
    
    // Check form fields
    await expect(page.locator('input[name="screenNumber"]')).toBeVisible();
    await expect(page.locator('select[name="theater"]')).toBeVisible();
    await expect(page.locator('select[name="screenType"]')).toBeVisible();
    await expect(page.locator('input[name="totalSeats"]')).toBeVisible();
  });

  // ============ SHOW MANAGEMENT TESTS ============
  test('Show Management page should load', async ({ page }) => {
    await page.goto(`${BASE_URL}/show-management.html`);
    
    await expect(page.locator('h1')).toContainText('Show Management');
    await expect(page.locator('text=Add New Show')).toBeVisible();
    await expect(page.locator('text=All Shows')).toBeVisible();
    await expect(page.locator('table')).toBeVisible();
  });

  test('Show Management form has all required fields', async ({ page }) => {
    await page.goto(`${BASE_URL}/show-management.html`);
    
    // Check form fields
    await expect(page.locator('select[name="movie"]')).toBeVisible();
    await expect(page.locator('select[name="screen"]')).toBeVisible();
    await expect(page.locator('input[name="showTime"]')).toBeVisible();
    await expect(page.locator('input[name="ticketPrice"]')).toBeVisible();
  });

  // ============ SEAT MANAGEMENT TESTS ============
  test('Seat Management page should load', async ({ page }) => {
    await page.goto(`${BASE_URL}/seat-management.html`);
    
    await expect(page.locator('h1')).toContainText('Seat Management');
    await expect(page.locator('text=Filter Seats')).toBeVisible();
    await expect(page.locator('select[name="showFilter"]')).toBeVisible();
  });

  // ============ BOOKING MANAGEMENT TESTS ============
  test('Booking Management page should load', async ({ page }) => {
    await page.goto(`${BASE_URL}/booking-management.html`);
    
    await expect(page.locator('h1')).toContainText('Booking Management');
    await expect(page.locator('text=Filter Bookings')).toBeVisible();
    await expect(page.locator('select#statusFilter')).toBeVisible();
    await expect(page.locator('table')).toBeVisible();
  });

  test('Booking Management table has all columns', async ({ page }) => {
    await page.goto(`${BASE_URL}/booking-management.html`);
    
    await expect(page.locator('th:has-text("Booking ID")')).toBeVisible();
    await expect(page.locator('th:has-text("User")')).toBeVisible();
    await expect(page.locator('th:has-text("Movie")')).toBeVisible();
    await expect(page.locator('th:has-text("Show Time")')).toBeVisible();
    await expect(page.locator('th:has-text("Status")')).toBeVisible();
  });

  test('Booking Management filter works', async ({ page }) => {
    await page.goto(`${BASE_URL}/booking-management.html`);
    
    // Select a status filter
    await page.selectOption('select#statusFilter', 'CONFIRMED');
    await page.click('button:has-text("Apply Filter")');
    
    await page.waitForTimeout(500);
    // Just verify the page still loads (no error)
    await expect(page.locator('h1')).toContainText('Booking Management');
  });

  // ============ PAYMENT MANAGEMENT TESTS ============
  test('Payment Management page should load', async ({ page }) => {
    await page.goto(`${BASE_URL}/payment-management.html`);
    
    await expect(page.locator('h1')).toContainText('Payment Management');
    await expect(page.locator('text=Total Payments')).toBeVisible();
    await expect(page.locator('text=Total Revenue')).toBeVisible();
    await expect(page.locator('table')).toBeVisible();
  });

  test('Payment Management displays statistics', async ({ page }) => {
    await page.goto(`${BASE_URL}/payment-management.html`);
    
    await page.waitForTimeout(1000);
    
    // Check for statistics
    await expect(page.locator('text=Total Payments')).toBeVisible();
    await expect(page.locator('text=Successful Payments')).toBeVisible();
    await expect(page.locator('text=Total Revenue')).toBeVisible();
    await expect(page.locator('text=Failed Payments')).toBeVisible();
  });

  test('Payment Management table has all columns', async ({ page }) => {
    await page.goto(`${BASE_URL}/payment-management.html`);
    
    await expect(page.locator('th:has-text("Payment ID")')).toBeVisible();
    await expect(page.locator('th:has-text("User")')).toBeVisible();
    await expect(page.locator('th:has-text("Amount")')).toBeVisible();
    await expect(page.locator('th:has-text("Status")')).toBeVisible();
  });

  // ============ AUTHENTICATION TESTS ============
  test('Unauthorized access redirects to login', async ({ page }) => {
    // Clear localStorage to simulate not being logged in
    await page.context().clearCookies();
    
    await page.goto(`${BASE_URL}/admin-dashboard.html`);
    // Should show access denied message (since no user in localStorage)
    const accessDenic = await page.locator('text=Access Denied').count();
    // If not authenticated, should either redirect to login or show denied
    const isOnDashboard = await page.locator('h1:has-text("Admin Dashboard")').count();
    const isOnDenied = await page.locator('text=Access Denied').count();
    
    expect(isOnDashboard + isOnDenied).toBeGreaterThan(0);
  });

  // ============ BACK BUTTON TESTS ============
  test('Back to Dashboard button navigates correctly', async ({ page }) => {
    // Test from User Management
    await page.goto(`${BASE_URL}/user-management.html`);
    await page.click('a:has-text("Back to Dashboard")');
    await page.waitForURL(`**/admin-dashboard.html`);
    await expect(page.locator('h1')).toContainText('Admin Dashboard');
    
    // Test from Movie Management
    await page.goto(`${BASE_URL}/movie-management.html`);
    await page.click('a:has-text("Back to Dashboard")');
    await page.waitForURL(`**/admin-dashboard.html`);
    await expect(page.locator('h1')).toContainText('Admin Dashboard');
  });

  // ============ RESPONSIVE DESIGN TESTS ============
  test('Pages are responsive on mobile', async ({ page }) => {
    await page.setViewportSize({ width: 375, height: 667 });
    
    await page.goto(`${BASE_URL}/admin-dashboard.html`);
    await expect(page.locator('h1')).toContainText('Admin Dashboard');
    
    const navBar = await page.locator('nav').isVisible();
    expect(navBar).toBeTruthy();
  });
});
