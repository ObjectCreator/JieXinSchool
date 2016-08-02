package cn.lx.jk.web.action;

import cn.lx.jk.web.action.BaseAction;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import sun.misc.BASE64Encoder;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import cn.lx.jk.domain.Factory;
import cn.lx.jk.domain.Product;
import cn.lx.jk.domain.User;
import cn.lx.jk.service.FactoryService;
import cn.lx.jk.service.ProductService;
import cn.lx.jk.utils.Page;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @Description:	ProductService接口
 * @Author:			传智播客 java学院	传智宋江
 * @Company:		http://java.itcast.cn
 * @CreateDate:		2016-7-16 11:48:51
 */

public class ProductAction extends BaseAction implements ModelDriven<Product> {
	//注入service
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	private FactoryService factoryService;
	
	public void setFactoryService(FactoryService factoryService) {
		this.factoryService = factoryService;
	}
	//model驱动
	private Product model = new Product();
	public Product getModel() {
		return this.model;
	}
	
	//作为属性驱动，接收并封装页面参数
	private Page page = new Page();			//封装页面的参数，主要当前页参数
	public void setPage(Page page) {
		this.page = page;
	}

	//处理图片上传
	private File img;
	private String imgFileName;
	private String imgContentType;
	
	public void setImg(File img) {
		this.img = img;
	}

	
	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}


	public void setImgContentType(String imgContentType) {
		this.imgContentType = imgContentType;
	}

	//列表展示
	public String list(){
		String hql = "from Product where state !=-1 order by state";			//查询所有内容
		//给页面提供分页数据
		page.setUrl("productAction_list");		//配置分页按钮的转向链接
		page = productService.findPage(hql, page, Product.class, null);
		super.put("page", page);
		
		return "plist";						//page list
	}
	
	//转向新增页面
	public String tocreate(){
		//准备数据
		List<Product> productList = productService.productList();
		super.put("productList", productList);		//页面就可以访问productList
		//查询所有启用的的厂家
		List<Factory> factoryList = factoryService.find("from Factory where state = 1", Factory.class, null);
		super.put("factoryList", factoryList);
		return "pcreate";
	}
	
	//新增保存
	public String insert() throws IOException{
		System.out.println("状态"+imgFileName);
		//处理图片上传
		if(img !=null){
			
			model.setProductImage(imgFileName);
			//获取文件路径
			String filePath = ServletActionContext.getServletContext().getRealPath("/")+"/ufiles/jquery/"+imgFileName;
		
			File file = new File(filePath);
			if(!file.getParentFile().exists()){
				file.getParentFile().mkdirs();
			}
		
			//拷贝文件
			FileUtils.copyFile(img, file);
		}
		model.setCreateTime(new Date());
		User user = super.getCurUser();
		model.setCreateBy(user.getId());
		model.setCreateDept(user.getDept().getId());
		productService.saveOrUpdate(model);
		return "alist";			//返回列表，重定向action_list
	}

	//转向修改页面
	public String toupdate(){
		
				
		//准备修改的数据
		Product obj = productService.get(Product.class, model.getId());
		super.push(obj);
		//查询所有启用的的厂家
		List<Factory> factoryList = factoryService.find("from Factory where state = 1 order by state", Factory.class, null);
		super.put("factoryList", factoryList);
		return "pupdate";
	}
	
	//修改保存
	public String update() throws IOException{
		Product product = productService.get(Product.class, model.getId());
		if(product != null){
			//设置修改的属性，根据业务去掉自动生成多余的属性
			product.setProductNo(model.getProductNo());
			
			product.setDescription(model.getDescription());
			product.setFactory(model.getFactory());
			product.setFactoryName(model.getFactoryName());
			product.setPrice(model.getPrice());
			product.setSizeLenght(model.getSizeLenght());
			product.setSizeWidth(model.getSizeWidth());
			product.setSizeHeight(model.getSizeHeight());
			product.setColor(model.getColor());
			product.setPacking(model.getPacking());
			product.setPackingUnit(model.getPackingUnit());
			
		
			product.setRemark(model.getRemark());
		
			product.setState(model.getState());
			//处理图片上传
			if(img !=null){
				String old = product.getProductImage();
				String path = ServletActionContext.getServletContext().getRealPath("/")+"/ufiles/jquery/";
				String oldPath = path+old;
				File oldFile = new File(oldPath);
				if(!oldFile.getParentFile().exists()){
					oldFile.getParentFile().mkdirs();
				}
				//如果文件存在就删除
				if(oldFile.exists()){
					oldFile.delete();
				}
				//尽心新文件读写
				product.setProductImage(imgFileName);
				//获取文件路径
				String filePath = ServletActionContext.getServletContext().getRealPath("/")+"/ufiles/jquery/"+imgFileName;

				File file = new File(filePath);
			
				//拷贝文件
				FileUtils.copyFile(img, file);
			}
			productService.saveOrUpdate(product);
		}
		
		
		return "alist";
	}
	
	//删除一条
	public String deleteById(){
		productService.deleteById(Product.class, model.getId());
		
		return "alist";
	}
	
	
	//删除多条
	public String delete(){
		productService.delete(Product.class, model.getId().split(", "));
		
		return "alist";
	}
	
	//查看
	public String toview(){
		Product obj = productService.get(Product.class, model.getId());
		super.push(obj);
	
		return "pview";			//转向查看页面
	}
	
	//实现图片下载
	String picName = null;
	public String download() throws Exception {
		
		Product product = productService.get(Product.class, model.getId());
		if(product !=null){
			picName = product.getProductImage();
		}
		return "download";
	}
	//一个流
	public InputStream getInputStream() throws Exception {
		//1.得到文件的绝对路径
		String path = ServletActionContext.getServletContext().getRealPath("/")+"/ufiles/jquery/"+picName;
	
		//2.产生文件对象
		File file = new File(path);
		if(file.exists()){
			return new FileInputStream(file);
		}
		return null;
	}
	//两个头     MIME类型
	public String getContentType() throws Exception {
		//根据文件名，得到文件的mime类型
		return ServletActionContext.getServletContext().getMimeType(picName);
	}
	//指定浏览器要使用打开一个新窗口，提供下载框的方式来实现文件下载
	public String getContentDisposition() throws Exception {
		String agent = ServletActionContext.getRequest().getHeader("user-agent");//得到请求的这个浏览器的版本
		return "attachment;filename="+encodeDownloadFilename(picName,agent);
	}
	/**
	 * 下载文件时，针对不同浏览器，进行附件名的编码
	 * @param filename 下载文件名
	 * @param agent 客户端浏览器
	 * @return 编码后的下载附件名
	 * @throws IOException
	 */
	public String encodeDownloadFilename(String filename, String agent) throws IOException{
		if(agent.contains("Firefox")){ // 火狐浏览器
			filename = "=?UTF-8?B?"+new BASE64Encoder().encode(filename.getBytes("utf-8"))+"?=";
		}else{ // IE及其他浏览器
			filename = URLEncoder.encode(filename,"utf-8");
		}
		return filename;
	}
}
