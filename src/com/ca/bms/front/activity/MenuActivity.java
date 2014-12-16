package com.ca.bms.front.activity;

import com.example.demo.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends Activity{
	 protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
//        Button exit = (Button)findViewById(R.id.menu_quit);
//        exit.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View arg0) {
//				// TODO 自动生成的方法存根
//				//System.exit(0);
//				Intent intent = new Intent();
////	            intent.putExtra("usertoken",ret.getUsertoken());
////	            intent.putExtra("username",ret.);
//	            /* 指定intent要启动的类 */
//	            intent.setClass(MenuActivity.this,LoginActivity.class);
//	            /* 启动一个新的Activity */
//	            MenuActivity.this.startActivity(intent);
//	            /* 关闭当前的Activity */
//	            MenuActivity.this.finish();
//			}
//		});
	 }
	 public void exit(){
		 System.exit(0);
	 }
}
