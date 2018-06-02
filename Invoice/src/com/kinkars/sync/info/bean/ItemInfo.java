package com.kinkars.sync.info.bean;

public class ItemInfo {
	private double item_quantity;
	private double item_price;
	private double item_discount_amount;
	private int ext_product_id;
    private int ext_tax_id;
    private int ext_unit_id;
    
	
    public int getExt_unit_id() {
		return ext_unit_id;
	}
	public void setExt_unit_id(int ext_unit_id) {
		this.ext_unit_id = ext_unit_id;
	}
	public double getItem_quantity() {
		return item_quantity;
	}
	public void setItem_quantity(double item_quantity) {
		this.item_quantity = item_quantity;
	}
	public double getItem_price() {
		return item_price;
	}
	public void setItem_price(double item_price) {
		this.item_price = item_price;
	}
	public double getItem_discount_amount() {
		return item_discount_amount;
	}
	public void setItem_discount_amount(double item_discount_amount) {
		this.item_discount_amount = item_discount_amount;
	}
	public int getExt_product_id() {
		return ext_product_id;
	}
	public void setExt_product_id(int ext_product_id) {
		this.ext_product_id = ext_product_id;
	}
	public int getExt_tax_id() {
		return ext_tax_id;
	}
	public void setExt_tax_id(int ext_tax_id) {
		this.ext_tax_id = ext_tax_id;
	}
}
