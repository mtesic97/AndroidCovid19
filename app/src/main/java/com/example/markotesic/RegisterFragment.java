package com.example.markotesic;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class RegisterFragment extends Fragment {

    EditText username;
    EditText password;
    EditText firstname;
    EditText lastname;
    Button registry;

    TextView toLogin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_register, container, false);

        username=v.findViewById(R.id.registerUsername);
        password=v.findViewById(R.id.registerPassword);
        firstname=v.findViewById(R.id.registerFirstname);
        lastname=v.findViewById(R.id.registerLastname);
        registry=v.findViewById(R.id.registerButton);
        toLogin=v.findViewById(R.id.registryloginButton);

        toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).changeLogin();
            }
        });

        registry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username.setError(null);
                password.setError(null);
                firstname.setError(null);
                lastname.setError(null);
                if(username.getText().toString().equalsIgnoreCase(" ")){
                    username.setError("error");
                    return;
                }
                if(password.getText().toString().equalsIgnoreCase(" ")){
                    password.setError("error");
                    return;
                }
                if (firstname.getText().toString().equalsIgnoreCase(" ")){
                   firstname.setError("error");
                   return;
                }
                if(lastname.getText().toString().equalsIgnoreCase(" ")){
                    lastname.setError("error");
                    return;
                }
                register();
                ((MainActivity)getActivity()).changeLogin();

            }
        });

        return v;
    }
    public boolean register(){
        FeedReederDbHelper feedReederDbHelper=new FeedReederDbHelper(getContext());
        String firstnametext=firstname.getText().toString();
        String lastnametext=lastname.getText().toString();
        String usernametext=username.getText().toString();
        String passwordtext=password.getText().toString();
        feedReederDbHelper.saveKorisnik(usernametext,passwordtext,firstnametext,lastnametext);

        return true;
    }
}