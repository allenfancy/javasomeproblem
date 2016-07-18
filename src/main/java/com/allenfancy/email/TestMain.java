/*package com.allenfancy.email;

import java.rmi.RemoteException;

import com.focussend.FocusReceiverAction;
import com.focussend.FocusReport;
import com.focussend.FocusSendWebServiceSoapProxy;
import com.focussend.FocusUser;

public class TestMain {

	public static void main(String[] args) throws RemoteException {
		userGetReportByName();
		//useGetReceiverAction();
		useGetAllReceiverAction();
	}

	public static void userGetReportByName() throws RemoteException {
		FocusSendWebServiceSoapProxy uid = new FocusSendWebServiceSoapProxy();
		FocusUser fUser = new FocusUser();
		fUser.setEmail("larry.lang@babailiren.com");

		String pwd = "38b20fe6a5b35bd3c2ce83eacad3cc6b37895a8d";
		fUser.setPassword(pwd);

		FocusReport fr = uid.getReportByName(fUser, "allenTest-1456923821669");
		System.out.println("总点击次数：" + fr.getAllClicked() + "  总打开次数 " + fr.getOpenCount() + " 已经发送的数：：" + fr.getSent()
				+ " 总数：" + fr.getTotal() + " 硬退： " + fr.getHardBounce() + " 链接点击数： " + fr.getLinkClicked() + " 邮件点击数： "
				+ fr.getMailClicked() + " " + fr.getSoftBounce() + " 纯打开书：" + fr.getUniqueOpenCount() + " 退订数："
				+ fr.getUnsubscribe());

	}

	public static void useGetReceiverAction() throws RemoteException {

		FocusSendWebServiceSoapProxy uid = new FocusSendWebServiceSoapProxy();

		FocusUser fUser = new FocusUser();
		fUser.setEmail("larry.lang@babailiren.com");

		String pwd = "38b20fe6a5b35bd3c2ce83eacad3cc6b37895a8d";
		fUser.setPassword(pwd);
		FocusReceiverAction frc = uid.getReceiverAction(fUser, "allenTest-1456923821669", "598717394@qq.com");
		System.out.println(frc.getAction() + " " + frc.getActionDate() + " " + frc.getDescription() + " "
				+ frc.getEmail() + " " + frc.getIP() + " " + frc.getTaskId());

	}
	
	public static void useGetAllReceiverAction() throws RemoteException{

		FocusSendWebServiceSoapProxy uid = new FocusSendWebServiceSoapProxy();

		FocusUser fUser = new FocusUser();
		fUser.setEmail("larry.lang@babailiren.com");

		String pwd = "38b20fe6a5b35bd3c2ce83eacad3cc6b37895a8d";
		fUser.setPassword(pwd);
		
		FocusReceiverAction[] receiversAction  = uid.getAllReceiverAction(fUser, "allenTest-1456923821669", 1, 10000);
		
		for(FocusReceiverAction fra : receiversAction){
			System.out.println(fra.getAction() + " " + fra.getActionDate() + " " + fra.getDescription() + " "
					+ fra.getEmail() + " " + fra.getIP() + " " + fra.getTaskId());

		}
		
	}
}
*/