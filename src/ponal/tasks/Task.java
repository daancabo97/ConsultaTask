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
    
    
    public static void main(String[] args) throws ClassNotFoundException , SQLException, InstantiationException, IllegalAccessException{
        try {
            String user="UTOIM_OIM";
            String pass="D3v01MDB";
            String url="jdcb:oracle:thin:@10.12.31.74:1521/OCOIMUAT.DATABASE.TDECLNOPROD.ORACLEVCN.COM";
            Connection connection=null;
            
            System.out.println("- - - - - - - - -");
            try {
                Class.forName("oracle.jdbc.OracleDriver");
                connection=DriverManager.getConnection(url, user, pass);
                JOptionPane.showMessageDialog(null,"Conexion realizada");
                System.out.println("Conexion exitosa");
            } catch (Exception e) {
                e.printStackTrace();
            }
            
               
            //DataSource ds = Platform.getOperationalDS();
            //Connection connection = ds.getConnection();
            Statement st = connection.createStatement();
            System.out.println("conectado");

            ResultSet result = st.executeQuery("SELECT USR_LOGIN FROM USR WHERE USR_START_DATE >= '13/01/15';");
            //int uloginIndex = result.findColumn("USR_LOGIN");
            while (result.next()) {
                String userLogin = result.getString("USER_LOGIN");
                System.out.println("USR_LOGIN: " + result.getString(user));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

