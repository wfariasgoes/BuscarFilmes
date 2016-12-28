package br.com.zup.omdb.buscadorfilmes.view.activity.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import br.com.zup.omdb.buscadorfilmes.R;
import br.com.zup.omdb.buscadorfilmes.application.enums.ControlFrags;
import br.com.zup.omdb.buscadorfilmes.view.fragment.moviedetail.MovieDetailFragment;
import br.com.zup.omdb.buscadorfilmes.view.fragment.movielist.MovieListFragment;
import br.com.zup.omdb.buscadorfilmes.view.fragment.moviesearch.MovieSearchFragment;

public class MainActivity extends AbstractFragmentActivity {
    private TextView                 mTxtEmpty;
    private transient TextView       textLoading;
    private transient View           viewLoadingText;
    private transient View           viewLoading;
    protected BottomNavigationView   bottomNavigationView;
    private Toolbar                  toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar        = (Toolbar) findViewById(R.id.toolbarMain);
        toolbar        .setTitle("Buscador de Filmes");
        setSupportActionBar(toolbar);
        binds();
        viewLoading.setVisibility(View.GONE);

    }


    private void binds() {
        MovieListFragment fragment = (MovieListFragment) registerFragment(ControlFrags.LIST);
        replaceFragment(ControlFrags.LIST,false);

        bottomNavigationView        = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        this.viewLoading            = findViewById(R.id.progress_init_app);
        this.viewLoadingText        = findViewById(R.id.rl_progress_main_with_text);
        this.textLoading            = (TextView) viewLoadingText.findViewById(R.id.txt_progress_main_with_text);

        bottomNavigationView        .setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.list_movie) {
                    MovieListFragment fragment = (MovieListFragment) registerFragment(ControlFrags.LIST);
                    replaceFragment(ControlFrags.LIST,false);
                } else if (item.getItemId() == R.id.search_movie) {
                    MovieSearchFragment fragment1 = (MovieSearchFragment) registerFragment(ControlFrags.SEARCH);
                    replaceFragment(ControlFrags.SEARCH,false);
                }else if (item.getItemId() == R.id.home_movie) {
                    Toast.makeText(MainActivity.this,"Em construção",Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });

    }


    @Override
    public void showMovieDetail() {
        MovieDetailFragment fragment = (MovieDetailFragment) registerFragment(ControlFrags.DETAIL);
        replaceFragment(ControlFrags.DETAIL,false);
    }

    @Override
    public void showMovieList() {
        MovieListFragment fragment = (MovieListFragment) registerFragment(ControlFrags.LIST);
        replaceFragment(ControlFrags.LIST,false);
    }

    @Override
    public void showPutMovieList(Bundle args) {

    }

    @Override
    public void showMovieSearch() {
        MovieSearchFragment fragment = (MovieSearchFragment) registerFragment(ControlFrags.SEARCH);
        replaceFragment(ControlFrags.SEARCH,false);
    }

    @Override
    public void showLoading(boolean visible) {
        if (!visible) {
            viewLoadingText.setVisibility(View.GONE);
            viewLoading.setVisibility(View.GONE);
        } else {
            viewLoading.setVisibility(View.VISIBLE);
        }
    }
}
