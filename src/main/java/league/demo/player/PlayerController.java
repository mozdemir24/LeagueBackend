package league.demo.player;

import league.demo.model.Player;
import league.demo.player.dto.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/player")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping("/add")
    public void addPlayer(@RequestBody DtoAddPlayerRequest dtoAddPlayerRequest) {
        playerService.addPlayer(dtoAddPlayerRequest);
    }

    @DeleteMapping("/delete/{id}")
    public DtoDeletePlayerResponse deletePlayer(@PathVariable UUID id) {
        return playerService.deletePlayer(id);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Player getById(@PathVariable UUID id) {
        return playerService.getById(id);
    }

    @GetMapping("/list/{name}")
    @ResponseBody
    public List<DtoListPlayerResponse> getByTeamName(@PathVariable String name) {
        return playerService.getByTeamName(name);
    }

    @GetMapping("/list/all")
    @ResponseBody
    public List<DtoListAllPlayers> getAll() {
        return playerService.getAll();
    }

    @GetMapping("/injured/{status}")
    @ResponseBody
    public List<DtoInjuredPlayersResponse> getByInjuryStatus(@PathVariable boolean status) {
        return playerService.getByInjuryStatus(status);
    }

    @GetMapping("/salary/{salary}")
    @ResponseBody
    public List<DtoPlayerSalaryResponse> getBySalary(@PathVariable double salary) {
        return playerService.getBySalary(salary);
    }
}
