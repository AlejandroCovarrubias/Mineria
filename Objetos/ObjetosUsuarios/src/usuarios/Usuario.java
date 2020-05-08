package usuarios;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author lv1013
 */
public class Usuario implements Serializable {
    
    private int IDUsuario;
    private String tipo;
    private String nombre;
    private String apellidos;
    private int edad;
    private String telefono;
    private String correoElectronico;
    private String contrasenia;

    public Usuario() {
    }
    
    public Usuario(String tipo, String nombre, String apellidos, int edad, String telefono, String correoElectronico, String contrasenia) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.contrasenia = contrasenia;
    }
    
    public Usuario(int IDUsuario, String tipo, String nombre, String apellidos, int edad, String telefono, String correoElectronico, String contrasenia) {
        this.IDUsuario = IDUsuario;
        this.tipo = tipo;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.contrasenia = contrasenia;
    }

    public int getIDUsuario() {
        return IDUsuario;
    }

    public void setIDUsuario(int IDUsuario) {
        this.IDUsuario = IDUsuario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.correoElectronico);
        hash = 41 * hash + Objects.hashCode(this.contrasenia);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.correoElectronico, other.correoElectronico)) {
            return false;
        }
        if (!Objects.equals(this.contrasenia, other.contrasenia)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario{" + "IDUsuario=" + IDUsuario + ", tipo=" + tipo + ", nombre=" + nombre + ", apellidos=" + apellidos + ", edad=" + edad + ", telefono=" + telefono + ", correoElectronico=" + correoElectronico + ", contrasenia=" + contrasenia + '}';
    }
}