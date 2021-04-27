
package gestion;

import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import model.Conexion;
import model.Roles;

public class RolesGestion {
    
    private static final String SQL_GETROLES = "Select * from roles";
    
    public static String generarJson() {
        Roles rol = null;
        String tiraJson = "";
        try {
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_GETROLES);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                rol = new Roles(
                        rs.getInt(1),       //idRol
                        rs.getString(2),    //nombre
                        rs.getString(3)     //descripcion
                );

                JsonObjectBuilder creadorJson = Json.createObjectBuilder();
                JsonObject objectJson = creadorJson.add("idRol", rol.getIdRol())
                        .add("nombre", rol.getNombre())
                        .add("descripcion", rol.getDescripcion())
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
            Logger.getLogger(RolesGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return tiraJson;
    }

    
}
