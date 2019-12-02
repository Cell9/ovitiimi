package ohtu.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ohtu.database.entities.data.Book;
import ohtu.database.entities.data.Course;
import ohtu.database.entities.data.Link;
import ohtu.database.entities.data.Podcast;
import ohtu.database.entities.recommendations.BookRecommendation;
import ohtu.database.entities.recommendations.PodcastRecommendation;
import ohtu.database.entities.recommendations.Recommendation;
import ohtu.database.entities.recommendations.RecommendationType;
import ohtu.database.entities.recommendations.StubRecommendation;
import ohtu.database.entities.recommendations.LinkRecommendation;
import ohtu.database.repositories.BookRepository;
import ohtu.database.repositories.CourseRepository;
import ohtu.database.repositories.LinkRepository;
import ohtu.database.repositories.PodcastRepository;
import ohtu.database.repositories.RecommendationRepository;

@Controller
public class RecommendationController {

    @Autowired
    private LinkRepository linkRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PodcastRepository podcastRepository;

    @Autowired
    private RecommendationRepository recommendationRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping(value = {"/", "index"})
    public String list(Model model) {
        List<Recommendation> recommendations = recommendationRepository.findAll();
        model.addAttribute("recommendations", recommendations);
        return "index";
    }

    @GetMapping("/uusi")
    public String form(Model model) {
        List<RecommendationType> types = RecommendationType.valuesAsList();
        List<Course> courses = courseRepository.findAll();

        model.addAttribute("types", types);
        model.addAttribute("courses", courses);

        if (!model.containsAttribute("podcast")) {
            model.addAttribute("showPodcast", false);
            model.addAttribute("podcast", new PodcastRecommendation());
        } else {
            model.addAttribute("showPodcast", true);
        }
        if (!model.containsAttribute("link")) {
            model.addAttribute("showLink", false);
            model.addAttribute("link", new LinkRecommendation());
        } else {
            model.addAttribute("showLink", true);
        }
        if (!model.containsAttribute("book")) {
            model.addAttribute("showBook", false);
            model.addAttribute("book", new BookRecommendation());
        } else {
            model.addAttribute("showBook", true);
        }


        return "newRecommendation";
    }

    @PostMapping("/uusi")
    public String create(Model model, @ModelAttribute("recommendation") @Valid StubRecommendation stub, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return createError(model, stub);
        }

        //TODO: Kinda ugly...
        Recommendation recommendation;
        if (stub.getType() == RecommendationType.BOOK) {
            Book book = this.bookRepository.findById(stub.getEntityId()).orElse(null);
            if (book == null) {
                return createErrorNotFound(model, stub);
            }

            recommendation = new BookRecommendation();
        } else if (stub.getType() == RecommendationType.LINKKI) {
            Link link = this.linkRepository.findById(stub.getEntityId()).orElse(null);
            if (link == null) {
                return createErrorNotFound(model, stub);
            }

            recommendation = new LinkRecommendation();
        } else if (stub.getType() == RecommendationType.PODCAST) {
            Podcast podcast = this.podcastRepository.findById(stub.getEntityId()).orElse(null);
            if (podcast == null) {
                return createErrorNotFound(model, stub);
            }

            recommendation = new PodcastRecommendation();
        } else {
            return createErrorType(model, stub);
        }

        this.recommendationRepository.save(recommendation);

        redirectAttributes.addFlashAttribute("message", "Lisäys onnistui!");

        return "redirect:/index";
    }

    private String createErrorNotFound(Model model, StubRecommendation stub) {
        stub.setEntityId(0);

        return createError(model, stub);
    }

    private String createErrorType(Model model, StubRecommendation stub) {
        stub.setTypeId(null);

        return createError(model, stub);
    }

    private String createError(Model model, StubRecommendation stub) {
        List<Link> links = this.linkRepository.findAll();
        List<Book> books = this.bookRepository.findAll();
        List<Podcast> podcasts = this.podcastRepository.findAll();

        model.addAttribute("links", links);
        model.addAttribute("books", books);
        model.addAttribute("podcasts", podcasts);

        model.addAttribute("recommendation", stub);
        model.addAttribute("types", RecommendationType.valuesAsList());

        return "index";
    }
}