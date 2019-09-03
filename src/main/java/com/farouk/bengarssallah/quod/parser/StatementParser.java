package com.farouk.bengarssallah.quod.parser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.farouk.bengarssallah.quod.model.BalanceSheetStatement;
import com.farouk.bengarssallah.quod.model.CashFlowStatement;
import com.farouk.bengarssallah.quod.model.IncomeStatement;
import com.farouk.bengarssallah.quod.model.Period;


				/** 
				 * {@link StatementParser} integrates  APIs related to 
				 * financial statement data extraction.
				 * 
				 ***/

@Component
public class StatementParser {
	
	
		 private static final String income_type = "I3";
		 private static final String cash_flow_type = "C6";
		 private static final String balance_sheet_type = "2s";
		 
		 
		 
		 
	       /** 
			 * This method return the {@link IncomeStatement}. It parses all necessary statement data.
			 *  @param company  {@link String} , company ID
			 *  @param period  {@link String} , statement period, 'annual' or 'quarter'
			 *  @return {@link IncomeStatement }
			 ***/
	     
	     
	     
					  //                                               symbol
					 //    MAIN URL :             http://quotes.wsj.com/FB/financials/annual/
					 //    Statement :     
					//     URL Income Statement : main url + / income-statement, 
					//     URL Balance Sheet Statement : main url + / balance-sheet  , 
					//     URL CashFlow Statement : main url + /cash-flow
	     
