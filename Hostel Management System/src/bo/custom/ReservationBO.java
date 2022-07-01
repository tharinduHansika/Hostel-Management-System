package bo.custom;

import bo.SuperBO;
import dto.ReservationDTO;
import dto.StudentDTO;

import java.io.IOException;
import java.util.ArrayList;

public interface ReservationBO extends SuperBO {
    public boolean save(ReservationDTO reservationDTO) throws Exception;
    public boolean update(ReservationDTO reservationDTO) throws Exception;
    public boolean delete(String id) throws Exception;
    public ArrayList<ReservationDTO> getAll() throws Exception;
    public boolean studentExist(String id) throws Exception;
    public StudentDTO search(String id);
    public String generateNewReservationID() throws IOException;
}
