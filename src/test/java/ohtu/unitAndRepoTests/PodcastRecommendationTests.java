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
public class PodcastRecommendationTests {

    @Autowired
    private RecommendationRepository recommendationRepository;

    private PodcastRecommendation podcastRecommendation;

    @Before
    public void setUp() {
        this.podcastRecommendation = new PodcastRecommendation(
                "Sarasvuo", new ArrayList<>(), new ArrayList<>(),
                "Yle Puhe", "https://www.yle.fi/jotain", "coaching");
    }

    @Test
    public void podcastsCanBeCreatedEmptyConstructor() {
        PodcastRecommendation podcast = new PodcastRecommendation();
        assertNotNull(podcast);
    }

    @Test
    public void podcastAttributesCanBeSetAndGot() {
        PodcastRecommendation podcast = new PodcastRecommendation();
        podcast.setTitle("Sarasvuo");
        podcast.setAuthor("Yle Puhe");
        podcast.setDescription("coaching");
        podcast.addTag("puhe");
        assertEquals(podcast.getTitle(), "Sarasvuo");
        assertEquals(podcast.getAuthor(), "Yle Puhe");
        assertEquals(podcast.getDescription(), "coaching");

        List<String> tags = podcast.getTags();
        assertEquals(tags.get(0), "puhe");
    }

    @Test
    public void podcastCourseCanBeSetAndGet() {
        PodcastRecommendation podcast = new PodcastRecommendation();

        List<Course> courses = new ArrayList<>();
        Course oneCourse = new Course("tkt101", "", new ArrayList<Recommendation>());
        courses.add(oneCourse);
        podcast.setCourses(courses);

        courses = podcast.getCourses();
        assertEquals(courses.get(0).getCode(), "tkt101");
    }

    @Test
    public void podcastRecommendationAttributesCanBeGet() {
        PodcastRecommendation podcastRecommendation = new PodcastRecommendation();
        podcastRecommendation.setTitle("Sarasvuo");
        podcastRecommendation.setAuthor("Yle Puhe");
        podcastRecommendation.setDescription("coaching");
        podcastRecommendation.setUrl("url");

        assertEquals(podcastRecommendation.getTitle(), "Sarasvuo");
        assertEquals(podcastRecommendation.getType(), RecommendationType.PODCAST);
        assertEquals(podcastRecommendation.getTitle(), "Sarasvuo");
        assertEquals(podcastRecommendation.getUrl(), "url");
        assertEquals(podcastRecommendation.toString(), "Sarasvuo, Yle Puhe, coaching");
    }

    @Test
    public void podcastsCanBeCreatedNonEmptyConstructor() {
        PodcastRecommendation podcast = new PodcastRecommendation(
                "Sarasvuo", new ArrayList<>(), new ArrayList<>(),
                "Yle Puhe", "https://www.yle.fi/jotain", "coaching");
        assertNotNull(podcast);
        assertEquals(podcast.getTitle(), "Sarasvuo");
    }

    @Test
    public void podcastsValuesCanBeSetAndGotten() {
        PodcastRecommendation podcast = new PodcastRecommendation();
        podcast.setAuthor("author");
        podcast.setDescription("description");
        podcast.setTitle("title");

        ArrayList<Course> courses = new ArrayList<>();
        Course oneCourse = new Course("tkt101", "", new ArrayList<Recommendation>());
        courses.add(oneCourse);
        podcast.setCourses(courses);

        ArrayList<String> tags = new ArrayList<>();
        tags.add("educational");
        podcast.setTags(tags);

        assertEquals(podcast.getAuthor(), "author");
        assertTrue(podcast.getCourses().contains(oneCourse));
        assertTrue(podcast.getTags().contains("educational"));
        assertEquals(podcast.getTitle(), "title");
        assertEquals(podcast.getDescription(), "description");
    }