	     public static IncomeStatement getIncomeStatement(String company, String period) throws Exception {
	    	                                     IncomeStatement income = null;
	    	                                     String symbol = company.substring(0, company.indexOf(":"));
											  	           // Getting IncomeStatement HTML Page
											     Document doc =  Jsoup.connect("http://quotes.wsj.com/" + symbol + "/financials/" + period + "/income-statement").userAgent("Mozilla").get();
										 
											     
											     Element companyID = doc.select("div.cr_quotesHeader").first();
											     String companyName = companyID.select("span.companyName").first().text();
											     String exchange = companyID.select("span.exchangeName").first().text();
											        
									                     // Income Statement 
											        {
											     String unitStr = doc.select("table.cr_dataTable").select("thead").
																   select("tr").select("th.fiscalYr").first().text(); 
											     String unit = unitStr.substring(unitStr.indexOf(". ") + 1, unitStr.length()).replace(".", "");
											     LinkedList<Period> periodList = new LinkedList();   	
											               { 
											            // Income Statement Periods
												 Elements elements = doc.select("table.cr_dataTable").select("thead").
														                 select("tr").select("th");
												 for(Element element : elements){
															                 String title = element.text();
															                 if(!element.hasClass("fiscalYr") && !title.equals("5-year trend") && !title.equals(unit) && !title.equals("5-qtr trend")){
																					                 Period periode = new Period();
																					                 periode.setReference(generatePeriodReference(symbol, title, income_type));
																					                 periode.setTitle(title);
																						             periode.setCompany(company);
																						             periode.setExchange(exchange);
																						             periode.setUnit(unit);
																						             periodList.add(periode);
															                                                       }
														                                                }
														     }
											                 {
											               // Income Statement Periods data
											     Elements elements = doc.select("table.cr_dataTable").select("tbody").
														                  select("tr");
											     for(Element element : elements){
											    	                      if(!element.hasClass("barPos") && !element.hasClass("barNeg")){
															    	                      String dataName = element.select("td").first().text();
															    	                      Iterator<Period> iterator = periodList.iterator();
															    	                      Elements dataElements = element.select("td");
															    	                      int i=1;
															    	                      while(iterator.hasNext()){
				                    	                                                                             iterator.next().getData().put(dataName, dataElements.get(i).text());
				                    	                                                                             i++;
															    	                                                      }
											                                                              }
											                                     }
											                 }
											     income = new IncomeStatement();
											     income.setReference(generateStatementReference(symbol, income_type));
												 income.setCompany(companyName);
												 income.setUnit(unit);
												 income.setExchange(exchange);
												 income.setCompanySymbol(symbol);
												     {
												 List<Period> list = new ArrayList<Period>();
												 for(int i = (periodList.size() -1); i > -1 ; i--){
													 list.add(periodList.get(i));
												           }
												 income.setPeriodList(list);
												      }
												 
											        }
										         return income;
	    	         }
	     
	     
	     
	     
	     
	     
	     
	     
	       /** 
			 * This method return the {@link BalanceSheetStatement}. It parses all necessary statement data.
			 *  @param company  {@link String} , company ID
			 *  @param period  {@link String} , statement period, 'annual' or 'quarter'
			 *  @return {@link BalanceSheetStatement }
			 ***/
	     
	     
	     public static BalanceSheetStatement getBalanceSheetStatement(String company, String period) throws Exception {
	    	                             BalanceSheetStatement balnceSheet = null;
	    	                             String symbol = company.substring(0, company.indexOf(":"));
									  	         // Getting BalanceSheetStatement HTML Page
									     Document doc =  Jsoup.connect("http://quotes.wsj.com/" + symbol + "/financials/" + period + "/balance-sheet").userAgent("Mozilla").get();
								 
									     
									     Element companyID = doc.select("div.cr_quotesHeader").first();
									     String companyName = companyID.select("span.companyName").first().text();
									     String exchange = companyID.select("span.exchangeName").first().text();
									        
										 									     
									       // BalanceSheet Statement 
									        {
									     String unitStr = doc.select("table.cr_dataTable").select("thead").
														   select("tr").select("th.fiscalYr").first().text(); 
									     String unit = unitStr.substring(unitStr.indexOf(". ") + 1, unitStr.length()).replace(".", "");
									     LinkedList<Period> periodList = new LinkedList();   	
									               { 
									            // BalanceSheet Statement Periods
										 Elements elements = doc.select("table.cr_dataTable").select("thead").
												                 select("tr").select("th");
										 for(Element element : elements){
													                 String title = element.text();
													                 if(!element.hasClass("fiscalYr") && !title.equals("5-year trend") && !title.equals(unit) && !title.equals("5-qtr trend")){
																			                 Period periode = new Period();
																			                 periode.setReference(generatePeriodReference(symbol, title, balance_sheet_type));
																			                 periode.setTitle(title);
																				             periode.setCompany(company);
																				             periode.setExchange(exchange);
																				             periode.setUnit(unit);
																				             periodList.add(periode);
													                                                       }
												                                                }
												     }
									                 {
									               // BalanceSheet Statement Periods data
									     Elements elements = doc.select("table.cr_dataTable").select("tbody").
												                  select("tr");
									     for(Element element : elements){
									    	                          if(!element.hasClass("barPos") && !element.hasClass("barNeg") && !element.hasClass("hide")){
																					    	  String dataName = element.select("td").first().text();
																    	                      Iterator<Period> iterator = periodList.iterator();
																    	                      Elements dataElements = element.select("td");
																    	                      int i=1;
																    	                      while(iterator.hasNext()){
											                                                                             iterator.next().getData().put(dataName, dataElements.get(i).text());
											                                                                             i++;
																    	                                                                          }
																					                                                  }
									                                                          }
									                  }
									     balnceSheet = new BalanceSheetStatement();
									     balnceSheet.setReference(generateStatementReference(symbol, balance_sheet_type));
										 balnceSheet.setCompany(companyName);
										 balnceSheet.setUnit(unit);
										 balnceSheet.setExchange(exchange);
										 balnceSheet.setCompanySymbol(symbol);
										     {
										 List<Period> list = new ArrayList<Period>();
										 for(int i = (periodList.size() -1); i > -1 ; i--){
											 list.add(periodList.get(i));
										           }
										 balnceSheet.setPeriodList(list);
										      }
							               }
								         return balnceSheet;
					}
	     
	     
	     
	     
	     
	     
	     
	       /** 
			 * This method return the {@link CashFlowStatement}. It parses all necessary statement data.
			 *  @param company  {@link String} , company ID
			 *  @param period  {@link String} , statement period, 'annual' or 'quarter'
			 *  @return {@link CashFlowStatement }
			 ***/
	     
	     
	     
