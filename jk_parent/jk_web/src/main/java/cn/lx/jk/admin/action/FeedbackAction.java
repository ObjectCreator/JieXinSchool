package cn.lx.jk.admin.action;

import java.util.List;

import com.opensymphony.xwork2.ModelDriven;

import cn.lx.jk.domain.Feedback;
import cn.lx.jk.domain.Type;
import cn.lx.jk.domain.User;
import cn.lx.jk.service.FeedbackService;
import cn.lx.jk.service.TypeService;
import cn.lx.jk.service.UserService;
import cn.lx.jk.utils.Page;
import cn.lx.jk.web.action.BaseAction;

/**
 * @Description:	FeedbackService接口
 * @Author:			传智播客 java学院	传智宋江
 * @Company:		http://java.itcast.cn
 * @CreateDate:		2016-7-16 12:24:47
 */

public class FeedbackAction extends BaseAction implements ModelDriven<Feedback> {
	//注入service
	private FeedbackService feedbackService;
	public void setFeedbackService(FeedbackService feedbackService) {
		this.feedbackService = feedbackService;
	}
	
	//model驱动
	private Feedback model = new Feedback();
	public Feedback getModel() {
		return this.model;
	}
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	private TypeService typeService;
	
	public void setTypeService(TypeService typeService) {
		this.typeService = typeService;
	}

	//作为属性驱动，接收并封装页面参数
	private Page page = new Page();			//封装页面的参数，主要当前页参数
	public void setPage(Page page) {
		this.page = page;
	}


	//列表展示
	public String list(){
		//查询状态不等于3的意见，3代表意见已回复且时间超过一个月
		String hql = "from Feedback o where 1=1 and state !=3  ";			//查询所有内容
		//根据用户 的权限查询所有意见  如果是普通员工就只能查看公开的意见，其他人可以查看所有意见
		User user = super.getCurUser();
		if(user.getUserInfo().getDegree()==4){
			hql+= "  and isShare=1" ;
		}
		//按时间进行排序
		hql+= " order by inputTime desc";
		System.err.println(hql);
		//给页面提供分页数据
		page.setUrl("feedbackAction_list");		//配置分页按钮的转向链接
		page = feedbackService.findPage(hql, page, Feedback.class, null);
		super.push(page);
		
		return "list";						//page list
	}
	
	//转向新增页面
	public String tocreate(){
		//查询所有sort为0的分类
		List<Type> typeList = typeService.find("from Type where typeSort =0 ", Type.class, null);
		super.put("typeList", typeList);
	
		return "pcreate";
	}
	
	//新增保存
	public String insert(){
		//获取当前登陆的用户
		User user = super.getCurUser();
		model.setCreateBy(user.getId());
		feedbackService.saveOrUpdate(model);
		
		return "alist";			//返回列表，重定向action_list
	}

	//转向回复页面
	public String toreply(){
		//准备数据
		//根据Id查询当前的反馈意见
		model = feedbackService.findById(Feedback.class,model.getId());
		
		super.push(model);
		List<User> userList  = userService.findAll();
		//查询所有 的用户并进行显示
		super.put("userList", userList);
		return "reply";
	}
	
	//修改保存
	public String update(){
		
		Feedback feedback = feedbackService.get(Feedback.class, model.getId());
	
		//设置修改的属性，根据业务去掉自动生成多余的属性
		System.out.println(model.getAnswerName());
		feedback.setAnswerBy(model.getAnswerBy());
		feedback.setAnswerTime(model.getAnswerTime());
		feedback.setSolveMethod(model.getSolveMethod());
		feedback.setAnswerName(model.getAnswerName());
		feedback.setResolution(model.getResolution());
		feedback.setDifficulty(model.getDifficulty());
		feedback.setIsShare(model.getIsShare());
		feedback.setState(model.getState());
		
		feedbackService.saveOrUpdate(feedback);
		
		return "alist";
	}
	
	//删除一条
	public String deleteById(){
		feedbackService.deleteById(Feedback.class, model.getId());
		
		return "alist";
	}
	
	
	//删除多条
	public String delete(){
		feedbackService.delete(Feedback.class, model.getId().split(", "));
		
		return "alist";
	}
	
	//查看
	public String toview(){
		Feedback obj = feedbackService.get(Feedback.class, model.getId());
		super.push(obj);
		
		return "pview";			//转向查看页面
	}
	
	/**查询我所创建的所有反馈
	 * @return
	 * @throws Exception
	 */
	public String myFeedback() throws Exception {
		User user = super.getCurUser();
		feedbackService.findPage("from Feedback where createBy=? ", page, Feedback.class, new Object[]{user.getId()});
		page.setUrl("feedbackAction_myFeedback");
		super.push(page);
		return "myList";
	}
}
