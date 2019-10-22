package com.testing.www;



import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Information extends Activity {
ListView lvcontents;
Bundle bundle;
TextView tvDealName;
EditText eQty;
String Price, DealName, Qty,newqty;
Cursor cursor;
SQLiteDatabase db;
ImageView btncart , btncancel; 
int CurrQty , i = 0;
String []All_Deals ;
String name ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info);
		tvDealName = (TextView) findViewById(R.id.IdTvSelectedDealName);
		lvcontents = (ListView) findViewById(R.id.IdLvBill);
		eQty = (EditText) findViewById(R.id.IdEtQuentity);
		btncart = (ImageView) findViewById(R.id.IdIvDone);
		btncancel = (ImageView) findViewById(R.id.ivBillDelete);
	    db = openOrCreateDatabase("CART", MODE_PRIVATE, null);
		db.execSQL("CREATE TABLE IF NOT EXISTS BILL(DealName VARCHAR, Qty VARCHAR, Price VARCHAR)");
		bundle = getIntent().getExtras();
		if (bundle != null)
		{
			Price = bundle.getString("Price");
			DealName = bundle.getString("DealName");
			String [] Detail = bundle.getStringArray("Detail");
			tvDealName.setText(DealName);
			
			ArrayAdapter<String> Contents = new ArrayAdapter<String>(Information.this, android.R.layout.simple_list_item_1,Detail);
			lvcontents.setAdapter(Contents);
			
			}
		//	
//		
		btncancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(Information.this,HomePage.class);
				startActivity(intent);
				
				// TODO Auto-generated method stub
				
			}
		});
//		
		btncart.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View arg0) {
				Qty = eQty.getText().toString();
				if (Qty.contentEquals(""))
				{
					Toast.makeText(Information.this,"Please make sure u have entered qunatity",Toast.LENGTH_LONG).show();
				}
				else if (Qty.contentEquals("0")){
					Toast.makeText(Information.this,"Please make sure u haven't entered 0",Toast.LENGTH_LONG).show();
					
			
				}
				else 
				 {
					if (IsExists(DealName)) 
					{
						ContentValues cvalue = new ContentValues();
						//cvalue.put("DealName", DealName);
						int q = Integer.parseInt(Qty);
						 q+= CurrQty ;
						 Qty = String.valueOf(q);
						cvalue.put("Qty",Qty );
						int price,qty;
						 price = Integer.parseInt(Price);
						 qty  = Integer.parseInt(Qty);
					     Price = String.valueOf(price*qty);
						 cvalue.put("Price", Price);
						 db.update("BILL", cvalue, "DealName = '"+DealName+"';", null);
						 //db.insert("BILL", null, cvalue);
						//Toast.makeText(Information.this, "Update", Toast.LENGTH_LONG).show();
						Intent intent = new Intent(Information.this,HomePage.class);
						startActivity(intent);
					}
					else
					{
						ContentValues cvalue = new ContentValues();
						cvalue.put("DealName", DealName);
						cvalue.put("Qty",Qty );
						int price,qty;
						 price = Integer.parseInt(Price);
						 qty  = Integer.parseInt(Qty);
					     Price = String.valueOf(price*qty);
						 cvalue.put("Price", Price);
						db.insert("BILL", null, cvalue);
						Toast.makeText(Information.this, "data inserted successfully"+Price, Toast.LENGTH_LONG).show();
						Intent intent = new Intent(Information.this,HomePage.class);
						startActivity(intent);
					}
					
					
					
				}
				
				
				
			
				

				// TODO Auto-generated method stub
				
			}
		});
	}
	public boolean IsExists(String DealName)
	{
		cursor = db.rawQuery("SELECT * FROM BILL WHERE DealName = '"+DealName+"';", null);
		if (cursor != null)
		{
			if (cursor.getCount() > 0)
			{
				cursor.moveToFirst();
				CurrQty = Integer.parseInt(cursor.getString(cursor.getColumnIndex("Qty")));
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.information, menu);
		return true;
	}

}
