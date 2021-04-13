
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
import model.Empleado;

public class EmpleadoGestion {
    
    private static final String SQL_GETEMPLEADOS = "Select * from empleado";
    
    
    public static String generarJson() {
        Empleado empleado = null;
        String tiraJson = "";        
        try {
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_GETEMPLEADOS);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                empleado = new Empleado(
                        rs.getInt(1), //id
                        rs.getString(2), //idEmpleado
                        rs.getString(3), //rol
                        rs.getString(4), //nombre            
                        rs.getString(5), //apellido1
                        rs.getString(6), //apellido2
                        rs.getString(7), //correo
                        rs.getString(8), //celular
                        rs.getString(9).charAt(0), //genero
                        rs.getString(10) //domicilio
                );
                
                JsonObjectBuilder creadorJson = Json.createObjectBuilder();
                JsonObject objectJson = creadorJson.add("idEmpleado", empleado.getIdEmpleado())
                        .add("rol", empleado.getRol())
                        .add("nombre", empleado.getNombre())
                        .add("apellido1", empleado.getApellido1())
                        .add("apellido2", empleado.getApellido2())
                        .add("correo", empleado.getCorreo())
                        .add("celular", empleado.getCelular())
                        .add("genero", empleado.getGenero())
                        .add("domicilio", empleado.getDomicilio())                        
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
