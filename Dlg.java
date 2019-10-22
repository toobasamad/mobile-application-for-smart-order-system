package com.testing.www;

import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class Dlg extends Activity {
	TextView tvSurity;
	ImageView ivTick ,ivCross;
	Bundle bundle;
	SQLiteDatabase db;
	String dlgname;
	int Total=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_dlg);
		db = openOrCreateDatabase("CART",MODE_PRIVATE, null);
		db.execSQL("CREATE TABLE IF NOT EXISTS BILL(DealName VARCHAR, Qty VARCHAR , Price VARCHAR)");
		tvSurity = (TextView) findViewById(R.id.tvSurity);
		ivTick = (ImageView) findViewById(R.id.ivTick);
		ivCross = (ImageView) findViewById(R.id.ivCross);
		bundle = getIntent().getExtras();
		if (bundle != null) {
          	String msg = bundle.getString("Message");
          	tvSurity.setText(msg);
		}
		ivCross.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(Dlg.this, MyBill.class);
				startActivity(intent);
				// TODO Auto-generated method stub
				
			}
		});
		ivTick.setOnClickListener(new OnClickListener() {
			
			
			@Override
			public void onClick(View arg0) {

				dlgname = bundle.getString("name");
				db.delete("BILL","DealName = '"+dlgname+"';" , null);
				Intent intent = new Intent(Dlg.this, MyBill.class);
				startActivity(intent);
				// TODO Auto-generated method stub
				
			}
		});
	}

	
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.dlg, menu);
//		return true;
//	}
//
}
