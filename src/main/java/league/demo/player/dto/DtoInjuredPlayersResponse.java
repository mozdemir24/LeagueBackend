package league.demo.player.dto;

import java.sql.Date;
import java.util.UUID;

public class DtoInjuredPlayersResponse {
    private UUID id;
    private String name;
    private String surname;
    private int jerseyNumber;
    private Date birthDate;
    private boolean injuryStatus;
    private double salary;
    private String teamName;

    public DtoInjuredPlayersResponse() {

    }

    public DtoInjuredPlayersResponse(UUID id, String name, String surname, int jerseyNumber, Date birthDate, boolean injuryStatus, double salary, String teamName) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.jerseyNumber = jerseyNumber;
        this.birthDate = birthDate;
        this.injuryStatus = injuryStatus;
        this.salary = salary;
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

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
