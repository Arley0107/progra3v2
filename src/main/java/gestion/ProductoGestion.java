
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
import model.Facturas;
import model.Productos;

public class ProductoGestion {
    
    private static final String SQL_GETPRODUCTOS = "Select * from productos";
    private static final String SQL_GETPRODUCTO = "Select * from productos where idProducto=?";
    
     public static ArrayList<Productos> getProductos() {
        ArrayList<Productos> list = new ArrayList<>();
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETPRODUCTOS);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                list.add(new Productos(
                        rs.getInt(1),    //idProducto
                        rs.getString(2),    //linkImagen
                        rs.getString(3),    //nombre                             
                        rs.getString(4),    //descripcion
                        rs.getDouble(5)     //precio
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductoGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static Productos getProducto(int idProducto) {
        Productos producto = null;
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETPRODUCTO);            
            sentencia.setInt(1, idProducto);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                producto = new Productos(
                        rs.getInt(1),    //idProducto
                        rs.getString(2),    //linkImagen
                        rs.getString(3),    //nombre                             
                        rs.getString(4),    //descripcion
                        rs.getDouble(5)     //precio                     
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductoGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return producto;
    }
    
    public static String generarJson() {
        Productos producto = null;
        String tiraJson = "";
        try {
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_GETPRODUCTOS);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                producto = new Productos(
                        rs.getInt(1),    //idProducto
                        rs.getString(2),    //linkImagen
                        rs.getString(3),    //nombre                             
                        rs.getString(4),    //descripcion
                        rs.getDouble(5)     //precio   
                );
                
                JsonObjectBuilder creadorJson = Json.createObjectBuilder();
                JsonObject objectJson = creadorJson.add("idProducto", producto.getIdProducto())
                        .add("linkImagen", producto.getLinkImagen())
                        .add("nombre", producto.getNombre())                        
                        .add("descripcion", producto.getDescripcion())
                        .add("precio", producto.getPrecio())
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
            Logger.getLogger(ProductoGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return tiraJson;
    }

    
}
