package cn.lx.jk.web.job;

import java.util.List;

import cn.lx.jk.domain.Feedback;
import cn.lx.jk.service.FeedbackService;

/**将已解决，且时间超过一个月的意见状态设置为3
 * @author liuxuan
 *
 */
public class FeedbackJob {
	//注入feedbackService
	private FeedbackService feedbackService;

	public void setFeedbackService(FeedbackService feedbackService) {
		this.feedbackService = feedbackService;
	}
	/**
	 * 执行将以解决且时间超过三个月的意见反馈状态设置为3
	 */
	public void excute(){
		
		/*
		 * select *  from feedback_c  WHERE INPUT_TIME < add_months(sysdate,-1) AND STATE = 1;
		 * */
		//查询所有提出日期在三个月之前且已经解决的反馈意见
		String hql = "from Feedback where state = 1 and NPUT_TIME < add_months(sysdate,-3)";
		
		List<Feedback> list = feedbackService.find(hql, Feedback.class, null);
		
		if(list != null && list.size()>0){
			for(Feedback feedback :list){
				feedback.setState(2);
				feedbackService.saveOrUpdate(feedback);
			}
		}
	}
	
}
