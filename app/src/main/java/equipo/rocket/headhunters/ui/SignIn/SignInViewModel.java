package equipo.rocket.headhunters.ui.SignIn;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SignInViewModel extends ViewModel {

    private MutableLiveData<String> correo;
    private MutableLiveData<String> contrasena;
    private MutableLiveData<String> nombres;
    private MutableLiveData<String> apellidos;

    public SignInViewModel() {
        correo = new MutableLiveData<>();
        correo.setValue("Username");

        contrasena = new MutableLiveData<>();
        contrasena.setValue("Password");

        nombres = new MutableLiveData<>();
        nombres.setValue("Nombres completos");

        apellidos = new MutableLiveData<>();
        apellidos.setValue("Apellidos completos");
    }

    public LiveData<String> getCorreo() {return correo;}
    public LiveData<String> getContrasena() {return contrasena;}
    public LiveData<String> getNombres() {return nombres;}
    public LiveData<String> getApellidos() {return apellidos;}

}