package ohtu.controllers;


import ohtu.database.entities.data.Book;
import ohtu.database.entities.data.Course;
import ohtu.database.repositories.BookRepository;
import ohtu.database.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/books")
    public String list(Model model) {
        // Jos tietokannassa ei ole vielä esimerkkikirjaa
        if (courseRepository.findAll().size() < 1) {
            Course course1 = new Course();
            course1.setCode("TKT20006");
            course1.setName("Ohjelmistotuotanto");
            courseRepository.save(course1);

            Course course2 = new Course();
            course2.setCode("TKT20001");
            course2.setName("Tietorakenteet ja algoritmit");
            courseRepository.save(course2);
        }
        if (bookRepository.findAll().size() < 1) {
            // Luodaan kaksi uutta kirjaa

            // Luo uusi kirja
            Book book = new Book();
            book.setAuthor("Make");
            book.setTitle("How to use JPArepository");
            book.setIsbn("ABCKISSAKAVELEE");
            book.addCourse(courseRepository.getOne(1L));
            // Huomaa, etten täyttänyt kaikkia kohtia, koska olen laiska.

            // Tallenna kirja tietokantaan.
            bookRepository.save(book);

            // Luo uusi kirja
            Book book2 = new Book();
            book2.setAuthor("Maija");
            book2.setTitle("And other stuff");
            book2.setIsbn("TIKAPUITAPITKINABC");
            book2.addCourse(courseRepository.getOne(2L));
            // Huomaa, etten täyttänyt kaikkia kohtia, koska olen laiska.

            // Tallenna kirja tietokantaan.
            bookRepository.save(book2);
        }

        // Hae kaikki kirjat tietokannasta
        List<Book> books = bookRepository.findAll();

        // Lisää haetut kirjat HTML näkymässä saatavilla olevaan model olioon attribuutiksi books.
        model.addAttribute("books", books);

        List<Course> courses = courseRepository.findAll();
        model.addAttribute("courses", courses);

        // Palauta käytetyn html filen nimi (katso html filestä miten käyttää books listaa ja sen book olioita HTML-filessä). HTML-filet löytyvät main => resources => templates kansiosta.
        return "books";
    }

    @PostMapping("/books")
    public String create(@RequestParam String author,
                         @RequestParam String title,
                         @RequestParam String isbn,
                         @RequestParam Long selectedCourseId) {
        Book book = new Book();
        book.setAuthor(author);
        book.setTitle(title);
        book.setIsbn(isbn);
        Course course = courseRepository.getOne(selectedCourseId);
        book.addCourse(course);

        bookRepository.save(book);
        return "redirect:/books";
    }
}
