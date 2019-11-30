package league.demo.player;

import league.demo.module.player.dto.DtoListAllPlayers;
import league.demo.util.CalendarFormatter;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class PlayerGetAllTest extends PlayerBaseTest {


    @Test
    public void canGetAll() throws Exception {

        TestContext testContext = prepareFixtures();

        MvcResult mvcResult = mvc.perform(
                get("/player/list/all").with(user("username").password("pass").roles("USER")))
                .andExpect(status().isOk())
                .andReturn();

        List<DtoListAllPlayers> result = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, DtoListAllPlayers.class));

        assertThat(testContext.dbPlayers.size()).isEqualTo(result.size());

        testContext.dbPlayers.forEach(dbPlayer -> {
            DtoListAllPlayers response = result.stream()
                    .filter(player -> player.getId().equals(dbPlayer.getId()))
                    .findFirst().get();

            assertThat(response).isNotNull();
            assertThat(dbPlayer.getId()).isEqualTo(response.getId());
            assertThat(dbPlayer.getName()).isEqualTo(response.getName());
            assertThat(dbPlayer.getSurname()).isEqualTo(response.getSurname());
            assertThat(dbPlayer.getJerseyNumber()).isEqualTo(response.getJerseyNumber());
            assertThat(CalendarFormatter.formatCalender(dbPlayer.getBirthDate())).isEqualTo(CalendarFormatter.formatCalender(response.getBirthDate()));
            assertThat(dbPlayer.isInjuryStatus()).isEqualTo(response.isInjuryStatus());
            assertThat(dbPlayer.getSalary()).isEqualTo(response.getSalary());
            assertThat(dbPlayer.getTeam().getName()).isEqualTo(response.getTeamName());
        });
    }
}
