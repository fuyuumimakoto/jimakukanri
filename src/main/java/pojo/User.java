package pojo;

public class User {
    
    int id;
    String password;
    String mail;
    String md5id;
    
	public String getMd5id() {
		return md5id;
	}
	public void setMd5id(String md5id) {
		this.md5id = md5id;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


}