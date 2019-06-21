package com.sisucon.loopdaliy_server.Model;

import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class UserModel implements UserDetails {
    @Id
    private long id;
    private String userName;
    private String mobileNum;
    private Collection<? extends GrantedAuthority> myAuthorities;
    private String passWord;
    private ArrayList<Long> attendAction;

    public ArrayList<Long> getFriendList() {
        if (friendList==null){
            friendList = new ArrayList<Long>();
        }
        return friendList;
    }

    public void setFriendList(ArrayList<Long> friendList) {
        this.friendList = friendList;
    }

    private ArrayList<Long> friendList;
    public String getAvatorFileName() {
        return avatorFileName;
    }

    public void setAvatorFileName(String avatorFileName) {
        this.avatorFileName = avatorFileName;
    }

    private String avatorFileName;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return myAuthorities;
    }

    @Override
    public String getPassword() {
        return passWord;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public Collection<? extends GrantedAuthority> getMyAuthorities() {
        return myAuthorities;
    }

    public void setMyAuthorities(Collection<? extends GrantedAuthority> myAuthorities) {
        this.myAuthorities = myAuthorities;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public ArrayList<Long> getAttendAction() {
        if (attendAction==null){
            attendAction = new ArrayList<Long>();
        }
        return attendAction;
    }

    public void setAttendAction(ArrayList<Long> attendAction) {
        this.attendAction = attendAction;
    }
}
