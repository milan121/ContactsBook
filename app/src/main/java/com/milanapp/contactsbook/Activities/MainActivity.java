

package com.milanapp.contactsbook.Activities;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.milanapp.contactsbook.Adapters.UserAdapter;
import com.milanapp.contactsbook.Model.Datum;
import com.milanapp.contactsbook.Model.User;
import com.milanapp.contactsbook.R;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {


    private static final String URL = "https://reqres.in/api/users?page=1&per_page=10";
    private static final String URL2 = "https://reqres.in/api/users?page=2&per_page=10";


    private List<Datum> datumList = new ArrayList<>();


    private UserAdapter userAdapter;
    private RecyclerView rv_user;

    private RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv_user = findViewById(R.id.rv_users);

        rv_user.setHasFixedSize(true);
        userAdapter = new UserAdapter(this, datumList);
        rv_user.setLayoutManager(new LinearLayoutManager(this));
        rv_user.setAdapter(userAdapter);


        jsonRequest1();



    }


    private void jsonRequest1() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.serializeNulls().create();

                User user = gson.fromJson(response, User.class);


                datumList.clear();
                datumList.addAll(user.getData());
                jsonRequest2();
                userAdapter.notifyDataSetChanged();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error to load data", Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);
    }

    private void jsonRequest2() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.serializeNulls().create();

                User user = gson.fromJson(response, User.class);


                datumList.addAll(user.getData());
                userAdapter.notifyDataSetChanged();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error to load data", Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);
    }


}





