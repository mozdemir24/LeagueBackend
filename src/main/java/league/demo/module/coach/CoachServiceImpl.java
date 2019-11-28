package league.demo.module.coach;

import league.demo.entity.Coach;
import league.demo.entity.Team;
import league.demo.module.coach.dto.DtoAddCoachRequest;
import league.demo.module.coach.dto.DtoListAllCoaches;
import league.demo.module.team.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CoachServiceImpl implements CoachService {

    private final CoachRepository coachRepository;
    private final TeamRepository teamRepository;

    public CoachServiceImpl(CoachRepository coachRepository, TeamRepository teamRepository) {
        this.coachRepository = coachRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public Coach getByTeamId(UUID id) {
        return coachRepository.findByTeamId(id);
    }

    @Override
    public List<DtoListAllCoaches> getAll() {
        Iterable<Coach> coachList = coachRepository.findAll();
        List<DtoListAllCoaches> dtoListAllCoachesList = new ArrayList<>();
        for (Coach coach : coachList) {
            DtoListAllCoaches dtoListAllCoaches = new DtoListAllCoaches(
                    coach.getId(),
                    coach.getName(),
                    coach.getSurname(),
                    coach.getTeam().getName());
            dtoListAllCoachesList.add(dtoListAllCoaches);
        }
        return dtoListAllCoachesList;
    }

    @Override
    public void addCoach(DtoAddCoachRequest request) {
        Optional<Team> team = teamRepository.findById(request.getTeamId());
        if (team.isPresent()) {
            Coach coach = new Coach(
                    request.getName(),
                    request.getSurname(),
                    team.get()
            );
            coachRepository.save(coach);
        }
    }
}
