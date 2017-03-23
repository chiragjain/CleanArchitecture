package com.cjlib.cleanarchitecture.home.viewmodels;

import com.cjlib.cleanarchitecture.R;
import com.cjlib.cleanarchitecture.Utils;
import com.cjlib.cleanarchitecture.base.ViewModel;
import com.cjlib.cleanarchitecture.components.ResourceProvider;

import java.util.ArrayList;
import java.util.List;

public class SearchViewModel implements ViewModel {

    private List<MovieViewModel> movieViewModels = new ArrayList<>();

    private String persistenceError;

    private String searchQuery;

    private boolean isErrorVisible;

    private boolean isListVisible;

    public List<MovieViewModel> getMovieViewModels() {
        return movieViewModels;
    }

    public void setMovieViewModels(List<MovieViewModel> movieViewModels) {
        this.movieViewModels = movieViewModels;
    }

    public String getPersistenceError() {
        return persistenceError == null ? "" : persistenceError;
    }

    public void setPersistenceError(String persistenceError) {
        this.persistenceError = persistenceError;
    }

    public String getSearchQuery() {
        return searchQuery == null ? "" : searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public boolean isErrorVisible() {
        return isErrorVisible;
    }

    public void setErrorVisible(boolean errorVisible) {
        isErrorVisible = errorVisible;
    }

    public boolean isListVisible() {
        return isListVisible;
    }

    public void setListVisible(boolean listVisible) {
        isListVisible = listVisible;
    }

    public void processViewModel(List<MovieViewModel> movies, ResourceProvider resourceProvider) {

        if (Utils.isEmpty(getSearchQuery())) {
            setErrorVisible(true);
            setListVisible(false);
            setPersistenceError(resourceProvider.getString(R.string.error_no_search_query));
            return;

        }

        if (movies == null || movies.size() == 0) {
            setErrorVisible(true);
            setListVisible(false);
            setPersistenceError(resourceProvider.getString(R.string.error_no_result_found));
            return;
        }

        setErrorVisible(false);
        setListVisible(true);
        setPersistenceError(null);
        setMovieViewModels(movies);
    }
}
