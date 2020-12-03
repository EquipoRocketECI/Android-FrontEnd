package equipo.rocket.headhunters.ui.interacciones.post.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.List;

import equipo.rocket.headhunters.MainActivity;
import equipo.rocket.headhunters.R;
import equipo.rocket.headhunters.model.Interaccion;
import equipo.rocket.headhunters.services.InteraccionesServices;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InvertirFragment extends Fragment {

    private static final String TAG = InvertirFragment.class.getSimpleName();

    protected RecyclerView inversionesRecyclerView;
    protected RecyclerView.LayoutManager inversionesLayoutManager;
    protected AdapterInteraccionPost inversionesAdapter;

    protected List<Interaccion> inversionesList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_invertir, container, false);
        rootView.setTag(TAG);
        inversionesAdapter = new AdapterInteraccionPost(this);
        inversionesLayoutManager = new LinearLayoutManager(getActivity());

        inversionesRecyclerView = (RecyclerView) rootView.findViewById(R.id.invertirRecyclerView);

        inversionesRecyclerView.setLayoutManager(inversionesLayoutManager);
        inversionesRecyclerView.scrollToPosition(0);
        inversionesRecyclerView.setAdapter(inversionesAdapter);

        Interaccion interaccion = new Interaccion();

        Button buttonInteractuar = rootView.findViewById(R.id.buttonEnviarInvertir);
        buttonInteractuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get values
                String tipo = "inversion";

                EditText editTextNumber = (EditText) rootView.findViewById(R.id.editTextNumberInvertir);
                int monto = Integer.parseInt(editTextNumber.getText().toString());

                EditText editText = (EditText) rootView.findViewById(R.id.comentarioInvercion);
                String comentario = editText.getText().toString();

                RadioGroup rg = (RadioGroup) rootView.findViewById(R.id.radio_group_calificacionInvertir);
                final int calificacion = Integer.parseInt(((RadioButton) rootView.findViewById(rg.getCheckedRadioButtonId())).getText().toString());

                SharedPreferences sharedPref = getActivity().getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);

                int idea = Integer.parseInt(sharedPref.getString("idea", ""));
                String user = sharedPref.getString("user", "");

                //set values

                interaccion.setTipo(tipo);
                interaccion.setMonto(monto);
                interaccion.setComentario(comentario);
                interaccion.setCalificacion(calificacion);
                interaccion.setIdea(idea);
                interaccion.setUsuario(user);

                final Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(InteraccionesServices.INTERACCION_BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                InteraccionesServices interaccionesServices = retrofit.create(InteraccionesServices.class);

                Call<Interaccion> interacciones = interaccionesServices.addInteraccion(interaccion);

                interacciones.enqueue(new Callback<Interaccion>() {

                    @Override
                    public void onResponse(Call<Interaccion> call, Response<Interaccion> response) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle("Comentario Exitoso");
                        builder.setMessage("Ha comentado exitosamente este proyecto");
                        builder.setPositiveButton("Aceptar", null);

                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }

                    @Override
                    public void onFailure(Call<Interaccion> call, Throwable t) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle("Error");
                        builder.setMessage("Necesita iniciar sesión para poder comentar");
                        builder.setPositiveButton("Aceptar", null);

                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                });

                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

}