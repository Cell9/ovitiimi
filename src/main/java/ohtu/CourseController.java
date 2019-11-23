package ohtu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CourseController {
    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/courses")
    public String list(Model model) {
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
        List<Course> courses = courseRepository.findAll();
        model.addAttribute("courses", courses);
        return "courses";
    }

    @PostMapping("/courses")
    public String create(@RequestParam String code, @RequestParam String name) {
        Course course = new Course();
        course.setCode(code);
        course.setName(name);

        courseRepository.save(course);
        return "redirect:/courses";
    }
}
