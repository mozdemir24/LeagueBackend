package league.demo.player;

import league.demo.model.Player;
import league.demo.player.dto.DtoInjuredPlayersResponse;
import league.demo.util.CalendarFormatter;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PlayerGetByInjuryStatusTest extends PlayerBaseTest {

    @Test
    public void canGetByInjuryStatusTrue() throws Exception {

        TestContext testContext = prepareFixtures();

        List<Player> filteredPlayers = testContext.dbPlayers.stream()
                .filter(Player::isInjuryStatus)
                .collect(Collectors.toList());

        MvcResult mvcResult = mvc.perform(
                get("/player/injured/{status}", true))
                .andExpect(status().isOk())
                .andReturn();

        List<DtoInjuredPlayersResponse> result = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, DtoInjuredPlayersResponse.class)
        );

        assertThat(result.size()).isEqualTo(filteredPlayers.size());

        testContext.dbPlayers.forEach(dbPlayer -> {
            List<DtoInjuredPlayersResponse> response = result.stream()
                    .filter(player -> player.getId().equals(dbPlayer.getId()))
                    .collect(Collectors.toList());

            for (DtoInjuredPlayersResponse injured : response) {
                assertThat(dbPlayer.getId()).isEqualTo(injured.getId());
                assertThat(dbPlayer.getName()).isEqualTo(injured.getName());
                assertThat(dbPlayer.getSurname()).isEqualTo(injured.getSurname());
                assertThat(dbPlayer.getJerseyNumber()).isEqualTo(injured.getJerseyNumber());
                //assertThat(dbPlayer.getBirthDate()).isEqualTo(injured.getBirthDate()); TODO: birthdate
                assertThat(dbPlayer.isInjuryStatus()).isEqualTo(injured.isInjuryStatus());
                assertThat(dbPlayer.getSalary()).isEqualTo(injured.getSalary());
                assertThat(dbPlayer.getTeam().getName()).isEqualTo(injured.getTeamName());

            }

        });
    }

    @Test
    public void canGetByInjuryStatusFalse() throws Exception {

        TestContext testContext = prepareFixtures();


        List<Player> filteredPlayers = testContext.dbPlayers.stream()
                .filter(player -> !player.isInjuryStatus())
                .collect(Collectors.toList());

        MvcResult mvcResult = mvc.perform(
                get("/player/injured/{status}", false))
                .andExpect(status().isOk())
                .andReturn();

        List<DtoInjuredPlayersResponse> result = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, DtoInjuredPlayersResponse.class)
        );

        assertThat(result.size()).isEqualTo(filteredPlayers.size());

        filteredPlayers.forEach(filteredPlayer -> {
            DtoInjuredPlayersResponse response = result.stream()
                    .filter(player -> player.getId().equals(filteredPlayer.getId()))
                    .findFirst().get();

            assertThat(response).isNotNull();
            assertThat(filteredPlayer.getId()).isEqualTo(response.getId());
            assertThat(filteredPlayer.getName()).isEqualTo(response.getName());
            assertThat(filteredPlayer.getSurname()).isEqualTo(response.getSurname());
            assertThat(filteredPlayer.getJerseyNumber()).isEqualTo(response.getJerseyNumber());
            assertThat(CalendarFormatter.formatCalender(filteredPlayer.getBirthDate())).isEqualTo(CalendarFormatter.formatCalender(response.getBirthDate()));
            assertThat(filteredPlayer.isInjuryStatus()).isEqualTo(response.isInjuryStatus());
            assertThat(filteredPlayer.getSalary()).isEqualTo(response.getSalary());
            assertThat(filteredPlayer.getTeam().getName()).isEqualTo(response.getTeamName());
        });
    }
}
