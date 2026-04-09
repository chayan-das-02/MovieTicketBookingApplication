import { defineConfig, devices } from '@playwright/test';

/**
 * Playwright configuration for Movie Ticket Admin Dashboard Tests
 * This configuration sets up automated testing for all admin management pages
 */
export default defineConfig({
  testDir: './',
  testMatch: '*.test.js',
  timeout: 30000,
  retries: 1,
  workers: 1,

  use: {
    baseURL: 'http://localhost:8080',
    screenshot: 'only-on-failure',
    video: 'retain-on-failure',
    trace: 'on-first-retry',
  },

  webServer: {
    // Uncomment if you want Playwright to start the server
    // command: 'mvn spring-boot:run',
    // port: 8080,
    // timeout: 120 * 1000,
    // reuseExistingServer: true,
  },

  projects: [
    {
      name: 'chromium',
      use: { ...devices['Desktop Chrome'] },
    },
    {
      name: 'firefox',
      use: { ...devices['Desktop Firefox'] },
    },
    {
      name: 'webkit',
      use: { ...devices['Desktop Safari'] },
    },
    {
      name: 'mobile-chrome',
      use: { ...devices['Pixel 5'] },
    },
  ],

  reporter: [
    ['html', { outputFolder: 'test-results/html' }],
    ['json', { outputFile: 'test-results/results.json' }],
    ['junit', { outputFile: 'test-results/junit.xml' }],
    ['list'],
  ],
});
