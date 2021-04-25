
package model;

import java.util.Date;

public class Facturas {
    
    private String idFactura;
    private int codigo;
    private String idCliente;
    private Date fecha;
    private Double total;

    public Facturas() {
    }

    public Facturas(String idFactura, int codigo, String idCliente, Date fecha, Double total) {
        this.idFactura = idFactura;
        this.codigo = codigo;
        this.idCliente = idCliente;
        this.fecha = fecha;
        this.total = total;
    }

    public String getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(String idFactura) {
        this.idFactura = idFactura;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }        
    
}
