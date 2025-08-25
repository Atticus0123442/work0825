package controller.mainui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controller.AccountService.combobox;
import dao.impl.UserDaoImpl;
import model.users;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.Timer;

import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import java.awt.Color;

public class userdataUI extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField namein;
    private JTextField weightin;
    private JTextField yearsin;
    private JTextField heightin;
    private JTextField accountin;
    private JTextField timing_out;
    private JTextField passwordin;
    private JButton enterbtn;
    private JLabel lblNewLabel_1_1_1_1_1_2;
    private JTextField loc_in;

    // 🔹 建議清單相關
    private DefaultListModel<String> listModel;
    private JList<String> suggestionList;
    private JScrollPane suggestionScroll;

    // 🔹 台北市行政區清單
    private static final String[] TAIPEI_DISTRICTS = {
        "中正區", "大同區", "中山區", "松山區", "大安區",
        "萬華區", "信義區", "士林區", "北投區",
        "內湖區", "南港區", "文山區"
    };

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UserDaoImpl dao = new UserDaoImpl();
                    users user = dao.getById(1);
                    mainui frame0 = new mainui(user);
                    frame0.setVisible(false);

                    userdataUI frame = new userdataUI(user);
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
    public userdataUI() {
        this(null);
    }

    public userdataUI(users user) {
        super("資料更改", false, false, false, false); // 禁止最大化、最小化、可關閉
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(900, 600);
        setLocation(20, 20);
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null); // 移除標題欄
        getContentPane().setLayout(null);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(190, 232, 233));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("姓名");
        lblNewLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 30));
        lblNewLabel.setBounds(100, 72, 71, 41);
        contentPane.add(lblNewLabel);

        namein = new JTextField();
        namein.setFont(new Font("微軟正黑體", Font.PLAIN, 30));
        namein.setBounds(198, 76, 148, 37);
        contentPane.add(namein);
        namein.setColumns(10);
        if (user != null) namein.setText(user.getName());

        JLabel lblNewLabel_1 = new JLabel("性別");
        lblNewLabel_1.setFont(new Font("微軟正黑體", Font.PLAIN, 30));
        lblNewLabel_1.setBounds(100, 133, 71, 41);
        contentPane.add(lblNewLabel_1);

        JComboBox<combobox> genderin = new JComboBox<>();
        genderin.setFont(new Font("微軟正黑體", Font.PLAIN, 30));
        genderin.addItem(new combobox("男", "M"));
        genderin.addItem(new combobox("女", "F"));
        genderin.setBounds(198, 133, 59, 41);
        contentPane.add(genderin);
        if (user != null) {
            if (user.getGender().equals("M")) {
                genderin.setSelectedIndex(0);
            } else {
                genderin.setSelectedIndex(1);
            }
        }

        JLabel lblNewLabel_1_1 = new JLabel("體重");
        lblNewLabel_1_1.setFont(new Font("微軟正黑體", Font.PLAIN, 30));
        lblNewLabel_1_1.setBounds(100, 266, 71, 41);
        contentPane.add(lblNewLabel_1_1);

        weightin = new JTextField();
        weightin.setFont(new Font("微軟正黑體", Font.PLAIN, 30));
        weightin.setColumns(10);
        weightin.setBounds(198, 268, 148, 37);
        contentPane.add(weightin);
        if (user != null) weightin.setText(String.valueOf(user.getWeight()));

        JLabel lblNewLabel_1_1_1 = new JLabel("年紀");
        lblNewLabel_1_1_1.setFont(new Font("微軟正黑體", Font.PLAIN, 30));
        lblNewLabel_1_1_1.setBounds(100, 203, 71, 41);
        contentPane.add(lblNewLabel_1_1_1);

        yearsin = new JTextField();
        yearsin.setFont(new Font("微軟正黑體", Font.PLAIN, 30));
        yearsin.setColumns(10);
        yearsin.setBounds(198, 203, 148, 41);
        contentPane.add(yearsin);
        if (user != null) yearsin.setText(String.valueOf(user.getAge()));

        JLabel lblNewLabel_1_1_1_1 = new JLabel("身高");
        lblNewLabel_1_1_1_1.setFont(new Font("微軟正黑體", Font.PLAIN, 30));
        lblNewLabel_1_1_1_1.setBounds(100, 324, 71, 41);
        contentPane.add(lblNewLabel_1_1_1_1);

        heightin = new JTextField();
        heightin.setFont(new Font("微軟正黑體", Font.PLAIN, 30));
        heightin.setColumns(10);
        heightin.setBounds(198, 324, 148, 41);
        contentPane.add(heightin);
        if (user != null) heightin.setText(String.valueOf(user.getHeight()));

        JLabel lblNewLabel_1_1_1_1_1 = new JLabel("帳號名稱");
        lblNewLabel_1_1_1_1_1.setFont(new Font("微軟正黑體", Font.PLAIN, 30));
        lblNewLabel_1_1_1_1_1.setBounds(436, 101, 132, 41);
        contentPane.add(lblNewLabel_1_1_1_1_1);

        accountin = new JTextField();
        accountin.setEditable(false);
        accountin.setFont(new Font("新細明體", Font.PLAIN, 30));
        accountin.setColumns(10);
        accountin.setBounds(579, 100, 148, 41);
        contentPane.add(accountin);
        if (user != null) accountin.setText(user.getUsername());

        JLabel lblNewLabel_1_1_1_2 = new JLabel("時間");
        lblNewLabel_1_1_1_2.setFont(new Font("新細明體", Font.PLAIN, 30));
        lblNewLabel_1_1_1_2.setBounds(425, 11, 71, 41);
        contentPane.add(lblNewLabel_1_1_1_2);

        timing_out = new JTextField();
        timing_out.setEditable(false);
        timing_out.setFont(new Font("新細明體", Font.PLAIN, 30));
        timing_out.setColumns(10);
        timing_out.setBounds(514, 10, 346, 41);
        contentPane.add(timing_out);

        passwordin = new JTextField();
        passwordin.setFont(new Font("新細明體", Font.PLAIN, 30));
        passwordin.setColumns(10);
        passwordin.setBounds(571, 170, 148, 41);
        contentPane.add(passwordin);
        if (user != null) passwordin.setText(user.getPassword());

        JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("密碼設定");
        lblNewLabel_1_1_1_1_1_1.setFont(new Font("微軟正黑體", Font.PLAIN, 30));
        lblNewLabel_1_1_1_1_1_1.setBounds(436, 170, 132, 41);
        contentPane.add(lblNewLabel_1_1_1_1_1_1);

        enterbtn = new JButton("修改資料");
        enterbtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                combobox selectedGender = (combobox) genderin.getSelectedItem();
                String genderValue = selectedGender.getValue(); // 會是 "M" 或 "F"
                if (namein.getText().trim().isEmpty() ||
                    genderValue.isEmpty() ||
                    yearsin.getText().trim().isEmpty() ||
                    weightin.getText().trim().isEmpty() ||
                    heightin.getText().trim().isEmpty() ||
                    accountin.getText().trim().isEmpty() ||
                    passwordin.getText().trim().isEmpty() ||
                    loc_in.getText().trim().isEmpty()) 
                {
                    JOptionPane.showMessageDialog(null, "請勿空白!" );
                } else {
                    UserDaoImpl dao = new UserDaoImpl();
                    users newUser = new users();
                    newUser.setId(user.getId());
                    newUser.setName(namein.getText().trim());
                    newUser.setGender(genderValue);
                    newUser.setAge(Integer.parseInt(yearsin.getText().trim()));
                    newUser.setWeight(Double.parseDouble(weightin.getText().trim()));
                    newUser.setHeight(Double.parseDouble(heightin.getText().trim()));
                    newUser.setUsername(accountin.getText().trim());
                    newUser.setPassword(passwordin.getText().trim());
                    newUser.setLocation(loc_in.getText().trim()); // 🔹補上地區
                    dao.update(newUser);
                    JOptionPane.showMessageDialog(null, "修改成功！");
                }
            }
        });
        enterbtn.setFont(new Font("新細明體", Font.PLAIN, 31));
        enterbtn.setBounds(543, 365, 195, 63);
        contentPane.add(enterbtn);

        lblNewLabel_1_1_1_1_1_2 = new JLabel("地區");
        lblNewLabel_1_1_1_1_1_2.setFont(new Font("微軟正黑體", Font.PLAIN, 30));
        lblNewLabel_1_1_1_1_1_2.setBounds(100, 387, 65, 41);
        contentPane.add(lblNewLabel_1_1_1_1_1_2);

        loc_in = new JTextField();
        loc_in.setFont(new Font("微軟正黑體", Font.PLAIN, 30));
        loc_in.setColumns(10);
        loc_in.setBounds(198, 389, 148, 41);
        contentPane.add(loc_in);
        if (user != null) loc_in.setText(user.getLocation());

        // === 建議功能 ===
        listModel = new DefaultListModel<>();
        suggestionList = new JList<>(listModel);
        suggestionList.setVisibleRowCount(5);
        suggestionList.setBackground(new Color(222, 222, 222));
        suggestionList.setFont(new Font("新細明體", Font.PLAIN, 20));
        suggestionScroll = new JScrollPane(suggestionList);
        suggestionScroll.setVisible(false);
        suggestionScroll.setBounds(loc_in.getX(), loc_in.getY() + loc_in.getHeight(), loc_in.getWidth(), 100);
        contentPane.add(suggestionScroll);

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

        loc_in.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { updateSuggestions(); }
            public void removeUpdate(DocumentEvent e) { updateSuggestions(); }
            public void changedUpdate(DocumentEvent e) { updateSuggestions(); }
        });

        // 啟動 Timer 每秒更新時間
        startTimer();
    }

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

    private void updateSuggestions() {
        String keyword = loc_in.getText().trim();
        if (keyword.isEmpty()) {
            suggestionScroll.setVisible(false);
            return;
        }
        listModel.clear();
        boolean hasMatches = false;
        for (String district : TAIPEI_DISTRICTS) {
            if (district.startsWith(keyword)) { // 可以改成 contains 更友善
                listModel.addElement(district);
                hasMatches = true;
            }
        }
        if (!hasMatches) {
            suggestionScroll.setVisible(false);
            return;
        }
        suggestionScroll.setVisible(true);
    }
}
