package uz.pdp.university.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.university.entity.Faculty;
import uz.pdp.university.entity.University;
import uz.pdp.university.payload.FacultyDTO;
import uz.pdp.university.repository.FacultyRepository;
import uz.pdp.university.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    UniversityRepository universityRepository;

    //CREATE
    @PostMapping
    public String addFaculties(@RequestBody FacultyDTO facultyDTO) {
        Optional<University> optionaluniversity = universityRepository.findById(facultyDTO.getUniversityId());
        if (optionaluniversity.isPresent()) {
            University university = optionaluniversity.get();
            Faculty faculty = new Faculty();
            faculty.setName(facultyDTO.getFacultyName());
            faculty.setUniversity(university);
            facultyRepository.save(faculty);
            return "saved";
        } else {
            return "bunday id li universitet topilmadi";
        }
    }

    //READFORVAZIRLIK
    @GetMapping
    public List<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }

    //READFORUNIVERXODIMI
    @GetMapping("/byUniversityId/{universityId}")
    public List<Faculty> getFaculty(@PathVariable Integer universityId) {
        return facultyRepository.findByUniversityId(universityId);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public String deleteFaculty(@PathVariable Integer id) {
        try {
            facultyRepository.deleteById(id);
            return "faculty deleted";
        } catch (Exception e) {
            return "deleted error";
        }
    }

    //UPDATE
    @PutMapping("/{id}")
    public String editFaculty(@PathVariable Integer id, @RequestBody FacultyDTO facultyDTO) {
        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
        if (optionalFaculty.isPresent()) {
            Faculty faculty = optionalFaculty.get();
            faculty.setName(facultyDTO.getFacultyName());
            Optional<University> optionalUniversity = universityRepository.findById(id);
            if (!optionalUniversity.isPresent())
                return "bunday id li universitet topilmadi";

            University university = optionalUniversity.get();
            faculty.setUniversity(university);
            faculty.setId(id);
            facultyRepository.save(faculty);

        }
        return "faculty not found";
    }
}
