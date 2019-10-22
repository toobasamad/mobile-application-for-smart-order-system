package com.testing.www;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class OrderHistory extends Activity {
	ListView lvHistory;
	ImageView ivHisHome ,ivHisDelete;
	SQLiteDatabase db;
	HashMap<String , String> hm;
	Cursor cursor;
	String [] DEAL;
	String [] QTY;
    ArrayList<HashMap<String, String>> allvalues ;
	String [] DATEnTIME;
	String [] All_Deals;
	int i = 0 , Total = 0;
	String name;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_order_history);
		lvHistory = (ListView) findViewById(R.id.lvOrderHistory);
		ivHisHome = (ImageView) findViewById(R.id.IdHisHome);
		ivHisDelete = (ImageView) findViewById(R.id.IdHisDel);
		db = openOrCreateDatabase("CART",MODE_PRIVATE, null);
		db.execSQL("CREATE TABLE IF NOT EXISTS IQRA(Deal VARCHAR,Quantity VARCHAR,Price VARCHAR,DATEnTIME VARCHAR)");
		db.execSQL("CREATE TABLE IF NOT EXISTS BILL(DealName VARCHAR, Qty VARCHAR, Price VARCHAR)");
        cursor = db.rawQuery("SELECT * FROM IQRA", null);
        allvalues = new ArrayList<HashMap<String, String>>();
        if (cursor != null)
        {
			if (cursor.getCount() > 0)
			{
				cursor.moveToFirst();
				DEAL = new String[cursor.getCount()];
				DATEnTIME = new String[cursor.getCount()];
				QTY = new String[cursor.getCount()];
				while (!cursor.isAfterLast())
				{
					hm = new HashMap<String, String>();
					hm.put("DN", cursor.getString(cursor.getColumnIndex("Deal")));
					hm.put("Qty", cursor.getString(cursor.getColumnIndex("Quantity")));
					hm.put("Dta", cursor.getString(cursor.getColumnIndex("DATEnTIME")));
					allvalues.add(hm);

					cursor.moveToNext();
				}
				
				
				ListView LvQtty = (ListView) findViewById(R.id.lvOrderHistory);
				String [] from = {"DN" , "Qty" , "Dta"};
				int [] to = {R.id.DN , R.id.Qt , R.id.Dt};
				SimpleAdapter adapter = new SimpleAdapter(OrderHistory.this	, allvalues, R.layout.hisrow, from, to);
				//ArrayAdapter<String> adapter = new ArrayAdapter<String>(OrderHistory.this, android.R.layout.simple_list_item_1, QTY);
				LvQtty.setAdapter(adapter);
			}
			else
			{
				Toast.makeText(getApplicationContext(), "dsdsds", Toast.LENGTH_LONG).show();
			}
		}
		
        lvHistory.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,int index, long arg3)
			{
				
				i = 0 ;
				cursor = db.rawQuery("SELECT * FROM IQRA", null);
				if (cursor != null)
				{
					if (cursor.getCount() > 0)
					{
						cursor.moveToFirst();
						All_Deals = new String[cursor.getCount()];
						while (!cursor.isAfterLast()) 
						{
							All_Deals[i++] = cursor.getString(cursor.getColumnIndex("Deal"));
							Total += Integer.parseInt(cursor.getString(cursor.getColumnIndex("Quantity")));
							cursor.moveToNext();
						}
						 name = All_Deals[index];
					}
				}

				Intent intent = new Intent(OrderHistory.this, Dlghistory.class);
				String txt = "Are you sure you want to delete?";
				intent.putExtra("name", name);
				intent.putExtra("Message", txt);
				startActivity(intent);
			}
		});		

		
		ivHisHome.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(OrderHistory.this, MainActivity.class);
				startActivity(intent);
				finish();
				// TODO Auto-generated method stub
				
			}
		});
//	ivHisDelete.setOnClickListener(new OnClickListener() {
//		
//		@Override
//		public void onClick(View v) {
//			// TODO Auto-generated method stub
//			cursor = db.rawQuery("SELECT * FROM IQRA",null);
//			if (cursor != null) {
//				if (cursor.getCount() > 0) {
//			
//			Intent intent = new Intent(OrderHistory.this, DelHisDialogue.class);
//			String txt = "Are you sure you want to delete all?";
//			intent.putExtra("Message", txt);
//			startActivity(intent);
//			// TODO Auto-generated method stub
//				}
//				else {
//					Toast.makeText(OrderHistory.this, "There is nothing to delete.", Toast.LENGTH_LONG).show();
//				}	
//		
//			}
//			
//		}
//	});
	

		
		ivHisHome.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(OrderHistory.this, MainActivity.class);
				startActivity(intent);
				// TODO Auto-generated method stub
				
			}
		});
	ivHisDelete.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			cursor = db.rawQuery("SELECT * FROM IQRA",null);
			if (cursor != null) {
				if (cursor.getCount() > 0) {
			
			Intent intent = new Intent(OrderHistory.this, DelHisDialogue.class);
			String txt = "Are you sure you want to delete all?";
			intent.putExtra("Message", txt);
			startActivity(intent);
			// TODO Auto-generated method stub
				}
				else {
					Toast.makeText(OrderHistory.this, "There is nothing to delete.", Toast.LENGTH_LONG).show();
				}	
		
			}
		}
	});
	
		
	}
	public class AllValues
	{
		String DName , DQty , DDate ;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.order_history, menu);
		return true;
	}

}
