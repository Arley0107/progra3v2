
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
import model.DetalleFactura;

public class DetalleFacturaGestion {
    
    private static final String SQL_GETDETALLEFACTURAS = "Select * from detalleFactura";
    private static final String SQL_GETDETALLEFACTURA = "Select * from detalleFactura where idDetalle=?";
    private static final String SQL_INSERTDETALLEFACTURA = "Insert into detalleFactura(idDetalle,idFactura,idProducto,precio,cantidad,subtotal)values(?,?,?,?,?,?)";

    public static ArrayList<DetalleFactura> getDetalleFacturas() {
        ArrayList<DetalleFactura> list = new ArrayList<>();
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETDETALLEFACTURAS);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                list.add(new DetalleFactura(
                        rs.getString(1),  //idDetalle                       
                        rs.getString(2),  //idFactura  
                        rs.getString(3), //idProducto 
                        rs.getDouble(4),     //precio
                        rs.getInt(5),      //cantidad
                        rs.getDouble(6)    //subtotal                        
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DetalleFacturaGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static DetalleFactura getDetalleFactura(String idDetalle) {
        DetalleFactura detalleFactura = null;
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETDETALLEFACTURA);
            sentencia.setString(1, idDetalle);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                detalleFactura = new DetalleFactura(
                        rs.getString(1),  //idDetalle                       
                        rs.getString(2),  //idFactura  
                        rs.getString(3), //idProducto 
                        rs.getDouble(4),     //precio
                        rs.getInt(5),      //cantidad
                        rs.getDouble(6)    //subtotal                        
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(DetalleFacturaGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return detalleFactura;
    }

    public static boolean insertDetalleFactura(DetalleFactura detalleFactura) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_INSERTDETALLEFACTURA);
            sentencia.setString(1, detalleFactura.getIdDetalle());            
            sentencia.setString(2, detalleFactura.getIdFactura()); 
            sentencia.setString(3, detalleFactura.getIdProducto());
            sentencia.setDouble(4, detalleFactura.getPrecio());
            sentencia.setInt(5, detalleFactura.getCantidad());
            sentencia.setDouble(6, detalleFactura.getSubtotal());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DetalleFacturaGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static String generarJson() {
        DetalleFactura detalleFactura = null;
        String tiraJson = "";
        try {
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_GETDETALLEFACTURAS);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                detalleFactura = new DetalleFactura(
                        rs.getString(1),  //idDetalle                       
                        rs.getString(2),  //idFactura  
                        rs.getString(3), //idProducto 
                        rs.getDouble(4),     //precio
                        rs.getInt(5),      //cantidad
                        rs.getDouble(6)    //subtotal    
                );
                               
                JsonObjectBuilder creadorJson = Json.createObjectBuilder();
                JsonObject objectJson = creadorJson.add("idDetalle", detalleFactura.getIdDetalle())
                        .add("idFactura", detalleFactura.getIdFactura())
                        .add("idProducto", detalleFactura.getIdProducto())
                        .add("precio", detalleFactura.getPrecio())
                        .add("cantidad", detalleFactura.getCantidad())
                        .add("subtotal", detalleFactura.getSubtotal())
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
            Logger.getLogger(DetalleFacturaGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return tiraJson;
    }
    
    
    
}
