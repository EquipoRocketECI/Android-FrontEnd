package equipo.rocket.headhunters.ui.destacadas;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import equipo.rocket.headhunters.R;
import equipo.rocket.headhunters.model.Idea;
import equipo.rocket.headhunters.services.IdeaServices;
import equipo.rocket.headhunters.ui.explore.IdeaCardAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    private static final String TAG = HomeFragment.class.getSimpleName();

    protected RecyclerView destacadasRecyclerView;
    protected RecyclerView.LayoutManager destacadasLayoutManager;
    protected IdeaCardAdapterDestacadas destacadasAdapter;

    protected List<Idea> destacadasList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        rootView.setTag(TAG);
        destacadasAdapter = new IdeaCardAdapterDestacadas(this);
        destacadasLayoutManager = new LinearLayoutManager(getActivity());

        destacadasRecyclerView = (RecyclerView) rootView.findViewById(R.id.destacadasRecyclerView);

        destacadasRecyclerView.setLayoutManager(destacadasLayoutManager);
        destacadasRecyclerView.scrollToPosition(0);
        destacadasRecyclerView.setAdapter(destacadasAdapter);

        initIdeasList();

        return rootView;
    }

    private void initIdeasList() {

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IdeaServices.IDEA_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IdeaServices ideaServices = retrofit.create(IdeaServices.class);

        Call<List<Idea>> ideasDestacadas = ideaServices.getIdeasDestacadas();
        ideasDestacadas.enqueue(new Callback<List<Idea>>() {

            @Override
            public void onResponse(Call<List<Idea>> call, Response<List<Idea>> response) {
                destacadasList = response.body();
                destacadasAdapter.setIdeasList(destacadasList);
                destacadasAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Idea>> call, Throwable t) {
                Log.d(this.getClass().getSimpleName(),t.getMessage() +"|||||||||||||||||||");
            }
        });
    }

}