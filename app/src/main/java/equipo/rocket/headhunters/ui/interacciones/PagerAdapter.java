package equipo.rocket.headhunters.ui.interacciones;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import equipo.rocket.headhunters.ui.interacciones.fragments.ComentariosFragment;
import equipo.rocket.headhunters.ui.interacciones.fragments.DonacionesFragment;
import equipo.rocket.headhunters.ui.interacciones.fragments.InversionesFragment;

public class PagerAdapter extends FragmentPagerAdapter {

    int numOfTabs;

    public PagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.numOfTabs = behavior;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new ComentariosFragment();
        }
        if (position == 1) {
             return new DonacionesFragment();
        } else {
            return new InversionesFragment();
        }
    }


    @Override
    public int getCount() {
        return numOfTabs;
    }
}
