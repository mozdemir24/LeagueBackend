package league.demo.team;

import league.demo.model.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TeamRepository extends CrudRepository<Team, UUID> {
    List<Team> findByEstablishmentYear(int establishmentYear);

    List<Team> findByName(String name);
}
