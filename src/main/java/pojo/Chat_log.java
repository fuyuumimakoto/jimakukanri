package pojo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Chat_log implements Serializable {
int uid;
String date;
String text;
public int getUid() {
	return uid;
}
public void setUid(int uid) {
	this.uid = uid;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public String getText() {
	return text;
}
public void setText(String text) {
	this.text = text;
}
public Chat_log(int uid,String text) {
	 String timePattern = "yyyy-MM-dd HH:mm:ss";
	 SimpleDateFormat sdf = new SimpleDateFormat(timePattern);
		String formatedDate = sdf.format(new Date());
		this.text=text;
		this.uid=uid;
		this.date=formatedDate;
	// TODO Auto-generated constructor stub
}
}
