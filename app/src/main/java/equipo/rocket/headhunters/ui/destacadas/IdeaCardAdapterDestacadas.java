package equipo.rocket.headhunters.ui.destacadas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import equipo.rocket.headhunters.R;
import equipo.rocket.headhunters.model.Idea;

public class IdeaCardAdapterDestacadas extends RecyclerView.Adapter<IdeaCardAdapterDestacadas.IdeaCardViewHolder> {

    private Fragment fragment;

    public void setIdeasList(List<Idea> IdeasList) {

        this.IdeasList = IdeasList;
    }

    public List<Idea> getIdeasList() {

        return IdeasList;
    }

    private List<Idea> IdeasList;

    public IdeaCardAdapterDestacadas(Fragment fragment){

        this.fragment = fragment;
        setIdeasList(new ArrayList<>());
    }

    @NonNull
    @Override
    public IdeaCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.idea_card_destacadas_layout,parent,false);

        return new IdeaCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IdeaCardViewHolder holder, int position) {

        Glide.with(fragment).load(IdeasList.get(position).getImagen()).apply(RequestOptions.fitCenterTransform()).into(holder.getImageView());

        holder.getTitleView().setText(IdeasList.get(position).getNombre());
        holder.getDescriptionView().setText(IdeasList.get(position).getDescripcion());
        holder.getCategoriaView().setText(IdeasList.get(position).getCategoria());
        holder.getCalificacionView().setText(String.valueOf(IdeasList.get(position).getCalificacion()));
        //holder.getFaseView().setText("Fase: "+IdeasList.get(position).getFase());
        holder.getPropietarioView().setText("De: "+ IdeasList.get(position).getPropietario() );
        holder.getButton().setEnabled(true);
    }

    @Override
    public int getItemCount() {

        return IdeasList.size();
    }

    public static class IdeaCardViewHolder extends RecyclerView.ViewHolder{

        private final CardView cardView;

        private final TextView titleView;

        private final ImageView imageView;

        private final TextView descriptionView;

        private final TextView categoriaView;

        private final TextView calificacionView;

        private final TextView faseView;

        private final TextView propietarioView;

        private final Button button;

        public IdeaCardViewHolder(View view){
            super(view);
            cardView = (CardView) view.findViewById(R.id.idea_card_viewDestacada);
            titleView = (TextView) view.findViewById(R.id.idea_card_titleDestacada);
            imageView = (ImageView) view.findViewById(R.id.idea_card_imageDestacada);
            descriptionView = (TextView) view.findViewById(R.id.idea_card_descriptionDestacada);
            categoriaView = (TextView) view.findViewById(R.id.idea_card_categoriaDestacada);
            calificacionView = (TextView) view.findViewById(R.id.idea_card_calificacionDestacada);
            faseView = (TextView) view.findViewById(R.id.idea_card_faseDestacada);
            propietarioView = (TextView) view.findViewById(R.id.idea_card_propietarioDestacada);
            button = (Button) view.findViewById(R.id.idea_card_buttonDestacada);

        }

        public CardView getCardView(){
            return cardView;
        }

        public TextView getTitleView() {
            return titleView;
        }

        public ImageView getImageView() {
            return imageView;
        }

        public TextView getDescriptionView() {
            return descriptionView;
        }

        public TextView getCategoriaView() {
            return categoriaView;
        }

        public TextView getCalificacionView() {
            return calificacionView;
        }

        public TextView getFaseView() {
            return faseView;
        }

        public TextView getPropietarioView() {
            return propietarioView;
        }

        public Button getButton() {
            return button;
        }
    }
}
