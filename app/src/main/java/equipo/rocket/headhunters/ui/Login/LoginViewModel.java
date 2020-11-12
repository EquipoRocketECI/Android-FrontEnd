package equipo.rocket.headhunters.ui.Login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<String> correo;
    private MutableLiveData<String> contrasena;
    private MutableLiveData<String> nombrecompleto;

    public LoginViewModel() {
        correo = new MutableLiveData<>();
        correo.setValue("Username");

        contrasena = new MutableLiveData<>();
        contrasena.setValue("Password");

        nombrecompleto = new MutableLiveData<>();
        nombrecompleto.setValue("Nombre completo del usuario");
    }

    public LiveData<String> getCorreo() {return correo;}
    public LiveData<String> getContrasena() {return contrasena;}
    public LiveData<String> getNombreCompleto() {return nombrecompleto;}

}