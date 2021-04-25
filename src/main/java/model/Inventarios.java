
package model;

public class Inventarios {
    
    private String idInventario;
    private String nombre;
    private String idSucursal;

    public Inventarios() {
    }

    public Inventarios(String idInventario, String nombre, String idSucursal) {
        this.idInventario = idInventario;
        this.nombre = nombre;
        this.idSucursal = idSucursal;
    }

    public String getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(String idInventario) {
        this.idInventario = idInventario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(String idSucursal) {
        this.idSucursal = idSucursal;
    }        
    
}
