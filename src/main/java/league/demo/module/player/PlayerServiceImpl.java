package league.demo.module.player;

import league.demo.entity.Player;
import league.demo.entity.Team;
import league.demo.module.player.dto.*;
import league.demo.module.team.TeamRepository;
import league.demo.module.team.dto.Status;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public Player getById(UUID id) {
        Optional<Player> player = playerRepository.findById(id);
        return player.orElse(null);
    }

    @Override
    public List<DtoListPlayerResponse> getByTeamName(String name) {
        List<Player> players = playerRepository.findByTeamName(name);
        List<DtoListPlayerResponse> responseList = new ArrayList<>();

        if (!players.isEmpty()) {
            players.forEach(player -> {
                DtoListPlayerResponse response = new DtoListPlayerResponse(
                        player.getName(),
                        player.getSurname(),
                        player.getJerseyNumber(),
                        player.getBirthDate(),
                        player.isInjuryStatus(),
                        player.getSalary()
                );
                responseList.add(response);
            });
            return responseList;
        } else return new ArrayList<>();
    }

    @Override
    public void addPlayer(DtoAddPlayerRequest request) {
        Optional<Team> team = teamRepository.findById(request.getTeamId());
        if (team.isPresent()) {
            Player player = new Player(
                    request.getName(),
                    request.getSurname(),
                    request.getJerseyNumber(),
                    request.getBirthDate(),
                    request.isInjuryStatus(),
                    request.getSalary(),
                    team.get()
            );
            playerRepository.save(player);
        }
    }

    @Override
    public DtoDeletePlayerResponse deletePlayer(UUID id) {
        Optional<Player> player = playerRepository.findById(id);
        if (player.isPresent()) {
            playerRepository.delete(player.get());
            return new DtoDeletePlayerResponse(Status.OK, player.get().getName() + " deleted.");
        } else {
            return new DtoDeletePlayerResponse(Status.ERROR, "Player could not be deleted.");
        }

    }

    @Override
    public List<DtoListAllPlayers> getAll() {
        Iterable<Player> players = playerRepository.findAll();
        List<DtoListAllPlayers> dtoListAllPlayers = new ArrayList<>();

        for (Player player : players) {
            DtoListAllPlayers dtoPlayer = new DtoListAllPlayers(
                    player.getId(),
                    player.getName(),
                    player.getSurname(),
                    player.getJerseyNumber(),
                    player.getBirthDate(),
                    player.isInjuryStatus(),
                    player.getSalary(),
                    player.getTeam().getName()
            );
            dtoListAllPlayers.add(dtoPlayer);
        }
        return dtoListAllPlayers;
    }

    @Override
    public List<DtoInjuredPlayersResponse> getByInjuryStatus(boolean status) {
        Iterable<Player> players = playerRepository.findByInjuryStatus(status);
        List<DtoInjuredPlayersResponse> dtoInjuredPlayersResponseList = new ArrayList<>();

        for (Player player : players) {
            if (player.isInjuryStatus() == status) {
                DtoInjuredPlayersResponse dtoInjuredPlayersResponse = new DtoInjuredPlayersResponse(
                        player.getId(),
                        player.getName(),
                        player.getSurname(),
                        player.getJerseyNumber(),
                        player.getBirthDate(),
                        player.isInjuryStatus(),
                        player.getSalary(),
                        player.getTeam().getName());
                dtoInjuredPlayersResponseList.add(dtoInjuredPlayersResponse);
            }
        }
        return dtoInjuredPlayersResponseList;
    }

    @Override
    public List<DtoPlayerSalaryResponse> getBySalary(double salary) {
        Iterable<Player> players = playerRepository.findBySalaryGreaterThanEqual(salary);
        List<DtoPlayerSalaryResponse> dtoPlayerSalaryList = new ArrayList<>();

        for (Player player : players) {
            DtoPlayerSalaryResponse dtoPlayerSalary = new DtoPlayerSalaryResponse(
                    player.getName(),
                    player.getSurname(),
                    player.getSalary(),
                    player.getTeam().getName());
            dtoPlayerSalaryList.add(dtoPlayerSalary);
        }
        return dtoPlayerSalaryList;
    }
}
