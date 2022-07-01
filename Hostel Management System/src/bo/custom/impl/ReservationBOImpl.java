package bo.custom.impl;

import bo.custom.ReservationBO;
import dao.DAOFactory;
import dao.custom.ReservationDAO;
import dao.custom.RoomDAO;
import dto.ReservationDTO;
import dto.RoomDTO;
import dto.StudentDTO;
import entity.Reservartion;
import entity.Room;
import entity.Student;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ReservationBOImpl implements ReservationBO {
    private final ReservationDAO reservationDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.RESERVATION);

    @Override
    public boolean save(ReservationDTO reservationDTO) throws Exception {
        return reservationDAO.save(new Reservartion(
                reservationDTO.getResID(),
                reservationDTO.getDate(),
                reservationDTO.getStudentID(),
                reservationDTO.getRoomTypeID(),
                reservationDTO.getStatus()
        ));
    }

    @Override
    public boolean update(ReservationDTO reservationDTO) throws Exception {
        return reservationDAO.update(new Reservartion(
                reservationDTO.getResID(),
                reservationDTO.getDate(),
                reservationDTO.getStudentID(),
                reservationDTO.getRoomTypeID(),
                reservationDTO.getStatus()
        ));
    }

    @Override
    public boolean delete(String id) throws Exception {
        return reservationDAO.delete(id);
    }

    @Override
    public ArrayList<ReservationDTO> getAll() throws Exception {
        ArrayList<ReservationDTO> allReservation = new ArrayList<>();
        ArrayList<Reservartion> all = (ArrayList<Reservartion>) reservationDAO.getAll();
        for (Reservartion reservartion: all) {
            allReservation.add(new ReservationDTO(reservartion.getRes_ID(),reservartion.getDate(),reservartion.getStudentID(),reservartion.getRoomTypeID(),reservartion.getStatus()));
        }
        return allReservation;
    }

    @Override
    public boolean studentExist(String id) throws Exception {
        return false;
    }

    @Override
    public StudentDTO search(String id) {
        return null;
    }

    @Override
    public String generateNewReservationID() throws IOException {
        return reservationDAO.generateNewID();
    }
}
