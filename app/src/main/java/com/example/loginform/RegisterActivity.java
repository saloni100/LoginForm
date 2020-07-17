package com.example.loginform;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    String itemtype = " ";
    EditText edt_username, edt_type, edt_password;
    Button registerbtn;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }
        setContentView(R.layout.activity_register);
        initView();
        setClickListeners();
    }

    private void initView() {
        db = new DatabaseHelper(this);
        edt_username = findViewById(R.id.edt_username);
        edt_password = findViewById(R.id.edt_password);
        edt_type = findViewById(R.id.edt_type);
        registerbtn = findViewById(R.id.btn_register);
    }

    private void setClickListeners() {
        edt_type.setOnClickListener(this);
        registerbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id =v.getId();
        switch (id){
            case R.id.edt_type : openTypeMenu(); break;
            case R.id.btn_register : validateData(); break;
        }
    }

    private void openTypeMenu() {
        //Creating the instance of PopupMenu
        PopupMenu popup = new PopupMenu(RegisterActivity.this, edt_type);
        //Inflating the Popup using xml file
        popup.getMenuInflater()
                .inflate(R.menu.menu, popup.getMenu());
        //registering popup with OnMenuItemClickListener
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                itemtype = (String) item.getTitle();
                edt_type.setText(item.getTitle());
                Toast.makeText(
                        RegisterActivity.this,
                        "You Clicked : " + item.getTitle(),
                        Toast.LENGTH_SHORT
                ).show();
                return true;
            }
        });
        popup.show(); //showing popup menu
    }

    private void validateData() {
        String username = edt_username.getText().toString().trim();
        String password = edt_password.getText().toString().trim();
        String type = itemtype;
        boolean failFlag = false;
        if (username.length() == 0) {
            failFlag = true;
            edt_username.setError("Please fill the username");
        }
        if (password.length() == 0) {
            failFlag = true;
            edt_password.setError("Password is required");
        }
        if (itemtype.equals(" ")) {
            failFlag = true;
            edt_type.setError("type cannot be null");
        }

        if (failFlag == false) {
            db.addUser(username, password, type);
            Toast.makeText(
                    RegisterActivity.this,
                    "User information added in database",
                    Toast.LENGTH_SHORT
            ).show();

        }
    }


}
