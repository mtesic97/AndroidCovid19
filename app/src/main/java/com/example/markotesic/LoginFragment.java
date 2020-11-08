package com.example.markotesic;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginFragment extends Fragment {


    EditText username;
    EditText password;

    Button button;
    TextView toRegistry;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_login, container, false);
        username=v.findViewById(R.id.loginUsername);
        password=v.findViewById(R.id.loginPassword);
        button=v.findViewById(R.id.loginButton);
        toRegistry=v.findViewById(R.id.loginRegistryButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().equalsIgnoreCase(" ")){
                    username.setError("error");
                    return;
                }
                if(password.getText().toString().equalsIgnoreCase(" ")){
                    password.setError("error");
                    return; 
                }
                String usernametext=username.getText().toString();
                String passwordtext=password.getText().toString();
                int br=login(usernametext,passwordtext);
                if(br!=-1) {
                    SharedPreferences sharedPreferences=getActivity().getSharedPreferences("marko",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor= sharedPreferences.edit();
                    editor.putInt("korisnikid",br);
                    editor.apply();
                    Intent intent = new Intent(getActivity(), MapActivity.class);
                    startActivity(intent);
                }

            }
        });
        toRegistry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).changeRegister();
            }
        });
        return v;
    }
    public int  login(String username, String password){
        FeedReederDbHelper feedReederDbHelper=new FeedReederDbHelper(getContext());
        Cursor cc=feedReederDbHelper.getAll();
        System.out.println(cc.getCount());
        Cursor c= feedReederDbHelper.getKorisnik(username,password);
        System.out.println(c.getCount());
        if(c.getCount()==0){
            return -1;
        }
        c.moveToFirst();
        return c.getInt(0);

    }
}