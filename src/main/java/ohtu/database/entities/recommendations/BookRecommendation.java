package ohtu.database.entities.recommendations;

import javax.persistence.ConstraintMode;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Getter;
import ohtu.database.entities.data.Book;

@Entity
@DiscriminatorValue("book")
public class BookRecommendation extends Recommendation {

	@OneToOne
    @JoinColumn(name = "generic_id", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    @Getter private Book book;
    
    public BookRecommendation() {
    	
    }
    
    public BookRecommendation(Book book) {
    	this.book = book;
    }

	@Override
	public String getTitle() {
		return this.book.getTitle();
	}

	@Override
	public RecommendationType getType() {
		return RecommendationType.BOOK;
	}
	
    @Override
    public String toString() {
    	return String.format("%s, kirjoittanut %s", this.book.getTitle(), this.book.getAuthor());
    }
}
