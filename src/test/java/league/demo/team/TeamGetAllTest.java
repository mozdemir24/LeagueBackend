package league.demo.team;

import league.demo.module.team.dto.DtoListTeamsResponse;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TeamGetAllTest extends TeamBaseTest {

    @Test
    public void canGetAll() throws Exception {
        TestContext testContext = prepareFixtures();

        MvcResult mvcResult = mvc.perform(
                get("/team/list/all").with(user("user").password("pass").roles("USER")))
                .andExpect(status().isOk())
                .andReturn();

        List<DtoListTeamsResponse> result = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, DtoListTeamsResponse.class));

        assertThat(testContext.dbTeams.size()).isEqualTo(result.size());

        testContext.dbTeams.forEach(dbTeam -> {
            DtoListTeamsResponse response = result.stream()
                    .filter(team -> team.getId().equals(dbTeam.getId()))
                    .findFirst().get();

            assertThat(response).isNotNull();
            assertThat(dbTeam.getId()).isEqualTo(response.getId());
            assertThat(dbTeam.getName()).isEqualTo(response.getName());
            assertThat(dbTeam.getFlagColor1()).isEqualTo(response.getFlagColor1());
            assertThat(dbTeam.getFlagColor2()).isEqualTo(response.getFlagColor2());
        });
    }
}
