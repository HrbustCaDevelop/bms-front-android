package com.ca.bms.front.activity;

import com.ca.bms.enumtype.UserStatusEnum;
import com.ca.bms.front.utils.A_UserDTO;
import com.ca.bms.msg.dto.ReturnMsgUserDTO;
import com.example.demo.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity  extends Activity{
	ReturnMsgUserDTO ret;
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        final EditText username = (EditText)findViewById(R.id.username);
        final EditText password = (EditText)findViewById(R.id.password);
        Button login = (Button)findViewById(R.id.button_login);
        final TextView h_return = (TextView)findViewById(R.id.l_return); 
        TextView reg = (TextView)findViewById(R.id.reg);
        reg.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent();
	            /* 指定intent要启动的类 */
	            intent.setClass(LoginActivity.this,RegActivity.class);
	            /* 启动一个新的Activity */
	            LoginActivity.this.startActivity(intent);
	            /* 关闭当前的Activity */
	            LoginActivity.this.finish();
			}
		});
        
        login.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
				new Thread(runnable).start();
			}
			Handler handler = new Handler(){
			    @Override
			    public void handleMessage(Message msg) {
			        super.handleMessage(msg);
			        Bundle data = msg.getData();
			        String val = data.getString("value");
			        h_return.setText(UserStatusEnum.parse(ret.getReturnmsg()).getDisplayName());
			        if(val.equals("LOGIN_SUCCESS") ){
			        	/* 新建一个Intent对象 */
			            Intent intent = new Intent();
//			            intent.putExtra("usertoken",ret.getUsertoken());
//			            intent.putExtra("username",ret.);
			            /* 指定intent要启动的类 */
			            A_UserDTO.m_user = ret;
			            intent.setClass(LoginActivity.this,MainActivity.class);
			            /* 启动一个新的Activity */
			            LoginActivity.this.startActivity(intent);
			            /* 关闭当前的Activity */
			            LoginActivity.this.finish();
			        }
			        else{
			        	h_return.setText(UserStatusEnum.parse(ret.getReturnmsg()).getDisplayName());
			        }
			    }
			};

			Runnable runnable = new Runnable(){
			    @Override
			    public void run() {
			        //
			        // TODO: http request.
			        //				
					ret = A_UserDTO.login(username.getText().toString(),password.getText().toString());
			        Message msg = new Message();
			        Bundle data = new Bundle();
			        data.putString("value",ret.getReturnmsg());
			        msg.setData(data);
			        handler.sendMessage(msg);
			    }
			};
		});
	}

}
