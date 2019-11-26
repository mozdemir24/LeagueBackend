package league.demo.player.dto;

import java.sql.Date;
import java.util.UUID;

public class DtoAddPlayerRequest {

    private String name;
    private String surname;
    private int jerseyNumber;
    private Date birthDate;
    private boolean injuryStatus;
    private double salary;
    private UUID teamId;

    public DtoAddPlayerRequest() {
    }

    public DtoAddPlayerRequest(String name, String surname, int jerseyNumber, Date birthDate, boolean injuryStatus, double salary, UUID teamId) {
        this.name = name;
        this.surname = surname;
        this.jerseyNumber = jerseyNumber;
        this.birthDate = birthDate;
        this.injuryStatus = injuryStatus;
        this.salary = salary;
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

    public int getJerseyNumber() {
        return jerseyNumber;
    }

    public void setJerseyNumber(int jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isInjuryStatus() {
        return injuryStatus;
    }

    public void setInjuryStatus(boolean injuryStatus) {
        this.injuryStatus = injuryStatus;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public UUID getTeamId() {
        return teamId;
    }

    public void setTeamId(UUID teamId) {
        this.teamId = teamId;
    }
}
