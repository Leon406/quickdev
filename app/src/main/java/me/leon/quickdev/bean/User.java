package me.leon.quickdev.bean;

import android.text.TextUtils;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Author:  Leon
 * Time:    2017/4/11 下午2:37
 * Desc:    用户数据类
 */

@SuppressWarnings({"unused", "WeakerAccess"})
public class User extends RealmObject {

    @PrimaryKey
    public int u_id;
    public String u_token;
    public String u_location;
    public String u_tel;
    public String u_time;
    public String u_password;
    public String u_img;
    public int u_status;
    public String u_name;
    public String u_realname;
    public String u_sex;
    public String u_email;
    public int u_money;
    public int u_type;
    public String u_birthday;
    public String u_province;
    public String u_city;
    public int u_income_id;
    public String u_income;
    public String u_info;
    public String u_photo;
    public int u_height;
    public int u_age;
    public String u_feel_experience;
    public String u_edu;
    public String u_household_province;
    public int u_iscar;
    public int u_ishouse;
    public String u_zodiac_sign;
    public String u_constellation;
    public int u_history;
    public String u_family;
    public String u_career_planning;
    public String u_expect_time;
    public String u_nation;
    public String u_marriage;
    public String u_occupation;
    public String u_faith;
    public String u_wine;
    public String u_smoke;
    public String u_home_rand;
    public String u_children;
    public String u_weight;
    public String u_household_city;
    public int u_credentials_type;
    public String u_credentials_num;
    public String u_credentials_img1;
    public String u_credentials_img2;
    public int u_credentials_status;
    public int u_auth_pay;
    public int u_vip;
    public int u_recommend;
    public String u_updatetime;
    public String u_credentials_time;
    public String u_quyu;
    public String u_household_quyu;
    public int u_mark;
    public int u_like;
    public int u_black;
    public String u_credentials_addtime;
    public String u_online;
    public String u_recommend_type;
    public String u_lng;
    public String u_lat;
    public String u_fictitious;
    public String u_against;
    public String u_yx_token;
    public String u_ret_status;
    public String c_name;
    public String c_cityname;
    public String c_quyuname;
    public String authsf;
    public int authsf_status;
    public String authedu;
    public int authedu_status;
    public String authcar;
    public int authcar_status;
    public String authhouse;
    public int authhouse_status;
    public int uid;
    public String u_user_mate;
    public String u_yx_user;
    public int part_count;
    public int u_cur_likecount; //我的动心数量
    public String u_weixin;
    public String householdprovincename;
    public String householdcityname;
    public String householdquyu;
    public int u_chatcount;
    public String u_exp_time;
    public String u_exp_day;
    public int u_photo_status;
    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^SPLIT START^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

    public String strvip_name;      //会员
    public String strmatching_name; //月老牵线
    public String strjisu_name;    //急速
    public int u_jisu; //极速联络剩余张数
    public int u_matching; //月老牵线剩余次数
    public int u_complete; //完善度
    public int complete_count; //完善条数
    public int strvip_iscolor;
    public int strmatching_iscolor;
    public int strjisu_iscolor;
    public boolean qxstatus;
    public boolean isbuy_qx;//是否有购买过牵线服务

    //高级
    public String ui_housework;             //家务分工
    public String ui_children;             //是否想要孩子
    public String ui_parents;             //是否愿意和父母同住
    public String ui_wedding;             //婚礼形式
    public String ui_importance;             //希望对方看重
    public String ui_rendezvous;             //约会方式
    public String ui_love_plan;             //爱情规划
    public String ui_life;             //生活作息
    public String ui_looks;             //相貌自评
    public String ui_shape;             //体型
    public String ui_blood;             //血型
    public String ui_edu;             //最高学历
    public String ui_school;             //毕业学校
    public String ui_emotion;             //情感经历
    public String ui_registered;             //户口
    public String ui_hometown;             //家乡
    public String um_faith;             //宗教信仰

    // 原生家庭
    public String ui_parents_situation;      //父母情况
    public String ui_parents_economy;        //父母经济
    public String ui_parents_health;         //父母医保
    public String u_pushid_t;                //极光推送id
    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^SPLIT   END^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

//    public int showtype;//显示类型

    private int u_black_status;//1是我拉黑了对方 2是对方拉黑了我  3是互相拉黑

    public int getU_black_status() {
        return u_black_status;
    }

    public void setU_black_status(int u_black_status) {
        this.u_black_status = u_black_status;
    }

    public int getImageStatus() {
        return u_photo_status;
    }

    public int getId() {
        return u_id;
    }

    public void setId(int u_id) {
        this.u_id = u_id;
    }

    public String getToken() {
        return u_token;
    }

