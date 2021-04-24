package Dominio;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Usuario implements Serializable {

    private String contrasena;
    private String nombre;
    private String apellidos;
    private String fechaNacimiento;
    private String email;
    private String ultimoAcceso;
    private String foto;
    private int idUser;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }



    @Override
    public String toString() {
        return "Usuario{" +
                "contrasena='" + contrasena + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", email='" + email + '\'' +
                ", ultimoAcceso='" + ultimoAcceso + '\'' +
                ", foto='" + foto + '\'' +
                ", listaPasos=" + listaPasos +
                ", idUser=" + idUser +
                '}';
    }

    public ArrayList<Paso> getListaPasos() {
        return listaPasos;
    }

    public void setListaPasos(ArrayList<Paso> listaPasos) {
        this.listaPasos = listaPasos;
    }

    private ArrayList<Paso> listaPasos;

    private static final SimpleDateFormat dateFormat =
            new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());


    //Constructor leer desde la BBDD
    public Usuario(int idUser, String contrasena, String nombre, String apellidos, String fechaNacimiento, String email, String ultimoAcceso, String foto) {
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
        this.ultimoAcceso = ultimoAcceso;
        this.foto = foto;
        this.idUser = idUser;
        this.listaPasos = new ArrayList<Paso>();
    }


    //Constructor registrar usuario
    public Usuario( String email, String contrasena, String nombre, String apellidos, String fechaNacimiento, String foto) {
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
        this.foto = foto;

        this.listaPasos  = new ArrayList<Paso>();
        this.ultimoAcceso = this.dateFormat.format(new Date());
    }

    //Constructor comprobar si existe usuario
    public Usuario(String email, String contrasena) {
        this.contrasena = contrasena;
        this.email = email;
    }

    public void nuevoPaso(Paso paso) {
        this.listaPasos.add(paso);
    }

    public int getEdad() {
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            Date fechaNacimiento = dateFormat.parse(this.fechaNacimiento);
            Calendar cal = Calendar.getInstance();
            Date fechaActual = cal.getTime();

            return calculaEdad(fechaNacimiento, fechaActual);

        } catch (ParseException e) {
            return 0;
        }
    }

    private int calculaEdad(Date fechaNacimiento, Date fechaActual) {
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        int dIni = Integer.parseInt(formatter.format(fechaNacimiento));
        int dEnd = Integer.parseInt(formatter.format(fechaActual));
        int age = (dEnd-dIni)/10000;
        return age;
    }


    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
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

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUltimoAcceso() {
        return ultimoAcceso;
    }

    public void setUltimoAcceso(String ultimoAcceso) {
        this.ultimoAcceso = ultimoAcceso;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
