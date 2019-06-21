package com.sisucon.loopdaliy_server.Model;

import org.springframework.data.annotation.Id;

public class ActionEvent {
    @Id
    private long id;
    //用户id
    private long userId;
    //活动id
    private long actionId;
    //上传时间
    private long updateTime;
    //上传次数
    private int holdTime;


    public long getALLTime() {
        return ALLTime;
    }

    public void setALLTime(long ALLTime) {
        this.ALLTime = ALLTime;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    private boolean isPrivate;
    private long ALLTime;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getActionId() {
        return actionId;
    }

    public void setActionId(long actionId) {
        this.actionId = actionId;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public int getHoldTime() {
        return holdTime;
    }

    public void setHoldTime(int holdTime) {
        this.holdTime = holdTime;
    }
}
