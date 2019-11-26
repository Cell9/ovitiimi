package ohtu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ohtu.database.entities.data.Course;
import ohtu.database.entities.data.Link;
import ohtu.database.repositories.CourseRepository;
import ohtu.database.repositories.LinkRepository;

import java.util.List;

//Roni
@Controller
public class LinkController {

    @Autowired
    private LinkRepository linkRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/links")
    public String list(Model model) {
        // Jos tietokannassa ei ole vielä esimerkkinettilähdettä
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
        if (linkRepository.findAll().size() < 1) {
            // Luodaan kaksi uutta kirjaa

            // Luo uusi nettilähde
            Link link = new Link();
            link.setTitle("Tärkeä lähde yksi");
            link.setUrl("https://google.com");
            link.addCourse(courseRepository.getOne(1L));
            link.addTag("Hyvä lähde");

            // Tallenna nettilähde tietokantaan.
            linkRepository.save(link);

            // Luo uusi nettilähde
            Link link2 = new Link();
            link2.setTitle("Tärkeä lähde kaksi");
            link2.setUrl("https://instagram.com");
            link2.addCourse(courseRepository.getOne(2L));
            link.addTag("Tärkeä");

            // Tallenna nettilähde tietokantaan.
            linkRepository.save(link2);
        }

        List<Link> links = linkRepository.findAll();
        model.addAttribute("links", links);
        List<Course> courses = courseRepository.findAll();
        model.addAttribute("courses", courses);
         return "links";
    }

    @PostMapping("/links")
    public String create(@RequestParam String title,
                         @RequestParam String url,
                         @RequestParam Long selectedCourseId) {
        Link link = new Link();
        link.setTitle(title);
        if (!url.contains("http://") && !url.contains("https://") && !url.contains("//")) {
            url = "https://" + url;
        }
        link.setUrl(url);
        Course course = courseRepository.getOne(selectedCourseId);
        link.addCourse(course);
        linkRepository.save(link);
        return "redirect:/links";
    }
}
