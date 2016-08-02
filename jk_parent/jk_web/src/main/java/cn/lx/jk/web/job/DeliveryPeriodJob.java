package cn.lx.jk.web.job;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;

import cn.lx.jk.domain.Contract;
import cn.lx.jk.service.ContractService;
import cn.lx.jk.utils.MailUtil;



public class DeliveryPeriodJob {
	private ContractService contractService;
	
	public void setContractService(ContractService contractService) {
		this.contractService = contractService;
	}

	/**
	 * 快要到交货日期进行发邮件提醒
	 * @throws InterruptedException 
	 */
	public void excute() throws InterruptedException{
		//查询交货日期在，两天之后的
		Calendar cal = Calendar.getInstance();
		//设置当前时间
		cal.setTime(new Date());
		//时间增加两天
		cal.add(Calendar.DAY_OF_MONTH, 1);
		//增加两天后的时间
		Date date = cal.getTime();               
		String hql = "from Contract where to_char(deliveryPeriod,'yyyy-MM-dd')=?";
		String dataTime = new SimpleDateFormat("yyyy-MM-dd").format(date);
		System.out.println(dataTime);
		List<Contract> list = contractService.findByCondition(hql,dataTime);
	
		if(list != null &&list.size()>0){
			
			/*for(final Contract contract:list){
				Thread.sleep(3000);
				Thread t = new Thread(new Runnable() {
					
				
					public void run() {
						
						try {
							MailUtil.sendMail("389351782@qq.com", "交货日期即将到来", "两天之后就是交货日期，请准备好，货物");
						} catch (Exception e) {
							 TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				});
				
				t.start();
			}*/
		}else{
			Thread.sleep(3000);
	
		/*	Thread t = new Thread(new Runnable() {
				
			
				public void run() {
					
					try {
						MailUtil.sendMail("389351782@qq.com", "交货日期没有快要到期的 ", "没有快要到期的货物，继续努力");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			});
			
			t.start();*/
		}
	}
}
