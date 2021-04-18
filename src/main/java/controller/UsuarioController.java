package controller;

import gestion.UsuarioGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.Conexion;
import model.Usuario;

@Named(value = "usuarioController")
@SessionScoped
public class UsuarioController extends Usuario implements Serializable {

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

    public UsuarioController() {
    }

    public String usuario() {
        Usuario usuario = UsuarioGestion.getUsuarioLogin(this.getIdUsuario(), this.getPwUsuario());
        if (usuario != null) {
            if ("admin".equals(usuario.getIdUsuario())) {
                show = true;
            }
            return "principal.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Usuario o contraseña invalida");
            FacesContext.getCurrentInstance().addMessage("loginForm:clave", msg);
            return "index.xhtml";
        }
    }

    public void buscarUsuario(String idUsuario) {
        Usuario u = UsuarioGestion.buscarUsuario(idUsuario);
        if (u != null) {
            this.setIdUsuario(u.getIdUsuario());
            this.setNombre(u.getNombre());
            this.setApellido1(u.getApellido1());
            this.setApellido2(u.getApellido2());
            this.setCorreo(u.getCorreo());
            this.setCelular(u.getCelular());
            this.setGenero(u.getGenero());
            this.setDomicilio(u.getDomicilio());
            noImprimir = false;
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Posiblemente el registro no exista");
            FacesContext.getCurrentInstance().addMessage("buscarUsuarioForm:identificacion", msg);
            noImprimir = true;
        }
    }

    public List<Usuario> getUsuarios() {
        return UsuarioGestion.getUsuarios();
    }

    public String editaUsuario(int id, String idUsuario) {
        Usuario u = UsuarioGestion.getUsuario(id,idUsuario);
        if (u != null) {    
            this.setId(u.getId());
            this.setIdUsuario(u.getIdUsuario());
            this.setPwUsuario(u.getPwUsuario());
            this.setNombre(u.getNombre());
            this.setApellido1(u.getApellido1());
            this.setApellido2(u.getApellido2());
            this.setCorreo(u.getCorreo());
            this.setCelular(u.getCelular());
            this.setGenero(u.getGenero());
            this.setDomicilio(u.getDomicilio());
            return "editarUsuarios.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Posiblemente el registro no exista");
            FacesContext.getCurrentInstance().addMessage("editaUsuarioForm:identificacion", msg);
            return "principal.xhtml";
        }
    }

    public String updateUsuario() {
        if (UsuarioGestion.updateUsuario(this)) {
            return "principal.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al actualizar el registro del usuario");
            FacesContext.getCurrentInstance().addMessage("editaUsuarioForm:identificacion", msg);
            return "editarUsuarios.xhtml";
        }
    }

    public String deleteEstudiante() {
        if (UsuarioGestion.deleteUsuario(this)) {
            return "listaUsuarios.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al eliminar el usuario");
            FacesContext.getCurrentInstance().addMessage("editaUsuarioForm:identificacion", msg);
            return "editarUsuarios.xhtml";
        }
    }

    public String logout() {        
            ((HttpSession) FacesContext.getCurrentInstance().getExternalContext()
                    .getSession(false)).invalidate();
            // Conexion.setConn(null);
            return "index.xhtml";        
    }

    public String insertaUsuario() {
        if (UsuarioGestion.insertUsuario(this)) {
            return "confirmacion.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Posiblemente el usuario se encuentre duplicado.");
            FacesContext.getCurrentInstance().addMessage("ingresaUsuarioForm:identificacion", msg);
            return "registroUsuario.xhtml";
        }
    }
}
