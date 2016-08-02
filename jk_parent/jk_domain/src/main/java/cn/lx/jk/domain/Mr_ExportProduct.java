package cn.lx.jk.domain;

/**用于封装修改页面传递来的数据
 * @author liuxuan
 *
 */
public class Mr_ExportProduct {
	private String mr_id;
	
	private String[] mr_changed;
	private Integer[] mr_cnumber;			
			
	private Double[] mr_grossWeight;			
	private Double[] mr_netWeight;		
	private Double[] mr_sizeLength;			
	private Double[] mr_sizeWidth;			
	private Double[] mr_sizeHeight;			
	private Double[] mr_exPrice;			//sales confirmation 中的价格（手填）			
	private Double[] mr_tax;
	public String getMr_id() {
		return mr_id;
	}
	public void setMr_id(String mr_id) {
		this.mr_id = mr_id;
	}
	
	public Integer[] getMr_cnumber() {
		return mr_cnumber;
	}
	public void setMr_cnumber(Integer[] mr_cnumber) {
		this.mr_cnumber = mr_cnumber;
	}
	public Double[] getMr_grossWeight() {
		return mr_grossWeight;
	}
	public void setMr_grossWeight(Double[] mr_grossWeight) {
		this.mr_grossWeight = mr_grossWeight;
	}
	public Double[] getMr_netWeight() {
		return mr_netWeight;
	}
	public void setMr_netWeight(Double[] mr_netWeight) {
		this.mr_netWeight = mr_netWeight;
	}
	public Double[] getMr_sizeLength() {
		return mr_sizeLength;
	}
	public void setMr_sizeLength(Double[] mr_sizeLength) {
		this.mr_sizeLength = mr_sizeLength;
	}
	public Double[] getMr_sizeWidth() {
		return mr_sizeWidth;
	}
	public void setMr_sizeWidth(Double[] mr_sizeWidth) {
		this.mr_sizeWidth = mr_sizeWidth;
	}
	public Double[] getMr_sizeHeight() {
		return mr_sizeHeight;
	}
	public void setMr_sizeHeight(Double[] mr_sizeHeight) {
		this.mr_sizeHeight = mr_sizeHeight;
	}
	public Double[] getMr_exPrice() {
		return mr_exPrice;
	}
	public void setMr_exPrice(Double[] mr_exPrice) {
		this.mr_exPrice = mr_exPrice;
	}
	public Double[] getMr_tax() {
		return mr_tax;
	}
	public void setMr_tax(Double[] mr_tax) {
		this.mr_tax = mr_tax;
	}
	public String[] getMr_changed() {
		return mr_changed;
	}
	public void setMr_changed(String[] mr_changed) {
		this.mr_changed = mr_changed;
	}


}
