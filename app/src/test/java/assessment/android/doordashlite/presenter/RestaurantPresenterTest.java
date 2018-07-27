package assessment.android.doordashlite.presenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import assessment.android.doordashlite.models.Restaurant;
import assessment.android.doordashlite.network.RestaurantService;
import assessment.android.doordashlite.views.RestaurantsViewInterface;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RestaurantPresenterTest {

    @Mock
    RestaurantsViewInterface restaurantsView;

    @Mock
    RestaurantService restaurantService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void fetchRestaurants_shouldGetRestaurants(){
        

        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(new Restaurant());

        when(restaurantService.getRestaurants(anyDouble(), anyDouble(), anyInt(), anyInt())).thenReturn
                (Observable
                .just
                (restaurants));

        RestaurantPresenter presenter = new RestaurantPresenter(this.restaurantsView, this.restaurantService,
                Schedulers.trampoline(), Schedulers.trampoline());

        presenter.fetchRestaurants();
        verify(restaurantsView).onFetchSuccess(restaurants);
    }

    @Test
    public void fetchRestaurants_shouldShowError() {

        when(restaurantService.getRestaurants(anyDouble(), anyDouble(), anyInt(), anyInt())).thenReturn
                (Observable
                .<List<Restaurant>>error(new Exception()));

        RestaurantPresenter presenter = new RestaurantPresenter(restaurantsView, restaurantService,
                Schedulers.trampoline(), Schedulers.trampoline());

        presenter.fetchRestaurants();
        verify(restaurantsView, times(1)).onFetchError();
        
    }
}