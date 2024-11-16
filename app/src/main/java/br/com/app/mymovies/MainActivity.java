package br.com.app.mymovies;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.ui.AppBarConfiguration;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import br.com.app.mymovies.fragments.MovieDetailFragment;
import br.com.app.mymovies.fragments.SearchFragment;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_search, R.id.nav_details)
                .setDrawerLayout(drawer)
                .build();

        navigationView.setNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            if(item.getItemId() == R.id.nav_search)
                selectedFragment = new SearchFragment();


            if(item.getItemId() == R.id.nav_details)
                selectedFragment = new MovieDetailFragment();

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment_content_main, selectedFragment)
                        .commit();
            }

            drawer.closeDrawers();
            return true;
        });

        // Carrega o fragment inicial (Search)
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment_content_main, new SearchFragment())
                    .commit();
        }
    }
}
