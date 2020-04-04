package pojo;

import java.io.Serializable;

public class member implements Serializable{
int uid;
int role;
public int getUid() {
	return uid;
}
public void setUid(int uid) {
	this.uid = uid;
}
public int getRole() {
	return role;
}
public void setRole(int role) {
	this.role = role;
}
}
