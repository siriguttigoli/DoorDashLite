package assessment.android.doordashlite.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import assessment.android.doordashlite.R;
import assessment.android.doordashlite.models.Restaurant;
import assessment.android.doordashlite.network.RestaurantService;
import assessment.android.doordashlite.presenter.RestaurantPresenter;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements RestaurantsViewInterface {

    private String TAG = this.getClass().getSimpleName();

    @BindView(R.id.restaurant_list)
    RecyclerView restaurantsRecyclerView;
    RestaurantAdapter adapter;
    RestaurantPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setUpViews();
        setUpPresenter();
        fetchRestaurantList();
    }

    void setUpPresenter(){
        RestaurantService service = new RestaurantService();
        presenter = new RestaurantPresenter(this, service, Schedulers.io(),
                AndroidSchedulers.mainThread());
    }

    void setUpViews(){
        adapter = new RestaurantAdapter(this);
        restaurantsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        restaurantsRecyclerView.setAdapter(adapter);
        DividerItemDecoration itemDecor = new DividerItemDecoration(this, DividerItemDecoration
                .VERTICAL );
        restaurantsRecyclerView.addItemDecoration(itemDecor);
    }

    void fetchRestaurantList(){
        presenter.fetchRestaurants();
    }

    @Override
    public void onFetchSuccess(List<Restaurant> restaurantList) {
        adapter.setData(restaurantList);
    }

    @Override
    public void onFetchError() {
        Toast.makeText(this,getString(R.string.error),Toast.LENGTH_SHORT).show();
    }
}
