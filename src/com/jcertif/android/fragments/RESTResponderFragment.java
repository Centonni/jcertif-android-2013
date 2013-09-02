package com.jcertif.android.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.jcertif.android.compound.WaitDialog;
import com.jcertif.android.service.RESTService;

/**
 * 
 * @author Patrick Bashizi
 * 
 */
public abstract class RESTResponderFragment extends SherlockFragment {

	private ResultReceiver mReceiver;
	WaitDialog pd;

	public RESTResponderFragment() {

		mReceiver = new ResultReceiver(new Handler()) {

			@Override
			protected void onReceiveResult(int resultCode, Bundle resultData) {
				if (resultData != null
						&& resultData.containsKey(RESTService.REST_RESULT)) {
					onRESTResult(resultCode, resultData);
				} else {
					onRESTResult(resultCode, null);
				}
			}

		};
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	public ResultReceiver getResultReceiver() {
		return mReceiver;
	}

	// Implementers of this Fragment will handle the result here.
	abstract public void onRESTResult(int code, Bundle result);

	protected void setLoading(boolean state) {
		SherlockFragmentActivity act = getSherlockActivity();
		if (act != null) {
			act.setSupportProgressBarIndeterminateVisibility(state);
		}
	/*	if (pd == null) {
			pd = new WaitDialog(this.getSherlockActivity().getApplicationContext());
			
		}
		if (state == true) {
			pd.show();

		} else {
			pd.dismiss();
		}
	}*/
	}
}
