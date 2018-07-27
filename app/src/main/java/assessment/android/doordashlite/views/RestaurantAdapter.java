package assessment.android.doordashlite.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import assessment.android.doordashlite.R;
import assessment.android.doordashlite.models.Restaurant;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter
        .RestaurantViewHolder> {

    private List<Restaurant> restaurantList;
    private Context context;

    public RestaurantAdapter(Context context){
        restaurantList = new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @Override
    public RestaurantAdapter.RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .restaurant_item_layout, parent, false);
        RestaurantViewHolder viewHolder = new RestaurantViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull RestaurantAdapter.RestaurantViewHolder holder, int position) {
        Restaurant restaurant = restaurantList.get(position);
        holder.textName.setText(restaurant.getName());
        holder.textDescription.setText(restaurant.getDescription());
        holder.testStatus.setText(restaurant.getStatus());
        String imageUrl = restaurant.getCoverImgUrl();
        Picasso.get().load(imageUrl).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    public void setData(List<Restaurant> list){
        restaurantList = list;
        notifyDataSetChanged();
    }

    class RestaurantViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.image) ImageView imageView;
        @BindView(R.id.name) TextView textName;
        @BindView(R.id.description) TextView textDescription;
        @BindView(R.id.status) TextView testStatus;

        public RestaurantViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
