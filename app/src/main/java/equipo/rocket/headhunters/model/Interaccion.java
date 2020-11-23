package equipo.rocket.headhunters.model;

public class Interaccion {

    private int id;
    private String tipo;
    private int monto;
    private String comentario;
    private int calificacion;
    private int idea;
    private String usuario;

    public Interaccion(){}

    public Interaccion(int id, String tipo, int monto, String comentario, int calificacion, int idea, String usuario) {
        this.id = id;
        this.tipo = tipo;
        this.monto = monto;
        this.comentario = comentario;
        this.calificacion = calificacion;
        this.idea = idea;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public int getIdea() {
        return idea;
    }

    public void setIdea(int idea) {
        this.idea = idea;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Interaccion{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", monto=" + monto +
                ", comentario='" + comentario + '\'' +
                ", calificacion=" + calificacion +
                ", idea=" + idea +
                ", usuario='" + usuario + '\'' +
                '}';
    }
}
