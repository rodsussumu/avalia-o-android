package br.com.fiap.nacandroidcative;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtUsuario;
    EditText edtSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUsuario = findViewById(R.id.edtUsuario);
        edtSenha = findViewById(R.id.edtSenha);
    }

    public void Login(View view) {
        String username = edtUsuario.getText().toString();
        String password = edtSenha.getText().toString();
        if(username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, R.string.campos_preenchidos, Toast.LENGTH_SHORT).show();
            return;
        }

        UsersDB usersDB = new UsersDB(this);

        Usuario usuario = usersDB.Logar(username);

        if(usuario.getUsername() == null) {
            System.out.println(usuario.getUsername());
            Toast.makeText(this, "Usuário não existe", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!usuario.getPassword().equals(password)) {
            Toast.makeText(this, "Senha incorreta", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent it = new Intent(this, AreaRestritaActivity.class);
        startActivity(it);
        finish();
    }

    public void Registro(View view) {
        Intent it = new Intent(this, RegistroActiviy.class);
        startActivity(it);
        finish();
    }
}