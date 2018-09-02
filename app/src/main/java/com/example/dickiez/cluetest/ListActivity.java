package com.example.dickiez.cluetest;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.dickiez.cluetest.Model.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    CustomAdapter adapter;
    List<Data> dataList;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = (RecyclerView)findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(ListActivity.this));

        getData();
    }

    private void getData() {
        ApiInterface ai = ApiClient.getRetrofit().create(ApiInterface.class);
        Call<List<Data>> call = ai.getData(getHeader());
        call.enqueue(new Callback<List<Data>>() {
            @Override
            public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
                dataList = response.body();
                adapter = new CustomAdapter(dataList, ListActivity.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Data>> call, Throwable t) {

            }
        });
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public Map<String, String> getHeader(){
        Map<String, String> mHeader = new HashMap<String, String>();
        mHeader.put("token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoicWx1ZWluIiwiaWF0IjoxNDk0Mzk5Njg1fQ.mG5wmoCwmchufTPloxI7AjZaeM_bwcpCEJpyUnCDrmk");
        return mHeader;
    }
}
