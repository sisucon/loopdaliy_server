package com.sisucon.loopdaliy_server.Security;

import java.util.HashMap;

public  class RoleList {
     public enum Roles{
        NoMobileMember,
        NormalMember,
        Admin;
    }

    private static HashMap<Roles,String> roleMap = new HashMap<Roles,String>(){
        {
            put(Roles.NoMobileMember,"ROLE_NOMOBILE");
            put(Roles.NormalMember,"ROLE_NORMAL");
            put(Roles.Admin,"ROLE_ADMIN");
        }
    };

    public  static String value(Roles roles){
        return roleMap.get(roles);
    }
}
