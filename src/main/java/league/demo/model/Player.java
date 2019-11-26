package league.demo.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.sql.Date;

@Entity
public class Player extends PersonBase {

    private int jerseyNumber;
    private Date birthDate;
    private boolean injuryStatus;
    private double salary;
    @ManyToOne
    private Team team;

    public Player() {
    }

    public Player(String name, String surname, int jerseyNumber, Date birthDate, boolean injuryStatus, double salary, Team team) {
        super(name, surname);
        this.jerseyNumber = jerseyNumber;
        this.birthDate = birthDate;
        this.injuryStatus = injuryStatus;
        this.salary = salary;
        this.team = team;
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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
