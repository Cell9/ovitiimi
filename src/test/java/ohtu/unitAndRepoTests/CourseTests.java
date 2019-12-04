package ohtu.unitAndRepoTests;

import javax.transaction.Transactional;

import ohtu.database.entities.data.Course;
import ohtu.database.entities.recommendations.PodcastRecommendation;
import ohtu.database.entities.recommendations.Recommendation;
import ohtu.database.repositories.CourseRepository;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest()
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
public class CourseTests {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void coursesCanBeCreatedEmptyConstructor() {
        Course course = new Course();
        assertNotNull(course);
    }

    @Test
    public void coursesCanBeCreatedFullConstructor() {
        Course course = new Course("code", "name");
        assertNotNull(course);
        assertEquals(course.getCode(), "code");
        assertEquals(course.getName(), "name");
    }

    @Test
    public void courseAttributesCanBeSetAndGot() {
        Course course = new Course();
        course.setCode("code");
        course.setName("name");
        assertEquals(course.getCode(), "code");
        assertEquals(course.getName(), "name");
    }
    
    @Test
    public void coursesCanBeSavedInAndRetrievedFromRepository() {
        Course course = new Course();
        course.setCode("code");
        course.setName("name");
        Course savedCourse = courseRepository.save(course);
        savedCourse = courseRepository.getOne(savedCourse.getId());
        assertEquals(course.getCode(), savedCourse.getCode());
        assertEquals(course.getName(), savedCourse.getName());
    }
    
    @Test
    public void courseAsStringIsOk() {
        Course course = new Course();
        course.setCode("001kurssi");
        course.setName("kurssin nimi");

        assertTrue(course.toString().equals("kurssin nimi (001kurssi)"));
    }

    @Test
    public void courseRecommendationCanBeSet() {
        Course course = new Course();
        course.setCode("001kurssi");
        course.setName("kurssin nimi");
        List<Recommendation> list = new ArrayList<>();
        PodcastRecommendation podcast = new PodcastRecommendation();
        podcast.setTitle("Sarasvuo");
        podcast.setAuthor("Yle Puhe");
        list.add(podcast);
        course.setRecommendations(list);

        assertTrue(course.getRecommendations().contains(podcast));
    }

    @Test
    public void coursesAreSame() {
        Course course = new Course();
        assertTrue(course.areSimilar(course));
    }

    @Test
    public void coursesAreEquals() {
        Course courseA = new Course();
        courseA.setCode("001kurssi");
        courseA.setName("kurssin nimi");

        Course courseB = new Course();
        courseB.setCode("001kurssi");
        courseB.setName("kurssin nimi");

        assertTrue(courseA.areSimilar(courseB));
        assertTrue(courseB.areSimilar(courseA));
    }

    @Test
    public void coursesAreNotEquals() {
        Course courseA = new Course();
        courseA.setCode("001kurssi");
        courseA.setName("kurssin nimi");

        Course courseB = new Course();
        courseB.setCode("002kurssi");
        courseB.setName("kurssin nimi");

        assertFalse(courseA.areSimilar(courseB));
        assertFalse(courseB.areSimilar(courseA));
    }
}
