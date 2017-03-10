package cn.edu.ldu.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import cn.edu.ldu.socket.Server;

/**
 * ����������
 */
public class StartServer extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JButton startServer_btn;
	private JButton endServer_btn;
	private JLabel p;
	
	public StartServer() {
		setLayout(new FlowLayout());
		startServer_btn = new JButton("����������");
		endServer_btn = new JButton("�رշ�����");
		p = new JLabel("<html><font size=5 color=blue>��Ϣ���������ѧԺ</font><br/>"
				+ "<font size=5 color=blue>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��1403-ʷ��</font><br/>"
				+ "<font size=5 color=blue>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;20142203717</font></html>",SwingConstants.CENTER);
		add(startServer_btn);
		add(endServer_btn);
		add(p);
		setTitle("qq�����");
		setSize(300, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		startServer_btn.addActionListener(this);
		endServer_btn.addActionListener(this);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new StartServer();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		//������˿�����������ť���¿���һ���߳� ������������
		if (e.getSource() == startServer_btn) {
			new startServerThread().start();
			JOptionPane.showMessageDialog(null, "qq�����������ɹ���������...");
		}
		if (e.getSource() == endServer_btn) {
			System.exit(0);
		}
	}
}

class startServerThread extends Thread {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Server s = new Server();
		s.startService();
	}

}