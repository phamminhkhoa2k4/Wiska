/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shoes.message;

/**
 *
 * @author ASUS
 */
public class Message {
    private String msg;
    private String code;
    private boolean  isMsg;

    public  Message(){}
    public Message(String msg, String code, boolean isMsg) {
        this.msg = msg;
        this.code = code;
        this.isMsg = isMsg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isIsMsg() {
        return isMsg;
    }

    public void setIsMsg(boolean isMsg) {
        this.isMsg = isMsg;
    }
}
