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
}
