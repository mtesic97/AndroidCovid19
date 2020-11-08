package com.example.markotesic;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.markotesic.model.CountryResponse;

import java.util.Date;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<CountryResponse> lista;

    public MyAdapter(List<CountryResponse> lista){
        this.lista=lista;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        CountryResponse current=lista.get(position);
        String date=current.getDate();
        System.out.println(date);
        int index=date.indexOf('T');
        String newdate=date.substring(0,index);
        holder.textView0.setText(newdate+"  ");
        holder.textView1.setText(current.getCountry()+"  ");
        holder.textView2.setText("Confirmed:"+Integer.toString(current.getConfirmed())+"  ");
        holder.textView3.setText("Deadths:"+Integer.toString(current.getDeadths()));
    }

    @Override
    public int getItemCount() {
        return this.lista.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textView0;
        TextView textView1;
        TextView textView2;
        TextView textView3;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView0=itemView.findViewById(R.id.carddate);
            textView1=itemView.findViewById(R.id.cardcountry);
            textView2=itemView.findViewById(R.id.cardcases);
            textView3=itemView.findViewById(R.id.carddeadths);
        }
    }

}
