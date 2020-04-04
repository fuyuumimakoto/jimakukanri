package pojo;

import java.util.Date;
import java.util.List;

public class task {
public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
int id;
String name;
String url;
int createuid;
Date createdate;
int tid;
int  isdone;
int part;
int time;
public int getTime() {
	return time;
}
public void setTime(int time) {
	this.time = time;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public int getPart() {
	return part;
}
public void setPart(int part) {
	this.part = part;
}
public int getIsdone() {
	return isdone;
}
public void setIsdone(int isdone) {
	this.isdone = isdone;
}
public void setName(String name) {
	this.name = name;
}
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}
public int getCreateuid() {
	return createuid;
}
public void setCreateuid(int createuid) {
	this.createuid = createuid;
}
public Date getCreatedate() {
	return createdate;
}
public void setCreatedate(Date createdate) {
	this.createdate = createdate;
}

}
