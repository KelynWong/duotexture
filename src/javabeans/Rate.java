package javabeans;

public class Rate {
	String rateType;
	Double rate;
	
	// default constructor
	public Rate() {
		
	}
	
	// constructor overload
	public Rate(String rateType, Double rate) {
		this.rateType = rateType;
		this.rate = rate;
	}
	
	// get rate type
	public String getRateType() {
		return rateType;
	}
	
	// get rate
	public Double getRate() {
		return rate;
	}
	
	// set rate type
	public void setRateType(String rateType) {
		this.rateType = rateType;
	}
	
	// set rate
	public void setRate(Double rate) {
		this.rate = rate;
	}
}
