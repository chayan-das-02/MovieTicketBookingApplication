/**
 * API Utility Class
 * Handles all API calls to the backend
 */
class APIClient {
    constructor(baseURL = 'http://localhost:8080/api/v1') {
        this.baseURL = baseURL;
    }

    /**
     * Get all movies
     */
    async getMovies() {
        return this.getRequest('/movies');
    }

    /**
     * Get movie by ID
     */
    async getMovieById(movieId) {
        return this.getRequest(`/movies/${movieId}`);
    }

    /**
     * Get all shows
     */
    async getShows() {
        return this.getRequest('/shows');
    }

    /**
     * Get show by ID
     */
    async getShowById(showId) {
        return this.getRequest(`/shows/${showId}`);
    }

    /**
     * Get shows by theater
     */
    async getShowsByTheater(theaterId) {
        return this.getRequest(`/shows/theater/${theaterId}`);
    }

    /**
     * Get all theaters
     */
    async getTheaters() {
        return this.getRequest('/theaters');
    }

    /**
     * Get theater by ID
     */
    async getTheaterById(theaterId) {
        return this.getRequest(`/theaters/${theaterId}`);
    }

    /**
     * Get all screens
     */
    async getScreens() {
        return this.getRequest('/screens');
    }

    /**
     * Get screens by theater
     */
    async getScreensByTheater(theaterId) {
        return this.getRequest(`/screens/theater/${theaterId}`);
    }

    /**
     * Get all users
     */
    async getUsers() {
        return this.getRequest('/users');
    }

    /**
     * Get user by ID
     */
    async getUserById(userId) {
        return this.getRequest(`/users/${userId}`);
    }

    /**
     * Get all seats for a show
     */
    async getSeatsForShow(showId) {
        return this.getRequest(`/seats/show/${showId}`);
    }

    /**
     * Get available seats for a show
     */
    async getAvailableSeatsForShow(showId) {
        return this.getRequest(`/seats/show/${showId}/available`);
    }

    /**
     * Create a booking
     */
    async createBooking(userId, bookingData) {
        return this.postRequest(`/bookings/users/${userId}`, bookingData);
    }

    /**
     * Get bookings for a user
     */
    async getUserBookings(userId) {
        return this.getRequest(`/bookings/users/${userId}`);
    }

    /**
     * Get booking by ID
     */
    async getBookingById(bookingId) {
        return this.getRequest(`/bookings/${bookingId}`);
    }

    /**
     * Confirm a booking
     */
    async confirmBooking(bookingId) {
        return this.patchRequest(`/bookings/${bookingId}/confirm`, {});
    }

    /**
     * Cancel a booking
     */
    async cancelBooking(bookingId) {
        return this.deleteRequest(`/bookings/${bookingId}`);
    }

    /**
     * Process payment
     */
    async processPayment(paymentData) {
        return this.postRequest('/payments/process', paymentData);
    }

    /**
     * Get payment by ID
     */
    async getPaymentById(paymentId) {
        return this.getRequest(`/payments/${paymentId}`);
    }

    /**
     * Get authentication headers from localStorage
     */
    getAuthHeaders() {
        const headers = {
            'Content-Type': 'application/json'
        };

        try {
            const user = JSON.parse(localStorage.getItem('user'));
            if (user) {
                headers['X-User-Id'] = user.userId;
                headers['X-User-Role'] = user.role;
            }
        } catch (error) {
            console.warn('Error reading user from localStorage:', error);
        }

        return headers;
    }

    /**
     * Helper method for GET requests
     */
    async getRequest(endpoint) {
        try {
            const response = await fetch(`${this.baseURL}${endpoint}`, {
                method: 'GET',
                headers: this.getAuthHeaders()
            });

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            return await response.json();
        } catch (error) {
            console.error(`Error fetching ${endpoint}:`, error);
            throw error;
        }
    }

    /**
     * Helper method for POST requests
     */
    async postRequest(endpoint, data) {
        try {
            const response = await fetch(`${this.baseURL}${endpoint}`, {
                method: 'POST',
                headers: this.getAuthHeaders(),
                body: JSON.stringify(data)
            });

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            return await response.json();
        } catch (error) {
            console.error(`Error posting to ${endpoint}:`, error);
            throw error;
        }
    }

    /**
     * Helper method for PATCH requests
     */
    async patchRequest(endpoint, data) {
        try {
            const response = await fetch(`${this.baseURL}${endpoint}`, {
                method: 'PATCH',
                headers: this.getAuthHeaders(),
                body: JSON.stringify(data)
            });

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            return await response.json();
        } catch (error) {
            console.error(`Error patching ${endpoint}:`, error);
            throw error;
        }
    }

    /**
     * Helper method for DELETE requests
     */
    async deleteRequest(endpoint) {
        try {
            const response = await fetch(`${this.baseURL}${endpoint}`, {
                method: 'DELETE',
                headers: this.getAuthHeaders()
            });

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            return await response.json();
        } catch (error) {
            console.error(`Error deleting ${endpoint}:`, error);
            throw error;
        }
    }
}

// Create a global API instance
const API = new APIClient();
