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
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.net.URL;
import java.time.LocalDate;
import com.google.gson.*;
import java.awt.Color;

public class mainui extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField welout;
	private JTextField advfood;
	private JTextField advexe;
	private JTextField userfood;
	private JTextField userexe;
	private JTextField foodck;
	private JTextField execk;
	private JTextField wea_out;
	private JTextField exe_out;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserDaoImpl dao = new UserDaoImpl();
					users user = new users();
					user=dao.getById(0);
					mainui frame = new mainui(user);
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
	public mainui() {
		 this(null);
	    }
	public mainui(users user) {
		
		super("首頁", false, false, false, false); // 禁止最大化、最小化、可關閉
	    setSize(900, 600);
	    ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null); // 移除標題欄
	    getContentPane().setLayout(null);
		mainui self = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 882, 545);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(27, 73, 101));
		contentPane.setBackground(new Color(190, 233, 232));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		welout = new JTextField();
		welout.setForeground(new Color(27, 73, 101));
		welout.setBackground(new Color(202, 233, 255));
		welout.setEditable(false);
		welout.setFont(new Font("微軟正黑體", Font.PLAIN, 22));
		welout.setBounds(62, 25, 772, 38);
		contentPane.add(welout);
		welout.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("每日建議熱量攝取");
		lblNewLabel.setForeground(new Color(27, 73, 101));
		lblNewLabel.setFont(new Font("微軟正黑體", Font.BOLD, 25));
		lblNewLabel.setBounds(62, 141, 213, 38);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("每日建議運動量");
		lblNewLabel_1.setForeground(new Color(27, 73, 101));
		lblNewLabel_1.setFont(new Font("微軟正黑體", Font.BOLD, 25));
		lblNewLabel_1.setBounds(62, 235, 186, 38);
		contentPane.add(lblNewLabel_1);
		
		advfood = new JTextField();
		advfood.setForeground(new Color(27, 73, 101));
		advfood.setBackground(new Color(202, 233, 255));
		advfood.setFont(new Font("新細明體", Font.PLAIN, 20));
		advfood.setEditable(false);
		advfood.setColumns(10);
		advfood.setBounds(269, 141, 123, 38);
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
		advexe.setForeground(new Color(27, 73, 101));
		advexe.setBackground(new Color(202, 233, 255));
		advexe.setFont(new Font("新細明體", Font.PLAIN, 20));
		advexe.setEditable(false);
		advexe.setColumns(10);
		advexe.setBounds(269, 235, 123, 38);
		contentPane.add(advexe);
		
		
		double advexes=(adv*1.55)-adv;
		advexe.setText(String.format("%.1f kcal", advexes));
		
		JLabel lblNewLabel_2 = new JLabel("今日熱量攝取");
		lblNewLabel_2.setForeground(new Color(27, 73, 101));
		lblNewLabel_2.setFont(new Font("微軟正黑體", Font.BOLD, 25));
		lblNewLabel_2.setBounds(418, 141, 155, 38);
		contentPane.add(lblNewLabel_2);
		
		userfood = new JTextField();
		userfood.setForeground(new Color(27, 73, 101));
		userfood.setBackground(new Color(202, 233, 255));
		userfood.setFont(new Font("新細明體", Font.PLAIN, 20));
		userfood.setEditable(false);
		userfood.setColumns(10);
		userfood.setBounds(583, 141, 123, 38);
		contentPane.add(userfood);
		
		Food_logsDaoImpl dao = new Food_logsDaoImpl();
		userfood.setText(String.valueOf(dao.all(user.getId())) + " kcal");

		
		JLabel lblNewLabel_1_1 = new JLabel("今日運動量");
		lblNewLabel_1_1.setForeground(new Color(27, 73, 101));
		lblNewLabel_1_1.setFont(new Font("微軟正黑體", Font.BOLD, 25));
		lblNewLabel_1_1.setBounds(418, 235, 155, 38);
		contentPane.add(lblNewLabel_1_1);
		
		userexe = new JTextField();
		userexe.setForeground(new Color(27, 73, 101));
		userexe.setBackground(new Color(202, 233, 255));
		userexe.setFont(new Font("新細明體", Font.PLAIN, 20));
		userexe.setEditable(false);
		userexe.setColumns(10);
		userexe.setBounds(583, 241, 123, 38);
		contentPane.add(userexe);
		
		foodck = new JTextField();
		foodck.setForeground(new Color(27, 73, 101));
		foodck.setBackground(new Color(202, 233, 255));
		foodck.setFont(new Font("新細明體", Font.PLAIN, 20));
		foodck.setEditable(false);
		foodck.setColumns(10);
		foodck.setBounds(716, 141, 100, 38);
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
		execk.setForeground(new Color(27, 73, 101));
		execk.setBackground(new Color(202, 233, 255));
		execk.setFont(new Font("新細明體", Font.PLAIN, 20));
		execk.setEditable(false);
		execk.setColumns(10);
		execk.setBounds(716, 241, 100, 38);
		contentPane.add(execk);
		
		Exercise_logsDaoImpl dao2 = new Exercise_logsDaoImpl();
		userexe.setText(String.valueOf(dao2.all(user.getId())) + " kcal");
		
		wea_out = new JTextField();
		wea_out.setBackground(new Color(202, 233, 255));
		wea_out.setForeground(new Color(27, 73, 101));
		wea_out.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		wea_out.setEditable(false);
		wea_out.setColumns(10);
		wea_out.setBounds(163, 337, 683, 50);
		contentPane.add(wea_out);
		wea_out.setText(weath(user.getLocation()));
		
		exe_out = new JTextField();
		exe_out.setBackground(new Color(202, 233, 255));
		exe_out.setForeground(new Color(27, 73, 101));
		exe_out.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		exe_out.setEditable(false);
		exe_out.setColumns(10);
		exe_out.setBounds(163, 397, 683, 53);
		contentPane.add(exe_out);
		exe_out.setText(isSuitableForExercise(weath(user.getLocation())));
		
		String exeText = userexe.getText().replace("kcal", "").trim();
		double userExeValue = Double.parseDouble(exeText);

		if (userExeValue >= advexes) {
		    execk.setText("達標");
		} else {
		    execk.setText("尚未達標");
		}
		
		ImageIcon icon = new ImageIcon(getClass().getResource("wea_icon.png"));
		Image img = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		JLabel logo = new JLabel(new ImageIcon(img));
		logo.setBounds(27, 337, 126, 113);
		contentPane.add(logo);
		// 啟動 Timer 每秒更新時間
        startTimer(user.getName(),user.getLocation());
	}
	
	private void startTimer(String name,String loc) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");

        Timer timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LocalDateTime now = LocalDateTime.now();
                welout.setText("歡迎！"+ name + "， 現在時間：" +now.format(formatter)+"， 所在區域："+loc);
            }
        });
        timer.start();
    }
	
	private static String weath(String locationName) {
		String description="無法取得天氣資料";
    try {
        String apiKey = "CWA-9A37C150-A17D-45C8-B985-D3BA8D9A20D5";
//        String locationName = "大安區";
        String elementName = "天氣預報綜合描述";
       
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime next4h = now.plusHours(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        String timeFrom = now.format(formatter);
        String timeTo = next4h.format(formatter);

        String urlStr = "https://opendata.cwa.gov.tw/api/v1/rest/datastore/F-D0047-061"
                + "?Authorization=" + apiKey
                + "&LocationName=" + URLEncoder.encode(locationName, StandardCharsets.UTF_8)
                + "&ElementName=" + URLEncoder.encode(elementName, StandardCharsets.UTF_8)
                + "&timeFrom=" + URLEncoder.encode(timeFrom, StandardCharsets.UTF_8)
                + "&timeTo=" + URLEncoder.encode(timeTo, StandardCharsets.UTF_8)
                + "&format=JSON";

        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) sb.append(line);
        reader.close();

        JsonObject root = JsonParser.parseString(sb.toString()).getAsJsonObject();
        JsonArray locations = root.getAsJsonObject("records").getAsJsonArray("Locations");

        if (locations.size() > 0) {
            JsonObject city = locations.get(0).getAsJsonObject();
            JsonArray locationArray = city.getAsJsonArray("Location");

            for (JsonElement locEl : locationArray) {
                JsonObject locObj = locEl.getAsJsonObject();
                if (!locObj.get("LocationName").getAsString().equals(locationName)) continue;

                JsonArray weatherElements = locObj.getAsJsonArray("WeatherElement");
                for (JsonElement weEl : weatherElements) {
                    JsonObject weObj = weEl.getAsJsonObject();
                    JsonArray times = weObj.getAsJsonArray("Time");

                    for (JsonElement tEl : times) {
                        JsonObject tObj = tEl.getAsJsonObject();
                        String startTime = tObj.get("StartTime").getAsString();
                        String endTime = tObj.get("EndTime").getAsString();
                         description = tObj.getAsJsonArray("ElementValue")
                                .get(0).getAsJsonObject()
                                .get("WeatherDescription").getAsString();
//                        System.out.println(startTime + " ~ " + endTime + " : " + description);
//                        break; // 找到目前時間對應的第一筆就結束
                        System.out.println(startTime + " ~ " + endTime + " : " + description);
                        return description; // 抓完第一筆就結束

                    }
                }
            }
        }

    } catch (Exception e) {
        e.printStackTrace();
        
    }
    
    return description;
}

	private static String isSuitableForExercise(String weatherDesc) {
	    weatherDesc = weatherDesc.toLowerCase();
	    String bad="";
	    try {
	        int tempStart = Integer.parseInt(weatherDesc.replaceAll(".*降雨機率(\\d+).*", "$1"));
	        if (tempStart >= 50) {
	        	bad= bad +  " 不適合戶外運動（下雨機率高於50%）";
	        }
	    } catch (Exception e) {
	        // 無法解析溫度，忽略
	    }

	    // 取得溫度範圍
	    try {
	        int tempStart = Integer.parseInt(weatherDesc.replaceAll(".*溫度攝氏(\\d+).*", "$1"));
	        if (tempStart >= 35) {
	        	bad= bad +  " 不適合劇烈戶外運動（高溫易中暑）";
	        }
	    } catch (Exception e) {
	        // 無法解析溫度，忽略
	    }

	    // 取得相對濕度
	    try {
	        String[] humidityRange = weatherDesc.replaceAll(".*相對濕度(\\d+)至(\\d+)%.*", "$1,$2").split(",");
	        int humidity = Integer.parseInt(humidityRange[1]); // 取上限
	        if (humidity >= 70) {
	        	bad= bad + " 建議室內運動（悶熱）";
	        }
	    } catch (Exception e) {
	        // 無法解析濕度，忽略
	    }

	    if(bad.isEmpty()) {
	        return "適合運動";
	    }
	    return bad;
	}
}
