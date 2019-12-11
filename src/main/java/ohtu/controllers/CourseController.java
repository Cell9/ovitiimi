package ohtu.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ohtu.database.entities.data.Course;
import ohtu.database.repositories.CourseRepository;

@Controller
public class CourseController {
    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/courses")
    public String list(Model model) {
        model.addAttribute("courses", this.courseRepository.findAll());
        model.addAttribute("course", new Course());
        
        return "courses";
    }

    @PostMapping("/courses")
    public String create(Model model, @Valid Course course, BindingResult result, RedirectAttributes redirectAttributes) {
    	
        if (result.hasErrors()) {

            model.addAttribute("courses", this.courseRepository.findAll());
            model.addAttribute("course", course);
            
            return "courses";
        }

        this.courseRepository.save(course);
        
        redirectAttributes.addFlashAttribute("message", "Lisäys onnistui!");
        
        return "redirect:/courses";
    }
}
