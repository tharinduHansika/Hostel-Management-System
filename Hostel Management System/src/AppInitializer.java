import dto.StudentDTO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.FactoryConfiguration;

import java.time.LocalDate;

public class AppInitializer extends Application {
    public static void main(String args[]) {
        launch(args);

        /*StudentDTO s1 = new StudentDTO();

        s1.setStudentID("s001");
        s1.setName("Tharindu");
        s1.setAddress("Matara");
        s1.setContactNo("071-6624548");
        s1.setDOB(LocalDate.parse("1999-03-11"));
        s1.setGender("Male");

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(s1);

        transaction.commit();
        session.close();*/
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("view/LoginForm.fxml"))));
        primaryStage.setTitle("Hostel Management System");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}
