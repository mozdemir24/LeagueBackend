package league.demo.team;

import league.demo.model.Team;
import league.demo.team.dto.DtoDeleteTeamResponse;
import league.demo.team.dto.Status;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class TeamDeleteTeamTest extends TeamBaseTest {

    @Test
    public void canDeleteTeam() throws Exception {
        TestContext testContext = prepareFixtures();
        Team candidateTeam = testContext.dbTeams.stream()
                .filter(team -> team.getName().equals("Fenerbahce"))
                .collect(Collectors.toList()).get(0);

        MvcResult mvcResult = mvc.perform(delete("/team/delete/{id}", candidateTeam.getId()))
                .andExpect(status().isOk())
                .andReturn();

        DtoDeleteTeamResponse result = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                DtoDeleteTeamResponse.class);

        Iterable<Team> allTeams = teamRepository.findAll();
        long dbResultSize = StreamSupport.stream(allTeams.spliterator(), false).count();
        assertThat(dbResultSize).isEqualTo(testContext.dbTeams.size() - 1);

        Optional<Team> deletedTeam = teamRepository.findById(candidateTeam.getId());
        assertThat(deletedTeam.isPresent()).isFalse();

        assertThat(result.getStatus()).isEqualTo(Status.OK);
        assertThat(result.getResult()).isEqualTo(candidateTeam.getName() + " deleted.");
    }

    @Test
    public void cannotDeleteTeam() throws Exception {
        prepareFixtures();

        MvcResult mvcResult = mvc.perform(delete("/team/delete/{id}", UUID.randomUUID()))
                .andExpect(status().isOk())
                .andReturn();

        DtoDeleteTeamResponse result = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                DtoDeleteTeamResponse.class);

        assertThat(result.getStatus()).isEqualTo(Status.ERROR);
        assertThat(result.getResult()).isEqualTo("Team could not be deleted.");
    }
}
