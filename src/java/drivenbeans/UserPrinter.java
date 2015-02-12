/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drivenbeans;

/**
 *
 * @author Oana
 */
public class UserPrinter implements java.io.Serializable {

    private int id;
    private int user;
    private int printer;
    private String text1;
    private String text2;
    private String doc;

    public UserPrinter() {
    }

    public UserPrinter(int id, int user, int printer, String text1, String text2, String doc) {
        this.id = id;
        this.user = user;
        this.printer = printer;
        this.text1 = text1;
        this.text2 = text2;
        this.doc = doc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getPrinter() {
        return printer;
    }

    public void setPrinter(int printer) {
        this.printer = printer;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

}
