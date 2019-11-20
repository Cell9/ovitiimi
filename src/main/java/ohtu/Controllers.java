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
    
    @GetMapping("/other")
    @ResponseBody
    public String other() {
        return "Hei Maailma!";
    }
    
    @GetMapping ("/books")
    public String books(Model model) {
        // Jos tietokannassa ei ole vielä esimerkkikirjaa
        if (bookRepo.findAll().size() < 1) {
            // Luodaan kaksi uutta kirjaa
            
            // Luo uusi kirja
            Book book = new Book();
            book.setAuthor("Make");
            book.setTitle("How to use JPArepository");
            book.setISBN("ABCKISSAKAVELEE");
            // Huomaa, etten täyttänyt kaikkia kohtia, koska olen laiska.
            
            // Tallenna kirja tietokantaan.
            bookRepo.save(book);
            
            // Luo uusi kirja
            Book book2 = new Book();
            book2.setAuthor("Maija");
            book2.setTitle("And other stuff");
            book2.setISBN("TIKAPUITAPITKINABC");
            // Huomaa, etten täyttänyt kaikkia kohtia, koska olen laiska.
            
            // Tallenna kirja tietokantaan.
            bookRepo.save(book2);
        }

        // Hae kaikki kirjat tietokannasta
        List<Book> books = bookRepo.findAll();
        
        // Lisää haetut kirjat HTML näkymässä saatavilla olevaan model olioon attribuutiksi books.
        model.addAttribute("books", books);
        
        // Palauta käytetyn html filen nimi (katso html filestä miten käyttää books listaa ja sen book olioita HTML-filessä). HTML-filet löytyvät main => resources => templates kansiosta.
        return "books";
    }
}