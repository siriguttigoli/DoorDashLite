package assessment.android.doordashlite.network;

import java.util.List;

import assessment.android.doordashlite.models.Restaurant;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestaurantAPI {

    @GET("v2/restaurant/")
    Observable<List<Restaurant>> getRestaurants(@Query("lat") double latitude, @Query("lng") double
            longitude, @Query("offset") int offset, @Query("limit") int limit);

    @GET("v2/restaurant/{restaurant_id}/")
    Observable<Restaurant> getRestaurantDetails(@Path("restaurant_id") long id);

}
