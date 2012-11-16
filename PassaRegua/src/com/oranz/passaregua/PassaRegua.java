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
	  private TextView valorText;
	  private TextView qtdText;
	  private TextView xTotalText;
	  private TextView xValorText;
	  private TextView dividirText;
	  
	  private ToggleButton tenpercentButton;
	  private Button historico;
	  private Spinner quantidadeSpinner;
	  private Spinner dividirSpinner;
	  
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
        
        total = (EditText) findViewById(R.id.totalET);
        subtotal = (EditText) findViewById(R.id.subtotalET);
        suaparte = (EditText) findViewById(R.id.suaparteET);// DUPLICADO!!!!!!!!!1
        acrescenta = (EditText) findViewById(R.id.acrescentaitemET);
        restaEdit = (EditText) findViewById(R.id.subtotalET);// ISSO TA CERTO?
        suaparteEdit = (EditText) findViewById(R.id.suaparteET);
        

        passareguaLogo = (TextView) findViewById(R.id.passareguaLogo);
        restaText = (TextView) findViewById(R.id.restaTV);
        suaparteText = (TextView) findViewById(R.id.suaparteTV);
        tenpercentText = (TextView) findViewById(R.id.tenpercentTV);
        totalText = (TextView) findViewById(R.id.totalTV);
        valorText = (TextView) findViewById(R.id.valorTV);
        qtdText = (TextView) findViewById(R.id.quantidadeTV);
        xTotalText = (TextView) findViewById(R.id.xTotalTextView);
        xValorText = (TextView) findViewById(R.id.xValorTextView);
        dividirText = (TextView) findViewById(R.id.dividirTV);
        
        quantidadeSpinner = (Spinner) findViewById(R.id.qtdSP);  
        dividirSpinner = (Spinner) findViewById(R.id.dividirSP);        
        
        tenpercentButton = (ToggleButton) findViewById(R.id.tenpercetTB);
        
        historico = (Button) findViewById(R.id.historicoBT);
        
        Typeface fontRockwell = Typeface.createFromAsset(this.getAssets(), "fonts/sketchRockwell-bold.ttf");
        Typeface carefree = Typeface.createFromAsset(this.getAssets(), "fonts/carefree.ttf");
        
        passareguaLogo.setTypeface(fontRockwell);
        restaText.setTypeface(carefree);
        suaparteText.setTypeface(carefree);
        tenpercentText.setTypeface(carefree);
        totalText.setTypeface(carefree);
        valorText.setTypeface(carefree);
        qtdText.setTypeface(carefree);
        xTotalText.setTypeface(carefree);
        xValorText.setTypeface(carefree);
        dividirText.setTypeface(carefree);
        
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
		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array_spinner);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
        quantidadeSpinner.setAdapter(adapter2);
        quantidadeSpinner.setSelection(0);
    
        dividirSpinner.setAdapter(adapter2);
        dividirSpinner.setSelection(0);
        
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
              Toast.makeText(this, "Passa Regua v2.1\nDesenvolvido por Oranz", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void addValue(View view) {
        switch (view.getId()) {
        case R.id.somaBT:
        	float tempSuaParte = 0;
        	float tempSubTotal = 0;
            ToggleButton tenPercentButton = (ToggleButton) findViewById(R.id.tenpercetTB);
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
    	  	
    	  	if(dividirSpinner.getSelectedItem().toString().equalsIgnoreCase("1")){
	    	  	operacao = ("Soma: " + acrescenta.getText().toString()) + " x " + Integer.parseInt(quantidadeSpinner.getSelectedItem().toString());
		        tempSuaParte = newSuaParte + (Float.parseFloat(acrescenta.getText().toString()) * Integer.parseInt(quantidadeSpinner.getSelectedItem().toString()));
		        tempSubTotal = Float.parseFloat(total.getText().toString()) - tempSuaParte; 
    	  	}else{
	    	  	operacao = ("Soma: " + acrescenta.getText().toString() + " x " + Integer.parseInt(quantidadeSpinner.getSelectedItem().toString()) + " / " + dividirSpinner.getSelectedItem().toString());
		        tempSuaParte = newSuaParte + ((Float.parseFloat(acrescenta.getText().toString()) * Integer.parseInt(quantidadeSpinner.getSelectedItem().toString())) / Integer.parseInt(dividirSpinner.getSelectedItem().toString()));
		        tempSubTotal = Float.parseFloat(total.getText().toString()) - tempSuaParte; 
    	  	}
	        quantidadeSpinner.setSelection(0);	   
	        dividirSpinner.setSelection(0);
	        
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
        case R.id.tenpercetTB:
            ToggleButton tenPercentButton = (ToggleButton) findViewById(R.id.tenpercetTB);
            
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
        case R.id.subtraiBT:
        	float tempSuaParte = 0;
        	float tempSubTotal = 0;
            String operacao = "";
            ToggleButton tenPercentButton = (ToggleButton) findViewById(R.id.tenpercetTB);

	        if (acrescenta.getText().length() == 0 || Float.parseFloat(acrescenta.getText().toString()) <= 0) {
	            Toast.makeText(this, "Favor Inserir o valor do item a subtrair", Toast.LENGTH_LONG).show();
	            return;
	        }
            
	    	if (newTotal <= 0){
	            Toast.makeText(this, "Total igual ou inferior a zero", Toast.LENGTH_LONG).show();
	            return;    		
	    	}	        
	
    	  	if(dividirSpinner.getSelectedItem().toString().equalsIgnoreCase("1")){
	    	  	operacao = ("Soma: " + acrescenta.getText().toString()) + " x " + Integer.parseInt(quantidadeSpinner.getSelectedItem().toString());
		        tempSuaParte = newSuaParte + (Float.parseFloat(acrescenta.getText().toString()) * Integer.parseInt(quantidadeSpinner.getSelectedItem().toString()));
		        tempSubTotal = Float.parseFloat(total.getText().toString()) - tempSuaParte; 
    	  	}else{
	    	  	operacao = ("Soma: " + acrescenta.getText().toString() + " x " + Integer.parseInt(quantidadeSpinner.getSelectedItem().toString()) + " / " + dividirSpinner.getSelectedItem().toString());
		        tempSuaParte = newSuaParte + ((Float.parseFloat(acrescenta.getText().toString()) * Integer.parseInt(quantidadeSpinner.getSelectedItem().toString())) / Integer.parseInt(dividirSpinner.getSelectedItem().toString()));
		        tempSubTotal = Float.parseFloat(total.getText().toString()) - tempSuaParte; 
    	  	}
	        quantidadeSpinner.setSelection(0);	   
	        dividirSpinner.setSelection(0);        
	        
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
