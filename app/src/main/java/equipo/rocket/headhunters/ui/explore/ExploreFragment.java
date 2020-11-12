package equipo.rocket.headhunters.ui.explore;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import equipo.rocket.headhunters.R;
import equipo.rocket.headhunters.model.Idea;
import equipo.rocket.headhunters.services.IdeaServices;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ExploreFragment extends Fragment {

    private final ExecutorService executorService = Executors.newFixedThreadPool(1);

    protected RecyclerView mRecyclerView;
    protected RecyclerView.LayoutManager mLayoutManager;

    protected List<Idea> mIdeasList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mLayoutManager = new LinearLayoutManager(getActivity());

        //mRecyclerView.setLayoutManager(mLayoutManager);
        //mRecyclerView.scrollToPosition(0);

        initIdeasList();

        //Log.d(this.getClass().getSimpleName(),mIdeasList.get(0).toString());

        return inflater.inflate(R.layout.fragment_explore, container, false);
    }

    private void initIdeasList() {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IdeaServices.IDEA_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IdeaServices ideaServices = retrofit.create(IdeaServices.class);

        Call<List<Idea>> getAllIdeas = ideaServices.getAllIdeas();
        getAllIdeas.enqueue(new Callback<List<Idea>>() {
            @Override
            public void onResponse(Call<List<Idea>> call, Response<List<Idea>> response) {
                mIdeasList = response.body();
            }

            @Override
            public void onFailure(Call<List<Idea>> call, Throwable t) {

                Log.d(this.getClass().getSimpleName(),"ERROR");
            }
        });

         /*executorService.execute(new Runnable() {
             @Override
            public void run() {
                 try {


                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             }
        });*/
    }

}