package assessment.android.doordashlite.network;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.List;

import assessment.android.doordashlite.models.Restaurant;
import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestaurantService implements RestaurantAPI{

    private String BASE_URL = "https://api.doordash.com/";

    private RestaurantAPI api;

    public RestaurantService(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(loggingInterceptor);

        OkHttpClient client = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.api = retrofit.create(RestaurantAPI.class);
    }

    @Override
    public Observable<List<Restaurant>> getRestaurants(double latitude, double longitude, int offset, int limit) {
        return this.api.getRestaurants(latitude, longitude, offset,limit);
    }

    @Override
    public Observable<Restaurant> getRestaurantDetails(long id) {
        return this.api.getRestaurantDetails(id);
    }
}
