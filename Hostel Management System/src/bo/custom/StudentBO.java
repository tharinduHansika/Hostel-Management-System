package bo.custom;

import bo.SuperBO;
import dto.StudentDTO;

import java.io.IOException;
import java.util.ArrayList;

public interface StudentBO extends SuperBO {
    public boolean save(StudentDTO studentDTO) throws Exception;
    public boolean update(StudentDTO studentDTO) throws Exception;
    public boolean delete(String id) throws Exception;
    public ArrayList<StudentDTO> getAll() throws Exception;
    public boolean studentExist(String id) throws Exception;
    public StudentDTO search(String id) throws IOException;
    public String generateNewStudentID();
}
