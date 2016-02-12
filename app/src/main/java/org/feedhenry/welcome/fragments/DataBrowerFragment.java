package org.feedhenry.welcome.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.feedhenry.sdk.FH;
import com.feedhenry.sdk.FHActCallback;
import com.feedhenry.sdk.FHResponse;
import com.feedhenry.sdk.api.FHCloudRequest;

import org.feedhenry.welcome.R;
import org.json.fh.JSONObject;

public class DataBrowerFragment extends android.support.v4.app.Fragment {

	private static final String TAG = DataBrowerFragment.class.getSimpleName();

	private Button requestButton;
	private EditText dataEditText;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
							 @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_data_browser, null);

		dataEditText = (EditText) view.findViewById(R.id.data);
		requestButton = (Button) view.findViewById(R.id.save);
		requestButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				saveData(dataEditText.getText().toString());
			}
		});

		return view;
	}

	private void saveData(String data) {

		requestButton.setEnabled(false);

		FHActCallback callback = new FHActCallback() {
			@Override
			public void success(FHResponse fhResponse) {
				requestButton.setEnabled(true);
				Toast.makeText(getContext(), R.string.data_saved, Toast.LENGTH_LONG).show();
				dataEditText.setText("");
				dataEditText.setFocusable(true);
			}

			@Override
			public void fail(FHResponse fhResponse) {
				Log.e(TAG, fhResponse.getErrorMessage(), fhResponse.getError());
				Toast.makeText(getContext(), fhResponse.getErrorMessage(),
						Toast.LENGTH_SHORT).show();
				requestButton.setEnabled(true);
			}
		};

		JSONObject params = new JSONObject("{'collection':'Users', 'document': {data: " + data + "}}");

		try {
			FHCloudRequest request = FH.buildCloudRequest("saveData", "POST", null, params);
			request.executeAsync(callback);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage(), e);
		}

	}

}
