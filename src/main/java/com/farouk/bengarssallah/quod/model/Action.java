package com.farouk.bengarssallah.quod.model;

import java.io.Serializable;

public class Action implements Serializable {

	  private long id;
	  private String ticker;
	  private String name;
	  private String exchange;
	  private String sector;
	  
	  public Action() {
		  
	  }
	  
	public Action(long id, String ticker, String name, String exchange, String sector) {
		this.id = id;
		this.ticker = ticker;
		this.name = name;
		this.exchange = exchange;
		this.sector = sector;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	  
	  
	  
}
