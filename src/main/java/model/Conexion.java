
package model;


import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {

    //jdbc:mysql://localhost:3306/mariannsfood?serverTimezone=UTC
    private static Conexion conexion;
    private static final String DBURL = "jdbc:mysql://localhost:3306/mariannsfood?serverTimezone=UTC";
    private static Connection conn = null;

    public static Connection getConn() {
        return conn;
    }

    public static void setConn(Connection conn) {
        Conexion.conn = conn;
    }
        

    private Conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            conn=DriverManager.getConnection(DBURL, "mariannsfood_user", "12345");
        } catch (ClassNotFoundException | SQLException | NoSuchMethodException | SecurityException
                | InstantiationException | IllegalArgumentException | IllegalAccessException
                | InvocationTargetException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    public static synchronized Connection getConexion(){
        if (conexion==null) {
            conexion= new Conexion();
        }
        return conn;
    }
}
