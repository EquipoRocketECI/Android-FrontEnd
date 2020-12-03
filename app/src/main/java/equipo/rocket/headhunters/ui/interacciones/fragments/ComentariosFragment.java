package equipo.rocket.headhunters.ui.interacciones.fragments;

import android.content.Context;
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

import java.util.List;

import equipo.rocket.headhunters.R;
import equipo.rocket.headhunters.model.Interaccion;
import equipo.rocket.headhunters.services.InteraccionesServices;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ComentariosFragment extends Fragment {

    private static final String TAG = ComentariosFragment.class.getSimpleName();

    protected RecyclerView comentariosRecyclerView;
    protected RecyclerView.LayoutManager comentariosLayoutManager;
    protected AdapterInteraccion comentariosAdapter;

    protected List<Interaccion> comentariosList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_comentarios, container, false);
        rootView.setTag(TAG);
        comentariosAdapter = new AdapterInteraccion(this);
        comentariosLayoutManager = new LinearLayoutManager(getActivity());

        comentariosRecyclerView = (RecyclerView) rootView.findViewById(R.id.comentariosRecyclerView);

        comentariosRecyclerView.setLayoutManager(comentariosLayoutManager);
        comentariosRecyclerView.scrollToPosition(0);
        comentariosRecyclerView.setAdapter(comentariosAdapter);

        initComentariosList();

        return rootView;
    }

    private void initComentariosList() {

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(InteraccionesServices.INTERACCION_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InteraccionesServices interaccionesServices = retrofit.create(InteraccionesServices.class);

        SharedPreferences sharedPref = this.getActivity().getSharedPreferences( getString( R.string.preference_file_key ), Context.MODE_PRIVATE );
        String ideaId = sharedPref.getString("idea" , "");

        Call<List<Interaccion>> interacciones = interaccionesServices.byIdeaAndTipo(ideaId,"comentario");
        interacciones.enqueue(new Callback<List<Interaccion>>() {

            @Override
            public void onResponse(Call<List<Interaccion>> call, Response<List<Interaccion>> response) {
                comentariosList = response.body();
                comentariosAdapter.setInteraccionesList(comentariosList);
                comentariosAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Interaccion>> call, Throwable t) {
                Log.d(this.getClass().getSimpleName(),t.getMessage() +"||");
            }
        });
    }

}