    public void setToken(String u_token) {
        this.u_token = u_token;
    }

    public int getMark() {
        return u_mark;
    }

    public String getGender() {
        return u_sex;
    }

    public void setGender(String u_sex) {
        this.u_sex = u_sex;
    }

    public int getVip() {
        return u_vip;
    }
    public boolean isVip() {
        return u_vip ==1;
    }

    public int getIdType() {
        return u_credentials_type;
    }

    public void setIdType(int u_credentials_type) {
        this.u_credentials_type = u_credentials_type;
    }

    public String getIdCode() {
        return u_credentials_num;
    }

    public void setIdCode(String u_credentials_num) {
        this.u_credentials_num = u_credentials_num;
    }

    public String getIdTime() {
        return u_credentials_time;
    }

    public int getUid() {
        return uid;
    }


    public String getIdFrontImage() {
        return u_credentials_img1;
    }

    public void setIdFrontImage(String u_credentials_img1) {
        this.u_credentials_img1 = u_credentials_img1;
    }

    public String getIdHandImage() {
        return u_credentials_img2;
    }

    public void setIdHandImage(String u_credentials_img2) {
        this.u_credentials_img2 = u_credentials_img2;
    }

    public String getNickName() {
        return u_name;
    }

    public void setNickName(String u_name) {
        this.u_name = u_name;
    }

    public String getRealName() {
        return u_realname;
    }

    public void setRealName(String u_realname) {
        this.u_realname = u_realname;
    }

    public String getImage() {
        return u_photo;
    }

    public int getAge() {
        return u_age;
    }

    public void setLocation(String u_location) {
        this.u_location = u_location;
    }

    public void setInfo(String u_info) {
        this.u_info = u_info;
    }

    public void setIncomeId(int u_income_id) {
        this.u_income_id = u_income_id;
    }

    public int getIdStatus() {
        return authsf_status;
    }

    public String getIdDesc() {
        return authsf;
    }

    public int getAcademicStatus() {
        return authedu_status;
    }

    public String getAcademicDesc() {
        return authedu;
    }

    public int getCarStatus() {
        return authcar_status;
    }

    public String getCarDesc() {
        return authcar;
    }

    public int getHouseStatus() {
        return authhouse_status;
    }

    public String getHouseDesc() {
        return authhouse;
    }

    public int getDynamicCount() {
        return part_count;
    }

    public int getType() {
        return u_type;
    }

    public String getTel() {
        return u_tel;
    }

    public void setTel(String u_tel) {
        this.u_tel = u_tel;
    }

    public String getPlace() {
        return (c_name.equals("") ? "" : c_name + "·") + (c_cityname.equals("") ? "" : c_cityname + "·") + c_quyuname;
    }

    public String getNativePlace() {
        return (householdprovincename.equals("") ? "" : householdprovincename + "·")
                + (householdcityname.equals("") ? "" : householdcityname + "·") + householdquyu;
    }

    public String getWechat() {
        return u_weixin;
    }

    public String getBirth() {
        return u_birthday;
    }

    public void setBirth(String u_birthday) {
        this.u_birthday = u_birthday;
    }

    public String getConstellation() {
        return u_constellation;
    }

    public String getZodiac() {
        return u_zodiac_sign;
    }

    public String getIncome() {
        return u_income;
    }

    public void setIncome(String u_income) {
        this.u_income = u_income;
    }

    public String getHeight() {
        return u_height == 0 ? "" : u_height + "cm";
    }

    public String getWeight() {
        return TextUtils.isEmpty(u_weight) ? "" : u_weight + "kg";
    }

    public String getExpect() {
        return u_expect_time;
    }

    public String getNation() {
        return u_nation;
    }

    public String getFaith() {
        return u_faith;
    }

    public String getWine() {
        return u_wine;
    }

    public String getSmoke() {
        return u_smoke;
    }

    public String getJob() {
        return u_occupation;
    }

    public String getExperience() {
        return ui_emotion;
    }

    public String getMarriage() {
        return u_marriage;
    }

    public String getFamily() {
        return u_family;
    }

    public String getChild() {
        return ui_children;
    }

    public String getRank() {
        return u_home_rand;
    }

    public String getIntro() {
        return u_info;
    }

    public String getPlan() {
        return u_career_planning;
    }

    public String getNimAccount() {
        return u_yx_user;
    }

    public String getNimToken() {
        return u_yx_token;
    }

    public int getChatCount() {
        return u_chatcount;
    }

    public void setChatCount(int u_chatcount) {
        this.u_chatcount = u_chatcount;
    }

    public String getVipLeftTime() {
        return u_exp_time + " " + u_exp_day;
    }

    public int getU_matching(){
        return  u_matching;
    }
}
