package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reservartion implements SuperEntity{
    @Id
    String res_ID;
    LocalDate date;
    String studentID;
    String roomTypeID;
    String status;

    @ManyToOne
    private Student student;
    @ManyToOne
    private Room room;

    public Reservartion(String res_ID, LocalDate date, String studentID, String roomTypeID, String status) {
        this.res_ID = res_ID;
        this.date = date;
        this.studentID = studentID;
        this.roomTypeID = roomTypeID;
        this.status = status;
    }
}
