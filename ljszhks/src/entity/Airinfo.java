package entity;

public class Airinfo {
    private int id;
    private int flight_number;
    private String destination;
    private String flight_date;

    @Override
    public String toString() {
        return "航班编号：" + id +
                ", 航班号：" + flight_number +
                ", 目的地：'" + destination +
                ", 起飞日期：'" + flight_date +"\n";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFlight_number() {
        return flight_number;
    }

    public void setFlight_number(int flight_number) {
        this.flight_number = flight_number;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getFlight_date() {
        return flight_date;
    }

    public void setFlight_date(String flight_date) {
        this.flight_date = flight_date;
    }
}
