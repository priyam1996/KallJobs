package com.kuncham.kalljobs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.kuncham.kalljobs.Adapters.AlljobsAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

public class AllJobsActivity extends AppCompatActivity {

    ListView mAllListView;
    List<Alljobs> alljobsList;
    AlljobsAdapter mAlljobsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_jobs);

        alljobsList = new Vector<Alljobs>();
        mAllListView = (ListView)findViewById(R.id.list_alljobs);
        mAlljobsAdapter = new AlljobsAdapter(this,alljobsList,R.layout.alljobs_listview);
        mAllListView.setAdapter(mAlljobsAdapter);


        load(null);

    }

    public void load(String s) {

        String url = "http://api.geonames.org/wikipediaSearchJSON?formatted=true&q=%20%22&maxRows=10&username=srik784&style=full";

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        alljobsList.clear();

                        try {
                            JSONArray ja = response.getJSONArray("geonames");
                            for (int i = 0; i < ja.length(); i++) {
                                try {


                                    JSONObject jas = ja.getJSONObject(i);


                                    Alljobs p = new Alljobs();
                                    p.setHeadAlljobs(jas.getString("title"));
                                    p.setContentALljobs(jas.getString("summary"));
                                  //  p.setReadMore(jas.getString("wikipediaUrl"));
                                    p.setImageAlljobs(jas.getString("thumbnailImg"));

                                    alljobsList.add(p);
                                } catch (Exception e) {
                                    e.printStackTrace();

                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        mAlljobsAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub

                        Toast.makeText(AllJobsActivity.this, "Try again later", Toast.LENGTH_SHORT).show();
                    }
                });

        Volley.newRequestQueue(this).add(jsObjRequest);

        mAllListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(AllJobsActivity.this, AllJobsOverview.class);
                it.putExtra("p", (Serializable) mAlljobsAdapter.getItem(i));
                startActivity(it);
            }
        });
    }
}
