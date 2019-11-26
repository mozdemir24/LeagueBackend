package league.demo.team;

import league.demo.model.Team;
import league.demo.team.dto.DtoAddTeamRequest;
import league.demo.team.dto.DtoDeleteTeamResponse;
import league.demo.team.dto.DtoListTeamsResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/team")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping("/add")
    public void addTeam(@RequestBody DtoAddTeamRequest team) {
        teamService.addTeam(team);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public DtoDeleteTeamResponse deleteTeam(@PathVariable UUID id) {
        return teamService.deleteTeam(id);
    }

    @GetMapping("/list/{establishmentYear}")
    @ResponseBody
    public List<Team> getByEstablishmentYear(@PathVariable int establishmentYear) {
        return teamService.getByEstablishmentYear(establishmentYear);
    }

    @GetMapping("/list/all")
    @ResponseBody
    public List<DtoListTeamsResponse> getAll() {
        return teamService.getAll();
    }
}
