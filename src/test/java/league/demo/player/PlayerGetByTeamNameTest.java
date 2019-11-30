package league.demo.player;

import league.demo.entity.Player;
import league.demo.module.player.dto.DtoListPlayerResponse;
import league.demo.util.CalendarFormatter;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PlayerGetByTeamNameTest extends PlayerBaseTest {

    @Test
    public void canGetByTeamName() throws Exception {
        TestContext testContext = prepareFixtures();

        List<Player> filteredPlayers = testContext.dbPlayers.stream()
                .filter(player -> player.getTeam().getName().equals("Fenerbahce"))
                .collect(Collectors.toList());

        MvcResult mvcResult = mvc.perform(
                get("/player/list/{name}", "Fenerbahce").with(user("username").password("pass").roles("USER")))
                .andExpect(status().isOk())
                .andReturn();

        List<DtoListPlayerResponse> result = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, DtoListPlayerResponse.class));

        assertThat(result.size()).isEqualTo(filteredPlayers.size());

        filteredPlayers.forEach(filteredPlayer -> {
            DtoListPlayerResponse response = result.stream()
                    .filter(player -> player.getName().equals(filteredPlayer.getName()))
                    .findFirst().get();

            assertThat(response).isNotNull();
            assertThat(filteredPlayer.getName()).isEqualTo(response.getName());
            assertThat(filteredPlayer.getSurname()).isEqualTo(response.getSurname());
            assertThat(filteredPlayer.getJerseyNumber()).isEqualTo(response.getJerseyNumber());
            assertThat(CalendarFormatter.formatCalender(filteredPlayer.getBirthDate())).isEqualTo(CalendarFormatter.formatCalender(response.getBirthDate()));
            assertThat(filteredPlayer.isInjuryStatus()).isEqualTo(response.isInjuryStatus());
            assertThat(filteredPlayer.getSalary()).isEqualTo(response.getSalary());
        });
    }
}
