package controller;

import gestion.PersonaGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Personas;

@Named(value = "personaController")
@SessionScoped
public class PersonaController extends Personas implements Serializable {

    public PersonaController() {
    }

    public List<Personas> getPersonas() {
        return PersonaGestion.getPersonas();
    }

    public String editaPersona(String idPersona) {
        Personas p = PersonaGestion.getPersona(idPersona);
        if (p != null) {
            this.setIdPersona(p.getIdPersona());
            this.setNombre(p.getNombre());
            this.setApellido1(p.getApellido1());
            this.setApellido2(p.getApellido2());
            this.setCelular(p.getCelular());
            this.setGenero(p.getGenero());
            this.setDomicilio(p.getDomicilio());
            return "editarUsuarios.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Posiblemente el registro no exista");
            FacesContext.getCurrentInstance().addMessage("editaUsuarioForm:identificacion", msg);
            return "principal.xhtml";
        }
    }

    public String updatePersona() {
        if (PersonaGestion.updatePersona(this)) {
            return "principal.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al actualizar el registro del usuario");
            FacesContext.getCurrentInstance().addMessage("editaUsuarioForm:identificacion", msg);
            return "editarUsuarios.xhtml";
        }
    }

    public String insertaPersona() {
        if (PersonaGestion.insertPersona(this)) {
            return "registroCliente.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Posiblemente el usuario se encuentre duplicado.");
            FacesContext.getCurrentInstance().addMessage("ingresaPersonaForm:idPersona", msg);
            return "registroPersona.xhtml";
        }
    }
    
    /**
     * public String delete() { if (ClienteGestion.deleteCliente(this)) { return
     * "listaUsuarios.xhtml"; } else { FacesMessage msg = new
     * FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrio un error al
     * eliminar el usuario");
     * FacesContext.getCurrentInstance().addMessage("editaUsuarioForm:identificacion",
     * msg); return "editarUsuarios.xhtml"; } }
    * *
     */

}
