package ohtu.unitAndRepoTests;

import javax.transaction.Transactional;

import ohtu.database.entities.data.Course;
import ohtu.database.repositories.CourseRepository;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest()
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
public class courseTests {

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
    public void coursesCanBePrintedProperly() {
        Course course = new Course();
        course.setCode("code");
        course.setName("name");
        assertEquals(course.toString(), "code name");
    }
}
