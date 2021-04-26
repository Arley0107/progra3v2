package gestion;

import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import model.Conexion;
import model.Empleados;

public class EmpleadoGestion {

    private static final String SQL_GETEMPLEADOS = "Select * from empleado";
    private static final String SQL_GETEMPLEADOLOGIN = "Select * from empleados where correo=? and pwUsuario=MD5(?)";
    private static final String SQL_GETEMPLEADO = "Select * from empleados where idEmpleado=? and idPersona=?";
    private static final String SQL_INSERTEMPLEADO = "Insert into empleados(idEmpleado,idPersona,pwUsuario,idRol,correo)values(?,?,MD5(?),?,?)";
    private static final String SQL_UPDATEEMPLEADO = "Update empleados set pwUsuario=MD5(?),idRol=?,correo=? where idEmpleado=? and idPersona=?";
    private static final String SQL_DELETEEMPLEADO = "Delete from empleados where idEmpleado=?";
    
    public static Empleados getEmpleadoLogin(String correo, String pwUsuario) {
        Empleados empleado = null;
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETEMPLEADOLOGIN);
            sentencia.setString(1, correo);
            sentencia.setString(2, pwUsuario);
            ResultSet rs = sentencia.executeQuery();

            if (rs.next()) {
                empleado = new Empleados();
                empleado.setIdEmpleado(rs.getInt(1));
                empleado.setIdPersona(rs.getInt(2));
                empleado.setPwUsuario(rs.getString(3));
                empleado.setIdRol(rs.getInt(4));
                empleado.setCorreo(rs.getString(5));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoGestion.class.getName()).log(Level.SEVERE, null, ex);

        }

        return empleado;

    }
    
    public static ArrayList<Empleados> getEmpleados() {
        ArrayList<Empleados> list = new ArrayList<>();
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETEMPLEADOS);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                list.add(new Empleados(                        
                        rs.getInt(1), //idEmpleado
                        rs.getInt(2), //idPersona
                        rs.getString(3), //pwUsuario         
                        rs.getInt(4), //idRol
                        rs.getString(5) //correo
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public static Empleados getEmpleado(int idEmpleado, int idPersona) {
        Empleados empleado = null;
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETEMPLEADO);
            sentencia.setInt(1, idEmpleado);
            sentencia.setInt(2, idPersona);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                empleado = new Empleados(                        
                        rs.getInt(1), //idEmpleado
                        rs.getInt(2), //idPersona
                        rs.getString(3), //pwUsuario         
                        rs.getInt(4), //idRol
                        rs.getString(5) //correo                       
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return empleado;
    }
    
    public static boolean insertEmpleado(Empleados empleado) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_INSERTEMPLEADO);           
            sentencia.setInt(1, empleado.getIdPersona());            
            sentencia.setString(2, empleado.getPwUsuario());
            sentencia.setInt(3, empleado.getIdRol());
            sentencia.setString(4, empleado.getCorreo());                       
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
        
        public static boolean updateEmpleado(Empleados empleado) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_UPDATEEMPLEADO);                                    
            sentencia.setString(1, empleado.getPwUsuario());            
            sentencia.setInt(2, empleado.getIdRol());
            sentencia.setString(3, empleado.getCorreo());
            sentencia.setInt(4, empleado.getIdEmpleado());
            sentencia.setInt(5, empleado.getIdPersona());            
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean deleteEmpleado(Empleados empleado) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_DELETEEMPLEADO);
            sentencia.setInt(1, empleado.getIdEmpleado());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public static String generarJson() {
        Empleados empleado = null;
        String tiraJson = "";
        try {
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_GETEMPLEADOS);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                empleado = new Empleados(
                        rs.getInt(1), //idEmpleado
                        rs.getInt(2), //idPersona
                        rs.getString(3), //pwUsuario           
                        rs.getInt(4), //idRol
                        rs.getString(5) //correo
                );

                JsonObjectBuilder creadorJson = Json.createObjectBuilder();
                JsonObject objectJson = creadorJson.add("idEmpleado", empleado.getIdEmpleado())
                        .add("idPersona", empleado.getIdPersona())
                        .add("pwUsuario", empleado.getPwUsuario())
                        .add("idRol", empleado.getIdRol())
                        .add("correo", empleado.getCorreo())
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
            Logger.getLogger(EmpleadoGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return tiraJson;
    }

}
