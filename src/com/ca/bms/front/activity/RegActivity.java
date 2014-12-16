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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegActivity  extends Activity {
	ReturnMsgUserDTO C_ret;
	ReturnMsgUserDTO R_ret;
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg);
        Button check = (Button)findViewById(R.id.button_check);
        check.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
				new Thread(runnable1).start();
			}
			
		});
        Button reg = (Button)findViewById(R.id.button_reg);
        reg.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
				new Thread(runnable2).start();
			}
		});
        Button cancel = (Button)findViewById(R.id.button_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
	            Intent intent = new Intent();
//	            intent.putExtra("usertoken",ret.getUsertoken());
//	            intent.putExtra("username",ret.);
	            /* 指定intent要启动的类 */
	            intent.setClass(RegActivity.this,LoginActivity.class);
	            /* 启动一个新的Activity */
	            RegActivity.this.startActivity(intent);
	            /* 关闭当前的Activity */
	            RegActivity.this.finish();
			}
		});
	}
    Handler handler1 = new Handler(){
	    @Override
	    public void handleMessage(Message msg) {
	        super.handleMessage(msg);
	        Bundle data = msg.getData();
	        String val = data.getString("value");
	        TextView t_message = (TextView) findViewById(R.id.textView_username);
	        t_message.setText(UserStatusEnum.parse(C_ret.getReturnmsg()).getDisplayName());
	    }
	};

	Runnable runnable1 = new Runnable(){
	    @Override
	    public void run() {
	        //
	        // TODO: http request.
	        //				
	    	EditText ed = (EditText) (findViewById(R.id.editText_user));
			C_ret = A_UserDTO.checkusername(ed.getText().toString());
	        Message msg = new Message();
	        Bundle data = new Bundle();
	        data.putString("value",C_ret.getReturnmsg());
	        msg.setData(data);
	        handler1.sendMessage(msg);
	    }
	};
    Handler handler2 = new Handler(){
	    @Override
	    public void handleMessage(Message msg) {
	        super.handleMessage(msg);
	        Bundle data = msg.getData();
	        String val = data.getString("value");
	        TextView t_message = (TextView) findViewById(R.id.textView_msg);
	        t_message.setText(UserStatusEnum.parse(R_ret.getReturnmsg()).getDisplayName());
	        if(val.equals("REGISTER_SUCCESS")){
	            Intent intent = new Intent();
//	            intent.putExtra("usertoken",ret.getUsertoken());
//	            intent.putExtra("username",ret.);
	            /* 指定intent要启动的类 */
	            intent.setClass(RegActivity.this,LoginActivity.class);
	            /* 启动一个新的Activity */
	            RegActivity.this.startActivity(intent);
	            /* 关闭当前的Activity */
	            RegActivity.this.finish();
	        }
	    }
	};

	Runnable runnable2 = new Runnable(){
	    @Override
	    public void run() {
	        //
	        // TODO: http request.
	        //		
	    	EditText e_username = (EditText)findViewById(R.id.editText_user);
	    	EditText pass = (EditText)findViewById(R.id.editText_pass);
	    	EditText nick = (EditText)findViewById(R.id.editText_nick);
	    	EditText phone = (EditText)findViewById(R.id.editText_phone);
			R_ret = A_UserDTO.register(e_username.getText().toString(), pass.getText().toString(), nick.getText().toString(), phone.getText().toString());
	        Message msg = new Message();
	        Bundle data = new Bundle();
	        data.putString("value",R_ret.getReturnmsg());
	        msg.setData(data);
	        handler2.sendMessage(msg);
	    }
	};
}
