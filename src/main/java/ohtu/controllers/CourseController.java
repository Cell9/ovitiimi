package ohtu.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ohtu.database.entities.data.Course;
import ohtu.database.repositories.CourseRepository;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CourseController {
    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/courses")
    public String list(Model model) {
        List<Course> courses = courseRepository.findAll();
        
        model.addAttribute("courses", courses);
        model.addAttribute("course", new Course());
        
        return "courses";
    }

    @PostMapping("/courses")
    public String create(Model model, @Valid Course course, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {

            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.course", result);
            //redirectAttributes.addFlashAttribute("error", "Kurssin lisäys epäonnistui!");
            redirectAttributes.addFlashAttribute("course", course);
            return "redirect:/courses";
        }

        this.courseRepository.save(course);
        redirectAttributes.addFlashAttribute("message", "Lisäys onnistui!");
        
        return "redirect:/courses";
    }
}
