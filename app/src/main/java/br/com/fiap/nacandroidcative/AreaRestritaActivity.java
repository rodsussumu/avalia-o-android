package br.com.fiap.nacandroidcative;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AreaRestritaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_restrita);
    }

    public void logout(View view) {
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
        finish();
    }
}