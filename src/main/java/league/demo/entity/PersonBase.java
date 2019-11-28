package league.demo.entity;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
public class PersonBase {

    @Id
    private UUID id = UUID.randomUUID();
    private String name;
    private String surname;

    public PersonBase() {
    }

    public PersonBase(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
