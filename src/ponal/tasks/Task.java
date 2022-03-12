package ponal.tasks;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import javax.swing.JOptionPane;
import oracle.iam.platform.Platform;

/**
 *
 * @author jusei
 */
public class Task {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        try {
            String user = "PRODOIM_OIM";
            String pass = "PRD33z14";
            String url = "jdcb:oracle:thin:@10.51.12.76:1521:EOIM3PD11";
            Connection connection = null;

            System.out.println("- - - - - - - - -");
            try {
                Class.forName("oracle.jdbc.OracleDriver");
                connection = DriverManager.getConnection(url, user, pass);
                JOptionPane.showMessageDialog(null, "Conexion realizada");
                System.out.println("Conexion exitosa");
            } catch (Exception e) {
                e.printStackTrace();
            }

            //DataSource ds = Platform.getOperationalDS();
            //Connection connection = ds.getConnection();
            
            Statement st = connection.createStatement();
            System.out.println("conectado");
            ResultSet result = st.executeQuery("SELECT USR_START_DATE ,USR_LOGIN FROM USR WHERE USR_START_DATE >= USR_START_DATE - 5");

            //int uloginIndex = result.findColumn("userLogin");
            while (result.next()) {
                String userLogin = result.getString("USR_LOGIN");
                String fecha = result.getString("USR_START_DATE");
                System.out.println("USR_LOGIN: " + fecha + ' ' +userLogin);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
