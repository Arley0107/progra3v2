package model;


public class Usuario {
    
    private int id;
    private String idUsuario;
    private String pwUsuario;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String correo;
    private String celular;
    private char genero;
    private String domicilio;

    public Usuario() {
    }

    public Usuario(int id, String idUsuario, String pwUsuario, String nombre, String apellido1, String apellido2, String correo, String celular, char genero, String domicilio) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.pwUsuario = pwUsuario;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.correo = correo;
        this.celular = celular;
        this.genero = genero;
        this.domicilio = domicilio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }   

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getPwUsuario() {
        return pwUsuario;
    }

    public void setPwUsuario(String pwUsuario) {
        this.pwUsuario = pwUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }
    
    @Override
    public String toString() {                
        return "{\"Usuario\":{\n\"idUsuario\":\""
                + idUsuario + "\",\n\"nombreUsuario\":\""
                + pwUsuario + "\",\n\"pwUsuario\":\""
                + nombre + "\",\n\"nombre\":\""
                + apellido1 + "\",\n\"apellido1\":\""
                + apellido2 + "\",\n\"apellido2\":\""
                + correo + "\",\n\"correo\":\""
                + celular + "\",\n\"celular\":\""
                + genero + "\",\n\"genero\":\""
                + domicilio + "\"\n}\n}";
    }
    
    public String getNombreCompleto() {
        String texto="";
        texto+=nombre!=null?nombre+" ":"";
        texto+=apellido1!=null?apellido1+" ":"";
        texto+=apellido2!=null?apellido2+" ":"";
        return texto;
}

}
