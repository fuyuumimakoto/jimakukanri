package pojo;

import java.io.Serializable;
import java.util.Date;

public class Forusm_info implements Serializable{

int id;
int fid;
int uid;
String text;
String re_time;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getFid() {
	return fid;
}
public void setFid(int fid) {
	this.fid = fid;
}
public int getUid() {
	return uid;
}
public void setUid(int uid) {
	this.uid = uid;
}
public String getRe_time() {
	return re_time;
}
public void setRe_time(String re_time) {
	this.re_time = re_time;
}
public String getText() {
	return text;
}
public void setText(String text) {
	this.text = text;
}


}
