package controller.AccountService;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import Service.DbService;
import controller.mainui.mainui;
import controller.nevbar.navbar;
import dao.impl.UserDaoImpl;
import model.users;
import java.awt.Color;

public class AccountServiceUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField accountin;
	private JTextField passwordin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccountServiceUI frame = new AccountServiceUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AccountServiceUI() {
		AccountServiceUI self = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 799, 545);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(98, 182, 203));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("帳號");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("新細明體", Font.BOLD, 31));
		lblNewLabel.setBounds(174, 128, 75, 52);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("密碼");
		lblNewLabel_1.setFont(new Font("新細明體", Font.BOLD, 31));
		lblNewLabel_1.setBounds(174, 216, 75, 58);
		contentPane.add(lblNewLabel_1);
		
		accountin = new JTextField();
		accountin.setBackground(new Color(202, 233, 255));
		accountin.setFont(new Font("新細明體", Font.PLAIN, 30));
		accountin.setBounds(316, 132, 280, 44);
		contentPane.add(accountin);
		accountin.setColumns(10);
		
		passwordin = new JTextField();
		passwordin.setBackground(new Color(202, 233, 255));
		passwordin.setFont(new Font("新細明體", Font.PLAIN, 30));
		passwordin.setColumns(10);
		passwordin.setBounds(316, 223, 280, 44);
		contentPane.add(passwordin);
		
		JButton log_b = new JButton("登入");
		log_b.setBackground(new Color(202, 233, 255));
		log_b.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(accountin.getText().trim().isEmpty() || passwordin.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "帳號或密碼空白" );
				}
				else {
					UserDaoImpl dao = new UserDaoImpl();
			        users user = dao.login(accountin.getText().trim(), passwordin.getText().trim());

			        if (user != null) {
			            JOptionPane.showMessageDialog(null, "登入成功！歡迎 " + user.getName());
			            self.setVisible(false);
			            navbar frame = new navbar(user);
						frame.setVisible(true);
			            // 這裡可以開啟主畫面
			        } else {
			            JOptionPane.showMessageDialog(null, "帳號或密碼錯誤");
			        }
				}
			}
		});
		log_b.setFont(new Font("新細明體", Font.PLAIN, 31));
		log_b.setBounds(190, 370, 133, 87);
		contentPane.add(log_b);
		
		JButton sign_b = new JButton("註冊");
		sign_b.setBackground(new Color(202, 233, 255));
		sign_b.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				self.setVisible(false);
				signUI frame = new signUI(self);
		        frame.setVisible(true);
			}
		});
		sign_b.setFont(new Font("新細明體", Font.PLAIN, 31));
		sign_b.setBounds(474, 370, 133, 87);
		contentPane.add(sign_b);
		
		JLabel lblNewLabel_2 = new JLabel("健康管理系統");
		lblNewLabel_2.setFont(new Font("新細明體", Font.BOLD, 40));
		lblNewLabel_2.setForeground(new Color(27, 73, 101));
		lblNewLabel_2.setBounds(310, 38, 253, 58);
		contentPane.add(lblNewLabel_2);
		
		ImageIcon icon = new ImageIcon(getClass().getResource("logo.png"));
		Image img = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		JLabel logo = new JLabel(new ImageIcon(img));
		logo.setBounds(220, 20, 80, 78);
		contentPane.add(logo);

	}
}
