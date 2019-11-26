package league.demo.player;

import com.fasterxml.jackson.databind.ObjectMapper;
import league.demo.model.FlagColor;
import league.demo.model.Player;
import league.demo.model.Team;
import league.demo.team.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static java.sql.Date.valueOf;

@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PlayerBaseTest {

    @Autowired
    protected MockMvc mvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected PlayerRepository playerRepository;

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

        List<Team> dbTeams = new ArrayList<>();
        dbTeams.add(team1);
        dbTeams.add(team2);
        teamRepository.saveAll(dbTeams);

        Player player1 = new Player(
                "Vedat",
                "Muriqi",
                94,
                valueOf("1994-02-10"),
                false,
                2000000.00,
                team1);
        Player player3 = new Player(
                "Altay",
                "Bayindir",
                98,
                valueOf("1998-03-12"),
                false,
                1000000.00,
                team1);

        Player player2 = new Player(
                "Radamel",
                "Falcao",
                9,
                valueOf("1988-05-12"),
                true,
                5000000.00,
                team2);

        List<Player> dbPlayers = new ArrayList<>();
        dbPlayers.add(player1);
        dbPlayers.add(player2);
        dbPlayers.add(player3);
        playerRepository.saveAll(dbPlayers);

        return new TestContext(dbTeams, dbPlayers);
    }

    class TestContext {
        public List<Team> dbTeams;
        public List<Player> dbPlayers;

        public TestContext(List<Team> dbTeams, List<Player> dbPlayers) {
            this.dbTeams = dbTeams;
            this.dbPlayers = dbPlayers;
        }
    }
}
