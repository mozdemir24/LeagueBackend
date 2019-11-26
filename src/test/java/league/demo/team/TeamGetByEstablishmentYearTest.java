package league.demo.team;

import league.demo.model.Team;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class TeamGetByEstablishmentYearTest extends TeamBaseTest {

    @Test
    public void canGetByEstablishmentYear() throws Exception {
        TestContext testContext = prepareFixtures();

        List<Team> filteredTeams = testContext.dbTeams.stream()
                .filter(dbTeam -> dbTeam.getEstablishmentYear() == 1903)
                .collect(Collectors.toList());

        MvcResult mvcResult = mvc.perform(
                get("/team//list/{establishmentYear}", 1903))
                .andExpect(status().isOk())
                .andReturn();

        List<Team> result = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Team.class)
        );

        assertThat(result.size()).isEqualTo(filteredTeams.size());

        filteredTeams.forEach(filtered -> {
            Team response = result.stream()
                    .filter(team -> team.getId().equals(filtered.getId()))
                    .findFirst().get();

            assertThat(response).isNotNull();
            assertThat(filtered.getId()).isEqualTo(response.getId());
            assertThat(filtered.getName()).isEqualTo(response.getName());
            assertThat(filtered.getFlagColor1()).isEqualTo(response.getFlagColor1());
            assertThat(filtered.getFlagColor2()).isEqualTo(response.getFlagColor2());
        });
    }

    @Test
    public void cannotGetByEstablishmentYear() throws Exception {
        prepareFixtures();

        MvcResult mvcResult = mvc.perform(
                get("/team//list/{establishmentYear}", 1902))
                .andExpect(status().isOk())
                .andReturn();

        List<Team> result = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Team.class)
        );

        assertThat(result).isEmpty();
    }
}
