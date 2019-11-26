package league.demo.coach;

import league.demo.model.Coach;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CoachRepository extends CrudRepository<Coach, UUID> {
    Coach findByTeamId(UUID id);

    List<Coach> findByName(String name);
}
