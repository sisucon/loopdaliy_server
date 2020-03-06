package com.sisucon.loopdaliy_server.Model;


import org.springframework.data.annotation.Id;


public class ActionClass {
    @Id
    private long id;
    private String name;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getAttendNumber() {
        return attendNumber;
    }

    public void setAttendNumber(int attendNumber) {
        this.attendNumber = attendNumber;
    }

    public void addAttendNumber(){
        this.attendNumber++;
    }

    public void cancelAttendNumber(){
        this.attendNumber--;
    }

    private int attendNumber;
    //    0学习,1运动,2生活
    private int type;
    public boolean isAccept() {
        return isAccept;
    }
  
    public void setAccept(boolean accept) {
        isAccept = accept;
    }

    private boolean isAccept;
    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    private String imageName;
    private long loopTime;
    private long uploadTime;

    public int getSuccessTime() {
        return successTime;
    }

    public void setSuccessTime(int successTime) {
        this.successTime = successTime;
    }
    //完成次数
    private int successTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public long getLoopTime() {
        return loopTime;
    }

    public void setLoopTime(long loopTime) {
        this.loopTime = loopTime;
    }

    public long getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(long uploadTime) {
        this.uploadTime = uploadTime;
    }

}
