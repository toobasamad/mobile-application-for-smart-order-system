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

public class DelHisDialogue extends Activity {
	TextView tvSurity;
	ImageView ivTick ,ivCross;
	Bundle bundle;
	SQLiteDatabase db;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_del_his_dialogue);
		db = openOrCreateDatabase("CART",MODE_PRIVATE, null);
		db.execSQL("CREATE TABLE IF NOT EXISTS IQRA(Deal VARCHAR,Quantity VARCHAR,Price VARCHAR,DATEnTIME VARCHAR)");
		tvSurity = (TextView) findViewById(R.id.tvHisDel);
		ivTick = (ImageView) findViewById(R.id.Deldone);
		ivCross = (ImageView) findViewById(R.id.DelCancel);
		bundle = getIntent().getExtras();
		if (bundle != null) {
          	String msg = bundle.getString("Message");
          	tvSurity.setText(msg);
		}
		ivCross.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(DelHisDialogue.this, OrderHistory.class);
				startActivity(intent);
				// TODO Auto-generated method stub
				
			}
		});
		ivTick.setOnClickListener(new OnClickListener() {
			
			
			@Override
			public void onClick(View arg0) {

				db.delete("IQRA", null, null);
				Intent intent = new Intent(DelHisDialogue.this, OrderHistory.class);
				startActivity(intent);
				// TODO Auto-generated method stub
				
			}
		});
	}
	

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.del_his_dialogue, menu);
//		return true;
//	}

}
