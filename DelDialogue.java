package com.testing.www;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class DelDialogue extends Activity {
	TextView tvSurity;
	ImageView ivTick ,ivCross;
	Bundle bundle;
	SQLiteDatabase db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_del_dialogue);
		db = openOrCreateDatabase("CART",MODE_PRIVATE, null);
		db.execSQL("CREATE TABLE IF NOT EXISTS BILL(DealName VARCHAR, Qty VARCHAR , Price VARCHAR)");
		tvSurity = (TextView) findViewById(R.id.BillDel);
		ivTick = (ImageView) findViewById(R.id.DelTick);
		ivCross = (ImageView) findViewById(R.id.DelCross);
		bundle = getIntent().getExtras();
		if (bundle != null) {
          	String msg = bundle.getString("Message");
          	tvSurity.setText(msg);
		}
		ivCross.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(DelDialogue.this, MyBill.class);
				startActivity(intent);
				// TODO Auto-generated method stub
				
			}
		});
		ivTick.setOnClickListener(new OnClickListener() {
			
			
			@Override
			public void onClick(View arg0) {

				db.delete("BILL", null, null);
				Intent intent = new Intent(DelDialogue.this, MyBill.class);
				startActivity(intent);
				// TODO Auto-generated method stub
				
			}
		});
	}
}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.del_dialogue, menu);
//		return true;
//	}
//
//}
