package com.kuncham.kalljobs;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

public class HomePage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ListView lv;
    List<Jobs> jobsList;
    MyAdapter ma;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        jobsList=new Vector<Jobs>();
        lv=(ListView)findViewById(R.id.govt_lv);
        ma = new MyAdapter(this,jobsList,R.layout.list);
        lv.setAdapter(ma);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        load(null);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_alljobs) {

            Intent alljobsIntent = new Intent(this,AllJobsActivity.class);
            startActivity(alljobsIntent);
            // Handle the camera action
        } else if (id == R.id.nav_govt) {

        } else if (id == R.id.nav_central) {

        } else if (id == R.id.nav_bank) {

        } else if (id == R.id.nav_railway) {

        } else if (id == R.id.nav_police) {

        } else if (id == R.id.nav_freshers){

        } else if (id == R.id.nav_it){

        } else if (id == R.id.nav_nonit){

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void load(String s) {

        String url = "http://api.geonames.org/wikipediaSearchJSON?formatted=true&q=%20%22&maxRows=10&username=srik784&style=full";

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        jobsList.clear();

                        try {
                            JSONArray ja = response.getJSONArray("geonames");
                            for (int i = 0; i < ja.length(); i++) {
                                try {


                                    JSONObject jas = ja.getJSONObject(i);


                                    Jobs p = new Jobs();
                                    p.setJobName(jas.getString("title"));
                                    jobsList.add(p);
                                } catch (Exception e) {
                                    e.printStackTrace();

                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                      ma.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub

                        Toast.makeText(HomePage.this, "Try again later", Toast.LENGTH_SHORT).show();
                    }
                });

        Volley.newRequestQueue(this).add(jsObjRequest);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(HomePage.this, AllJobsOverview.class);
                it.putExtra("p", (Serializable) ma.getItem(i));
                startActivity(it);
            }
        });
    }
}
