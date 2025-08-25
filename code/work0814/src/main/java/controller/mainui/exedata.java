package controller.mainui;

import org.jfree.data.time.Hour;
import org.jfree.data.time.Millisecond;
import java.util.Date;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.*;

import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.XYPlot;

import java.awt.Font;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import controller.mainui.fooddata.ButtonEditor;
import controller.mainui.fooddata.JButtonRenderer;
import dao.impl.Exercise_logsDaoImpl;
import dao.impl.ExercisesDaoImpl;
import dao.impl.Food_logsDaoImpl;
import dao.impl.FoodsDaoImpl;
import dao.impl.UserDaoImpl;
import model.exercise_logs;
import model.exercises;
import model.food_logs;
import model.foods;
import model.users;

import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;

public class exedata extends JInternalFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField exename;
    private JTextField execal;
    private JTextField muchin;
    private JList<String> suggestionList;
    private JScrollPane suggestionScroll;
    private DefaultListModel<String> listModel;

    private JTable mytable;
    private DefaultTableModel model;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UserDaoImpl dao = new UserDaoImpl();
                users user = dao.getById(1);
                exedata frame = new exedata(user);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public exedata() {
        this(null);
    }

    public exedata(users user) {
    	super("運動紀錄", false, false, false, false); // 禁止最大化、最小化、可關閉
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setSize(900, 600);
	    setLocation(20, 20);
	    ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null); // 移除標題欄
	    getContentPane().setLayout(null);
	    
	    
	    contentPane = new JPanel();
	    contentPane.setBackground(new Color(202, 233, 255));
	    contentPane.setLayout(null);
	    setContentPane(contentPane);

        JButton addbtn = new JButton("新增");
        addbtn.setBackground(new Color(190, 233, 232));
        addbtn.setForeground(new Color(27, 73, 101));
        addbtn.setFont(new Font("微軟正黑體", Font.BOLD, 15));
        addbtn.setBounds(558, 29, 81, 41);
        contentPane.add(addbtn);

        JLabel lblNewLabel = new JLabel("運動名稱");
        lblNewLabel.setForeground(new Color(27, 73, 101));
        lblNewLabel.setFont(new Font("微軟正黑體", Font.BOLD, 15));
        lblNewLabel.setBounds(22, 28, 69, 43);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("熱量");
        lblNewLabel_1.setForeground(new Color(27, 73, 101));
        lblNewLabel_1.setFont(new Font("微軟正黑體", Font.BOLD, 15));
        lblNewLabel_1.setBounds(203, 29, 30, 43);
        contentPane.add(lblNewLabel_1);

        exename = new JTextField();
        exename.setForeground(new Color(27, 73, 101));
        exename.setFont(new Font("微軟正黑體", Font.BOLD, 15));
        exename.setBounds(97, 34, 96, 31);
        contentPane.add(exename);
        exename.setColumns(10);

        execal = new JTextField();
        execal.setForeground(new Color(27, 73, 101));
        execal.setFont(new Font("微軟正黑體", Font.BOLD, 15));
        execal.setColumns(10);
        execal.setBounds(253, 34, 96, 33);
        contentPane.add(execal);

        JLabel lblNewLabel_1_1 = new JLabel("分鐘數");
        lblNewLabel_1_1.setForeground(new Color(27, 73, 101));
        lblNewLabel_1_1.setFont(new Font("微軟正黑體", Font.BOLD, 15));
        lblNewLabel_1_1.setBounds(359, 29, 50, 43);
        contentPane.add(lblNewLabel_1_1);

        muchin = new JTextField();
        muchin.setForeground(new Color(27, 73, 101));
        muchin.setFont(new Font("微軟正黑體", Font.BOLD, 15));
        muchin.setColumns(10);
        muchin.setBounds(419, 34, 96, 33);
        contentPane.add(muchin);

        listModel = new DefaultListModel<>();
        suggestionList = new JList<>(listModel);
        suggestionList.setVisibleRowCount(5);
        suggestionList.setBackground(new Color(222, 222, 222));
        suggestionList.setFont(new Font("新細明體", Font.PLAIN, 14));
        suggestionScroll = new JScrollPane(suggestionList);
        suggestionScroll.setVisible(false);
        suggestionScroll.setBounds(exename.getX(), exename.getY() + exename.getHeight(), exename.getWidth(), 100);
        contentPane.add(suggestionScroll);

        String[] columns = {"名稱", "消耗熱量", "分鐘數", "紀錄時間", "更新", "刪除", "id"};
        Exercise_logsDaoImpl dao = new Exercise_logsDaoImpl();
        Object[][] allData = dao.getAllAsArray(user != null ? String.valueOf(user.getId()) : null);

        model = new DefaultTableModel(allData, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2 || column == 4 || column == 5;
            }
        };
        mytable = new JTable(model);
        mytable.setRowHeight(30);

        mytable.getColumn("更新").setCellRenderer(new JButtonRenderer());
        mytable.getColumn("刪除").setCellRenderer(new JButtonRenderer());
        mytable.getColumn("更新").setCellEditor(new ButtonEditor(true));
        mytable.getColumn("刪除").setCellEditor(new ButtonEditor(false));

        JScrollPane scrollPane = new JScrollPane(mytable);
        scrollPane.setBounds(22, 95, 829, 388);
        contentPane.add(scrollPane);

        JButton chartbtn = new JButton("趨勢圖");
        chartbtn.setBackground(new Color(190, 233, 232));
        chartbtn.setForeground(new Color(27, 73, 101));
        chartbtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showTrendChart(user);
            }
        });
        chartbtn.setFont(new Font("微軟正黑體", Font.BOLD, 15));
        chartbtn.setBounds(668, 29, 81, 41);
        contentPane.add(chartbtn);

        JButton optbtn = new JButton("匯出");
        optbtn.setBackground(new Color(190, 233, 232));
        optbtn.setForeground(new Color(27, 73, 101));
        optbtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                exportTableToExcel(mytable);
            }
        });
        optbtn.setFont(new Font("微軟正黑體", Font.BOLD, 15));
        optbtn.setBounds(777, 29, 74, 41);
        contentPane.add(optbtn);

        TableColumn hiddenIdCol = mytable.getColumnModel().getColumn(6);
        hiddenIdCol.setMinWidth(0);
        hiddenIdCol.setMaxWidth(0);
        hiddenIdCol.setPreferredWidth(0);

        addbtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (exename.getText().trim().isEmpty() || execal.getText().trim().isEmpty() || muchin.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "不可以空白!");
                } else {
                    exercises exe = new exercises();
                    model.exercise_logs exelog = new model.exercise_logs();
                    Exercise_logsDaoImpl dao_exelog = new Exercise_logsDaoImpl();
                    ExercisesDaoImpl dao_exe = new ExercisesDaoImpl();

                    exe = dao_exe.searchbyname(exename.getText().trim());
                    if (exe == null) {
                        exe = new exercises();
                        exe.setName(exename.getText().trim());
                        exe.setCalories_burned_per_minute(Integer.parseInt(execal.getText().trim()));
                        dao_exe.add(exe);
                        exe = dao_exe.searchbyname(exename.getText().trim());
                    }

                    if (user != null) {
                        exelog.setUserId(user.getId());
                    }
                    exe.setCalories_burned_per_minute(Integer.parseInt(execal.getText().trim()));
                    exelog.setExercisedAt(LocalDateTime.now());
                    exelog.setExerciseId(exe.getId());
                    exelog.setMinutes(Integer.parseInt(muchin.getText().trim()));

                    dao_exe.update(exe);
                    dao_exelog.add(exelog);
                    JOptionPane.showMessageDialog(null, "新增成功");

                    refreshTable(user);
                }
            }
        });

        suggestionList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String selected = suggestionList.getSelectedValue();
                if (selected != null) {
                    exename.setText(selected);

                    ExercisesDaoImpl dao = new ExercisesDaoImpl();
                    exercises[] result = dao.searchByName(selected);
                    if (result != null && result.length > 0) {
                        execal.setText(String.valueOf(result[0].getCalories_burned_per_minute()));
                    }
                    suggestionScroll.setVisible(false);
                    exename.requestFocusInWindow();
                }
            }
        });

        exename.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { updateSuggestions(); }
            public void removeUpdate(DocumentEvent e) { updateSuggestions(); }
            public void changedUpdate(DocumentEvent e) { updateSuggestions(); }
        });
    }

    private void refreshTable(users user) {
        Exercise_logsDaoImpl dao = new Exercise_logsDaoImpl();
        Object[][] newData = dao.getAllAsArray(user != null ? String.valueOf(user.getId()) : null);
        model.setDataVector(newData, new String[]{"名稱", "消耗熱量", "分鐘數", "紀錄時間", "更新", "刪除", "id"});
        mytable.getColumn("更新").setCellRenderer(new JButtonRenderer());
        mytable.getColumn("刪除").setCellRenderer(new JButtonRenderer());
        mytable.getColumn("更新").setCellEditor(new ButtonEditor(true));
        mytable.getColumn("刪除").setCellEditor(new ButtonEditor(false));

        TableColumn hiddenIdCol = mytable.getColumnModel().getColumn(6);
        hiddenIdCol.setMinWidth(0);
        hiddenIdCol.setMaxWidth(0);
        hiddenIdCol.setPreferredWidth(0);
    }

    private void updateSuggestions() {
        String keyword = exename.getText().trim();
        if (keyword.isEmpty()) {
            suggestionScroll.setVisible(false);
            return;
        }

        ExercisesDaoImpl dao = new ExercisesDaoImpl();
        exercises[] matched = dao.searchByName(keyword);
        if (matched.length == 0) {
            suggestionScroll.setVisible(false);
            return;
        }

        listModel.clear();
        for (exercises e : matched) {
            listModel.addElement(e.getName());
        }

        suggestionScroll.setVisible(true);
    }

    class JButtonRenderer extends JButton implements TableCellRenderer {
        public JButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText(value == null ? "" : value.toString());
            return this;
        }
    }

