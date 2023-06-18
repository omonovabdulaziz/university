package uz.pdp.university.payload;


import lombok.Data;

@Data
public class StudentDTO {
    private String firstName;
    private String lastName;
    private Integer groupId;
}
