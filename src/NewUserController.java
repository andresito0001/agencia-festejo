import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import main.java.dao.ClientDAO;
import main.java.entities.Client;

public class NewUserController {
    @FXML
    private TextField textFielCedula;
    @FXML
    private TextField textFieldNombre;
    @FXML
    private TextField textFieldApellido;
    @FXML
    private TextField textFieldDir;
    @FXML
    private TextField textFieldTlf;
    @FXML
    private TextField textFieldMail;
    @FXML
    private Button registerButtom;

    public void registerUser(ActionEvent event) {
        final Client client = new Client (
            textFielCedula.getText(),
            textFieldNombre.getText(),
            textFieldApellido.getText(),
            textFieldDir.getText(),
            textFieldTlf.getText(),
            textFieldMail.getText()
        );

        // ADVERTENCIA: ESTO NO SE DEBE HACER BAJO NINGUNA SITUACION Y DEBE SER ARREGLADO POR UN CONNECTION POOL
        try {         
            final Connection conn = DriverManager.getConnection("", "", "");

            ClientDAO clientDAO = new ClientDAO(conn);
            clientDAO.insertClient(client);
            
            textFielCedula.clear();
            textFieldNombre.clear();
            textFieldApellido.clear();
            textFieldDir.clear();
            textFieldTlf.clear();
            textFieldMail.clear();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}