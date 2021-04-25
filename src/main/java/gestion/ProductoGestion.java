
package gestion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Conexion;
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
                        rs.getString(1),    //idProducto
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

    public static Productos getProducto(String idProducto) {
        Productos producto = null;
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETPRODUCTO);            
            sentencia.setString(1, idProducto);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                producto = new Productos(
                        rs.getString(1),    //idProducto
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
    
}
