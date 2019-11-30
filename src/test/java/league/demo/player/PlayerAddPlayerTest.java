package league.demo.player;

import league.demo.entity.Player;
import league.demo.entity.Team;
import league.demo.module.player.dto.DtoAddPlayerRequest;
import league.demo.util.CalendarFormatter;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.stream.StreamSupport;

import static java.sql.Date.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PlayerAddPlayerTest extends PlayerBaseTest {

    @Test
    public void canAddPlayer() throws Exception {
        TestContext testContext = prepareFixtures();

        Team testTeam = testContext.dbTeams.stream().filter(team -> team.getName().equals("Fenerbahce")).findFirst().get();

        DtoAddPlayerRequest playerRequest = new DtoAddPlayerRequest(
                "Nabil",
                "Dirar",
                18,
                valueOf("1990-06-19"),
                false,
                1500000.00,
                testTeam.getId());

        mvc.perform(post("/player/add")
                .with(user("username").password("pass").roles("USER")).with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(playerRequest)))
                .andExpect(status().isOk());

        Iterable<Player> allPlayers = playerRepository.findByName(playerRequest.getName());
        long dbResultSize = StreamSupport.stream(allPlayers.spliterator(), false).count();

        assertThat(dbResultSize).isEqualTo(1);
        allPlayers.forEach(player -> {
            assertThat(player.getName()).isEqualTo(playerRequest.getName());
            assertThat(player.getSurname()).isEqualTo(playerRequest.getSurname());
            assertThat(player.getJerseyNumber()).isEqualTo(playerRequest.getJerseyNumber());
            assertThat(CalendarFormatter.formatCalender(player.getBirthDate())).isEqualTo(CalendarFormatter.formatCalender(playerRequest.getBirthDate()));
            assertThat(player.getSalary()).isEqualTo(playerRequest.getSalary());
            assertThat(player.getTeam().getId()).isEqualTo(playerRequest.getTeamId());
        });
    }
}
