package com.example.markotesic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    LoginFragment loginFragment;
    RegisterFragment registerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginFragment=new LoginFragment();
        registerFragment=new RegisterFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.framlayout,registerFragment).commit();
    }
    public void changeLogin(){
        getSupportFragmentManager().beginTransaction().replace(R.id.framlayout,loginFragment).commit();
    }
    public void changeRegister(){
        getSupportFragmentManager().beginTransaction().replace(R.id.framlayout,registerFragment).commit();
    }

}