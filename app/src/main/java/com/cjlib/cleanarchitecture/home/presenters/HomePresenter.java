package com.cjlib.cleanarchitecture.home.presenters;

import com.cjlib.cleanarchitecture.R;
import com.cjlib.cleanarchitecture.Utils;
import com.cjlib.cleanarchitecture.base.Presenter;
import com.cjlib.cleanarchitecture.components.ResourceProvider;
import com.cjlib.cleanarchitecture.components.Navigator;
import com.cjlib.cleanarchitecture.home.entities.Movie;
import com.cjlib.cleanarchitecture.home.interactors.MovieInteractor;
import com.cjlib.cleanarchitecture.home.viewmodels.MovieViewModel;
import com.cjlib.cleanarchitecture.home.viewmodels.SearchViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;

public class HomePresenter implements Presenter {

    private MovieInteractor interactor;

    private Navigator navigator;

    private ResourceProvider resourceProvider;

    private BehaviorSubject<SearchViewModel> searchBehaviorSubject = BehaviorSubject.create();

    private SearchViewModel viewModel;

    public HomePresenter(MovieInteractor interactor, Navigator navigator, ResourceProvider provider) {
        this.interactor = interactor;
        this.navigator = navigator;
        this.resourceProvider = provider;
    }

    @Override
    public void onCreate() {
        viewModel = new SearchViewModel();
        searchBehaviorSubject.onNext(viewModel);
    }


    public void search(String query) {
        viewModel.setSearchQuery(query);

        if (Utils.isEmpty(query)) {
            viewModel.processViewModel(null, resourceProvider);
            searchBehaviorSubject.onNext(viewModel);
            return;
        }
        searchMovies(query);
    }


    private void searchMovies(String query) {
        navigator.showProgressDialog(R.string.msg_loading);
        interactor.getListOfMovies(query)
                .map(new Function<List<Movie>, List<MovieViewModel>>() {
                    @Override
                    public List<MovieViewModel> apply(List<Movie> movies) throws Exception {
                        List<MovieViewModel> models = new ArrayList<>();
                        for (Movie movie : movies) {
                            models.add(MovieViewModel.getMovieViewModel(resourceProvider, movie));
                        }
                        return models;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<MovieViewModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<MovieViewModel> moviesModels) {
                        navigator.dismissProgressDialog();
                        viewModel.processViewModel(moviesModels, resourceProvider);
                        searchBehaviorSubject.onNext(viewModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        navigator.dismissProgressDialog();
                        viewModel.processViewModel(null, resourceProvider);
                        searchBehaviorSubject.onNext(viewModel);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public Observable<SearchViewModel> getSearchViewModel() {
        return searchBehaviorSubject;
    }
}