class ButtonEditor extends DefaultCellEditor {
    private JButton button;
    private boolean isUpdate;
    private boolean clicked;
    private int row;

    public ButtonEditor(boolean isUpdate) {
        super(new JCheckBox());
        this.isUpdate = isUpdate;
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(e -> {
            // 在按鈕點擊時處理邏輯，而不是在getCellEditorValue中
            handleButtonClick();
            fireEditingStopped();
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        button.setText(value == null ? "" : value.toString());
        this.clicked = true;
        this.row = row;
        return button;
    }
    
    private void handleButtonClick() {
        if (!clicked) return;
        
        Object idObj = model.getValueAt(row, 6);
        int id = (Integer) idObj;
        
        // 停止數量欄位的編輯（如果正在編輯中）
        if (mytable.getEditingRow() == row && mytable.getEditingColumn() == 2) {
            mytable.getCellEditor(row, 2).stopCellEditing();
        }
        
        // 獲取數量值
        Object muchObj = model.getValueAt(row, 2);
        int much;
        try {
            if (muchObj instanceof Integer) {
                much = (Integer) muchObj;
            } else if (muchObj instanceof String) {
                much = Integer.parseInt((String) muchObj);
            } else {
                much = Integer.parseInt(muchObj.toString());
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "必須是有效的數字！");
            return;
        }

        String name = (String) model.getValueAt(row, 0);
        if (isUpdate) {
            try {
            		model.setValueAt(much, row, 2);
                Exercise_logsDaoImpl dao = new Exercise_logsDaoImpl();
                exercise_logs exelog = dao.getById(id);
                if (exelog != null) {
                    exelog.setMinutes(much);
                    dao.update(exelog);
                    // 更新模型中的值
                    
                    
                    JOptionPane.showMessageDialog(null, "更新成功: " + name);
                } else {
                    JOptionPane.showMessageDialog(null, "找不到要更新的紀錄！");
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "更新失敗: " + e.getMessage());
            }
        } else {
            int confirm = JOptionPane.showConfirmDialog(null, "確定刪除 " + name + "？");
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                	Exercise_logsDaoImpl dao = new Exercise_logsDaoImpl();
                    dao.deletebyid(id);
                    model.removeRow(row);
                    JOptionPane.showMessageDialog(null, "刪除成功");
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "刪除失敗: " + e.getMessage());
                }
            }
        }
    }

    @Override
    public Object getCellEditorValue() {
        // 簡單返回按鈕文字，不在這裡處理業務邏輯
        return isUpdate ? "更新" : "刪除";
    }

    @Override
    public boolean stopCellEditing() {
        clicked = false;
        return super.stopCellEditing();
    }
}
    void exportTableToExcel(JTable table) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("運動紀錄");

        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < 3; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(table.getColumnName(i));
        }

        for (int row = 0; row < table.getRowCount(); row++) {
            Row excelRow = sheet.createRow(row + 1);
            for (int col = 0; col < 3; col++) {
                Object value = table.getValueAt(row, col);
                excelRow.createCell(col).setCellValue(value != null ? value.toString() : "");
            }
        }

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String defaultFileName = "ExeLogs_" + timestamp + ".xlsx";

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("選擇儲存位置和檔名");
        fileChooser.setSelectedFile(new File(defaultFileName));
        fileChooser.setFileFilter(new FileNameExtensionFilter("Excel Files", "xlsx"));

        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = new File(fileChooser.getSelectedFile().getAbsolutePath());
            if (!fileToSave.getName().toLowerCase().endsWith(".xlsx")) {
                fileToSave = new File(fileToSave.getParentFile(), fileToSave.getName() + ".xlsx");
            }
            try (FileOutputStream fos = new FileOutputStream(fileToSave)) {
                workbook.write(fos);
                workbook.close();
                JOptionPane.showMessageDialog(null, "匯出成功：" + fileToSave.getAbsolutePath());
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(fileToSave);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "匯出失敗：" + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "已取消匯出");
        }
    }

    void showTrendChart(users user) {
        Exercise_logsDaoImpl dao = new Exercise_logsDaoImpl();
        Object[][] data = dao.getAllAsArray(user != null ? String.valueOf(user.getId()) : null);

        TimeSeries series = new TimeSeries("熱量消耗趨勢");

        for (Object[] row : data) {
            try {
                double calories = Double.parseDouble(row[1].toString());
                Object timeObj = row[3];
                LocalDateTime dt = null;
                if (timeObj instanceof LocalDateTime) {
                    dt = (LocalDateTime) timeObj;
                } else if (timeObj instanceof java.sql.Timestamp) {
                    dt = ((java.sql.Timestamp) timeObj).toLocalDateTime();
                } else if (timeObj instanceof String) {
                    dt = LocalDateTime.parse((String) timeObj, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                } else {
                    continue;
                }

                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.YEAR, dt.getYear());
                cal.set(Calendar.MONTH, dt.getMonthValue() - 1);
                cal.set(Calendar.DAY_OF_MONTH, dt.getDayOfMonth());
                cal.set(Calendar.HOUR_OF_DAY, dt.getHour());
                cal.set(Calendar.MINUTE, dt.getMinute());
                cal.set(Calendar.SECOND, dt.getSecond());
                cal.set(Calendar.MILLISECOND, 0);

                Date date = cal.getTime();
                Millisecond ms = new Millisecond(date);
                series.addOrUpdate(ms, calories);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(series);

        StandardChartTheme chartTheme = new StandardChartTheme("JFree");
        Font font = new Font("SansSerif", Font.PLAIN, 14);
        chartTheme.setExtraLargeFont(font);
        chartTheme.setLargeFont(font);
        chartTheme.setRegularFont(font);
        ChartFactory.setChartTheme(chartTheme);

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
            "熱量消耗趨勢圖",
            "時間",
            "熱量(大卡)",
            dataset,
            false,
            false,
            false
        );

        XYPlot plot = chart.getXYPlot();
        DateAxis xAxis = (DateAxis) plot.getDomainAxis();
        xAxis.setDateFormatOverride(new SimpleDateFormat("MM/dd HH:mm:ss"));
        xAxis.setAutoRange(true);
        xAxis.setLowerMargin(0.02);
        xAxis.setUpperMargin(0.02);

        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setAutoRangeIncludesZero(true);
        yAxis.setAutoTickUnitSelection(true);

        ChartPanel chartPanel = new ChartPanel(chart);
        JFrame chartFrame = new JFrame("熱量消耗趨勢圖");
        chartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        chartFrame.setSize(800, 600);
        chartFrame.setLocationRelativeTo(null);
        chartFrame.setContentPane(chartPanel);
        chartFrame.setVisible(true);
    }
}
