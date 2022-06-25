//JDBC
import java.sql.*;

public class Conn {
    //create connection
    Connection c;
    public Conn(){
        try{
            //register the driver
            String url = "jdbc:mysql://localhost:3306/Bank_Management_System";
            String name = "root";
            String pass = "";
            c = DriverManager.getConnection(url,name,pass);
        }catch(Exception e){
            System.out.println(e);
        }
    }

}
