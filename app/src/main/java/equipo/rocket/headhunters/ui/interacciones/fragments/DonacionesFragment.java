package equipo.rocket.headhunters.ui.interacciones.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import equipo.rocket.headhunters.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DonacionesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DonacionesFragment extends Fragment {

    public DonacionesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DonacionesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DonacionesFragment newInstance(String param1, String param2) {
        DonacionesFragment fragment = new DonacionesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_donaciones, container, false);
    }
}