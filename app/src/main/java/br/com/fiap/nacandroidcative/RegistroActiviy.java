package br.com.fiap.nacandroidcative;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class RegistroActiviy extends AppCompatActivity {

    EditText edtUsuario;
    EditText edtSenha;

    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_activiy);

        edtUsuario = findViewById(R.id.edtUsuario);
        edtSenha = findViewById(R.id.edtSenha);
    }

    public void Registrar(View view) {
        String username = edtUsuario.getText().toString();
        String password = edtSenha.getText().toString();

        if(username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, R.string.campos_preenchidos, Toast.LENGTH_SHORT).show();
            return;
        }

        Usuario usuario = new Usuario(username, password);
        UsersDB usersDB = new UsersDB(this);

        boolean userExist = usersDB.buscarPorUsuario(username);

        if(userExist == true) {
            Toast.makeText(this, "Usuário já existente", Toast.LENGTH_SHORT).show();
            return;
        }

        usersDB.registrar(usuario);
        Toast.makeText(this, "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show();

        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
        finish();
    }

    public void VoltarLogin(View view) {
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
        finish();
    }
}