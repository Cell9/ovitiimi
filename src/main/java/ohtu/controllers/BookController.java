package ohtu.controllers;


import java.util.List;

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
    public String create(Model model, @Valid Book book, BindingResult result, @RequestParam Long selectedCourseId) {
    	if (result.hasErrors()) {
            List<Course> courses = courseRepository.findAll();
            
            model.addAttribute("courses", courses);
    		model.addAttribute("book", book);
    		
    		return "books";
    	}
    	
        Course course = courseRepository.getOne(selectedCourseId);
        book.addCourse(course);
        
        this.bookRepository.save(book);

        return "redirect:/books";
    }
}