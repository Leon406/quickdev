package me.leon.quickdev.bean;

import java.io.Serializable;

/**
 * Author:  Parorisim
 * Time:    2017/4/14 下午2:34
 * Desc:    简单用户数据类
 */

@SuppressWarnings({"unused", "FieldCanBeLocal"})
public class SimpleUser implements Serializable {

    private int u_id;
    private String u_name;
    private String u_realname;
    private String u_sex;
    private int u_age;
    private int u_height;
    private String u_income;
    private String u_zodiac_sign;
    private String u_constellation;
    private String u_photo;
    private String u_info;
    private int u_credentials_status;
    private int u_vip;
    private String ue_school;
    private String ue_high;
    private int u_online;
    private int u_likestatus;
    private String c_name;
    private String c_cityname;
    private String c_quyuname;
    private int b_id;
    private String u_yx_user;
    private String at_identity;
    private String at_edu;
    private String at_house;
    private String at_car;
    private boolean u_top;
    private int showtype;
    private String u_marriage;

    public String getU_marriage() {
        if(u_marriage == null||u_marriage.equals("null")){
            u_marriage="从未结婚";
        }
        return u_marriage;
    }

    public void setU_marriage(String u_marriage) {
        this.u_marriage = u_marriage;
    }

    public int getShowtype() {
        return showtype;
    }

    public void setShowtype(int showtype) {
        this.showtype = showtype;
    }

    public int getId() {
        return u_id;
    }

    public String getNickName() {
        return u_name;
    }

    public String getRealName() {
        return u_realname;
    }

    public String getGender() {
        return u_sex;
    }

    public int getAge() {
        return u_age;
    }

    public int getHeight() {
        return u_height;
    }
    public String getStrHeight() {
        return u_height+ "cm";
    }

    public String getIncome() {
        return u_income;
    }

    public String getZodiac() {
        return u_zodiac_sign;
    }

    public String getConstellation() {
        return u_constellation;
    }

    public String getImage() {
        return u_photo;
    }

    public String getInfo() {
        return u_info;
    }

    public String getCity() {
        return c_cityname;
    }

    public int getOnline() {
        return u_online;
    }

    public int getLikeStatus() {
        return u_likestatus;
    }

    public void setLikeStatus(int u_likestatus) {
        this.u_likestatus = u_likestatus;
    }

    public int getCredentialStatus() {
        return u_credentials_status;
    }

    public int getVip() {
        return u_vip;
    }

    public String getSchool() {
        return ue_school;
    }

    public String getAcademic() {
        return ue_high;
    }

    public int getBid() {
        return b_id;
    }

    public String getNimAccount() {
        return u_yx_user;
    }

    public String getCarTag() {
        return at_car;
    }

    public String getHouseTag() {
        return at_house;
    }

    public String getIdTag() {
        return at_identity;
    }

    public String getAcademicTag() {
        return at_edu;
    }

    public boolean isTop() {
        return u_top;
    }

    @Deprecated
    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    @Deprecated
    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    @Deprecated
    public void setU_realname(String u_realname) {
        this.u_realname = u_realname;
    }

    @Deprecated
    public void setU_sex(String u_sex) {
        this.u_sex = u_sex;
    }

    @Deprecated
    public void setU_age(int u_age) {
        this.u_age = u_age;
    }

    @Deprecated
    public void setU_height(int u_height) {
        this.u_height = u_height;
    }

    @Deprecated
    public void setU_income(String u_income) {
        this.u_income = u_income;
    }

    @Deprecated
    public void setU_zodiac_sign(String u_zodiac_sign) {
        this.u_zodiac_sign = u_zodiac_sign;
    }

    @Deprecated
    public void setU_constellation(String u_constellation) {
        this.u_constellation = u_constellation;
    }

    @Deprecated
    public void setU_photo(String u_photo) {
        this.u_photo = u_photo;
    }

    @Deprecated
    public void setU_info(String u_info) {
        this.u_info = u_info;
    }

    @Deprecated
    public void setU_credentials_status(int u_credentials_status) {
        this.u_credentials_status = u_credentials_status;
    }

    @Deprecated
    public void setU_vip(int u_vip) {
        this.u_vip = u_vip;
    }

    @Deprecated
    public void setUe_school(String ue_school) {
        this.ue_school = ue_school;
    }

    @Deprecated
    public void setUe_high(String ue_high) {
        this.ue_high = ue_high;
    }

    @Deprecated
    public void setU_online(int u_online) {
        this.u_online = u_online;
    }

    @Deprecated
    public void setU_likestatus(int u_likestatus) {
        this.u_likestatus = u_likestatus;
    }

    @Deprecated
    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    @Deprecated
    public void setC_cityname(String c_cityname) {
        this.c_cityname = c_cityname;
    }

    @Deprecated
    public void setC_quyuname(String c_quyuname) {
        this.c_quyuname = c_quyuname;
    }

    @Deprecated
    public void setB_id(int b_id) {
        this.b_id = b_id;
    }

    @Deprecated
    public void setU_yx_user(String u_yx_user) {
        this.u_yx_user = u_yx_user;
    }

    @Deprecated
    public void setAt_car(String at_car) {
        this.at_car = at_car;
    }

    @Deprecated
    public void setAt_edu(String at_edu) {
        this.at_edu = at_edu;
    }

    @Deprecated
    public void setAt_house(String at_house) {
        this.at_house = at_house;
    }

    @Deprecated
    public void setAt_identity(String at_identity) {
        this.at_identity = at_identity;
    }

    @Deprecated
    public void setU_top(boolean u_top) {
        this.u_top = u_top;
    }
}
