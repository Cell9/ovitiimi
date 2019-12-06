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
import ohtu.database.entities.recommendations.BookRecommendation;
import ohtu.database.entities.recommendations.Recommendation;
import ohtu.database.entities.recommendations.RecommendationType;
import ohtu.database.repositories.RecommendationRepository;

@RunWith(SpringRunner.class)
@SpringBootTest()
@TestPropertySource(
  locations = "classpath:application-test.properties")
@Transactional
public class BookRecommendationTests {

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
                "title", new HashMap<>(), new ArrayList<>(), "author", "isbn");
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
        assertTrue(bookRecommendation.hasCourse(oneCourse));
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
        assertTrue(Helpers.areSimilar(bookRecommendation.getCourses(), savedBook.getCourses()));
        assertEquals(bookRecommendation.getTags(), savedBook.getTags());
    }

    @Test
    public void coursesCanBeAddedToBooks() {
        BookRecommendation bookRecommendation = new BookRecommendation();
        Course onecourse = new Course("tkt101", "", new ArrayList<Recommendation>());
        bookRecommendation.addCourse(onecourse);
        assertTrue(bookRecommendation.hasCourse(onecourse));
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
        assertTrue(bookRecommendation.toString().equals("otsikko, kirjoittanut kirjoittaja"));
    }

    @Test
    public void getTypeBook() {
        BookRecommendation bookRecommendation = new BookRecommendation();
        bookRecommendation.setAuthor("kirjoittaja");
        bookRecommendation.setIsbn("isbn");
        bookRecommendation.setTitle("otsikko");

        ArrayList<Course> courses = new ArrayList<>();
        Course onecourse = new Course("tkt101", "", new ArrayList<Recommendation>());
        courses.add(onecourse);
        bookRecommendation.setCourses(courses);
        assertTrue(bookRecommendation.getType() == RecommendationType.BOOK);
    }

    @Test
    public void bookRecommendationsAreSame() {
        BookRecommendation bookRecommendationA = new BookRecommendation();
        assertTrue(bookRecommendationA.equals(bookRecommendationA));
    }

    @Test
    public void bookRecommendationsAreEquals() {
        BookRecommendation bookRecommendationA = new BookRecommendation();
        bookRecommendationA.setAuthor("kirjoittaja");
        bookRecommendationA.setIsbn("isbn");
        bookRecommendationA.setTitle("otsikko");

        BookRecommendation bookRecommendationB = new BookRecommendation();
        bookRecommendationB.setAuthor("kirjoittaja");
        bookRecommendationB.setIsbn("isbn");
        bookRecommendationB.setTitle("otsikko");

        assertTrue(bookRecommendationA.equals(bookRecommendationB));
        assertTrue(bookRecommendationB.equals(bookRecommendationA));
    }

    @Test
    public void bookRecommendationsAreNotEquals() {
        BookRecommendation bookRecommendationA = new BookRecommendation();
        bookRecommendationA.setAuthor("kirjoittajaa");
        bookRecommendationA.setIsbn("isbn");
        bookRecommendationA.setTitle("otsikko");

        BookRecommendation bookRecommendationB = new BookRecommendation();
        bookRecommendationB.setAuthor("kirjoittajab");
        bookRecommendationB.setIsbn("isbn");
        bookRecommendationB.setTitle("otsikko");

        assertFalse(bookRecommendationA.equals(bookRecommendationB));
        assertFalse(bookRecommendationB.equals(bookRecommendationA));
    }

    @Test
    public void bookRecommendationHashCodesAreEquals() {
        BookRecommendation bookRecommendationA = new BookRecommendation();
        bookRecommendationA.setAuthor("kirjoittaja");
        bookRecommendationA.setIsbn("isbn");
        bookRecommendationA.setTitle("otsikko");

        BookRecommendation bookRecommendationB = new BookRecommendation();
        bookRecommendationB.setAuthor("kirjoittaja");
        bookRecommendationB.setIsbn("isbn");
        bookRecommendationB.setTitle("otsikko");

        assertTrue(bookRecommendationA.hashCode() == bookRecommendationA.hashCode());
        assertTrue(bookRecommendationA.hashCode() == bookRecommendationB.hashCode());
    }

    @Test
    public void bookRecommendationHashCodesAreNotEquals() {
        BookRecommendation bookRecommendationA = new BookRecommendation();
        bookRecommendationA.setAuthor("kirjoittajaa");
        bookRecommendationA.setIsbn("isbn");
        bookRecommendationA.setTitle("otsikko");

        BookRecommendation bookRecommendationB = new BookRecommendation();
        bookRecommendationB.setAuthor("kirjoittajab");
        bookRecommendationB.setIsbn("isbn");
        bookRecommendationB.setTitle("otsikko");

        assertFalse(bookRecommendationA.hashCode() == bookRecommendationB.hashCode());
    }
}
