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

import ohtu.database.entities.recommendations.PodcastRecommendation;

@Controller
public class PodcastController extends AbstractRecommendationItemController {

    @PostMapping("/podcasts")
    public String create(Model model, @Valid PodcastRecommendation podcast, BindingResult result, RedirectAttributes redirectAttributes,
            @RequestParam(value = "selectedCourseId", required = false, defaultValue = "0") List<Long> selectedCourseIds) {

        podcast.setCourses(this.courseRepository.findAllById(selectedCourseIds));

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", "Podcastin lisäys epäonnistui!");
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.podcast", result);
            redirectAttributes.addFlashAttribute("podcast", podcast);
            return "redirect:/uusi";
        }
        
        recommendationRepository.save(podcast);

        redirectAttributes.addFlashAttribute("message", "Lisäys onnistui!");

        return "redirect:/";
    }

    @PostMapping("podcast/edit")
    public String edit(Model model,  @ModelAttribute("recommendation") @Valid PodcastRecommendation recommendation, BindingResult result, @RequestParam(value = "id", required = true) Long id,
                       RedirectAttributes redirectAttributes, @RequestParam(value = "selectedCourseId", required = false, defaultValue = "0") List<Long> selectedCourseIds) {

    	return this.editMapping(model, id, recommendation, result, selectedCourseIds, redirectAttributes);
    }
}
