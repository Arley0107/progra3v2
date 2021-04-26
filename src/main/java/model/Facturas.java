
package model;

import java.util.Date;

public class Facturas {
    
    private int idFactura;
    private int codigo;
    private int idCliente;
    private Date fecha;
    private Double total;

    public Facturas() {
    }

    public Facturas(int idFactura, int codigo, int idCliente, Date fecha, Double total) {
        this.idFactura = idFactura;
        this.codigo = codigo;
        this.idCliente = idCliente;
        this.fecha = fecha;
        this.total = total;
    }

    public Facturas(int codigo, int idCliente, Date fecha, Double total) {
        this.codigo = codigo;
        this.idCliente = idCliente;
        this.fecha = fecha;
        this.total = total;
    }        

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
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
