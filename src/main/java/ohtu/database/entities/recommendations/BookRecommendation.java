package ohtu.database.entities.recommendations;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

import lombok.*;

import org.hibernate.validator.constraints.ISBN;

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

    @Override
    public RecommendationType getType() {
        return RecommendationType.BOOK;
    }

    @Override
    public String toString() {
        return String.format("%s, kirjoittanut %s", '\"' + this.getTitle() + '\"', this.getAuthor());
    }
}
