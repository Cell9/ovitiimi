package ohtu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PodcastController {

    @Autowired
    private PodcastRepository podcastRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/podcasts")
    public String list(Model model) {
        List<Course> courses = courseRepository.findAll();
        model.addAttribute("courses", courses);

        List<Podcast> podcasts = podcastRepository.findAll();
        model.addAttribute("podcasts", podcasts);

        return "podcasts";
    }

    @PostMapping("/podcasts")
    public String create(@RequestParam String title,
                         @RequestParam String author,
                         @RequestParam String description,
                         @RequestParam Long selectedCourseId) {
        Podcast podcast = new Podcast();
        podcast.setTitle(title);
        podcast.setAuthor(author);
        podcast.setDescription(description);
        Course course = courseRepository.getOne(selectedCourseId);
        podcast.addCourse(course.toString());

        podcastRepository.save(podcast);
        return "redirect:/podcasts";
    }
}
