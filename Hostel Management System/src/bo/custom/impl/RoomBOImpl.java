package bo.custom.impl;

import bo.custom.RoomBO;
import dao.DAOFactory;
import dao.custom.RoomDAO;
import dao.custom.StudentDAO;
import dto.RoomDTO;
import dto.StudentDTO;
import entity.Room;
import entity.Student;

import java.io.IOException;
import java.util.ArrayList;

public class RoomBOImpl implements RoomBO {
    private final RoomDAO roomDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ROOM);

    @Override
    public boolean save(RoomDTO roomDTO) throws Exception {
        return roomDAO.save(new Room(
                roomDTO.getRoomTypeID(),
                roomDTO.getType(),
                roomDTO.getKeyMoney(),
                roomDTO.getQty()

        ));
    }

    @Override
    public boolean update(RoomDTO roomDTO) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String id) throws Exception {
        return false;
    }

    @Override
    public ArrayList<RoomDTO> getAll() throws Exception {
        ArrayList<RoomDTO> allRoom = new ArrayList<>();
        ArrayList<Room> all = (ArrayList<Room>) roomDAO.getAll();
        for (Room room: all) {
            allRoom.add(new RoomDTO(room.getRoomTypeID(),room.getType(),room.getKeyMoney(),room.getQty()));
        }
        return allRoom;
    }

    @Override
    public boolean roomExist(String id) throws Exception {
        //return roomDAO.exists(id);
        return false;
    }

    @Override
    public RoomDTO search(String id) throws IOException {
        Room room =roomDAO.search(id);
        return new RoomDTO(room.getRoomTypeID(),room.getType(),room.getKeyMoney(),room.getQty());
    }

    @Override
    public String generateNewRoomID() {
        return null;
    }
}
