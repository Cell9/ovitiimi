package ohtu.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ohtu.database.entities.recommendations.Recommendation;

public interface RecommendationRepository extends JpaRepository<Recommendation, Long>  {

}
