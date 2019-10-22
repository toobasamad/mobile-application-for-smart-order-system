package com.testing.www;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class Dlghistory extends Activity {
	TextView tvHisSurity;
	ImageView ivHisTick ,ivHisCross;
	Bundle bundle;
	SQLiteDatabase db;
	String name;
	int Total=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_dlghistory);
		db = openOrCreateDatabase("CART",MODE_PRIVATE, null);
		db.execSQL("CREATE TABLE IF NOT EXISTS IQRA(Deal VARCHAR,Quantity VARCHAR,Price VARCHAR,DATEnTIME VARCHAR)");
		tvHisSurity = (TextView) findViewById(R.id.tvHisSurity);
		ivHisTick = (ImageView) findViewById(R.id.ivHisTick);
		ivHisCross = (ImageView) findViewById(R.id.ivHisCross);
		bundle = getIntent().getExtras();
		if (bundle != null) {
          	String msg = bundle.getString("Message");
          	tvHisSurity.setText(msg);
		}
		
ivHisCross.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(Dlghistory.this, OrderHistory.class);
				startActivity(intent);
				// TODO Auto-generated method stub
				
			}
		});
		ivHisTick.setOnClickListener(new OnClickListener() {
			
			
			@Override
			public void onClick(View arg0) {

				name = bundle.getString("name");
				db.delete("IQRA","Deal = '"+name+"';" , null);
				Intent intent = new Intent(Dlghistory.this, OrderHistory.class);
				startActivity(intent);
				// TODO Auto-generated method stub
				
			}
		});
	}
}
	

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.dlghistory, menu);
//		return true;
//	}
//
//}
