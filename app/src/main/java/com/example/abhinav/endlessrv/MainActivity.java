package com.example.abhinav.endlessrv;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.pnikosis.materialishprogress.ProgressWheel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import lumenghz.com.pullrefresh.PullToRefreshView;

@TargetApi(Build.VERSION_CODES.M)
public class MainActivity extends AppCompatActivity implements RecyclerView.OnScrollChangeListener{
    RecyclerView mRecycler;
    private static final int ITEMS_ON_PAGE = 8;
    private  int TOTAL_PAGES;
    private static final long DELAY = 1000L;
    private RecyclerView list;
    Adapter mAdapter;
    SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView recyclerView;
    ServerHelper serverHelper;
    ArrayList<user> userprofile;
    private static final String TAG_DESC = "description";
    private int requestCount = 1;
    private RequestQueue requestQueue;
    String error;
    private final Handler handler = new Handler();
    private boolean loading = false;
    PullToRefreshView mPullToRefresh;
    private static final String TAG_USERID = "id";
    private static final String TAG_LARGEURL = "largeimage";
    private static final String TAG_SMALLURLLEFT = "smallimageright";
    private static final String TAG_SMALLURLRIGHT = "smallimageright";
    private static final String TAG_LARGETITLE = "largetitle";
    private static final String TAG_SMALLTITLELEFT = "smalltitleleft";
    private static final String TAG_SMALLTITLERIGHT = "smalltitleright";
    private static final String TAG_LARGECATEGORY = "largecategory";
    private static final String TAG_SMALLCATEGORYLEFT = "smallcategoryleft";
    private static final String TAG_SMALLCATEGORYRIGHT = "smallcategoryright";
    private static final String TAG_LARGEDATE = "largedate";
    private static final String TAG_SMALLDATELEFT = "smalldateleft";
    private static final String TAG_SMALLDATERIGHT = "smalldateright";
    private static final String TAG_RESULTS = "result";
    String errorDescription="";
     ProgressWheel progressBar;
    String LOG_TAG="ani";

    String url = "http://192.168.1.14/Login/endless.php?page=";
    private ArrayList<user> listSuperHeroes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (RecyclerView) findViewById(R.id.list);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        list.setLayoutManager(manager);
        list.setHasFixedSize(true);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshContent();
            }
        });
                listSuperHeroes=new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);
         progressBar = (ProgressWheel) findViewById(R.id.progress_wheel);
        getData();
        list.setOnScrollChangeListener(this);
        mAdapter = new Adapter(MainActivity.this,listSuperHeroes);
        list.setAdapter(mAdapter);



    }
    private void refreshContent() {
        {
            mAdapter = new Adapter(MainActivity.this, listSuperHeroes);
            list.setAdapter(mAdapter);

            onItemsLoadComplete();        }
    }
    void onItemsLoadComplete() {
        // Update the adapter and notify data set changed
        // ...
        mAdapter.notifyDataSetChanged();
        // Stop refresh animation
        mSwipeRefreshLayout.setRefreshing(false);
    }

    private void initRefreshView() {
        mPullToRefresh.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullToRefresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullToRefresh.setRefreshing(false);
                    }
                }, 2000);
            }
        });
    }

    private StringRequest getDataFromServer(int requestCount) { // we can also do by server heler class here using library code
        //  is reduced also we we dont add parser class in the mainactivity list is coming null and we add adater in resonse then ek ke upar ek aata h not neche
        //Initializing ProgressBar


        //Displaying Progressbar
        progressBar.setVisibility(View.VISIBLE);

        //Displaying Progressbar
        // progressBar.setVisibility(View.VISIBLE);
        //  setProgressBarIndeterminateVisibility(true);

        //JsonArrayRequest of volley
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url+String.valueOf(requestCount),
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        Log.d("ani",response+"in on resonse");
//                        //Calling method parseData to parse the json response
//                        parseData(response);
//                        progressBar.setVisibility(View.GONE);
//
//                        //Hiding the progressbar
//                        // //   progressBar.setVisibility(View.GONE);
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        //  progressBar.setVisibility(View.GONE);
//                        //If an error occurs that means end of the list has reached
//                        Toast.makeText(MainActivity.this, "No More Items Available", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//        //Returning the request
//        return jsonArrayRequest;
//    }
        StringRequest strReq = new StringRequest(Request.Method.GET,
                url + String.valueOf(requestCount), new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("ani", response.toString());
                progressBar.setVisibility(View.VISIBLE);
                try {
                    parseData(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("ani", "Error: " + error.getMessage());

            }
        });
        return strReq;
    }
    private void getData() {
        //Adding the method to the queue by calling the method getDataFromServer
        requestQueue.add(getDataFromServer(requestCount));
        //Incrementing the request counter
        requestCount++;
    }

    private void parseData(String jsonString) throws JSONException {
        parser parser=new parser();
             JSONObject jsonObj = new JSONObject(jsonString);
             JSONArray jsonarray = jsonObj.getJSONArray(TAG_RESULTS);
             if(jsonarray.length()!=0) {
                 Log.d("@@", jsonarray.length() + jsonarray.get(0).toString());
                 for (int i = 0; i < jsonarray.length(); i++) {
                     //Creating the superhero object
                     user superHero = new user();
                     JSONObject json = null;
                     try {
                         //Getting json
                         json = jsonarray.getJSONObject(i);

                         //Adding data to the superhero object
                         superHero.setId(json.getString(TAG_USERID));
                         superHero.setLargeimage(json.getString(TAG_LARGEURL));
                         superHero.setSmallimageleft(json.getString(TAG_SMALLURLLEFT));
                         superHero.setSmallimageright(json.getString(TAG_SMALLURLRIGHT));
                         superHero.setLargetitle(json.getString(TAG_LARGETITLE));
                         superHero.setSmalltitleleft(json.getString(TAG_SMALLTITLELEFT));
                         superHero.setSmalltitleright(json.getString(TAG_SMALLTITLERIGHT));
                         superHero.setLargedate(json.getString(TAG_LARGEDATE));
                         superHero.setSmalldateleft(json.getString(TAG_SMALLDATELEFT));
                         superHero.setSmalldateright(json.getString(TAG_SMALLDATERIGHT));
                         superHero.setLargecategory(json.getString(TAG_LARGECATEGORY));
                         superHero.setSmallcategoryleft(json.getString(TAG_SMALLCATEGORYLEFT));
                         superHero.setSmallcategoryright(json.getString(TAG_SMALLCATEGORYRIGHT));
                     } catch (JSONException e) {
                         e.printStackTrace();
                     }
                     //Adding the superhero object to the list
                     listSuperHeroes.add(superHero);
                 }
             }

        else Toast.makeText(MainActivity.this,"Reached end of the list", Toast.LENGTH_SHORT).show();

        //Notifying the adapter that data has been added or changed
        mAdapter.notifyDataSetChanged();
    }

    //This method would check that the recyclerview scroll has reached the bottom or not
    private boolean isLastItemDisplaying(RecyclerView recyclerView) {
        if (recyclerView.getAdapter().getItemCount() != 0) {
            int lastVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
            if (lastVisibleItemPosition != RecyclerView.NO_POSITION && lastVisibleItemPosition == recyclerView.getAdapter().getItemCount() - 1)
                return true;
        }
        return false;
    }

    @Override
    public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//Ifscrolled at last then
        if (isLastItemDisplaying(list)) {
            //Calling the method getdata again
            //addItems();
            getData();
        }
    }
}
