package pojo;

import java.util.Date;

public class Forusm {
int id;
int bid;//blockid
int uid;//user id of create the forusm
String title;
String create_time;
String re_time;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getBid() {
	return bid;
}
public void setBid(int bid) {
	this.bid = bid;
}
public int getUid() {
	return uid;
}
public String getCreate_time() {
	return create_time;
}
public void setCreate_time(String create_time) {
	this.create_time = create_time;
}
public String getRe_time() {
	return re_time;
}
public void setRe_time(String re_time) {
	this.re_time = re_time;
}
public void setUid(int uid) {
	this.uid = uid;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}


}
