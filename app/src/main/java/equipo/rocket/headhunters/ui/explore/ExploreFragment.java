package equipo.rocket.headhunters.ui.explore;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.List;

import equipo.rocket.headhunters.R;
import equipo.rocket.headhunters.model.Idea;
import equipo.rocket.headhunters.services.IdeaServices;
import equipo.rocket.headhunters.ui.explore.filter.FilterDialogFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//maybe reimplement with activity?
public class ExploreFragment extends Fragment implements FilterDialogFragment.FilterDialogListener {

    private static final String TAG = ExploreFragment.class.getSimpleName();

    protected RecyclerView mRecyclerView;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected IdeaCardAdapter mAdapter;

    protected List<Idea> mIdeasList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_explore, container, false);
        rootView.setTag(TAG);
        mAdapter = new IdeaCardAdapter(this);
        mLayoutManager = new LinearLayoutManager(getActivity());

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.exploreRecyclerView);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(0);
        mRecyclerView.setAdapter(mAdapter);

        FloatingActionButton filterBtn = rootView.findViewById(R.id.filterBtn);
        filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFilterDialog(view);
            }
            });

        initIdeasList();
        //filter();

        return rootView;
    }

    private void initIdeasList() {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IdeaServices.IDEA_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IdeaServices ideaServices = retrofit.create(IdeaServices.class);

        Call<List<Idea>> getAllIdeas = ideaServices.getAllIdeas();
        Log.d(this.getClass().getSimpleName(),getAllIdeas.request().toString());
        getAllIdeas.enqueue(new Callback<List<Idea>>() {
            @Override
            public void onResponse(Call<List<Idea>> call, Response<List<Idea>> response) {
                mIdeasList = response.body();
                mAdapter.setmIdeasList(mIdeasList);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Idea>> call, Throwable t) {

                Log.d(this.getClass().getSimpleName(),t.getMessage() +"|||||||||||||||||||");
            }
        });
    }

    private void filter(JsonObject selectedFilters){
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://mysterious-refuge-36454.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IdeaServices ideaServices = retrofit.create(IdeaServices.class);
        //melectedFilters = new JsonParser().parse("{ \"selectedCategories\":{ \"Moda\":true}}").getAsJsonObject();//change to gson, avoid valuepair

        Call<List<Idea>> filter = ideaServices.filter(selectedFilters);
        filter.enqueue(new Callback<List<Idea>>() {
            @Override
            public void onResponse(Call<List<Idea>> call, Response<List<Idea>> response) {
                mIdeasList=response.body();
                mAdapter.setmIdeasList(mIdeasList);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Idea>> call, Throwable t) {
                Log.d(this.getClass().getSimpleName(),t.getMessage()+"||||||----");
            }
        });
    }

    public void showFilterDialog(View view){//perhaps should have been a side sheet https://material.io/components/sheets-side, too late to change now
        //implement button for filters
        DialogFragment filterDialog = new FilterDialogFragment();
        filterDialog.show(getParentFragmentManager(),"filters");
    }

    @Override
    public void onDialogPositiveClick(FilterDialogFragment dialog) {
        filter(dialog.getSelectedFilters());
    }

    @Override
    public void onDialogNegativeClick(FilterDialogFragment dialog) {
        dialog.clearSelectedFilters();
    }
}