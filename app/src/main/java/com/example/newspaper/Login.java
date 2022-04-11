package com.example.newspaper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.newspaper.R;

public class Login extends AppCompatActivity {

    EditText txtLogin, txtPassword;
    DataBaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtLogin = findViewById(R.id.txtLogin);
        txtPassword = findViewById(R.id.txtPassword);
        databaseHelper = new DataBaseHelper(this);
    }

    public void enterClick(View view) {
        Intent intent = new Intent(this, AllNewsAdmin.class);
        Cursor res = databaseHelper.getData(txtLogin.getText().toString().trim(), txtPassword.getText().toString().trim());
        if (res.getCount() == 0) {
            Toast.makeText(this, "Неверный логин или пароль", Toast.LENGTH_SHORT).show();
            return;
        }
        while (res.moveToNext()) {
            if (res.getString(4).equals("Администратор")) {
                intent.putExtra("Id", res.getInt(0));
                startActivity(intent);
            } else {
                startActivity(new Intent(this, AllNews.class));
            }
        }
    }

    public void registrationClick(View view) {
        startActivity(new Intent(this, Registration.class));
    }
}