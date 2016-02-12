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

import com.feedhenry.sdk.FH;
import com.feedhenry.sdk.FHActCallback;
import com.feedhenry.sdk.FHResponse;

import org.feedhenry.welcome.fragments.CloudFragment;
import org.feedhenry.welcome.fragments.DataBrowerFragment;
import org.feedhenry.welcome.fragments.HomeFragment;
import org.feedhenry.welcome.fragments.InitFragment;
import org.feedhenry.welcome.fragments.IntegrationFragment;
import org.feedhenry.welcome.fragments.LocationFragment;
import org.feedhenry.welcome.fragments.NativeAppInfoFragment;
import org.feedhenry.welcome.fragments.PushFragment;
import org.feedhenry.welcome.fragments.StatisticsFragment;

public class MainActivity extends AppCompatActivity {

	private static final String TAG = MainActivity.class.getSimpleName();

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
					case R.id.drawer_cloud:
						fragment = new CloudFragment();
						break;
					case R.id.drawer_push:
						fragment = new PushFragment();
						break;
					case R.id.drawer_location:
						fragment = new LocationFragment();
						break;
					case R.id.drawer_data:
						fragment = new DataBrowerFragment();
						break;
					case R.id.drawer_info:
						fragment = new NativeAppInfoFragment();
						break;
					case R.id.drawer_integration:
						fragment = new IntegrationFragment();
						break;
					case R.id.drawer_statistics:
						fragment = new StatisticsFragment();
						break;
				}

				if (fragment != null) {
					FragmentManager fragmentManager = getSupportFragmentManager();
					fragmentManager.beginTransaction()
							.replace(R.id.content, fragment)
							.commit();

					menuItem.setChecked(true);
				}

				drawerLayout.closeDrawers();
				return true;
			}
		});

	}

	@Override
	protected void onStart() {
		super.onStart();

		getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.content, new InitFragment())
				.commit();

		FH.init(getApplicationContext(), new FHActCallback() {
			@Override
			public void success(FHResponse fhResponse) {
				getSupportFragmentManager()
						.beginTransaction()
						.replace(R.id.content, new HomeFragment())
						.commit();
			}

			@Override
			public void fail(FHResponse fhResponse) {
				Log.d(TAG, "init - fail");
				Log.e(TAG, fhResponse.getErrorMessage(), fhResponse.getError());
				Toast.makeText(getApplicationContext(), R.string.error_initialize_app,
						Toast.LENGTH_SHORT).show();
			}
		});
	}

}
