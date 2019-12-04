package ohtu.controllers;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ohtu.database.entities.data.Course;
import ohtu.database.entities.recommendations.BookRecommendation;
import ohtu.database.entities.recommendations.LinkRecommendation;
import ohtu.database.entities.recommendations.PodcastRecommendation;
import ohtu.database.entities.recommendations.Recommendation;
import ohtu.database.entities.recommendations.RecommendationType;
import ohtu.database.entities.recommendations.YoutubeRecommendation;
import ohtu.database.repositories.CourseRepository;
import ohtu.database.repositories.RecommendationRepository;
import ohtu.rest.request.RestSetRecommendationReadStatus;
import ohtu.rest.response.RestGeneralResponse;

@Controller
public class RecommendationController {

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
        if (!model.containsAttribute("youtube")) {
            model.addAttribute("showYoutube", false);
            model.addAttribute("youtube", new YoutubeRecommendation());            
        } else {
            model.addAttribute("showYoutube", true);
        }


        return "newRecommendation";
    }

    @GetMapping("/lukuvinkki/")
    public String oneRecommendaton(Model model, @RequestParam Long id) {
        Recommendation recommendation = recommendationRepository.findById(id).orElse(null);
        // jos ei löydy, ohjataan listaukseen
        if (recommendation == null) {
            return "redirect:/index";
        }

        model.addAttribute("recommendation", recommendation);

        return "recommendation";

    }
    
    @PostMapping("recommendation/read")
    @ResponseBody
    public RestGeneralResponse recommendationRead(@RequestBody RestSetRecommendationReadStatus request) {
    	Recommendation recommendation = this.recommendationRepository.findById(request.getId()).orElse(null);
    	if (recommendation == null) {
    		return RestGeneralResponse.error("Lukuvinkkiä ei löydetty!");
    	}
    	
    	recommendation.setReadTime(request.isRead() ? Timestamp.from(Instant.now()) : null);
    	
    	this.recommendationRepository.save(recommendation);
    	
    	return RestGeneralResponse.success();
    }
}