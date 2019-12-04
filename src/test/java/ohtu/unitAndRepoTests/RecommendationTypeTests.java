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

import ohtu.database.entities.recommendations.RecommendationType;

@RunWith(SpringRunner.class)
@SpringBootTest()
@TestPropertySource(
        locations = "classpath:application-test.properties")
@Transactional
public class RecommendationTypeTests {

    private RecommendationType test;
    
    @Before
    public void setUp() {
    }

    @Test
    public void toStringIsOk() {
        test = RecommendationType.BOOK; 
        assertEquals("Kirja", test.toString());
    }


}

