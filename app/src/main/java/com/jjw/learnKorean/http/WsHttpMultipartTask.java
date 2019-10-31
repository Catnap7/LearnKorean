package com.jjw.learnKorean.http;

import android.content.Context;
import android.os.AsyncTask;

import com.wts.common.Create_ProgressDialog;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class WsHttpMultipartTask extends AsyncTask<ComRequestParam, Void, ComResponse> {

    private static final String TAG = "WsHttpMultipartTask";

    private Context context;
    private WsHttpRequestListener listener;
    protected Create_ProgressDialog pDialog;
    private ComRequestParam reqparam;
    private boolean showDialog = false;
    private boolean tryAgain = false;
    private String serverName = "";

    public WsHttpMultipartTask(Context context) {
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
                pDialog = Create_ProgressDialog.show(context, "", "", true, true,null);
                super.onPreExecute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onPreExecute();
    }

    @Override
    protected ComResponse doInBackground(ComRequestParam... params) {

        this.reqparam = params[0];

        ComResponse response = new ComResponse();

        String charset = "UTF-8";

        JSONArray jsonArray;
        JSONObject json;

        try {
            String requestURL = ""; //DicKey.SCHEME + "://" + this.serverName + "/" + reqparam.getPath();

            MultipartUtility multipart = new MultipartUtility(requestURL, charset);

            List<NameValuePair> paramList = reqparam.getParams();
            Map<String, File> files = reqparam.getFiles();

            for (int i = 0; i < paramList.size(); i++) {
                NameValuePair param = paramList.get(i);
                multipart.addFormField(param.getName(), param.getValue());
            }

            for (String key : files.keySet()) {
                multipart.addFilePart(key, files.get(key));
            }

            List<String> responseList = multipart.finish();

            String msg = responseList.toString();

            jsonArray = new JSONArray(msg);
            json = (JSONObject) jsonArray.get(0);

            int code = json.getInt("code");
            String message = json.getString("msg");

            response.setCode(code);
            response.setMessage(message);
            return response;

        } catch (IOException e) {
            response.setCode(500);
            response.setMessage("FAIL..");
            e.printStackTrace();
            return response;
        } catch (JSONException e) {
            response.setCode(200);
            response.setMessage("HTML..");
            e.printStackTrace();
            return response;
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

    public WsHttpMultipartTask setOnPostListener(WsHttpRequestListener listener) {
        this.listener = listener;
        return this;
    }

    public WsHttpMultipartTask setShowProgress(boolean showDialog) {
        this.showDialog = showDialog;
        return this;
    }

    public WsHttpMultipartTask setLastChance(boolean lastChance) {
        this.tryAgain = lastChance;
        return this;
    }

    public WsHttpMultipartTask setServerName(String serverName) {
        this.serverName = serverName;
        return this;
    }

}
