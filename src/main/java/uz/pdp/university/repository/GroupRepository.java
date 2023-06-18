package uz.pdp.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.university.entity.Group;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group , Integer> {
    List<Group> findByFacultyId(Integer faculty_id);
}
