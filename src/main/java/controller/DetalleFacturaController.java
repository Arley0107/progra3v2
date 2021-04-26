
package controller;

import gestion.DetalleFacturaGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.DetalleFactura;

@Named(value = "detalleFacturaController")
@SessionScoped
public class DetalleFacturaController extends DetalleFactura implements Serializable {

    public DetalleFacturaController() {
    }
    
    public List<DetalleFactura> getDetalleFacturas() {
        return DetalleFacturaGestion.getDetalleFacturas();
    }   
    
     public String insertaDetalleFactura() {
        if (DetalleFacturaGestion.insertDetalleFactura(this)) {
            return "principal.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Posiblemente el usuario se encuentre duplicado.");
            FacesContext.getCurrentInstance().addMessage("menuForm:cantidad", msg);
            return "menu.xhtml";
        }
    }
    
}
