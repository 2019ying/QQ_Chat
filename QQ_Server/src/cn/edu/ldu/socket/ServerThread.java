package cn.edu.ldu.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import cn.edu.ldu.bean.User;
import cn.edu.ldu.service.UserService;
import cn.edu.ldu.util.CommandTranser;
import cn.edu.ldu.util.SocketList;

/**
 *  �������߳�
 */
public class ServerThread extends Thread {
	private Socket socket;

	public ServerThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;
		// ʱ�̼��� �ͻ��˷�����������
		while (socket != null) {
			try {
				ois = new ObjectInputStream(socket.getInputStream());
				CommandTranser msg = (CommandTranser) ois.readObject();
				// ����ͻ��˷���������Ϣ
				msg = execute(msg);
				if ("message".equals(msg.getCmd())) {
					/*
					 * ��� msg.ifFlag�� ����������ɹ�,������ú��ѷ�����Ϣ;���������������Ϣʧ��,��Ϣ���͸������߱���
					 */
					if (msg.isFlag()) {
						oos = new ObjectOutputStream(SocketList.getSocket(
								msg.getReceiver()).getOutputStream());
					} else {
						oos = new ObjectOutputStream(socket.getOutputStream());
					}
				}
				// ����ǵ�¼���� ���͸������߱���
				if ("login".equals(msg.getCmd())) {
					oos = new ObjectOutputStream(socket.getOutputStream());
				}
				if ("checkregist".equals(msg.getCmd())) {
					System.out.println("��֤�ɹ�");
					oos = new ObjectOutputStream(socket.getOutputStream());
				}
				if ("regist".equals(msg.getCmd())) {
					System.out.println("ע��ɹ�");
					oos = new ObjectOutputStream(socket.getOutputStream());
				}
				oos.writeObject(msg);
			} catch (IOException e) {
				socket = null;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	// ����ͻ��˷��͵���Ϣ
	private CommandTranser execute(CommandTranser msg) {
		// �����ע����֤����
		if ("checkregist".equals(msg.getCmd())) {
			UserService userService = new UserService();
			User user = (User) msg.getData();
			userService.checkregistUser(user);
			msg.setFlag(userService.checkregistUser(user));
			if(msg.isFlag()) {
				msg.setResult("�û��Ѵ��ڣ�");
			} else {
				System.out.println("����ע�ᣡ");
			}
		}	
		// ��������ע������
		if ("regist".equals(msg.getCmd())) {
			UserService userService = new UserService();
			User user = (User) msg.getData();
			userService.registUser(user);
			msg.setFlag(true);
			msg.setResult("ע��ɹ�");
		}
		// ����ǵ�¼����
		if ("login".equals(msg.getCmd())) {
			UserService userService = new UserService();
			User user = (User) msg.getData();
			msg.setFlag(userService.checkUser(user));
			/*
			 * �����½�ɹ������ÿͻ��˼����Ѿ����ӳɹ���map��������,���ҿ������û��Ľ����߳�
			 */
			if (msg.isFlag()) {
				// �����̼߳������ӳɹ���map����
				SocketThread socketThread = new SocketThread();
				socketThread.setName(msg.getSender());
				socketThread.setSocket(socket);
				SocketList.addSocket(socketThread);
				msg.setResult("��½�ɹ�");
			} else {
				msg.setResult("�˺������������");
			}
		}

		// ����Ƿ�����Ϣ��ָ�� �жϵ�ǰ�û��Ƿ�����
		if ("message".equals(msg.getCmd())) {
			// ���Ҫ���͵��û����� ������Ϣ
			if (SocketList.getSocket(msg.getReceiver()) != null) {
				msg.setFlag(true);
			} else {
				msg.setFlag(false);
				msg.setResult("��ǰ�û�������");
			}
		}
		
		return msg;
	}
}