package bo.custom;

import bo.SuperBO;
import dto.RoomDTO;
import dto.StudentDTO;

import java.io.IOException;
import java.util.ArrayList;

public interface RoomBO extends SuperBO {
    public boolean save(RoomDTO roomDTO) throws Exception;
    public boolean update(RoomDTO roomDTO) throws Exception;
    public boolean delete(String id) throws Exception;
    public ArrayList<RoomDTO> getAll() throws Exception;
    public boolean roomExist(String id) throws Exception;
    public RoomDTO search(String id) throws IOException;
    public String generateNewRoomID();
}
