package com.kinkars.sync.info.bean;

import java.util.List;

//{"invoice_id":"4","user_id":"1","ext_client_id":"6836","invoice_date_created":"2018-05-18","invoice_time_created":"13:29:09","invoice_item_subtotal":"6230.00","invoice_item_tax_total":"311.50","invoice_total":"6541.50","invoice_number":"4","items":[{"item_quantity":"1.00","item_price":"5230.00","item_discount_amount":null,"ext_product_id":"17521","ext_tax_id":"1181"},{"item_quantity":"1.00","item_price":"1000.00","item_discount_amount":null,"ext_product_id":"17482","ext_tax_id":"1181"}]}
public class InvoiceInfo {
	private int invoice_id;
	private int user_id;
	private int ext_client_id;
	private String invoice_date_created;
	private String invoice_time_created;
	private double invoice_item_subtotal;
	private double invoice_item_tax_total;
	private double invoice_total;
	private int invoice_number;
	private List<ItemInfo> items;
	public int getInvoice_id() {
		return invoice_id;
	}
	public void setInvoice_id(int invoice_id) {
		this.invoice_id = invoice_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getExt_client_id() {
		return ext_client_id;
	}
	public void setExt_client_id(int ext_client_id) {
		this.ext_client_id = ext_client_id;
	}
	public String getInvoice_date_created() {
		return invoice_date_created;
	}
	public void setInvoice_date_created(String invoice_date_created) {
		this.invoice_date_created = invoice_date_created;
	}
	public String getInvoice_time_created() {
		return invoice_time_created;
	}
	public void setInvoice_time_created(String invoice_time_created) {
		this.invoice_time_created = invoice_time_created;
	}
	public double getInvoice_item_subtotal() {
		return invoice_item_subtotal;
	}
	public void setInvoice_item_subtotal(double invoice_item_subtotal) {
		this.invoice_item_subtotal = invoice_item_subtotal;
	}
	public double getInvoice_item_tax_total() {
		return invoice_item_tax_total;
	}
	public void setInvoice_item_tax_total(double invoice_item_tax_total) {
		this.invoice_item_tax_total = invoice_item_tax_total;
	}
	public double getInvoice_total() {
		return invoice_total;
	}
	public void setInvoice_total(double invoice_total) {
		this.invoice_total = invoice_total;
	}
	public int getInvoice_number() {
		return invoice_number;
	}
	public void setInvoice_number(int invoice_number) {
		this.invoice_number = invoice_number;
	}
	public List getItems() {
		return items;
	}
	public void setItems(List items) {
		this.items = items;
	}

}
