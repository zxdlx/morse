package com.zhuang.init;

import com.zhuang.component.Components;

import javax.swing.*;

/**
 * 初始化界面类
 *
 * @module
 * @author zxd
 * @date 2022/10/14  15:36
**/
public class MorseInit {
    public MorseInit(){
        Components components = new Components();
        //设置窗口
        JFrame jFrame = components.setWindow();
        //设置面板
        components.setContentPane();
        //设置标签
        components.setLabel();
        //设置文本框
        components.setTextField();
        //文本转莫斯按钮
        components.setToMorseBtn();
        //莫斯转文本按钮
        components.setToTextBtn();
        //清空文本框按钮
        components.clear();
        //动态设置分隔符
        components.setSeparator();

        //显示窗口
        jFrame.setVisible(true);
    }

}
