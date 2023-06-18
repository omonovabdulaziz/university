package uz.pdp.university.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.university.entity.Group;
import uz.pdp.university.entity.Student;
import uz.pdp.university.payload.StudentDTO;
import uz.pdp.university.repository.GroupRepository;
import uz.pdp.university.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    GroupRepository  groupRepository;

   @PostMapping
    public String addStudent(@RequestBody StudentDTO studentDTO ){
       Optional<Group> optionalGroup = groupRepository.findById(studentDTO.getGroupId());
       if (!optionalGroup.isPresent())
           return "bunday id li group yoq";

       Group group = optionalGroup.get();
       Student student = new Student();
       studentDTO.setFirstName(studentDTO.getFirstName());
       student.setGroup(group);
       student.setLastName(studentDTO.getLastName());
       studentRepository.save(student);
       return "saved";
   }
   //READFORVAZIRLIK
   @GetMapping("/forMinstry")
    public Page<Student> getStudent(@RequestParam int page){
       Pageable pageable = PageRequest.of(page , 10);
       Page<Student> pageStudent = studentRepository.findAll(pageable);
       return pageStudent;
   }

   //READFORUNIVERSITYXODIMI
    @GetMapping("/forUniversity/{univerId}")
    public Page<Student> getStudentforUniversity(@PathVariable  Integer  univerId , @RequestParam int page ){
       Pageable pageable = PageRequest.of(page , 10);
        return studentRepository.findAllByGroup_Faculty_UniversityId(univerId, pageable);

    }
   //FACULTYUCHUN
    @GetMapping("/forFaculty/{facultyId}")
    public Page<Student> getStudentByFaculty(@PathVariable Integer facultyId , @RequestParam int page){
         Pageable pageable = PageRequest.of(page , 10);
        return studentRepository.findAllByGroup_FacultyId(facultyId, pageable);
    }
    //GROUPOWNER
    @GetMapping("/groupId/{groupid}")
    public Page<Student> getStudentpage(@PathVariable Integer groupid , @RequestParam int page){
       Pageable pageable = PageRequest.of(page , 10);
       return studentRepository.findAllByGroupId(groupid , pageable);
    }
}
