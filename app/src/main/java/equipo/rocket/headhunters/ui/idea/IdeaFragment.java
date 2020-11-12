package equipo.rocket.headhunters.ui.idea;

import android.os.Bundle;


import androidx.annotation.NonNull;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import equipo.rocket.headhunters.R;
import equipo.rocket.headhunters.ui.gallery.GalleryViewModel;



public class IdeaFragment extends Fragment {

    private IdeaViewModel ideaviewmodel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ideaviewmodel =
                new ViewModelProvider(this).get(IdeaViewModel.class);
        View root = inflater.inflate(R.layout.fragment_idea, container, false);
        //set Nombre
        final TextView nombre = root.findViewById(R.id.textPublicarTitulo2);

        ideaviewmodel.getNombre().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) { nombre.setText(s); }
        });

        final TextView descripcion = root.findViewById(R.id.textView16);
        ideaviewmodel.getDescripcion().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) { descripcion.setText(s); }
        });

        //set categoria
        final TextView categoria = root.findViewById(R.id.textView4);
        ideaviewmodel.getCategoria().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) { categoria.setText(s); }
        });

        //set calificacion
        final TextView calificacion = root.findViewById(R.id.textView6);
        ideaviewmodel.getCalificacion().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) { calificacion.setText(s); }
        });

        //set etapa
        final TextView etapa = root.findViewById(R.id.textView3);
        ideaviewmodel.getEtapa().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) { etapa.setText(s); }
        });

        //set autor
        final TextView autor = root.findViewById(R.id.textView2);
        ideaviewmodel.getAutor().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) { autor.setText(s); }
        });

        return root;
    }
}