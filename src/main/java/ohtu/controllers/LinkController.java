package ohtu.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//import ohtu.database.entities.data.Link;
import ohtu.database.entities.recommendations.LinkRecommendation;

//Roni
@Controller
public class LinkController extends AbstractRecommendationItemController {

    @PostMapping("/links")
    public String create(Model model, @Valid LinkRecommendation link, BindingResult result, RedirectAttributes redirectAttributes,
            @RequestParam(value = "selectedCourseId", required = false, defaultValue = "0") List<Long> selectedCourseIds) {

        link.setCourses(this.courseRepository.findAllById(selectedCourseIds));
        
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", "Nettilähteen lisäys epäonnistui!");
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.link", result);
            redirectAttributes.addFlashAttribute("link", link);
            return "redirect:/uusi";
        }
        
        if (!link.getUrl().contains("//")) {
            link.setUrl("https://" + link.getUrl());
        }
        
        recommendationRepository.save(link);
        
        redirectAttributes.addFlashAttribute("message", "Lisäys onnistui!");

        return "redirect:/";
    }

    @PostMapping("link/edit")
    public String edit(Model model,  @ModelAttribute("recommendation") @Valid LinkRecommendation recommendation, BindingResult result, @RequestParam(value = "id", required = true) Long id,
                       RedirectAttributes redirectAttributes, @RequestParam(value = "selectedCourseId", required = false, defaultValue = "0") List<Long> selectedCourseIds) {

    	return this.editMapping(model, id, recommendation, result, selectedCourseIds, redirectAttributes);
    }
}
