package cn.edu.ldu.util;

import java.net.Socket;
import java.util.HashMap;

import cn.edu.ldu.socket.SocketThread;

/**
 *	�����Ѿ��ɹ���¼��������socket���ǳ�
 */

public class SocketList {
	private static HashMap<String, Socket> map=new HashMap<String, Socket>();
	//��SocketThread�뼯��
	public static void addSocket(SocketThread socketThread){
		map.put(socketThread.getName(), socketThread.getSocket());
	}
	//ͨ���ǳƷ���socket
	public static Socket getSocket(String name){
		return map.get(name);
	}
}