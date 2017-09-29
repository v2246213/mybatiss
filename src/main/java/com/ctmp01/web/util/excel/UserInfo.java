package com.ctmp01.web.util.excel;

import java.util.List;

/**
 * Created by Administrator on 2017/6/9.
 */
public class UserInfo {
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
   // @ExcelResources(title="账号",order=1)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    private String mobile;
    private String nickname;
    private String birtherDay;
    private Integer sex;
    private String age;
   // @ExcelResources(title="性别",order=4)
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    private String gender;
    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    private Long createTime;
    private List<UserLableList> userLableLists;

    public UserInfo(int userId, String mobile,String nickname, String birtherDay, Integer sex, String age,String gender,String lable,String[] lables, List<UserLableList> userLableLists) {
       this.userId=userId;
        this.nickname = nickname;
        this.birtherDay = birtherDay;
        this.sex = sex;
        this.age = age;
        this.gender = gender;
        this.lable = lable;
        this.lables = lables;
        this.mobile = mobile;
        this.userLableLists = userLableLists;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", nickname='" + nickname + '\'' +
                ", birtherDay=" + birtherDay +
                ", sex=" + sex +
                ", mobile=" + mobile +
                ", gender=" + gender +
                ", lable=" + lable +
                ", age='" + age + '\'' +
                ", lables='" + lables + '\'' +
                ", userLableLists=" + userLableLists +
                '}';
    }

    public UserInfo() {
    }


    //@ExcelResources(title="昵称",order=2)
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
   // @ExcelResources(title="注册时间",order=3)
    public String getBirtherDay() {
        return birtherDay;
    }
    public void setBirtherDay(String birtherDay) {
        this.birtherDay = birtherDay;
    }

    public Integer getSex() {
        return sex;
    }
    public void setSex(Integer sex) {
        this.sex = sex;
    }
   // @ExcelResources(title="生日",order=5)
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
    //@ExcelResources(title="标签",order=6)
    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }


    private String lable;

    public String[] getLables() {
        return lables;
    }

    public void setLables(String[] lables) {
        this.lables = lables;
    }

    private String[] lables;
    public List<UserLableList> getUserLableLists() {
        return userLableLists;
    }

    public void setUserLableLists(List<UserLableList> userLableLists) {
        this.userLableLists = userLableLists;
    }
}
