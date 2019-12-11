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

import ohtu.database.entities.recommendations.YoutubeRecommendation;

@Controller
public class YoutubeController extends AbstractRecommendationItemController {

    @PostMapping("/youtubes")
    public String create(Model model, @Valid YoutubeRecommendation youtube, BindingResult result, RedirectAttributes redirectAttributes,
            @RequestParam(value = "selectedCourseId", required = false, defaultValue = "0") List<Long> selectedCourseIds) {

    	youtube.setCourses(this.courseRepository.findAllById(selectedCourseIds));
        
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", "Youtube videon lisäys epäonnistui!");
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.youtube", result);
            redirectAttributes.addFlashAttribute("youtube", youtube);
            return "redirect:/uusi";
        }
        
        recommendationRepository.save(youtube);

        redirectAttributes.addFlashAttribute("message", "Lisäys onnistui!");

        return "redirect:/";
    }


    @PostMapping("youtube/edit")
    public String edit(Model model, @ModelAttribute("recommendation") @Valid YoutubeRecommendation recommendation, BindingResult result, @RequestParam(value = "id", required = true) Long id,
                       RedirectAttributes redirectAttributes, @RequestParam(value = "selectedCourseId", required = false, defaultValue = "0") List<Long> selectedCourseIds) {

    	return this.editMapping(model, id, recommendation, result, selectedCourseIds, redirectAttributes);
    }
}
