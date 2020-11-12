package com.mhmdalhafizh.aplikasipenjualanmakanan.pembeli;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mhmdalhafizh.aplikasipenjualanmakanan.R;
import com.mhmdalhafizh.aplikasipenjualanmakanan.adapter.AdapterMakanan;
import com.mhmdalhafizh.aplikasipenjualanmakanan.admin.HomeAdminActivity;
import com.mhmdalhafizh.aplikasipenjualanmakanan.model.ModelMakanan;
import com.mhmdalhafizh.aplikasipenjualanmakanan.server.BaseURL;
import com.mhmdalhafizh.aplikasipenjualanmakanan.session.PrefSetting;
import com.mhmdalhafizh.aplikasipenjualanmakanan.session.SessionManager;
import com.mhmdalhafizh.aplikasipenjualanmakanan.users.LoginActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomePembeli extends AppCompatActivity {

    ProgressDialog pDialog;

    AdapterMakanan adapter;
    ListView list;

    ArrayList<ModelMakanan> newsList = new ArrayList<ModelMakanan>();
    private RequestQueue mRequestQueue;

    FloatingActionButton floatingExit;

    SessionManager session;
    SharedPreferences prefs;
    PrefSetting prefSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_pembeli);

        prefSetting = new PrefSetting(this);
        prefs = prefSetting.getSharedPreferences();

        session = new SessionManager(HomePembeli.this);

        prefSetting.isLogin(session, prefs);

        getSupportActionBar().setTitle("Data Makanan");
        mRequestQueue = Volley.newRequestQueue(this);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        list = (ListView) findViewById(R.id.array_list);

        floatingExit = (FloatingActionButton) findViewById(R.id.exit);

        floatingExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.setLogin(false);
                session.setSessid(0);
                Intent i = new Intent(HomePembeli.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        newsList.clear();
        adapter = new AdapterMakanan(HomePembeli.this, newsList);
        list.setAdapter(adapter);
        getAllMakanan();

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(HomePembeli.this, HomeAdminActivity.class);
        startActivity(i);
        finish();
    }

    private void getAllMakanan() {
        // Pass second argument as "null" for GET requests
        pDialog.setMessage("Loading");
        showDialog();
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, BaseURL.dataMakanan, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            boolean status = response.getBoolean("error");
                            if (status == false) {
                                Log.d("data makanan = ", response.toString());
                                String data = response.getString("data");
                                JSONArray jsonArray = new JSONArray(data);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    final ModelMakanan makanan = new ModelMakanan();
                                    final String _id = jsonObject.getString("_id");
                                    final String kodemakanan = jsonObject.getString("kodemakanan");
                                    final String namamakanan = jsonObject.getString("namamakanan");
                                    final String jumlah = jsonObject.getString("jumlah");
                                    final String hargamakanan = jsonObject.getString("hargamakanan");
                                    final String gambar = jsonObject.getString("gambar");
                                    makanan.setKodemakanan(kodemakanan);
                                    makanan.setNamamakanan(namamakanan);
                                    makanan.setJumlahmakanan(jumlah);
                                    makanan.setHargamakanan(hargamakanan);
                                    makanan.setGambar(gambar);

                                    makanan.set_id(_id);

                                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            // TODO Auto-generated method stub
                                            Intent a = new Intent(HomePembeli.this, DetailMakanan.class);
                                            a.putExtra("kodebarang", newsList.get(position).getKodemakanan());
                                            a.putExtra("_id", newsList.get(position).get_id());
                                            a.putExtra("namabarang", newsList.get(position).getNamamakanan());
                                            a.putExtra("jumlah", newsList.get(position).getJumlahmakanan());
                                            a.putExtra("hargabarang", newsList.get(position).getHargamakanan());
                                            a.putExtra("gambar", newsList.get(position).getGambar());
                                            startActivity(a);
                                        }
                                    });
                                    newsList.add(makanan);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                hideDialog();
            }
        });

        /* Add your Requests to the RequestQueue to execute */
        mRequestQueue.add(req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
