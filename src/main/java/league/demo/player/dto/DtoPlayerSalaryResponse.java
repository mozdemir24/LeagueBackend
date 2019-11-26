package league.demo.player.dto;

public class DtoPlayerSalaryResponse {

    private String name;
    private String surname;
    private double salary;
    private String teamName;

    public DtoPlayerSalaryResponse() {

    }

    public DtoPlayerSalaryResponse(String name, String surname, double salary, String teamName) {
        this.name = name;
        this.surname = surname;
        this.salary = salary;
        this.teamName = teamName;
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
