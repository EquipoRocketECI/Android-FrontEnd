package equipo.rocket.headhunters.ui.idea;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class IdeaViewModel extends ViewModel {

    private MutableLiveData<String> nombre;
    private MutableLiveData<String> autor;
    private MutableLiveData<String> categoria;
    private MutableLiveData<String> calificacion;
    private MutableLiveData<String> etapa;
    private MutableLiveData<String> descripcion;

    public IdeaViewModel(){
        nombre = new MutableLiveData<>();
        nombre.setValue("Nombre de Idea");

        descripcion = new MutableLiveData<>();
        descripcion.setValue("Descripción de la idea para convencer a donadores e inversores");

        categoria = new MutableLiveData<>();
        categoria.setValue("Moda");

        calificacion = new MutableLiveData<>();
        calificacion.setValue("5");

        etapa = new MutableLiveData<>();
        etapa.setValue("Etapa");

        autor = new MutableLiveData<>();
        autor.setValue("De: Autor");


    }

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
}
