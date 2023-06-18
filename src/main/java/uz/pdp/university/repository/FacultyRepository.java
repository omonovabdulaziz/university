package uz.pdp.university.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.university.entity.Faculty;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty  , Integer> {
    List<Faculty> findByUniversityId(Integer universityId);
}
