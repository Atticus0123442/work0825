package controller.mainui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AccountService.AccountServiceUI;
import dao.impl.Exercise_logsDaoImpl;
import dao.impl.Food_logsDaoImpl;
import dao.impl.UserDaoImpl;
import model.users;

import javax.swing.JTextField;
import javax.swing.Timer;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class mainui_old extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField welout;
	private JTextField advfood;
	private JTextField advexe;
	private JTextField userfood;
	private JTextField userexe;
	private JTextField foodck;
	private JTextField execk;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserDaoImpl dao = new UserDaoImpl();
					users user = new users();
					user=dao.getById(1);
					mainui_old frame = new mainui_old(user);
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
	public mainui_old() {
		 this(null);
	    }
	public mainui_old(users user) {
		super("首頁", false, false, false, false); // 禁止最大化、最小化、可關閉
	    setSize(800, 500);
	    setLocation(20, 20);
	    ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null); // 移除標題欄
	    getContentPane().setLayout(null);
		mainui_old self = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 882, 545);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		welout = new JTextField();
		welout.setEditable(false);
		welout.setFont(new Font("新細明體", Font.PLAIN, 25));
		welout.setBounds(267, 10, 578, 38);
		contentPane.add(welout);
		welout.setColumns(10);
		
		
		
		
		JButton databtn = new JButton("個人資料");
		databtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UserDaoImpl dao = new UserDaoImpl();
				users newuser=new users();
				newuser=dao.getById(user.getId());
//				userdataUI frame = new userdataUI(newuser,self);
//				frame.setVisible(true);
				self.dispose();
			}
		});
		databtn.setFont(new Font("新細明體", Font.PLAIN, 20));
		databtn.setBounds(44, 10, 193, 38);
		contentPane.add(databtn);
		
		JButton foodbtn = new JButton("飲食紀錄");
		foodbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UserDaoImpl dao = new UserDaoImpl();
				users newuser=new users();
				newuser=dao.getById(user.getId());
				fooddata frame = new fooddata(newuser);
				frame.setVisible(true);
				self.dispose();
			}
		});
		foodbtn.setFont(new Font("新細明體", Font.PLAIN, 31));
		foodbtn.setBounds(79, 327, 261, 99);
		contentPane.add(foodbtn);
		
		JButton exebtn = new JButton("運動紀錄");
		exebtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UserDaoImpl dao = new UserDaoImpl();
				users newuser=new users();
				newuser=dao.getById(user.getId());
				exedata frame = new exedata(newuser);
				frame.setVisible(true);
				self.dispose();
			}
		});
		exebtn.setFont(new Font("新細明體", Font.PLAIN, 31));
		exebtn.setBounds(518, 327, 261, 99);
		contentPane.add(exebtn);
		
		JLabel lblNewLabel = new JLabel("每日建議熱量攝取");
		lblNewLabel.setFont(new Font("新細明體", Font.PLAIN, 25));
		lblNewLabel.setBounds(71, 131, 213, 38);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("每日建議運動量");
		lblNewLabel_1.setFont(new Font("新細明體", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(71, 237, 186, 38);
		contentPane.add(lblNewLabel_1);
		
		advfood = new JTextField();
		advfood.setFont(new Font("新細明體", Font.PLAIN, 20));
		advfood.setEditable(false);
		advfood.setColumns(10);
		advfood.setBounds(280, 130, 123, 38);
		contentPane.add(advfood);
		
//		BMR = 10 × 體重(kg) + 6.25 × 身高(cm) − 5 × 年齡 + 5 M
//		BMR = 10 × 體重(kg) + 6.25 × 身高(cm) − 5 × 年齡 − 161 F
		double adv=0;
		if(user.getGender().equals("M")) {
			adv=10*user.getWeight()+6.25*user.getHeight()-5*user.getAge()+5;
		}
		else {
			adv=10*user.getWeight()+6.25*user.getHeight()-5*user.getAge()-161;
		}
		advfood.setText(String.format("%.1f kcal", adv));

		
		advexe = new JTextField();
		advexe.setFont(new Font("新細明體", Font.PLAIN, 20));
		advexe.setEditable(false);
		advexe.setColumns(10);
		advexe.setBounds(267, 236, 123, 38);
		contentPane.add(advexe);
		
		
		double advexes=(adv*1.55)-adv;
		advexe.setText(String.format("%.1f kcal", advexes));
		
		JLabel lblNewLabel_2 = new JLabel("今日熱量攝取");
		lblNewLabel_2.setFont(new Font("新細明體", Font.PLAIN, 25));
		lblNewLabel_2.setBounds(434, 131, 155, 38);
		contentPane.add(lblNewLabel_2);
		
		userfood = new JTextField();
		userfood.setFont(new Font("新細明體", Font.PLAIN, 20));
		userfood.setEditable(false);
		userfood.setColumns(10);
		userfood.setBounds(599, 130, 123, 38);
		contentPane.add(userfood);
		
		Food_logsDaoImpl dao = new Food_logsDaoImpl();
		userfood.setText(String.valueOf(dao.all(user.getId())) + " kcal");

		
		JLabel lblNewLabel_1_1 = new JLabel("今日運動量");
		lblNewLabel_1_1.setFont(new Font("新細明體", Font.PLAIN, 25));
		lblNewLabel_1_1.setBounds(434, 237, 155, 38);
		contentPane.add(lblNewLabel_1_1);
		
		userexe = new JTextField();
		userexe.setFont(new Font("新細明體", Font.PLAIN, 20));
		userexe.setEditable(false);
		userexe.setColumns(10);
		userexe.setBounds(599, 236, 123, 38);
		contentPane.add(userexe);
		
		foodck = new JTextField();
		foodck.setFont(new Font("新細明體", Font.PLAIN, 20));
		foodck.setEditable(false);
		foodck.setColumns(10);
		foodck.setBounds(745, 131, 100, 38);
		contentPane.add(foodck);
		// 去掉 " kcal" 再轉 double
		String foodText = userfood.getText().replace("kcal", "").trim();
		double userFoodValue = Double.parseDouble(foodText);

		if (userFoodValue >= adv) {
		    foodck.setText("達標");
		} else {
		    foodck.setText("尚未達標");
		}
		
		
		execk = new JTextField();
		execk.setFont(new Font("新細明體", Font.PLAIN, 20));
		execk.setEditable(false);
		execk.setColumns(10);
		execk.setBounds(745, 237, 100, 38);
		contentPane.add(execk);
		
		Exercise_logsDaoImpl dao2 = new Exercise_logsDaoImpl();
		userexe.setText(String.valueOf(dao2.all(user.getId())) + " kcal");
		
		String exeText = userexe.getText().replace("kcal", "").trim();
		double userExeValue = Double.parseDouble(exeText);

		if (userExeValue >= advexes) {
		    execk.setText("達標");
		} else {
		    execk.setText("尚未達標");
		}
		// 啟動 Timer 每秒更新時間
        startTimer(user.getName());
	}
	
	private void startTimer(String name) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");

        Timer timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LocalDateTime now = LocalDateTime.now();
                welout.setText("歡迎！"+ name + "， 現在時間：" +now.format(formatter));
            }
        });
        timer.start();
    }

}
