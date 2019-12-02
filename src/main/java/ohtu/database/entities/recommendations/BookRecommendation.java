package ohtu.database.entities.recommendations;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

import lombok.Getter;
import lombok.Setter;
//import ohtu.database.entities.data.Book;
import ohtu.database.entities.data.Course;
import org.hibernate.validator.constraints.ISBN;

@Entity
@Data
@DiscriminatorValue("book")
public class BookRecommendation extends Recommendation {

    @Id
    private Long id;

    @NotEmpty
    @Getter
    @Setter
    private String author;

    // @Column(name = "isbn", unique = true) //NULL's aren't indexed
    //@ISBN
    @NotEmpty
    @Getter
    @Setter
    private String isbn;

    public BookRecommendation() {
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public RecommendationType getType() {
        return RecommendationType.BOOK;
    }

    @Override
    public String toString() {
        return String.format("%s, kirjoittanut %s", '\"' + this.getTitle() + '\"', this.getAuthor());
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void addCourse(Course course) {
        this.courses.add(course);
    }
}
