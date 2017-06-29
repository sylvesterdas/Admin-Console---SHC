package com.SacredHeartColaba.admin.helpers;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.SacredHeartColaba.admin.MainActivity;
import com.SacredHeartColaba.admin.R;
import com.SacredHeartColaba.admin.preference.AdminSharedPreference;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * @author SylvesterDas
 * @version 1.0.0
 * @since 11-Jun-17.
 */

public class LoginAsyncTask extends AsyncTask<String, Void, String> {

    private static final String BASE_URL = "http://sacredheartcolaba.com/new_scripts/";
    private static final String URL_ERROR = "Please check URL";
    private static final String SOCKET_ERROR = "Timed Out";
    private static final String IO_ERROR = "IO Exception";
    private static final String TAG = LoginAsyncTask.class.getSimpleName();
    private static final String ENC = "UTF-8";

    private String fileName, username, password;

    private Activity mActivity;
    private ProgressDialog progressDialog;

    public LoginAsyncTask(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(mActivity, mActivity.getString(R.string.app_name), "Verifying...", true, false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }

    @Override
    protected String doInBackground(String... strings) {
        HttpURLConnection connection = null;
        StringBuilder response = new StringBuilder("");
        fileName = strings[0];
        username = strings[1];
        password = strings[2];
        try {
            connection = (HttpURLConnection) new URL(BASE_URL + strings[0]).openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(15000);
            connection.setReadTimeout(15000);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            String urlParameters =
                    URLEncoder.encode("username", ENC) + "=" + URLEncoder.encode(strings[1], ENC) + "&" +
                            URLEncoder.encode("password", ENC) + "=" + URLEncoder.encode(strings[2], ENC);

            // Send post request
            connection.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            int responseCode = connection.getResponseCode();
            Log.i(TAG, "\nSending 'POST' request to URL : " + connection.getURL().toString());
            Log.i(TAG, "Post parameters : " + urlParameters);
            Log.i(TAG, "Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            Log.i(TAG, response.toString());

        } catch (MalformedURLException e) {
            return URL_ERROR;
        } catch (SocketException e) {
            return SOCKET_ERROR;
        } catch (IOException e) {
            return IO_ERROR;
        } finally {
            progressDialog.dismiss();
            if (connection != null) {
                connection.disconnect();
            }
        }
        return response.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s != null && !s.equals("") && !s.equals(URL_ERROR) && !s.equals(SOCKET_ERROR) && !s.equals(IO_ERROR)) {
            if (verified(s)) {
                new AdminSharedPreference(mActivity).login(username, password);
                Intent intent = new Intent(mActivity, MainActivity.class);
                mActivity.startActivity(intent);
                mActivity.finish();
            }
        } else {
            Toast.makeText(mActivity, "Something went wrong. Please try again.", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean verified(String s) {
        try {
            JSONObject jsonObject = new JSONObject(s);
            Toast.makeText(mActivity, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
            return jsonObject.getBoolean("status");
        } catch (JSONException e) {
            Toast.makeText(mActivity, "Something went wrong. Please try again. JSON", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
