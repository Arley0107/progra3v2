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
import model.Clientes;
import model.Personas;

public class ClienteGestion {

    private static final String SQL_GETCLIENTES = "Select * from clientes";
    private static final String SQL_GETCLIENTELOGIN = "Select * from clientes where correo=? and pwUsuario=MD5(?)";
    private static final String SQL_GETCLIENTE = "Select * from clientes where idCliente=?";
    private static final String SQL_INSERTCLIENTE = "Insert into clientes(idPersona,pwUsuario,correo)values(?,MD5(?),?)";
    private static final String SQL_UPDATECLIENTE = "Update clientes set pwUsuario=MD5(?),correo=? where idEmpleado=? and idPersona=?";
    private static final String SQL_DELETECLIENTE = "Delete from clientes where idCliente=?";
    private static final String SQL_DELETEPERSONA = "Delete from personas where idPersona=?";
    public static Clientes getCliente;

    public static Clientes getClienteLogin(String correo, String pwUsuario) {
        Clientes cliente = null;
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETCLIENTELOGIN);
            sentencia.setString(1, correo);
            sentencia.setString(2, pwUsuario);
            ResultSet rs = sentencia.executeQuery();

            if (rs.next()) {
                cliente = new Clientes();
                cliente.setIdCliente(rs.getInt(1));
                cliente.setIdPersona(rs.getString(2));
                cliente.setPwUsuario(rs.getString(3));
                cliente.setCorreo(rs.getString(4));
                getCliente = cliente;
            }            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cliente;
    }

    public static ArrayList<Clientes> getClientes() {
        ArrayList<Clientes> list = new ArrayList<>();
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETCLIENTES);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                list.add(new Clientes(
                        rs.getInt(1), //idCliente
                        rs.getString(2), //idPersona
                        rs.getString(3), //pwUsuario                                 
                        rs.getString(4) //correo
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static Clientes getCliente(int idCliente) {
        Clientes cliente = null;
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETCLIENTE);
            sentencia.setInt(1, idCliente);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                cliente = new Clientes(
                        rs.getInt(1), //idCliente
                        rs.getString(2), //idPersona
                        rs.getString(3), //pwUsuario                                 
                        rs.getString(4) //correo                      
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cliente;
    }

    public static boolean insertCliente(Clientes cliente) {
        Personas persona = PersonaGestion.getUltimaPersona();
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_INSERTCLIENTE);              
            sentencia.setInt(1, persona.getIdPersona());
            sentencia.setString(2, cliente.getPwUsuario());
            sentencia.setString(3, cliente.getCorreo());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean updateCliente(Clientes cliente) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_UPDATECLIENTE);
            sentencia.setString(1, cliente.getPwUsuario());
            sentencia.setString(2, cliente.getCorreo());
            sentencia.setInt(3, cliente.getIdCliente());
            sentencia.setString(4, cliente.getIdPersona());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean deleteCliente(Clientes cliente) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_DELETECLIENTE);
            sentencia.setInt(1, cliente.getIdCliente());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public static String generarJson() {
        Clientes cliente = null;
        String tiraJson = "";
        try {
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_GETCLIENTES);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                cliente = new Clientes(
                        rs.getInt(1), //idCliente
                        rs.getString(2), //idPersona
                        rs.getString(3), //pwUsuario                                 
                        rs.getString(4) //correo
                );

                JsonObjectBuilder creadorJson = Json.createObjectBuilder();
                JsonObject objectJson = creadorJson.add("idCliente", cliente.getIdCliente())
                        .add("idPersona", cliente.getIdPersona())
                        .add("pwUsuario", cliente.getPwUsuario())
                        .add("correo", cliente.getCorreo())
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
            Logger.getLogger(ClienteGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return tiraJson;
    }

}
