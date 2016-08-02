package cn.lx.jk.web.stat.action;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;

import cn.lx.jk.dao.common.SqlDao;
import cn.lx.jk.util.file.FileUtil;
import cn.lx.jk.web.action.BaseAction;

public class StatChartAction extends BaseAction {
	private SqlDao sqlDao;

	public void setSqlDao(SqlDao sqlDao) {
		this.sqlDao = sqlDao;
	}

	/**
	 * 统计工厂的销售情况
	 * 
	 * @return
	 * @throws Exception
	 */
	public String factorysale1() throws Exception {
		String sql = "select factory_name,sum(amount) s from CONTRACT_PRODUCT_C t group by factory_name order by s desc";
		// 执行sql返回结果集合
		List<String> list = excuteSQL(sql);
		// list中存储的元素，以两个为一组，第一个位为factory_name，第二个为sum(amount)
		// 组织数据
		String str = getPieXML(list);
		// 写入文件
		writeXML(str, "/stat/chart/factorysale");
		return "factorysale";
	}

	/**
	 * 使用json实现
	 * 
	 * @return
	 * @throws Exception
	 */
	public String factorysale() throws Exception {
		String sql = "select factory_name,sum(amount) s from CONTRACT_PRODUCT_C t group by factory_name order by s desc";
		// 执行sql返回结果集合
		List<String> list = excuteSQL(sql);
		String[] colors = { "#FF0F00", "#FF6600", "#FF9E01", "#FCD202", "#F8FF01", "#B0DE09", "#04D215", "#0D8ECF",
				"#0D52D1", "#2A0CD0", "#8A0CCF" };
		List<Map<String, Object>> mapList = new ArrayList();
		int j = 0;
		for (int i = 0; i < list.size(); i++) {
			if (j >= colors.length) {
				j = 0;
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("factoryName", list.get((i++)));
			map.put("TotalAmount", list.get(i));
			map.put("color", colors[(j++)]);
			mapList.add(map);
		}
		String chartDataList = JSON.toJSONString(mapList);

		request.put("chartDataList", chartDataList);
		return "factorysale";
	}

	/**
	 * 写入xml文件
	 * 
	 * @param str
	 * @throws FileNotFoundException
	 */
	private void writeXML(String str, String filePath) throws FileNotFoundException {
		String path = ServletActionContext.getServletContext().getRealPath("/") + filePath;
		FileUtil fileUtil = new FileUtil();
		fileUtil.createTxt(path, "data.xml", str, "UTF-8");
	}

	/**
	 * 获得饼形图数据
	 * 
	 * @param list
	 * @return
	 */
	private String getPieXML(List<String> list) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		stringBuilder.append("<pie>");
		// 返回前八个数据，其他的合一起
		Double sumOther = 0.0d;
		for (int i = 0; i < list.size(); i++) {
			if (i < 16) {
				stringBuilder.append("<slice title=\"" + list.get(i++) + "\">" + list.get(i) + "</slice>");
			} else {
				sumOther += Double.valueOf(list.get(++i));
			}

		}
		stringBuilder.append("<slice title=\"" + "其他" + "\">" + sumOther + "</slice>");
		stringBuilder.append("</pie>");
		return stringBuilder.toString();
	}

	/**
	 * 执行sql语句，返回结果集
	 * 
	 * @param sql
	 * @return
	 */
	private List<String> excuteSQL(String sql) {
		// 准备数据
		List<String> list = sqlDao.executeSQL(sql);
		return list;
	}

	/**
	 * 统计货物的销售情况,使用json实现
	 * 
	 * @return
	 * @throws Exception
	 */
	public String productsale() throws Exception {
		String sql = "select b.* from (select product_no,sum(amount) s from CONTRACT_PRODUCT_C "
				+ "t group by product_no order by s desc) b" + " where rownum<6";
		List<String> list = excuteSQL(sql);
		List<Map<String, Object>> mapList = new ArrayList();

		for (int i = 0; i < list.size(); i++) {

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("product_no", list.get((i++)));
			map.put("TotalAmount", list.get(i));

			mapList.add(map);
		}
		String chartDataList = JSON.toJSONString(mapList);

		request.put("chartDataList", chartDataList);
		
		return "productsale";
	}

