package com.example.markotesic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.markotesic.model.CountryResponse;
import com.example.markotesic.model.CovidCountryResponse;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListActivity extends AppCompatActivity {
    Retrofit retrofit;

    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    EditText textViewCountry;
    Button button;

    Button buttontomap;
    Button buttontosymptoms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recyclerView=findViewById(R.id.listrecyclerview);
        textViewCountry=findViewById(R.id.listedittext);
        button=findViewById(R.id.listbutton);

        buttontomap=findViewById(R.id.listbuttontomap);
        buttontomap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ListActivity.this,MapActivity.class);
                startActivity(intent);
            }
        });
        buttontosymptoms=findViewById(R.id.listbuttontosymptoms);
        buttontosymptoms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ListActivity.this,MapActivity.class);
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!textViewCountry.getText().toString().equalsIgnoreCase(" ")){

                    retrofit=new Retrofit.Builder()
                            .baseUrl("https://api.covid19api.com")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    Covid19API covid19API=retrofit.create(Covid19API.class);
                    String tekst=textViewCountry.getText().toString();

                    Calendar cal=Calendar.getInstance();
                    Date date=new Date();
                    cal.setTime(date);
                    int day=cal.get(Calendar.DAY_OF_MONTH);
                    cal.add(Calendar.DATE,-15);
                    Date newdate=cal.getTime();
                    //Api trazi taj format
                    final String format="yyyy-MM-dd";
                    SimpleDateFormat sfg=new SimpleDateFormat(format);
                    sfg.applyPattern(format);
                    String datumPre=sfg.format(newdate);
                    String datumSad=sfg.format(new Date());


                    Call<List<CountryResponse>> call=covid19API.getByCountry(tekst,datumPre,datumSad);

                    call.enqueue(new Callback<List<CountryResponse>>() {
                        @Override
                        public void onResponse(Call<List<CountryResponse>> call, Response<List<CountryResponse>> response) {
                            System.out.println(response.code());
                            if(response.isSuccessful()){
                                MyAdapter myAdapter=new MyAdapter(response.body());
                                recyclerView.setHasFixedSize(true);
                                layoutManager=new LinearLayoutManager(getApplicationContext());
                                recyclerView.setAdapter(myAdapter);
                                recyclerView.setLayoutManager(layoutManager);
                            }else{
                                Toast.makeText(getApplicationContext(),"Neuspesan zahtev"+Integer.toString(response.code()),Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<List<CountryResponse>> call, Throwable t) {
                            Toast.makeText(getApplicationContext(),"Neuspesan zahtev",Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });

    }
}