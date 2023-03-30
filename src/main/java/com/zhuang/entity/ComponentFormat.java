package com.zhuang.entity;

import java.awt.*;
import java.io.Serializable;

/**
 * 组件风格实体类
 *
 * @module
 * @author zxd
 * @date 2022/10/14  15:36
**/
public class ComponentFormat implements Serializable {
    /**
     * 标题
     */
    private String label;

    /**
     * x轴
     */
    private Integer x;

    /**
     * y轴
     */
    private Integer y;

    /**
     * 宽
     */
    private Integer width;

    /**
     * 高
     */
    private Integer height;

    /**
     * 字体
     */
    private String typeface;

    /**
     * 字体格式，宋体、楷体、黑体...
     */
    private Integer style;

    /**
     * 字体大小
     */
    private Integer size;

    /**
     * 字体颜色
     */
    private Color typefaceColor;

    /**
     * 组件颜色
     */
    private Color componentColor;

    /**
     * 创建标题
     * @param label
     */
    public ComponentFormat(String label){
        this.label=label;
    }

    /**
     * 设置位置宽高
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public void setBounds(Integer x, Integer y, Integer width, Integer height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * 设置字体风格
     * @param typeface
     * @param style
     * @param size
     * @param typefaceColor
     */
    public void setFont(String typeface, Integer style, Integer size, Color typefaceColor) {
        this.typeface = typeface;
        this.style = style;
        this.size = size;
        this.typefaceColor = typefaceColor;
    }

    public void setColor(Color color){
        this.componentColor=color;
    }

    public String getLabel() {
        return label;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

    public String getTypeface() {
        return typeface;
    }

    public Integer getStyle() {
        return style;
    }

    public Integer getSize() {
        return size;
    }

    public Color getComponentColor() {
        return componentColor;
    }

    public Color getTypefaceColor() {
        return typefaceColor;
    }
}
