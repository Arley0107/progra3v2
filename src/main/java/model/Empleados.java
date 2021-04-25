
package model;

public class Empleados {
    
    private String idEmpleado;
    private String idPersona;
    private String pwUsuario;
    private String idRol;
    private String correo;

    public Empleados() {
    }

    public Empleados(String idEmpleado, String idPersona, String pwUsuario, String idRol, String correo) {
        this.idEmpleado = idEmpleado;
        this.idPersona = idPersona;
        this.pwUsuario = pwUsuario;
        this.idRol = idRol;
        this.correo = correo;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

    public String getPwUsuario() {
        return pwUsuario;
    }

    public void setPwUsuario(String pwUsuario) {
        this.pwUsuario = pwUsuario;
    }

    public String getIdRol() {
        return idRol;
    }

    public void setIdRol(String idRol) {
        this.idRol = idRol;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }       
    
}
