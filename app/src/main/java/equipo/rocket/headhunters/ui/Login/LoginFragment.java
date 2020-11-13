package equipo.rocket.headhunters.ui.Login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import equipo.rocket.headhunters.R;
import equipo.rocket.headhunters.ui.Login.LoginViewModel;

public class LoginFragment extends Fragment {

    private LoginViewModel loginViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

    loginViewModel = new ViewModelProvider(this).get(LoginViewModel .class);
    View root = inflater.inflate(R.layout.fragment_login, container, false);

    //set correo
    final TextView correo = root.findViewById(R.id.text_correo);
        loginViewModel.getCorreo().observe(getViewLifecycleOwner(), new Observer<String>() {
        @Override
        public void onChanged(@Nullable String s) { correo.setText(s); }
    });

    //set contrasena
    final TextView contrasena = root.findViewById(R.id.text_contrasena);
        loginViewModel.getContrasena().observe(getViewLifecycleOwner(), new Observer<String>() {
        @Override
        public void onChanged(@Nullable String s) { contrasena.setText(s); }
    });

    //set nombrecompleto




        return root;
}
}