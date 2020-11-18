package equipo.rocket.headhunters.ui.explore;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
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

public class IdeaCardAdapter extends RecyclerView.Adapter<IdeaCardAdapter.IdeaCardViewHolder> {
    private static final String TAG = IdeaCardAdapter.class.getSimpleName();

    private Fragment fragment;

    public void setmIdeasList(List<Idea> mIdeasList) {
        this.mIdeasList = mIdeasList;
    }

    public List<Idea> getmIdeasList() {
        return mIdeasList;
    }

    private List<Idea> mIdeasList;

    public IdeaCardAdapter(Fragment fragment){

        this.fragment = fragment;
        setmIdeasList(new ArrayList<>());
    }

    @NonNull
    @Override
    public IdeaCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.idea_card_layout,parent,false);//crear layout card para el primer parametro

        return new IdeaCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IdeaCardViewHolder holder, int position) {

        Glide.with(fragment).load(mIdeasList.get(position).getImagen()).apply(RequestOptions.fitCenterTransform()).into(holder.getImageView());
        holder.getTitleView().setText(mIdeasList.get(position).getNombre());
        holder.getDescriptionView().setText(mIdeasList.get(position).getDescripcion());
        //holder.getButton().setEnabled(false);

    //asociar idea particular con la tarjeta que entrega el viewholder
    }

    @Override
    public int getItemCount() {
        return mIdeasList.size();
    }

    //--------------------------------------------------------------------
    // VIEW HOLDER
    //--------------------------------------------------------------------
    public static class IdeaCardViewHolder extends RecyclerView.ViewHolder{

        private final CardView cardView;

        private final TextView titleView;

        private final TextView descriptionView;

        private final ImageView imageView;

        private final Button button;

        //Crear la tarjeta y poner getters
        public IdeaCardViewHolder(View view){
            super(view);
            cardView = (CardView) view.findViewById(R.id.idea_card_view);
            titleView = (TextView) view.findViewById(R.id.idea_card_title);
            descriptionView = (TextView) view.findViewById(R.id.idea_card_description);
            imageView = (ImageView) view.findViewById(R.id.idea_card_image);
            button = (Button) view.findViewById(R.id.idea_card_button);

        }

        public CardView getCardView(){
            return cardView;
        }

        public TextView getTitleView() {
            return titleView;
        }

        public TextView getDescriptionView() {
            return descriptionView;
        }

        public ImageView getImageView() {
            return imageView;
        }

        public Button getButton() {
            return button;
        }
    }
}
