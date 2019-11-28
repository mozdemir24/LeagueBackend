package league.demo.module.coach;

import league.demo.entity.Coach;
import league.demo.module.coach.dto.DtoAddCoachRequest;
import league.demo.module.coach.dto.DtoListAllCoaches;

import java.util.List;
import java.util.UUID;

public interface CoachService {
    Coach getByTeamId(UUID id);

    List<DtoListAllCoaches> getAll();

    void addCoach(DtoAddCoachRequest coach);
}
