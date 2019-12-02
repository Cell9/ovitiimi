
package ohtu.unitAndRepoTests;

import java.util.ArrayList;
import javax.transaction.Transactional;

import ohtu.database.entities.data.Course;
import ohtu.database.entities.recommendations.BookRecommendation;
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

@RunWith(SpringRunner.class)
@SpringBootTest()
@TestPropertySource(
  locations = "classpath:application-test.properties")
@Transactional
public class bookRecommendationTests {

    @Autowired
    private RecommendationRepository recommendationRepository;

    @Before
    public void setUp() {
    }

   @Test
    public void booksCanBeCreatedEmptyConstructor() {
        BookRecommendation bookRecommendation = new BookRecommendation();
        assertNotNull(bookRecommendation);
    }

    @Test
    public void booksCanBeCreatedNonEmptyConstructor() {
        BookRecommendation bookRecommendation = new BookRecommendation(
                "title", new ArrayList<>(), new ArrayList<>(), "author", "isbn");
        assertNotNull(bookRecommendation);
        assertEquals(bookRecommendation.getTitle(), "title");
    }

    @Test
    public void booksValuesCanBeSetAndGotten() {
        BookRecommendation bookRecommendation = new BookRecommendation();
        bookRecommendation.setAuthor("author");
        bookRecommendation.setIsbn("isbn");
        bookRecommendation.setTitle("title");

        ArrayList<Course> courses = new ArrayList<>();
        Course oneCourse = new Course("tkt101", "", new ArrayList<Recommendation>());
        courses.add(oneCourse);
        bookRecommendation.setCourses(courses);

        ArrayList<String> tags = new ArrayList<>();
        tags.add("educational");
        bookRecommendation.setTags(tags);

        assertEquals(bookRecommendation.getAuthor(), "author");
        assertTrue(bookRecommendation.getCourses().contains(oneCourse));
        assertTrue(bookRecommendation.getTags().contains("educational"));
        assertEquals(bookRecommendation.getTitle(), "title");
        assertEquals(bookRecommendation.getIsbn(), "isbn");
    }

    @Test
    public void booksCanBeSavedAndRetrievedFromRepo(){
        BookRecommendation bookRecommendation = new BookRecommendation();
        bookRecommendation.setAuthor("author");
        bookRecommendation.setIsbn("isbn");
        bookRecommendation.setTitle("title");

        ArrayList<Course> courses = new ArrayList<>();
        Course onecourse = new Course("tkt101", "", new ArrayList<Recommendation>());
        courses.add(onecourse);
        bookRecommendation.setCourses(courses);

        ArrayList<String> tags = new ArrayList<>();
        tags.add("educational");
        bookRecommendation.setTags(tags);

        BookRecommendation savedBook = recommendationRepository.save(bookRecommendation);
        Long id = savedBook.getId();
        savedBook = (BookRecommendation) recommendationRepository.getOne(id);
        assertEquals(bookRecommendation.getAuthor(), savedBook.getAuthor());
        assertEquals(bookRecommendation.getTitle(), savedBook.getTitle());
        assertEquals(bookRecommendation.getIsbn(), savedBook.getIsbn());
        assertEquals(bookRecommendation.getCourses(), savedBook.getCourses());
        assertEquals(bookRecommendation.getTags(), savedBook.getTags());
    }

    @Test
    public void coursesCanBeAddedToBooks() {
        BookRecommendation bookRecommendation = new BookRecommendation();
        Course onecourse = new Course("tkt101", "", new ArrayList<Recommendation>());
        bookRecommendation.addCourse(onecourse);
        assertTrue(bookRecommendation.getCourses().contains(onecourse));
    }

    @Test
    public void bookRecommendationAsStringIsOk() {
        BookRecommendation bookRecommendation = new BookRecommendation();
        bookRecommendation.setAuthor("kirjoittaja");
        bookRecommendation.setIsbn("isbn");
        bookRecommendation.setTitle("otsikko");

        ArrayList<Course> courses = new ArrayList<>();
        Course onecourse = new Course("tkt101", "", new ArrayList<Recommendation>());
        courses.add(onecourse);
        bookRecommendation.setCourses(courses);
        assertTrue(bookRecommendation.toString().equals("\"otsikko\", kirjoittanut kirjoittaja"));
    }
}
