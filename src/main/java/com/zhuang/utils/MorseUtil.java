package com.zhuang.utils;

import cn.hutool.core.codec.Morse;
import cn.hutool.core.util.CharUtil;
import cn.hutool.core.util.StrUtil;

import java.util.regex.Matcher;

/**
 * 转换类
 *
 * @module
 * @author zxd
 * @date 2022/10/14  15:36
**/
public class MorseUtil {
    /**
     * 莫斯密码转字符
     * @param code 莫斯密码
     * @param charSeparator 字母分隔符
     * @param wordSeparator 单词分隔符
     * @return 转换后的文字
     */
    public static String morseToString(String code,String charSeparator,String wordSeparator){
        Morse morse = new Morse(CharUtil.DOT, CharUtil.DASHED, charSeparator.charAt(0));
        if (StrUtil.isNotBlank(code)) {
            if (code.contains(wordSeparator)) {
                //如果单词分隔符是$或者\，需要使用特殊方式进行替换
                code = code.replaceAll(Matcher.quoteReplacement(wordSeparator)+" ", Matcher.quoteReplacement(charSeparator)+"-.....");
            }
            return morse.decode(code);
        }
        return "";
    }

    /**
     * 字符转摩斯密码
     * @param str 文字
     * @param charSeparator 字母分隔符
     * @param wordSeparator 单词分隔符
     * @return 转换后的摩斯密码
     */
    public static String stringToMorse(String str,String charSeparator,String wordSeparator){
        Morse morse = new Morse(CharUtil.DOT, CharUtil.DASHED, charSeparator.charAt(0));
        //如果连续按了多个空格，设置为一个
        if (StrUtil.isNotBlank(str)) {
            String[] s = str.split(" ");
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < s.length; i++) {
                //忽略掉空格
                if (StrUtil.isNotBlank(s[i])) {
                    stringBuilder.append(s[i]);
                    //最后一个不拼接了
                    if (i != s.length - 1) {
                        stringBuilder.append(" ");
                    }
                }
            }
            //进行编码
            String encode = morse.encode(stringBuilder.toString());
            //生成摩斯密码后会在后面多一个空格，占字数，去掉最后一个空格
            encode = encode.substring(0,encode.length()-1);
            if (encode.contains("-.....")) {
                StringBuilder append = new StringBuilder();
                //需要转义的符号
                String[] strings = {"$","(",")","*","+",".","[","]","?","\\","/","^","{","}"};
                if (StrUtil.containsAnyIgnoreCase(charSeparator, strings)) {
                    append.append("\\").append(charSeparator).append("-\\.\\.\\.\\.\\.").append("\\");
                }else {
                    append.append(charSeparator).append("-\\.\\.\\.\\.\\.");
                }
                append.append(charSeparator);
                //如果单词分隔符是$或者\，需要使用特殊方式进行替换
                encode =encode.replaceAll(append.toString(), Matcher.quoteReplacement(wordSeparator)+" ");
            }
            return encode;
        }
        return "";
    }
}
