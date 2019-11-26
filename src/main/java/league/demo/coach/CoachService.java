package league.demo.coach;

import league.demo.coach.dto.DtoAddCoachRequest;
import league.demo.coach.dto.DtoListAllCoaches;
import league.demo.model.Coach;

import java.util.List;
import java.util.UUID;

public interface CoachService {
    Coach getByTeamId(UUID id);

    List<DtoListAllCoaches> getAll();

    void addCoach(DtoAddCoachRequest coach);
}
