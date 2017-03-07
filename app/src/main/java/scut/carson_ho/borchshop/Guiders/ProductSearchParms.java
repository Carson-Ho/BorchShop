/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package scut.carson_ho.borchshop.Guiders;


import android.os.Parcelable;

import java.io.Serializable;

/**
 * 查询参数
 * 
 * @author Tony
 * 
 */
public class ProductSearchParms implements Serializable {

	/*
	 * 添加选型页面的表格参数到产品搜索的参数里，实现选型页面请求跳转到搜索匹配返回的页面的逻辑
	 */
	private String productType;
	private String material;
	private Float productWeight;
	private Float moduleLength;
	private Float moduleWidth;
	private Float moduleHeight;
	private Float area;
	private String ejector;
	private String locatingRing;
	private String screwType;
	private String powerType;
	private String name;
	private String phoneNumber;
	private String company;
	
	/*
	 *	根据headerId获取对应的类目 
	 */
	private Long headerId;
	
	private String prop;// urlQueryString

	private Double startPrice;

	private Double endPrice;

	private String orders;// 排序

	private Long categoryId;// 分类ID

	private Integer shopCategoryId;

	private Integer shopId;

	private Integer page;

	private String keyword;

	private Boolean isHasProd;
	
	private Boolean isSupportDist;

	private String tag;

	/** The provinceid. */
	private String provinceid;

	/** The cityid. */
	private String cityid;

	/** The areaid. */
	private String areaid;
	
	/** 是否推荐产品. */
	protected String commend;
	
	/** 是否热门产品. */
	protected String hot;
	
	private Integer pageSize;
	

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public Float getProductWeight() {
		return productWeight;
	}

	public void setProductWeight(Float productWeight) {
		this.productWeight = productWeight;
	}

	public Float getModuleLength() {
		return moduleLength;
	}

	public void setModuleLength(Float moduleLength) {
		this.moduleLength = moduleLength;
	}

	public Float getModuleWidth() {
		return moduleWidth;
	}

	public void setModuleWidth(Float moduleWidth) {
		this.moduleWidth = moduleWidth;
	}

	public Float getModuleHeight() {
		return moduleHeight;
	}

	public void setModuleHeight(Float moduleHeight) {
		this.moduleHeight = moduleHeight;
	}

	public Float getArea() {
		return area;
	}

	public void setArea(Float area) {
		this.area = area;
	}

	public String getEjector() {
		return ejector;
	}

	public void setEjector(String ejector) {
		this.ejector = ejector;
	}

	public String getLocatingRing() {
		return locatingRing;
	}

	public void setLocatingRing(String locatingRing) {
		this.locatingRing = locatingRing;
	}

	public String getScrewType() {
		return screwType;
	}

	public void setScrewType(String screwType) {
		this.screwType = screwType;
	}

	public String getPowerType() {
		return powerType;
	}

	public void setPowerType(String powerType) {
		this.powerType = powerType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Double getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(Double startPrice) {
		this.startPrice = startPrice;
	}

	public Double getEndPrice() {
		return endPrice;
	}

	public void setEndPrice(Double endPrice) {
		this.endPrice = endPrice;
	}

	public String getOrders() {
		return orders;
	}

	public void setOrders(String orders) {
		this.orders = orders;
	}
//
//	public void appendParms(AttrParam param) {
//		if (param == null) {
//			return;
//		}
//		if (AppUtils.isBlank(prop)) {
//			prop = param.getQueryString();
//		} else {
//			prop = prop + ";" + param.getQueryString();
//		}
//	}

	

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public void setHasProd(Boolean isHasProd) {
		this.isHasProd = isHasProd;
	}
	
	

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getShopCategoryId() {
		return shopCategoryId;
	}

	public void setShopCategoryId(Integer shopCategoryId) {
		this.shopCategoryId = shopCategoryId;
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public Boolean isHasProd() {
		return isHasProd;
	}

	public String getProvinceid() {
		return provinceid;
	}

	public void setProvinceid(String provinceid) {
		this.provinceid = provinceid;
	}

	public String getCityid() {
		return cityid;
	}

	public void setCityid(String cityid) {
		this.cityid = cityid;
	}

	public String getAreaid() {
		return areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}

	public String getProp() {
		return prop;
	}

	public void setProp(String prop) {
		this.prop = prop;
	}

	public String getCommend() {
		return commend;
	}

	public void setCommend(String commend) {
		this.commend = commend;
	}

	public String getHot() {
		return hot;
	}

	public void setHot(String hot) {
		this.hot = hot;
	}

	public Boolean getIsHasProd() {
		return isHasProd;
	}

	public void setIsHasProd(Boolean isHasProd) {
		this.isHasProd = isHasProd;
	}

	public Boolean getIsSupportDist() {
		return isSupportDist;
	}

	public void setIsSupportDist(Boolean isSupportDist) {
		this.isSupportDist = isSupportDist;
	}

	public Long getHeaderId() {
		return headerId;
	}

	public void setHeaderId(Long headerId) {
		this.headerId = headerId;
	}
}
