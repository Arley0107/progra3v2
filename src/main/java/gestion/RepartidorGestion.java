
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
import model.Repartidor;

public class RepartidorGestion {
    
    private static final String SQL_GETREPARTIDORES = "Select * from repartidor";
    
    
    public static String generarJson() {
        Repartidor repartidor = null;
        String tiraJson = "";        
        try {
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_GETREPARTIDORES);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                repartidor = new Repartidor(                        
                        rs.getString(1), //idRepartidor
                        rs.getString(2), //nombre
                        rs.getString(3), //apellido1            
                        rs.getString(4), //apellido2
                        rs.getString(5), //correo 
                        rs.getString(6), //celular 
                        rs.getString(7).charAt(0), //genero
                        rs.getString(8) //numero placa
                );
                
                JsonObjectBuilder creadorJson = Json.createObjectBuilder();
                JsonObject objectJson = creadorJson.add("idRepartidor", repartidor.getIdRepartidor())                     
                        .add("nombre", repartidor.getNombre())
                        .add("apellido1", repartidor.getApellido1())
                        .add("apellido2", repartidor.getApellido2())
                        .add("correo", repartidor.getCorreo())
                        .add("celular", repartidor.getCelular())
                        .add("genero", repartidor.getGenero())
                        .add("numPlaca", repartidor.getNumPlaca())                        
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
