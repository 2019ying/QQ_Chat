package cn.edu.ldu.bean;

import java.io.Serializable;

/**
 * �û���Ϣ��ʵ����
 */
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private String username;// �˺�
	private String password;// ����
	private String sex; //�Ա�
	private String realname; //��ʵ����
	private String phone; //�ֻ���
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	public User(String username, String password, String realname, String sex, String phone) {
		this.username = username;
		this.password = password;
		this.sex = sex;
		this.realname = realname;
		this.phone = phone;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}