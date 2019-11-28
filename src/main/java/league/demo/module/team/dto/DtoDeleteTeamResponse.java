package league.demo.module.team.dto;

public class DtoDeleteTeamResponse {

    private Status status;
    private String result;

    public DtoDeleteTeamResponse() {
    }

    public DtoDeleteTeamResponse(Status status, String result) {
        this.status = status;
        this.result = result;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
