package ohtu.unitAndRepoTests;

import java.util.ArrayList;
import javax.transaction.Transactional;

import ohtu.database.entities.data.Course;
import ohtu.database.entities.recommendations.LinkRecommendation;
import ohtu.database.entities.recommendations.Recommendation;
import ohtu.database.repositories.RecommendationRepository;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

//Roni
@RunWith(SpringRunner.class)
@SpringBootTest()
@TestPropertySource(
  locations = "classpath:application-test.properties")
@Transactional
public class linkRecommendationTests {

    @Autowired
    private RecommendationRepository recommendationRepository;

    @Before
    public void setUp() {
    }

    @Test
    public void linksCanBeCreatedEmptyConstructor() {
        LinkRecommendation linkRecommendation = new LinkRecommendation();
        assertNotNull(linkRecommendation);
    }

    @Test
    public void linksCanBeCreatedNonEmptyConstructor() {
        LinkRecommendation linkRecommendation = new LinkRecommendation(
                "title", new ArrayList<>(), new ArrayList<>(), "http://www.urlijokaeitoimi.fi");
        assertNotNull(linkRecommendation);
        assertEquals(linkRecommendation.getUrl(), "http://www.urlijokaeitoimi.fi");
    }

    @Test
    public void linksValuesCanBeSetAndGotten() {
        LinkRecommendation linkRecommendation = new LinkRecommendation();
        linkRecommendation.setUrl("url");
        linkRecommendation.setTitle("title");

        ArrayList<Course> courses = new ArrayList<>();
        Course oneCourse = new Course("tkt101", "", new ArrayList<Recommendation>());
        courses.add(oneCourse);
        linkRecommendation.setCourses(courses);

        ArrayList<String> tags = new ArrayList<>();
        tags.add("educational");
        linkRecommendation.setTags(tags);

        assertEquals(linkRecommendation.getUrl(), "url");
        assertTrue(linkRecommendation.getCourses().contains(oneCourse));
        assertTrue(linkRecommendation.getTags().contains("educational"));
        assertEquals(linkRecommendation.getTitle(), "title");
    }

    @Test
    public void linksCanBeSavedAndRetrievedFromRepo(){
        LinkRecommendation linkRecommendation = new LinkRecommendation();
        linkRecommendation.setUrl("url");
        linkRecommendation.setTitle("title");

        ArrayList<Course> courses = new ArrayList<>();
        Course oneCourse = new Course("tkt101", "", new ArrayList<Recommendation>());
        courses.add(oneCourse);
        linkRecommendation.setCourses(courses);

        ArrayList<String> tags = new ArrayList<>();
        tags.add("educational");
        linkRecommendation.setTags(tags);

        LinkRecommendation savedLink = recommendationRepository.save(linkRecommendation);
        Long id = savedLink.getId();
        savedLink = (LinkRecommendation) recommendationRepository.getOne(id);
        assertEquals(linkRecommendation.getUrl(), savedLink.getUrl());
        assertEquals(linkRecommendation.getTitle(), savedLink.getTitle());
        assertEquals(linkRecommendation.getCourses(), savedLink.getCourses());
        assertEquals(linkRecommendation.getTags(), savedLink.getTags());
    }
}
