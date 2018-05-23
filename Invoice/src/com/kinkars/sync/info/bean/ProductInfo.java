package com.kinkars.sync.info.bean;
public class ProductInfo {
	
    private int ext_product_id;
    private String product_sku;
    private String product_name;
    private String product_description;
    private String product_price;
    private String purchase_price;
    private String provider_name;
    private String tax_rate_id;
    private String unit_id;
    private String product_tariff;
    private String product_family_id;
	
    
    public String getProduct_family_id() {
		return product_family_id;
	}
	public void setProduct_family_id(String product_family_id) {
		this.product_family_id = product_family_id;
	}
	public int getExt_product_id() {
		return ext_product_id;
	}
	public void setExt_product_id(int ext_product_id) {
		this.ext_product_id = ext_product_id;
	}
	public String getProduct_sku() {
		return product_sku;
	}
	public void setProduct_sku(String product_sku) {
		this.product_sku = product_sku;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_description() {
		return product_description;
	}
	public void setProduct_description(String product_description) {
		this.product_description = product_description;
	}
	public String getProduct_price() {
		return product_price;
	}
	public void setProduct_price(String product_price) {
		this.product_price = product_price;
	}
	public String getPurchase_price() {
		return purchase_price;
	}
	public void setPurchase_price(String purchase_price) {
		this.purchase_price = purchase_price;
	}
	public String getProvider_name() {
		return provider_name;
	}
	public void setProvider_name(String provider_name) {
		this.provider_name = provider_name;
	}
	public String getTax_rate_id() {
		return tax_rate_id;
	}
	public void setTax_rate_id(String tax_rate_id) {
		this.tax_rate_id = tax_rate_id;
	}
	public String getUnit_id() {
		return unit_id;
	}
	public void setUnit_id(String unit_id) {
		this.unit_id = unit_id;
	}
	public String getProduct_tariff() {
		return product_tariff;
	}
	public void setProduct_tariff(String product_tariff) {
		this.product_tariff = product_tariff;
	}
}
