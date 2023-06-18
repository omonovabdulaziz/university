package uz.pdp.university.controller;


import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.university.entity.Faculty;
import uz.pdp.university.entity.Group;
import uz.pdp.university.payload.GroupDTO;
import uz.pdp.university.repository.FacultyRepository;
import uz.pdp.university.repository.GroupRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/groups")
public class GroupController {
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    FacultyRepository facultyRepository;

    //create
    @PostMapping
    public String addGroups(@RequestBody GroupDTO groupDTO ){
        Optional<Faculty> optionalFaculty = facultyRepository.findById(groupDTO.getFacultyId());
        if (optionalFaculty.isPresent()){
            Faculty faculty = optionalFaculty.get();
            Group group = new Group();
            group.setFaculty(faculty);
            group.setName(groupDTO.getName());
            groupRepository.save(group);
            return "saved";
        }else{
            return "bunday id li facultet yoq";
        }
    }
    //READ FOR VAZIRLIK
    @GetMapping
    public List<Group> getAllgroups(){
        return groupRepository.findAll();
    }
    //read for faculty xodimi
    @GetMapping("/byFacultyId/{facultyId}")
    public List<Group> getGrop(@PathVariable Integer facultyId){
        return groupRepository.findByFacultyId(facultyId);
    }
}
