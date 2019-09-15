package dao;

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
}
