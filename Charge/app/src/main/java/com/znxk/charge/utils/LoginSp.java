package com.znxk.charge.utils;


import com.znxk.charge.base.MyApplication;

/**
 * Created by Administrator on 2017/2/8.
 */

public class LoginSp {

    public static  void save(String key ,String username){
        SharedPrefrencesUtil.saveData(MyApplication.mContextGlobal,"userData",key,username);
    }

    public static  String get(String key){
        return SharedPrefrencesUtil.getData(MyApplication.mContextGlobal,"userData",key,"");
    }

    public static void remove(String key){
        SharedPrefrencesUtil.removeData(MyApplication.mContextGlobal,"userData",key);
    }


}
