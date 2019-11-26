package ohtu.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ohtu.database.entities.data.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
