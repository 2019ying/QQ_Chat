package cn.edu.ldu.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ��������
 */
public class Server {
	public void startService() {
		try {
			ServerSocket ss = new ServerSocket(8081);
			Socket socket = null;
			// ѭ�������ͻ��˵�����,ÿ����һ���ͻ��� ��Ϊ��ʵ����һ���߳�
			// �ڸ��߳� I/O���������ͻ��˷��͵���Ϣ,��Ȼֻ���յ�һ����Ϣ~
			while (true) {
				socket = ss.accept();
				ServerThread thread = new ServerThread(socket);
				thread.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}