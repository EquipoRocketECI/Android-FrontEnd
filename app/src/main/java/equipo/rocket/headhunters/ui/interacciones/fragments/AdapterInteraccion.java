package equipo.rocket.headhunters.ui.interacciones.fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import equipo.rocket.headhunters.R;
import equipo.rocket.headhunters.model.Interaccion;

public class AdapterInteraccion extends RecyclerView.Adapter<AdapterInteraccion.InteraccionCardViewHolder> {

    private Fragment fragment;

    private List<Interaccion> interaccionesList;

    public void setInteraccionesList(List<Interaccion> interaccionesList) {

        this.interaccionesList = interaccionesList;
    }

    public List<Interaccion> getInteraccionesList() {

        return interaccionesList;
    }

    public AdapterInteraccion(Fragment fragment){

        this.fragment = fragment;
        setInteraccionesList(new ArrayList<>());
    }

    @NonNull
    @Override
    public InteraccionCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.interaccion_layout,parent,false);

        return new InteraccionCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InteraccionCardViewHolder holder, int position) {

        holder.getUsuarioView().setText(interaccionesList.get(position).getUsuario());
        holder.getComentarioView().setText(interaccionesList.get(position).getComentario());
        holder.getCalificacionView().setText(Integer.toString(interaccionesList.get(position).getCalificacion()));
        if (!interaccionesList.get(position).getTipo().equals("comentario")) {
            holder.getMontoView().setText(Integer.toString(interaccionesList.get(position).getMonto()));
        }
    }

    @Override
    public int getItemCount() {

        return interaccionesList.size();
    }

    public static class InteraccionCardViewHolder extends RecyclerView.ViewHolder{

        private final CardView cardView;

        private final TextView usuarioView;

        private final TextView comentarioView;

        private final TextView calificacionView;

        private final TextView montoView;

        public InteraccionCardViewHolder(View view){

            super(view);

            cardView = (CardView) view.findViewById(R.id.card_view_interaccion);
            usuarioView = (TextView) view.findViewById(R.id.usuario_interaccion);
            comentarioView = (TextView) view.findViewById(R.id.comentario_interaccion);
            calificacionView = (TextView) view.findViewById(R.id.calificacion_interaccion);
            montoView = (TextView) view.findViewById(R.id.monto_interaccion);

        }

        public CardView getCardView(){
            return cardView;
        }
        public TextView getUsuarioView() {
            return usuarioView;
        }
        public TextView getComentarioView() {
            return comentarioView;
        }
        public TextView getCalificacionView() {
            return calificacionView;
        }
        public TextView getMontoView() {
            return montoView;
        }

    }
}