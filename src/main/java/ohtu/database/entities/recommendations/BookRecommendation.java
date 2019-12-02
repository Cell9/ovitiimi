package ohtu.database.entities.recommendations;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

import lombok.*;

import ohtu.database.entities.data.Course;
import org.hibernate.validator.constraints.ISBN;

import java.util.List;

@Entity
@NoArgsConstructor
@Data
@DiscriminatorValue("book")
public class BookRecommendation extends Recommendation {

    @NotEmpty
    private String author;

    // @Column(name = "isbn", unique = true) //NULL's aren't indexed
    //@ISBN
    @NotEmpty
    private String isbn;

    public BookRecommendation(String title, List<Course> courses, List<String> tags, String author, String isbn) {
        super(title, courses, tags);
        this.author = author;
        this.isbn = isbn;
    }

    @Override
    public RecommendationType getType() {
        return RecommendationType.BOOK;
    }

    @Override
    public String toString() {
        return String.format("%s, kirjoittanut %s", '\"' + this.getTitle() + '\"', this.getAuthor());
    }
}
