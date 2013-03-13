package com.oranz.passaregua;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.ListActivity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ListaItens extends ListActivity {
	  ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
	  private SimpleAdapter notes;
	  
	  private TextView passareguaLogo;
	  private TextView totalTextView;
	  private TextView historicoTextView;
	  
	  private Button btnClose;


	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_itens);
        btnClose = (Button) findViewById(R.id.button10);
        totalTextView = (TextView) findViewById(R.id.totalTextView2);
        historicoTextView = (TextView) findViewById(R.id.textView1);
        passareguaLogo = (TextView) findViewById(R.id.passareguaLogo2);
        
        Typeface fontRockwell = Typeface.createFromAsset(this.getAssets(), "fonts/sketchRockwell-bold.ttf");
        Typeface carefree = Typeface.createFromAsset(this.getAssets(), "fonts/carefree.ttf");
        
        passareguaLogo.setTypeface(fontRockwell);
        historicoTextView.setTypeface(carefree);
        totalTextView.setTypeface(carefree);
 
        List<String> operacoes = PassaRegua.getItens();
 
        notes = new SimpleAdapter( 
 				this, 
 				list,
 				R.layout.main_item_two_line_row,
 				new String[] { "line1","line2" },
 				new int[] { R.id.text1, R.id.text2 }  );
        setListAdapter( notes );
        
        String[] operacao;
        for (String string : operacoes) {
        	operacao = string.split(":");
            addItem(operacao[1],operacao[0]);			
		}
        
        totalTextView.setText("" + (getResources().getString(R.string.total)) + ": " + PassaRegua.getTotal() + "\n" + (getResources().getString(R.string.suaparte)) + ": " + PassaRegua.getSuaparte());
 
        btnClose.setOnClickListener(new View.OnClickListener() {public void onClick(View arg0) {finish();}});
	}
	
    private void addItem(String first, String second) {
    	  HashMap<String,String> item = new HashMap<String,String>();
    	  item.put( "line1", first );
    	  item.put( "line2", second);
    	  list.add( item );
        notes.notifyDataSetChanged();
    	}
}