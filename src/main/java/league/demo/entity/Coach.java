package league.demo.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Coach extends PersonBase {

    @OneToOne
    private Team team;

    public Coach() {
    }

    public Coach(String name, String surname, Team team) {
        super(name, surname);
        this.team = team;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
