package dao;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

public class Company {
    public Integer id;
    public String name;
    public String directorName;
    public String directorSurname;
    public String phone;

    public Company(Integer id, String name, String directorName, String directorSurname, String phone) {
        this.id = id;
        this.name = name;
        this.directorName = directorName;
        this.directorSurname = directorSurname;
        this.phone = phone;
    }
    @Override
    public String toString() {
        return String.format("{\n\tid: %d,\n\tname: %s,\n\tdirectorName: %s,\n\tdirectorSurname:  %s,\n\tphone:  %s\n}\n",
                this.id, this.name, this.directorName, this.directorSurname, this.phone);
    }

    public DBObject toDBObject() {
        BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();

        docBuilder.append("id", this.id);
        docBuilder.append("name", this.name);
        docBuilder.append("directorName", this.directorName);
        docBuilder.append("directorSurname", this.directorSurname);
        docBuilder.append("phone", this.phone);
        return docBuilder.get();
    }
}
