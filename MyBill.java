package com.testing.www;

import java.util.ArrayList;
import java.util.HashMap;

import android.R.string;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.telephony.gsm.SmsManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MyBill extends Activity {
	SQLiteDatabase db;
	Cursor cursor;
	ListView lvBill;
	String [] deals;
	String [] price;
	String [] quantity;
	String [] order;
	String All_Deals[];
	String name ;
	int i = 0 , Total = 0;
	ImageView ivdone,home,Delete;
	
	HashMap<String,String> hm;
	AlertDialog alert ;
	TextView TvTotalBill ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_bill);
		TvTotalBill = (TextView) findViewById(R.id.IdTvTot);
		lvBill = (ListView) findViewById(R.id.IdLvBill);
		db = openOrCreateDatabase("CART",MODE_PRIVATE, null);
		db.execSQL("CREATE TABLE IF NOT EXISTS BILL(DealName VARCHAR, Qty VARCHAR , Price VARCHAR)");
		ivdone = (ImageView) findViewById(R.id.ivHogaya);
		home = (ImageView) findViewById(R.id.ivHomebtn);
		Delete = (ImageView) findViewById(R.id.ivBillDelete);
		cursor = db.rawQuery("SELECT * FROM BILL", null);
		if (cursor != null)
		{
			if (cursor.getCount() > 0)
			{
				cursor.moveToFirst();
				All_Deals = new String[cursor.getCount()];
				while (!cursor.isAfterLast()) 
				{
					
					//All_Deals[i++] = cursor.getString(cursor.getColumnIndex("DealName"));
					Total += Integer.parseInt(cursor.getString(cursor.getColumnIndex("Price")));
					cursor.moveToNext();
				}
				//Toast.makeText(MyBill.this, String.valueOf(Total), Toast.LENGTH_LONG).show();
				TvTotalBill.setText(String.valueOf(Total));
				 //name = All_Deals[index];
			}
		}
		ivdone.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				cursor = db.rawQuery("SELECT * FROM BILL", null);
				if (cursor != null) {
					
				
				if (cursor.getCount()>0) {
					String DEALS = "" + quantity;
					StringBuilder builder = new StringBuilder();
					for (int i = 0; i < deals.length; i++) {
					DEALS =  deals [i] + "" + quantity[i];
					order[i]=DEALS;
						builder.append(""+order[i]+""+",");
					}				
					String MyOrder = String.valueOf(builder);
					Intent intent = new Intent(MyBill.this, Dialogue.class);
					intent.putExtra("ORDER", MyOrder);
					startActivity(intent);
						
				
					
				}
				else {
					Toast.makeText(MyBill.this, "no item", Toast.LENGTH_LONG).show();
				}
				}
		
				// TODO Auto-generated method stub
			
				
			}
		});
		lvBill.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,int index, long arg3)
			{
				
				i = 0 ;
				cursor = db.rawQuery("SELECT * FROM BILL", null);
				if (cursor != null)
				{
					if (cursor.getCount() > 0)
					{
						cursor.moveToFirst();
						All_Deals = new String[cursor.getCount()];
						while (!cursor.isAfterLast()) 
						{
							All_Deals[i++] = cursor.getString(cursor.getColumnIndex("DealName"));
							Total += Integer.parseInt(cursor.getString(cursor.getColumnIndex("Qty")));
							cursor.moveToNext();
						}
						 name = All_Deals[index];
					}
				}

				Intent intent = new Intent(MyBill.this, Dlg.class);
				String txt = "Are you sure you want to delete?";
				intent.putExtra("name", name);
				intent.putExtra("Message", txt);
				startActivity(intent);
			}
		});		


		home.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MyBill.this, MainActivity.class);
				startActivity(intent);
				// TODO Auto-generated method stub
				
			}
		});
		Delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				cursor = db.rawQuery("SELECT * FROM BILL",null);
				if (cursor != null) {
					if (cursor.getCount() > 0) {
				
				Intent intent = new Intent(MyBill.this, DelDialogue.class);
				String txt = "Are you sure you want to delete all?";
				intent.putExtra("Message", txt);
				startActivity(intent);
				// TODO Auto-generated method stub
					}
					else {
						Toast.makeText(MyBill.this, "There is nothing to delete.", Toast.LENGTH_LONG).show();
					}	
			
				}
			}
		});
		cursor = db.rawQuery("SELECT * FROM BILL",null);
		if (cursor != null) {
			if (cursor.getCount() > 0) {
				cursor.moveToFirst();
			
				order = new String [cursor.getCount()];
				deals = new String [cursor.getCount()];
				quantity = new String [cursor.getCount()];
				price = new String [cursor.getCount()];
				while (!cursor.isAfterLast()) {
					
					deals [i] = cursor.getString(cursor.getColumnIndex("DealName"));
					quantity[i] = cursor.getString(cursor.getColumnIndex("Qty"));
					price[i++] = cursor.getString(cursor.getColumnIndex("Price"));
					
					cursor.moveToNext();
					
					
				}
				
				ArrayList<HashMap<String, String>> al = new ArrayList<HashMap<String,String>>();
				for (int i = 0; i < deals.length; i++) {
					hm = new HashMap<String, String>();
					hm.put("Name", deals[i]);
					hm.put("Quantity", quantity[i]);
					hm.put("Price", price[i]);
					
					al.add(hm);
					//Total += Total + Integer.parseInt(price[i]) ;
					
					
				}
				
				
				
					String [] from = {"Name","Quantity","Price"};
					int [] to = {R.id.TvDealName,R.id.TvQty,R.id.IdTvMyDealNae};
					SimpleAdapter adapter = new SimpleAdapter(MyBill.this, al, R.layout.newrow, from, to);
					lvBill.setAdapter(adapter);
					
				//TvTotalBill.setText(Total);
				
			}
				
			
			
			else
			{
				//Toast.makeText(MyBill.this, "No item selected", Toast.LENGTH_LONG).show();
			}
			
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_bill, menu);
		return true;
	}

}
