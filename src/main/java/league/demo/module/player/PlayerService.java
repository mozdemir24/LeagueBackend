package league.demo.module.player;

import league.demo.entity.Player;
import league.demo.module.player.dto.*;

import java.util.List;
import java.util.UUID;

public interface PlayerService {
    Player getById(UUID id);

    List<DtoListPlayerResponse> getByTeamName(String name);

    void addPlayer(DtoAddPlayerRequest player);

    List<DtoListAllPlayers> getAll();

    List<DtoInjuredPlayersResponse> getByInjuryStatus(boolean injury);

    List<DtoPlayerSalaryResponse> getBySalary(double salary);

    DtoDeletePlayerResponse deletePlayer(UUID id);

}
