package com.example.jsondata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.jsondata.Button_Adapter.BannerAdapter;
import com.example.jsondata.Button_Adapter.TopIteamsAdapter;
import com.example.jsondata.Button_Adapter.TopSellingAdapter;
import com.example.jsondata.Model.Banner;
import com.example.jsondata.Model.Categroy;
import com.example.jsondata.Model.Data;
import com.example.jsondata.Model.DtaClass;
import com.example.jsondata.Model.TopItem;
import com.example.jsondata.Model.TopSelling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.TlsVersion;
import okhttp3.internal.Platform;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button BtnBanner,BtnCategory,BtnTopItemsr,BtnTopSells;

    DtaClass dtaClass = new DtaClass();

    private ProgressDialog progressDialog;
    private ArrayList<DtaClass> data;
    private ArrayList<TopItem> topItems;
    private DataAdapter adapter;
    private RecyclerView recyclerview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait ....");
        progressDialog.setCancelable(false);
        progressDialog.show();

        BtnBanner = (Button) findViewById(R.id.BtnBanner);
        BtnCategory = (Button) findViewById(R.id.BtnCategory);
        BtnTopItemsr = (Button) findViewById(R.id.BtnTopItemsr);
        BtnTopSells = (Button) findViewById(R.id.BtnTopSells);

        generateData();
    }


    private void generateData() {

        loadJson();
        recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setHasFixedSize(true);  //Size fixed
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layoutManager);

    }

    private void loadJson() {


        ApiInterface apiInterface = RetrofitClient.getRetrofit().create(ApiInterface.class);

        Call<DtaClass> call = apiInterface.getData();

        call.enqueue(new Callback<DtaClass>() {
            @Override
            public void onResponse(Call<DtaClass> call, Response<DtaClass> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {

                    Log.e("TAG", "==success==>" + response.body());
                    //  ArrayList<Banner> banners =  new ArrayList<>();

                  //  DtaClass dtaClass = new DtaClass();  // Globallu sign.

                    dtaClass = response.body();
                    List<TopItem> topItems =  new ArrayList<>();
                    topItems = dtaClass.getData().getTopItems();

                    //  banners = (ArrayList<Banner>) response.body().getBanner();

                    Log.d("Sunder",  topItems.toString());

                    adapter = new DataAdapter((ArrayList<TopItem>) topItems, MainActivity.this);
                    recyclerview.setAdapter(adapter);
                    adapter.notifyDataSetChanged();


                }
            }

            @Override
            public void onFailure(Call<DtaClass> call, Throwable t) {
                Log.e("TAG", "==failure==>" + t.getMessage());
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "went wrong !", Toast.LENGTH_SHORT).show();
            }
        });

        BtnBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Banner>arrayList = new ArrayList<>();
                arrayList = dtaClass.getData().getBanner();
                BannerAdapter adapter = new BannerAdapter(arrayList,MainActivity.this);
                recyclerview.setAdapter(adapter);

            }
        });
        BtnCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Categroy>arrayList = new ArrayList<>();
                arrayList = dtaClass.getData().getCategroy();
                TopIteamsAdapter adapter = new TopIteamsAdapter((ArrayList<Categroy>) arrayList,MainActivity.this);
                recyclerview.setAdapter(adapter);
            }
        });
        BtnTopSells.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<TopSelling>arrayList = new ArrayList<>();
                arrayList = dtaClass.getData().getTopSelling();
                TopSellingAdapter adapter = new TopSellingAdapter((ArrayList<TopSelling>) arrayList,MainActivity.this);
                recyclerview.setAdapter(adapter);
            }
        });
        BtnTopItemsr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<TopItem>arrayList = new ArrayList<>();
                arrayList = dtaClass.getData().getTopItems();
                DataAdapter adapter = new DataAdapter((ArrayList<TopItem>) arrayList, MainActivity.this);
                recyclerview.setAdapter(adapter);
            }
        });
    }


}