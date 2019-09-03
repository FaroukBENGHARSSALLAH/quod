package com.farouk.bengarssallah.quod.model;

import java.util.LinkedList;

public class Stock {

	       private String name;
	       private String symbol;
	       private String price;
	       private String change;
	       private String percentage;
	       private String beta;
	       
	       private String marketCap;
	       private String sharesFloat;
	       private String sharesOutstanding;
	       private String sharesOwned;
	       
	       private String eps;
	       private String pe;
	       private String peg;
	       
	       private String epsEstimateCurrentYear;
	       private String epsEstimateNextQuarter;
	       private String epsEstimateNextYear;
	       
	       private String priceBook;
	       private String priceSales;
	       private String bookValuePerShare;
	       
	       private String revenue; 
	       private String EBITDA; 
	       private String oneYearTargetPrice;
	       
	       private String shortRatio;
	       private String roe;
	       
	       public String getBeta() {
			return beta;
		}


		public void setBeta(String beta) {
			this.beta = beta;
		}


		private LinkedList<StockData> stockDataList;
	       
	       
		public Stock() {}


		public String getName() {
			return name;
		}


		public LinkedList<StockData> getStockDataList() {
			return stockDataList;
		}


		public void setStockDataList(LinkedList<StockData> stockDataList) {
			this.stockDataList = stockDataList;
		}


		public void setName(String name) {
			this.name = name;
		}


		public String getSymbol() {
			return symbol;
		}


		public void setSymbol(String symbol) {
			this.symbol = symbol;
		}


		public String getPrice() {
			return price;
		}


		public void setPrice(String price) {
			this.price = price;
		}


		public String getChange() {
			return change;
		}


		public void setChange(String change) {
			this.change = change;
		}


		public String getPercentage() {
			return percentage;
		}


		public void setPercentage(String percentage) {
			this.percentage = percentage;
		}


		public String getMarketCap() {
			return marketCap;
		}


		public void setMarketCap(String marketCap) {
			this.marketCap = marketCap;
		}


		public String getSharesFloat() {
			return sharesFloat;
		}


		public void setSharesFloat(String sharesFloat) {
			this.sharesFloat = sharesFloat;
		}


		public String getSharesOutstanding() {
			return sharesOutstanding;
		}


		public void setSharesOutstanding(String sharesOutstanding) {
			this.sharesOutstanding = sharesOutstanding;
		}


		public String getSharesOwned() {
			return sharesOwned;
		}


		public void setSharesOwned(String sharesOwned) {
			this.sharesOwned = sharesOwned;
		}


		public String getEps() {
			return eps;
		}


		public void setEps(String eps) {
			this.eps = eps;
		}


		public String getPe() {
			return pe;
		}


		public void setPe(String pe) {
			this.pe = pe;
		}


		public String getPeg() {
			return peg;
		}


		public void setPeg(String peg) {
			this.peg = peg;
		}


		public String getEpsEstimateCurrentYear() {
			return epsEstimateCurrentYear;
		}


		public void setEpsEstimateCurrentYear(String epsEstimateCurrentYear) {
			this.epsEstimateCurrentYear = epsEstimateCurrentYear;
		}


		public String getEpsEstimateNextQuarter() {
			return epsEstimateNextQuarter;
		}


		public void setEpsEstimateNextQuarter(String epsEstimateNextQuarter) {
			this.epsEstimateNextQuarter = epsEstimateNextQuarter;
		}


		public String getEpsEstimateNextYear() {
			return epsEstimateNextYear;
		}


		public void setEpsEstimateNextYear(String epsEstimateNextYear) {
			this.epsEstimateNextYear = epsEstimateNextYear;
		}


		public String getPriceBook() {
			return priceBook;
		}


		public void setPriceBook(String priceBook) {
			this.priceBook = priceBook;
		}


		public String getPriceSales() {
			return priceSales;
		}


		public void setPriceSales(String priceSales) {
			this.priceSales = priceSales;
		}


		public String getBookValuePerShare() {
			return bookValuePerShare;
		}


		public void setBookValuePerShare(String bookValuePerShare) {
			this.bookValuePerShare = bookValuePerShare;
		}


		public String getRevenue() {
			return revenue;
		}


		public void setRevenue(String revenue) {
			this.revenue = revenue;
		}


		public String getEBITDA() {
			return EBITDA;
		}


		public void setEBITDA(String eBITDA) {
			EBITDA = eBITDA;
		}


		public String getOneYearTargetPrice() {
			return oneYearTargetPrice;
		}


		public void setOneYearTargetPrice(String oneYearTargetPrice) {
			this.oneYearTargetPrice = oneYearTargetPrice;
		}


		public String getShortRatio() {
			return shortRatio;
		}


		public void setShortRatio(String shortRatio) {
			this.shortRatio = shortRatio;
		}


		public String getRoe() {
			return roe;
		}


		public void setRoe(String roe) {
			this.roe = roe;
		}


	
    
    }