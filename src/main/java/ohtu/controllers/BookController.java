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

import ohtu.database.entities.recommendations.BookRecommendation;

@Controller
public class BookController extends AbstractRecommendationItemController {

    @PostMapping("/books")
    public String create(Model model, @Valid BookRecommendation book, BindingResult result,
            RedirectAttributes redirectAttributes, @RequestParam(value = "selectedCourseId", required = false, defaultValue = "0") List<Long> selectedCourseIds) {

        book.setCourses(this.courseRepository.findAllById(selectedCourseIds));
        
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", "Kirjan lisäys epäonnistui!");
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.book", result);
            redirectAttributes.addFlashAttribute("book", book);
            return "redirect:/uusi";
        }

        recommendationRepository.save(book);

        redirectAttributes.addFlashAttribute("message", "Lisäys onnistui!");

        return "redirect:/";
    }

    @PostMapping("books/edit")
    public String edit(Model model,  @ModelAttribute("recommendation") @Valid BookRecommendation recommendation, BindingResult result, @RequestParam(value = "id", required = true) Long id,
                       RedirectAttributes redirectAttributes, @RequestParam(value = "selectedCourseId", required = false, defaultValue = "0") List<Long> selectedCourseIds) {

    	return this.editMapping(model, id, recommendation, result, selectedCourseIds, redirectAttributes);
    }
}
