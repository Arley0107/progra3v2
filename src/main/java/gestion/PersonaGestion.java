package gestion;

import static gestion.ClienteGestion.getCliente;
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
import model.Personas;

public class PersonaGestion {

    private static final String SQL_GETPERSONAS = "Select * from personas";
    private static final String SQL_GETPERSONA = "Select * from personas where idPersona=?";
    private static final String SQL_INSERTPERSONA = "Insert into personas(nombre,apellido1,apellido2,celular,genero,domicilio) values (?,?,?,?,?,?)";
    private static final String SQL_UPDATEPERSONA = "Update personas set nombre=?,apellido1=?,apellido2=?,celular=?,genero=?,domicilio=? where idPersona=?";
    private static final String SQL_GETULTIMAPERSONA = "Select * from personas where idPersona=(select max(idPersona) from personas)";    
    
    public static ArrayList<Personas> getPersonas() {
        ArrayList<Personas> list = new ArrayList<>();
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETPERSONAS);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                list.add(new Personas(
                        rs.getInt(1),      //idPersona
                        rs.getString(2),         //nombre
                        rs.getString(3),      //apellido1                                 
                        rs.getString(4),      //apellido2
                        rs.getString(5),        //celular
                        rs.getString(6).charAt(0), //genero
                        rs.getString(7)       //domiiclio                        
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonaGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static Personas getPersona(int idPersona) {
        Personas persona = null;
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETPERSONA);            
            sentencia.setInt(1, idPersona);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                persona = new Personas(
                        rs.getInt(1),      //idPersona
                        rs.getString(2),         //nombre
                        rs.getString(3),      //apellido1                                 
                        rs.getString(4),      //apellido2
                        rs.getString(5),        //celular
                        rs.getString(6).charAt(0), //genero
                        rs.getString(7)       //domiiclio                      
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonaGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return persona;
    }
    
    public static Personas getUltimaPersona() {    
        Personas persona = null;
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETULTIMAPERSONA);                        
            ResultSet rs = sentencia.executeQuery();
                if (rs.next()) {
                persona = new Personas();
                persona.setIdPersona(rs.getInt(1));
                persona.setNombre(rs.getString(2));
                persona.setApellido1(rs.getString(3));
                persona.setApellido2(rs.getString(4));
                persona.setCelular(rs.getString(5));
                persona.setGenero(rs.getString(6).charAt(0));
                persona.setDomicilio(rs.getString(7));                              
                }
            
        } catch (SQLException ex) {
            Logger.getLogger(PersonaGestion.class.getName()).log(Level.SEVERE, null, ex);
        }    
        return persona;
    }

    public static boolean insertPersona(Personas persona) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_INSERTPERSONA);            
            sentencia.setString(1, persona.getNombre());
            sentencia.setString(2, persona.getApellido1());
            sentencia.setString(3, persona.getApellido2());
            sentencia.setString(4, persona.getCelular());
            sentencia.setString(5, "" + persona.getGenero());
            sentencia.setString(6, persona.getDomicilio());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(PersonaGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean updatePersona(Personas persona) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_UPDATEPERSONA);            
            sentencia.setString(1, persona.getNombre());
            sentencia.setString(2, persona.getApellido1());
            sentencia.setString(3, persona.getApellido2());
            sentencia.setString(4, persona.getCelular());
            sentencia.setString(5, "" + persona.getGenero());
            sentencia.setString(6, persona.getDomicilio());
            sentencia.setInt(7, persona.getIdPersona());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(PersonaGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
    public static boolean deletePersona(Personas persona) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_DELETECLIENTE);
            sentencia.setString(1, persona.getIdPersona());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }
    **/

    public static String generarJson() {
        Personas persona = null;
        String tiraJson = "";
        try {
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_GETPERSONAS);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                persona = new Personas(
                        rs.getInt(1),      //idPersona
                        rs.getString(2),         //nombre
                        rs.getString(3),      //apellido1                                 
                        rs.getString(4),      //apellido2
                        rs.getString(5),        //celular
                        rs.getString(6).charAt(0), //genero
                        rs.getString(7)       //domiiclio 
                );

                JsonObjectBuilder creadorJson = Json.createObjectBuilder();
                JsonObject objectJson = creadorJson.add("idPersona", persona.getIdPersona())
                        .add("nombre", persona.getNombre())
                        .add("apellido1", persona.getApellido1())
                        .add("apellido2", persona.getApellido2())
                        .add("celular", persona.getCelular())
                        .add("genero", persona.getGenero())
                        .add("domicilio", persona.getDomicilio())
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
            Logger.getLogger(PersonaGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return tiraJson;
    }

}
