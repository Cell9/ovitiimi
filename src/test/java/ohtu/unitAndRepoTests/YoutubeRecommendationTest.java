package ohtu.unitAndRepoTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import ohtu.database.entities.data.Course;
import ohtu.database.entities.recommendations.Recommendation;
import ohtu.database.entities.recommendations.RecommendationType;
import ohtu.database.entities.recommendations.YoutubeRecommendation;
import ohtu.database.repositories.RecommendationRepository;

@RunWith(SpringRunner.class)
@SpringBootTest()
@TestPropertySource(
  locations = "classpath:application-test.properties")
@Transactional
public class YoutubeRecommendationTest {
    
    @Autowired
    private RecommendationRepository recommendationRepository;

    private YoutubeRecommendation youtubeRecommendation;

    @Before
    public void setUp() {
        this.youtubeRecommendation = new YoutubeRecommendation(
                "Sarasvuo", new HashMap<>(), new ArrayList<>(),
                "Yle Puhe", "https://www.yle.fi/jotain", "coaching");
    }

    @Test
    public void youtubesCanBeCreatedEmptyConstructor() {
        YoutubeRecommendation youtube = new YoutubeRecommendation();
        assertNotNull(youtube);
    }

    @Test
    public void youtubeAttributesCanBeSetAndGot() {
        YoutubeRecommendation youtube = new YoutubeRecommendation();
        youtube.setTitle("Sarasvuo");
        youtube.setAuthor("Yle Puhe");
        youtube.setDescription("coaching");
        youtube.addTag("puhe");
        assertEquals(youtube.getTitle(), "Sarasvuo");
        assertEquals(youtube.getAuthor(), "Yle Puhe");
        assertEquals(youtube.getDescription(), "coaching");

        List<String> tags = youtube.getTags();
        assertEquals(tags.get(0), "puhe");
    }

    @Test
    public void youtubeCourseCanBeSetAndGet() {
        YoutubeRecommendation youtube = new YoutubeRecommendation();

        Collection<Course> courses = new ArrayList<>();
        Course oneCourse = new Course("tkt101", "", new ArrayList<Recommendation>());
        courses.add(oneCourse);
        youtube.setCourses(courses);

        courses = youtube.getCourses();
        assertTrue(youtube.hasCourse(oneCourse));
    }

    @Test
    public void youtubeRecommendationAttributesCanBeGet() {
        YoutubeRecommendation youtubeRecommendation = new YoutubeRecommendation();
        youtubeRecommendation.setTitle("Sarasvuo");
        youtubeRecommendation.setAuthor("Yle Puhe");
        youtubeRecommendation.setDescription("coaching");
        youtubeRecommendation.setUrl("url");

        assertEquals(youtubeRecommendation.getTitle(), "Sarasvuo");
        assertEquals(youtubeRecommendation.getType(), RecommendationType.YOUTUBE);
        assertEquals(youtubeRecommendation.getTitle(), "Sarasvuo");
        assertEquals(youtubeRecommendation.getUrl(), "url");
        assertEquals(youtubeRecommendation.toString(), "Sarasvuo, Yle Puhe, coaching");
    }

    @Test
    public void youtubesCanBeCreatedNonEmptyConstructor() {
        YoutubeRecommendation youtube = new YoutubeRecommendation(
                "Sarasvuo", new HashMap<>(), new ArrayList<>(),
                "Yle Puhe", "https://www.yle.fi/jotain", "coaching");
        assertNotNull(youtube);
        assertEquals(youtube.getTitle(), "Sarasvuo");
    }

    @Test
    public void youtubesValuesCanBeSetAndGotten() {
        YoutubeRecommendation youtube = new YoutubeRecommendation();
        youtube.setAuthor("author");
        youtube.setDescription("description");
        youtube.setTitle("title");

        ArrayList<Course> courses = new ArrayList<>();
        Course oneCourse = new Course("tkt101", "", new ArrayList<Recommendation>());
        courses.add(oneCourse);
        youtube.setCourses(courses);

        ArrayList<String> tags = new ArrayList<>();
        tags.add("educational");
        youtube.setTags(tags);

        assertEquals(youtube.getAuthor(), "author");
        assertTrue(youtube.hasCourse(oneCourse));
        assertTrue(youtube.getTags().contains("educational"));
        assertEquals(youtube.getTitle(), "title");
        assertEquals(youtube.getDescription(), "description");
    }

