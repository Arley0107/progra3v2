
package controller;

import gestion.FacturaGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.DetalleFactura;
import model.Facturas;
import model.Productos;

@Named(value = "facturaController")
@SessionScoped
public class FacturaController extends Facturas implements Serializable {

    public FacturaController() {
    }
    
     public List<Facturas> getFacturas() {
        return FacturaGestion.getFacturas();
    }   
    
     public String insertaFactura(Productos listaProductos) {
        Facturas plantillaFactura = new Facturas(codigo, fecha, total, idCliente);
        FacturaGestion.insertFactura(plantillaFactura);
        factura = FacturaGestion.getUltimaFactura();
        if (factura) {
            for listaProductos : producto
                    producto.setIdFacturaDetalleFactura(factura.idFactura);
                    insertaDetalleFactura(producto.getDetalleFactura())
        }
        if () {
            return "confirmacion.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Posiblemente el usuario se encuentre duplicado.");
            FacesContext.getCurrentInstance().addMessage("ingresaUsuarioForm:identificacion", msg);
            return "registroUsuario.xhtml";
        }
    }
    
     private void insertaDetalleFactura(DetalleFactura detalleFactura){
         // INSERT A LA BD DE DETALLE FACTURA
     }
     
     private int calculaTotal(Productos listaProductos) {
         total = 0
         for listaProductos : producto
                 total += producto.getSubtotalDetalleFactura()
     }
}
