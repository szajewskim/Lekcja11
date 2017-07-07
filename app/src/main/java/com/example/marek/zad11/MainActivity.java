package com.example.marek.zad11;

import android.app.Activity;
import android.database.sqlite.SQLiteCursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    Button addData;
    EditText eName;
    EditText eBrand;
    EditText eYear;
    EditText eid;
    BaseHelper dh;
    Button getdata;
    Button deletedata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dh = new BaseHelper(this);
        addData = (Button) findViewById(R.id.button);
        eName = (EditText) findViewById(R.id.editText);
        eBrand = (EditText) findViewById(R.id.editText2);
        eYear = (EditText) findViewById(R.id.editText3);
        getdata = (Button) findViewById(R.id.button2);
        deletedata = (Button) findViewById(R.id.button3);
        eid = (EditText) findViewById(R.id.editText4);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dh.addData(eName.getText().toString(),eBrand.getText().toString(),eYear.getText().toString())){
                    Toast.makeText(MainActivity.this,"ADDED",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this,"ERROR",Toast.LENGTH_LONG).show();
                }
            }
        });
        getdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteCursor kursor = dh.getData();
                if (kursor.getCount() > 0){
                    StringBuffer buff = new StringBuffer();
                    while (kursor.moveToNext()){
                        buff.append("ID: "+kursor.getString(0)+"\n");
                        buff.append("Nazwa: "+kursor.getString(1)+"\n");
                        buff.append("Marka: "+kursor.getString(2)+"\n");
                        buff.append("Pochodzenie: "+kursor.getString(3)+"\n");
                    }
                    showMessage("Rekord",buff.toString());
                }
                else{
                    showMessage("Brak rekordow","Dodaj rekord");
                }
            }
        });
        deletedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dh.delateData(eid.getText().toString()))
                    Toast.makeText(MainActivity.this,"Usunieto",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Blad",Toast.LENGTH_LONG).show();
            }
        });
    }
    public void showMessage(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setMessage(message);
        builder.setTitle(title);
        builder.show();

    }
}