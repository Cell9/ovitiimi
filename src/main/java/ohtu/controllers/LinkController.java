package ohtu.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ohtu.database.entities.data.Course;
import ohtu.database.entities.data.Link;
import ohtu.database.entities.recommendations.LinkRecommendation;
import ohtu.database.repositories.CourseRepository;
import ohtu.database.repositories.LinkRepository;
import ohtu.database.repositories.RecommendationRepository;

//Roni
@Controller
public class LinkController {

    @Autowired
    private LinkRepository linkRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private RecommendationRepository recommendationRepository;

    @GetMapping("/links")
    public String list(Model model) {
        List<Link> links = linkRepository.findAll();
        model.addAttribute("links", links);
        List<Course> courses = courseRepository.findAll();
        model.addAttribute("courses", courses);
        return "links";
    }

    @PostMapping("/links")
    public String create(@RequestParam String title,
            @RequestParam String url,
            @RequestParam(value = "selectedCourseId", required = false, defaultValue = "0") Long selectedCourseId) {
        LinkRecommendation link = new LinkRecommendation();
        link.setTitle(title);

        if (!url.contains("//")) {
            url = "https://" + url;
        }

        link.setUrl(url);

        if (courseRepository.existsById(selectedCourseId)) {
            Course course = courseRepository.getOne(selectedCourseId);
            link.addCourse(course);
        }
        recommendationRepository.save(link);
        
        return "redirect:/";
    }
}
