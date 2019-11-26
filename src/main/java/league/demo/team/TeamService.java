package league.demo.team;

import league.demo.model.Team;
import league.demo.team.dto.DtoAddTeamRequest;
import league.demo.team.dto.DtoDeleteTeamResponse;
import league.demo.team.dto.DtoListTeamsResponse;

import java.util.List;
import java.util.UUID;

public interface TeamService {
    List<Team> getByEstablishmentYear(int establishmentYear);

    void addTeam(DtoAddTeamRequest team);

    DtoDeleteTeamResponse deleteTeam(UUID id);

    List<DtoListTeamsResponse> getAll();
}
