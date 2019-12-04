package ohtu.database.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ohtu.database.entities.recommendations.Recommendation;

public interface RecommendationRepository extends JpaRepository<Recommendation, Long>  {
	@Query(value = "SELECT DISTINCT tags FROM recommendation_tags", nativeQuery = true)
	public Set<String> getAllTags();
}
