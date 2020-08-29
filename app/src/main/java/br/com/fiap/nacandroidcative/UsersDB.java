package br.com.fiap.nacandroidcative;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class UsersDB extends SQLiteOpenHelper {

    private static final String NOME_DB = "Cative";
    private static final String NOME_TABELA = "usuarios";

    public UsersDB(Context context) {
        super(context, NOME_DB, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE usuarios (" +
                " id INTEGER NOT NULL," +
                " username TEXT NOT NULL UNIQUE," +
                " password TEXT NOT NULL," +
                " PRIMARY KEY(id AUTOINCREMENT)" +
                ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public void registrar(Usuario usuario) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("username", usuario.getUsername());
        cv.put("password", usuario.getPassword());

        db.insert(NOME_TABELA, null, cv);
    }

    public boolean buscarPorUsuario(String username) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(
                NOME_TABELA,
                new String[]{"id", "username", "password"},
                "username = ?",
                new String[]{username},
                null,
                null,
                null
        );

        cursor.moveToFirst();
        if(cursor.moveToFirst()) {
            do{
                Usuario usuario = new Usuario();
                usuario.setId(cursor.getInt(0));
                usuario.setUsername(cursor.getString(1));
                usuario.setPassword(cursor.getString(2));
                return true;
            }while(cursor.moveToNext());
        }
        cursor.close();
        return false;
    }


    public Usuario Logar(String username) {
        Usuario usuario = new Usuario();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(
                NOME_TABELA,
                new String[]{"id", "username", "password"},
                "username = ?",
                new String[]{username},
                null,
                null,
                null
        );

        cursor.moveToFirst();
        if(cursor.moveToFirst()) {
            do{
                usuario.setId(cursor.getInt(0));
                usuario.setUsername(cursor.getString(1));
                usuario.setPassword(cursor.getString(2));
                return usuario;
            }while(cursor.moveToNext());
        }
        cursor.close();
        return usuario;
    }
}

