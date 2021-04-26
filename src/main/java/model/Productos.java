
package model;

public class Productos {
    private int cantidadDetalleFactura = 0;
    
    private int idProducto;
    private String linkImagen;
    private String nombre;
    private String descripcion;
    private Double precio;

    private DetalleFactura detalleFactura;
            
    public Productos() {
    }

    public Productos(int idProducto, String linkImagen, String nombre, String descripcion, Double precio) {
        this.idProducto = idProducto;
        this.linkImagen = linkImagen;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.detalleFactura = new DetalleFactura(cantidadDetalleFactura, precio);
    }
    
    public DetalleFactura getDetalleFactura() {
        return this.detalleFactura;
    }
    
    public Double getSubtotalDetalleFactura() {
        return this.detalleFactura.getSubtotal();
    }

    public int getCantidadDetalleFactura() {
        return detalleFactura.getCantidad();
    }

    public void setCantidadDetalleFactura(int nuevaCantidad) {
        this.detalleFactura.setCantidad(nuevaCantidad);
    }
    
    public void setIdFacturaDetalleFactura(int idFactura) {
        this.detalleFactura.setIdFactura(idFactura);
    }
    
    public int getIdFacturaDetalleFactura(int idFactura) {
        return this.detalleFactura.getIdFactura();
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
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
