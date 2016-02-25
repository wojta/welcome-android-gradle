package org.feedhenry.welcome.fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.feedhenry.sdk.FH;
import com.feedhenry.sdk.FHActCallback;
import com.feedhenry.sdk.FHResponse;
import com.feedhenry.sdk.api.FHCloudRequest;

import org.feedhenry.welcome.R;
import org.json.fh.JSONObject;

public class NativeAppInfoFragment extends android.support.v4.app.Fragment {

	private static final String TAG = NativeAppInfoFragment.class.getSimpleName();

	private TextView manufactorurer, model, product, serial, cpu, host;
	private TextView appName, appDomain, appEnv, appPort;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
							 @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_native_app_info, null);

		manufactorurer = (TextView) view.findViewById(R.id.device_manufacturer);
		model = (TextView) view.findViewById(R.id.device_model);
		product = (TextView) view.findViewById(R.id.device_product);
		serial = (TextView) view.findViewById(R.id.device_serial);
		cpu = (TextView) view.findViewById(R.id.device_cpu);
		host = (TextView) view.findViewById(R.id.device_host);

		appName = (TextView) view.findViewById(R.id.app_name);
		appDomain = (TextView) view.findViewById(R.id.app_domain);
		appEnv = (TextView) view.findViewById(R.id.app_env);
		appPort = (TextView) view.findViewById(R.id.app_port);

		getDeviceInformation();
		getCouldInformation();

		return view;
	}

	private void getDeviceInformation() {

		manufactorurer.setText(Build.MANUFACTURER);
		model.setText(Build.MODEL);
		product.setText(Build.PRODUCT);
		serial.setText(Build.SERIAL);
		cpu.setText(Build.CPU_ABI);
		host.setText(Build.HOST);
	}

	private void getCouldInformation() {

		FHActCallback callback = new FHActCallback() {
			@Override
			public void success(FHResponse fhResponse) {
				JSONObject json = fhResponse.getJson();

				appName.setText(json.getString("appName"));
				appDomain.setText(json.getString("domain"));
				appEnv.setText(json.getString("env"));
				appPort.setText(json.getString("port"));
			}

			@Override
			public void fail(FHResponse fhResponse) {
				Log.e(TAG, fhResponse.getErrorMessage(), fhResponse.getError());
				Toast.makeText(getActivity(), fhResponse.getErrorMessage(),
						Toast.LENGTH_SHORT).show();
			}
		};

		JSONObject params = new JSONObject("{}");

		try {
			FHCloudRequest request = FH.buildCloudRequest("getFhVars", "POST", null, params);
			request.executeAsync(callback);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage(), e);
			Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
		}

	}

}
