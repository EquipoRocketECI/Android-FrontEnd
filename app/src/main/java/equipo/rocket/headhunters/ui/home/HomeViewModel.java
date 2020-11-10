package equipo.rocket.headhunters.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> nombre;
    private MutableLiveData<String> descripcion;
    private MutableLiveData<String> categoria;
    private MutableLiveData<String> calificacion;
    private MutableLiveData<String> etapa;
    private MutableLiveData<String> propietario;

    public HomeViewModel() {

        nombre = new MutableLiveData<>();
        nombre.setValue("Innova Clothing");

        descripcion = new MutableLiveData<>();
        descripcion.setValue("Ropa hecha con un material místico, no se ensucia");

        categoria = new MutableLiveData<>();
        categoria.setValue("Moda");

        calificacion = new MutableLiveData<>();
        calificacion.setValue("5");

        etapa = new MutableLiveData<>();
        etapa.setValue("Consolidada");

        propietario = new MutableLiveData<>();
        propietario.setValue("De: Daniel Cifuentes");
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
    public LiveData<String> getPropietario() {
        return propietario;
    }

}