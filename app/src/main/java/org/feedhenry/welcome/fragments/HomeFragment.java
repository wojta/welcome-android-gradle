package org.feedhenry.welcome.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.feedhenry.welcome.MainActivity;
import org.feedhenry.welcome.R;

public class HomeFragment extends Fragment {

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
							 @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_home, null);

		final MainActivity activity = (MainActivity) getActivity();

		View callCloud = view.findViewById(R.id.call_cloud);
		callCloud.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				activity.navigateToCallCloud();
			}
		});

		View pushNotification = view.findViewById(R.id.push_notification);
		pushNotification.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				activity.navigateToPushNotification();
			}
		});

		View location = view.findViewById(R.id.location);
		location.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				activity.navigateToLocation();
			}
		});

		View dataBrowser = view.findViewById(R.id.data_browser);
		dataBrowser.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				activity.navigateToDataBrowser();
			}
		});

		return view;
	}
}
