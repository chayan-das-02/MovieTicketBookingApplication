package com.movieticket.config;

import com.movieticket.entity.*;
import com.movieticket.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Initialize sample data for development and testing.
 * This only runs when 'dev' profile is active.
 */
@Configuration
@Profile("dev")
public class DataInitializer {

    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final TheaterRepository theaterRepository;
    private final ScreenRepository screenRepository;
    private final ShowRepository showRepository;
    private final SeatRepository seatRepository;

    @Autowired
    public DataInitializer(UserRepository userRepository, MovieRepository movieRepository,
                          TheaterRepository theaterRepository, ScreenRepository screenRepository,
                          ShowRepository showRepository, SeatRepository seatRepository) {
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
        this.theaterRepository = theaterRepository;
        this.screenRepository = screenRepository;
        this.showRepository = showRepository;
        this.seatRepository = seatRepository;
    }

    @Bean
    public CommandLineRunner initializeData() {
        return args -> {
            // Check if data already exists
            if (userRepository.count() > 0) {
                return;
            }

            // Create admin user
            User admin = new User();
            admin.setEmail("admin@example.com");
            admin.setPassword("admin123");
            admin.setFirstName("Admin");
            admin.setLastName("User");
            admin.setPhoneNumber("9999999999");
            admin.setRole(User.UserRole.ADMIN);
            admin.setCreatedAt(LocalDateTime.now());
            admin.setUpdatedAt(LocalDateTime.now());
            userRepository.save(admin);

            // Create sample users
            User user1 = new User();
            user1.setEmail("john@example.com");
            user1.setPassword("password123");
            user1.setFirstName("John");
            user1.setLastName("Doe");
            user1.setPhoneNumber("9876543210");
            user1.setRole(User.UserRole.USER);
            user1.setCreatedAt(LocalDateTime.now());
            user1.setUpdatedAt(LocalDateTime.now());
            userRepository.save(user1);

            User user2 = new User();
            user2.setEmail("jane@example.com");
            user2.setPassword("password123");
            user2.setFirstName("Jane");
            user2.setLastName("Smith");
            user2.setPhoneNumber("9876543211");
            user2.setRole(User.UserRole.USER);
            user2.setCreatedAt(LocalDateTime.now());
            user2.setUpdatedAt(LocalDateTime.now());
            userRepository.save(user2);

            // Create sample movies
            // Create Indian movies
            Movie movie1 = new Movie();
            movie1.setTitle("Gadar 2");
            movie1.setDescription("An Indian soldier returns to action to avenge his brothers fall and face his enemy for the ultimate battle.");
            movie1.setGenre("Action/Drama");
            movie1.setDurationMinutes(162);
            movie1.setLanguage("Hindi");
            movie1.setRating(Movie.RatingType.PG);
            movie1.setDirector("Anil Sharma");
            movie1.setReleaseDate(LocalDateTime.of(2023, 8, 11, 0, 0));
            movie1.setCreatedAt(LocalDateTime.now());
            movie1.setUpdatedAt(LocalDateTime.now());
            movieRepository.save(movie1);

            Movie movie2 = new Movie();
            movie2.setTitle("Jawan");
            movie2.setDescription("An ex-special forces officer takes on a powerful but arrogant man and seeks revenge using unconventional methods.");
            movie2.setGenre("Action/Thriller");
            movie2.setDurationMinutes(169);
            movie2.setLanguage("Hindi");
            movie2.setRating(Movie.RatingType.PG13);
            movie2.setDirector("Atlee");
            movie2.setReleaseDate(LocalDateTime.of(2023, 9, 7, 0, 0));
            movie2.setCreatedAt(LocalDateTime.now());
            movie2.setUpdatedAt(LocalDateTime.now());
            movieRepository.save(movie2);

            Movie movie3 = new Movie();
            movie3.setTitle("Pathaan");
            movie3.setDescription("An Indian intelligence officer on a secret mission goes undercover to stop a renegade spy from his past.");
            movie3.setGenre("Action/Spy Thriller");
            movie3.setDurationMinutes(146);
            movie3.setLanguage("Hindi");
            movie3.setRating(Movie.RatingType.PG13);
            movie3.setDirector("Siddharth Anand");
            movie3.setReleaseDate(LocalDateTime.of(2023, 1, 25, 0, 0));
            movie3.setCreatedAt(LocalDateTime.now());
            movie3.setUpdatedAt(LocalDateTime.now());
            movieRepository.save(movie3);

            Movie movie4 = new Movie();
            movie4.setTitle("Bhool Bhulaiyaa 3");
            movie4.setDescription("A psychologist helps a couple uncover secrets in their mansion with mysterious paranormal activities.");
            movie4.setGenre("Horror/Comedy");
            movie4.setDurationMinutes(152);
            movie4.setLanguage("Hindi");
            movie4.setRating(Movie.RatingType.PG);
            movie4.setDirector("Anees Bazmee");
            movie4.setReleaseDate(LocalDateTime.of(2024, 11, 1, 0, 0));
            movie4.setCreatedAt(LocalDateTime.now());
            movie4.setUpdatedAt(LocalDateTime.now());
            movieRepository.save(movie4);

            // Create Indian theaters
            Theater theater1 = new Theater();
            theater1.setName("PVR Cinemas - Delhi");
            theater1.setCity("Delhi");
            theater1.setAddress("SCO 43, Sector 9, Faridabad, Haryana 121003");
            theater1.setPhoneNumber("9111234567");
            theater1.setEmail("pvr.delhi@example.com");
            theater1.setTotalScreens(5);
            theater1.setCreatedAt(LocalDateTime.now());
            theater1.setUpdatedAt(LocalDateTime.now());
            theaterRepository.save(theater1);

            Theater theater2 = new Theater();
            theater2.setName("INOX - Mumbai");
            theater2.setCity("Mumbai");
            theater2.setAddress("Infiniti Mall, Malad West, Mumbai, Maharashtra 400064");
            theater2.setPhoneNumber("9887654321");
            theater2.setEmail("inox.mumbai@example.com");
            theater2.setTotalScreens(6);
            theater2.setCreatedAt(LocalDateTime.now());
            theater2.setUpdatedAt(LocalDateTime.now());
            theaterRepository.save(theater2);

            Theater theater3 = new Theater();
            theater3.setName("Cinepolis - Bangalore");
            theater3.setCity("Bangalore");
            theater3.setAddress("UB City, 24 Vittal Mallya Road, Bangalore, Karnataka 560001");
            theater3.setPhoneNumber("9776543210");
            theater3.setEmail("cinepolis.bangalore@example.com");
            theater3.setTotalScreens(4);
            theater3.setCreatedAt(LocalDateTime.now());
            theater3.setUpdatedAt(LocalDateTime.now());
            theaterRepository.save(theater3);

            Theater theater4 = new Theater();
            theater4.setName("Big Screen Movies - Hyderabad");
            theater4.setCity("Hyderabad");
            theater4.setAddress("Prasadz, 1-93/1A, Opp. Prasads IMAX, Ameerpet, Hyderabad, Telangana 500016");
            theater4.setPhoneNumber("9665543210");
            theater4.setEmail("bigscreen.hyderabad@example.com");
            theater4.setTotalScreens(5);
            theater4.setCreatedAt(LocalDateTime.now());
            theater4.setUpdatedAt(LocalDateTime.now());
            theaterRepository.save(theater4);

            // Add more Indian theaters
            Theater theater5 = new Theater();
            theater5.setName("Cinemed Cinemas - Kolkata");
            theater5.setCity("Kolkata");
            theater5.setAddress("AJC Bose Road, Near Rabindra Sarovar, Kolkata, West Bengal 700026");
            theater5.setPhoneNumber("9876543215");
            theater5.setEmail("cinemed.kolkata@example.com");
            theater5.setTotalScreens(3);
            theater5.setCreatedAt(LocalDateTime.now());
            theater5.setUpdatedAt(LocalDateTime.now());
            theaterRepository.save(theater5);

            Theater theater6 = new Theater();
            theater6.setName("Miraj Cinemas - Pune");
            theater6.setCity("Pune");
            theater6.setAddress("MG Road, Camp, Pune, Maharashtra 411001");
            theater6.setPhoneNumber("9876543216");
            theater6.setEmail("miraj.pune@example.com");
            theater6.setTotalScreens(4);
            theater6.setCreatedAt(LocalDateTime.now());
            theater6.setUpdatedAt(LocalDateTime.now());
            theaterRepository.save(theater6);

            Theater theater7 = new Theater();
            theater7.setName("Sathyam Cinemas - Chennai");
            theater7.setCity("Chennai");
            theater7.setAddress("100 F, Arcot Road, Vadapalani, Chennai, Tamil Nadu 600026");
            theater7.setPhoneNumber("9876543217");
            theater7.setEmail("sathyam.chennai@example.com");
            theater7.setTotalScreens(5);
            theater7.setCreatedAt(LocalDateTime.now());
            theater7.setUpdatedAt(LocalDateTime.now());
            theaterRepository.save(theater7);

            Theater theater8 = new Theater();
            theater8.setName("INOX - Jaipur");
            theater8.setCity("Jaipur");
            theater8.setAddress("Malviya Nagar, C-Square Mall, Jaipur, Rajasthan 302017");
            theater8.setPhoneNumber("9876543218");
            theater8.setEmail("inox.jaipur@example.com");
            theater8.setTotalScreens(4);
            theater8.setCreatedAt(LocalDateTime.now());
            theater8.setUpdatedAt(LocalDateTime.now());
            theaterRepository.save(theater8);

            Theater theater9 = new Theater();
            theater9.setName("PVR Cinemas - Ahmedabad");
            theater9.setCity("Ahmedabad");
            theater9.setAddress("Raheja Towers, Ahmedabad One, Satellite, Ahmedabad, Gujarat 380015");
            theater9.setPhoneNumber("9876543219");
            theater9.setEmail("pvr.ahmedabad@example.com");
            theater9.setTotalScreens(6);
            theater9.setCreatedAt(LocalDateTime.now());
            theater9.setUpdatedAt(LocalDateTime.now());
            theaterRepository.save(theater9);

            // Create screens for theater1
            Screen screen1 = new Screen();
            screen1.setTheater(theater1);
            screen1.setScreenName("Screen 1 - IMAX");
            screen1.setTotalSeats(150);
            screen1.setTotalRows(10);
            screen1.setSeatsPerRow(15);
            screen1.setScreenType(Screen.ScreenType.IMAX);
            screen1.setCreatedAt(LocalDateTime.now());
            screen1.setUpdatedAt(LocalDateTime.now());
            screenRepository.save(screen1);

            Screen screen2 = new Screen();
            screen2.setTheater(theater1);
            screen2.setScreenName("Screen 2 - Standard");
            screen2.setTotalSeats(100);
            screen2.setTotalRows(10);
            screen2.setSeatsPerRow(10);
            screen2.setScreenType(Screen.ScreenType.STANDARD);
            screen2.setCreatedAt(LocalDateTime.now());
            screen2.setUpdatedAt(LocalDateTime.now());
            screenRepository.save(screen2);

            // Create screens for theater2
            Screen screen3 = new Screen();
            screen3.setTheater(theater2);
            screen3.setScreenName("Screen 1 - 4DX");
            screen3.setTotalSeats(120);
            screen3.setTotalRows(8);
            screen3.setSeatsPerRow(15);
            screen3.setScreenType(Screen.ScreenType.STANDARD);
            screen3.setCreatedAt(LocalDateTime.now());
            screen3.setUpdatedAt(LocalDateTime.now());
            screenRepository.save(screen3);

            Screen screen4 = new Screen();
            screen4.setTheater(theater2);
            screen4.setScreenName("Screen 2 - Dolby");
            screen4.setTotalSeats(130);
            screen4.setTotalRows(10);
            screen4.setSeatsPerRow(13);
            screen4.setScreenType(Screen.ScreenType.STANDARD);
            screen4.setCreatedAt(LocalDateTime.now());
            screen4.setUpdatedAt(LocalDateTime.now());
            screenRepository.save(screen4);

            // Create screens for theater3
            Screen screen5 = new Screen();
            screen5.setTheater(theater3);
            screen5.setScreenName("Screen 1 - Premium");
            screen5.setTotalSeats(100);
            screen5.setTotalRows(10);
            screen5.setSeatsPerRow(10);
            screen5.setScreenType(Screen.ScreenType.STANDARD);
            screen5.setCreatedAt(LocalDateTime.now());
            screen5.setUpdatedAt(LocalDateTime.now());
            screenRepository.save(screen5);

            // Create screens for theater4
            Screen screen6 = new Screen();
            screen6.setTheater(theater4);
            screen6.setScreenName("Screen 1 - IMAX");
            screen6.setTotalSeats(160);
            screen6.setTotalRows(10);
            screen6.setSeatsPerRow(16);
            screen6.setScreenType(Screen.ScreenType.IMAX);
            screen6.setCreatedAt(LocalDateTime.now());
            screen6.setUpdatedAt(LocalDateTime.now());
            screenRepository.save(screen6);

            // Create screens for theater5
            Screen screen7 = new Screen();
            screen7.setTheater(theater5);
            screen7.setScreenName("Screen 1 - Standard");
            screen7.setTotalSeats(110);
            screen7.setTotalRows(11);
            screen7.setSeatsPerRow(10);
            screen7.setScreenType(Screen.ScreenType.STANDARD);
            screen7.setCreatedAt(LocalDateTime.now());
            screen7.setUpdatedAt(LocalDateTime.now());
            screenRepository.save(screen7);

            // Create screens for theater6
            Screen screen8 = new Screen();
            screen8.setTheater(theater6);
            screen8.setScreenName("Screen 1 - Premium");
            screen8.setTotalSeats(140);
            screen8.setTotalRows(10);
            screen8.setSeatsPerRow(14);
            screen8.setScreenType(Screen.ScreenType.STANDARD);
            screen8.setCreatedAt(LocalDateTime.now());
            screen8.setUpdatedAt(LocalDateTime.now());
            screenRepository.save(screen8);

            // Create screens for theater7
            Screen screen9 = new Screen();
            screen9.setTheater(theater7);
            screen9.setScreenName("Screen 1 - Dolby");
            screen9.setTotalSeats(150);
            screen9.setTotalRows(10);
            screen9.setSeatsPerRow(15);
            screen9.setScreenType(Screen.ScreenType.STANDARD);
            screen9.setCreatedAt(LocalDateTime.now());
            screen9.setUpdatedAt(LocalDateTime.now());
            screenRepository.save(screen9);

            // Create screens for theater8
            Screen screen10 = new Screen();
            screen10.setTheater(theater8);
            screen10.setScreenName("Screen 1 - IMAX");
            screen10.setTotalSeats(130);
            screen10.setTotalRows(10);
            screen10.setSeatsPerRow(13);
            screen10.setScreenType(Screen.ScreenType.IMAX);
            screen10.setCreatedAt(LocalDateTime.now());
            screen10.setUpdatedAt(LocalDateTime.now());
            screenRepository.save(screen10);

            // Create screens for theater9
            Screen screen11 = new Screen();
            screen11.setTheater(theater9);
            screen11.setScreenName("Screen 1 - Premium");
            screen11.setTotalSeats(140);
            screen11.setTotalRows(10);
            screen11.setSeatsPerRow(14);
            screen11.setScreenType(Screen.ScreenType.STANDARD);
            screen11.setCreatedAt(LocalDateTime.now());
            screen11.setUpdatedAt(LocalDateTime.now());
            screenRepository.save(screen11);

            // Create shows with different statuses (Indian prices in Rupees)
            // Upcoming shows (cannot be booked)
            Show show1 = new Show();
            show1.setMovie(movie1);
            show1.setScreen(screen1);
            show1.setStartTime(LocalDateTime.now().plusDays(3).withHour(18).withMinute(0).withSecond(0));
            show1.setEndTime(LocalDateTime.now().plusDays(3).withHour(20).withMinute(42).withSecond(0));
            show1.setTicketPrice(BigDecimal.valueOf(250)); // ₹250
            show1.setTotalSeatsAvailable(150);
            show1.setTotalSeatsBooked(0);
            show1.setStatus(Show.ShowStatus.UPCOMING);
            show1.setCreatedAt(LocalDateTime.now());
            show1.setUpdatedAt(LocalDateTime.now());
            showRepository.save(show1);

            // Running/Screening shows (can be booked)
            Show show2 = new Show();
            show2.setMovie(movie2);
            show2.setScreen(screen2);
            show2.setStartTime(LocalDateTime.now().withHour(14).withMinute(30).withSecond(0));
            show2.setEndTime(LocalDateTime.now().withHour(17).withMinute(19).withSecond(0));
            show2.setTicketPrice(BigDecimal.valueOf(200)); // ₹200
            show2.setTotalSeatsAvailable(100);
            show2.setTotalSeatsBooked(0);
            show2.setStatus(Show.ShowStatus.RUNNING);
            show2.setCreatedAt(LocalDateTime.now());
            show2.setUpdatedAt(LocalDateTime.now());
            showRepository.save(show2);

            // Running show for movie1 at Mumbai theater
            Show show3 = new Show();
            show3.setMovie(movie1);
            show3.setScreen(screen3);
            show3.setStartTime(LocalDateTime.now().withHour(18).withMinute(0).withSecond(0));
            show3.setEndTime(LocalDateTime.now().withHour(21).withMinute(2).withSecond(0));
            show3.setTicketPrice(BigDecimal.valueOf(300)); // ₹300
            show3.setTotalSeatsAvailable(120);
            show3.setTotalSeatsBooked(0);
            show3.setStatus(Show.ShowStatus.RUNNING);
            show3.setCreatedAt(LocalDateTime.now());
            show3.setUpdatedAt(LocalDateTime.now());
            showRepository.save(show3);

            // Upcoming show at Mumbai theater
            Show show4 = new Show();
            show4.setMovie(movie3);
            show4.setScreen(screen4);
            show4.setStartTime(LocalDateTime.now().plusDays(2).withHour(20).withMinute(0).withSecond(0));
            show4.setEndTime(LocalDateTime.now().plusDays(2).withHour(21).withMinute(46).withSecond(0));
            show4.setTicketPrice(BigDecimal.valueOf(280)); // ₹280
            show4.setTotalSeatsAvailable(130);
            show4.setTotalSeatsBooked(0);
            show4.setStatus(Show.ShowStatus.UPCOMING);
            show4.setCreatedAt(LocalDateTime.now());
            show4.setUpdatedAt(LocalDateTime.now());
            showRepository.save(show4);

            // Running show at Bangalore
            Show show5 = new Show();
            show5.setMovie(movie4);
            show5.setScreen(screen5);
            show5.setStartTime(LocalDateTime.now().withHour(19).withMinute(30).withSecond(0));
            show5.setEndTime(LocalDateTime.now().withHour(21).withMinute(22).withSecond(0));
            show5.setTicketPrice(BigDecimal.valueOf(220)); // ₹220
            show5.setTotalSeatsAvailable(100);
            show5.setTotalSeatsBooked(0);
            show5.setStatus(Show.ShowStatus.RUNNING);
            show5.setCreatedAt(LocalDateTime.now());
            show5.setUpdatedAt(LocalDateTime.now());
            showRepository.save(show5);

            // Running show at Hyderabad
            Show show6 = new Show();
            show6.setMovie(movie2);
            show6.setScreen(screen6);
            show6.setStartTime(LocalDateTime.now().withHour(15).withMinute(0).withSecond(0));
            show6.setEndTime(LocalDateTime.now().withHour(17).withMinute(49).withSecond(0));
            show6.setTicketPrice(BigDecimal.valueOf(290)); // ₹290
            show6.setTotalSeatsAvailable(160);
            show6.setTotalSeatsBooked(0);
            show6.setStatus(Show.ShowStatus.RUNNING);
            show6.setCreatedAt(LocalDateTime.now());
            show6.setUpdatedAt(LocalDateTime.now());
            showRepository.save(show6);

            // Running show at Kolkata
            Show show7 = new Show();
            show7.setMovie(movie3);
            show7.setScreen(screen7);
            show7.setStartTime(LocalDateTime.now().withHour(16).withMinute(30).withSecond(0));
            show7.setEndTime(LocalDateTime.now().withHour(18).withMinute(16).withSecond(0));
            show7.setTicketPrice(BigDecimal.valueOf(210)); // ₹210
            show7.setTotalSeatsAvailable(110);
            show7.setTotalSeatsBooked(0);
            show7.setStatus(Show.ShowStatus.RUNNING);
            show7.setCreatedAt(LocalDateTime.now());
            show7.setUpdatedAt(LocalDateTime.now());
            showRepository.save(show7);

            // Upcoming show at Kolkata
            Show show8 = new Show();
            show8.setMovie(movie4);
            show8.setScreen(screen7);
            show8.setStartTime(LocalDateTime.now().plusDays(1).withHour(19).withMinute(0).withSecond(0));
            show8.setEndTime(LocalDateTime.now().plusDays(1).withHour(20).withMinute(52).withSecond(0));
            show8.setTicketPrice(BigDecimal.valueOf(215)); // ₹215
            show8.setTotalSeatsAvailable(110);
            show8.setTotalSeatsBooked(0);
            show8.setStatus(Show.ShowStatus.UPCOMING);
            show8.setCreatedAt(LocalDateTime.now());
            show8.setUpdatedAt(LocalDateTime.now());
            showRepository.save(show8);

            // Running show at Pune
            Show show9 = new Show();
            show9.setMovie(movie1);
            show9.setScreen(screen8);
            show9.setStartTime(LocalDateTime.now().withHour(17).withMinute(0).withSecond(0));
            show9.setEndTime(LocalDateTime.now().withHour(19).withMinute(42).withSecond(0));
            show9.setTicketPrice(BigDecimal.valueOf(240)); // ₹240
            show9.setTotalSeatsAvailable(140);
            show9.setTotalSeatsBooked(0);
            show9.setStatus(Show.ShowStatus.RUNNING);
            show9.setCreatedAt(LocalDateTime.now());
            show9.setUpdatedAt(LocalDateTime.now());
            showRepository.save(show9);

            // Upcoming show at Pune
            Show show10 = new Show();
            show10.setMovie(movie2);
            show10.setScreen(screen8);
            show10.setStartTime(LocalDateTime.now().plusDays(3).withHour(20).withMinute(30).withSecond(0));
            show10.setEndTime(LocalDateTime.now().plusDays(3).withHour(23).withMinute(19).withSecond(0));
            show10.setTicketPrice(BigDecimal.valueOf(260)); // ₹260
            show10.setTotalSeatsAvailable(140);
            show10.setTotalSeatsBooked(0);
            show10.setStatus(Show.ShowStatus.UPCOMING);
            show10.setCreatedAt(LocalDateTime.now());
            show10.setUpdatedAt(LocalDateTime.now());
            showRepository.save(show10);

            // Running show at Chennai
            Show show11 = new Show();
            show11.setMovie(movie4);
            show11.setScreen(screen9);
            show11.setStartTime(LocalDateTime.now().withHour(18).withMinute(30).withSecond(0));
            show11.setEndTime(LocalDateTime.now().withHour(20).withMinute(22).withSecond(0));
            show11.setTicketPrice(BigDecimal.valueOf(230)); // ₹230
            show11.setTotalSeatsAvailable(150);
            show11.setTotalSeatsBooked(0);
            show11.setStatus(Show.ShowStatus.RUNNING);
            show11.setCreatedAt(LocalDateTime.now());
            show11.setUpdatedAt(LocalDateTime.now());
            showRepository.save(show11);

            // Running show at Jaipur
            Show show12 = new Show();
            show12.setMovie(movie3);
            show12.setScreen(screen10);
            show12.setStartTime(LocalDateTime.now().withHour(19).withMinute(0).withSecond(0));
            show12.setEndTime(LocalDateTime.now().withHour(20).withMinute(46).withSecond(0));
            show12.setTicketPrice(BigDecimal.valueOf(250)); // ₹250
            show12.setTotalSeatsAvailable(130);
            show12.setTotalSeatsBooked(0);
            show12.setStatus(Show.ShowStatus.RUNNING);
            show12.setCreatedAt(LocalDateTime.now());
            show12.setUpdatedAt(LocalDateTime.now());
            showRepository.save(show12);

            // Upcoming show at Jaipur
            Show show13 = new Show();
            show13.setMovie(movie1);
            show13.setScreen(screen10);
            show13.setStartTime(LocalDateTime.now().plusDays(2).withHour(21).withMinute(0).withSecond(0));
            show13.setEndTime(LocalDateTime.now().plusDays(2).withHour(23).withMinute(42).withSecond(0));
            show13.setTicketPrice(BigDecimal.valueOf(270)); // ₹270
            show13.setTotalSeatsAvailable(130);
            show13.setTotalSeatsBooked(0);
            show13.setStatus(Show.ShowStatus.UPCOMING);
            show13.setCreatedAt(LocalDateTime.now());
            show13.setUpdatedAt(LocalDateTime.now());
            showRepository.save(show13);

            // Running show at Ahmedabad
            Show show14 = new Show();
            show14.setMovie(movie2);
            show14.setScreen(screen11);
            show14.setStartTime(LocalDateTime.now().withHour(14).withMinute(0).withSecond(0));
            show14.setEndTime(LocalDateTime.now().withHour(16).withMinute(49).withSecond(0));
            show14.setTicketPrice(BigDecimal.valueOf(225)); // ₹225
            show14.setTotalSeatsAvailable(140);
            show14.setTotalSeatsBooked(0);
            show14.setStatus(Show.ShowStatus.RUNNING);
            show14.setCreatedAt(LocalDateTime.now());
            show14.setUpdatedAt(LocalDateTime.now());
            showRepository.save(show14);

            // Upcoming show at Ahmedabad
            Show show15 = new Show();
            show15.setMovie(movie4);
            show15.setScreen(screen11);
            show15.setStartTime(LocalDateTime.now().plusDays(1).withHour(20).withMinute(30).withSecond(0));
            show15.setEndTime(LocalDateTime.now().plusDays(1).withHour(22).withMinute(22).withSecond(0));
            show15.setTicketPrice(BigDecimal.valueOf(245)); // ₹245
            show15.setTotalSeatsAvailable(140);
            show15.setTotalSeatsBooked(0);
            show15.setStatus(Show.ShowStatus.UPCOMING);
            show15.setCreatedAt(LocalDateTime.now());
            show15.setUpdatedAt(LocalDateTime.now());
            showRepository.save(show15);

            // Create seats for all shows
            // Show 1: 150 seats in 10 rows of 15
            for (int row = 1; row <= 10; row++) {
                for (int col = 1; col <= 15; col++) {
                    Seat seat = new Seat();
                    seat.setShow(show1);
                    seat.setRowNumber(String.valueOf((char) (64 + row))); // A, B, C, etc.
                    seat.setColumnNumber(col);
                    seat.setSeatType(Seat.SeatType.STANDARD);
                    seat.setStatus(Seat.SeatStatus.AVAILABLE);
                    seat.setCreatedAt(LocalDateTime.now());
                    seat.setUpdatedAt(LocalDateTime.now());
                    seatRepository.save(seat);
                }
            }

            // Show 2: 100 seats in 10 rows of 10
            for (int row = 1; row <= 10; row++) {
                for (int col = 1; col <= 10; col++) {
                    Seat seat = new Seat();
                    seat.setShow(show2);
                    seat.setRowNumber(String.valueOf((char) (64 + row))); // A, B, C, etc.
                    seat.setColumnNumber(col);
                    seat.setSeatType(Seat.SeatType.STANDARD);
                    seat.setStatus(Seat.SeatStatus.AVAILABLE);
                    seat.setCreatedAt(LocalDateTime.now());
                    seat.setUpdatedAt(LocalDateTime.now());
                    seatRepository.save(seat);
                }
            }

            // Show 3: 120 seats in 8 rows of 15
            for (int row = 1; row <= 8; row++) {
                for (int col = 1; col <= 15; col++) {
                    Seat seat = new Seat();
                    seat.setShow(show3);
                    seat.setRowNumber(String.valueOf((char) (64 + row)));
                    seat.setColumnNumber(col);
                    seat.setSeatType(Seat.SeatType.STANDARD);
                    seat.setStatus(Seat.SeatStatus.AVAILABLE);
                    seat.setCreatedAt(LocalDateTime.now());
                    seat.setUpdatedAt(LocalDateTime.now());
                    seatRepository.save(seat);
                }
            }

            // Show 4: 130 seats in 10 rows of 13
            for (int row = 1; row <= 10; row++) {
                for (int col = 1; col <= 13; col++) {
                    Seat seat = new Seat();
                    seat.setShow(show4);
                    seat.setRowNumber(String.valueOf((char) (64 + row)));
                    seat.setColumnNumber(col);
                    seat.setSeatType(Seat.SeatType.STANDARD);
                    seat.setStatus(Seat.SeatStatus.AVAILABLE);
                    seat.setCreatedAt(LocalDateTime.now());
                    seat.setUpdatedAt(LocalDateTime.now());
                    seatRepository.save(seat);
                }
            }

            // Show 5: 100 seats in 10 rows of 10
            for (int row = 1; row <= 10; row++) {
                for (int col = 1; col <= 10; col++) {
                    Seat seat = new Seat();
                    seat.setShow(show5);
                    seat.setRowNumber(String.valueOf((char) (64 + row)));
                    seat.setColumnNumber(col);
                    seat.setSeatType(Seat.SeatType.STANDARD);
                    seat.setStatus(Seat.SeatStatus.AVAILABLE);
                    seat.setCreatedAt(LocalDateTime.now());
                    seat.setUpdatedAt(LocalDateTime.now());
                    seatRepository.save(seat);
                }
            }

            // Show 6: 160 seats in 10 rows of 16
            for (int row = 1; row <= 10; row++) {
                for (int col = 1; col <= 16; col++) {
                    Seat seat = new Seat();
                    seat.setShow(show6);
                    seat.setRowNumber(String.valueOf((char) (64 + row)));
                    seat.setColumnNumber(col);
                    seat.setSeatType(Seat.SeatType.STANDARD);
                    seat.setStatus(Seat.SeatStatus.AVAILABLE);
                    seat.setCreatedAt(LocalDateTime.now());
                    seat.setUpdatedAt(LocalDateTime.now());
                    seatRepository.save(seat);
                }
            }

            // Show 7: 110 seats in 11 rows of 10
            for (int row = 1; row <= 11; row++) {
                for (int col = 1; col <= 10; col++) {
                    Seat seat = new Seat();
                    seat.setShow(show7);
                    seat.setRowNumber(String.valueOf((char) (64 + row)));
                    seat.setColumnNumber(col);
                    seat.setSeatType(Seat.SeatType.STANDARD);
                    seat.setStatus(Seat.SeatStatus.AVAILABLE);
                    seat.setCreatedAt(LocalDateTime.now());
                    seat.setUpdatedAt(LocalDateTime.now());
                    seatRepository.save(seat);
                }
            }

            // Show 8: 110 seats in 11 rows of 10
            for (int row = 1; row <= 11; row++) {
                for (int col = 1; col <= 10; col++) {
                    Seat seat = new Seat();
                    seat.setShow(show8);
                    seat.setRowNumber(String.valueOf((char) (64 + row)));
                    seat.setColumnNumber(col);
                    seat.setSeatType(Seat.SeatType.STANDARD);
                    seat.setStatus(Seat.SeatStatus.AVAILABLE);
                    seat.setCreatedAt(LocalDateTime.now());
                    seat.setUpdatedAt(LocalDateTime.now());
                    seatRepository.save(seat);
                }
            }

            // Show 9: 140 seats in 10 rows of 14
            for (int row = 1; row <= 10; row++) {
                for (int col = 1; col <= 14; col++) {
                    Seat seat = new Seat();
                    seat.setShow(show9);
                    seat.setRowNumber(String.valueOf((char) (64 + row)));
                    seat.setColumnNumber(col);
                    seat.setSeatType(Seat.SeatType.STANDARD);
                    seat.setStatus(Seat.SeatStatus.AVAILABLE);
                    seat.setCreatedAt(LocalDateTime.now());
                    seat.setUpdatedAt(LocalDateTime.now());
                    seatRepository.save(seat);
                }
            }

            // Show 10: 140 seats in 10 rows of 14
            for (int row = 1; row <= 10; row++) {
                for (int col = 1; col <= 14; col++) {
                    Seat seat = new Seat();
                    seat.setShow(show10);
                    seat.setRowNumber(String.valueOf((char) (64 + row)));
                    seat.setColumnNumber(col);
                    seat.setSeatType(Seat.SeatType.STANDARD);
                    seat.setStatus(Seat.SeatStatus.AVAILABLE);
                    seat.setCreatedAt(LocalDateTime.now());
                    seat.setUpdatedAt(LocalDateTime.now());
                    seatRepository.save(seat);
                }
            }

            // Show 11: 150 seats in 10 rows of 15
            for (int row = 1; row <= 10; row++) {
                for (int col = 1; col <= 15; col++) {
                    Seat seat = new Seat();
                    seat.setShow(show11);
                    seat.setRowNumber(String.valueOf((char) (64 + row)));
                    seat.setColumnNumber(col);
                    seat.setSeatType(Seat.SeatType.STANDARD);
                    seat.setStatus(Seat.SeatStatus.AVAILABLE);
                    seat.setCreatedAt(LocalDateTime.now());
                    seat.setUpdatedAt(LocalDateTime.now());
                    seatRepository.save(seat);
                }
            }

            // Show 12: 130 seats in 10 rows of 13
            for (int row = 1; row <= 10; row++) {
                for (int col = 1; col <= 13; col++) {
                    Seat seat = new Seat();
                    seat.setShow(show12);
                    seat.setRowNumber(String.valueOf((char) (64 + row)));
                    seat.setColumnNumber(col);
                    seat.setSeatType(Seat.SeatType.STANDARD);
                    seat.setStatus(Seat.SeatStatus.AVAILABLE);
                    seat.setCreatedAt(LocalDateTime.now());
                    seat.setUpdatedAt(LocalDateTime.now());
                    seatRepository.save(seat);
                }
            }

            // Show 13: 130 seats in 10 rows of 13
            for (int row = 1; row <= 10; row++) {
                for (int col = 1; col <= 13; col++) {
                    Seat seat = new Seat();
                    seat.setShow(show13);
                    seat.setRowNumber(String.valueOf((char) (64 + row)));
                    seat.setColumnNumber(col);
                    seat.setSeatType(Seat.SeatType.STANDARD);
                    seat.setStatus(Seat.SeatStatus.AVAILABLE);
                    seat.setCreatedAt(LocalDateTime.now());
                    seat.setUpdatedAt(LocalDateTime.now());
                    seatRepository.save(seat);
                }
            }

            // Show 14: 140 seats in 10 rows of 14
            for (int row = 1; row <= 10; row++) {
                for (int col = 1; col <= 14; col++) {
                    Seat seat = new Seat();
                    seat.setShow(show14);
                    seat.setRowNumber(String.valueOf((char) (64 + row)));
                    seat.setColumnNumber(col);
                    seat.setSeatType(Seat.SeatType.STANDARD);
                    seat.setStatus(Seat.SeatStatus.AVAILABLE);
                    seat.setCreatedAt(LocalDateTime.now());
                    seat.setUpdatedAt(LocalDateTime.now());
                    seatRepository.save(seat);
                }
            }

            // Show 15: 140 seats in 10 rows of 14
            for (int row = 1; row <= 10; row++) {
                for (int col = 1; col <= 14; col++) {
                    Seat seat = new Seat();
                    seat.setShow(show15);
                    seat.setRowNumber(String.valueOf((char) (64 + row)));
                    seat.setColumnNumber(col);
                    seat.setSeatType(Seat.SeatType.STANDARD);
                    seat.setStatus(Seat.SeatStatus.AVAILABLE);
                    seat.setCreatedAt(LocalDateTime.now());
                    seat.setUpdatedAt(LocalDateTime.now());
                    seatRepository.save(seat);
                }
            }

            System.out.println("Sample data initialized successfully!");
        };
    }
}
