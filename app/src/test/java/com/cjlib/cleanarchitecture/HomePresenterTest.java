package com.cjlib.cleanarchitecture;

import com.cjlib.cleanarchitecture.components.Navigator;
import com.cjlib.cleanarchitecture.components.ResourceProvider;
import com.cjlib.cleanarchitecture.home.entities.Movie;
import com.cjlib.cleanarchitecture.home.interactors.MovieInteractor;
import com.cjlib.cleanarchitecture.home.presenters.HomePresenter;
import com.cjlib.cleanarchitecture.home.viewmodels.SearchViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import rxrules.ImmediateSchedulerRule;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HomePresenterTest {

    @Mock
    MovieInteractor interactor;

    @Mock
    Navigator navigator;

    @Mock
    ResourceProvider provider;

    @InjectMocks
    HomePresenter presenter;

    @Rule
    public final ImmediateSchedulerRule schedulers = new ImmediateSchedulerRule();


    @Before
    public void setUp() {
        presenter.onCreate();
        when(provider.getString(R.string.error_no_search_query)).thenReturn("Search Query is Empty");
        when(provider.getString(R.string.error_no_result_found)).thenReturn("No Result Found!!!");

        when(interactor.getListOfMovies("no_data")).thenReturn(Observable.create(e -> {
            List<Movie> data = new ArrayList<>();
            e.onNext(data);
        }));

        when(interactor.getListOfMovies("matru")).thenReturn(Observable.create(e -> {
            List<Movie> data = new ArrayList<>();
            Movie movie1 = new Movie();
            movie1.title = "Matru ki Bijlee ka Mandola";
            movie1.year = "2013";
            movie1.imdbId = "tt2106537";
            movie1.type = "movie";
            movie1.poster = "https://images-na.ssl-images-amazon.com/images/M/MV5BMTQyMzkxNjI5M15BMl5BanBnXkFtZTcwNDAzODY4OA@@._V1_SX300.jpg";
            data.add(movie1);

            Movie movie2 = new Movie();
            movie2.title = "Pathirai Matru Thangam";
            movie2.year = "1959";
            movie2.imdbId = "tt0368129";
            movie2.type = "movie";
            movie2.poster = "N/A";
            data.add(movie2);

            e.onNext(data);
        }));
    }

    @Test
    public void testInputValidation_EmptyInput() {
        presenter.search("");

        Observable<SearchViewModel> observable = presenter.getSearchViewModel();
        observable.subscribe(viewModel -> {
            assertEquals("Search Query is Empty", viewModel.getPersistenceError());
            assertEquals(true, viewModel.isErrorVisible());
            assertEquals(false, viewModel.isListVisible());
        });
    }

    @Test
    public void testDataValidation_NoData() {
        presenter.search("no_data");

        Observable<SearchViewModel> observable = presenter.getSearchViewModel();
        observable.subscribe(viewModel -> {
            assertEquals("No Result Found!!!", viewModel.getPersistenceError());
            assertEquals(true, viewModel.isErrorVisible());
            assertEquals(false, viewModel.isListVisible());
        });
    }

    @Test
    public void testDataValidation_WithData() {
        presenter.search("matru");

        Observable<SearchViewModel> observable = presenter.getSearchViewModel();
        observable.subscribe(viewModel -> {
            assertEquals(2, viewModel.getMovieViewModels().size());
            assertEquals(false, viewModel.isErrorVisible());
            assertEquals(true, viewModel.isListVisible());
        });
    }


}
