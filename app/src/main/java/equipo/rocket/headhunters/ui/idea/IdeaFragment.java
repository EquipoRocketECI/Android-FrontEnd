package equipo.rocket.headhunters.ui.idea;

import android.content.Intent;
import android.os.Bundle;


import androidx.annotation.NonNull;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import equipo.rocket.headhunters.R;
import equipo.rocket.headhunters.model.Idea;
import equipo.rocket.headhunters.services.IdeaServices;
import equipo.rocket.headhunters.ui.gallery.GalleryViewModel;
import equipo.rocket.headhunters.ui.interacciones.Interacciones;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class IdeaFragment extends Fragment {

    protected RecyclerView mRecyclerView;
    protected RecyclerView.LayoutManager mLayoutManager;
    private IdeaViewModel ideaviewmodel;
    protected Idea mIdea;
    private int ideaId;

    public static final String EXTRA_IDEA_ID = "equipo.rocket.headhunters.EXTRA_IDEA_ID";

    private void initIdea(){

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IdeaServices.IDEA_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IdeaServices ideaServices = retrofit.create(IdeaServices.class);

        Call<Idea> IdeabyId = ideaServices.getIdeabyId(ideaId);
        IdeabyId.enqueue(new Callback<Idea>() {
            @Override
            public void onResponse( Call<Idea> call,  Response<Idea> response) {
                mIdea = response.body();
                ideaviewmodel.setmIdea(mIdea);

            }
            @Override
            public void onFailure( Call<Idea> call, Throwable t) {

                Log.d(this.getClass().getSimpleName(),t.getMessage() +"|||||||||||||||||||");
            }
        });
    };

    public IdeaFragment(int ideaId){
        this.ideaId=ideaId;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        ideaviewmodel =
                new ViewModelProvider(this).get(IdeaViewModel.class);

        View root = inflater.inflate(R.layout.fragment_idea, container, false);

        initIdea();
        //set Nombre
        final TextView nombre = root.findViewById(R.id.textPublicarTitulo2);
        ideaviewmodel.getNombre().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) { nombre.setText(s); }
        });

        final TextView descripcion = root.findViewById(R.id.textView16);
        ideaviewmodel.getDescripcion().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) { descripcion.setText(s); }
        });

        //set categoria
        final TextView categoria = root.findViewById(R.id.textView4);
        ideaviewmodel.getCategoria().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) { categoria.setText(s); }
        });

        //set calificacion
        final TextView calificacion = root.findViewById(R.id.textView6);
        ideaviewmodel.getCalificacion().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) { calificacion.setText(s); }
        });

        //set etapa
        final TextView etapa = root.findViewById(R.id.textView3);
        ideaviewmodel.getEtapa().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) { etapa.setText(s); }
        });

        //set autor
        final TextView autor = root.findViewById(R.id.textView2);
        ideaviewmodel.getAutor().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) { autor.setText(s); }
        });

        //set recuado
        final TextView recaudo = root.findViewById(R.id.textRecaudado);
        ideaviewmodel.getMonto().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) { recaudo.setText(s); }
        });

        //set porcentaje
        final ProgressBar porcentaje = root.findViewById(R.id.progressBar);
        ideaviewmodel.getPorcent().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                porcentaje.setProgress(integer);
            }
        });

        //set antesDe
        final TextView antesDe = root.findViewById(R.id.textView12);
        ideaviewmodel.getFechaLimite().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) { antesDe.setText(s); }
        });

        //set Image
        final ImageView imageView =  root.findViewById(R.id.imageView8);
        ideaviewmodel.getImage().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) { Glide.with(imageView.getContext()).load(s).apply(RequestOptions.fitCenterTransform()).into(imageView); }
        });

        //interacciones
        Button button = root.findViewById(R.id.interaccionesButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Interacciones.class);
                intent.putExtra(EXTRA_IDEA_ID,  ideaviewmodel.getId().getValue().toString());
                startActivity(intent);
            }
        });

        return root;
    }

}