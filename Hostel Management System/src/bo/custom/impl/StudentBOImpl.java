package bo.custom.impl;

import bo.custom.StudentBO;
import dao.DAOFactory;
import dao.custom.StudentDAO;
import dao.custom.impl.StudentDAOImpl;
import dto.StudentDTO;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {
    private final StudentDAO studentDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.STUDENT);

    @Override
    public boolean save(StudentDTO studentDTO) throws Exception {
        return studentDAO.save(new Student(
                studentDTO.getStudentID(),
                studentDTO.getName(),
                studentDTO.getAddress(),
                studentDTO.getContactNo(),
                studentDTO.getDOB(),
                studentDTO.getGender()

        ));
    }

    @Override
    public boolean update(StudentDTO studentDTO) throws Exception {
        return studentDAO.update(new Student(
                studentDTO.getStudentID(),
                studentDTO.getName(),
                studentDTO.getAddress(),
                studentDTO.getContactNo(),
                studentDTO.getDOB(),
                studentDTO.getGender()
        ));
    }

    @Override
    public boolean delete(String id) throws Exception {
        return studentDAO.delete(id);
    }

    @Override
    public ArrayList<StudentDTO> getAll() throws Exception {
        ArrayList<StudentDTO> allStudent = new ArrayList<>();
        ArrayList<Student> all = (ArrayList<Student>) studentDAO.getAll();
        for (Student student: all) {
            allStudent.add(new StudentDTO(student.getStudentID(),student.getName(),student.getAddress(),student.getContactNo(),student.getDOB(),student.getGender()));
        }
        return allStudent;

    }

    @Override
    public boolean studentExist(String id) throws Exception {
        //return studentDAO.exists(id);
        return false;
    }

    @Override
    public StudentDTO search(String id) throws IOException {
        Student student =studentDAO.search(id);
        return new StudentDTO(student.getStudentID(),student.getName(),student.getAddress(),student.getContactNo(),student.getDOB(),student.getGender());
    }

    @Override
    public String generateNewStudentID() {
        return null;
    }
}
