package equipo.rocket.headhunters.ui.idea;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import equipo.rocket.headhunters.model.Idea;


public class IdeaViewModel extends ViewModel {

    private MutableLiveData<Integer> id;

    private MutableLiveData<String> nombre;
    private MutableLiveData<String> autor;
    private MutableLiveData<String> categoria;
    private MutableLiveData<String> calificacion;
    private MutableLiveData<String> etapa;
    private MutableLiveData<String> descripcion;

    private MutableLiveData<String> monto;

    private MutableLiveData<Integer> porcent;
    private MutableLiveData<String> fechaLimite;
    private MutableLiveData<String> imagen;

    private static int IdeaID=28;


    public IdeaViewModel(){
        id = new MutableLiveData<>();
        nombre = new MutableLiveData<>();
        descripcion = new MutableLiveData<>();
        categoria = new MutableLiveData<>();
        calificacion = new MutableLiveData<>();
        etapa = new MutableLiveData<>();
        autor = new MutableLiveData<>();
        monto = new MutableLiveData<>();
        porcent = new MutableLiveData<>();
        fechaLimite = new MutableLiveData<>();
        imagen = new MutableLiveData<>();

    }

    public void setmIdea (Idea idea){

        id.setValue(idea.getId());

        nombre.setValue(idea.getNombre());

        descripcion.setValue(idea.getDescripcion());

        categoria.setValue(idea.getCategoria());

        DecimalFormat df = new DecimalFormat("#.0");
        calificacion.setValue(""+df.format(idea.getCalificacion()));

        etapa.setValue(idea.getFase());

        autor.setValue("De: "+idea.getPropietario());

        df = new DecimalFormat("#.##");
        double porcentaje = (100*idea.getMontoRecolectado())/idea.getMontoLimite();//divide by 0
        monto.setValue("Recaudado $"+idea.getMontoRecolectado()+" de $"+idea.getMontoLimite()+" ("+df.format(porcentaje)+"%)");

        int porcentajeaAprox = (int) porcentaje;
        porcent.setValue(porcentajeaAprox);

        Date fechaLim = idea.getFechaLimite();
        SimpleDateFormat Datefor = new SimpleDateFormat("MMM dd yyyy");
        fechaLimite.setValue("Aporta antes de "+Datefor.format(fechaLim));

        imagen.setValue(idea.getImagen());


    };

    public static int getIdeaID(){return IdeaID;};
    public static void setIdeaID(int IdeaID){IdeaID=IdeaID;};

    public LiveData<Integer> getId() { return id; }

    public LiveData<String> getNombre() {
        return nombre;
    }
    public LiveData<String> getDescripcion() {
        return descripcion;
    }
    public LiveData<String> getCategoria() {
        return categoria;
    }
    public LiveData<String> getCalificacion() {
        return calificacion;
    }
    public LiveData<String> getEtapa() {
        return etapa;
    }
    public LiveData<String> getAutor() {
        return autor;
    }

    public LiveData<String> getMonto() {
        return monto;
    }
    public LiveData<String> getFechaLimite() {
        return fechaLimite;
    }
    public LiveData<Integer> getPorcent() {
        return porcent;
    }

    public LiveData<String> getImage() {
        return imagen;
    }

}
