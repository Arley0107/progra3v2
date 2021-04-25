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

public class FacturaGestion {

    private static final String SQL_GETFACTURAS = "Select * from facturas";
    private static final String SQL_GETFACTURA = "Select * from facturas where idFactura=?";
    private static final String SQL_INSERTFACTURA = "Insert into facturas(idFactura,codigo,idCliente,fecha,total)values(?,?,?,now(),?)";

    public static ArrayList<Facturas> getFacturas() {
        ArrayList<Facturas> list = new ArrayList<>();
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETFACTURAS);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                list.add(new Facturas(
                        rs.getString(1), //idFactura
                        rs.getInt(2), //codigo
                        rs.getString(3), //idCliente                              
                        rs.getDate(4), //fecha
                        rs.getDouble(5) //total
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FacturaGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static Facturas getFactura(String idFactura) {
        Facturas factura = null;
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETFACTURA);
            sentencia.setString(1, idFactura);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                factura = new Facturas(
                        rs.getString(1), //idFactura
                        rs.getInt(2), //codigo
                        rs.getString(3), //idCliente                              
                        rs.getDate(4), //fecha
                        rs.getDouble(5) //total                     
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(FacturaGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return factura;
    }

    public static boolean insertFactura(Facturas factura) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_INSERTFACTURA);
            sentencia.setString(1, factura.getIdFactura());
            sentencia.setInt(2, factura.getCodigo());
            sentencia.setString(3, factura.getIdCliente());
            sentencia.setObject(4, factura.getFecha());
            sentencia.setDouble(5, factura.getTotal());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(FacturaGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static String generarJson() {
        Facturas factura = null;
        String tiraJson = "";
        try {
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_GETFACTURAS);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                factura = new Facturas(
                        rs.getString(1), //idFactura
                        rs.getInt(2), //codigo
                        rs.getString(3), //idCliente                              
                        rs.getDate(4), //fecha
                        rs.getDouble(5) //total 
                );

                DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String fecha1 = sdf.format(factura.getFecha());
                JsonObjectBuilder creadorJson = Json.createObjectBuilder();
                JsonObject objectJson = creadorJson.add("idEmpleado", factura.getIdFactura())
                        .add("codigo", factura.getCodigo())
                        .add("idCliente", factura.getIdCliente())
                        .add("fecha", fecha1)
                        .add("correo", factura.getTotal())
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
            Logger.getLogger(FacturaGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return tiraJson;
    }

}
