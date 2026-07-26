package hard.moviebookinglld;

import hard.moviebookinglld.booking.Booking;
import hard.moviebookinglld.booking.BookingStatus;
import hard.moviebookinglld.seat.Seat;
import hard.moviebookinglld.seat.SeatStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class MovieTicketBookingSystem {
    private static MovieTicketBookingSystem instance = null;
    private final List<Movie> movies;
    private final List<Theater> theaters;
    private final Map<String, Show> shows;
    private final Map<String, Booking> bookings;

    private static final String BOOKING_ID_PREFIX = "BKG_";
    private static final AtomicLong bookingCounter = new AtomicLong(0);

    private MovieTicketBookingSystem(){
        movies = new ArrayList<>();
        theaters = new ArrayList<>();
        shows = new ConcurrentHashMap<>();
        bookings = new ConcurrentHashMap<>();
    }

    public static synchronized MovieTicketBookingSystem getInstance(){
        if(instance == null){
            return new MovieTicketBookingSystem();
        }

        return instance;
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public void addTheater(Theater theater) {
        theaters.add(theater);
    }

    public void addShow(Show show) {
        shows.put(show.getId(), show);
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public List<Theater> getTheaters() {
        return theaters;
    }

    public Show getShow(String showId) {
        return shows.get(showId);
    }

    public synchronized Booking bookTickets(User user, Show show, List<Seat> selectedSeats){
        if(areSeatsAvailable(show,selectedSeats)){
            markSeatsAsBooked(show,selectedSeats);
            double price = calculateTotalPrice(selectedSeats);
            String bookingId = generateBookingId();
            Booking booking = new Booking(bookingId, user, show, selectedSeats, price, BookingStatus.PENDING);
            bookings.put(bookingId, booking);
            return booking;
        }
        System.out.println("Seats are not available");
        return null;
    }

    public synchronized void confirmBooking(String bookingId) {
        Booking booking = bookings.get(bookingId);
        if (booking != null && booking.getStatus() == BookingStatus.PENDING) {

            // Process payment and send confirmation
            // ...

            booking.setStatus(BookingStatus.CONFIRMED);
        }
    }

    public synchronized void cancelBooking(String bookingId) {
        Booking booking = bookings.get(bookingId);
        if (booking != null && booking.getStatus() != BookingStatus.CANCELLED) {
            booking.setStatus(BookingStatus.CANCELLED);
            markSeatsAsAvailable(booking.getShow(), booking.getSeats());
            // Process refund and send cancellation notification
            // ...
        }
    }

    private boolean areSeatsAvailable(Show show, List<Seat> selectedSeats){
        for(Seat seat : selectedSeats){
            Seat showSeat = show.getSeats().get(seat.getId());

            if(showSeat==null || !showSeat.getSeatStatus().equals(SeatStatus.AVAILABLE)){
                return false;
            }
        }

        return true;
    }

    private void markSeatsAsBooked(Show show, List<Seat> selectedSeats){
        for(Seat seat : selectedSeats){
            Seat showSeat = show.getSeats().get(seat.getId());
            showSeat.setSeatStatus(SeatStatus.BOOKED);
        }
    }

    private double calculateTotalPrice(List<Seat> selectedSeats) {
        return selectedSeats.stream().mapToDouble(Seat::getPrice).sum();
    }

    private String generateBookingId() {
        long bookingNumber = bookingCounter.incrementAndGet();
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        return BOOKING_ID_PREFIX + timestamp + String.format("%06d", bookingNumber);
    }

    private void markSeatsAsAvailable(Show show, List<Seat> seats) {
        for (Seat seat : seats) {
            Seat showSeat = show.getSeats().get(seat.getId());
            showSeat.setSeatStatus(SeatStatus.AVAILABLE);
        }
    }
}
