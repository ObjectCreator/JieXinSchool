package cn.lx.jk.web.cargo.action;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import cn.lx.jk.domain.ContractProduct;
import cn.lx.jk.service.ContractProductService;
import cn.lx.jk.utils.DownloadUtil;
import cn.lx.jk.utils.MyCellStyle;
import cn.lx.jk.utils.UtilFuns;
import cn.lx.jk.web.action.BaseAction;



public class OutProductAction extends BaseAction{
	//注入船期
	private String inputDate;
	
	public void setInputDate(String inputDate) {
		this.inputDate = inputDate;
	}
	//注入Service
	private ContractProductService contractProductService;
	
	public void setContractProductService(ContractProductService contractProductService) {
		this.contractProductService = contractProductService;
	}

	/**进入出货表的打印页面
	 * @return
	 * @throws Exception
	 */
	public String toedit() throws Exception {
		
		return "toedit";
	}
	
	/**生成出货表，使用新建工作簿
	 * @return
	 * @throws Exception
	 */
	/**
	 * @return
	 * @throws Exception
	 */
	public String print() throws Exception {
		//创建工作簿
		/*
		 * XSSFWorkbook表示使用2007以上版本
		 * HSSFWorkbook表示使用2003以上版本
		 * 
		 * */
		Workbook wb = new XSSFWorkbook();
		//创建工作表
		Sheet sheet = wb.createSheet();
		//设置列宽
		/**因为列宽的实际是n/256
		 * */
		sheet.setColumnWidth(0, 6*256);
		sheet.setColumnWidth(1, 26*256);
		sheet.setColumnWidth(2, 12*256);
		sheet.setColumnWidth(3, 30*256);
		sheet.setColumnWidth(4, 12*256);
		sheet.setColumnWidth(5, 15*256);
		sheet.setColumnWidth(6, 10*256);
		sheet.setColumnWidth(7, 10*256);
		sheet.setColumnWidth(8, 10*256);
		//初始化行列索引
		int rowNo = 0;
		int cellNo = 1;
		//创建行对象
		Row nRow = sheet.createRow(++rowNo);
		//创建单元格对象
		Cell nCell = nRow.createCell(cellNo);
		//设置行高
		nRow.setHeightInPoints(36f);
		/**横向合并单元格
		 * 需要传入  CellRangeAddress对象 (开始行，结束行，开始列，结束列)
		 * */

		sheet.addMergedRegion(new CellRangeAddress(rowNo, rowNo, cellNo, cellNo+8));
		String fileName  = inputDate.replace("-0", "-").replace("-", "年")+"月份出货表";
		//设置标题单元格的内容  inputDate 2012-06 变为2012年6
		nCell.setCellValue(fileName);
		//设置标题单元格的样式
		nCell.setCellStyle(MyCellStyle.bigTitle(wb));
		
		//处理小标题行，获取行对象
		nRow = sheet.createRow(++rowNo);
		nRow.setHeightInPoints(27f);
		String[] tittles ={"客户","订单号","货号","数量","工厂"	,"工厂交期","船期","贸易条款"};
		
		for(String tittle:tittles){
			nCell = nRow.createCell(cellNo++);
			nCell.setCellValue(tittle);
			nCell.setCellStyle(MyCellStyle.title(wb));
		}
		//查询数据库获得响应的货物的值
		String hql = "from ContractProduct where to_char(contract.shipTime,'yyyy-MM-dd') like '"+inputDate+"%'";
		List<ContractProduct> list = contractProductService.findByCondition(hql,ContractProduct.class,null);
		//遍历所有货物，填写excel表格
		for(ContractProduct contractProduct:list){
		
			//行对象
			nRow = sheet.createRow(++rowNo);
			nRow.setHeightInPoints(24f);
			cellNo = 1;
			//客户单元格
			nCell = nRow.createCell(cellNo++);
			nCell.setCellValue(contractProduct.getContract().getCustomName());
			nCell.setCellStyle(MyCellStyle.text(wb));
			//订单号单元格
			nCell = nRow.createCell(cellNo++);
			nCell.setCellValue(contractProduct.getContract().getContractNo());
			nCell.setCellStyle(MyCellStyle.text(wb));
			//货号单元格
			nCell = nRow.createCell(cellNo++);
			nCell.setCellValue(contractProduct.getProductNo());
			nCell.setCellStyle(MyCellStyle.text(wb));
			//数量单元格
			nCell = nRow.createCell(cellNo++);
			nCell.setCellValue(contractProduct.getCnumber());
			nCell.setCellStyle(MyCellStyle.text(wb));
			//工厂单元格
			nCell = nRow.createCell(cellNo++);
			nCell.setCellValue(contractProduct.getFactoryName());
			nCell.setCellStyle(MyCellStyle.text(wb));
			//工厂交期单元格
			nCell = nRow.createCell(cellNo++);
			//将时间转换为字符串
			nCell.setCellValue(UtilFuns.dateTimeFormat(contractProduct.getContract().getDeliveryPeriod()));
			nCell.setCellStyle(MyCellStyle.text(wb));
			//船期单元格
			nCell = nRow.createCell(cellNo++);
			nCell.setCellValue(UtilFuns.dateTimeFormat(contractProduct.getContract().getShipTime()));
			nCell.setCellStyle(MyCellStyle.text(wb));
			//贸易条款单元格
			nCell = nRow.createCell(cellNo++);
			nCell.setCellValue(contractProduct.getContract().getTradeTerms());
			nCell.setCellStyle(MyCellStyle.text(wb));
			
		}
		
		//文件下载
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		wb.write(os);
		DownloadUtil.download(os, ServletActionContext.getResponse(), fileName+".xlsl");
		return NONE;
	}
	/**生成出货表，使用已存在模版
	 * @return
	 * @throws Exception
	 */
	public String print1() throws Exception {
		//创建工作簿
		/*
		 * XSSFWorkbook表示读取2007以上版本
		 * HSSFWorkbook表示读取2003以上版本
		 * 
		 * */
		//获取模版文件路径
		String path = ServletActionContext.getServletContext().getRealPath("/");
		System.out.println(path);
		path = path+"/make/xlsprint/tOUTPRODUCT.xls"; 
		System.out.println(path);
		//获取模版文件读取流
		InputStream is = new FileInputStream(path);
		System.out.println("isis"+is);
		//打开模版文件
		Workbook wb = new HSSFWorkbook(is);
		System.out.println("ssssss1");
		//读取指定工作表
		Sheet sheet = wb.getSheetAt(0);
		Row nRow = null;
		Cell nCell = null;
		//初始化行列索引
		int rowNo = 0;
		int cellNo = 1;
		//读取行对象
		nRow = sheet.getRow(rowNo++);
		//读取单元格对象
		nCell = nRow.getCell(cellNo);
		//设置行高
		nRow.setHeightInPoints(36f);
		
		String fileName  = inputDate.replace("-0", "-").replace("-", "年")+"月份出货表";
		//设置标题单元格的内容  inputDate 2012-06 变为2012年6
		nCell.setCellValue(fileName);
		
		//从第三行开始读取样式
		rowNo++;
		
		//读取第三行对象 rowNo =2;
		nRow = sheet.getRow(rowNo);
		
		//依次读取单元格样式	
		//读取客户单元格样式
		CellStyle customerCellStyle = nRow.getCell(cellNo++).getCellStyle();
		//读取订单号单元格样式
		CellStyle orderNoCellStyle = nRow.getCell(cellNo++).getCellStyle();
		//读取货号单元格样式
		CellStyle productNoCellStyle = nRow.getCell(cellNo++).getCellStyle();
		//读取数量单元格样式
		CellStyle numCellStyle = nRow.getCell(cellNo++).getCellStyle();
		//读取工厂单元格样式
		CellStyle factoryCellStyle = nRow.getCell(cellNo++).getCellStyle();
		//读取工厂交期单元格样式
		CellStyle deliveryPeriodCellStyle = nRow.getCell(cellNo++).getCellStyle();
		//读取船期单元格样式
		CellStyle shipTimeCellStyle = nRow.getCell(cellNo++).getCellStyle();
		//读取贸易条款单元格样式
		CellStyle tradeCellStyle = nRow.getCell(cellNo++).getCellStyle();
		//查询数据库获得响应的货物的值
		String hql = "from ContractProduct where to_char(contract.shipTime,'yyyy-MM-dd') like '"+inputDate+"%'";
		List<ContractProduct> list = contractProductService.findByCondition(hql,ContractProduct.class,null);
		
		//遍历所有货物，填写excel表格
		for(ContractProduct contractProduct:list){
		
			//行对象
			nRow = sheet.createRow(rowNo++);
			nRow.setHeightInPoints(24f);
			cellNo = 1;
			//客户单元格
			nCell = nRow.createCell(cellNo++);
			nCell.setCellValue(contractProduct.getContract().getCustomName());
			nCell.setCellStyle(customerCellStyle);
			//订单号单元格
			nCell = nRow.createCell(cellNo++);
			nCell.setCellValue(contractProduct.getContract().getContractNo());
			nCell.setCellStyle(orderNoCellStyle);
			//货号单元格
			nCell = nRow.createCell(cellNo++);
			nCell.setCellValue(contractProduct.getProductNo());
			nCell.setCellStyle(productNoCellStyle);
			//数量单元格
			nCell = nRow.createCell(cellNo++);
			nCell.setCellValue(contractProduct.getCnumber());
			nCell.setCellStyle(numCellStyle);
			//工厂单元格
			nCell = nRow.createCell(cellNo++);
			nCell.setCellValue(contractProduct.getFactoryName());
			nCell.setCellStyle(factoryCellStyle);
			//工厂交期单元格
			nCell = nRow.createCell(cellNo++);
			nCell.setCellValue(UtilFuns.dateTimeFormat(contractProduct.getContract().getDeliveryPeriod()));
			nCell.setCellStyle(deliveryPeriodCellStyle);
			//船期单元格
			nCell = nRow.createCell(cellNo++);
			nCell.setCellValue(UtilFuns.dateTimeFormat(contractProduct.getContract().getShipTime()));
			nCell.setCellStyle(shipTimeCellStyle);
			//贸易条款单元格
			nCell = nRow.createCell(cellNo++);
			nCell.setCellValue(contractProduct.getContract().getTradeTerms());
			nCell.setCellStyle(tradeCellStyle);
		}
		
		//文件下载
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		wb.write(os);
		DownloadUtil.download(os, ServletActionContext.getResponse(), fileName+".xls");
		return NONE;
	}
}
