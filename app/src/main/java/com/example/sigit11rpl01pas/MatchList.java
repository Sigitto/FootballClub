package com.example.sigit11rpl01pas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MatchList extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AdapterMatch adapter;
    private ArrayList<ModelMatch> DataArrayList; //kit add kan ke main_menu
    private ImageView tambah_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_teams);
        recyclerView = (RecyclerView)findViewById(R.id.rvdata);

        addDataOnline();

    }

    void addDataOnline() {

        //data online
        AndroidNetworking.get("https://www.thesportsdb.com/api/v1/json/1/eventslast.php?id=133613")
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {

                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        Log.d("hasiljson", "onResponse: " + response.toString());
                        //jika sudah berhasil debugm lanjutkan code dibawah ini
                        DataArrayList = new ArrayList<>();
                        ModelMatch modelku;

                        try {
                            Log.d("hasiljson", "onResponse: " + response.toString());
                            JSONArray jsonArray = response.getJSONArray("results");
                            Log.d("hasiljson2", "onResponse: " + jsonArray.toString());
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                modelku = new ModelMatch();
                                modelku.setIdEvent(jsonObject.getInt("idEvent"));
                                modelku.setStrAwayTeam(jsonObject.getString("strAwayTeam"));
                                modelku.setStrHomeTeam(jsonObject.getString("strHomeTeam"));
                                modelku.setStrSeason(jsonObject.getString("strSeason"));
                                modelku.setStrVenue(jsonObject.getString("strVenue"));
                                modelku.setStrLeague(jsonObject.getString("strLeague"));
                                modelku.setStrEvent(jsonObject.getString("strEvent"));
                                DataArrayList.add(modelku);


                            }

                            //untuk handle click
                            adapter = new AdapterMatch(DataArrayList, new AdapterMatch.Callback() {
                                @Override
                                public void onClick(int position) {

                                }

                                @Override
                                public void test() {

                                }
                            });
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MatchList.this);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Log.d("errorku", "onError errorCode : " + error.getErrorCode());
                        Log.d("errorku", "onError errorBody : " + error.getErrorBody());
                        Log.d("errorku", "onError errorDetail : " + error.getErrorDetail());
                    }
                });

    }
}