    @Test
    public void podcastsCanBeSavedAndRetrievedFromRepo(){
        PodcastRecommendation podcast = new PodcastRecommendation();
        podcast.setAuthor("author");
        podcast.setDescription("description");
        podcast.setTitle("title");

        ArrayList<Course> courses = new ArrayList<>();
        Course oneCourse = new Course("tkt101", "", new ArrayList<Recommendation>());
        courses.add(oneCourse);
        podcast.setCourses(courses);

        ArrayList<String> tags = new ArrayList<>();
        tags.add("educational");
        podcast.setTags(tags);

        PodcastRecommendation savedpodcast = recommendationRepository.save(podcast);
        Long id = savedpodcast.getId();
        savedpodcast = (PodcastRecommendation) recommendationRepository.getOne(id);
        assertEquals(podcast.getAuthor(), savedpodcast.getAuthor());
        assertEquals(podcast.getTitle(), savedpodcast.getTitle());
        assertEquals(podcast.getDescription(), savedpodcast.getDescription());
        assertEquals(podcast.getCourses(), savedpodcast.getCourses());
        assertEquals(podcast.getTags(), savedpodcast.getTags());
    }

    @Test
    public void podcastRecommendationAsStringIsOk() {
        PodcastRecommendation podcast = new PodcastRecommendation();
        podcast.setAuthor("author");
        podcast.setDescription("description");
        podcast.setTitle("title");

        assertTrue(podcast.toString().equals("title, author, description"));
    }

    @Test
    public void getTypePodcast() {
        PodcastRecommendation podcast = new PodcastRecommendation();
        podcast.setAuthor("author");
        podcast.setDescription("description");
        podcast.setTitle("title");

        assertTrue(podcast.getType() == RecommendationType.PODCAST);
    }

    @Test
    public void podcastRecommendationsAreSame() {
        PodcastRecommendation podcast = new PodcastRecommendation();
        assertTrue(podcast.equals(podcast));
    }

    @Test
    public void podcastRecommendationsAreEquals() {
        PodcastRecommendation podcastA = new PodcastRecommendation();
        podcastA.setAuthor("author");
        podcastA.setDescription("description");
        podcastA.setTitle("title");

        PodcastRecommendation podcastB = new PodcastRecommendation();
        podcastB.setAuthor("author");
        podcastB.setDescription("description");
        podcastB.setTitle("title");

        assertTrue(podcastA.equals(podcastB));
        assertTrue(podcastB.equals(podcastA));
    }

    @Test
    public void podcastRecommendationsAreNotEquals() {
        PodcastRecommendation podcastA = new PodcastRecommendation();
        podcastA.setAuthor("authorA");
        podcastA.setDescription("description");
        podcastA.setTitle("title");

        PodcastRecommendation podcastB = new PodcastRecommendation();
        podcastB.setAuthor("authorB");
        podcastB.setDescription("description");
        podcastB.setTitle("title");

        assertFalse(podcastA.equals(podcastB));
        assertFalse(podcastB.equals(podcastA));
    }

    @Test
    public void podcastRecommendationHashCodesAreEquals() {
        PodcastRecommendation podcastA = new PodcastRecommendation();
        podcastA.setAuthor("authorA");
        podcastA.setDescription("description");
        podcastA.setTitle("title");

        PodcastRecommendation podcastB = new PodcastRecommendation();
        podcastB.setAuthor("authorA");
        podcastB.setDescription("description");
        podcastB.setTitle("title");

        assertTrue(podcastA.hashCode() == podcastA.hashCode());
        assertTrue(podcastA.hashCode() == podcastB.hashCode());
    }

    @Test
    public void podcastRecommendationHashCodesAreNotEquals() {
        PodcastRecommendation podcastA = new PodcastRecommendation();
        podcastA.setAuthor("authorA");
        podcastA.setDescription("description");
        podcastA.setTitle("title");

        PodcastRecommendation podcastB = new PodcastRecommendation();
        podcastB.setAuthor("authorB");
        podcastB.setDescription("description");
        podcastB.setTitle("title");

        assertFalse(podcastA.hashCode() == podcastB.hashCode());
    }
}
