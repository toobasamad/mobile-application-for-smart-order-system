package com.testing.www;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class HomePage extends Activity {
ImageView ivBurger,ivCrispyDeal,ivTurkey,ivChickenpiece,ivWings,ivChips,ivJuices,ivFries,ivHome ,ivout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homepage);
		ivBurger = (ImageView) findViewById(R.id.Iv1);
		ivCrispyDeal = (ImageView) findViewById(R.id.CrispyDeal);
		ivTurkey = (ImageView) findViewById(R.id.Turkey);
		ivChickenpiece = (ImageView) findViewById(R.id.ChickenPiece);
		ivWings = (ImageView) findViewById(R.id.Wings);
		ivJuices = (ImageView) findViewById(R.id.juices);
		ivFries = (ImageView) findViewById(R.id.Fries);
		ivHome = (ImageView) findViewById(R.id.ivHome);
		ivout = (ImageView) findViewById(R.id.ivbill);
		
		ivBurger.setOnClickListener(new OnClickListener() {
			
		
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(HomePage.this, Information.class);
				intent.putExtra("DealName", "Burger Deal");
				intent.putExtra("Price", "250");
				String [] Content  = {"1 zedburger"  ,"1 regularcoke"};
				intent.putExtra("Detail", Content);
				startActivity(intent);
				
			}
		});
		
		ivCrispyDeal.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				Intent intent = new Intent(getApplicationContext(), Information.class);
				intent.putExtra("DealName", "Crispy Deal");
				intent.putExtra("Price", "350");
				String [] Content  = {"1 zed tower" , "1 regularfries" ,"1 regularcoke"};
				intent.putExtra("Detail", Content);
				startActivity(intent);
				// TODO Auto-generated method stub
				
			}
		});
		ivTurkey.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), Information.class);
				intent.putExtra("DealName", "Turkey");
				intent.putExtra("Price", "400");
				String [] Content  = {"1 Large Turkey (baked)" , "1 liter Drink" };
				intent.putExtra("Detail", Content);
				startActivity(intent);
				// TODO Auto-generated method stub
				
			}
		});
		ivChickenpiece.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getApplicationContext(), Information.class);
				intent.putExtra("DealName", "Chicken Pieces");
				intent.putExtra("Price", "120");
				String [] Content  = {"2 Chicken Pieces" , "1 reg drink" };
				intent.putExtra("Detail", Content);
				startActivity(intent);
				// TODO Auto-generated method stub
				
			}
		});
		ivWings.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getApplicationContext(), Information.class);
				intent.putExtra("DealName", "Wings");
				intent.putExtra("Price", "300");
				String [] Content  = {"1 bucket wings" , "1 reg drink" };
				intent.putExtra("Detail", Content);
				startActivity(intent);
				// TODO Auto-generated method stub
				
			}
		});
		ivJuices.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getApplicationContext(), Information.class);
				intent.putExtra("DealName", "Juices");
				intent.putExtra("Price", "75");
				String [] Content  = {"1 juice from  following available juices" , "apple, mango, lemonade, pineapple" };
				intent.putExtra("Detail", Content);
				startActivity(intent);
				// TODO Auto-generated method stub
				
			}
		});
		ivFries.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getApplicationContext(), Information.class);
				intent.putExtra("DealName", "Family Deal");
				intent.putExtra("Price", "950");
				String [] Content  = {"6 Chicken piece" , "6 zed burger","6 reg fries","6 reg drinks" };
				intent.putExtra("Detail", Content);
				startActivity(intent);
				// TODO Auto-generated method stub
				
			}
		});
		ivHome.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(HomePage.this,MainActivity.class);
				startActivity(intent);
				// TODO Auto-generated method stub
				
			}
		});
		
		ivout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(HomePage.this, MyBill.class);
				startActivity(intent);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home_page, menu);
		return true;
	}

}
