package ohtu.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ohtu.database.entities.data.Course;
//import ohtu.database.entities.data.Link;
import ohtu.database.entities.recommendations.LinkRecommendation;
import ohtu.database.repositories.CourseRepository;
//import ohtu.database.repositories.LinkRepository;
import ohtu.database.repositories.RecommendationRepository;

//Roni
@Controller
public class LinkController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private RecommendationRepository recommendationRepository;

    @PostMapping("/links")
    public String create(Model model, @Valid LinkRecommendation link, BindingResult result, RedirectAttributes redirectAttributes,
            @RequestParam(value = "selectedCourseId", required = false, defaultValue = "0") Long selectedCourseId) {
        
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", "Nettilähteen lisäys epäonnistui!");
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.link", result);
            redirectAttributes.addFlashAttribute("link", link);
            return "redirect:/uusi";
        }
        
        if (!link.getUrl().contains("//")) {
            link.setUrl("https://" + link.getUrl());
        }
        
        if (courseRepository.existsById(selectedCourseId)) {
            Course course = courseRepository.getOne(selectedCourseId);
            link.addCourse(course);
        }
        
        recommendationRepository.save(link);
        
        redirectAttributes.addFlashAttribute("message", "Lisäys onnistui!");

        return "redirect:/";
    }
}
