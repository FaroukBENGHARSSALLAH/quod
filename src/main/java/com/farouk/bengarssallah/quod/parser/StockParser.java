package com.farouk.bengarssallah.quod.parser;

import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.springframework.stereotype.Component;

import com.farouk.bengarssallah.quod.model.Stock;

import yahoofinance.YahooFinance;

		/** 
		 * {@link StockParser} integrates some APIs related to 
		 * stock data extraction.
		 * 
		 ***/


@Component
public class StockParser {

	
	
	
		   /** 
			 * This method return a {@link Stock} object with the current related data.
			 *  @param company  {@link String} , company ID
			 *  @return {@link Stock }
			 ***/
	
	
	 public static Stock getStockData(String company) throws Exception {
		                 try{
						                 yahoofinance.Stock yStock = YahooFinance.get(company);
						                 Stock stock = new Stock();
						                 stock.setName(yStock.getName());
						                 stock.setSymbol(company);
						                 stock.setPrice(""+yStock.getQuote().getPrice());
						                 stock.setChange(""+yStock.getQuote().getChange());
						                 stock.setPercentage(""+yStock.getQuote().getChangeInPercent());
						                 return stock;
		                           }
		                 catch(Exception  e){}
		                 return null;
	         }
	 
	 
	         /** 
				 * This method return a {@link Stock} object with the current related extended data.
				 *  @param company  {@link String} , company ID
				 *  @return {@link Stock }
				 ***/
	 
	 public static Stock getExtendedStockData(String company) throws Exception {
			         try{
					                 yahoofinance.Stock yStock = YahooFinance.get(company);
					                 Stock stock = new Stock();
					                 stock.setName(yStock.getName());
					                 stock.setSymbol(company);
					                 stock.setPrice(""+yStock.getQuote().getPrice());
					                 stock.setChange(""+yStock.getQuote().getChange());
					                 stock.setPercentage(""+yStock.getQuote().getChangeInPercent());
					                 stock.setBookValuePerShare(""+yStock.getStats().getBookValuePerShare());
					                 stock.setEBITDA(""+yStock.getStats().getEBITDA());
					                 stock.setEps(""+yStock.getStats().getEps());
					                 stock.setEpsEstimateCurrentYear(""+yStock.getStats().getEpsEstimateCurrentYear());
					                 stock.setEpsEstimateNextQuarter(""+yStock.getStats().getEpsEstimateNextQuarter());
					                 stock.setEpsEstimateNextYear(""+yStock.getStats().getEpsEstimateNextYear());
					                 stock.setMarketCap(""+yStock.getStats().getMarketCap());
					                 stock.setOneYearTargetPrice(""+yStock.getStats().getOneYearTargetPrice());
					                 stock.setPe(""+yStock.getStats().getPe());
					                 stock.setPeg(""+yStock.getStats().getPeg());
					                 stock.setPriceBook(""+yStock.getStats().getPriceBook());
					                 stock.setPriceSales(""+yStock.getStats().getPriceSales());
					                 stock.setBookValuePerShare(""+yStock.getStats().getBookValuePerShare());
					                 stock.setOneYearTargetPrice(""+yStock.getStats().getOneYearTargetPrice());
					                 stock.setRevenue(""+yStock.getStats().getRevenue());
					                 stock.setRoe(""+yStock.getStats().getROE());
					                 stock.setShortRatio(""+yStock.getStats().getShortRatio());
					                 return stock;
			                   }
			         catch(Exception  e){}
			         return null;
			}
	 
	 
	          /** 
				 * This method return a {@link Stock} object with the current related data.
				 *  @param company  {@link String} , company ID
				 *  @return {@link Stock }
				 ***/
		
		
		 public static List<Stock> getListStockData(String[] companies) throws Exception {
			                 List<Stock> result = new LinkedList<Stock>();
			                 Map<String, yahoofinance.Stock> yStockMap = YahooFinance.get(companies);
			                 for (Map.Entry<String, yahoofinance.Stock> entry : yStockMap.entrySet()){
			                	 yahoofinance.Stock yStock = entry.getValue();
			                	 Stock stock = new Stock();
			                	 stock.setName(yStock.getName());
				                 stock.setSymbol(yStock.getSymbol());
				                 stock.setPrice(""+yStock.getQuote().getPrice());
				                 stock.setChange(""+yStock.getQuote().getChange());
				                 stock.setPercentage(""+yStock.getQuote().getChangeInPercent());
				                 result.add(stock);
			                 }
							return result;
		            }
	 
	 
	 
	 
	   /** 
		 * This method return a {@link Stock} object with the historical related data.
		 *  @param company  {@link String} , company ID
		 *  @return {@link Stock }
		 ***/
	 	 
	 
	 public static Stock getStockHistory(String company) throws Exception {
							 return null;
	           }
	 
	 
		 
		 /** 
			 * This method return a list of  {@link Stock} object with the index's data.
			 *  @return a list of   {@link Stock }
			 ***/
		
	 public static List getIndexData() throws Exception {
		                        List <Stock> indexData = new LinkedList<Stock>();
		                        try{
							    URL url = new URL("http://download.finance.yahoo.com/d/quotes.csv?s=^GSPC+^IXIC+^DJI&f=sl1d1tclohgv&e=.csv");
							    URLConnection urlConnection = url.openConnection();
							    Scanner scanner = new Scanner(urlConnection.getInputStream());
							    String[] indexNames  = new String[]{"NASDAQ", "S&P500", "Dow Jones"};
							    int index =  0;
							    while(scanner.hasNextLine()){
							     	        StringTokenizer tokenizer = new StringTokenizer(scanner.nextLine(), ",");  
							     	        Stock stock = new Stock();
							     	        stock.setSymbol(tokenizer.nextToken().replace("\"", ""));
									        stock.setName(indexNames[index]);
									        stock.setPrice(tokenizer.nextToken());
									        if(stock.getPrice().equals("N/A")){
									            	        continue;
									                   }
								            tokenizer.nextToken();
								            tokenizer.nextToken();
								            String[] change_percentage = tokenizer.nextToken().replace("\"", "").split(" - ");
								            if(change_percentage[0].equals("N/A")){
								            	            stock.setChange("N/A");
									                        stock.setPercentage("N/A");
								                     }
								            else {
											                stock.setChange(change_percentage[0]);
											                stock.setPercentage(change_percentage[1]);
								                     }
							                indexData.add(stock);
							                index ++;
							             }
							     scanner.close();
							            } catch(Exception e){
							    	        e.printStackTrace();
							         }
							      return indexData;
								       }
				           
	           
	 
				   /** 
					 *  It returns the {@link Stock} 's current BETA value
					 *  @param symbol   {@link String}  , company symbol 
					 *  @return {@link String}
					 **/
			  
		/**	  public static String getStockBETA(String symbol) throws Exception {
				                          try{
													  Document doc = Jsoup.connect("https://finance.yahoo.com/quote/" + symbol + "?p=" + symbol).userAgent("Mozilla").get();
										              return doc.select("td[data-test]").get(9).text();
				                                   }
				                          catch(Exception e){e.printStackTrace();}
				                          return null;
			                  }  **/
	 
  }