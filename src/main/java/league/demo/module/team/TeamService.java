package league.demo.module.team;

import league.demo.entity.Team;
import league.demo.module.team.dto.DtoAddTeamRequest;
import league.demo.module.team.dto.DtoDeleteTeamResponse;
import league.demo.module.team.dto.DtoListTeamsResponse;

import java.util.List;
import java.util.UUID;

public interface TeamService {
    List<Team> getByEstablishmentYear(int establishmentYear);

    void addTeam(DtoAddTeamRequest team);

    DtoDeleteTeamResponse deleteTeam(UUID id);

    List<DtoListTeamsResponse> getAll();
}
