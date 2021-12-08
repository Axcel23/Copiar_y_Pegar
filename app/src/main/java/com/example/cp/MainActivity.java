package com.example.cp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{
      EditText  txtcopy;
      TextView txtpegar;
      Button btncopy,btnpegar;
    ClipboardManager clipboardManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtcopy= (EditText) findViewById(R.id.txtcopy);
        txtpegar=(TextView) findViewById(R.id.texto);
        btncopy= (Button) findViewById(R.id.btncopy);
        btnpegar= (Button) findViewById(R.id.btnpegar);

        clipboardManager=(ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

        if (!clipboardManager.hasPrimaryClip()){
            btnpegar.setEnabled(false);
        }

        btncopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text= txtcopy.getText().toString();

                if (!text.equals("")){
                    ClipData clipData=ClipData.newPlainText("text",text);
                    clipboardManager.setPrimaryClip(clipData);

                    Toast.makeText(MainActivity.this,"Texto Copiado",Toast.LENGTH_SHORT).show();
                    btnpegar.setEnabled(true);
                }
            }
        });

        btnpegar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipData clipData=clipboardManager.getPrimaryClip();
                ClipData.Item item=clipData.getItemAt(0);

                txtpegar.setText(item.getText().toString());
                Toast.makeText(MainActivity.this,"Pegar",Toast.LENGTH_SHORT).show();
            }
        });

    }


}