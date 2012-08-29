package com.mobile.passaregua;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PassaRegua extends Activity {
	  private EditText total;
	  private EditText subtotal;
	  private EditText suaparte;
	  private EditText acrescenta;
		
      float newSubTotal;
      float newAcrescenta;
      float newSuaParte;
      float newTotal;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passa_regua);
        total = (EditText) findViewById(R.id.editText1);
        subtotal = (EditText) findViewById(R.id.editText3);
        suaparte = (EditText) findViewById(R.id.EditText02);
        acrescenta = (EditText) findViewById(R.id.editText2);
        newSubTotal = 0;
        newAcrescenta = 0;
        newSuaParte = 0;
        newTotal = 0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_passa_regua, menu);
        return true;
    }

    public void addValue(View view) {
        switch (view.getId()) {
        case R.id.button1:
	
        	if(newSubTotal >= Float.parseFloat(total.getText().toString())){
                Toast.makeText(this, "Alooo... Vai pagar mais do que o total?", Toast.LENGTH_LONG).show();
                return;
        	}
        		
        	
	        newSuaParte = newSuaParte + Float.parseFloat(acrescenta.getText().toString());
	        newSubTotal = Float.parseFloat(total.getText().toString()) - newSuaParte; 
	        
	        subtotal.setText(String.valueOf(newSubTotal));
            suaparte.setText(String.valueOf(newSuaParte));
	        break;
        }
      }
}
