
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
import model.Sucursales;

public class SucursalesGestion {
    
    private static final String SQL_GETSUCURSALES = "Select * from sucursales";
    
    public static String generarJson() {
        Sucursales sucursal = null;
        String tiraJson = "";
        try {
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_GETSUCURSALES);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                sucursal = new Sucursales(
                        rs.getInt(1),       //idSucursal
                        rs.getString(2),    //nombre
                        rs.getString(3),    //direccion
                        rs.getInt(4)        //idProvincia                                                   
                );
             
                JsonObjectBuilder creadorJson = Json.createObjectBuilder();
                JsonObject objectJson = creadorJson.add("idSucursal", sucursal.getIdSucursal())
                        .add("nombre", sucursal.getNombre())
                        .add("direccion", sucursal.getDireccion()) 
                        .add("idProvincia", sucursal.getIdProvincia()) 
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
            Logger.getLogger(SucursalesGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return tiraJson;
    }

    
}
