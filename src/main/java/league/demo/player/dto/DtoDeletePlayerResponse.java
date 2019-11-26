package league.demo.player.dto;

import league.demo.team.dto.Status;

public class DtoDeletePlayerResponse {

    private league.demo.team.dto.Status status;
    private String result;

    public DtoDeletePlayerResponse() {

    }

    public DtoDeletePlayerResponse(league.demo.team.dto.Status status, String result) {
        this.status = status;
        this.result = result;
    }

    public league.demo.team.dto.Status getStatus() {
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
