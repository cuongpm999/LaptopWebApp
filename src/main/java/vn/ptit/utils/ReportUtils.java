package vn.ptit.utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import vn.ptit.entities.Bill;

public class ReportUtils {
	public static String getStringMonth() {
		SimpleDateFormat sdf= new SimpleDateFormat("MMMM");
		Calendar cal = Calendar.getInstance();
		return sdf.format(cal.getTime());		
	}
	
	public static String getStringMonth1() {
		SimpleDateFormat sdf= new SimpleDateFormat("MMMM");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		return sdf.format(cal.getTime());		
	}
	
	public static String getStringMonth2() {
		SimpleDateFormat sdf= new SimpleDateFormat("MMMM");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -2);
		return sdf.format(cal.getTime());	
	}
	
	public static String getStringMonth3() {
		SimpleDateFormat sdf= new SimpleDateFormat("MMMM");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -3);
		return sdf.format(cal.getTime());		
	}
	
	public static String getStringMonth4() {
		SimpleDateFormat sdf= new SimpleDateFormat("MMMM");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -4);
		return sdf.format(cal.getTime());		
	}
	
	public static String getStringMonth5() {
		SimpleDateFormat sdf= new SimpleDateFormat("MMMM");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -5);
		return sdf.format(cal.getTime());	
	}
	
	public static BigDecimal getTotalMoneyMoth(List<Bill> list) {
		SimpleDateFormat sdf= new SimpleDateFormat("MMMM");
		Calendar cal = Calendar.getInstance();
		BigDecimal total=new BigDecimal(4);
		for(int i=0;i<list.size();i++) {
			if(sdf.format(list.get(i).getDate_create()).equalsIgnoreCase(sdf.format(cal.getTime()))) {
				total=total.add(list.get(i).getPayment().getAll_money());
				
			}
		}
		return total;	
	}
	
	public static BigDecimal getTotalMoneyMoth1(List<Bill> list) {
		SimpleDateFormat sdf= new SimpleDateFormat("MMMM");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		BigDecimal total=new BigDecimal(0);
		for(int i=0;i<list.size();i++) {
			if(sdf.format(list.get(i).getDate_create()).equalsIgnoreCase(sdf.format(cal.getTime()))) {
				total=total.add(list.get(i).getPayment().getAll_money());
				
			}
		}
		return total;	
	}
	
	public static BigDecimal getTotalMoneyMoth2(List<Bill> list) {
		SimpleDateFormat sdf= new SimpleDateFormat("MMMM");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -2);
		BigDecimal total=new BigDecimal(0);
		for(int i=0;i<list.size();i++) {
			if(sdf.format(list.get(i).getDate_create()).equalsIgnoreCase(sdf.format(cal.getTime()))) {
				total=total.add(list.get(i).getPayment().getAll_money());
				
			}
		}
		return total;	
	}
	
	public static BigDecimal getTotalMoneyMoth3(List<Bill> list) {
		SimpleDateFormat sdf= new SimpleDateFormat("MMMM");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -3);
		BigDecimal total=new BigDecimal(0);
		for(int i=0;i<list.size();i++) {
			if(sdf.format(list.get(i).getDate_create()).equalsIgnoreCase(sdf.format(cal.getTime()))) {
				total=total.add(list.get(i).getPayment().getAll_money());
				
			}
		}
		return total;	
	}
	
	public static BigDecimal getTotalMoneyMoth4(List<Bill> list) {
		SimpleDateFormat sdf= new SimpleDateFormat("MMMM");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -4);
		BigDecimal total=new BigDecimal(0);
		for(int i=0;i<list.size();i++) {
			if(sdf.format(list.get(i).getDate_create()).equalsIgnoreCase(sdf.format(cal.getTime()))) {
				total=total.add(list.get(i).getPayment().getAll_money());
				
			}
		}
		return total;	
	}
	
	public static BigDecimal getTotalMoneyMoth5(List<Bill> list) {
		SimpleDateFormat sdf= new SimpleDateFormat("MMMM");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -5);
		BigDecimal total=new BigDecimal(0);
		for(int i=0;i<list.size();i++) {
			if(sdf.format(list.get(i).getDate_create()).equalsIgnoreCase(sdf.format(cal.getTime()))) {
				total=total.add(list.get(i).getPayment().getAll_money());
				
			}
		}
		return total;	
	}

}
