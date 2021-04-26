
package model;

public class Clientes {
    
    private int idCliente;
    private String idPersona;
    private String pwUsuario;
    private String correo;

    public Clientes() {
    }

    public Clientes(int idCliente, String idPersona, String pwUsuario, String correo) {
        this.idCliente = idCliente;
        this.idPersona = idPersona;
        this.pwUsuario = pwUsuario;
        this.correo = correo;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }    
    
    @Override
    public String toString() {        
        return "{\"Cliente\":{\n\"idCliente\":\""
                + idCliente + "\",\n\"idPersona\":\""
                + idPersona + "\",\n\"pwUsuario\":\""
                + pwUsuario + "\",\n\"correo\":\""
                + correo + "\"\n}\n}";

    }
}
