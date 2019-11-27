package ohtu.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ohtu.database.entities.data.Book;
import ohtu.database.entities.data.Course;
import ohtu.database.repositories.BookRepository;
import ohtu.database.repositories.CourseRepository;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/books")
    public String list(Model model) {
        List<Book> books = bookRepository.findAll();
        List<Course> courses = courseRepository.findAll();

        model.addAttribute("books", books);
        model.addAttribute("courses", courses);
        model.addAttribute("book", new Book());

        return "books";
    }

    @PostMapping("/books")
    public String create(Model model, @Valid Book book, BindingResult result,
            RedirectAttributes redirectAttributes, @RequestParam(value = "selectedCourseId", required = false, defaultValue = "0") Long selectedCourseId) {

        if (result.hasErrors()) {
            List<Book> books = bookRepository.findAll();
            List<Course> courses = courseRepository.findAll();

            model.addAttribute("books", books);
            model.addAttribute("courses", courses);
            model.addAttribute("book", book);
            //redirectAttributes.addAttribute("message", "Lisäys epäonnistui, tarkista tiedot");

            return "books";
        }

        if (courseRepository.existsById(selectedCourseId)) {
            Course course = courseRepository.getOne(selectedCourseId);
            book.addCourse(course);
        }

        this.bookRepository.save(book);
        redirectAttributes.addFlashAttribute("message", "Lisäys onnistui!");

        return "redirect:/books";
    }
}
