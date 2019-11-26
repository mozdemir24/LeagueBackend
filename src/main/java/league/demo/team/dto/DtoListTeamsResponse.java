package league.demo.team.dto;

import league.demo.model.FlagColor;

import java.util.UUID;

public class DtoListTeamsResponse {
    private UUID id = UUID.randomUUID();
    private String name;
    private int establishmentYear;
    private FlagColor flagColor1;
    private FlagColor flagColor2;

    public DtoListTeamsResponse() {

    }

    public DtoListTeamsResponse(UUID id, String name, int establishmentYear, FlagColor flagColor1, FlagColor flagColor2) {
        this.id = id;
        this.name = name;
        this.establishmentYear = establishmentYear;
        this.flagColor1 = flagColor1;
        this.flagColor2 = flagColor2;
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

    public int getEstablishmentYear() {
        return establishmentYear;
    }

    public void setEstablishmentYear(int establishmentYear) {
        this.establishmentYear = establishmentYear;
    }

    public FlagColor getFlagColor1() {
        return flagColor1;
    }

    public void setFlagColor1(FlagColor flagColor1) {
        this.flagColor1 = flagColor1;
    }

    public FlagColor getFlagColor2() {
        return flagColor2;
    }

    public void setFlagColor2(FlagColor flagColor2) {
        this.flagColor2 = flagColor2;
    }
}
