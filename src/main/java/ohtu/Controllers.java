package ohtu;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Controllers {

    @Autowired
    BookRepository bookRepo;
    
    @Autowired
    LinkRepository linkRepo;
    
    @Autowired
    PodcastRepository podRepo;
    
    @GetMapping("/")
    @ResponseBody
    public String home() {
        return "<a href='/other'>linkki</a>";
    }

    @GetMapping("/index")
    public String handleDefault() {
        return "index";
    }
    
    @GetMapping("/other")
    @ResponseBody
    public String other() {
        return "Hei Maailma!";
    }
}