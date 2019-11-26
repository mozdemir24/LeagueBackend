package league.demo.coach;

import league.demo.coach.dto.DtoListAllCoaches;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CoachGetAllTest extends CoachBaseTest {

    @Test
    public void canGetAll() throws Exception {
        TestContext testContext = prepareFixtures();

        MvcResult mvcResult = mvc.perform(get("/coach/list"))
                .andExpect(status().isOk())
                .andReturn();

        List<DtoListAllCoaches> result = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, DtoListAllCoaches.class));

        assertThat(testContext.dbCoaches.size()).isEqualTo(result.size());


        testContext.dbCoaches.forEach(dbCoach -> {
            DtoListAllCoaches response = result.stream()
                    .filter(coach -> coach.getId().equals(dbCoach.getId()))
                    .findFirst().get();

            assertThat(response).isNotNull();
            Assertions.assertThat(dbCoach.getId()).isEqualTo(response.getId());
            Assertions.assertThat(dbCoach.getName()).isEqualTo(response.getName());
            Assertions.assertThat(dbCoach.getSurname()).isEqualTo(response.getSurname());
            Assertions.assertThat(dbCoach.getTeam().getName()).isEqualTo(response.getTeamName());
        });
    }
}
