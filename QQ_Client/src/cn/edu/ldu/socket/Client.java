package cn.edu.ldu.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import cn.edu.ldu.util.CommandTranser;

/**
 * �ͻ��˴���
 */
public class Client {
	private int port = 8081;
	private String address = "127.0.0.1";
	private Socket socket;

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	// ʵ����ʱ ��������
	public Client() {
		try {
			socket = new Socket(address, port);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "�����δ����");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "�����δ����");
		}
	}

	// �����˷�������
	public void sendData(CommandTranser msg) {
		ObjectOutputStream oos = null;
		try {
			if (socket == null)
				return;
			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(msg);
		} catch (UnknownHostException e1) {
			JOptionPane.showMessageDialog(null, "�����δ����");
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "�����δ����");
		}
	}

	// �����˽�������
	public CommandTranser getData() {
		ObjectInputStream ois = null;
		CommandTranser msg = null;
		if (socket == null)
			return null;
		try {
			ois = new ObjectInputStream(socket.getInputStream());
			msg = (CommandTranser) ois.readObject();
		} catch (IOException e) {
			return null;
		} catch (ClassNotFoundException e) {
			return null;
		}
		if ("message".equals(msg.getCmd()))
			System.out.println((String) msg.getData());
		return msg;
	}
}