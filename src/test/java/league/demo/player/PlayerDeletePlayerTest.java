package league.demo.player;

import league.demo.entity.Player;
import league.demo.module.player.dto.DtoDeletePlayerResponse;
import league.demo.module.team.dto.Status;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PlayerDeletePlayerTest extends PlayerBaseTest {

    @Test
    public void canDeletePlayer() throws Exception {
        TestContext testContext = prepareFixtures();

        Player candidatePlayer = testContext.dbPlayers.stream()
                .filter(player -> player.getName().equals("Radamel"))
                .collect(Collectors.toList()).get(0);

        MvcResult mvcResult = mvc.perform(delete("/player/delete/{id}", candidatePlayer.getId()))
                .andExpect(status().isOk())
                .andReturn();

        DtoDeletePlayerResponse result = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                DtoDeletePlayerResponse.class);

        Iterable<Player> allPlayers = playerRepository.findAll();
        long dbResultSize = StreamSupport.stream(allPlayers.spliterator(), false).count();
        assertThat(dbResultSize).isEqualTo(testContext.dbPlayers.size() - 1);

        Optional<Player> deletedPlayer = playerRepository.findById(candidatePlayer.getId());
        assertThat(deletedPlayer.isPresent()).isFalse();

        assertThat(result.getStatus()).isEqualTo(Status.OK);
        assertThat(result.getResult()).isEqualTo(candidatePlayer.getName() + " deleted.");
    }

    @Test
    public void cannotDeleteTeam() throws Exception {
        prepareFixtures();

        MvcResult mvcResult = mvc.perform(delete("/player/delete/{id}", UUID.randomUUID()))
                .andExpect(status().isOk())
                .andReturn();

        DtoDeletePlayerResponse result = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                DtoDeletePlayerResponse.class);

        assertThat(result.getStatus()).isEqualTo(Status.ERROR);
        assertThat(result.getResult()).isEqualTo("Player could not be deleted.");
    }

}
