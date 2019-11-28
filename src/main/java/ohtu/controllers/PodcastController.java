package ohtu.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ohtu.database.entities.data.Course;
import ohtu.database.entities.data.Podcast;
import ohtu.database.entities.recommendations.PodcastRecommendation;
import ohtu.database.repositories.CourseRepository;
import ohtu.database.repositories.PodcastRepository;
import ohtu.database.repositories.RecommendationRepository;

@Controller
public class PodcastController {

    @Autowired
    private PodcastRepository podcastRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private RecommendationRepository recommendationRepository;

    @GetMapping("/podcasts")
    public String list(Model model) {
        List<Course> courses = courseRepository.findAll();
        model.addAttribute("courses", courses);

        List<Podcast> podcasts = podcastRepository.findAll();
        model.addAttribute("podcasts", podcasts);

        return "podcasts";
    }

    @PostMapping("/podcasts")
    public String create(RedirectAttributes redirectAttributes, @RequestParam String title,
            @RequestParam String author,
            @RequestParam String description,
            @RequestParam(value = "selectedCourseId", required = false, defaultValue = "0") Long selectedCourseId) {
        Podcast podcast = new Podcast();
        podcast.setTitle(title);
        podcast.setAuthor(author);
        podcast.setDescription(description);

        if (courseRepository.existsById(selectedCourseId)) {
            Course course = courseRepository.getOne(selectedCourseId);
            podcast.addCourse(course);
        }

        Podcast savedPodcast = podcastRepository.save(podcast);
        
        this.recommendationRepository.save(new PodcastRecommendation(savedPodcast));
        
        redirectAttributes.addFlashAttribute("message", "Lisäys onnistui!");
                
        return "redirect:/podcasts";
    }
}
