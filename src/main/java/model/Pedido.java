
package model;

import java.util.Date;


public class Pedido {
    
    private int idPedido;
    private Date fecha;
    private String idPlato;
    private String idRepartidor;
    private int cantidad;
    private double total;

    public Pedido() {
    }

    public Pedido(int idPedido, Date fecha, String idPlato, String idRepartidor, int cantidad, double total) {
        this.idPedido = idPedido;
        this.fecha = fecha;
        this.idPlato = idPlato;
        this.idRepartidor = idRepartidor;
        this.cantidad = cantidad;
        this.total = total;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getIdPlato() {
        return idPlato;
    }

    public void setIdPlato(String idPlato) {
        this.idPlato = idPlato;
    }

    public String getIdRepartidor() {
        return idRepartidor;
    }

    public void setIdRepartidor(String idRepartidor) {
        this.idRepartidor = idRepartidor;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }        
        
}
