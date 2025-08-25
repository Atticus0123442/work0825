// NavbarWithInternalFrame.java
package controller.nevbar;

import java.awt.*;
import javax.swing.*;

import controller.mainui.exedata;
import controller.mainui.fooddata;
import controller.mainui.mainui;
import controller.mainui.userdataUI;
import dao.impl.Exercise_logsDaoImpl;
import dao.impl.UserDaoImpl;
import model.users;

public class navbar extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel navbars;
    private JPanel subMenuPanel;
    private JDesktopPane desktop;
    private String currentOpenMain = null;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
            	UserDaoImpl dao = new UserDaoImpl();
				users user = new users();
				user=dao.getById(0);
            	navbar frame = new navbar(user);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    
    public navbar() {
		 this(null);
	    }
    public navbar(users user) {
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        navbars = new JPanel(new FlowLayout(FlowLayout.LEFT));
        navbars.setBackground(new Color(40, 40, 40));
        addTopButton("首頁", null);
        addTopButton("健康追蹤", new String[]{ "運動紀錄", "飲食紀錄"});
        addTopButton("個人專區", new String[]{ "資料更改"});

        subMenuPanel = new JPanel();
        subMenuPanel.setVisible(false);
        subMenuPanel.setBackground(new Color(60, 60, 60));

        JPanel topArea = new JPanel(new BorderLayout());
        topArea.add(navbars, BorderLayout.NORTH);
        topArea.add(subMenuPanel, BorderLayout.CENTER);
        add(topArea, BorderLayout.NORTH);

        desktop = new JDesktopPane();
        add(desktop, BorderLayout.CENTER);
        
        openInternalFrame("首頁");
    }

    private void addTopButton(String title, String[] subItems) {
        JButton btn = new JButton(title);
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(40, 40, 40));
        btn.setFocusPainted(false);

        btn.addActionListener(e -> {
            if (subItems == null) {
                subMenuPanel.setVisible(false);
                currentOpenMain = null;
                openInternalFrame(title);
            } else {
                if (subMenuPanel.isVisible() && title.equals(currentOpenMain)) {
                    subMenuPanel.setVisible(false);
                    currentOpenMain = null;
                } else {
                    showSubMenu(subItems);
                    currentOpenMain = title;
                }
            }
        });
        navbars.add(btn);
    }

    private void showSubMenu(String[] items) {
        subMenuPanel.removeAll();
        subMenuPanel.setVisible(true);

        for (String s : items) {
            JButton b = new JButton(s);
            b.setBackground(new Color(80, 80, 80));
            b.setForeground(Color.WHITE);
            b.setFocusPainted(false);
            b.addActionListener(e -> {
                openInternalFrame(s);
                subMenuPanel.setVisible(false);
            });
            subMenuPanel.add(b);
        }
        revalidate();
        repaint();
    }

    private void openInternalFrame(String name) {
        for (JInternalFrame f : desktop.getAllFrames()) f.dispose();
        UserDaoImpl dao = new UserDaoImpl();
        users user = dao.getById(0);
        JInternalFrame internal;
        internal=null;
        switch (name) {
            case "首頁":
                
                
                internal = new mainui(user); 
                
                
                break;
            case "運動紀錄":
                internal = new exedata(user);    
                break;
            case "飲食紀錄":
            	internal = new fooddata(user); 
                break;
            case "資料更改":
            	internal = new userdataUI(user);
                
                
                break;
            default:
                internal = new JInternalFrame(name, true, true, true, true);
                internal.setLayout(new BorderLayout());
                //internal.add(new JLabel(name + " 內容", SwingConstants.CENTER));
                break;
        }

        internal.setSize(900, 600);
        internal.setLocation(0, 0);
        internal.setResizable(false);
        internal.setClosable(false);
        internal.setMaximizable(false);
        internal.setIconifiable(false);
        internal.putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        internal.setBorder(null);
        internal.setVisible(true);
        internal.setVisible(true);
        desktop.add(internal);
        try { internal.setSelected(true); } catch (java.beans.PropertyVetoException e) { e.printStackTrace(); }
    }
}
