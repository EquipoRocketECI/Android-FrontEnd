package equipo.rocket.headhunters.ui.SignIn;

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


public class SignInFragment extends Fragment {

    private SignInViewModel signinViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

    signinViewModel = new ViewModelProvider(this).get(SignInViewModel .class);
    View root = inflater.inflate(R.layout.fragment_signin, container, false);

    //set correo
    final TextView correo = root.findViewById(R.id.text_correo);
        signinViewModel.getCorreo().observe(getViewLifecycleOwner(), new Observer<String>() {
        @Override
        public void onChanged(@Nullable String s) { correo.setText(s); }
    });

    //set contrasena
    final TextView contrasena = root.findViewById(R.id.text_contrasena);
        signinViewModel.getContrasena().observe(getViewLifecycleOwner(), new Observer<String>() {
        @Override
        public void onChanged(@Nullable String s) { contrasena.setText(s); }
    });

    //set nombrecompleto

        final TextView nombres = root.findViewById(R.id.text_nombres);
        signinViewModel.getNombres().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) { nombres.setText(s); }
        });

        final TextView apellidos = root.findViewById(R.id.text_apellidos);
        signinViewModel.getApellidos().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) { apellidos.setText(s); }
        });


        return root;
}
}