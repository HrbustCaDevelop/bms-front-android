package com.ca.bms.front.activity;



import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.apache.http.HttpRequest;

import com.ca.bms.dto.SensorDTO;
import com.ca.bms.enumtype.UserStatusEnum;
import com.ca.bms.front.line.Line;
import com.ca.bms.front.utils.A_UserDTO;
import com.ca.bms.msg.dto.ReturnMsgDTO;
import com.ca.bms.msg.dto.ReturnMsgDataDTO;
import com.ca.bms.msg.dto.ReturnMsgSensorDTO;
import com.ca.bms.msg.dto.ReturnMsgUserDTO;
import com.example.demo.R;
import com.slidingmenu.lib.SlidingMenu;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
	
	int time = 0;
	boolean l_flag = true;
	AlertDialog alert;
	AlertDialog L_alert;
	String sensor_num = null;
	XYMultipleSeriesDataset mDataset ; 
	XYMultipleSeriesRenderer mRenderer; 
	Line mline = new Line();
	EditText NUM;
	String[] itemStrings = new String[100000];
	GraphicalView  l_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        movetoleft();
        new Thread(get_run).start();
        mline.set("Co","light","temper");
        lineView(mline);

//        while(sensor_num!=null){
//        	new Thread(runnable).start();
//        	break;
//        }
      Button exit = (Button)findViewById(R.id.menu_quit);
      exit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO �Զ����ɵķ������
				System.exit(0);
			}
		});
      Button sensor_bind = (Button)findViewById(R.id.sensor_bind);
      sensor_bind.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO �Զ����ɵķ������
			alert = new AlertDialog.Builder(MainActivity.this)  
	        .setTitle("�����봫������")  
	        .setIcon(android.R.drawable.ic_dialog_info)  
	        .setView(NUM = new EditText(MainActivity.this))  
	        .setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO �Զ����ɵķ������
					new Thread(bind_run).start();
				}
			})
	        .show();
			
		}
	});
      Button b_cancel = (Button)findViewById(R.id.menu_cancel);
      b_cancel.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO �Զ����ɵķ������
			//System.exit(0);
			new Thread(cancel_run).start();
		}
	});
      Button b_update = (Button)findViewById(R.id.user_update);
      b_update.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO �Զ����ɵķ������
			Intent intent = new Intent();
	        /* ָ��intentҪ�������� */
	        intent.setClass(MainActivity.this,UpdateActivity.class);
	        /* ����һ���µ�Activity */
	        MainActivity.this.startActivity(intent);
	        /* �رյ�ǰ��Activity */
	        MainActivity.this.finish();
		}
	});
    }
    
    Handler cancel = new Handler(){
	    @Override
	    public void handleMessage(Message msg) {
	        super.handleMessage(msg);
	        Bundle data = msg.getData();
	        String val = data.getString("value");
//	        Toast.makeText(LoginActivity, UserStatusEnum.parse(val).getDisplayName(), Toast.LENGTH_SHORT);
	        if(val.equals("USER_IS_LOGOUT")){
	        	/* �½�һ��Intent���� */
	            Intent intent = new Intent();
//	            intent.putExtra("usertoken",ret.getUsertoken());
//	            intent.putExtra("username",ret.);
	            /* ָ��intentҪ�������� */
	            intent.setClass(MainActivity.this,LoginActivity.class);
	            /* ����һ���µ�Activity */
	            MainActivity.this.startActivity(intent);
	            /* �رյ�ǰ��Activity */
	            MainActivity.this.finish();
	        }
	    }
	};

	Runnable cancel_run = new Runnable(){
	    @Override
	    public void run() {
	        //
	        // TODO: http request.
	        //		
	    	ReturnMsgUserDTO can_ret;
	    	can_ret = A_UserDTO.Cancellation(A_UserDTO.m_user.getUserdata().getUsername(),A_UserDTO.m_user.getUsertoken());
	        Message msg = new Message();
	        Bundle data = new Bundle();
	        data.putString("value",can_ret.getReturnmsg());
	        msg.setData(data);
	        cancel.sendMessage(msg);
	    }
	};
	Handler g_get = new Handler(){
	    @Override
	    public void handleMessage(Message msg) {
	        super.handleMessage(msg);
	        Bundle data = msg.getData();
	        String val = data.getString("value");
	        new AlertDialog.Builder(MainActivity.this)  
    		.setTitle("��ѡ��")  
    		.setIcon(android.R.drawable.ic_dialog_info)                  
    		.setSingleChoiceItems(itemStrings, 0,   
    		  new DialogInterface.OnClickListener() {  
    		                              
    		     public void onClick(DialogInterface dialog, int which) {  
    		    	 sensor_num = itemStrings[which];
    		    	 new Thread(runnable).start();
    		        dialog.dismiss();  
    		     }  
    		  }  
    		)  
    		.setNegativeButton("ȡ��", null)  
    		.show();  
	    }
	};

	Runnable get_run = new Runnable(){
	    @Override
	    public void run() {
	        //
	        // TODO: http request.
	        //		
	    	ReturnMsgSensorDTO get_ret = A_UserDTO.mysensor(A_UserDTO.m_user.getUserdata().getUsername(),A_UserDTO.m_user.getUsertoken());
	    	List<SensorDTO> get = get_ret.getSensor();
	    	int i=0;
	    	for(SensorDTO ret : get){
	    		itemStrings[i++] = ret.getSerialNum();
	    	}
	    	Message msg = new Message();
	        Bundle data = new Bundle();
	        data.putString("value",get_ret.getReturnmsg());
	        msg.setData(data);
	        g_get.sendMessage(msg);
	    }
	};
	
    Handler bind = new Handler(){
	    @Override
	    public void handleMessage(Message msg) {
	        super.handleMessage(msg);
	        Bundle data = msg.getData();
	        String val = data.getString("value");
	        AlertDialog bind_alert = new AlertDialog.Builder(MainActivity.this)  
	        .setTitle(val)  
	        .setIcon(android.R.drawable.ic_dialog_info)    
	        .setPositiveButton("ȷ��",null)
	        .show();
	    }
	};

	Runnable bind_run = new Runnable(){
	    @Override
	    public void run() {
	        //
	        // TODO: http request.
	        //		
	    	Message msg = new Message();
	        Bundle data = new Bundle();
	    	ReturnMsgDTO bind_ret;
	    	bind_ret = A_UserDTO.regsensor(A_UserDTO.m_user.getUserdata().getUsername(),A_UserDTO.m_user.getUsertoken(),NUM.getText().toString());
	        data.putString("value",bind_ret.getReturnmsg());
	        msg.setData(data);
	        bind.sendMessage(msg);
	    }
	};
    
    public void update(double s1x,double s2x,double s3x){
    	
    	mline.add(s1x,s2x,s3x,time);
    	time+=7;
//    	if(l_flag)
//    		lineView(mline);
    	l_view.invalidate();
    }

    public void movetoleft(){
    	 // configure the SlidingMenu  
        SlidingMenu menu = new SlidingMenu(this);  
        menu.setMode(SlidingMenu.LEFT);  
        // ���ô�����Ļ��ģʽ  
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);  
        menu.setShadowWidthRes(R.dimen.shadow_width);  
        //menu.setShadowDrawable(R.drawable.ic_launcher);  
        // ���û����˵���ͼ�Ŀ��  
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);  
        // ���ý��뽥��Ч����ֵ  
        menu.setFadeDegree(0.35f);  
        /** 
         * SLIDING_WINDOW will include the Title/ActionBar in the content 
         * section of the SlidingMenu, while SLIDING_CONTENT does not. 
         */  
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);  
        //Ϊ�໬�˵����ò���  
        menu.setMenu(R.layout.menu);
        menu.setOnOpenedListener(new SlidingMenu.OnOpenedListener() {
			
			@Override
			public void onOpened() {
				// TODO �Զ����ɵķ������
				l_flag = false;
			}
		});
        menu.setOnClosedListener(new SlidingMenu.OnClosedListener() {
			
			@Override
			public void onClosed() {
				// TODO �Զ����ɵķ������
				l_flag = true;
			}
		});
    }
    
    public void lineView(Line aim){  
        //ͬ������Ҫ����dataset����ͼ��Ⱦ��renderer
    	
        l_view = ChartFactory.getLineChartView(MainActivity.this,aim.getmDataset(), aim.getmRenderer());  
        l_view.setBackgroundColor(Color.BLACK);  
//       // set OnTouchListener on TextView
//       view.setOnTouchListener(new View.OnTouchListener() {
//			
//			@Override
//			public boolean onTouch(View arg0, MotionEvent arg1) {
//				// TODO �Զ����ɵķ������
//				l_flag = !l_flag;
//				if(l_flag){
//					Toast.makeText(MainActivity.this, "ȡ��ͼ��ˢ�£��ٴδ�������", Toast.LENGTH_SHORT).show();
//				}else{
//					Toast.makeText(MainActivity.this, "����ͼ��ˢ�£��ٴδ���ȡ��", Toast.LENGTH_SHORT).show();
//				}
//				return false;
//			}
//		}) ;
        setContentView(l_view);  
        movetoleft();
    }
    
    Handler handler = new Handler(){
	    @Override
	    public void handleMessage(Message msg) {
	        super.handleMessage(msg);
	        Bundle data = msg.getData();
	        double co= data.getDouble("Co");
	        double light= data.getDouble("light");
	        double temper= data.getDouble("temper");
	        update(co,light,temper);
	        //lineView(mline);
	    }
	};

	Runnable runnable = new Runnable(){
	    @Override
	    public void run() {
	        //
	        // TODO: http request.
	        //
//	    	while(!NUM.getText().toString().equals("B291")){};
	    	ReturnMsgDataDTO s_return;
			while(true){

				s_return = A_UserDTO.realtime(A_UserDTO.m_user.getUserdata().getUsername(),A_UserDTO.m_user.getUsertoken(),sensor_num);
		        Message msg = new Message();
		        Bundle data = new Bundle();
		        double co= s_return.getData().getCo();
		        double light= s_return.getData().getHumidity();
		        double temper= s_return.getData().getTemperature();
		        data.putDouble("Co",co);
		        data.putDouble("light",light);
		        data.putDouble("temper",temper);
		        msg.setData(data);
		        handler.sendMessage(msg);//
				try {
					Thread.sleep(7000);
				} catch (InterruptedException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
			}
	    }
	};
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}


