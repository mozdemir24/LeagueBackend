package league.demo.coach;

import com.fasterxml.jackson.databind.ObjectMapper;
import league.demo.model.Coach;
import league.demo.model.FlagColor;
import league.demo.model.Team;
import league.demo.team.TeamRepository;
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
public class CoachBaseTest {

    @Autowired
    protected MockMvc mvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected CoachRepository coachRepository;

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
                "Besiktas",
                1903,
                FlagColor.BLACK,
                FlagColor.WHITE);

        List<Team> dbTeams = new ArrayList<>();
        dbTeams.add(team1);
        dbTeams.add(team2);
        dbTeams.add(team3);
        teamRepository.saveAll(dbTeams);

        Coach coach1 = new Coach(
                "Ersun",
                "Yanal",
                team1);
        Coach coach2 = new Coach(
                "Fatih",
                "Terim",
                team2);

        List<Coach> dbCoaches = new ArrayList<>();
        dbCoaches.add(coach1);
        dbCoaches.add(coach2);
        coachRepository.saveAll(dbCoaches);

        return new TestContext(dbTeams, dbCoaches);
    }

    class TestContext {
        public List<Team> dbTeams;
        public List<Coach> dbCoaches;

        public TestContext(List<Team> dbTeams, List<Coach> dbCoaches) {
            this.dbTeams = dbTeams;
            this.dbCoaches = dbCoaches;
        }
    }
}
