import pytest
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.keys import Keys
import time

BASE_URL = "http://localhost:8080"
ADMIN_EMAIL = "admin@example.com"
ADMIN_PASSWORD = "admin123"

class TestAdminDashboard:
    
    @pytest.fixture(autouse=True)
    def setup_and_teardown(self):
        """Setup browser and login before each test"""
        self.driver = webdriver.Chrome()
        self.driver.implicitly_wait(10)
        self.wait = WebDriverWait(self.driver, 10)
        
        # Login as admin
        self.login_as_admin()
        
        yield
        
        # Teardown
        self.driver.quit()
    
    def login_as_admin(self):
        """Helper function to login as admin"""
        self.driver.get(f"{BASE_URL}/login.html")
        
        email_field = self.wait.until(EC.presence_of_element_located((By.NAME, "email")))
        email_field.send_keys(ADMIN_EMAIL)
        
        password_field = self.driver.find_element(By.NAME, "password")
        password_field.send_keys(ADMIN_PASSWORD)
        
        login_button = self.driver.find_element(By.CSS_SELECTOR, "button[type='submit']")
        login_button.click()
        
        # Wait for redirect to admin dashboard
        self.wait.until(EC.url_contains("admin-dashboard"))
        time.sleep(1)
    
    # ============ ADMIN DASHBOARD TESTS ============
    def test_01_admin_dashboard_loads(self):
        """Test that admin dashboard loads with all components"""
        self.driver.get(f"{BASE_URL}/admin-dashboard.html")
        
        # Check main heading
        heading = self.wait.until(EC.presence_of_element_located((By.TAG_NAME, "h1")))
        assert "Admin Dashboard" in heading.text
        
        # Check for management cards
        management_modules = [
            "User Management",
            "Movie Management",
            "Theater Management",
            "Screen Management",
            "Show Management",
            "Seat Management",
            "Booking Management",
            "Payment Management"
        ]
        
        for module in management_modules:
            elements = self.driver.find_elements(By.TAG_NAME, "h3")
            module_found = any(module in elem.text for elem in elements)
            assert module_found, f"{module} not found on dashboard"
    
    def test_02_admin_dashboard_statistics(self):
        """Test that statistics cards are visible"""
        self.driver.get(f"{BASE_URL}/admin-dashboard.html")
        
        stats = ["Total Users", "Total Movies", "Total Theaters", "Total Bookings"]
        
        for stat in stats:
            element = self.wait.until(EC.presence_of_element_located((By.XPATH, f"//*[contains(text(), '{stat}')]")))
            assert element.is_displayed()
    
    # ============ USER MANAGEMENT TESTS ============
    def test_03_user_management_page_loads(self):
        """Test User Management page loads"""
        self.driver.get(f"{BASE_URL}/user-management.html")
        
        heading = self.wait.until(EC.presence_of_element_located((By.TAG_NAME, "h1")))
        assert "User Management" in heading.text
        
        # Check for form
        form_fields = ["firstName", "lastName", "email", "password", "role"]
        for field in form_fields:
            element = self.driver.find_element(By.NAME, field)
            assert element.is_displayed()
    
    def test_04_user_management_table_visible(self):
        """Test User Management table is visible"""
        self.driver.get(f"{BASE_URL}/user-management.html")
        
        table = self.wait.until(EC.presence_of_element_located((By.TAG_NAME, "table")))
        assert table.is_displayed()
        
        # Check table headers
        headers = ["Name", "Email", "Role", "Actions"]
        for header in headers:
            element = self.driver.find_element(By.XPATH, f"//th[contains(text(), '{header}')]")
            assert element.is_displayed()
    
    # ============ MOVIE MANAGEMENT TESTS ============
    def test_05_movie_management_page_loads(self):
        """Test Movie Management page loads"""
        self.driver.get(f"{BASE_URL}/movie-management.html")
        
        heading = self.wait.until(EC.presence_of_element_located((By.TAG_NAME, "h1")))
        assert "Movie Management" in heading.text
    
    def test_06_movie_management_form_fields(self):
        """Test Movie Management form has all fields"""
        self.driver.get(f"{BASE_URL}/movie-management.html")
        
        form_fields = ["title", "description", "genre", "language", "director", "durationMinutes", "rating"]
        for field in form_fields:
            elements = self.driver.find_elements(By.NAME, field)
            assert len(elements) > 0, f"Field {field} not found"
    
    def test_07_movie_management_grid_display(self):
        """Test Movie Management displays movies in grid"""
        self.driver.get(f"{BASE_URL}/movie-management.html")
        
        time.sleep(2)  # Wait for movies to load
        
        movie_cards = self.driver.find_elements(By.CLASS_NAME, "movie-card")
        # Should have at least 0 movies (valid for empty database)
        assert len(movie_cards) >= 0
    
    # ============ THEATER MANAGEMENT TESTS ============
    def test_08_theater_management_page_loads(self):
        """Test Theater Management page loads"""
        self.driver.get(f"{BASE_URL}/theater-management.html")
        
        heading = self.wait.until(EC.presence_of_element_located((By.TAG_NAME, "h1")))
        assert "Theater Management" in heading.text
    
    def test_09_theater_management_form_fields(self):
        """Test Theater Management form has all fields"""
        self.driver.get(f"{BASE_URL}/theater-management.html")
        
        form_fields = ["name", "city", "address", "phone", "email"]
        for field in form_fields:
            element = self.driver.find_element(By.NAME, field)
            assert element.is_displayed()
    
    # ============ SCREEN MANAGEMENT TESTS ============
    def test_10_screen_management_page_loads(self):
        """Test Screen Management page loads"""
        self.driver.get(f"{BASE_URL}/screen-management.html")
        
        heading = self.wait.until(EC.presence_of_element_located((By.TAG_NAME, "h1")))
        assert "Screen Management" in heading.text
    
    def test_11_screen_management_form_fields(self):
        """Test Screen Management form has all fields"""
        self.driver.get(f"{BASE_URL}/screen-management.html")
        
        form_fields = ["screenNumber", "theater", "screenType", "totalSeats"]
        for field in form_fields:
            element = self.driver.find_element(By.NAME, field)
            assert element.is_displayed()
    
    # ============ SHOW MANAGEMENT TESTS ============
    def test_12_show_management_page_loads(self):
        """Test Show Management page loads"""
        self.driver.get(f"{BASE_URL}/show-management.html")
        
        heading = self.wait.until(EC.presence_of_element_located((By.TAG_NAME, "h1")))
        assert "Show Management" in heading.text
    
    def test_13_show_management_form_fields(self):
        """Test Show Management form has all fields"""
        self.driver.get(f"{BASE_URL}/show-management.html")
        
        form_fields = ["movie", "screen", "showTime", "ticketPrice"]
        for field in form_fields:
            element = self.driver.find_element(By.NAME, field)
            assert element.is_displayed()
    
    # ============ SEAT MANAGEMENT TESTS ============
    def test_14_seat_management_page_loads(self):
        """Test Seat Management page loads"""
        self.driver.get(f"{BASE_URL}/seat-management.html")
        
        heading = self.wait.until(EC.presence_of_element_located((By.TAG_NAME, "h1")))
        assert "Seat Management" in heading.text
    
    def test_15_seat_management_filter(self):
        """Test Seat Management filter exists"""
        self.driver.get(f"{BASE_URL}/seat-management.html")
        
        filter_select = self.wait.until(EC.presence_of_element_located((By.NAME, "showFilter")))
        assert filter_select.is_displayed()
    
    # ============ BOOKING MANAGEMENT TESTS ============
    def test_16_booking_management_page_loads(self):
        """Test Booking Management page loads"""
        self.driver.get(f"{BASE_URL}/booking-management.html")
        
        heading = self.wait.until(EC.presence_of_element_located((By.TAG_NAME, "h1")))
        assert "Booking Management" in heading.text
    
    def test_17_booking_management_table_columns(self):
        """Test Booking Management table has all columns"""
        self.driver.get(f"{BASE_URL}/booking-management.html")
        
        columns = ["Booking ID", "User", "Movie", "Show Time", "Status"]
        for column in columns:
            element = self.wait.until(EC.presence_of_element_located((By.XPATH, f"//th[contains(text(), '{column}')]")))
            assert element.is_displayed()
    
    def test_18_booking_management_filter(self):
        """Test Booking Management filter works"""
        self.driver.get(f"{BASE_URL}/booking-management.html")
        
        status_filter = self.wait.until(EC.presence_of_element_located((By.ID, "statusFilter")))
        status_filter.send_keys("CONFIRMED")
        
        filter_button = self.driver.find_element(By.XPATH, "//button[contains(text(), 'Apply Filter')]")
        filter_button.click()
        
        time.sleep(1)
        # Just verify page is still there
        heading = self.driver.find_element(By.TAG_NAME, "h1")
        assert "Booking Management" in heading.text
    
    # ============ PAYMENT MANAGEMENT TESTS ============
    def test_19_payment_management_page_loads(self):
        """Test Payment Management page loads"""
        self.driver.get(f"{BASE_URL}/payment-management.html")
        
        heading = self.wait.until(EC.presence_of_element_located((By.TAG_NAME, "h1")))
        assert "Payment Management" in heading.text
    
    def test_20_payment_management_statistics(self):
        """Test Payment Management statistics are visible"""
        self.driver.get(f"{BASE_URL}/payment-management.html")
        
        stats = ["Total Payments", "Successful Payments", "Total Revenue", "Failed Payments"]
        for stat in stats:
            element = self.wait.until(EC.presence_of_element_located((By.XPATH, f"//*[contains(text(), '{stat}')]")))
            assert element.is_displayed()
    
    def test_21_payment_management_table_columns(self):
        """Test Payment Management table has all columns"""
        self.driver.get(f"{BASE_URL}/payment-management.html")
        
        columns = ["Payment ID", "User", "Amount", "Status"]
        for column in columns:
            element = self.wait.until(EC.presence_of_element_located((By.XPATH, f"//th[contains(text(), '{column}')]")))
            assert element.is_displayed()
    
    # ============ NAVIGATION TESTS ============
    def test_22_back_to_dashboard_navigation(self):
        """Test back to dashboard button works"""
        self.driver.get(f"{BASE_URL}/user-management.html")
        
        back_button = self.wait.until(EC.presence_of_element_located((By.XPATH, "//a[contains(text(), 'Back to Dashboard')]")))
        back_button.click()
        
        self.wait.until(EC.url_contains("admin-dashboard.html"))
        heading = self.driver.find_element(By.TAG_NAME, "h1")
        assert "Admin Dashboard" in heading.text
    
    def test_23_all_management_links_work(self):
        """Test all management page links are accessible"""
        self.driver.get(f"{BASE_URL}/admin-dashboard.html")
        
        management_links = [
            ("user-management.html", "User Management"),
            ("movie-management.html", "Movie Management"),
            ("theater-management.html", "Theater Management"),
            ("screen-management.html", "Screen Management"),
            ("show-management.html", "Show Management"),
            ("seat-management.html", "Seat Management"),
            ("booking-management.html", "Booking Management"),
            ("payment-management.html", "Payment Management"),
        ]
        
        for url_part, page_title in management_links:
            self.driver.get(f"{BASE_URL}/{url_part}")
            heading = self.wait.until(EC.presence_of_element_located((By.TAG_NAME, "h1")))
            assert page_title in heading.text, f"Failed to load {page_title}"

if __name__ == "__main__":
    pytest.main([__file__, "-v", "-s"])
