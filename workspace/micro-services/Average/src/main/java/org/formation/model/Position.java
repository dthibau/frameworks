package org.formation.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Position {
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");

	private long id;
	private double latitude;
	private double longitude;
	private long timestamp;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Position id(long id) {
		this.setId(id);
		return this;
	}
	
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public Position latitude(double latitude) {
		this.setLatitude(latitude);
		return this;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public Position longitude(double longitude) {
		this.setLongitude(longitude);
		return this;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public Position timestamp(long timestamp) {
		this.setTimestamp(timestamp);
		return this;
	}
	
	@Override
	public String toString() {
		return "Position [id=" + id + ", latitude=" + latitude + ", longitude=" + longitude + ", timestamp=" + sdf.format(new Date(timestamp))
				+ "]";
	}
	
}
