package equipo.rocket.headhunters.ui.Login;

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
import androidx.recyclerview.widget.RecyclerView;

import java.math.BigInteger;
import java.security.MessageDigest;

import equipo.rocket.headhunters.R;
import equipo.rocket.headhunters.model.Login;
import equipo.rocket.headhunters.services.LoginServices;
import equipo.rocket.headhunters.ui.destacadas.HomeFragment;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginFragment extends Fragment {

    protected RecyclerView mRecyclerView;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected Login mLogin;

    private LoginViewModel loginViewModel;


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


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

    loginViewModel = new ViewModelProvider(this).get(LoginViewModel .class);
    View root = inflater.inflate(R.layout.fragment_login, container, false);

        Button Ingresar= root.findViewById(R.id.accionLoguearse);;



        Ingresar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println("lalal");
                final Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(LoginServices.LOGIN_BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                LoginServices loginServices = retrofit.create(LoginServices.class);
                final EditText correo = root.findViewById(R.id.text_correo);
                final EditText contrasena = root.findViewById(R.id.text_contrasena);

                correo.getText();
                contrasena.getText();


                String nuevaContrasena = getSHA256(contrasena.getText().toString());

                retrofit2.Call<Login> UsuarioByCorreo = loginServices.getUsuario(correo.getText().toString());
                UsuarioByCorreo.enqueue(new Callback<Login>() {

                    @Override
                    public void onResponse(retrofit2.Call<Login> call, Response<Login> response) {
                        mLogin = response.body();
                        System.out.println(mLogin.getContrasena());
                        System.out.println(nuevaContrasena);
                        if (mLogin.getContrasena().equals(nuevaContrasena)){
                            Fragment nuevoFragmento = new HomeFragment();
                            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                            transaction.replace(R.id.nav_login,nuevoFragmento );
                            transaction.addToBackStack(null);

                            // Commit a la transacción
                            transaction.commit();

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
            }
        });
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