package pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class task_info implements Serializable {
int id;
String name;
String url;
int isdone;
int tid;
int part;
List<member> members;
int[] staff_id;
String[] files_name;
int time;
public int getPart() {
	return part;
}
public int getTime() {
	return time;
}
public void setTime(int time) {
	this.time = time;
}
public int[] getStaff_id() {
	return staff_id;
}
public void setStaff_id(int[] staff_id) {
	this.staff_id = staff_id;
}
public String[] getFiles_name() {
	return files_name;
}
public void setFiles_name(String[] files_name) {
	this.files_name = files_name;
}
public void setPart(int part) {
	this.part = part;
}


public List<member> getMembers() {
	return members;
}
public void setMembers(List<member> members) {
	this.members = members;
}
public int getTid() {
	return tid;
}
public void setTid(int tid) {
	this.tid = tid;
}
int createuid;
Date createdate;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
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
public int getIsdone() {
	return isdone;
}
public void setIsdone(int isdone) {
	this.isdone = isdone;
}
public Date getCreatedate() {
	return createdate;
}
public void setCreatedate(Date createdate) {
	this.createdate = createdate;
}
public int getCreateuid() {
	return createuid;
}
public void setCreateuid(int createuid) {
	this.createuid = createuid;
}


}
