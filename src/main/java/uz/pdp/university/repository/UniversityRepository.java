package uz.pdp.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.university.entity.University;

import java.util.List;

public interface UniversityRepository extends JpaRepository<University , Integer> {
 boolean existsByName(String name);

}
