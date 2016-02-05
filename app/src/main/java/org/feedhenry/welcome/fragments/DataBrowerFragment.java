package org.feedhenry.welcome.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.feedhenry.sdk.FH;
import com.feedhenry.sdk.FHActCallback;
import com.feedhenry.sdk.FHResponse;
import com.feedhenry.sdk.api.FHCloudRequest;

import org.feedhenry.welcome.R;
import org.json.fh.JSONObject;

public class DataBrowerFragment extends android.support.v4.app.Fragment {

	private static final String LOG = DataBrowerFragment.class.getSimpleName();

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
							 @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_data_browser, null);

		final EditText data = (EditText) view.findViewById(R.id.data);
		final Button save = (Button) view.findViewById(R.id.save);

		save.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				saveData(data.getText().toString());
			}
		});

		return view;
	}

	private void saveData(String data) {

		FHActCallback callback = new FHActCallback() {
			@Override
			public void success(FHResponse fhResponse) {
			}

			@Override
			public void fail(FHResponse fhResponse) {
				Log.e(LOG, fhResponse.getErrorMessage(), fhResponse.getError());
			}
		};

		JSONObject params = new JSONObject("{'collection':'Users', 'document': {data: " + data + "}}");

		try {
			FHCloudRequest request = FH.buildCloudRequest("saveData", "POST", null, params);
			request.executeAsync(callback);
		} catch (Exception e) {
			Log.e(LOG, e.getMessage(), e);
		}

	}

}
