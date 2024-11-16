package Entity;

import java.time.LocalDateTime;

public class Pricing {
    private String id;
    private String tickerId;
    private LocalDateTime dateTime;
    private double openPrice;
    private double closePrice;
    private double currentPrice;

    public Pricing(){;}

    public Pricing(String id, String tickerId, LocalDateTime dateTime, double openPrice, double closePrice, double currentPrice) {
        this.id = id;
        this.tickerId = tickerId;
        this.dateTime = dateTime;
        this.openPrice = openPrice;
        this.closePrice = closePrice;
        this.currentPrice = currentPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTickerId() {
        return tickerId;
    }

    public void setTickerId(String tickerId) {
        this.tickerId = tickerId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public double getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(double openPrice) {
        this.openPrice = openPrice;
    }

    public double getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(double closePrice) {
        this.closePrice = closePrice;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    @Override
    public String toString() {
        return "Pricing{" +
                "id='" + id + '\'' +
                ", tickerId='" + tickerId + '\'' +
                ", dateTime=" + dateTime +
                ", openPrice=" + openPrice +
                ", closePrice=" + closePrice +
                ", currentPrice=" + currentPrice +
                '}';
    }
}
