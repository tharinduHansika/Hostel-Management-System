package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student implements SuperEntity{
    @Id
    String studentID;
    String name;
    @Column(columnDefinition = "TEXT")
    String address;
    String contactNo;
    LocalDate DOB;
    String gender;
    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
    private List<Reservartion> reservationList=new ArrayList();

    public Student(String studentID, String name, String address, String contactNo, LocalDate DOB, String gender) {
        this.studentID = studentID;
        this.name = name;
        this.address = address;
        this.contactNo = contactNo;
        this.DOB = DOB;
        this.gender = gender;
    }
}
