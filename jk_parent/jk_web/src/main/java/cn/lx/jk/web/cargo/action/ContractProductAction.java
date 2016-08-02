package cn.lx.jk.web.cargo.action;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;

import cn.lx.jk.domain.ContractProduct;
import cn.lx.jk.domain.Factory;
import cn.lx.jk.service.ContractProductService;
import cn.lx.jk.service.FactoryService;
import cn.lx.jk.util.file.FileUtil;
import cn.lx.jk.utils.Page;
import cn.lx.jk.web.action.BaseAction;



public class ContractProductAction extends BaseAction implements ModelDriven<ContractProduct> {
	private ContractProduct model = new ContractProduct();
	
	public ContractProduct getModel() {
		return model;
	}
	
	//注入ContractProductService
	private ContractProductService contractProductService ;
	public void setContractProductService(ContractProductService contractProductService) {
		this.contractProductService = contractProductService;
	}
	//注入factoryService
	private FactoryService factoryService ;
	
	
	public void setFactoryService(FactoryService factoryService) {
		this.factoryService = factoryService;
	}

	//分页组件
	private Page page = new Page();
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	
	private File productImg;//用于封装货物照片
	
	private String productImgFileName;//用于封装文件名称
	private String productImgContentType;//用于封装文件MIME类型
	
	
	

	public void setProductImg(File productImg) {
		this.productImg = productImg;
	}
	public void setProductImgFileName(String productImgFileName) {
		this.productImgFileName = productImgFileName;
	}
	public void setProductImgContentType(String productImgContentType) {
		this.productImgContentType = productImgContentType;
	}
	/**
	 * 查看详情
	 * <input type="checkbox" name="id" value="100"/>
	 * model对象：  ContractProduct类型
	 *      id  ：100
	 */
	public String toview() throws Exception {
		//1.根据id,得到对象
		ContractProduct contractProduct = contractProductService.findContractProductById(model.getId());
		
		//2.放入栈顶
		super.push(contractProduct);
		
		//3.跳页面
		return "toview";
	}
	
	/**
	 * 进入新增页面
	 */
	public String tocreate() throws Exception {
		// 1.得到用于生产货物的厂家列表
		List<Factory> factoryList = factoryService.find("from Factory where state=1 and ctype='货物'", Factory.class);
		
		
		contractProductService.findContractProductByPage("from ContractProduct where contract.id=?", page, model.getContract().getId());
		super.push(page);
		page.setUrl("contractproductAction_tocreate");
		super.put("factoryList",factoryList);
		return "tocreate";
	}
	
	/**
	 * 保存
	 * 
	 */
	public String insert() throws Exception {
	
		//处理文件上传
		if(productImg !=null){
			model.setProductImage(productImgFileName);
			//获取文件路径
			String filePath = ServletActionContext.getServletContext().getRealPath("/")+"/ufiles/jquery/"+productImgFileName;
		
			File file = new File(filePath);
			if(!file.getParentFile().exists()){
				file.getParentFile().mkdirs();
			}
			//拷贝文件
			FileUtils.copyFile(productImg, file);
		}
		contractProductService.saveOrUpdate(model);
		return tocreate() ;
	}
	
	/**
	 *  进入更新页面
	 */
	public String toupdate() throws Exception {
		//1.加载当前要更新的对象
		ContractProduct ContractProduct = contractProductService.findContractProductById(model.getId());
		super.push(ContractProduct);
		//查询所有生产厂家
		List<Factory> factoryList = factoryService.findAll();
		super.put("factoryList", factoryList);
		return "toupdate";
	}
	
	/**
	 * 更新
	 * @return
	 * @throws Exception
	 *  <input type="hidden" name="id" value="4028827c4fb645b0014fb64668550000"/>
	 */
	public String update() throws Exception {
		//1.根据id,得到要更新的对象
		ContractProduct contractProduct = contractProductService.findContractProductById(model.getId());
		if(contractProduct != null){
			//更新相应的数据
			contractProduct.setFactoryName(model.getFactoryName());
			contractProduct.setProductNo(model.getProductNo());
			contractProduct.setProductImage(model.getProductImage());
			contractProduct.setCnumber(model.getCnumber()); 	
			contractProduct.setPackingUnit(model.getPackingUnit());
			contractProduct.setLoadingRate(model.getLoadingRate());    
			contractProduct.setBoxNum(model.getBoxNum());
			contractProduct.setPrice(model.getPrice());
			contractProduct.setOrderNo(model.getOrderNo()); 	
			contractProduct.setProductDesc(model.getProductDesc());
			contractProduct.setProductRequest(model.getProductRequest());
		}
		
		contractProductService.saveOrUpdate(contractProduct);
		return tocreate();
	}
	
	
	public String delete() throws Exception {
	
		contractProductService.delete(model.getId());
		
		return tocreate();
	}
	
}
