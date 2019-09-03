package com.farouk.bengarssallah.quod.model;

import java.io.Serializable;
import java.util.List;

              /** 
               * {@link Statement} is a model class to integrate a single company financial statement.
               * All concrete Statement class must inherit from this abstract class
               * 
               ***/

@SuppressWarnings("serial")
public abstract class Statement implements Serializable  {

		  /**  Statement reference **/
		protected String reference ;
			
		 /** Statement company **/
		protected String company;
		
		  /** Statement company symbol **/
	   protected String companySymbol;
		
		  /** Statement Company exchange **/
		protected String exchange;
		
		  /** Statement unit **/
	   protected String unit;
		
		   /** Statement period data list  **/
		protected List<Period> periodList;

		public String getReference() {
			return reference;
		}

		public void setReference(String reference) {
			this.reference = reference;
		}

		public String getCompany() {
			return company;
		}

		public void setCompany(String company) {
			this.company = company;
		}

		public String getExchange() {
			return exchange;
		}

		public void setExchange(String exchange) {
			this.exchange = exchange;
		}

		  public String getCompanySymbol() {
			return companySymbol;
		}

		public void setCompanySymbol(String companySymbol) {
			this.companySymbol = companySymbol;
		}

		public String getUnit() {
			return unit;
		}

		public void setUnit(String unit) {
			this.unit = unit;
		}

		public List<Period> getPeriodList() {
			return periodList;
		}

		public void setPeriodList(List<Period> periodList) {
			this.periodList = periodList;
		}
	
   } 