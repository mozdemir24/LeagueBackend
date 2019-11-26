package league.demo.coach;

import league.demo.coach.dto.DtoAddCoachRequest;
import league.demo.coach.dto.DtoListAllCoaches;
import league.demo.model.Coach;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/coach")
public class CoachController {

    private final CoachService coachService;

    public CoachController(CoachService coachService) {
        this.coachService = coachService;
    }

    @GetMapping("/team/{id}")
    @ResponseBody
    public Coach getByTeamId(@PathVariable UUID id) {
        return coachService.getByTeamId(id);
    }

    @GetMapping("/list")
    @ResponseBody
    public List<DtoListAllCoaches> getAll() {
        return coachService.getAll();
    }

    @PostMapping("/add")
    public void addCoach(@RequestBody DtoAddCoachRequest dtoAddCoachRequest) {
        coachService.addCoach(dtoAddCoachRequest);
    }

}