	/**
	 * 统计货物的销售情况
	 * 
	 * @return
	 * @throws Exception
	 */
	public String productsale1() throws Exception {
		String sql = "select b.* from (select product_no,sum(amount) s from CONTRACT_PRODUCT_C "
				+ "t group by product_no order by s desc) b" + " where rownum<6";
		List<String> list = excuteSQL(sql);
		String str = getLineXML(list);
		writeXML(str, "/stat/chart/productsale");

		return "productsale";
	}

	private String getLineXML(List<String> list) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		stringBuilder.append("<chart>");
		stringBuilder.append("<series>");
		// 组装横坐标数据
		int j = 0;
		for (int i = 0; i < list.size(); i++) {
			stringBuilder.append("<value xid=\"" + (j++) + "\">" + list.get(i) + "</value>");
			i++;
		}
		stringBuilder.append("</series>");
		stringBuilder.append("<graphs>");
		stringBuilder.append("<graph gid=\"30\" color=\"#FFCC00\" gradient_fill_colors=\"#111111, #1A897C\">");
		// 拼装纵坐标数据
		j = 0;
		for (int i = 0; i < list.size(); i++) {
			i++;
			stringBuilder.append("<value xid=\"" + (j++) + "\" description=\"\" url=\"\">" + list.get(i) + "</value>");
		}
		stringBuilder.append("</graph>");
		stringBuilder.append("</graphs>");
		stringBuilder.append("<labels> <label lid=\"0\"><x>0</x><y>20</y><rotate></rotate> "
				+ "<width></width><align>center</align><text_color></text_color><text_size>"
				+ "</text_size><text><![CDATA[<b>产品销售排行</b>]]></text>      " + "  </label></labels></chart>");
		return stringBuilder.toString();
	}

	/**
	 * 统计系统的在线访问压力
	 * 
	 * @return
	 * @throws Exception
	 */
	public String onlineinfo1() throws Exception {
		String sql = "select a.a1,nvl(b.c,0) from online_info_t a left join (select to_char(login_time,'HH24') a1, count(*) c from login_log_p group by to_char(login_time,'HH24')) b on (a.a1= b.a1) order by a.a1";
		List<String> list = excuteSQL(sql);
		String str = getLineXML(list);
		writeXML(str, "/stat/chart/onlineinfo");
		return "onlineinfo";
	}

	/**
	 * 统计系统的在线访问压力,使用json实现
	 * 
	 * @return
	 * @throws Exception
	 */
	public String onlineinfo() throws Exception {
		String sql = "select a.a1,nvl(b.c,0) from online_info_t a left join (select to_char(login_time,'HH24') a1, count(*) c from login_log_p group by to_char(login_time,'HH24')) b on (a.a1= b.a1) order by a.a1";
		List<String> list = excuteSQL(sql);
		List<Map<String, Object>> mapList = new ArrayList();

		for (int i = 0; i < list.size(); i++) {

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("hour", list.get((i++)));
			map.put("num", list.get(i));
			map.put("color", "#000000");
			mapList.add(map);
		}
		String chartDataList = JSON.toJSONString(mapList);
		System.out.println(chartDataList);
		request.put("chartDataList", chartDataList);

		return "onlineinfo";
	}

	/**
	 * 市场价排名
	 * 
	 * @return
	 * @throws Exception
	 */
	
	  public String priceInfo() throws Exception {
	 
	 // 执行sql语句查询出排名 
		String sql =
				  "select * from (select factory_name,price from product_c order by price desc) where rownum > 0 and rownum <= 10"
				  ; 
		List<String> list = excuteSQL(sql);
	  
		String[] colors = new String[] { "#FF0FF0", "#FF6600", "#FF9E01",
							"#FCD202", "#F8FF01", "#B0DE09", "#04D215", "#0D8ECF", "#0D52D1",
							"#2A0CD0" };
	  // 转成json数据
		StringBuilder sb = new StringBuilder();
	  
		sb.append("[");
	 
		int j = 0; 
		for (int i = 0; i < list.size(); i++) { 
			sb.append("{\"factoryName\": \"").append(list.get(i)).append("\","); 
			i++;
			sb.append("\"price\":\"").append(list.get(i)).append("\",");
			sb.append("\"color\": \"").append(colors[j]).append("\"},"); 
			j++; 
		}
	  
	   sb.delete(sb.length() - 1, sb.length());
	  
	   sb.append("]");
	  
	   super.put("priceInfo", sb.toString());
	   System.out.println(sb.toString());
	   return "priceInfo"; 
	}
	 
	/**
		 * IP历史纪录
		 * 
		 * @return
		 * @throws Exception
		 */
	
	  public String ipInfo() throws Exception {
	  
	     // 执行sql语句查询出排名
		  String sql = "select * from (select ip_address,count(ip_address) c from login_log_p group by ip_address order by c desc) where rownum > 0 and rownum <= 10";
		 
		  List<String> list = excuteSQL(sql); 
		  List<Map<String, Object>> mapList= new ArrayList();
		  
		  for (int i = 0; i < list.size(); i++) {
		  
		  Map<String, Object> map = new HashMap<String, Object>();
		  map.put("ipAddress", list.get((i++))); map.put("c", list.get(i));
		  
		  mapList.add(map); } String ipJSON = JSON.toJSONString(mapList);
		  
		  request.put("ipJSON", ipJSON);
		  
		  return "ipInfo"; 
	 }
	 

	/**
	 * 市场价排名
	 * 
	 * @return
	 * @throws Exception
	 */
	/*public String priceInfo() throws Exception {
		
		// 执行sql语句查询出排名
		String sql = "select * from (select product_no,price from product_c order by price desc) where rownum > 0 and rownum <= 10";
		List<String> list = excuteSQL(sql);
		
		 * { "country": "USA", "visits": 4025, "color": "#FF0F00" },
		 
		// 转成xml数据
		StringBuilder sb = new StringBuilder();

		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<chart>");
		sb.append("<series>");

		int j = 0;
		for (int i = 0; i < list.size(); i++) {
			sb.append("<value xid=\"" + j + "\">" + list.get(i) + "/62180</value>");
			j++;
			i++;
		}

		sb.append("</series>");
		sb.append("<graphs>");
		sb.append("<graph gid=\"30\" color=\"#FFCC00\" gradient_fill_colors=\"#111111, #1A897C\">");

		int p = 0;
		for (int i = 0; i < list.size(); i++) {
			sb.append("<value xid=\"" + p + "\" description=\"\" url=\"\">" + list.get(++i) + "</value>");
			p++;
		}

		sb.append("</graph>");
		sb.append("</graphs>");
		sb.append("<labels>");
		sb.append("<label lid=\"0\">");
		sb.append("<x>0</x>");
		sb.append("<y>20</y>");
		sb.append("<rotate></rotate>");
		sb.append("<width></width>");
		sb.append("<align>center</align>");
		sb.append("<text_color></text_color>");
		sb.append("<text_size></text_size>");
		sb.append("<text><![CDATA[<b>市场价排名</b>]]></text>");
		sb.append("</label>");
		sb.append("</labels>");
		sb.append("</chart>");

		//写出到XML文件中
		String path = ServletActionContext.getServletContext().getRealPath("/") + "/stat/chart/price";
		FileUtil fileUtil = new FileUtil();
		fileUtil.createTxt(path, "data.xml", sb.toString(), "UTF-8");
	

		// 放入值栈
		return "priceInfo";
	}*/

	/**
	 * IP历史纪录
	 * 
	 * @return
	 * @throws Exception
	 */
	public String ipInfo1() throws Exception {

		// 执行sql语句查询出排名
		String sql = "select * from (select ip_address,count(ip_address) c from login_log_p group by ip_address order by c desc) where rownum > 0 and rownum <= 10";
		List<String> list = excuteSQL(sql);

		// 转成xml数据 
		StringBuilder sb = new StringBuilder();

		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<pie>");

		for (int i = 0; i < list.size(); i++) {
			sb.append("<slice title=\"" + list.get(i) + "\">" + list.get(++i) + "</slice>");
		}

		sb.append("</pie>");

		//写出到XML文件中
		String path = ServletActionContext.getServletContext().getRealPath("/") + "/stat/chart/ip";
		FileUtil fileUtil = new FileUtil();
		fileUtil.createTxt(path, "data.xml", sb.toString(), "UTF-8");
		return "ipInfo";
	}
}
