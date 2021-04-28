
package model;

public class Personas {
    
    private int idPersona;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String celular;
    private char genero;
    private String domicilio;

    public Personas() {
    }

    public Personas(int idPersona, String nombre, String apellido1, String apellido2, String celular, char genero, String domicilio) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.celular = celular;
        this.genero = genero;
        this.domicilio = domicilio;
    }
    
    public Personas(String nombre, String apellido1, String apellido2, String celular, char genero, String domicilio) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.celular = celular;
        this.genero = genero;
        this.domicilio = domicilio;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
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
        
}
