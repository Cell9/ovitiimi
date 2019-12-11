package ohtu.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ohtu.database.entities.recommendations.Recommendation;
import ohtu.database.repositories.CourseRepository;
import ohtu.database.repositories.RecommendationRepository;

public class AbstractRecommendationItemController {

    @Autowired
    protected CourseRepository courseRepository;

    @Autowired
    protected RecommendationRepository recommendationRepository;

    @InitBinder
    public void mapEmptyToNull(WebDataBinder binder) {
    	//This is used to map empty strings to null due some validators ignoring null values
    	//We can add seprate null validation, if we want to avoid this
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
    
    protected String editMapping(Model model, Long id, Recommendation recommendation, BindingResult result, List<Long> selectedCourseIds, RedirectAttributes redirectAttributes)
    {
    	recommendation.setIdDangerous(id);
    	recommendation.setCourses(this.courseRepository.findAllById(selectedCourseIds));
        
        if (result.hasErrors()) {
        	this.setAttributes(model, recommendation);
            
            return "editRecommendation";
        }
        
    	return this.edit(recommendation, redirectAttributes);
    }
    
    private void setAttributes(Model model, Recommendation recommendation) {
        model.addAttribute("recommendation", recommendation);
        
        model.addAttribute("courses", this.courseRepository.findAll());
        model.addAttribute("tags", this.recommendationRepository.getAllTags());
    }
    
    private String edit(Recommendation recommendation, RedirectAttributes redirectAttributes)
    {
        redirectAttributes.addAttribute("id", recommendation.getId());
        
        Recommendation saved = this.recommendationRepository.findById(recommendation.getId()).orElse(null);
        if (saved == null) { //Uh oh, no longer exists?
            return "redirect:/lukuvinkki/";
        }
        
        if (saved.getClass() != recommendation.getClass()) { //Uh oh! Different types
            return "redirect:/lukuvinkki/";
        }

        recommendation.copyTo(saved);
        
        this.recommendationRepository.save(recommendation);

        return "redirect:/lukuvinkki/";
    }
}
