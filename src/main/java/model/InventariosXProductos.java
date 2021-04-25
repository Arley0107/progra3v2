
package model;

public class InventariosXProductos {
    
    private String idInventario; 
    private String idProducto;
    private int cantidad;

    public InventariosXProductos() {
    }

    public InventariosXProductos(String idInventario, String idProducto, int cantidad) {
        this.idInventario = idInventario;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    public String getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(String idInventario) {
        this.idInventario = idInventario;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
}
