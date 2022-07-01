package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class  RoomDTO {
    String roomTypeID;
    String type;
    double keyMoney;
    Integer qty;
}
