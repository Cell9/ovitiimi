package ohtu.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ohtu.database.entities.data.Book;
import ohtu.database.entities.data.Course;
import ohtu.database.entities.recommendations.BookRecommendation;
import ohtu.database.entities.recommendations.PodcastRecommendation;
import ohtu.database.entities.recommendations.Recommendation;
import ohtu.database.entities.recommendations.RecommendationType;
import ohtu.database.entities.recommendations.StubRecommendation;
import ohtu.database.repositories.BookRepository;
import ohtu.database.repositories.CourseRepository;
import ohtu.database.repositories.RecommendationRepository;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private RecommendationRepository recommendationRepository;

    @InitBinder
    public void allowEmptyDateBinding(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/books")
    public String list(Model model) {
        List<Book> books = bookRepository.findAll();
        List<Course> courses = courseRepository.findAll();

        model.addAttribute("books", books);
        model.addAttribute("courses", courses);
        model.addAttribute("book", new Book());

        return "index";
    }

    @PostMapping("/books")
    public String create(Model model, @Valid BookRecommendation book, BindingResult result,
            RedirectAttributes redirectAttributes, @RequestParam(value = "selectedCourseId", required = false, defaultValue = "0") Long selectedCourseId) {

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", "Kirjan lisäys epäonnistui!");
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.book", result);
            redirectAttributes.addFlashAttribute("book", book);
            return "redirect:/uusi";
        }

        if (courseRepository.existsById(selectedCourseId)) {
            Course course = courseRepository.getOne(selectedCourseId);
            book.addCourse(course);
        }

        recommendationRepository.save(book);

        redirectAttributes.addFlashAttribute("message", "Lisäys onnistui!");

        return "redirect:/";
    }
}
