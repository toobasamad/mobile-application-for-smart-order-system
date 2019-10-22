package com.testing.www;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.telephony.gsm.SmsManager;
import android.text.Editable;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Dialogue extends Activity {
	
Bundle bundle;
TextView Message;
String msg;
ImageView BtnDone,BtnCancel ;
SQLiteDatabase db;
Cursor cursor;
    String [] Qty ;
    String [] Price ;
    String [] DealName ;
 int i = 0 ;
String SMS;
EditText etAdd ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_dialogue);
		Message = (TextView) findViewById(R.id.tvmsg);
		etAdd = (EditText) findViewById(R.id.etAdd);
		BtnCancel = (ImageView) findViewById(R.id.dlgbtncancel);
		bundle= getIntent().getExtras();
		if (bundle!=null) {
			msg = bundle.getString("ORDER");
			Message.setText(msg);
			}
		BtnDone = (ImageView) findViewById(R.id.dlgbtndone);
		db= openOrCreateDatabase("CART", MODE_PRIVATE, null);
	    db.execSQL("CREATE TABLE IF NOT EXISTS IQRA(Deal VARCHAR,Quantity VARCHAR,Price VARCHAR,DATEnTIME VARCHAR)");
		db.execSQL("CREATE TABLE IF NOT EXISTS BILL(DealName VARCHAR, Qty VARCHAR, Price VARCHAR)");
		BtnDone.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) 
			{
				Calendar calender = Calendar.getInstance();
				SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				String CurrTime = df.format(calender.getTime());
				Toast.makeText(Dialogue.this,""+CurrTime,Toast.LENGTH_LONG).show();
				
				
				
				  SMS = etAdd.getText().toString();
				
				if (SMS.equals(""))
				{
					Toast.makeText(Dialogue.this,"Please Enter Address",Toast.LENGTH_LONG).show();
					
				}
				else {
					SmsManager sms = SmsManager.getDefault();
				sms.sendTextMessage("03236549787", null, msg + SMS,null,null);	
				Toast.makeText(Dialogue.this,"Your order has been placed.",Toast.LENGTH_LONG).show();
				//db.delete("BILL", null , null);
				Intent intent = new Intent(Dialogue.this,MyBill.class);
				startActivity(intent);
				
				//finish();
				}
				
				cursor = db.rawQuery("SELECT * FROM BILL", null);
				if (cursor != null)
				{
					if (cursor.getCount() > 0)
					{
						//Toast.makeText(Dialogue.this, "Success", Toast.LENGTH_LONG).show();
						Qty = new String[cursor.getCount()];
						Price = new String[cursor.getCount()];
						DealName = new String[cursor.getCount()];
						cursor.moveToFirst();
						while (!cursor.isAfterLast())
						{
							
							DealName[i] = cursor.getString(cursor.getColumnIndex("DealName"));
							Qty[i] = cursor.getString(cursor.getColumnIndex("Qty"));
							Price[i] = cursor.getString(cursor.getColumnIndex("Price"));
							Toast.makeText(Dialogue.this,"d"+DealName[i]+Qty[i] +Price[i],Toast.LENGTH_LONG).show();
							
						
							ContentValues cv = new ContentValues();
							cv.put("Deal", DealName[i]);
							cv.put("Quantity", Qty[i]);
							cv.put("Price", Price[i] );
							cv.put("DATEnTIME",CurrTime );
						    i++;
							
							if (db.insert("IQRA", null, cv) > 0)
							{
								Toast.makeText(Dialogue.this, "Success", Toast.LENGTH_LONG).show();
								db.delete("BILL", null , null);
							}
							else
							{
								Toast.makeText(Dialogue.this, "Fail", Toast.LENGTH_LONG).show();
						}
							cursor.moveToNext();
							
						}
					}
				}
				
			}
		});
		BtnCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Dialogue.this, MyBill.class);
				startActivity(intent);
				
				// TODO Auto-generated method stub
				
			}
		});
	
	//	Format format = new 
		
	}
	
}

	
	
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu)
//	{		//Inflate the menu; this adds items to the action bar if it is present.
//		getLayoutInflater().inflate(R.menu.dialogue, menu);
//		return true;
//	}
//
//}
