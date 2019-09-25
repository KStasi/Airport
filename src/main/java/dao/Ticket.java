package dao;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

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
    @Override
    public String toString() {
        return String.format("{\n\tid: %d,\n\tflightId: %d,\n\tclientId: %d,\n\tprice:  %d,\n\tplace:  %d,\n\ttype: %s\n}\n",
                this.id, this.flightId, this.clientId, this.price, this.place, this.type);
    }

    public DBObject toDBObject() {
        BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();

        docBuilder.append("id", this.id);
        docBuilder.append("flightId", this.flightId);
        docBuilder.append("clientId", this.clientId);
        docBuilder.append("price", this.price);
        docBuilder.append("place", this.place);
        docBuilder.append("type", this.type);
        return docBuilder.get();
    }

}
