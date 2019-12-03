package ohtu.unitAndRepoTests;


import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import ohtu.database.entities.data.Course;

import ohtu.database.entities.data.Course;
import ohtu.database.entities.recommendations.PodcastRecommendation;
import ohtu.database.entities.recommendations.Recommendation;
import ohtu.database.entities.recommendations.RecommendationType;
import ohtu.database.repositories.RecommendationRepository;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest()
@TestPropertySource(
        locations = "classpath:application-test.properties")
@Transactional
public class RecommendationTests {

    private Recommendation recommendationA;
    private Recommendation recommendationB;

    @Before
    public void setUp() {
        recommendationA = Mockito.spy(Recommendation.class);
        recommendationB = Mockito.spy(Recommendation.class);
    }

    @Test
    public void recommendationsStringIsOk() {
        recommendationA.setTitle("title");
        assertEquals("title", recommendationA.toString());
    }

    @Test
    public void recommendationsAreSame() {
        recommendationA.setTitle("title");
        assertTrue(recommendationA.equals(recommendationA));
    }
}

