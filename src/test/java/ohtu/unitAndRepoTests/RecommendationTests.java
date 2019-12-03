package ohtu.unitAndRepoTests;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import ohtu.database.entities.recommendations.Recommendation;

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