	     public static CashFlowStatement getCashFlowStatement(String company, String period) throws Exception {
	    	                             CashFlowStatement cashFlow = null;
	    	                             String symbol = company.substring(0, company.indexOf(":"));
					            	               // Getting CashFlowStatement HTML Page
									     Document doc =  Jsoup.connect("http://quotes.wsj.com/" + symbol + "/financials/" + period + "/cash-flow").userAgent("Mozilla").get();
									     
									     Element companyID = doc.select("div.cr_quotesHeader").first();
									     String companyName = companyID.select("span.companyName").first().text();
									     String exchange = companyID.select("span.exchangeName").first().text();
									        		
									     
									           // CashFlow Statement 
									        {
									     String unitStr = doc.select("table.cr_dataTable").select("thead").
														   select("tr").select("th.fiscalYr").first().text(); 
									     String unit = unitStr.substring(unitStr.indexOf(". ") + 1, unitStr.length()).replace(".", "");
									     LinkedList<Period> periodList = new LinkedList();   	
									               { 
									            // CashFlow Statement Periods
										 Elements elements = doc.select("table.cr_dataTable").select("thead").
												                 select("tr").select("th");
										 for(Element element : elements){
													                 String title = element.text();
													                 if(!element.hasClass("fiscalYr") && !title.equals("5-year trend") && !title.equals(unit) && !title.equals("5-qtr trend")){
																			                 Period periode = new Period();
																			                 periode.setReference(generatePeriodReference(symbol, title, cash_flow_type));
																			                 periode.setTitle(title);
																				             periode.setCompany(company);
																				             periode.setExchange(exchange);
																				             periode.setUnit(unit);
																				             periodList.add(periode);
													                                                       }
												                                                }
												     }
									                 {
									               // CashFlow Statement Periods data
									     Elements elements = doc.select("table.cr_dataTable").select("tbody").
												                  select("tr");
									     for(Element element : elements){
									    	                          if(!element.hasClass("barPos") && !element.hasClass("barNeg") && !element.hasClass("hide")){
																					    	  String dataName = element.select("td").first().text();
																    	                      Iterator<Period> iterator = periodList.iterator();
																    	                      Elements dataElements = element.select("td");
																    	                      int i=1;
																    	                      while(iterator.hasNext()){
											                                                                             iterator.next().getData().put(dataName, dataElements.get(i).text());
											                                                                             i++;
																    	                                                      }
															    	                                               }
									                                           }
									                  }
						                 cashFlow = new CashFlowStatement();
						                 cashFlow.setReference(generateStatementReference(symbol, cash_flow_type));
										 cashFlow.setCompany(companyName);
										 cashFlow.setUnit(unit);
										 cashFlow.setExchange(exchange);
										 cashFlow.setCompanySymbol(symbol);
										   {
										 List<Period> list = new ArrayList<Period>();
										 for(int i = (periodList.size() -1); i > -1 ; i--){
											 list.add(periodList.get(i));
										           }
										 cashFlow.setPeriodList(list);
											}
								                     }
										 return cashFlow;
            }
	     
	     
	     
	     /** 
			 * It returns a {@link String} reference identifying the current {@link Period} instance.
			 *  @param symbol   {@link String}  , company symbol 
			 *	@param title   {@link String}  , period title
			 *	@param type   {@link String}  , period statement type 
			 *  @return {@link String}
			 **/
	     
	     protected static String generatePeriodReference(String symbol, String title, String period) {
					           String time = String.valueOf(Calendar.getInstance().get(Calendar.MILLISECOND));
					           return "p" + time + symbol.toLowerCase() + period + title;
	                     }
	     
	     
	     
	     /** 
			 * It returns a {@link String} reference identifying the current {@link Statement} instance.
			 *  @param symbol   {@link String}  , company symbol 
			 *  @param type   {@link String}  , statement type 
			 *  @return {@link String}
			 **/
	     
	     protected static String generateStatementReference(String symbol, String type) {
					           String time = String.valueOf(Calendar.getInstance().get(Calendar.MILLISECOND));
					           return "s" + time + symbol.toLowerCase() + type;
	                     }
							  
        }