package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Room implements SuperEntity{
    @Id
    String roomTypeID;
    String type;
    double keyMoney;
    Integer qty;
    @OneToMany(mappedBy = "room", fetch = FetchType.EAGER)
    private List<Reservartion> reservationList=new ArrayList();

    public Room(String roomTypeID, String type, double keyMoney, Integer qty) {
        this.roomTypeID = roomTypeID;
        this.type = type;
        this.keyMoney = keyMoney;
        this.qty = qty;
    }
}
