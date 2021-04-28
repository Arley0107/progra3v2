
package controller;

import gestion.ReservacionesGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Reservaciones;

@Named(value = "reservacionController")
@SessionScoped
public class ReservacionController extends Reservaciones implements Serializable {

    public ReservacionController() {
    }
    
    public List<Reservaciones> getReservaciones() {
        return ReservacionesGestion.getReservaciones();
    }
    
    public String insertaReservacion() {        
        if (ReservacionesGestion.insertReservacion(this)) {
            return "confirmacionReservacion.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Ocurrió un error al hacer la reservación");
            FacesContext.getCurrentInstance().addMessage("realizarReservacionForm:fecha", msg);
            return "realizarReservacion.xhtml";
        }
    }
}
