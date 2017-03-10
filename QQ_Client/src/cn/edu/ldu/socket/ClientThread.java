package cn.edu.ldu.socket;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import cn.edu.ldu.util.CommandTranser;


/**
 * �ͻ����߳��ࡣһ������������Ϊ�俪��һ���߳� 
 * I/O�������շ���˷��͵�����
 */
public class ClientThread extends Thread {
	private Client client;//�ͻ��˶���
	private boolean isOnline = true;//��ǰ������Ƿ����
	private JTextArea chat_txt;//�����

	public ClientThread(Client client, JTextArea chat_txt) {
		this.client = client;
		this.chat_txt = chat_txt;
	}

	public boolean isOnline() {
		return isOnline;
	}

	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}

	@Override
	public void run() {
		while (isOnline) {
			//I/O����  ���շ���˷��͵�����
			CommandTranser msg = client.getData();
			if (msg != null) {
				/*
				 * �������˴������ݳɹ���������Ϣ
				 * ���򵯳��Է������ߵĶԻ���
				 */
				if (msg.isFlag()) {
					Date date = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat(
							"hh:mm:ss a");
					String message = msg.getSender() + "˵��"
							+ (String) msg.getData() + "\t" + sdf.format(date)
							+ "\n";
					// �����������յ�����Ϣ
					chat_txt.append(message);
				} else {
					JOptionPane.showMessageDialog(chat_txt, msg.getResult());
				}

			}
		}
	}
}