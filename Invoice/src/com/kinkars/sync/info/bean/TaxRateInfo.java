package com.kinkars.sync.info.bean;
/*ext_tax_id=1tax_rate_name=GSTtax_rate_percent=2*/

public class TaxRateInfo {
 private int ext_tax_id;
 private String tax_rate_name;
 private double  tax_rate_percent;
 
public int getExt_tax_id() {
	return ext_tax_id;
}
public void setExt_tax_id(int ext_tax_id) {
	this.ext_tax_id = ext_tax_id;
}
public String getTax_rate_name() {
	return tax_rate_name;
}
public void setTax_rate_name(String tax_rate_name) {
	this.tax_rate_name = tax_rate_name;
}
public double getTax_rate_percent() {
	return tax_rate_percent;
}
public void setTax_rate_percent(double tax_rate_percent) {
	this.tax_rate_percent = tax_rate_percent;
}
 
 
}
