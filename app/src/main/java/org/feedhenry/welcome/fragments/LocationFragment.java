package org.feedhenry.welcome.fragments;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.feedhenry.sdk.FH;
import com.feedhenry.sdk.FHActCallback;
import com.feedhenry.sdk.FHResponse;
import com.feedhenry.sdk.api.FHCloudRequest;
import com.squareup.picasso.Picasso;

import org.feedhenry.welcome.R;
import org.json.fh.JSONObject;

public class LocationFragment extends android.support.v4.app.Fragment {

	private static final String TAG = LocationFragment.class.getSimpleName();

	private Button requestButton;
	private View toggle;

	private ImageView weatherIcon;
	private TextView weatherDesc;
	private TextView weatherDate;
	private TextView weatherLow;
	private TextView weatherHigh;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
							 @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_location, null);

		toggle = view.findViewById(R.id.weather_toggle);

		weatherIcon = (ImageView) view.findViewById(R.id.weather_icon);
		weatherDesc = (TextView) view.findViewById(R.id.weather_desc);
		weatherDate = (TextView) view.findViewById(R.id.weather_date);
		weatherLow = (TextView) view.findViewById(R.id.weather_low);
		weatherHigh = (TextView) view.findViewById(R.id.weather_high);

		final EditText lat = (EditText) view.findViewById(R.id.latitude);
		final EditText lng = (EditText) view.findViewById(R.id.longitude);

		requestButton = (Button) view.findViewById(R.id.get_weather);
		requestButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				getWeather(lat.getText().toString(), lng.getText().toString());
			}
		});

		return view;
	}

	private void getWeather(String lat, String lng) {

		requestButton.setEnabled(false);
		toggle.setVisibility(View.INVISIBLE);

		FHActCallback callback = new FHActCallback() {
			@Override
			public void success(FHResponse fhResponse) {
				parseWeather(fhResponse.getJson());
				toggle.setVisibility(View.VISIBLE);
				requestButton.setEnabled(true);
			}

			@Override
			public void fail(FHResponse fhResponse) {
				Log.e(TAG, fhResponse.getErrorMessage(), fhResponse.getError());
				Toast.makeText(getContext(), fhResponse.getErrorMessage(),
						Toast.LENGTH_SHORT).show();
				requestButton.setEnabled(true);
			}
		};


		JSONObject params = new JSONObject("{'lat':'" + lat + "', 'lon':'" + lng + "'}");

		try {
			FHCloudRequest request = FH.buildCloudRequest("getWeather", "POST", null, params);
			request.executeAsync(callback);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage(), e);
			Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
		}
	}

	private void parseWeather(JSONObject json) {
		JSONObject obj = json.getJSONArray("data").getJSONObject(0);

		String iconURL = obj.getString("icon");
		String desc = obj.getString("desc");
		String date = obj.getString("date");
		String lowTemperature = getString(R.string.temperature, obj.getString("low"));
		String hihgTemperature = getString(R.string.temperature, obj.getString("high"));

		Picasso.with(getActivity()).load(iconURL).into(weatherIcon);

		weatherDesc.setText(desc);
		weatherDate.setText(date);
		weatherLow.setText(lowTemperature);
		weatherHigh.setText(hihgTemperature);
	}

}
