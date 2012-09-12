package com.mobile.passaregua;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;

public class ListaItens extends Activity {

	//@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_itens);
        TextView txtName = (TextView) findViewById(R.id.textView10);
        Button btnClose = (Button) findViewById(R.id.button10);
 
        Intent i = getIntent();
        // Receiving the Data
        String operacoes = i.getStringExtra("operacoes");
 
        // Displaying Received data
        txtName.setText(operacoes);
 
        // Binding Click event to Button
        btnClose.setOnClickListener(new View.OnClickListener() {public void onClick(View arg0) {finish();}});
	}
}