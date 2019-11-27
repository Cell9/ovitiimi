package ohtu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ohtu.database.repositories.BookRepository;
import ohtu.database.repositories.LinkRepository;
import ohtu.database.repositories.PodcastRepository;

@Controller
public class Controllers {

    @Autowired
    BookRepository bookRepo;
    
    @Autowired
    LinkRepository linkRepo;
    
    @Autowired
    PodcastRepository podRepo;
    
    @GetMapping("/other")
    @ResponseBody
    public String other() {
        return "Hei Maailma!";
    }
}