package com.farouk.bengarssallah.quod.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

			/**
			 *  {@link Period} class encapsulates financial data associated
			 *  to a company financial statement for a given period.
			 * 
			 ***/

@SuppressWarnings("serial")
public class Period implements Serializable {
	
	             /**  Period reference **/
			private String reference ;
				
			  /** Period title **/
			private String title ;
			
			 /** Period  company **/
			private String company;
			
			  /** Period Company exchange **/
			private String exchange;
			
			   /** Period data unit **/
			private String unit;
			
			  /** Period associated Statement **/
			private Statement statement;
			
			  /** Period data  **/
			private Map<String, String> data;
			
			

			public String getReference() {
				return reference;
			}

			public void setReference(String reference) {
				this.reference = reference;
			}

			public String getTitle() {
				return title;
			}

			public void setTitle(String title) {
				this.title = title;
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

			public String getUnit() {
				return unit;
			}

			public void setUnit(String unit) {
				this.unit = unit;
			}

			public Statement getStatement() {
				return statement;
			}

			public void setStatement(Statement statement) {
				this.statement = statement;
			}

			public Map<String, String> getData() {
				           if(data == null){
				        	                   data = new HashMap();
				                           }
				return data;
			}

			public void setData(Map<String, String> data) {
				this.data = data;
			}
						
     }