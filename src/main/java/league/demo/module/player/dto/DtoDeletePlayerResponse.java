package league.demo.module.player.dto;

import league.demo.module.team.dto.Status;

public class DtoDeletePlayerResponse {

    private league.demo.module.team.dto.Status status;
    private String result;

    public DtoDeletePlayerResponse() {

    }

    public DtoDeletePlayerResponse(league.demo.module.team.dto.Status status, String result) {
        this.status = status;
        this.result = result;
    }

    public league.demo.module.team.dto.Status getStatus() {
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
