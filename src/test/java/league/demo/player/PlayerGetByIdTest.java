package league.demo.player;

import league.demo.entity.Player;
import league.demo.util.CalendarFormatter;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PlayerGetByIdTest extends PlayerBaseTest {

    @Test
    public void canGetById() throws Exception {
        TestContext testContext = prepareFixtures();

        Player candidatePlayer = testContext.dbPlayers.stream()
                .filter(player -> player.getName().equals("Radamel"))
                .findFirst().get();

        MvcResult mvcResult = mvc.perform(get("/player/{id}", candidatePlayer.getId())
                .with(user("username").password("pass").roles("USER")))
                .andExpect(status().isOk())
                .andReturn();

        Player result = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                Player.class);

        assertThat(candidatePlayer.getId()).isEqualTo(result.getId());
        assertThat(candidatePlayer.getSurname()).isEqualTo(result.getSurname());
        assertThat(candidatePlayer.getJerseyNumber()).isEqualTo(result.getJerseyNumber());
        assertThat(candidatePlayer.isInjuryStatus()).isEqualTo(result.isInjuryStatus());
        assertThat(candidatePlayer.getSalary()).isEqualTo(result.getSalary());
        assertThat(CalendarFormatter.formatCalender(candidatePlayer.getBirthDate())).isEqualTo(CalendarFormatter.formatCalender(result.getBirthDate()));
        assertThat(candidatePlayer.getTeam().getId()).isEqualTo(result.getTeam().getId());

    }
}
