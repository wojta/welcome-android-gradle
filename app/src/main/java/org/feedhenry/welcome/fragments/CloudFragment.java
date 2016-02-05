package org.feedhenry.welcome.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.feedhenry.sdk.FH;
import com.feedhenry.sdk.FHActCallback;
import com.feedhenry.sdk.FHResponse;
import com.feedhenry.sdk.api.FHCloudRequest;

import org.feedhenry.welcome.R;
import org.json.fh.JSONObject;

public class CloudFragment extends Fragment {

	private static final String LOG = CloudFragment.class.getSimpleName();

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
							 @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_cloud, null);

		final Button requestButton = (Button) view.findViewById(R.id.call_cloud);
		requestButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				callCloud();
			}
		});

		return view;
	}

	private void callCloud() {

		FHActCallback callback = new FHActCallback() {
			@Override
			public void success(FHResponse fhResponse) {
			}

			@Override
			public void fail(FHResponse fhResponse) {
				Log.e(LOG, fhResponse.getErrorMessage(), fhResponse.getError());
			}
		};

		JSONObject params = new JSONObject("{}");

		try {
			FHCloudRequest request = FH.buildCloudRequest("hello", "POST", null, params);
			request.executeAsync(callback);
		} catch (Exception e) {
			Log.e(LOG, e.getMessage(), e);
		}

	}

}
