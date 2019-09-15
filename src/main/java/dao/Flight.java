package dao;

import java.sql.Date;

public class Flight {
    public Integer id;
    public String cityFrom;
    public String cityTo;
    public Date departure;
    public Date arrival;
    public Integer boardId;
    public Integer companyId;
    public Integer placesCounter;

    public Flight(Integer id, Integer companyId, Integer boardId, Integer placesCounter, String cityFrom, String cityTo, Date departure, Date arrival) {
        this.id = id;
        this.companyId = companyId;
        this.boardId = boardId;
        this.placesCounter = placesCounter;
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        this.departure = departure;
        this.arrival = arrival;
    }

    @Override
    public String toString() {
        return String.format("{\n\tid: %d,\n\tcityFrom: %s,\n\tcityTo: %s,\n\tdeparture:  %tc,\n\tarrival:  %tc,\n\tboardId: %d,\n\tcompanyId: %d,\n\tplacesCounter: %d\n}\n",
                this.id, this.cityFrom.trim(), this.cityTo.trim(), this.departure, this.arrival, this.boardId, this.companyId, this.placesCounter);
    }
}
