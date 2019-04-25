package com.ngopidevteam.pranadana.metime.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.ngopidevteam.pranadana.metime.Adapter.AdapterData;
import com.ngopidevteam.pranadana.metime.Model.ModelData;
import com.ngopidevteam.pranadana.metime.R;
import com.ngopidevteam.pranadana.metime.Util.AppController;
import com.ngopidevteam.pranadana.metime.Util.ServerAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HistoryWH extends Fragment {
    RecyclerView mRecycleView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mManager;
    List<ModelData> mItems;
    ProgressDialog pd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_history_wh, container, false);

        mRecycleView = v.findViewById(R.id.recViewWH);
        mItems = new ArrayList<>();
        pd = new ProgressDialog(getContext());

        loadJson();

        mManager = new LinearLayoutManager (getContext(), LinearLayoutManager.VERTICAL, false);
        mRecycleView.setLayoutManager(mManager);
        mAdapter = new AdapterData(HistoryWH.this, mItems);
        mRecycleView.setAdapter(mAdapter);

        return v;
    }

    private void loadJson(){
        pd.setMessage("Sedang Mengambil Data");
        pd.setCancelable(false);
        pd.show();

        JsonArrayRequest reqData = new JsonArrayRequest(Request.Method.POST, ServerAPI.URL_DATA, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        pd.cancel();
                        Log.d("volley", "response : " + response.toString());
                        for (int i = 0; i < response.length(); i++){
                            try {
                                JSONObject data = response.getJSONObject(i);
                                ModelData md = new ModelData();
                                md.setJamMulai(data.getString("jam_mulai"));
                                md.setJamSelesai(data.getString("jam_selesai"));
                                mItems.add(md);
                            }catch (JSONException e){
                                e.printStackTrace();
                            }
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Log.d("volley", "error : " + error.getMessage());
                    }
                });

        AppController.getInstance().addToRequestQueue(reqData);
    }
}
