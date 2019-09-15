package dao;

public class Client {
    public Integer id;
    public String name;
    public String surname;
    public Long pasportId;
    public Integer discount;
    public String phone;

    public Client(Integer id, String name, String surname, Long pasportId, Integer discount, String phone) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.pasportId = pasportId;
        this.discount = discount;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return String.format("{\n\tid: %d,\n\tname: %s,\n\tsurname: %s,\n\tpassportId: %d,\n\tdiscount: %d,\n\tphone: %s\n}\n",
                this.id, this.name, this.surname, this.pasportId, this.discount, this.phone);
    }
}
