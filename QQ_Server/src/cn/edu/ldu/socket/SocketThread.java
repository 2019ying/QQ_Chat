package cn.edu.ldu.socket;

import java.net.Socket;

/**
 * ���гɹ����ӵ�socketʵ���� ����һ��socket��һ���û���(���˺�)
 */
public class SocketThread {
	private Socket socket;
	private String name;

	public SocketThread() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SocketThread(Socket socket, String name) {
		super();
		this.socket = socket;
		this.name = name;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}