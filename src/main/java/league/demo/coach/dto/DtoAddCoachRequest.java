package league.demo.coach.dto;


import java.util.UUID;

public class DtoAddCoachRequest {
    private String name;
    private String surname;
    private UUID teamId;

    public DtoAddCoachRequest() {

    }

    public DtoAddCoachRequest(String name, String surname, UUID teamId) {
        this.name = name;
        this.surname = surname;
        this.teamId = teamId;
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

    public UUID getTeamId() {
        return teamId;
    }

    public void setTeamId(UUID teamId) {
        this.teamId = teamId;
    }
}
