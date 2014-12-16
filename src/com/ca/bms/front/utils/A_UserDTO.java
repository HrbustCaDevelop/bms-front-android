package com.ca.bms.front.utils;

import com.alibaba.fastjson.JSON;
import com.ca.bms.msg.dto.ReturnMsgDTO;
import com.ca.bms.msg.dto.ReturnMsgDataDTO;
import com.ca.bms.msg.dto.ReturnMsgSensorDTO;
import com.ca.bms.msg.dto.ReturnMsgUserDTO;

public class A_UserDTO {
	public static ReturnMsgUserDTO m_user;
	public static ReturnMsgUserDTO register(String username,String password,String nickname,String phonenum) {
		// TODO 自动生成的构造函数存根
		String user;
		ReturnMsgUserDTO Return_user;
		if(phonenum == null){
			user = HttpRequest.sendPost("http://222.27.196.234/bms/user/add", "username="+username+"&"+"password="+password+"&"+"nickname="+nickname);
			Return_user = JSON.parseObject(user, ReturnMsgUserDTO.class);
			return Return_user;
		}else{
			user = HttpRequest.sendPost("http://222.27.196.234/bms/user/add", "username="+username+"&"+"password="+password+"&"+"nickname="+nickname+"&"+"phonenum="+phonenum);
			Return_user = JSON.parseObject(user, ReturnMsgUserDTO.class);
			return Return_user;
		}
	}
	
	public static ReturnMsgUserDTO login(String username,String password){
		String login;
		ReturnMsgUserDTO Return_login;
		login = HttpRequest.sendPost("http://222.27.196.234/bms/user/login", "username="+username+"&"+"password="+password);
		Return_login = JSON.parseObject(login, ReturnMsgUserDTO.class);
		return Return_login;
	}
	
	public static ReturnMsgUserDTO Cancellation(String username,String usertoken){
		String Cancellation;
		ReturnMsgUserDTO Return_Cancellation;
		Cancellation = HttpRequest.sendPost("http://222.27.196.234/bms/user/logout", "username="+username+"&"+"usertoken="+usertoken);
		Return_Cancellation = JSON.parseObject(Cancellation, ReturnMsgUserDTO.class);
		return Return_Cancellation;
	}
	
	public static ReturnMsgSensorDTO mysensor(String username,String usertoken){
		String mysensor;
		ReturnMsgSensorDTO Return_mysensor;
		mysensor = HttpRequest.sendPost("http://222.27.196.234/bms/user/mysensor", "username="+username+"&"+"usertoken="+usertoken);
		Return_mysensor = JSON.parseObject(mysensor, ReturnMsgSensorDTO.class);
		return Return_mysensor;
	}
	
	public static ReturnMsgDTO regsensor(String username,String usertoken,String serialnum){
		String regsensor;
		ReturnMsgDTO Return_regsensor;
		regsensor = HttpRequest.sendPost("http://222.27.196.234/bms/user/regsensor", "username="+username+"&"+"usertoken="+usertoken+"&"+"serialnum="+serialnum);
		Return_regsensor = JSON.parseObject(regsensor, ReturnMsgDTO.class);
		return Return_regsensor;
	}
	
	public static ReturnMsgDataDTO realtime(String username,String usertoken,String serialnum){
		ReturnMsgDataDTO Return_realtime;
		String realtime;
		realtime = HttpRequest.sendPost("http://222.27.196.234/bms/sensordata/realtime", "username="+username+"&"+"usertoken="+usertoken+"&"+"serialnum="+serialnum);
		Return_realtime = JSON.parseObject(realtime, ReturnMsgDataDTO.class);
		return Return_realtime;
	}
	public static ReturnMsgDTO update(String username,String usertoken,String password,String nickname,String phonenum){
		ReturnMsgDTO Return_update;
		String update;
		String buf;
		buf = "username="+username+"&"+"usertoken="+usertoken+"&";
		if(password != null)
			buf = buf+"password="+password+"&";
		if(nickname != null)
			buf = buf+"nickname="+nickname+"&";
		if(phonenum != null)
			buf = buf+"phonenum="+phonenum;
		update = HttpRequest.sendPost("http://222.27.196.234/bms/user/update",buf);
		Return_update = JSON.parseObject(update, ReturnMsgDTO.class);
		return Return_update;
	}
	public static ReturnMsgUserDTO checkusername(String username){
		String check;
		ReturnMsgUserDTO Return_check;
		check = HttpRequest.sendPost("http://222.27.196.234/bms/user/checkusername", "username="+username);
		Return_check = JSON.parseObject(check, ReturnMsgUserDTO.class);
		return Return_check;
	}
}
