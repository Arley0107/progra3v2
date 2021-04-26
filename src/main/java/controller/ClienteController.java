package controller;

import gestion.ClienteGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.Clientes;


@Named(value = "clienteController")
@SessionScoped
public class ClienteController extends Clientes implements Serializable {

    private String tiraJson;

    public String getTiraJson() {
        return tiraJson;
    }

    public void setTiraJson(String tiraJson) {
        this.tiraJson = tiraJson;
    }

    public ClienteController() {
    }

    public String cliente() {
        Clientes cliente = ClienteGestion.getClienteLogin(this.getCorreo(), this.getPwUsuario());
        if (cliente != null) {
            return "principal.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Usuario o contraseña invalida");
            FacesContext.getCurrentInstance().addMessage("loginForm:clave", msg);
            return "index.xhtml";
        }
    }

    public List<Clientes> getClientes() {
        return ClienteGestion.getClientes();
    }

    public String editaCliente(int idCliente) {
        Clientes c = ClienteGestion.getCliente(idCliente);
        if (c != null) {
            this.setIdCliente(c.getIdCliente());
            this.setIdPersona(c.getIdPersona());
            this.setPwUsuario(c.getPwUsuario());
            this.setCorreo(c.getCorreo());
            return "editarUsuarios.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Posiblemente el registro no exista");
            FacesContext.getCurrentInstance().addMessage("editaUsuarioForm:identificacion", msg);
            return "principal.xhtml";
        }
    }

    public String updateCliente() {
        if (ClienteGestion.updateCliente(this)) {
            return "principal.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al actualizar el registro del usuario");
            FacesContext.getCurrentInstance().addMessage("editaUsuarioForm:identificacion", msg);
            return "editarUsuarios.xhtml";
        }
    }

    public String deleteCliente() {
        if (ClienteGestion.deleteCliente(this)) {
            return "listaUsuarios.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al eliminar el usuario");
            FacesContext.getCurrentInstance().addMessage("editaUsuarioForm:identificacion", msg);
            return "editarUsuarios.xhtml";
        }
    }

    public String insertaCliente() {
        if (ClienteGestion.insertCliente(this)) {
            return "confirmacion.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Posiblemente el usuario se encuentre duplicado.");
            FacesContext.getCurrentInstance().addMessage("ingresaClienteForm:idCliente", msg);
            return "registroCliente.xhtml";
        }
    }

    public String logout() {
        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext()
                .getSession(false)).invalidate();
        // Conexion.setConn(null);
        return "index.xhtml";
    }

}
