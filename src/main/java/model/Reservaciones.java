
package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Reservaciones {
    
    private int idReservacion;
    private Date fecha;
    private String hora;
    private int idCliente;
    private int idSucursal;    

    public Reservaciones() {
    }

    public Reservaciones(int idReservacion, Date fecha, String hora, int idCliente, int idSucursal) {
        this.idReservacion = idReservacion;
        this.fecha = fecha;
        this.hora = hora;
        this.idCliente = idCliente;
        this.idSucursal = idSucursal;
    }        

    public Reservaciones(Date fecha, String hora, int idCliente, int idSucursal) {        
        this.fecha = fecha;
        this.hora = hora;
        this.idCliente = idCliente;
        this.idSucursal = idSucursal;
    }

    public int getIdReservacion() {
        return idReservacion;
    }

    public void setIdReservacion(int idReservacion) {
        this.idReservacion = idReservacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }
    
    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String fecha1 = format.format(this.fecha);
        return "{\"Reservacion\":{\n\"fecha\":\""
                + fecha1 + "\",\n\"hora\":\""
                + hora + "\",\n\"idCliente\":\""
                + idCliente + "\",\n\"idSucursal\":\""
                + idSucursal + "\"\n}\n}";

    }
    
}
