
package model;

public class Empleados {
    
    private int idEmpleado;
    private int idPersona;
    private String pwUsuario;
    private int idRol;
    private String correo;

    public Empleados() {
    }

    public Empleados(int idEmpleado, int idPersona, String pwUsuario, int idRol, String correo) {
        this.idEmpleado = idEmpleado;
        this.idPersona = idPersona;
        this.pwUsuario = pwUsuario;
        this.idRol = idRol;
        this.correo = correo;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getPwUsuario() {
        return pwUsuario;
    }

    public void setPwUsuario(String pwUsuario) {
        this.pwUsuario = pwUsuario;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }       
    
}
