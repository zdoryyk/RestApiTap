package ru.alishev.springcourse.FirstSecurityApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alishev.springcourse.FirstSecurityApp.models.Rating;

import javax.persistence.criteria.CriteriaBuilder;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {
}
