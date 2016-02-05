package org.feedhenry.welcome.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.feedhenry.welcome.R;

public class LocationFragment extends android.support.v4.app.Fragment {

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
							 @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_location, null);

		final Button requestButton = (Button) view.findViewById(R.id.get_location);
		requestButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				getLocation();
			}
		});

		return view;
	}

	private void getLocation() {

	}

}
