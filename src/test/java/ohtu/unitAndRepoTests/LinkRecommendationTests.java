package ohtu.unitAndRepoTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import ohtu.database.entities.data.Course;
import ohtu.database.entities.recommendations.LinkRecommendation;
import ohtu.database.entities.recommendations.Recommendation;
import ohtu.database.entities.recommendations.RecommendationType;
import ohtu.database.repositories.RecommendationRepository;

@RunWith(SpringRunner.class)
@SpringBootTest()
@TestPropertySource(
  locations = "classpath:application-test.properties")
@Transactional
public class LinkRecommendationTests {

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
                "title", new HashMap<>(), new ArrayList<>(), "http://www.urlijokaeitoimi.fi");
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
        assertTrue(linkRecommendation.hasCourse(oneCourse));
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
        assertTrue(Helpers.areSimilar(savedLink.getCourses(), savedLink.getCourses()));
        assertEquals(linkRecommendation.getTags(), savedLink.getTags());
    }

    @Test
    public void linkRecommendationAsStringIsOk() {
        LinkRecommendation linkRecommendation = new LinkRecommendation();
        linkRecommendation.setUrl("url");
        linkRecommendation.setTitle("title");

        ArrayList<Course> courses = new ArrayList<>();
        Course oneCourse = new Course("tkt101", "", new ArrayList<Recommendation>());
        courses.add(oneCourse);
        linkRecommendation.setCourses(courses);

        assertTrue(linkRecommendation.toString().equals("title: url"));
    }

    @Test
    public void getTypeLink() {
        LinkRecommendation linkRecommendation = new LinkRecommendation();
        linkRecommendation.setUrl("url");
        linkRecommendation.setTitle("title");

        assertTrue(linkRecommendation.getType() == RecommendationType.LINKKI);
    }

    @Test
    public void linkRecommendationsAreSame() {
        LinkRecommendation linkRecommendationA = new LinkRecommendation();
        assertTrue(linkRecommendationA.equals(linkRecommendationA));
    }

    @Test
    public void linkRecommendationsAreEquals() {
        LinkRecommendation linkRecommendationA = new LinkRecommendation();
        linkRecommendationA.setUrl("url");
        linkRecommendationA.setTitle("title");

        LinkRecommendation linkRecommendationB = new LinkRecommendation();
        linkRecommendationB.setUrl("url");
        linkRecommendationB.setTitle("title");

        assertTrue(linkRecommendationA.equals(linkRecommendationB));
        assertTrue(linkRecommendationB.equals(linkRecommendationA));
    }

    @Test
    public void linkRecommendationsAreNotEquals() {
        LinkRecommendation linkRecommendationA = new LinkRecommendation();
        linkRecommendationA.setUrl("url");
        linkRecommendationA.setTitle("title");

        LinkRecommendation linkRecommendationB = new LinkRecommendation();
        linkRecommendationB.setUrl("urt");
        linkRecommendationB.setTitle("title");

        assertFalse(linkRecommendationA.equals(linkRecommendationB));
        assertFalse(linkRecommendationB.equals(linkRecommendationA));
    }

    @Test
    public void linkRecommendationHashCodesAreEquals() {
        LinkRecommendation linkRecommendationA = new LinkRecommendation();
        linkRecommendationA.setUrl("url");
        linkRecommendationA.setTitle("title");

        LinkRecommendation linkRecommendationB = new LinkRecommendation();
        linkRecommendationB.setUrl("url");
        linkRecommendationB.setTitle("title");

        assertTrue(linkRecommendationA.hashCode() == linkRecommendationA.hashCode());
        assertTrue(linkRecommendationA.hashCode() == linkRecommendationB.hashCode());
    }

    @Test
    public void linkRecommendationHashCodesAreNotEquals() {
        LinkRecommendation linkRecommendationA = new LinkRecommendation();
        linkRecommendationA.setUrl("url");
        linkRecommendationA.setTitle("title");

        LinkRecommendation linkRecommendationB = new LinkRecommendation();
        linkRecommendationB.setUrl("urt");
        linkRecommendationB.setTitle("title");

        assertFalse(linkRecommendationA.hashCode() == linkRecommendationB.hashCode());
    }
}
