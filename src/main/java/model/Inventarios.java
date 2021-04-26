
package model;

public class Inventarios {
    
    private int idInventario;
    private String nombre;
    private int idSucursal;

    public Inventarios() {
    }

    public Inventarios(int idInventario, String nombre, int idSucursal) {
        this.idInventario = idInventario;
        this.nombre = nombre;
        this.idSucursal = idSucursal;
    }

    public int getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(int idInventario) {
        this.idInventario = idInventario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }        
    
}
