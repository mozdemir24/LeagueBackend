package league.demo.coach;

import league.demo.entity.Coach;
import league.demo.entity.Team;
import league.demo.module.coach.dto.DtoAddCoachRequest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CoachAddCoachTest extends CoachBaseTest {
    @Test
    public void canAddCoach() throws Exception {
        TestContext testContext = prepareFixtures();
        Team dbTeam = testContext.dbTeams.stream()
                .filter(team -> team.getName().equals("Besiktas")).findFirst().get();

        DtoAddCoachRequest coachRequest = new DtoAddCoachRequest(
                "Abdullah",
                "Avcı",
                dbTeam.getId());

        mvc.perform(post("/coach/add")
                .with(user("username").password("pass").roles("USER")).with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(coachRequest)))
                .andExpect(status().isOk())
                .andReturn();

        Iterable<Coach> allCoaches = coachRepository.findByName(coachRequest.getName());
        long dbResultSize = StreamSupport.stream(allCoaches.spliterator(), false).count();

        assertThat(dbResultSize).isEqualTo(1);
        allCoaches.forEach(coach -> {
            assertThat(coach.getName()).isEqualTo(coachRequest.getName());
            assertThat(coach.getSurname()).isEqualTo(coachRequest.getSurname());
            assertThat(coach.getTeam().getId()).isEqualTo(coachRequest.getTeamId());
        });
    }
}
