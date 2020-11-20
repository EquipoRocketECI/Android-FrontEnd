package equipo.rocket.headhunters.ui.interacciones.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import equipo.rocket.headhunters.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ComentariosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ComentariosFragment extends Fragment {

    public ComentariosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ComentariosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ComentariosFragment newInstance(String param1, String param2) {
        ComentariosFragment fragment = new ComentariosFragment();
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
        return inflater.inflate(R.layout.fragment_comentarios, container, false);
    }
}