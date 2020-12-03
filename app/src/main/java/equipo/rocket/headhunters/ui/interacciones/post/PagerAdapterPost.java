package equipo.rocket.headhunters.ui.interacciones.post;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import equipo.rocket.headhunters.ui.interacciones.post.fragments.ComentarFragment;
import equipo.rocket.headhunters.ui.interacciones.post.fragments.DonarFragment;
import equipo.rocket.headhunters.ui.interacciones.post.fragments.InvertirFragment;

public class PagerAdapterPost extends FragmentPagerAdapter {

    int numOfTabs;

    ComentarFragment comentarFragment = new ComentarFragment();
    DonarFragment donarFragment = new DonarFragment();
    InvertirFragment invertirFragment = new InvertirFragment();

    public PagerAdapterPost(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.numOfTabs = behavior;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return comentarFragment;
        }
        if (position == 1) {
            return donarFragment;
        } else {
            return invertirFragment;
        }
    }


    @Override
    public int getCount() {
        return numOfTabs;
    }
}