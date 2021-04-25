
package controller;

import gestion.EmpleadoGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.Empleados;

@Named(value = "empleadoController")
@SessionScoped
public class EmpleadoController extends Empleados implements Serializable {
    
    boolean show = false;
    private String tiraJson;
    private boolean noImprimir = true;

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public String getTiraJson() {
        return tiraJson;
    }

    public void setTiraJson(String tiraJson) {
        this.tiraJson = tiraJson;
    }

    public boolean isNoImprimir() {
        return noImprimir;
    }

    public void setNoImprimir(boolean noImprimir) {
        this.noImprimir = noImprimir;
    }       

    public EmpleadoController() {
    }
    
    public String empleado() {
        Empleados empleado = EmpleadoGestion.getEmpleadoLogin(this.getCorreo(), this.getPwUsuario());
        if (empleado != null) {
            if ("1".equals(empleado.getIdRol())) {
                show = true;
            }
            return "principal.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Usuario o contraseña invalida");
            FacesContext.getCurrentInstance().addMessage("adminLoginForm:clave", msg);
            return "index.xhtml";
        }
    }
    
    public List<Empleados> getEmpleados() {
        return EmpleadoGestion.getEmpleados();
    }
    
    public String editaEmpleado(String idEmpleado, String idPersona) {
        Empleados e = EmpleadoGestion.getEmpleado(idEmpleado,idPersona);
        if (e != null) {    
            this.setIdEmpleado(e.getIdEmpleado());
            this.setIdPersona(e.getIdPersona());
            this.setPwUsuario(e.getPwUsuario());
            this.setIdRol(e.getIdRol());
            this.setCorreo(e.getCorreo());            
            return "editarUsuarios.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Posiblemente el registro no exista");
            FacesContext.getCurrentInstance().addMessage("editaUsuarioForm:identificacion", msg);
            return "principal.xhtml";
        }
    }
    
    public String updateEmpleado() {
        if (EmpleadoGestion.updateEmpleado(this)) {
            return "principal.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al actualizar el registro del usuario");
            FacesContext.getCurrentInstance().addMessage("editaUsuarioForm:identificacion", msg);
            return "editarUsuarios.xhtml";
        }
    }

    public String deleteEmpleado() {
        if (EmpleadoGestion.deleteEmpleado(this)) {
            return "listaUsuarios.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al eliminar el usuario");
            FacesContext.getCurrentInstance().addMessage("editaUsuarioForm:identificacion", msg);
            return "editarUsuarios.xhtml";
        }
    }       
    
    public String insertaEmpleado() {        
        if (EmpleadoGestion.insertEmpleado(this)) {
            return "confirmacion.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Posiblemente el usuario se encuentre duplicado.");
            FacesContext.getCurrentInstance().addMessage("ingresaUsuarioForm:identificacion", msg);
            return "registroUsuario.xhtml";
        }
    }
    
    public String logout() {        
            ((HttpSession) FacesContext.getCurrentInstance().getExternalContext()
                    .getSession(false)).invalidate();
            // Conexion.setConn(null);
            return "index.xhtml";        
    }
    
}
