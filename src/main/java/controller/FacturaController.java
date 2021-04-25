
package controller;

import gestion.FacturaGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Facturas;

@Named(value = "facturaController")
@SessionScoped
public class FacturaController extends Facturas implements Serializable {

    public FacturaController() {
    }
    
     public List<Facturas> getFacturas() {
        return FacturaGestion.getFacturas();
    }   
    
     public String insertaFactura() {
        if (FacturaGestion.insertFactura(this)) {
            return "confirmacion.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Posiblemente el usuario se encuentre duplicado.");
            FacesContext.getCurrentInstance().addMessage("ingresaUsuarioForm:identificacion", msg);
            return "registroUsuario.xhtml";
        }
    }
    
}
