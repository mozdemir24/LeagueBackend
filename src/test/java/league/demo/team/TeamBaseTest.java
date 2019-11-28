package league.demo.team;

import com.fasterxml.jackson.databind.ObjectMapper;
import league.demo.entity.FlagColor;
import league.demo.entity.Team;
import league.demo.module.team.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TeamBaseTest {

    @Autowired
    protected MockMvc mvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected TeamRepository teamRepository;

    protected TestContext prepareFixtures() {
        Team team1 = new Team(
                "Fenerbahce",
                1907,
                FlagColor.YELLOW,
                FlagColor.BLUE);

        Team team2 = new Team(
                "Galatasaray",
                1905,
                FlagColor.YELLOW,
                FlagColor.RED);

        Team team3 = new Team(
                "Bjk",
                1903,
                FlagColor.BLACK,
                FlagColor.WHITE);

        List<Team> dbTeams = new ArrayList<>();
        dbTeams.add(team1);
        dbTeams.add(team2);
        dbTeams.add(team3);
        teamRepository.saveAll(dbTeams);
        return new TestContext(dbTeams);
    }

    class TestContext {
        public List<Team> dbTeams;

        public TestContext(List<Team> dbTeams) {
            this.dbTeams = dbTeams;
        }
    }

}
