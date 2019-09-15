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
}
