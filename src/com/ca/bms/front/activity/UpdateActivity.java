package com.ca.bms.front.activity;

import com.ca.bms.enumtype.UserStatusEnum;
import com.ca.bms.front.utils.A_UserDTO;
import com.ca.bms.msg.dto.ReturnMsgDTO;
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

public class UpdateActivity extends Activity{
	ReturnMsgUserDTO C_ret;
	ReturnMsgDTO R_ret;
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update);
	
        Button m_update = (Button)findViewById(R.id.update_button_update);
        m_update.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
				new Thread(runnable2).start();
			}
		});
        Button cancel = (Button)findViewById(R.id.update_button_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
	            Intent intent = new Intent();
//	            intent.putExtra("usertoken",ret.getUsertoken());
//	            intent.putExtra("username",ret.);
	            /* 指定intent要启动的类 */
	            intent.setClass(UpdateActivity.this,MainActivity.class);
	            /* 启动一个新的Activity */
	            UpdateActivity.this.startActivity(intent);
	            /* 关闭当前的Activity */
	            UpdateActivity.this.finish();
			}
		});
	}
   
    Handler handler2 = new Handler(){
	    @Override
	    public void handleMessage(Message msg) {
	        super.handleMessage(msg);
	        Bundle data = msg.getData();
	        String val = data.getString("value");
	        TextView t_message = (TextView) findViewById(R.id.update_textView_msg);
	        t_message.setText(UserStatusEnum.parse(R_ret.getReturnmsg()).getDisplayName());
	        if(val.equals("UPDATE_SUCCESS")){
	            Intent intent = new Intent();
//	            intent.putExtra("usertoken",ret.getUsertoken());
//	            intent.putExtra("username",ret.);
	            /* 指定intent要启动的类 */
	            intent.setClass(UpdateActivity.this,MainActivity.class);
	            /* 启动一个新的Activity */
	            UpdateActivity.this.startActivity(intent);
	            /* 关闭当前的Activity */
	            UpdateActivity.this.finish();
	        }
	    }
	};

	Runnable runnable2 = new Runnable(){
	    @Override
	    public void run() {
	        //
	        // TODO: http request.
	        //		
	    	EditText pass = (EditText) findViewById(R.id.update_editText_pass);
	    	EditText nick = (EditText) findViewById(R.id.update_editText_nick);
	    	EditText phone = (EditText) findViewById(R.id.update_editText_phone);
			R_ret = A_UserDTO.update(A_UserDTO.m_user.getUserdata().getUsername(),A_UserDTO.m_user.getUsertoken(), pass.getText().toString(), nick.getText().toString(), phone.getText().toString());
	        Message msg = new Message();
	        Bundle data = new Bundle();
	        data.putString("value",R_ret.getReturnmsg());
	        msg.setData(data);
	        handler2.sendMessage(msg);
	    }
	};
}
