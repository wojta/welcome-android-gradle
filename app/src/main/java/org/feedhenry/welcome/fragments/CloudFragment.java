package org.feedhenry.welcome.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.feedhenry.sdk.FH;
import com.feedhenry.sdk.FHActCallback;
import com.feedhenry.sdk.FHResponse;
import com.feedhenry.sdk.api.FHCloudRequest;

import org.feedhenry.welcome.R;
import org.json.fh.JSONObject;

public class CloudFragment extends Fragment {

	private static final String TAG = CloudFragment.class.getSimpleName();

	private Button requestButton;
	private View toggle;
	private TextView responseTextview;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
							 @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_cloud, null);

		requestButton = (Button) view.findViewById(R.id.call_cloud);
		requestButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				callCloud();
			}
		});

		toggle = view.findViewById(R.id.cloud_toggle);

		responseTextview = (TextView) view.findViewById(R.id.response);

		return view;
	}

	private void callCloud() {

		requestButton.setEnabled(false);
		toggle.setVisibility(View.INVISIBLE);

		FHActCallback callback = new FHActCallback() {
			@Override
			public void success(FHResponse fhResponse) {
				String response = getString(R.string.response,
						fhResponse.getJson().getString("text"));
				responseTextview.setText(response);
				toggle.setVisibility(View.VISIBLE);
				requestButton.setEnabled(true);
			}

			@Override
			public void fail(FHResponse fhResponse) {
				Log.e(TAG, fhResponse.getErrorMessage(), fhResponse.getError());
				Toast.makeText(getActivity(), fhResponse.getErrorMessage(),
						Toast.LENGTH_SHORT).show();
				requestButton.setEnabled(true);
			}
		};

		JSONObject params = new JSONObject("{}");

		try {
			FHCloudRequest request = FH.buildCloudRequest("hello", "POST", null, params);
			request.executeAsync(callback);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage(), e);
			Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
		}

	}

}
