package com.cjlib.cleanarchitecture.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.cjlib.cleanarchitecture.AppApplication;
import com.cjlib.cleanarchitecture.R;
import com.cjlib.cleanarchitecture.base.BaseActivity;
import com.cjlib.cleanarchitecture.home.adapters.MovieListAdapter;
import com.cjlib.cleanarchitecture.home.di.DaggerHomeActivityComponent;
import com.cjlib.cleanarchitecture.home.di.HomeActivityComponent;
import com.cjlib.cleanarchitecture.home.di.HomeActivityModule;
import com.cjlib.cleanarchitecture.home.presenters.HomePresenter;
import com.cjlib.cleanarchitecture.home.viewmodels.SearchViewModel;

import javax.inject.Inject;


public class HomeActivity extends BaseActivity {

    private EditText searchQueryView;
    private TextView errorView;
    private RecyclerView movieList;

    @Inject
    HomePresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        HomeActivityComponent component = DaggerHomeActivityComponent.builder()
                .homeActivityModule(new HomeActivityModule(this))
                .applicationComponent(AppApplication.get().getComponent()).build();
        component.inject(this);

        presenter.onCreate();

        initViews();
        subscribeViewModel();
    }

    private void initViews() {
        searchQueryView = (EditText) findViewById(R.id.search_query_view);
        errorView = (TextView) findViewById(R.id.error_view);
        movieList = (RecyclerView) findViewById(R.id.movies_list);
        movieList.setLayoutManager(new LinearLayoutManager(HomeActivity.this));

        searchQueryView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || event.getAction() == KeyEvent.ACTION_DOWN
                        && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {

                    presenter.search(searchQueryView.getText().toString());

                    return true;
                }
                return false;
            }
        });


    }

    private void subscribeViewModel() {
        presenter.getSearchViewModel().subscribe(model -> bindViewModel(model));
    }


    private void bindViewModel(SearchViewModel viewModel) {
        errorView.setText(viewModel.getPersistenceError());
        searchQueryView.setText(viewModel.getSearchQuery());
        errorView.setVisibility(viewModel.isErrorVisible() ? View.VISIBLE : View.GONE);
        movieList.setVisibility(viewModel.isListVisible() ? View.VISIBLE : View.GONE);
        MovieListAdapter adapter = new MovieListAdapter(viewModel.getMovieViewModels());
        movieList.setAdapter(adapter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
