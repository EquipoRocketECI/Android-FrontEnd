package equipo.rocket.headhunters.ui.interacciones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import equipo.rocket.headhunters.R;
import equipo.rocket.headhunters.ui.idea.IdeaFragment;

public class Interacciones extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    TabItem tab1, tab2, tab3;
    PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();

        SharedPreferences sharedPref =
                getSharedPreferences( getString( R.string.preference_file_key ), Context.MODE_PRIVATE );
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("idea", intent.getStringExtra(IdeaFragment.EXTRA_IDEA_ID));
        editor.commit();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interacciones);

        tabLayout = findViewById(R.id.tabInteracciones);
        viewPager = findViewById(R.id.viewPagerInteracciones);

        tab1 = findViewById(R.id.tabComentarios);
        tab2 = findViewById(R.id.tabDonaciones);
        tab3 = findViewById(R.id.tabInversiones);

        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition()==0){
                    pagerAdapter.notifyDataSetChanged();
                }
                if (tab.getPosition()==1){
                    pagerAdapter.notifyDataSetChanged();
                }
                if (tab.getPosition()==2){
                    pagerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }
}