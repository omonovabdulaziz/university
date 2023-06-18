package uz.pdp.university.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.university.entity.Address;
import uz.pdp.university.entity.University;
import uz.pdp.university.payload.UniversityDTO;
import uz.pdp.university.repository.AddresRepository;
import uz.pdp.university.repository.UniversityRepository;

import java.util.List;

@RestController
@RequestMapping("/university")
public class UniversityController {
    @Autowired
    UniversityRepository universityRepository;
    @Autowired
    AddresRepository addresRepository;

    //CREATE
    @PostMapping
    public String addUniversity(@RequestBody UniversityDTO universityDTO) {
        boolean univername = universityRepository.existsByName(universityDTO.getName());
        if (univername){
            return "bu hududga bunday nomli universitet avvaldan qo'shilgan";
        }else {
            Address address = new Address();
            address.setCity(universityDTO.getCity());
            address.setStreet(universityDTO.getStreet());
            addresRepository.save(address);
            University university = new University();
            university.setAddress(address);
            university.setName(universityDTO.getName());
            universityRepository.save(university);
            return "saved";
        }

    }
    //READ FOR VAZIRLIK
    @GetMapping
    public List<University> getUniversities(){
        return universityRepository.findAll();
    }




}
