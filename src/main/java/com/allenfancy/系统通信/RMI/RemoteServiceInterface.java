package com.allenfancy.系统通信.RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RemoteServiceInterface extends Remote{

	/**
     * 这个RMI接口负责查询目前已经注册的所有用户信息
     */
    public List<UserInfo> queryAllUserinfo() throws RemoteException;

}
