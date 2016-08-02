package cn.lx.jk.domain;


/**
 * @Description:	UserBaseInfoService接口
 * @Author:			传智播客 java学院	传智宋江
 * @Company:		http://java.itcast.cn
 * @CreateDate:		2016-7-17 23:34:14
 */

public class UserBaseInfo extends BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;	  	
	private String nickName;	//个人昵称		
	private String tel;			//手机号码
	private String nation;		//民族
	private String homeAddress;		//家庭住址	
	private String politicsStatus;	//政治面貌		
	private String speciality;		//个人特长	
	private String img;			  //照片

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	public String getNickName() {
		return this.nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}	
	
	public String getTel() {
		return this.tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}	
	
	public String getNation() {
		return this.nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}	
	
	public String getHomeAddress() {
		return this.homeAddress;
	}
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}	
	
	public String getPoliticsStatus() {
		return this.politicsStatus;
	}
	public void setPoliticsStatus(String politicsStatus) {
		this.politicsStatus = politicsStatus;
	}	
	
	public String getSpeciality() {
		return this.speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}	
	
	public String getImg() {
		return this.img;
	}
	public void setImg(String img) {
		this.img = img;
	}	
}
