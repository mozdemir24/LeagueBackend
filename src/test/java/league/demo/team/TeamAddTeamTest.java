package league.demo.team;

import league.demo.entity.FlagColor;
import league.demo.entity.Team;
import league.demo.module.team.dto.DtoAddTeamRequest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TeamAddTeamTest extends TeamBaseTest {

    @Test
    public void canAddTeam() throws Exception {
        prepareFixtures();

        DtoAddTeamRequest teamRequest = new DtoAddTeamRequest(
                "Kayserispor",
                1966,
                FlagColor.RED,
                FlagColor.YELLOW);

        mvc.perform(post("/team/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(teamRequest)))
                .andExpect(status().isOk())
                .andReturn();

        Iterable<Team> allTeams = teamRepository.findByName(teamRequest.getName());
        long dbResultSize = StreamSupport.stream(allTeams.spliterator(), false).count();

        assertThat(dbResultSize).isEqualTo(1);
        allTeams.forEach(team -> {
            assertThat(team.getName()).isEqualTo(teamRequest.getName());
            assertThat(team.getEstablishmentYear()).isEqualTo(teamRequest.getEstablishmentYear());
            assertThat(team.getFlagColor1()).isEqualTo(teamRequest.getFlagColor1());
            assertThat(team.getFlagColor2()).isEqualTo(teamRequest.getFlagColor2());
        });
    }
}
