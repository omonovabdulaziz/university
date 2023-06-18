package uz.pdp.university.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class FacultyDTO {
    private String facultyName;
    private Integer universityId;
}
