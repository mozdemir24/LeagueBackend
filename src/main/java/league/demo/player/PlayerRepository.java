package league.demo.player;

import league.demo.model.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PlayerRepository extends CrudRepository<Player, UUID> {
    List<Player> findByTeamName(String name);

    List<Player> findByInjuryStatus(boolean injury);

    List<Player> findBySalaryGreaterThanEqual(double salary);

    List<Player> findByName(String name);
}
