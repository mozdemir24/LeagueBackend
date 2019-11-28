package league.demo.module.team;

import league.demo.entity.Team;
import league.demo.module.team.dto.DtoAddTeamRequest;
import league.demo.module.team.dto.DtoDeleteTeamResponse;
import league.demo.module.team.dto.DtoListTeamsResponse;
import league.demo.module.team.dto.Status;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public List<Team> getByEstablishmentYear(int establishmentYear) {
        return teamRepository.findByEstablishmentYear(establishmentYear);
    }

    @Override

    public void addTeam(DtoAddTeamRequest dtoAddTeamRequest) {
        Team team = new Team(
                dtoAddTeamRequest.getName(),
                dtoAddTeamRequest.getEstablishmentYear(),
                dtoAddTeamRequest.getFlagColor1(),
                dtoAddTeamRequest.getFlagColor2()
        );
        teamRepository.save(team);
    }

    @Override
    public DtoDeleteTeamResponse deleteTeam(UUID id) {
        Optional<Team> team = teamRepository.findById(id);
        if (team.isPresent()) {
            teamRepository.delete(team.get());
            return new DtoDeleteTeamResponse(Status.OK, team.get().getName() + " deleted.");
        } else {
            return new DtoDeleteTeamResponse(Status.ERROR, "Team could not be deleted.");
        }
    }

    @Override
    public List<DtoListTeamsResponse> getAll() {
        Iterable<Team> teams = teamRepository.findAll();
        List<DtoListTeamsResponse> dtoListTeamsResponses = new ArrayList<>();
        for (Team team : teams) {
            DtoListTeamsResponse teamsResponse = new DtoListTeamsResponse(
                    team.getId(),
                    team.getName(),
                    team.getEstablishmentYear(),
                    team.getFlagColor1(),
                    team.getFlagColor2());
            dtoListTeamsResponses.add(teamsResponse);
        }
        return dtoListTeamsResponses;
    }

}

