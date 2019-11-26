package ohtu.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ohtu.database.entities.data.Book;
import ohtu.database.entities.data.Link;
import ohtu.database.entities.data.Podcast;
import ohtu.database.entities.recommendations.BookRecommendation;
import ohtu.database.entities.recommendations.PodcastRecommendation;
import ohtu.database.entities.recommendations.Recommendation;
import ohtu.database.entities.recommendations.VideoRecommendation;
import ohtu.database.repositories.BookRepository;
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
    
    @GetMapping("/recommendations")
    public String list(Model model) {
    	Link link = new Link("video", "lol");
    	Book book = new Book("book", "lol", "lol");
    	
    	Podcast podcast = new Podcast("podcast", "Waym", "Wew");
    	
    	this.linkRepository.save(link);
    	this.bookRepository.save(book);
    	this.podcastRepository.save(podcast);
    	
    	this.recommendationRepository.save(new VideoRecommendation(link));
    	this.recommendationRepository.save(new BookRecommendation(book));
    	this.recommendationRepository.save(new PodcastRecommendation(podcast));
    	
    	List<Recommendation> list = this.recommendationRepository.findAll();
    	
    	System.out.println(list);
    	
        model.addAttribute("recommendations", list);
    	
        return "recommendations";
    }
}
