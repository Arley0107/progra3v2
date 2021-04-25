
package controller;

import gestion.ProductoGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import model.Productos;

@Named(value = "productoController")
@SessionScoped
public class ProductoController implements Serializable {

    public ProductoController() {
    }
    
    public List<Productos> getProductos() {
        return ProductoGestion.getProductos();
    }
    
    
    
}
