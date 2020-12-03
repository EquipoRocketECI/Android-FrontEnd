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

public class DonacionesFragment extends Fragment {

    private static final String TAG = DonacionesFragment.class.getSimpleName();

    protected RecyclerView donacionesRecyclerView;
    protected RecyclerView.LayoutManager donacionesLayoutManager;
    protected AdapterInteraccion donacionesAdapter;

    protected List<Interaccion> donacionesList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_donaciones, container, false);
        rootView.setTag(TAG);
        donacionesAdapter = new AdapterInteraccion(this);
        donacionesLayoutManager = new LinearLayoutManager(getActivity());

        donacionesRecyclerView = (RecyclerView) rootView.findViewById(R.id.donacionesRecyclerView);

        donacionesRecyclerView.setLayoutManager(donacionesLayoutManager);
        donacionesRecyclerView.scrollToPosition(0);
        donacionesRecyclerView.setAdapter(donacionesAdapter);

        initDonacionesList();

        return rootView;
    }

    private void initDonacionesList() {

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(InteraccionesServices.INTERACCION_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InteraccionesServices interaccionesServices = retrofit.create(InteraccionesServices.class);

        SharedPreferences sharedPref = this.getActivity().getSharedPreferences( getString( R.string.preference_file_key ), Context.MODE_PRIVATE );
        String ideaId = sharedPref.getString("idea" , "");

        Call<List<Interaccion>> interacciones = interaccionesServices.byIdeaAndTipo(ideaId,"donacion");
        interacciones.enqueue(new Callback<List<Interaccion>>() {

            @Override
            public void onResponse(Call<List<Interaccion>> call, Response<List<Interaccion>> response) {
                donacionesList = response.body();
                donacionesAdapter.setInteraccionesList(donacionesList);
                donacionesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Interaccion>> call, Throwable t) {
                Log.d(this.getClass().getSimpleName(),t.getMessage() +"||");
            }
        });
    }

}