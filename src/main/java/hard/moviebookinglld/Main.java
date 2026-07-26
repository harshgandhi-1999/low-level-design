package hard.moviebookinglld;


import hard.moviebookinglld.booking.Booking;
import hard.moviebookinglld.seat.Seat;
import hard.moviebookinglld.seat.SeatType;

import java.time.LocalDateTime;
import java.util.*;

public class Main {
    public static void main(String[] args) {
//        Requirements
//        1. The system should allow users to view the list of movies playing in different theaters.
//        2. Users should be able to select a movie, theater, and show timing to book tickets.
//        3. The system should display the seating arrangement of the selected show and allow users to choose seats.
//        4. Users should be able to make payments and confirm their booking.
//        5. The system should handle concurrent bookings and ensure seat availability is updated in real-time.
//        6. The system should support different types of seats (e.g., normal, premium) and pricing.
//        7. The system should allow theater administrators to add, update, and remove movies, shows, and seating arrangements.
//        8. The system should be scalable to handle a large number of concurrent users and bookings.

        MovieTicketBookingSystem bookingSystem = MovieTicketBookingSystem.getInstance();

        // Add movies
        Movie movie1 = new Movie("M1", "Movie 1", "Description 1", 120);
        Movie movie2 = new Movie("M2", "Movie 2", "Description 2", 135);
        bookingSystem.addMovie(movie1);
        bookingSystem.addMovie(movie2);

        // Add theaters
        Theater theater1 = new Theater("T1", "Theater 1", "Location 1", new ArrayList<>());
        Theater theater2 = new Theater("T2", "Theater 2", "Location 2", new ArrayList<>());
        bookingSystem.addTheater(theater1);
        bookingSystem.addTheater(theater2);

        // Add shows
        Show show1 = new Show("S1", movie1, theater1, LocalDateTime.now(), LocalDateTime.now().plusMinutes(movie1.getDurationInMinutes()), createSeats(10, 10));
        Show show2 = new Show("S2", movie2, theater2, LocalDateTime.now(), LocalDateTime.now().plusMinutes(movie2.getDurationInMinutes()), createSeats(8, 8));
        bookingSystem.addShow(show1);
        bookingSystem.addShow(show2);

//        Scanner scanner = new Scanner(System.in);
//
//        // Show list of movies for user to select
//        System.out.println("Select movies from the below list");
//        for(Movie movie : bookingSystem.getMovies()){
//            System.out.println(movie.getId() + " " +movie.getTitle());
//        }
//
//        scanner.next();
//
//
//
//
//        // Show list of theatre and the shows timings
//        for(Theater theater : bookingSystem.getTheaters()){
//
//        }



        // Book tickets
        User user = new User("U1", "John Doe", "john@example.com");
        List<Seat> selectedSeats = Arrays.asList(show1.getSeats().get("1-5"), show1.getSeats().get("1-6"));
        Booking booking = bookingSystem.bookTickets(user, show1, selectedSeats);
        if (booking != null) {
            System.out.println("Booking successful. Booking ID: " + booking.getId());
            bookingSystem.confirmBooking(booking.getId());
        } else {
            System.out.println("Booking failed. Seats not available.");
        }

        // Cancel booking
        bookingSystem.cancelBooking(booking.getId());
        System.out.println("Booking canceled. Booking ID: " + booking.getId());
    }

    private static Map<String, Seat> createSeats(int rows, int columns) {
        Map<String, Seat> seats = new HashMap<>();
        for (int row = 1; row <= rows; row++) {
            for (int col = 1; col <= columns; col++) {
                String seatId = row + "-" + col;
                SeatType seatType = (row <= 2) ? SeatType.PREMIUM : SeatType.NORMAL;
                double price = (seatType == SeatType.PREMIUM) ? 150.0 : 100.0;
                Seat seat = new Seat(seatId, row, col, seatType, price);
                seats.put(seatId, seat);
            }
        }
        return seats;
    }
}
