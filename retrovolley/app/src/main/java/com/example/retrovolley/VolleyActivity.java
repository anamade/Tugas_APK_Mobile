package com.example.retrovolley;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import prasetyodwiki.Request;
import prasetyodwiki.UserAdapter;
import prasetyodwiki.UserResponse;

public class VolleyActivity extends AppCompatActivity implements Volley_Activity {
    private final String TAG = getClass().getSimpleName();
    private ListView lvUserVolley;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        lvUserVolley = findViewById(R.id.lv_user_volley);

        setTitle(getString(R.string.app_name));

        getUserFromAPI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.retrofit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                Intent intent = new Intent(this, AddUserActivity.class);
                intent.putExtra("typeConnection", "volley");
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void actionRefresh(View view) {
        getUserFromAPI();
    }

    public void actionClose(View view) {
        finish();
    }

    private void getUserFromAPI() {
        Gson gson = new Gson();
        String URL = "http://192.168.122.227/volley/User_Registration.php";
        ProgressDialog proDialog = new ProgressDialog(this);
        proDialog.setTitle("volley");
        proDialog.setMessage("Silahkan Tunggu");
        proDialog.show();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null ,
                new Response.Listener<JSONObject>() {
                    class Listener<T> {
                        public T ErrorListener() {
                            return null;
                        }
                    }

                    @Override
                    public void onResponse(JSONObject response) {
                        proDialog.dismiss();
                        UserResponse userResponse = gson.fromJson(response.toString(), UserResponse.class);
                        if (userResponse.getCode() == 200) {
                            UserAdapter adapter = new UserAdapter(getApplicationContext(), userResponse.getUser_list());
                            lvUserVolley.setAdapter(adapter);
                            lvUserVolley.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    Toast.makeText(getApplicationContext(), userResponse.getUser_list().get(i).getUser_fullname(),
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }. new Listener<JSONObject>().ErrorListener()) {
            @Override
                    public void onErrorResponse(VolleyError  error ) {
                        proDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Volley Error : " + error.getMessage(),
                                Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "Error : " + error.getMessage());
                    }
                };

        final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);
                }



}
