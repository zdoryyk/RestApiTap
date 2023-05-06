package ru.alishev.springcourse.FirstSecurityApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alishev.springcourse.FirstSecurityApp.models.Saved;

import java.util.Optional;


@Repository
public interface SavedRepository extends JpaRepository<Saved, Integer> {

    Saved findByUserId(int user_id);
}
