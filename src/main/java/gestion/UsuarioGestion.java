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
import model.Usuario;

public class UsuarioGestion {

    private static final String SQL_GETUSUARIOLOGIN = "Select * from usuario where idUsuario=? and pwUsuario=MD5(?)";

    private static final String SQL_GETUSUARIOS = "Select * from usuario";
    private static final String SQL_GETUSUARIO = "Select * from usuario where id=? and idUsuario=?";
    private static final String SQL_INSERTUSUARIO = "Insert into usuario(idUsuario,pwUsuario,nombre,apellido1,apellido2,correo,celular,genero,domicilio)values(?,MD5(?),?,?,?,?,?,?,?)";
    private static final String SQL_UPDATEUSUARIO = "Update usuario set idUsuario=?,pwUsuario=?,nombre=?,apellido1=?,apellido2=?,correo=?,celular=?,genero=?,domicilio=? where id=? and idUsuario=?";
    private static final String SQL_DELETEUSUARIO = "Delete from usuario where idUsuario=?";
    private static final String SQL_GETUSUARIOREPORTE = "SELECT * FROM usuario where idUsuario=?";

    public static Usuario getUsuarioLogin(String idUsuario, String pwUsuario) {
        Usuario usuario = null;
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETUSUARIOLOGIN);
            sentencia.setString(1, idUsuario);
            sentencia.setString(2, pwUsuario);
            ResultSet rs = sentencia.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(idUsuario);
                usuario.setPwUsuario(pwUsuario);
                usuario.setNombre(rs.getString(3));
                usuario.setApellido1(rs.getString(4));
                usuario.setApellido2(rs.getString(5));
                usuario.setCorreo(rs.getString(6));
                usuario.setCelular(rs.getString(7));
                usuario.setGenero(rs.getString(8).charAt(0));
                usuario.setDomicilio(rs.getString(9));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioGestion.class.getName()).log(Level.SEVERE, null, ex);

        }

        return usuario;

    }

    public static ArrayList<Usuario> getUsuarios() {
        ArrayList<Usuario> list = new ArrayList<>();
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETUSUARIOS);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                list.add(new Usuario(
                        rs.getInt(1),
                        rs.getString(2), //idUsuario
                        rs.getString(3), //pwUsuario
                        rs.getString(4), //nombre            
                        rs.getString(5), //apellido1
                        rs.getString(6), //apellido2
                        rs.getString(7), //correo
                        rs.getString(8), //celular
                        rs.getString(9).charAt(0), //genero
                        rs.getString(10) //domicilio
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static Usuario getUsuario(String idUsuario) {
        Usuario usuario = null;
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETUSUARIO);
            sentencia.setString(1, idUsuario);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                usuario = new Usuario(
                        rs.getInt(1),
                        rs.getString(2), //idUsuario
                        rs.getString(3), //pwUsuario
                        rs.getString(4), //nombre            
                        rs.getString(5), //apellido1
                        rs.getString(6), //apellido2
                        rs.getString(7), //correo
                        rs.getString(8), //celular
                        rs.getString(9).charAt(0), //genero
                        rs.getString(10) //domicilio
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }

    public static boolean insertUsuario(Usuario usuario) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_INSERTUSUARIO);
            sentencia.setString(1, usuario.getIdUsuario());
            sentencia.setString(2, usuario.getPwUsuario());            
            sentencia.setString(3, usuario.getNombre());
            sentencia.setString(4, usuario.getApellido1());
            sentencia.setString(5, usuario.getApellido2());
            sentencia.setString(6, usuario.getCorreo());
            sentencia.setString(7, usuario.getCelular());
            sentencia.setString(8, "" + usuario.getGenero());
            sentencia.setString(9, usuario.getDomicilio());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean updateUsuario(Usuario usuario) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_UPDATEUSUARIO);                        
            sentencia.setString(1, usuario.getIdUsuario());
            sentencia.setString(2, usuario.getPwUsuario());            
            sentencia.setString(3, usuario.getNombre());
            sentencia.setString(4, usuario.getApellido1());
            sentencia.setString(5, usuario.getApellido2());
            sentencia.setString(6, usuario.getCorreo());
            sentencia.setString(7, usuario.getCelular());
            sentencia.setString(8, "" + usuario.getGenero());
            sentencia.setString(9, usuario.getDomicilio());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean deleteUsuario(Usuario usuario) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_DELETEUSUARIO);
            sentencia.setString(1, usuario.getIdUsuario());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }
    
    public static Usuario buscarUsuario(String idUsuario) {
        Usuario usuario = null;
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETUSUARIOREPORTE);
            sentencia.setString(1, idUsuario);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                usuario = new Usuario(
                        rs.getInt(1),
                        rs.getString(2), //idUsuario
                        rs.getString(3), //pwUsuario
                        rs.getString(4), //nombre            
                        rs.getString(5), //apellido1
                        rs.getString(6), //apellido2
                        rs.getString(7), //correo
                        rs.getString(8), //celular
                        rs.getString(9).charAt(0), //genero
                        rs.getString(10) //domicilio
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return usuario;
    }
    
    public static String generarJson() {
        Usuario usuario = null;
        String tiraJson = "";        
        try {
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_GETUSUARIOS);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                usuario = new Usuario(
                        rs.getInt(1),
                        rs.getString(2), //idUsuario
                        rs.getString(3), //pwUsuario
                        rs.getString(4), //nombre            
                        rs.getString(5), //apellido1
                        rs.getString(6), //apellido2
                        rs.getString(7), //correo
                        rs.getString(8), //celular
                        rs.getString(9).charAt(0), //genero
                        rs.getString(10) //domicilio
                );
                
                JsonObjectBuilder creadorJson = Json.createObjectBuilder();
                JsonObject objectJson = creadorJson.add("idUsuario", usuario.getIdUsuario())
                        .add("pwUsuario", usuario.getPwUsuario())
                        .add("nombre", usuario.getNombre())
                        .add("apellido1", usuario.getApellido1())
                        .add("apellido2", usuario.getApellido2())
                        .add("correo", usuario.getCorreo())
                        .add("celular", usuario.getCelular())
                        .add("genero", usuario.getGenero())
                        .add("domicilio", usuario.getDomicilio())                        
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
            Logger.getLogger(UsuarioGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return tiraJson;
    }
    
}
