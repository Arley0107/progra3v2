
package model;

public class Productos {
    
    private String idProducto;
    private String linkImagen;
    private String nombre;
    private String descripcion;
    private Double precio;

    public Productos() {
    }

    public Productos(String idProducto, String linkImagen, String nombre, String descripcion, Double precio) {
        this.idProducto = idProducto;
        this.linkImagen = linkImagen;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getLinkImagen() {
        return linkImagen;
    }

    public void setLinkImagen(String linkImagen) {
        this.linkImagen = linkImagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }       
    
}
