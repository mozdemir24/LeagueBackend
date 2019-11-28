package league.demo.player;

import league.demo.entity.Player;
import league.demo.module.player.dto.DtoPlayerSalaryResponse;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PlayerGetBySalaryTest extends PlayerBaseTest {

    @Test
    public void canGetBySalary() throws Exception {

        TestContext testContext = prepareFixtures();


        List<Player> filteredPlayers = testContext.dbPlayers.stream()
                .filter(player -> player.getSalary() >= 3000000.00)
                .collect(Collectors.toList());

        MvcResult mvcResult = mvc.perform(
                get("/player/salary/{salary}", 3000000.00))
                .andExpect(status().isOk())
                .andReturn();

        List<DtoPlayerSalaryResponse> result = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, DtoPlayerSalaryResponse.class)
        );

        assertThat(result.size()).isEqualTo(filteredPlayers.size());

        filteredPlayers.forEach(filteredPlayer -> {
            DtoPlayerSalaryResponse response = result.stream()
                    .filter(player -> player.getName().equals(filteredPlayer.getName()))
                    .findFirst().get();

            assertThat(response).isNotNull();
            assertThat(filteredPlayer.getName()).isEqualTo(response.getName());
            assertThat(filteredPlayer.getSurname()).isEqualTo(response.getSurname());
            assertThat(filteredPlayer.getSalary()).isEqualTo(response.getSalary());
            assertThat(filteredPlayer.getTeam().getName()).isEqualTo(response.getTeamName());

        });
    }
}
