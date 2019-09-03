package com.farouk.bengarssallah.quod.model;

import java.util.Map;

public class SectorData {
	
	private String name;
	private String date;
	private Map<String, String> data;
	
	public SectorData() {}
	
	public SectorData(String name, String date, Map<String, String> data) {
		 this.name = name;
		 this.date = date;
		 this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Map<String, String> getData() {
		return data;
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}
	

   }
