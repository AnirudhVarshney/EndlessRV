package com.example.abhinav.endlessrv;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ABHINAV on 27-07-2016.
 */

public class parser {
    private static final String TAG_USERID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_DESC = "description";
    private static final String TAG_ERROR = "error";
    private static final String TAG_RESULTS = "result";
    String errorDescription="";
    String LOG_TAG="ani";
    private ArrayList<user> listSuperHeroes;
    Context con;
    Adapter mAdapter;
    public boolean isValidData(String jsonString) {

        try {
            JSONObject jsonObj = new JSONObject(jsonString);

            //check for successful reply which contains result
            String jsonArrayTest = jsonObj.optString(TAG_RESULTS);

            //JSON dose'nt have data with result
            if (jsonArrayTest.length() == 0) {
                Log.d(LOG_TAG, "no data with result - JSONParser");
                errorDescription = "Empty JSON : No data with Result";

                //check for successful reply which contains error
                jsonArrayTest = jsonObj.optString(TAG_ERROR);

                //JSON dose'nt have data with error
                if (jsonArrayTest.length() == 0) {
                    Log.d(LOG_TAG, "no data with error - JSONParser");
                    errorDescription = "Empty JSON : No data with error";
                }
                //when error is not empty
                else {
                    JSONArray jsonarray = jsonObj.getJSONArray(TAG_RESULTS);
                    JSONObject c = jsonarray.getJSONObject(0);
                    errorDescription = c.getString(TAG_DESC);
                    Log.d(LOG_TAG, "errorDescription = " + errorDescription + " - JSONParser");
                }
                return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public String getErrorDescription() {
        return errorDescription;
    }
    public ArrayList<user> getListSuperHeroes() {
        return listSuperHeroes;
    }
}