    @Test
    public void youtubesCanBeSavedAndRetrievedFromRepo(){
        YoutubeRecommendation youtube = new YoutubeRecommendation();
        youtube.setAuthor("author");
        youtube.setDescription("description");
        youtube.setTitle("title");

        ArrayList<Course> courses = new ArrayList<>();
        Course oneCourse = new Course("tkt101", "", new ArrayList<Recommendation>());
        courses.add(oneCourse);
        youtube.setCourses(courses);

        ArrayList<String> tags = new ArrayList<>();
        tags.add("educational");
        youtube.setTags(tags);

        YoutubeRecommendation savedyoutube = recommendationRepository.save(youtube);
        Long id = savedyoutube.getId();
        savedyoutube = (YoutubeRecommendation) recommendationRepository.getOne(id);
        assertEquals(youtube.getAuthor(), savedyoutube.getAuthor());
        assertEquals(youtube.getTitle(), savedyoutube.getTitle());
        assertEquals(youtube.getDescription(), savedyoutube.getDescription());
        assertTrue(Helpers.areSimilar(youtube.getCourses(), savedyoutube.getCourses()));
        assertEquals(youtube.getTags(), savedyoutube.getTags());
    }

    @Test
    public void youtubeRecommendationAsStringIsOk() {
        YoutubeRecommendation youtube = new YoutubeRecommendation();
        youtube.setAuthor("author");
        youtube.setDescription("description");
        youtube.setTitle("title");

        assertTrue(youtube.toString().equals("title, author, description"));
    }

    @Test
    public void getTypeYoutube() {
        YoutubeRecommendation youtube = new YoutubeRecommendation();
        youtube.setAuthor("author");
        youtube.setDescription("description");
        youtube.setTitle("title");

        assertTrue(youtube.getType() == RecommendationType.YOUTUBE);
    }

    @Test
    public void youtubeRecommendationsAreSame() {
        YoutubeRecommendation youtube = new YoutubeRecommendation();
        assertTrue(youtube.equals(youtube));
    }

    @Test
    public void youtubeRecommendationsAreEquals() {
        YoutubeRecommendation youtubeA = new YoutubeRecommendation();
        youtubeA.setAuthor("author");
        youtubeA.setDescription("description");
        youtubeA.setTitle("title");

        YoutubeRecommendation youtubeB = new YoutubeRecommendation();
        youtubeB.setAuthor("author");
        youtubeB.setDescription("description");
        youtubeB.setTitle("title");

        assertTrue(youtubeA.equals(youtubeB));
        assertTrue(youtubeB.equals(youtubeA));
    }

    @Test
    public void youtubeRecommendationsAreNotEquals() {
        YoutubeRecommendation youtubeA = new YoutubeRecommendation();
        youtubeA.setAuthor("authorA");
        youtubeA.setDescription("description");
        youtubeA.setTitle("title");

        YoutubeRecommendation youtubeB = new YoutubeRecommendation();
        youtubeB.setAuthor("authorB");
        youtubeB.setDescription("description");
        youtubeB.setTitle("title");

        assertFalse(youtubeA.equals(youtubeB));
        assertFalse(youtubeB.equals(youtubeA));
    }

    @Test
    public void youtubeRecommendationHashCodesAreEquals() {
        YoutubeRecommendation youtubeA = new YoutubeRecommendation();
        youtubeA.setAuthor("authorA");
        youtubeA.setDescription("description");
        youtubeA.setTitle("title");

        YoutubeRecommendation youtubeB = new YoutubeRecommendation();
        youtubeB.setAuthor("authorA");
        youtubeB.setDescription("description");
        youtubeB.setTitle("title");

        assertTrue(youtubeA.hashCode() == youtubeA.hashCode());
        assertTrue(youtubeA.hashCode() == youtubeB.hashCode());
    }

    @Test
    public void youtubeRecommendationHashCodesAreNotEquals() {
        YoutubeRecommendation youtubeA = new YoutubeRecommendation();
        youtubeA.setAuthor("authorA");
        youtubeA.setDescription("description");
        youtubeA.setTitle("title");

        YoutubeRecommendation youtubeB = new YoutubeRecommendation();
        youtubeB.setAuthor("authorB");
        youtubeB.setDescription("description");
        youtubeB.setTitle("title");

        assertFalse(youtubeA.hashCode() == youtubeB.hashCode());
    }
    
}
