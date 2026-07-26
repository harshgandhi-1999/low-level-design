package hard.moviebookinglld.seat;

public class Seat {
    private final String id;
    private final int row;
    private final int col;
    private final SeatType seatType;
    private final double price;
    private SeatStatus seatStatus;

    public Seat(String id, int row, int col, SeatType seatType, double price) {
        this.id = id;
        this.row = row;
        this.col = col;
        this.seatType = seatType;
        this.price = price;
        this.seatStatus = SeatStatus.AVAILABLE;
    }

    public String getId() {
        return id;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public double getPrice() {
        return price;
    }

    public SeatStatus getSeatStatus() {
        return seatStatus;
    }

    public void setSeatStatus(SeatStatus seatStatus) {
        this.seatStatus = seatStatus;
    }
}
