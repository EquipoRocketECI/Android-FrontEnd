package equipo.rocket.headhunters.ui.gallery;

import android.content.DialogInterface;
import  android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import equipo.rocket.headhunters.R;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        final TextView textView = root.findViewById(R.id.textPublicarTitulo);
        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        final TextView incentivosTexto =  root.findViewById(R.id.textView17);
        final CheckBox premium= root.findViewById(R.id.checkBox7);
        final CheckBox descuentos= root.findViewById(R.id.checkBox8);
        final CheckBox adqTemprana= root.findViewById(R.id.checkBox9);

        final TextView montoTexto =  root.findViewById(R.id.textView14);
        final EditText montoEspacio = root.findViewById(R.id.editTextNumberSigned);

        final CheckBox donaciones= root.findViewById(R.id.checkBox);

        donaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(donaciones.isChecked()){
                    incentivosTexto.setVisibility(View.VISIBLE);
                    premium.setVisibility(View.VISIBLE);
                    descuentos.setVisibility(View.VISIBLE);
                    adqTemprana.setVisibility(View.VISIBLE);

                    montoTexto.setVisibility(View.VISIBLE);
                    montoEspacio.setVisibility(View.VISIBLE);
                }else{
                    incentivosTexto.setVisibility(View.GONE);
                    premium.setVisibility(View.GONE );
                    descuentos.setVisibility(View.GONE );
                    adqTemprana.setVisibility(View.GONE );

                    montoTexto.setVisibility(View.GONE );
                    montoEspacio.setVisibility(View.GONE );
                }
            }
        });


        final CheckBox inversiones= root.findViewById(R.id.checkBox2);

        inversiones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inversiones.isChecked()){
                    montoTexto.setVisibility(View.VISIBLE);
                    montoEspacio.setVisibility(View.VISIBLE);
                }else{
                    montoTexto.setVisibility(View.GONE );
                    montoEspacio.setVisibility(View.GONE );
                }
            }
        });

        final CheckBox expertos= root.findViewById(R.id.checkBox3);

        final TextView areasTexto =  root.findViewById(R.id.textView18);
        final CheckBox derecho= root.findViewById(R.id.checkBox10);
        final CheckBox economia= root.findViewById(R.id.checkBox11);
        final CheckBox ingenieria= root.findViewById(R.id.checkBox12);

        expertos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(expertos.isChecked()){
                    areasTexto.setVisibility(View.VISIBLE);
                    derecho.setVisibility(View.VISIBLE);
                    economia.setVisibility(View.VISIBLE);
                    ingenieria.setVisibility(View.VISIBLE);
                }else{
                    areasTexto.setVisibility(View.GONE);
                    derecho.setVisibility(View.GONE );
                    economia.setVisibility(View.GONE );
                    ingenieria.setVisibility(View.GONE );
                }
            }
        });

        return root;
    }
}