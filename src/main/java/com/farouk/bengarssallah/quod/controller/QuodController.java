package com.farouk.bengarssallah.quod.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.farouk.bengarssallah.quod.export.PDFExport;
import com.farouk.bengarssallah.quod.export.XLSXExport;
import com.farouk.bengarssallah.quod.model.Period;
import com.farouk.bengarssallah.quod.model.Statement;
import com.farouk.bengarssallah.quod.model.Stock;
import com.farouk.bengarssallah.quod.parser.StatementParser;
import com.farouk.bengarssallah.quod.parser.StockParser;

@Controller
public class QuodController {
	
	@Autowired
	private StockParser stockParser;
	
	@Autowired
	private StatementParser statementParser;
	

	
	private Statement statement;

	@RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
	public ModelAndView welcomePage() {
		ModelAndView model = new ModelAndView();
		int index = ThreadLocalRandom.current().nextInt(1, 7 + 1);
		model.addObject("index", index);
		model.setViewName("welcome");
		return model;
	}
	
	@RequestMapping(value = { "/company"}, method = RequestMethod.GET)
	public ModelAndView companyPage() {
		ModelAndView model = new ModelAndView();
		model.addObject("view", "company");
		try{
	       {
	   		//List indexListData = quod.fetchIndexData(new String[]{"^GSPC", "^IXIC" , "^DJI"}); 	
	          List indexListData = stockParser.getListStockData(new String[]{"^GSPTSE", "^GSPC", "^FCHI"}); 	
	   		model.addObject("index_data", indexListData);	
	   		    }
		model.setViewName("company");
		       }
		catch (Exception e) {
		    model.setViewName("error");
          }
		return model;
	}
	
