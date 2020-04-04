package pojo;

import java.io.Serializable;
import java.util.ArrayList;

public class chat_log_file implements Serializable{
int[] chatuid;
ArrayList<ArrayList<Chat_log>> chatloglist;
public int[] getChatuid() {
	return chatuid;
}
public void setChatuid(int[] chatuid) {
	this.chatuid = chatuid;
}
public ArrayList<ArrayList<Chat_log>> getChatloglist() {
	return chatloglist;
}
public void setChatloglist(ArrayList<ArrayList<Chat_log>> chatloglist) {
	this.chatloglist = chatloglist;
}

}
