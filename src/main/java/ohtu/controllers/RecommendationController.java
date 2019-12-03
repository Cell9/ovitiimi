package ohtu.controllers;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import ohtu.database.entities.data.Course;
import ohtu.database.entities.recommendations.BookRecommendation;
import ohtu.database.entities.recommendations.LinkRecommendation;
import ohtu.database.entities.recommendations.PodcastRecommendation;
import ohtu.database.entities.recommendations.Recommendation;
import ohtu.database.entities.recommendations.RecommendationType;
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


        return "newRecommendation";
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