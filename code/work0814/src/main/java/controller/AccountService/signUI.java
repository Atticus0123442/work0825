package controller.AccountService;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import dao.impl.UserDaoImpl;
import model.users;

public class signUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField namein;
    private JTextField weightin;
    private JTextField yearsin;
    private JTextField heightin;
    private JTextField accountin;
    private JTextField timing_out;
    private JButton enterbtn;
    private JButton cancelbtn;
    private JTextField passwordcheck;
    private JPasswordField passwordin;
    private JLabel password_st;
    private JTextField loc_in;
    
    // 參照成功範例的建議功能元件
    private JList<String> suggestionList;
    private JScrollPane suggestionScroll;
    private DefaultListModel<String> listModel;
    
    // 建立台北市行政區的陣列
    private final String[] TAIPEI_DISTRICTS = {
    	    "中正區", "大同區", "中山區", "松山區", "大安區", "萬華區",
    	    "信義區", "士林區", "北投區", "內湖區", "南港區", "文山區"
    };


    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AccountServiceUI frame0 = new AccountServiceUI();
                    frame0.setVisible(false);
                    signUI frame = new signUI(frame0);
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
    public signUI() {
        this(null);
    }

    public signUI(AccountServiceUI window) {
        signUI self = this;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 838, 552);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(190, 233, 232));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("姓名");
        lblNewLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 25));
        lblNewLabel.setBounds(76, 59, 71, 41);
        contentPane.add(lblNewLabel);
        
        namein = new JTextField();
        namein.setFont(new Font("新細明體", Font.PLAIN, 25));
        namein.setBounds(137, 59, 148, 41);
        contentPane.add(namein);
        namein.setColumns(10);
        
        JLabel lblNewLabel_1 = new JLabel("性別");
        lblNewLabel_1.setFont(new Font("微軟正黑體", Font.PLAIN, 25));
        lblNewLabel_1.setBounds(76, 126, 59, 41);
        contentPane.add(lblNewLabel_1);
        
        JComboBox genderin = new JComboBox();
        genderin.setFont(new Font("新細明體", Font.PLAIN, 25));
        genderin.addItem(new combobox("男", "M"));
        genderin.addItem(new combobox("女", "F"));
        genderin.setBounds(149, 120, 59, 52);
        contentPane.add(genderin);
        
        JLabel lblNewLabel_1_1 = new JLabel("體重");
        lblNewLabel_1_1.setFont(new Font("微軟正黑體", Font.PLAIN, 25));
        lblNewLabel_1_1.setBounds(76, 317, 71, 41);
        contentPane.add(lblNewLabel_1_1);
        
        weightin = new JTextField();
        weightin.setFont(new Font("新細明體", Font.PLAIN, 25));
        weightin.setColumns(10);
        weightin.setBounds(146, 317, 148, 41);
        contentPane.add(weightin);
        
        JLabel lblNewLabel_1_1_1 = new JLabel("年紀");
        lblNewLabel_1_1_1.setFont(new Font("微軟正黑體", Font.PLAIN, 25));
        lblNewLabel_1_1_1.setBounds(76, 193, 71, 41);
        contentPane.add(lblNewLabel_1_1_1);
        
        yearsin = new JTextField();
        yearsin.setFont(new Font("新細明體", Font.PLAIN, 25));
        yearsin.setColumns(10);
        yearsin.setBounds(146, 193, 148, 41);
        contentPane.add(yearsin);
        
        JLabel lblNewLabel_1_1_1_1 = new JLabel("身高");
        lblNewLabel_1_1_1_1.setFont(new Font("微軟正黑體", Font.PLAIN, 25));
        lblNewLabel_1_1_1_1.setBounds(76, 257, 59, 41);
        contentPane.add(lblNewLabel_1_1_1_1);
        
        heightin = new JTextField();
        heightin.setFont(new Font("新細明體", Font.PLAIN, 25));
        heightin.setColumns(10);
        heightin.setBounds(146, 257, 148, 41);
        contentPane.add(heightin);
        
        JLabel lblNewLabel_1_1_1_1_1 = new JLabel("帳號名稱");
        lblNewLabel_1_1_1_1_1.setFont(new Font("微軟正黑體", Font.PLAIN, 25));
        lblNewLabel_1_1_1_1_1.setBounds(418, 59, 106, 41);
        contentPane.add(lblNewLabel_1_1_1_1_1);
        
        accountin = new JTextField();
        accountin.setFont(new Font("新細明體", Font.PLAIN, 25));
        accountin.setColumns(10);
        accountin.setBounds(534, 59, 148, 41);
        contentPane.add(accountin);
        
        JLabel lblNewLabel_1_1_1_2 = new JLabel("時間");
        lblNewLabel_1_1_1_2.setFont(new Font("新細明體", Font.BOLD, 25));
        lblNewLabel_1_1_1_2.setBounds(358, 16, 59, 30);
        contentPane.add(lblNewLabel_1_1_1_2);
        
        timing_out = new JTextField();
        timing_out.setEditable(false);
        timing_out.setFont(new Font("新細明體", Font.PLAIN, 25));
        timing_out.setColumns(10);
        timing_out.setBounds(427, 10, 366, 36);
        contentPane.add(timing_out);
        
        passwordin = new JPasswordField();
        passwordin.setFont(new Font("新細明體", Font.PLAIN, 25));
        passwordin.setColumns(10);
        passwordin.setBounds(534, 138, 148, 41);
        contentPane.add(passwordin);
        
        JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("密碼設定");
        lblNewLabel_1_1_1_1_1_1.setFont(new Font("微軟正黑體", Font.PLAIN, 25));
        lblNewLabel_1_1_1_1_1_1.setBounds(418, 138, 100, 41);
        contentPane.add(lblNewLabel_1_1_1_1_1_1);
        
        enterbtn = new JButton("送出資料");
        enterbtn.setFont(new Font("新細明體", Font.PLAIN, 25));
        enterbtn.setBounds(478, 439, 154, 52);
        contentPane.add(enterbtn);
        
        cancelbtn = new JButton("取消");
        cancelbtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                self.setVisible(false);
                window.setVisible(true);
            }
        });
        cancelbtn.setFont(new Font("新細明體", Font.PLAIN, 25));
        cancelbtn.setBounds(645, 439, 148, 52);
        contentPane.add(cancelbtn);
        
        JLabel lblNewLabel_1_1_1_1_1_1_1 = new JLabel("密碼確認");
        lblNewLabel_1_1_1_1_1_1_1.setFont(new Font("微軟正黑體", Font.PLAIN, 25));
        lblNewLabel_1_1_1_1_1_1_1.setBounds(418, 205, 100, 41);
        contentPane.add(lblNewLabel_1_1_1_1_1_1_1);
        
        passwordcheck = new JPasswordField();
        passwordcheck.setFont(new Font("新細明體", Font.PLAIN, 25));
        passwordcheck.setColumns(10);
        passwordcheck.setBounds(534, 205, 148, 41);
        contentPane.add(passwordcheck);
        
        password_st = new JLabel("");
        password_st.setFont(new Font("微軟正黑體", Font.PLAIN, 15));
        password_st.setBounds(703, 138, 76, 41);
        contentPane.add(password_st);
        
        JLabel lblNewLabel_1_2 = new JLabel("註冊類別");
        lblNewLabel_1_2.setFont(new Font("微軟正黑體", Font.PLAIN, 25));
        lblNewLabel_1_2.setBounds(418, 278, 106, 41);
        contentPane.add(lblNewLabel_1_2);
        
        JComboBox classin = new JComboBox();
        classin.setFont(new Font("新細明體", Font.PLAIN, 25));
        classin.setBounds(540, 272, 113, 52);
        classin.setFont(new Font("新細明體", Font.PLAIN, 25));
        classin.addItem(new combobox("學員", "T"));
        classin.addItem(new combobox("教練", "S"));
        contentPane.add(classin);
        
        JLabel lblNewLabel_1_1_2 = new JLabel("區域");
        lblNewLabel_1_1_2.setFont(new Font("微軟正黑體", Font.PLAIN, 25));
        lblNewLabel_1_1_2.setBounds(76, 389, 71, 41);
        contentPane.add(lblNewLabel_1_1_2);
        
        loc_in = new JTextField();
        loc_in.setFont(new Font("新細明體", Font.PLAIN, 25));
        loc_in.setColumns(10);
        loc_in.setBounds(146, 389, 148, 41);
        contentPane.add(loc_in);
        
        // === 參照成功範例初始化建議功能 ===
        listModel = new DefaultListModel<>();
        suggestionList = new JList<>(listModel);
        suggestionList.setVisibleRowCount(5);
        suggestionList.setBackground(new Color(222, 222, 222));
        suggestionList.setFont(new Font("新細明體", Font.PLAIN, 20));
        suggestionScroll = new JScrollPane(suggestionList);
        suggestionScroll.setVisible(false);
        // 設定建議清單的位置：在 loc_in 下方
        suggestionScroll.setBounds(loc_in.getX(), loc_in.getY() + loc_in.getHeight(), loc_in.getWidth(), 100);
        contentPane.add(suggestionScroll);

        // === 參照成功範例的滑鼠點擊事件 ===
        suggestionList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String selected = suggestionList.getSelectedValue();
                if (selected != null) {
                    loc_in.setText(selected);
                    suggestionScroll.setVisible(false);
                    loc_in.requestFocusInWindow();
                }
            }
        });

        // === 參照成功範例的文件監聽器 ===
        loc_in.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { 
                updateSuggestions(); 
            }
            public void removeUpdate(DocumentEvent e) { 
                updateSuggestions(); 
            }
            public void changedUpdate(DocumentEvent e) { 
                updateSuggestions(); 
            }
        });

        // === 送出按鈕事件處理 ===
        enterbtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                combobox selectedGender = (combobox) genderin.getSelectedItem();
                String genderValue = selectedGender.getValue();
                combobox selecteclass = (combobox) classin.getSelectedItem();
                String classValue = selecteclass.getValue();
                
                if(namein.getText().trim().isEmpty() ||
                   genderValue.isEmpty() ||
                   classValue.isEmpty() ||
                   yearsin.getText().trim().isEmpty() ||
                   weightin.getText().trim().isEmpty() ||
                   heightin.getText().trim().isEmpty() ||
                   accountin.getText().trim().isEmpty() ||
                   passwordin.getText().trim().isEmpty() || 
                   loc_in.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "請勿空白!");
                } else {
                    UserDaoImpl dao = new UserDaoImpl();

                    // 如果帳號已存在，先刪除
                    if (dao.login(accountin.getText().trim())) {
                        JOptionPane.showMessageDialog(null, "帳號名稱已存在!");
                    } else {
                        // 建立新使用者資料
                        users newUser = new users();
                        newUser.setName(namein.getText().trim());
                        newUser.setGender(genderValue);
                        newUser.setAge(Integer.parseInt(yearsin.getText().trim()));
                        newUser.setWeight(Double.parseDouble(weightin.getText().trim()));
                        newUser.setHeight(Double.parseDouble(heightin.getText().trim()));
                        newUser.setUsername(accountin.getText().trim());
                        newUser.setPassword(passwordin.getText().trim());
                        newUser.setCreatAt(LocalDateTime.now());
                        newUser.setClasses(classValue);
                        newUser.setLocation(loc_in.getText().trim());
                        
                        dao.add(newUser);
                        JOptionPane.showMessageDialog(null, "註冊成功！");
                        self.setVisible(false);
                        window.setVisible(true);
                    }
                }
            }
        });
        
        // 啟動 Timer 每秒更新時間
        startTimer();
        
        // 密碼強度檢測
        passwordin.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                updateStrength();
            }

            public void removeUpdate(DocumentEvent e) {
                updateStrength();
            }

            public void changedUpdate(DocumentEvent e) {
                updateStrength();
            }
        });
    }
    
    // === 參照成功範例的建議更新方法 ===
    private void updateSuggestions() {
        String keyword = loc_in.getText().trim();
        
        // 如果輸入為空，隱藏建議清單
        if (keyword.isEmpty()) {
            suggestionScroll.setVisible(false);
            return;
        }

        // 清除現有的建議
        listModel.clear();
        
        // 搜尋匹配的行政區
        boolean hasMatches = false;
        for (String district : TAIPEI_DISTRICTS) {
            if (district.startsWith(keyword)) {
                listModel.addElement(district);
                hasMatches = true;
            }
        }
        
        // 如果沒有匹配項目，隱藏建議清單
        if (!hasMatches) {
            suggestionScroll.setVisible(false);
            return;
        }

        // 顯示建議清單
        suggestionScroll.setVisible(true);
    }
    
    // === 時間更新方法 ===
    private void startTimer() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");

        Timer timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LocalDateTime now = LocalDateTime.now();
                timing_out.setText(now.format(formatter));
            }
        });
        timer.start();
    }
    
    // === 密碼強度檢測方法 ===
    private void updateStrength() {
        String password = new String(passwordin.getPassword());
        String strength = getPasswordStrength(password);
        password_st.setText("強度：" + strength);

        // 根據強度改顏色
        switch (strength) {
            case "弱":
                password_st.setForeground(Color.RED);
                break;
            case "中":
                password_st.setForeground(Color.ORANGE);
                break;
            case "強":
                password_st.setForeground(Color.GREEN);
                break;
        }
    }

    // === 密碼強度規則 ===
    private String getPasswordStrength(String password) {
        if (password.length() < 6) {
            return "弱";
        }

        boolean hasLetter = password.matches(".*[a-zA-Z].*");
        boolean hasDigit = password.matches(".*\\d.*");
        boolean hasSpecial = password.matches(".*[^a-zA-Z0-9].*");

        if (hasLetter && hasDigit && hasSpecial && password.length() >= 8) {
            return "強";
        } else if ((hasLetter && hasDigit) || (hasLetter && hasSpecial) || (hasDigit && hasSpecial)) {
            return "中";
        } else {
            return "弱";
        }
    }
}