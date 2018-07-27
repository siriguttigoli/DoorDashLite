package assessment.android.doordashlite.views;

import java.util.List;

import assessment.android.doordashlite.models.Restaurant;

public interface RestaurantsViewInterface {
    void onFetchSuccess(List<Restaurant> restaurantList);
    void onFetchError();
}
