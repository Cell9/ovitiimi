
package ohtu.unitAndRepoTests;
//
//import java.util.ArrayList;
//import javax.transaction.Transactional;
//
//import ohtu.database.entities.data.Book;
//import ohtu.database.repositories.BookRepository;
//
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.context.junit4.SpringRunner;
//
////Roni
//@RunWith(SpringRunner.class)
//@SpringBootTest()
//@TestPropertySource(
//  locations = "classpath:application-test.properties")
//@Transactional
//public class bookTests {
//
//    @Autowired
//    private BookRepository bookrepo;
//
//    @Before
//    public void setUp() {
//    }
//
//   @Test
//    public void booksCanBeCreatedEmptyConstructor() {
//        Book book = new Book();
//        assertNotNull(book);
//    }
///*
//    @Test
//    public void booksCanBeCreatedNonEmptyConstructor() {
//        Book book = new Book("author", "title", "isbn", new ArrayList<>(), new ArrayList<>());
//        assertNotNull(book);
//        assertEquals(book.getTitle(), "title");
//    }
//
//    @Test
//    public void booksValuesCanBeSetAndGotten() {
//        Book book = new Book();
//        book.setAuthor("author");
//        book.setIsbn("isbn");
//        book.setTitle("title");
//        ArrayList<String> courses = new ArrayList<>();
//        courses.add("tkt101");
//        book.setCourses(courses);
//        ArrayList<String> tags = new ArrayList<>();
//        tags.add("educational");
//        book.setTags(tags);
//        assertEquals(book.getAuthor(), "author");
//        assertTrue(book.getCourses().contains("tkt101"));
//        assertTrue(book.getTags().contains("educational"));
//        assertEquals(book.getTitle(), "title");
//        assertEquals(book.getIsbn(), "isbn");
//    }
//
//    @Test
//    public void booksCanBeSavedAndRetrievedFromRepo(){
//        Book book = new Book();
//        book.setAuthor("author");
//        book.setIsbn("isbn");
//        book.setTitle("title");
//        ArrayList<String> courses = new ArrayList<>();
//        courses.add("tkt101");
//        book.setCourses(courses);
//        ArrayList<String> tags = new ArrayList<>();
//        tags.add("educational");
//        book.setTags(tags);
//        Book savedbook = bookrepo.save(book);
//        Long id = savedbook.getId();
//        savedbook = bookrepo.getOne(id);
//        assertEquals(book.getAuthor(), savedbook.getAuthor());
//        assertEquals(book.getTitle(), savedbook.getTitle());
//        assertEquals(book.getIsbn(), savedbook.getIsbn());
//        assertEquals(book.getCourses(), savedbook.getCourses());
//        assertEquals(book.getTags(), savedbook.getTags());
//    }
//
//    @Test
//    public void coursesCanBeAddedToBooks() {
//        Book book = new Book();
//        book.addCourse("kurssi");
//        assertTrue(book.getCourses().contains("kurssi"));
//    }*/
//}
