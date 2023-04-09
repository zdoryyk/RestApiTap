package ru.alishev.springcourse.FirstSecurityApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.alishev.springcourse.FirstSecurityApp.models.Rating;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {

    Rating getRatingByUser_Id(int id);

    @Query("SELECT u.username, r.rating FROM User u JOIN u.rating r ORDER BY r.rating DESC")
    List<Object[]> getUsersByRating();

}
