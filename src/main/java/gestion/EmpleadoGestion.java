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
                empleado.setIdEmpleado(rs.getString(1));
                empleado.setIdPersona(rs.getString(2));
                empleado.setPwUsuario(rs.getString(3));
                empleado.setIdRol(rs.getString(4));
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
                        rs.getString(1), //idEmpleado
                        rs.getString(2), //idPersona
                        rs.getString(3), //pwUsuario         
                        rs.getString(4), //idRol
                        rs.getString(5) //correo
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public static Empleados getEmpleado(String idEmpleado, String idPersona) {
        Empleados empleado = null;
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETEMPLEADO);
            sentencia.setString(1, idEmpleado);
            sentencia.setString(2, idPersona);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                empleado = new Empleados(                        
                        rs.getString(1), //idEmpleado
                        rs.getString(2), //idPersona
                        rs.getString(3), //pwUsuario         
                        rs.getString(4), //idRol
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
            sentencia.setString(1, empleado.getIdEmpleado());
            sentencia.setString(2, empleado.getIdPersona());            
            sentencia.setString(3, empleado.getPwUsuario());
            sentencia.setString(4, empleado.getIdRol());
            sentencia.setString(5, empleado.getCorreo());                       
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
            sentencia.setString(2, empleado.getIdRol());
            sentencia.setString(3, empleado.getCorreo());
            sentencia.setString(4, empleado.getIdEmpleado());
            sentencia.setString(5, empleado.getIdPersona());            
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean deleteEmpleado(Empleados empleado) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_DELETEEMPLEADO);
            sentencia.setString(1, empleado.getIdEmpleado());
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
                        rs.getString(1), //idEmpleado
                        rs.getString(2), //idPersona
                        rs.getString(3), //pwUsuario           
                        rs.getString(4), //idRol
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
