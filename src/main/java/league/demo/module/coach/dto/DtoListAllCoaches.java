package league.demo.module.coach.dto;

import java.util.UUID;

public class DtoListAllCoaches {
    private UUID id;
    private String name;
    private String surname;
    private String teamName;

    public DtoListAllCoaches() {

    }

    public DtoListAllCoaches(UUID id, String name, String surname, String teamName) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.teamName = teamName;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
