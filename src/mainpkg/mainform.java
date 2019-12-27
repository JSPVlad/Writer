package mainpkg;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class mainform {
    private JTextArea textArea1;
    private JButton saveButton;
    private JButton openButton;
    private JPanel panel1;
    private JScrollPane scrollpane;
    private int r;

    mainform() {
        panel1.setPreferredSize(new Dimension(500, 500));
        saveButton.setBorder(new EmptyBorder(10, 10, 10, 10));
        openButton.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel1.setBackground(Color.BLACK);
        textArea1.setBackground(Color.BLACK);
        textArea1.setBorder(null);
        panel1.setBorder(null);
        scrollpane.setBorder(null);
        openButton.setModel(new FixedStateButtonModel());
        saveButton.setModel(new FixedStateButtonModel());
        JFileChooser jFileChooser = new JFileChooser();
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                r = jFileChooser.showOpenDialog(null);
                if (r == JFileChooser.APPROVE_OPTION) {
                    String FilePath = jFileChooser.getSelectedFile().getAbsolutePath();
                    try {
                        FileInputStream fileInputStream = new FileInputStream(FilePath);
                        textArea1.setText("");
                        while (fileInputStream.available() > 0) {
                            char c = (char) fileInputStream.read();
                            textArea1.append(String.valueOf(c));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                r = jFileChooser.showSaveDialog(null);
                if (r == JFileChooser.APPROVE_OPTION) {
                    String FilePath = jFileChooser.getSelectedFile().getAbsolutePath();
                    try {
                        FileOutputStream fileOutputStream = new FileOutputStream(FilePath);
                        fileOutputStream.write(textArea1.getText().getBytes());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }
    public static void main(String[] args) {
        JFrame jFrame = new JFrame("Writer");
        jFrame.setContentPane(new mainform().panel1);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
        setWindowPosition(jFrame, 0);
        jFrame.setBackground(Color.BLACK);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
    private static void setWindowPosition(JFrame window, int screen) {
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] allDevices = env.getScreenDevices();
        int topLeftX, topLeftY, screenX, screenY, windowPosX, windowPosY;
        if (screen < allDevices.length && screen > -1) {
            topLeftX = allDevices[screen].getDefaultConfiguration().getBounds().x;
            topLeftY = allDevices[screen].getDefaultConfiguration().getBounds().y;
            screenX = allDevices[screen].getDefaultConfiguration().getBounds().width;
            screenY = allDevices[screen].getDefaultConfiguration().getBounds().height;
        } else {
            topLeftX = allDevices[0].getDefaultConfiguration().getBounds().x;
            topLeftY = allDevices[0].getDefaultConfiguration().getBounds().y;
            screenX = allDevices[0].getDefaultConfiguration().getBounds().width;
            screenY = allDevices[0].getDefaultConfiguration().getBounds().height;
        }
        windowPosX = ((screenX - window.getWidth()) / 2) + topLeftX;
        windowPosY = ((screenY - window.getHeight()) / 2) + topLeftY;
        window.setLocation(windowPosX, windowPosY);
    }
}
