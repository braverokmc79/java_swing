package com.mcaronics.dto;



public class AgriculturalDTO {
	
	private int idx; //인덱스
	private String branchName; //지점명
	private String temperatures; //기온
	private String humidity; //습도
	private String windDirection; //풍향
	private String averageWindSpeed; //평균풍속
	private String 	momentMaximumWindSpeed;	 //순간 최대풍속
	private String  precipitation;    //강수량
	private String  daylightHours; //일조 시간
	private String soilMoistureCurrent ; //토양수분 현재 상태
	private String soilMoistureLastYear; //토양수분 작년
	
	
	
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getTemperatures() {
		return temperatures;
	}
	public void setTemperatures(String temperatures) {
		this.temperatures = temperatures;
	}
	public String getHumidity() {
		return humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	public String getWindDirection() {
		return windDirection;
	}
	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}
	public String getAverageWindSpeed() {
		return averageWindSpeed;
	}
	public void setAverageWindSpeed(String averageWindSpeed) {
		this.averageWindSpeed = averageWindSpeed;
	}
	public String getMomentMaximumWindSpeed() {
		return momentMaximumWindSpeed;
	}
	public void setMomentMaximumWindSpeed(String momentMaximumWindSpeed) {
		this.momentMaximumWindSpeed = momentMaximumWindSpeed;
	}
	public String getPrecipitation() {
		return precipitation;
	}
	public void setPrecipitation(String precipitation) {
		this.precipitation = precipitation;
	}
	public String getDaylightHours() {
		return daylightHours;
	}
	public void setDaylightHours(String daylightHours) {
		this.daylightHours = daylightHours;
	}
	public String getSoilMoistureCurrent() {
		return soilMoistureCurrent;
	}
	public void setSoilMoistureCurrent(String soilMoistureCurrent) {
		this.soilMoistureCurrent = soilMoistureCurrent;
	}
	public String getSoilMoistureLastYear() {
		return soilMoistureLastYear;
	}
	public void setSoilMoistureLastYear(String soilMoistureLastYear) {
		this.soilMoistureLastYear = soilMoistureLastYear;
	}
	@Override
	public String toString() {
		return "AgriculturalDTO [idx=" + idx + ", branchName=" + branchName + ", temperatures=" + temperatures
				+ ", humidity=" + humidity + ", windDirection=" + windDirection + ", averageWindSpeed="
				+ averageWindSpeed + ", momentMaximumWindSpeed=" + momentMaximumWindSpeed + ", precipitation="
				+ precipitation + ", daylightHours=" + daylightHours + ", soilMoistureCurrent=" + soilMoistureCurrent
				+ ", soilMoistureLastYear=" + soilMoistureLastYear + "]";
	}
	
	

	
	
}
