package equipo.rocket.headhunters.ui.gallery;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import  android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import equipo.rocket.headhunters.R;
import equipo.rocket.headhunters.model.Idea;
import equipo.rocket.headhunters.services.IdeaServices;
import equipo.rocket.headhunters.ui.interacciones.Interacciones;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    protected Idea newIdea;

    private Spinner spinnerCategoria;
    private EditText editTextNombre;
    private EditText editTextDescripcion;
    private CheckBox CheckBoxDonaciones;
    private CheckBox CheckBoxInversiones;
    private CheckBox CheckBoxExpertos;
    private CheckBox premium;
    private CheckBox descuentos;
    private CheckBox adqTemprana;
    private EditText editTextMonto;
    private EditText editTextDate;

    String Categoria;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        final TextView textView = root.findViewById(R.id.textPublicarTitulo);
        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        final TextView incentivosTexto =  root.findViewById(R.id.textView17);
        premium= root.findViewById(R.id.checkBox7);
        descuentos= root.findViewById(R.id.checkBox8);
        adqTemprana= root.findViewById(R.id.checkBox9);

        final TextView montoTexto =  root.findViewById(R.id.textView14);
        editTextMonto = root.findViewById(R.id.editTextNumberSigned);

        CheckBoxDonaciones= root.findViewById(R.id.checkBox);

        CheckBoxDonaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CheckBoxDonaciones.isChecked()){
                    incentivosTexto.setVisibility(View.VISIBLE);
                    premium.setVisibility(View.VISIBLE);
                    descuentos.setVisibility(View.VISIBLE);
                    adqTemprana.setVisibility(View.VISIBLE);

                    montoTexto.setVisibility(View.VISIBLE);
                    editTextMonto.setVisibility(View.VISIBLE);
                }else{
                    incentivosTexto.setVisibility(View.GONE);
                    premium.setVisibility(View.GONE );
                    descuentos.setVisibility(View.GONE );
                    adqTemprana.setVisibility(View.GONE );

                    montoTexto.setVisibility(View.GONE );
                    editTextMonto.setVisibility(View.GONE );
                }
            }
        });


        CheckBoxInversiones= root.findViewById(R.id.checkBox2);

        CheckBoxInversiones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CheckBoxInversiones.isChecked()){
                    montoTexto.setVisibility(View.VISIBLE);
                    editTextMonto.setVisibility(View.VISIBLE);
                }else{
                    montoTexto.setVisibility(View.GONE );
                    editTextMonto.setVisibility(View.GONE );
                }
            }
        });

        CheckBoxExpertos= root.findViewById(R.id.checkBox3);

        final TextView areasTexto =  root.findViewById(R.id.textView18);
        final CheckBox derecho= root.findViewById(R.id.checkBox10);
        final CheckBox economia= root.findViewById(R.id.checkBox11);
        final CheckBox ingenieria= root.findViewById(R.id.checkBox12);

        CheckBoxExpertos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CheckBoxExpertos.isChecked()){
                    areasTexto.setVisibility(View.VISIBLE);
                    derecho.setVisibility(View.VISIBLE);
                    economia.setVisibility(View.VISIBLE);
                    ingenieria.setVisibility(View.VISIBLE);
                }else{
                    areasTexto.setVisibility(View.GONE);
                    derecho.setVisibility(View.GONE );
                    economia.setVisibility(View.GONE );
                    ingenieria.setVisibility(View.GONE );
                }
            }
        });


        EditText etPlannedDate = (EditText) root.findViewById(R.id.editTextDate);
        etPlannedDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.editTextDate:
                        showDatePickerDialog(etPlannedDate);
                        break;
                }
            }
        });


        spinnerCategoria = root.findViewById(R.id.categorias_spinner);
        spinnerCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Categoria = spinnerCategoria.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        editTextNombre = root.findViewById(R.id.nombre_idea);
        editTextDescripcion = root.findViewById(R.id.descripcion_idea);
        editTextDate = root.findViewById(R.id.editTextDate);

        Button button = root.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSubmitClicked(view);
            }
        });




        return root;
    }

    private void showDatePickerDialog(EditText etPlannedDate) {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero

                final String selectedDate = day + "/" + (month+1) + "/" + year;
                etPlannedDate.setText(selectedDate);
            }
        });

        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }

    public void onSubmitClicked(View view){

        Idea idea = new Idea();

        idea.setCategoria(Categoria);

        String Nombre = editTextNombre.getText().toString();
        idea.setNombre(Nombre);

        String Descripcion = editTextDescripcion.getText().toString();
        idea.setDescripcion(Descripcion);

        idea.setPequenasdonaciones(CheckBoxDonaciones.isChecked());

        idea.setGrandesinversiones(CheckBoxInversiones.isChecked());

        idea.setExpertospersonal(CheckBoxExpertos.isChecked());

        idea.setVersionpremium(premium.isChecked());

        idea.setDescuento(descuentos.isChecked());

        idea.setAdquisiciontemprana(adqTemprana.isChecked());

        String Monto = editTextMonto.getText().toString();
        int montoInt=Integer.parseInt(Monto);
        idea.setMontoLimite(montoInt);

        DateFormat formatter = new SimpleDateFormat("d/M/yyyy");

        String FechaLimite = editTextDate.getText().toString();
        try {
            Date dateObject = formatter.parse(FechaLimite);
            idea.setFechaLimite(dateObject);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IdeaServices.IDEA_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IdeaServices ideaServices = retrofit.create(IdeaServices.class);


        Call<Idea> postIdea = ideaServices.postIdea(idea);

        postIdea.enqueue(new Callback<Idea>() {
            @Override
            public void onResponse( Call<Idea> call,  Response<Idea> response) {
                if(response.isSuccessful()) {
                    newIdea =response.body();
                    Snackbar.make(view, "Idea "+newIdea.getId()+" publicada", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }else{
                    Snackbar.make(view, "Error publicando la ideas", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    Log.d(this.getClass().getSimpleName(),response.message()+"|||||||||||||||||||");
                }

            }
            @Override
            public void onFailure( Call<Idea> call, Throwable t) {

                Log.d(this.getClass().getSimpleName(),t.getMessage() +"|||||||||||||||||||");
            }
        });


        /*
        ESPACIO PARA POST EXPERTOS


        final CheckBox derecho = view.findViewById(R.id.checkBox10);
        derecho.isChecked();

        final CheckBox economia = view.findViewById(R.id.checkBox11);
        economia.isChecked();

        final CheckBox ingenieriea = view.findViewById(R.id.checkBox12);
        ingenieriea.isChecked();

         */

    }
}