	@RequestMapping(value = { "/accounting"}, method = RequestMethod.GET)
	public ModelAndView statementPage() {
		ModelAndView model = new ModelAndView();
		model.addObject("view", "accounting");
        try{
              {
		//List indexListData = quod.fetchIndexData(new String[]{"^GSPC", "^IXIC" , "^DJI"}); 	
       List indexListData = stockParser.getListStockData(new String[]{"^GSPTSE", "^GSPC", "^FCHI"}); 	
		model.addObject("index_data", indexListData);	
		    }
        model.setViewName("accounting");
              }catch (Exception e) {
			    model.setViewName("error");
              }
        return model;
	}
	

	
	    @RequestMapping(value = { "/querystatement"}, method = RequestMethod.GET)
		public ModelAndView getStatement(@RequestParam String company, 
				@RequestParam(defaultValue = "income") String type,
				@RequestParam(defaultValue = "annual") String period){
			            ModelAndView model = new ModelAndView();
						try {
						        if(!(company == null || company.length() == 0)){
														    Statement statement = fetchStatementData(company, type, period);
															model.addObject("statement", statement);
															setStatement(statement);
															    {
															LinkedList<String> keys = new LinkedList<String>();    	
															for(String key : statement.getPeriodList().get(0).getData().keySet()){
																                            keys.add(key);      
															                          }
															model.addObject("keys", keys);
															    }
															model.addObject("company", company);
															model.addObject("type", type);
															model.addObject("period", period);
															if(type.equals("income")){
																				   {
																				List<String> list = new LinkedList<String>();    	
																				List<String> list_organised = new LinkedList<String>();
																				for(Period perrt : statement.getPeriodList()){
																					          list.add(perrt.getTitle() + "_" + perrt.getData().get("Gross Income").replace(",", "."));
																                         }
																				for(int index = 0; index < statement.getPeriodList().size(); index++){
																					list_organised.add(list.get(index));
														                         }
																				model.addObject("list", list_organised);
																				    }
																				    {
																				List<String> list = new LinkedList<String>();    	
																				List<String> list_organised = new LinkedList<String>();
																				for(Period perrt : statement.getPeriodList()){
																					          list.add(perrt.getTitle() + "_" + perrt.getData().get("Sales/Revenue").replace(",", "."));
																                         }
																				for(int index = 0; index < statement.getPeriodList().size(); index++){
																					list_organised.add(list.get(index));
														                         }
																				model.addObject("list2", list_organised);
																				    }
																				    {
																				List<String> list = new LinkedList<String>();    	
																				List<String> list_organised = new LinkedList<String>();
																				for(Period perrt : statement.getPeriodList()){
																					          list.add(perrt.getTitle() + "_" + perrt.getData().get("Net Income").replace(",", "."));
																                         }
																				for(int index = 0; index < statement.getPeriodList().size(); index++){
																					list_organised.add(list.get(index));
														                            }
																				model.addObject("list3", list_organised);
																				    }
																				    {
																				List<String> list = new LinkedList<String>();    	
																				List<String> list_organised = new LinkedList<String>();
																				for(Period perrt : statement.getPeriodList()){
																					          list.add(perrt.getTitle() + "_" + perrt.getData().get("Gross Interest Expense").replace(",", "."));
																                         }
																				for(int index = 0; index < statement.getPeriodList().size(); index++){
																					list_organised.add(list.get(index));
														                            }
																				model.addObject("list4", list_organised);
																				    }
															               }
															else if(type.equals("balance")){
																			            {
																				List<String> list = new LinkedList<String>();    	
																				List<String> list_organised = new LinkedList<String>();
																				for(Period perrt : statement.getPeriodList()){
																					          list.add(perrt.getTitle() + "_" + perrt.getData().get("Total Current Assets").replace(",", "."));
																                         }
																				for(int index = 0; index < statement.getPeriodList().size(); index++){
																					list_organised.add(list.get(index));
														                           }
																				model.addObject("list", list_organised);
																				    }
																				    {
																				List<String> list = new LinkedList<String>();    	
																				List<String> list_organised = new LinkedList<String>();
																				for(Period perrt : statement.getPeriodList()){
																					          list.add(perrt.getTitle() + "_" + perrt.getData().get("Total Current Liabilities").replace(",", "."));
																                         }
																				for(int index = 0; index < statement.getPeriodList().size(); index++){
																					list_organised.add(list.get(index));
														                         }
																				model.addObject("list2", list_organised);
																				    }
																				    {
																				List<String> list = new LinkedList<String>();    	
																				List<String> list_organised = new LinkedList<String>();
																				for(Period perrt : statement.getPeriodList()){
																					          list.add(perrt.getTitle() + "_" + perrt.getData().get("Common Equity (Total)").replace(",", "."));
																                         }
																				for(int index = 0; index < statement.getPeriodList().size(); index++){
																					list_organised.add(list.get(index));
														                            }
																				model.addObject("list3", list_organised);
																				    }
																				    {
																				List<String> list = new LinkedList<String>();    	
																				List<String> list_organised = new LinkedList<String>();
																				for(Period perrt : statement.getPeriodList()){
																					          list.add(perrt.getTitle() + "_" + perrt.getData().get("Accounts Receivables, Net").replace(",", "."));
																                         }
																				for(int index = 0; index < statement.getPeriodList().size(); index++){
																					list_organised.add(list.get(index));
														                            }
																				model.addObject("list4", list_organised);
																				    }
																
															                }
															else if(type.equals("cash")){
																                        {
																					List<String> list = new LinkedList<String>();    	
																					List<String> list_organised = new LinkedList<String>();
																					for(Period perrt : statement.getPeriodList()){
																						          list.add(perrt.getTitle() + "_" + perrt.getData().get("Depreciation and Depletion").replace(",", "."));
																	                         }
																					for(int index = 0; index < statement.getPeriodList().size(); index++){
																						list_organised.add(list.get(index));
															                           }
																					model.addObject("list", list_organised);
																					    }
																					    {
																					List<String> list = new LinkedList<String>();    	
																					List<String> list_organised = new LinkedList<String>();
																					for(Period perrt : statement.getPeriodList()){
																						          list.add(perrt.getTitle() + "_" + perrt.getData().get("Issuance of Long-Term Debt").replace(",", "."));
																	                         }
																					for(int index = 0; index < statement.getPeriodList().size(); index++){
																						list_organised.add(list.get(index));
															                         }
																					model.addObject("list2", list_organised);
																					    }
																					    {
																					List<String> list = new LinkedList<String>();    	
																					List<String> list_organised = new LinkedList<String>();
																					for(Period perrt : statement.getPeriodList()){
																						          list.add(perrt.getTitle() + "_" + perrt.getData().get("Free Cash Flow").replace(",", "."));
																	                         }
																					for(int index = 0; index < statement.getPeriodList().size(); index++){
																						list_organised.add(list.get(index));
															                            }
																					model.addObject("list3", list_organised);
																					    }
																					    {
																					List<String> list = new LinkedList<String>();    	
																					List<String> list_organised = new LinkedList<String>();
																					for(Period perrt : statement.getPeriodList()){
																						          list.add(perrt.getTitle() + "_" + perrt.getData().get("Deferred Taxes & Investment Tax Credit").replace(",", "."));
																	                         }
																					for(int index = 0; index < statement.getPeriodList().size(); index++){
																						list_organised.add(list.get(index));
															                            }
																					model.addObject("list4", list_organised);
																					    }
						                                                            }
																			        {
																				Stock stock = stockParser.getStockData(company.substring(0, company.indexOf(":"))); 	
																				model.addObject("stock", stock);	
																				    }
																			    model.addObject("view", "accounting");     
																			    model.setViewName("statement");
											                                   }
						        else{
						        	                        model.setViewName("index");
						                                 }
						                  }
						catch (Exception e) {
							model.setViewName("error");
							e.printStackTrace();
						                              }
						 return model;
			              }
	    
	    
	    @RequestMapping(value = { "/querycompany"}, method = RequestMethod.GET)
		public ModelAndView getCompany(@RequestParam String company){
			            ModelAndView model = new ModelAndView();
			            try {
			            	model.addObject("view", "company");
			            	model.setViewName("dashboard");
			            }
			            catch(Exception e) {
			            	model.setViewName("error");
			            	e.printStackTrace();
			            }
						 return model;
			              }
	
	     
	     @RequestMapping(value = "/error")
	 	public ModelAndView getErrorPage(){
	 		            ModelAndView model = new ModelAndView();
	 		            model.setViewName("error");
	 		            return model;
	 		              }
	 	
	 	
	 	
