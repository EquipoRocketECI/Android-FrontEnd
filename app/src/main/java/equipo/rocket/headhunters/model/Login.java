package equipo.rocket.headhunters.model;

public class Login {


    private String correo;
    private String contrasena;

    private String nombrecompleto;

    public Login(){

    }
    public Login(String correo, String contrasena,
                String nombrecompleto) {
        this.correo  = correo;
        this.contrasena = contrasena;
        this.nombrecompleto = nombrecompleto;
    }


    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getContrasena() {
        return contrasena;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombrecompleto() {
        return nombrecompleto;
    }
    public void setNombrecompleto(String nombrecompleto) {
        this.nombrecompleto = nombrecompleto;
    }



}
