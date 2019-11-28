package league.demo.coach;

import league.demo.entity.Coach;
import league.demo.entity.Team;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CoachGetByTeamIdTest extends CoachBaseTest {

    @Test
    public void canGetByTeamId() throws Exception {
        TestContext testContext = prepareFixtures();

        Team filteredTeam = testContext.dbTeams.stream()
                .filter(team -> team.getName().equals("Fenerbahce")).findFirst().get();

        MvcResult mvcResult = mvc.perform(
                get("/coach/team/{id}", filteredTeam.getId()))
                .andExpect(status().isOk())
                .andReturn();

        Coach result = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                Coach.class);

        assertThat(result).isNotNull();

        Coach dbCoach = testContext.dbCoaches.stream()
                .filter(coach -> coach.getId().equals(result.getId())).findFirst().get();

        assertThat(dbCoach).isNotNull();
        assertThat(dbCoach.getId()).isEqualTo(result.getId());
        assertThat(dbCoach.getName()).isEqualTo(result.getName());
        assertThat(dbCoach.getSurname()).isEqualTo(result.getSurname());
        assertThat(dbCoach.getTeam().getId()).isEqualTo(result.getTeam().getId());
        // TODO: coach'a dto oluştur.
    }
}
