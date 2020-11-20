package equipo.rocket.headhunters.ui.Login;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import equipo.rocket.headhunters.R;
import equipo.rocket.headhunters.model.Login;
import equipo.rocket.headhunters.services.LoginServices;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginFragment extends Fragment {

    protected RecyclerView mRecyclerView;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected Login mLogin;

    private LoginViewModel loginViewModel;



    private void LoginClicked(View root){

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LoginServices.LOGIN_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LoginServices loginServices = retrofit.create(LoginServices.class);
        final EditText correo = root.findViewById(R.id.text_correo);
        final EditText contrasena = root.findViewById(R.id.text_contrasena);

        correo.getText();
        contrasena.getText();

        retrofit2.Call<Login> UsuarioByCorreo = loginServices.getUsuario(correo.getText().toString());
        UsuarioByCorreo.enqueue(new Callback<Login>() {

            @Override
            public void onResponse(retrofit2.Call<Login> call, Response<Login> response) {
                mLogin = response.body();
                if (mLogin.getContrasena()==contrasena.getText().toString()){

                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Error");
                    builder.setMessage("La contrasena es incorrecta");
                    builder.setPositiveButton("Aceptar", null);

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }

            }

            @Override
            public void onFailure(retrofit2.Call<Login> call, Throwable t) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Error");
                builder.setMessage("El correo no existe");
                builder.setPositiveButton("Aceptar", null);

                AlertDialog dialog = builder.create();
                dialog.show();
            }


        });
    };

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