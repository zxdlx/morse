package com.zhuang.component;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.zhuang.MorseMain;
import com.zhuang.constant.MorseConstant;
import com.zhuang.utils.MorseUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * 组件封装类
 *
 * @module
 * @author zxd
 * @date 2022/10/14  15:36
**/
public class Components {
    //窗口
    private JFrame jFrame;
    //面板
    private Container container;
    //提醒文本
    private JLabel warn;
    //文本框
    private JTextArea jTextField;
    //字母分隔符
    private String charSeparator;
    //单词分隔符
    private String wordSeparator;
    //分隔符提示
    private JLabel separatorLabel;

    /**
     * 窗口
     */
    public JFrame setWindow(){
        jFrame = new JFrame("摩斯密码转换");                  //创建窗口，设置名称
        jFrame.setSize(MorseConstant.WINDOW_WIDTH,MorseConstant.WINDOW_HEIGHT);   //设置窗口大小
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //设置点击右上角关闭按钮后程序会停止，不设置的话关闭了程序不会停止
        jFrame.setLocationRelativeTo(null);                     //设置启动时默认位置为屏幕居中
        jFrame.setLayout(null);                                 //将原本的layout设置为null，不设置的话自己的样式看不见
        jFrame.setResizable(false);                             //设置窗口不可变大或缩小
        //设置窗口的图标，先获取图片再设置
        BufferedImage read = null;
        try {
            URL url = MorseMain.class.getClassLoader().getResource(MorseConstant.IMG_PATH);
            if (ObjectUtil.isNotEmpty(url)) {
                read = ImageIO.read(url);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        jFrame.setIconImage(read);
        return jFrame;
    }

    /**
     * 面板
     */
    public void setContentPane() {
        container = jFrame.getContentPane();             //再窗口中创建一个面板
        container.setBackground(new Color(0xA4A8D5E7, true));   //面板颜色
    }

    /**
     * 标签
     */
    public void setLabel() {
        JLabel label = new JLabel("输入内容：");          //创建一个标签
        label.setBounds(20,20,200,20);     //标签位置和大小
        label.setFont(new Font("宋体",Font.PLAIN,16));
        container.add(label);                              //将标签添加到面板中
        setSeparatorMessage();
    }

    /**
     * 设置提醒文本
     */
    public void setWarn(String text) {
        //如果存在提醒文本，就清空
        clearWarn();
        warn = new JLabel(text);
        warn.setBounds(20,300,600,20);
        warn.setFont(new Font("宋体",Font.PLAIN,16));
        warn.setForeground(new Color(0xC71321));
        container.add(warn);
        jFrame.repaint();
    }

    /**
     * 清空提醒文本
     */
    public void clearWarn(){
        if (container.isAncestorOf(warn)) {
            container.remove(warn);
            jFrame.repaint();
        }
    }

    /**
     * 文本框
     */
    public void setTextField() {
        jTextField = new JTextArea();                         //创建一个文本框
        jTextField.setBounds(20,50,900,200);          //设置文本框的位置和大小
        jTextField.setFont(new Font("宋体",Font.PLAIN,16));    //设置文本框里的字体格式
        jTextField.setLineWrap(true);        //激活自动换行功能，行满了自动换行
        jTextField.setWrapStyleWord(true);   //以单词为准进行换行
        container.add(jTextField);         //将文本框添加到面板中

        //文本框滚动条
        JScrollPane jScrollPane = new JScrollPane(jTextField);          //创建一个滚动条对象，传入文本框
        jScrollPane.setBounds(20,50,900,200);         //设置滚动条面板位置和大小，和文本框位置大小一样即可
//        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);    //显示横向滚动条
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);       //显示纵向滚动条
        jFrame.add(jScrollPane);                                        //把滚动条添加到窗口里
    }

    /**
     * 文本转摩斯密码按钮
     */
    public void setToMorseBtn() {
        JButton button = new JButton("转为摩斯密码");                  //创建一个按钮
        button.setBounds(20,260,155,30);               //设置按钮位置大小
        button.setFont(new Font("宋体", Font.PLAIN, 16));       //设置按钮字体的格式
        button.setBackground(new Color(0x09BBAC));                   //设置按钮颜色
        container.add(button);                                         //把按钮添加到面板中

        button.addActionListener(new ActionListener() {                  //设置监听，当按钮被点击时就会执行此方法
            @Override
            public void actionPerformed(ActionEvent e) {
                //如果提醒文本在容器，现在就移除并重绘
                clearWarn();
                //限制字符数
                if (jTextField.getText().length() > MorseConstant.STR_MAX) {
                    setWarn("最大支持" + MorseConstant.STR_MAX + "个字符的转换");
                }else {
                    String s = MorseUtil.stringToMorse(jTextField.getText(),charSeparator,wordSeparator);   //将字符转为摩斯密码
                    jTextField.setText(s);                                      //将文本框内容设置为转换后的摩斯密码
                    jTextField.requestFocus();      //聚焦文本框
                }
            }
        });
    }

    /**
     * 摩斯密码转文本按钮
     */
    public void setToTextBtn() {
        JButton button2 = new JButton("转为文字");                     //创建一个按钮
        button2.setBounds(190,260,150,30);              //设置按钮位置大小
        button2.setFont(new Font("宋体", Font.PLAIN, 16));       //设置按钮字体的格式
        button2.setBackground(new Color(0x09BBAC));                   //设置按钮颜色
        container.add(button2);                                         //把按钮添加到面板中
        button2.addActionListener(new ActionListener() {                  //设置监听，当按钮被点击时就会执行此方法
            @Override
            public void actionPerformed(ActionEvent e) {
                //如果提醒文本在容器，现在就移除并重绘
                clearWarn();
                //限制字符数
                if (jTextField.getText().length() > MorseConstant.MORSE_MAX) {
                    setWarn("最大支持" + MorseConstant.MORSE_MAX + "个摩斯密码的转换");
                }else if (false == StrUtil.containsOnly(jTextField.getText(), '.', '-', charSeparator.charAt(0),wordSeparator.charAt(0),' ')) {
                    //如果摩斯密码只包含这四个符号，如果有其他符号，就格式不正确，将提醒文字添加到容器中并重新绘制
                    setWarn("请输入正确的摩斯密码，区分字母用空格隔开，区分单词可用\" / \"隔开");
                }else {
                    String s = MorseUtil.morseToString(jTextField.getText(),charSeparator,wordSeparator);   //将摩斯密码转为字符
                    jTextField.setText(s);                                      //将文本框内容设置为转换后的字符
                    jTextField.requestFocus();      //聚焦文本框
                }
            }
        });
    }

    /**
     * 清空文本框按钮
     */
    public void clear(){
        JButton clear = new JButton("清空");                           //创建一个按钮
        clear.setBounds(355,260,150,30);                 //设置按钮位置大小
        clear.setFont(new Font("宋体", Font.PLAIN, 16));          //设置按钮字体的格式
        clear.setBackground(new Color(0x09BBAC));                      //设置按钮颜色
        container.add(clear);                                            //把按钮添加到面板中
        clear.addActionListener(new ActionListener() {                     //设置监听，当按钮被点击时就会执行此方法
            @Override
            public void actionPerformed(ActionEvent e) {
                //如果提醒文本在容器，现在就移除并重绘
                clearWarn();
                jTextField.setText(null);                                   //清空文本框的内容
                jTextField.requestFocus();      //聚焦文本框
            }
        });
    }

    /**
     * 分隔符文本标签
     */
    public void setSeparatorMessage(){
        if (StrUtil.isBlank(charSeparator)) {
            this.charSeparator = " ";
        }
        if (StrUtil.isBlank(wordSeparator)) {
            this.wordSeparator = "/";
        }
        if (container.isAncestorOf(separatorLabel)) {
            container.remove(separatorLabel);
        }
        String s = charSeparator == " " ? "空格" : charSeparator;
        JLabel label = new JLabel("当前输入摩斯密码时，字母分隔符\" "+s+" \"，单词分隔符\" "+wordSeparator+" \"");          //创建一个标签
        label.setBounds(220,20,700,20);     //标签位置和大小
        label.setFont(new Font("宋体",Font.PLAIN,16));
        container.add(label);                              //将标签添加到面板中
        this.separatorLabel = label;
    }

    /**
     * 动态设置摩斯密码区分字母与单词的分隔符
     */
    public void setSeparator(){
        JLabel label = new JLabel("字母分隔");
        label.setBounds(520,260,80,30);
        label.setFont(new Font("宋体",Font.PLAIN,16));
        container.add(label);

        JTextArea jTextArea = new JTextArea();                         //创建一个文本框
        jTextArea.setBounds(590,265,20,20);          //设置文本框的位置和大小
        jTextArea.setFont(new Font("宋体",Font.PLAIN,16));    //设置文本框里的字体格式
        container.add(jTextArea);         //将文本框添加到面板中

        JLabel label2 = new JLabel("单词分隔");
        label2.setBounds(620,260,80,30);
        label2.setFont(new Font("宋体",Font.PLAIN,16));
        container.add(label2);

        JTextArea jTextArea2 = new JTextArea();                         //创建一个文本框
        jTextArea2.setBounds(690,265,20,20);          //设置文本框的位置和大小
        jTextArea2.setFont(new Font("宋体",Font.PLAIN,16));    //设置文本框里的字体格式
        container.add(jTextArea2);         //将文本框添加到面板中

        JButton button = new JButton("确定");
        button.setBounds(720,260,70,30);                 //设置按钮位置大小
        button.setFont(new Font("宋体", Font.PLAIN, 16));          //设置按钮字体的格式
        button.setBackground(new Color(0x09BBAC));                      //设置按钮颜色
        container.add(button);                                            //把按钮添加到面板中
        button.addActionListener(new ActionListener() {                     //设置监听，当按钮被点击时就会执行此方法
            @Override
            public void actionPerformed(ActionEvent e) {
                //如果提醒文本在容器，现在就移除并重绘
                clearWarn();
                if (jTextArea.getText().length()>1 || jTextArea2.getText().length()>1){
                    setWarn("分隔符只能设置一位的汉字、字母、数字、符号");
                    return;
                }
                if (jTextArea.getText().equals(jTextArea2.getText())) {
                    setWarn("字母分隔符与单词分隔符不能相同");
                    return;
                }
                //如果分隔符数量为1，就设置
                if (jTextArea.getText().length()==1) {
                    setCharSeparator(jTextArea.getText());
                }
                if (jTextArea2.getText().length()==1) {
                    setWordSeparator(jTextArea2.getText());
                }
                setSeparatorMessage();
                jFrame.repaint();
                jTextField.requestFocus();      //聚焦文本框
            }
        });
    }

    public void setCharSeparator(String charSeparator) {
        this.charSeparator = charSeparator;
    }

    public void setWordSeparator(String wordSeparator) {
        this.wordSeparator = wordSeparator;
    }
}
