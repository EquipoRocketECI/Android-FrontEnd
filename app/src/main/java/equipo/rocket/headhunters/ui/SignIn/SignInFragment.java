package equipo.rocket.headhunters.ui.SignIn;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.math.BigInteger;
import java.security.MessageDigest;

import equipo.rocket.headhunters.R;
import equipo.rocket.headhunters.model.Login;
import equipo.rocket.headhunters.services.LoginServices;
import equipo.rocket.headhunters.ui.Login.LoginFragment;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SignInFragment extends Fragment {

    protected Login mSignIn;
    public static String getSHA256(String input){

        String toReturn = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.reset();
            digest.update(input.getBytes("utf8"));
            toReturn = String.format("%064x", new BigInteger(1, digest.digest()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return toReturn;
    }

    private SignInViewModel signinViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

    signinViewModel = new ViewModelProvider(this).get(SignInViewModel .class);
    View root = inflater.inflate(R.layout.fragment_signin, container, false);

        Button Registrarse= root.findViewById(R.id.accionRegistarse);;

        Login signin = new Login();



        Registrarse.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final EditText correo = root.findViewById(R.id.text_nuevocorreo);
                final EditText contrasena = root.findViewById(R.id.text_nuevacontrasena);
                final EditText nombres = root.findViewById(R.id.text_nombres);
                final EditText apellidos = root.findViewById(R.id.text_apellidos);
                String nuevoPostContrasena = getSHA256(contrasena.getText().toString());

                correo.getText();
                contrasena.getText();
                nombres.getText();
                apellidos.getText();

                signin.setCorreo(correo.getText().toString());
                signin.setContrasena(nuevoPostContrasena);
                signin.setNombrecompleto(nombres.getText().toString()+" "+apellidos.getText().toString());

                final Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(LoginServices.LOGIN_BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                LoginServices loginServices = retrofit.create(LoginServices.class);

                retrofit2.Call<Login> PostUsuario = loginServices.addUser(signin);
                PostUsuario.enqueue(new Callback<Login>() {

                    @Override
                    public void onResponse(retrofit2.Call<Login> call, Response<Login> response) {

                            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                            builder.setTitle("Correcto");
                            builder.setMessage("El usuario se creo correctamente");
                            builder.setPositiveButton("Aceptar", null);

                            AlertDialog dialog = builder.create();
                            dialog.show();

                            Fragment nuevoFragmento = new LoginFragment();
                            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                            transaction.replace(R.id.nav_signin,nuevoFragmento );
                            transaction.addToBackStack(null);
                            // Commit a la transacción
                            transaction.commit();

                    }

                    @Override
                    public void onFailure(retrofit2.Call<Login> call, Throwable t) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle("Error");
                        builder.setMessage("Nombre de usuario ya tomado");
                        builder.setPositiveButton("Aceptar", null);

                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }


                });
            }
        });
    //set correo
    final TextView correo = root.findViewById(R.id.text_nuevocorreo);
        signinViewModel.getCorreo().observe(getViewLifecycleOwner(), new Observer<String>() {
        @Override
        public void onChanged(@Nullable String s) { correo.setText(s); }
    });

    //set contrasena
    final TextView contrasena = root.findViewById(R.id.text_nuevacontrasena);
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