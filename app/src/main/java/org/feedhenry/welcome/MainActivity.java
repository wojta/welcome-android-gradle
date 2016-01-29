package org.feedhenry.welcome;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import org.feedhenry.welcome.fragments.HomeFragment;
import org.feedhenry.welcome.fragments.StatisticsFragment;

public class MainActivity extends AppCompatActivity {

	private static final String LOG = MainActivity.class.getSimpleName();

	private DrawerLayout drawerLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// -- Toolbar
		final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		// -- Actionbar
		final ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer);
			actionBar.setDisplayHomeAsUpEnabled(true);
		}

		// -- Drawer & NavigationView
		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
		navigationView.setNavigationItemSelectedListener(new OnNavigationItemSelectedListener() {
			@Override
			public boolean onNavigationItemSelected(MenuItem menuItem) {

				Fragment fragment = null;

				switch (menuItem.getItemId()) {
					case R.id.drawer_home:
						fragment = new HomeFragment();
						break;
					case R.id.drawer_statistics:
						fragment = new StatisticsFragment();
						break;
				}

				if ((fragment != null && (!menuItem.isChecked()))) {
					FragmentManager fragmentManager = getSupportFragmentManager();
					fragmentManager.beginTransaction()
							.replace(R.id.content, fragment)
							.addToBackStack(null).commit();

					menuItem.setChecked(true);
				} else {
					Toast.makeText(getApplicationContext(),
							"This feature is not implemented yet", Toast.LENGTH_SHORT).show();
				}

				drawerLayout.closeDrawers();
				return true;
			}
		});

		getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.content, new HomeFragment())
				.commit();
	}

}
