
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
import model.Inventarios;

public class InventarioGestion {
    
    private static final String SQL_GETINVENTARIOS = "Select * from inventarios";
    
    public static String generarJson() {
        Inventarios inventario = null;
        String tiraJson = "";
        try {
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_GETINVENTARIOS);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                inventario = new Inventarios(
                        rs.getInt(1),       //idInventario
                        rs.getString(2),    //nombre
                        rs.getInt(3)        //idSucursal                                                     
                );
                
                JsonObjectBuilder creadorJson = Json.createObjectBuilder();
                JsonObject objectJson = creadorJson.add("idInventario", inventario.getIdInventario())
                        .add("nombre", inventario.getNombre())
                        .add("idSucursal", inventario.getIdSucursal())                        
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
            Logger.getLogger(InventarioGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return tiraJson;
    }

    
}
