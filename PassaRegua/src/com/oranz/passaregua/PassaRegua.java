package com.oranz.passaregua;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class PassaRegua extends Activity {
	  private static EditText total;
	  private static EditText suaparte;
	  private EditText subtotal;
	  private EditText acrescenta;
	  private EditText restaEdit;
	  private EditText suaparteEdit;
	 
	  private TextView passareguaLogo;
	  private TextView restaText;
	  private TextView suaparteText;
	  private TextView tenpercentText;
	  private TextView totalText;
	  private TextView acrescentaitemText;
	  private TextView valorText;
	  private TextView qtdText;
	  private TextView xTotalText;
	  private TextView xValorText;
	  
	  private ToggleButton tenpercentButton;
	  private Button historico;
	  private Spinner spinner;
	  
	  private static List<String> itens;

      float newSubTotal;
      float newAcrescenta;
      float newSuaParte;
      float newTotal;
      float newSuaParteTenPercent;
      float newSubTotalTenPercent;
      
      private String array_spinner[];
      DecimalFormat df = new DecimalFormat("0.00");  

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passa_regua);
        
        total = (EditText) findViewById(R.id.editText1);
        subtotal = (EditText) findViewById(R.id.editText3);
        suaparte = (EditText) findViewById(R.id.EditText02);
        acrescenta = (EditText) findViewById(R.id.editText2);
        restaEdit = (EditText) findViewById(R.id.editText3);
        suaparteEdit = (EditText) findViewById(R.id.EditText02);
        

        passareguaLogo = (TextView) findViewById(R.id.passareguaLogo);
        restaText = (TextView) findViewById(R.id.textView4);
        suaparteText = (TextView) findViewById(R.id.textView2);
        tenpercentText = (TextView) findViewById(R.id.textView5);
        totalText = (TextView) findViewById(R.id.textView1);
        acrescentaitemText = (TextView) findViewById(R.id.textView6);
        valorText = (TextView) findViewById(R.id.textView3);
        qtdText = (TextView) findViewById(R.id.textView7);
        xTotalText = (TextView) findViewById(R.id.xTotalTextView);
        xValorText = (TextView) findViewById(R.id.xValorTextView);
        
        
        tenpercentButton = (ToggleButton) findViewById(R.id.toggleButton1);
        
        historico = (Button) findViewById(R.id.button2);
        
        Typeface fontRockwell = Typeface.createFromAsset(this.getAssets(), "fonts/sketchRockwell-bold.ttf");
        Typeface carefree = Typeface.createFromAsset(this.getAssets(), "fonts/carefree.ttf");
        
        passareguaLogo.setTypeface(fontRockwell);
        restaText.setTypeface(carefree);
        suaparteText.setTypeface(carefree);
        tenpercentText.setTypeface(carefree);
        totalText.setTypeface(carefree);
        acrescentaitemText.setTypeface(carefree);
        valorText.setTypeface(carefree);
        qtdText.setTypeface(carefree);
        xTotalText.setTypeface(carefree);
        xValorText.setTypeface(carefree);
        
        itens = new ArrayList<String>();
        
        newSubTotal = 0;
        newAcrescenta = 0;
        newSuaParte = 0;
        newTotal = 0;
        
        array_spinner=new String[10];
        array_spinner[0]="1";
        array_spinner[1]="2";
        array_spinner[2]="3";
        array_spinner[3]="4";
        array_spinner[4]="5";
        array_spinner[5]="6";
        array_spinner[6]="7";
        array_spinner[7]="8";
        array_spinner[8]="9";
        array_spinner[9]="10";
        spinner = (Spinner) findViewById(R.id.spinner1);            
		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array_spinner);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter2);
        spinner.setSelection(0);
    
        
    	Toast.makeText(this, "Informe o valor Total da Conta e acrescente cada item consumido com sua quantidade", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean result = super.onCreateOptionsMenu(menu);
        menu.add(0, 1, Menu.NONE, R.string.info );
        return result;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch ( item.getItemId() ) {
          case 1:   	
              Toast.makeText(this, "Passa Regua v2.0\nDesenvolvido por Oranz", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void addValue(View view) {
        switch (view.getId()) {
        case R.id.button1:
        	float tempSuaParte = 0;
        	float tempSubTotal = 0;
            ToggleButton tenPercentButton = (ToggleButton) findViewById(R.id.toggleButton1);
            String operacao = "";
                    	
            if (total.getText().length() == 0) {
                Toast.makeText(this, "Favor Inserir o total", Toast.LENGTH_LONG).show();
                return;
              }
            
            if (acrescenta.getText().length() == 0 || Float.parseFloat(acrescenta.getText().toString()) <= 0) {
                Toast.makeText(this, "Favor Inserir o valor do item a acrescentar", Toast.LENGTH_LONG).show();
                return;
              }
              
            restaText.setVisibility(0);
            restaEdit.setVisibility(0);
      	  	suaparteText.setVisibility(0);
    	  	tenpercentText.setVisibility(0);
    	  	suaparteEdit.setVisibility(0);
    	  	tenpercentButton.setVisibility(0);
    	  	historico.setVisibility(0);
    	  	xTotalText.setVisibility(1);
    	  	
    	  	operacao = ("Soma: " + acrescenta.getText().toString()) + " x " + Integer.parseInt(spinner.getSelectedItem().toString());
	        tempSuaParte = newSuaParte + (Float.parseFloat(acrescenta.getText().toString()) * Integer.parseInt(spinner.getSelectedItem().toString()));
	        tempSubTotal = Float.parseFloat(total.getText().toString()) - tempSuaParte; 
	        spinner.setSelection(0);	        
	        
	        if(tempSuaParte > Float.parseFloat(total.getText().toString())) {
	            Toast.makeText(this, "Sua parte ultrapassa o total da conta", Toast.LENGTH_LONG).show();
	            return;
	          }
	        
	        newTotal = Float.parseFloat(total.getText().toString());
	        
	        if(tenPercentButton.isChecked()){
	        	tenPercentButton.setChecked(false);
	        	Toast.makeText(this, "10% Desligado", Toast.LENGTH_LONG).show();
	        }
	        
	        itens.add(operacao + " = " + tempSuaParte);
	        
	        newSuaParte = tempSuaParte;
	        newSubTotal = tempSubTotal;

	        acrescenta.setText("");
	        subtotal.setText(String.valueOf(df.format(newSubTotal)));
            suaparte.setText(String.valueOf(df.format(newSuaParte)));
	        break;
        }
      }
    
    public void calculateTenPercent(View view){
    	switch (view.getId()) {
        case R.id.toggleButton1:
            ToggleButton tenPercentButton = (ToggleButton) findViewById(R.id.toggleButton1);
            
            if((newSuaParte * 1.1) > newTotal){
	            Toast.makeText(this, "10% não pode ser calculado. Sua parte ultrapassará o total.", Toast.LENGTH_LONG).show();  
	            if(tenPercentButton.isChecked()){
		        	tenPercentButton.setChecked(false);
		        }
            	return;
            }
            
        	if(tenPercentButton.isChecked()){
        		newSuaParteTenPercent = (float) (newSuaParte * 1.1);
        		newSubTotalTenPercent = Float.parseFloat(total.getText().toString()) - newSuaParteTenPercent;
    	        
    	        subtotal.setText(String.valueOf(df.format(newSubTotalTenPercent)));
                suaparte.setText(String.valueOf(df.format(newSuaParteTenPercent)));
	            return;        		
        	}else{
        		subtotal.setText(String.valueOf(df.format(newSubTotal)));
                suaparte.setText(String.valueOf(df.format(newSuaParte)));
        		return;
        	}
    	}
    }
    
    public void verHistorico(View view){    	
    	Intent nextScreen = new Intent(getApplicationContext(), ListaItens.class);
    	
    	String operacoes = "";
    	int i = 1;
    	for (String item: itens) {
			operacoes += "[" + i + "] " + item + "\n";
			i++;
		}
    	operacoes += "\nSua Parte: " + newSuaParte;
    	
    	nextScreen.putExtra("operacoes", operacoes);
    	startActivity(nextScreen);
    }
    

    
    public void subtrair(View view){
        switch (view.getId()) {
        case R.id.button3:
        	float tempSuaParte = 0;
        	float tempSubTotal = 0;
            String operacao = "";
            ToggleButton tenPercentButton = (ToggleButton) findViewById(R.id.toggleButton1);

	        if (acrescenta.getText().length() == 0 || Float.parseFloat(acrescenta.getText().toString()) <= 0) {
	            Toast.makeText(this, "Favor Inserir o valor do item a subtrair", Toast.LENGTH_LONG).show();
	            return;
	        }
            
	    	if (newTotal <= 0){
	            Toast.makeText(this, "Total igual ou inferior a zero", Toast.LENGTH_LONG).show();
	            return;    		
	    	}	        
	
		  	operacao = ("Subtrai: " + acrescenta.getText().toString()) + " x " + Integer.parseInt(spinner.getSelectedItem().toString());
	        tempSuaParte = newSuaParte - (Float.parseFloat(acrescenta.getText().toString()) * Integer.parseInt(spinner.getSelectedItem().toString()));
	        tempSubTotal = Float.parseFloat(total.getText().toString()) - tempSuaParte; 
	        spinner.setSelection(0);	        
	        
	        if(tempSuaParte < 0) {
	            Toast.makeText(this, "Sua parte não pode ser inferior a zero", Toast.LENGTH_LONG).show();
	            return;
	          }
	        
	        newTotal = Float.parseFloat(total.getText().toString());
	        
	        if(tenPercentButton.isChecked()){
	        	tenPercentButton.setChecked(false);
	        	Toast.makeText(this, "10% Desligado", Toast.LENGTH_LONG).show();
	        }	        

	        itens.add(operacao + " = " + tempSuaParte);
	        
	        newSuaParte = tempSuaParte;
	        newSubTotal = tempSubTotal;
	
	        acrescenta.setText("");
	        subtotal.setText(String.valueOf(df.format(newSubTotal)));
	        suaparte.setText(String.valueOf(df.format(newSuaParte)));
	        break;
        }
    }

	public static String getTotal() {
		return total.getText().toString();
	}
	
	public static String getSuaparte() {
		return suaparte.getText().toString();
	}

	public static List<String> getItens() {
		return itens;
	}    
}
