package equipo.rocket.headhunters.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import equipo.rocket.headhunters.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        //set Nombre
        final TextView nombre = root.findViewById(R.id.text_nombre);
        homeViewModel.getNombre().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) { nombre.setText(s); }
        });

        //set imagen

        //set descripcion
        final TextView descripcion = root.findViewById(R.id.text_descripcion);
        homeViewModel.getDescripcion().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) { descripcion.setText(s); }
        });

        //set categoria
        final TextView categoria = root.findViewById(R.id.text_categoria);
        homeViewModel.getCategoria().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) { categoria.setText(s); }
        });

        //set calificacion
        final TextView calificacion = root.findViewById(R.id.text_calificacion);
        homeViewModel.getCalificacion().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) { calificacion.setText(s); }
        });

        //set etapa
        final TextView etapa = root.findViewById(R.id.text_etapa);
        homeViewModel.getEtapa().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) { etapa.setText(s); }
        });

        //set propietario
        final TextView propietario = root.findViewById(R.id.text_propietario);
        homeViewModel.getPropietario().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) { propietario.setText(s); }
        });


        return root;
    }
}