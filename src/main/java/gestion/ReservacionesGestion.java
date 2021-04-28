
package gestion;

import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import model.Conexion;
import model.Reservaciones;

public class ReservacionesGestion {
    
    private static final String SQL_GETRESERVACIONES = "Select * from reservaciones";
    private static final String SQL_GETRESERVACION = "Select * from reservaciones where idReservacion=?";
    private static final String SQL_GETRESERVACIONPORCLIENTE = "Select * from reservaciones where idCliente=?";
    private static final String SQL_INSERTRESERVACION = "Insert into reservaciones(fecha,hora,idCliente,idSucursal)values(?,?,?,?)";
    private static final String SQL_DELETERESERVACION = "Delete from reservaciones where idReservacion=?";
    
    
    public static ArrayList<Reservaciones> getReservaciones() {
        ArrayList<Reservaciones> list = new ArrayList<>();
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETRESERVACIONES);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                list.add(new Reservaciones(
                        rs.getDate(1),      //fecha
                        rs.getString(2),    //hora
                        rs.getInt(3),       //idCliente                                 
                        rs.getInt(4)        //idSucursal
                )); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservacionesGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public static Reservaciones getReservacion(int idReservacion) {
        Reservaciones reservacion = null;
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETRESERVACION);
            sentencia.setInt(1, idReservacion);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                reservacion = new Reservaciones(                        
                        rs.getDate(1),      //fecha
                        rs.getString(2),    //hora
                        rs.getInt(3),       //idCliente                                 
                        rs.getInt(4)        //idSucursal
                ); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservacionesGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reservacion;
    }
    
    public static Reservaciones getReservacionPorCliente(int idCliente) {
        Reservaciones reservacion = null;
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETRESERVACIONPORCLIENTE);
            sentencia.setInt(1, idCliente);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                reservacion = new Reservaciones(                        
                        rs.getDate(1),      //fecha
                        rs.getString(2),    //hora
                        rs.getInt(3),       //idCliente                                 
                        rs.getInt(4)        //idSucursal
                ); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservacionesGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reservacion;
    }

    public static boolean insertReservacion(Reservaciones reservacion) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_INSERTRESERVACION);           
            sentencia.setObject(1, reservacion.getFecha());
            sentencia.setString(2, reservacion.getHora());
            sentencia.setInt(3, ClienteGestion.getCliente.getIdCliente());
            sentencia.setInt(4, reservacion.getIdSucursal());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ReservacionesGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static boolean deleteReservacion(Reservaciones reservacion) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_DELETERESERVACION);
            sentencia.setInt(1, reservacion.getIdReservacion());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ReservacionesGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public static String generarJson() {
        Reservaciones reservacion = null;
        String tiraJson = "";
        String fechaReservacion = "";
        
        try {
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_GETRESERVACIONES);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                reservacion = new Reservaciones(
                        rs.getInt(1),       //idReservacion
                        rs.getDate(2),      //fecha
                        rs.getString(3),    //hora
                        rs.getInt(4),       //idCliente                                 
                        rs.getInt(5)        //idSucursal
                );

                DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                fechaReservacion = sdf.format(reservacion.getFecha());
                JsonObjectBuilder creadorJson = Json.createObjectBuilder();
                JsonObject objectJson = creadorJson.add("idReservacion", reservacion.getIdReservacion())
                        .add("fecha", fechaReservacion)
                        .add("hora", reservacion.getHora())
                        .add("idCliente", reservacion.getIdCliente())
                        .add("idSucursal", reservacion.getIdSucursal())
                        .build();
                StringWriter tira = new StringWriter();
                JsonWriter jsonWriter = Json.createWriter(tira);
                jsonWriter.writeObject(objectJson);
                if (tiraJson == null) {
                    tiraJson = tira.toString() + "\n";
                } else {
                    tiraJson = tiraJson + tira.toString() + "\n";
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(ReservacionesGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return tiraJson;
    }

}