	 	@RequestMapping(value = "/pdfexport")
	 	protected ModelAndView pdfexport(HttpServletRequest request, HttpServletResponse response) throws Exception {
	 		                   return new ModelAndView(new PDFExport(),"statement",getStatement());
	      }
	 	
	 	
	 	
	 	@RequestMapping(value = "/xlsxexport")
	 	protected ModelAndView xlsxexport(HttpServletRequest request, HttpServletResponse response) throws Exception {
	 		                   return new ModelAndView(new XLSXExport(),"statement",getStatement());
	      }

	 	
	 	private Statement fetchStatementData(String company, String type, String period) throws Exception{
            if(type.equals("balance"))
                            return statementParser.getBalanceSheetStatement(company, period);
            if(type.equals("cash"))
                            return statementParser.getCashFlowStatement(company, period);
            return statementParser.getIncomeStatement(company, period);
            
          }


	 	public Statement getStatement() {
	 		return statement;
	 	}


	 	public void setStatement(Statement statement) {
	 		this.statement = statement;
	 	}
	
	
	@RequestMapping(value = { "/403"}, method = RequestMethod.GET)
	public ModelAndView exceptionPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("denied");
		return model;
	}
	

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			                  @RequestParam(value = "logout", required = false) String logout) {
		ModelAndView model = new ModelAndView();
		if (error != null) 
			model.addObject("error", "Invalid username and password!");
		if (logout != null) 
			model.addObject("message", "You've been logged out successfully.");
		model.setViewName("login");
		return model;
	}

}