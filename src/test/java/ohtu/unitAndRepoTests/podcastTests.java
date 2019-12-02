package ohtu.unitAndRepoTests;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import ohtu.database.entities.data.Course;

import ohtu.database.entities.data.Podcast;
import ohtu.database.repositories.PodcastRepository;
import ohtu.database.entities.recommendations.PodcastRecommendation;
import ohtu.database.entities.recommendations.RecommendationType;

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
public class podcastTests {
    
    @Autowired
    private PodcastRepository podcastrepo;
    
    private Podcast podcast;
    
    @Before
    public void setUp() {
        this.podcast = new Podcast("Sarasvuo", "Yle Puhe", "coaching");
    }

    @Test
    public void podcastsCanBeCreatedEmptyConstructor() {
        Podcast podcast = new Podcast();
        assertNotNull(podcast);
    }
        
    @Test
    public void podcastAttributesCanBeSetAndGot() {
        Podcast podcast = new Podcast();
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
        Podcast podcast = new Podcast();
        Course course = new Course("code", "name");
        podcast.addCourse(course);
        List<Course> courses = podcast.getCourses();
        assertEquals(courses.get(0).getCode(), "code");        
    }
    
    @Test
    public void podcastRecommendationAttributesCanBeGet() {
//        Podcast podcast = new Podcast("Sarasvuo", "Yle Puhe", "coaching");
        PodcastRecommendation podcastRecommendation = new PodcastRecommendation();
        podcastRecommendation.setTitle("Sarasvuo");
        podcastRecommendation.setAuthor("Yle Puhe");
        podcastRecommendation.setDescription("coaching");
        
        assertEquals(podcastRecommendation.getTitle(), "Sarasvuo");
        assertEquals(podcastRecommendation.getType(), RecommendationType.PODCAST);
        assertEquals(podcastRecommendation.getTitle(), "Sarasvuo");
        assertEquals(podcastRecommendation.toString(), "Sarasvuo, Yle Puhe, coaching");
    }

/*
    @Test
    public void podcastsCanBeCreatedNonEmptyConstructor() {
        Podcast podcast = new Podcast("author", "title", "description", new ArrayList<>(), new ArrayList<>());
        assertNotNull(podcast);
        assertEquals(podcast.getTitle(), "title");
    }

    @Test
    public void podcastsValuesCanBeSetAndGotten() {
        Podcast podcast = new Podcast();
        podcast.setAuthor("author");
        podcast.setDescription("description");
        podcast.setTitle("title");
        ArrayList<String> courses = new ArrayList<>();
        courses.add("tkt101");
        podcast.setCourses(courses);
        ArrayList<String> tags = new ArrayList<>();
        tags.add("educational");
        podcast.setTags(tags);
        assertEquals(podcast.getAuthor(), "author");
        assertTrue(podcast.getCourses().contains("tkt101"));
        assertTrue(podcast.getTags().contains("educational"));
        assertEquals(podcast.getTitle(), "title");
        assertEquals(podcast.getDescription(), "description");
    }
    
    @Test
    public void podcastsCanBeSavedAndRetrievedFromRepo(){
        Podcast podcast = new Podcast();
        podcast.setAuthor("author");
        podcast.setDescription("isbn");
        podcast.setTitle("title");
        ArrayList<String> courses = new ArrayList<>();
        courses.add("tkt101");
        podcast.setCourses(courses);
        ArrayList<String> tags = new ArrayList<>();
        tags.add("educational");
        podcast.setTags(tags);
        Podcast savedpodcast = podcastrepo.save(podcast);
        Long id = savedpodcast.getId();
        savedpodcast = podcastrepo.getOne(id);
        assertEquals(podcast.getAuthor(), savedpodcast.getAuthor());
        assertEquals(podcast.getTitle(), savedpodcast.getTitle());
        assertEquals(podcast.getDescription(), savedpodcast.getDescription());
        assertEquals(podcast.getCourses(), savedpodcast.getCourses());
        assertEquals(podcast.getTags(), savedpodcast.getTags());
    }*/
}
