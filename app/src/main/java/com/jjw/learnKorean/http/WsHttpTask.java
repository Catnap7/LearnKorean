package com.jjw.learnKorean.http;

import android.content.Context;
import android.os.AsyncTask;

import com.wts.common.Create_ProgressDialog;

public class WsHttpTask extends AsyncTask<ComRequestParam, Void, ComResponse> {

    private Context context;
    private WsHttpRequestListener listener;

    protected Create_ProgressDialog pDialog;
    private ComRequestParam reqparam;
    private boolean showDialog = false;

    private boolean tryAgain = false;

    private String serverName = "";

    public WsHttpTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        if (context != null && showDialog) {
            try {
                if (pDialog != null) {
                    pDialog.dismiss();
                    pDialog = null;
                }
//                pDialog = new Create_ProgressDialog (context);
//                pDialog.show();
                pDialog = Create_ProgressDialog.show(context, "", "", true, true,null);
                super.onPreExecute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onPreExecute();
    }

    @Override
    protected ComResponse doInBackground(ComRequestParam... param) {

        this.reqparam = param[0];
        try {
            do {
                ComResponse response = WsHttpClient.getInstance().request(serverName, this.reqparam);
                String message = response.getMessage();
                return response;
            } while (tryAgain);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(ComResponse result) {
        super.onPostExecute(result);

        if (pDialog != null) {
            try {
                pDialog.dismiss();
                pDialog = null;
            } catch (Exception e) {
            }
        }

        if (result == null || result.getCode() != 200) {
            if (this.listener != null) listener.onFailure(result);
            return;
        }

        if (this.listener != null) listener.onSuccess(result);
    }

    public WsHttpTask setOnPostListener(WsHttpRequestListener listener) {
        this.listener = listener;
        return this;
    }

    public WsHttpTask setShowProgress(boolean showDialog) {
        this.showDialog = showDialog;
        return this;
    }

    public WsHttpTask setLastChance(boolean lastChance) {
        this.tryAgain = lastChance;
        return this;
    }

    public WsHttpTask setServerName(String serverName) {
        this.serverName = serverName;
        return this;
    }
}
