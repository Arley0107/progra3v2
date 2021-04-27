
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
import model.InventariosXProductos;

public class InventarioXProductosGestion {
    
     private static final String SQL_GETINVENTARIOXPRODUCTOS = "Select * from inventariosxproductos";
    
    public static String generarJson() {
        InventariosXProductos inventarioXProducto = null;
        String tiraJson = "";
        try {
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_GETINVENTARIOXPRODUCTOS);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                inventarioXProducto = new InventariosXProductos(
                        rs.getInt(1), //idInventario
                        rs.getInt(2), //idProducto
                        rs.getInt(3) //cantidad                                                    
                );
             
                JsonObjectBuilder creadorJson = Json.createObjectBuilder();
                JsonObject objectJson = creadorJson.add("idInventario", inventarioXProducto.getIdInventario())
                        .add("idProducto", inventarioXProducto.getIdProducto())
                        .add("cantidad", inventarioXProducto.getCantidad())                        
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
            Logger.getLogger(InventarioXProductosGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return tiraJson;
    }

    
}
