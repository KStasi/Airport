package dao;

public class Ticket {
    public Integer id;
    public Integer flightId;
    public Integer clientId;
    public Long price;
    public Integer place;
    public String type;

    public Ticket(Integer id, Integer flightId, Integer clientId, Long price, Integer place, String type) {
        this.id = id;
        this.flightId =flightId;
        this.clientId = clientId;
        this.price = price;
        this.place = place;
        this.type = type;
    }
}
