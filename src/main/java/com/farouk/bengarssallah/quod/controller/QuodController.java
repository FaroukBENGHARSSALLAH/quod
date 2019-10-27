package com.farouk.bengarssallah.quod.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.farouk.bengarssallah.quod.export.PDFExport;
import com.farouk.bengarssallah.quod.export.XLSXExport;
import com.farouk.bengarssallah.quod.model.Action;
import com.farouk.bengarssallah.quod.model.Period;
import com.farouk.bengarssallah.quod.model.Statement;
import com.farouk.bengarssallah.quod.model.Stock;
import com.farouk.bengarssallah.quod.parser.StatementParser;
import com.farouk.bengarssallah.quod.parser.StockParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class QuodController {
	
	@Autowired
	private StockParser stockParser;
	
	

	private static Map<String, String> cncodes;
	
	@Autowired
	private StatementParser statementParser;
	
	@Value("argentina")
	private String country;
	
	@Value("XBUE")
	private String exchange;
	

	private static List<Action> actions;
	
	
	private static List<String> exchanges;
	
	static {
		Map<String, String> map = new HashMap<>();
		map.put("argentina", "AR");
		map.put("australia", "AU");
		map.put("austria", "AT");
		map.put("belguim", "BE");
		map.put("brazil", "BR");
		map.put("canada", "CA");
		map.put("chile", "CL");
		map.put("china", "CN");
		map.put("colombia", "CO");
		map.put("czech-republic", "CZ");
		map.put("denmark", "DK");
		map.put("estonia", "EE");
		map.put("finland", "FI");
		map.put("france", "FR");
		map.put("germany", "DE");
		map.put("greece", "GR");
		map.put("hong kong", "HK");
		map.put("hungary", "HU");
		map.put("iceland", "IS");
		map.put("india", "IN");
		map.put("indonesia", "ID");
		map.put("ireland", "IE");
		map.put("japan", "JP");
		map.put("united-kingdom", "UK");
		map.put("united-states", "");
		cncodes =  map;
		
		try {
		ObjectMapper objectMapper = new ObjectMapper();
		
		actions = objectMapper.readValue(new ClassPathResource("files/companies/argentina.json").getFile()	,
				     new TypeReference<List<Action>>(){});
		
		exchanges = objectMapper.readValue(new ClassPathResource("files/companies_ex/argentina_ex.json").getFile()	,
				     new TypeReference<List<String>>(){});
		
		}
		catch(Exception e){}
		
		
	}

	
	private Statement statement;

	@RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
	public ModelAndView welcomePage() {
		ModelAndView model = new ModelAndView();
		int index = ThreadLocalRandom.current().nextInt(1, 15 + 1);
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
	          List<Action> actions = getActions(country);
	          setActions(actions);
	          List<String> exchanges = actions.stream()
                      .map(a -> a.getExchange())     
                      .distinct()
                      .collect(Collectors.toList());
	   		  model.addObject("index_data", indexListData);	
	   		  model.addObject("exchanges", exchanges);	
	   		    }
		model.setViewName("company");
		       }
		catch (Exception e) {
		    model.setViewName("denied");
          }
		return model;
	}
	
	
	@RequestMapping(value = { "/company/exchanges"}, method = RequestMethod.GET)
	@ResponseBody
	public List<String> exchanges(String country) throws Exception {
		            setCountry(country);
		            setActions(getActions(country));
		            setExchanges(getExchanges(country));
			        setExchange(exchanges.get(0));
			        return exchanges;  
	       }
	
	
	
	@RequestMapping(value = { "/exchange"}, method = RequestMethod.GET)
	@ResponseBody
	public String exchange(String exchange) throws Exception {
		          setExchange(exchange);
		          return "ok";
	       }
	
	
	
	@RequestMapping(value = { "/companies"}, method = RequestMethod.GET)
	@ResponseBody
	public List<String> companies(String term) throws Exception {
		                   List<Action> act = actions.stream().filter(a -> a.getExchange().equals(exchange) && 
		                	    		    (a.getTicker().contains(term) || 
		                	    		    		a.getName().contains(term)))
		                      .collect(Collectors.toList());
		                   return act.stream().map(a -> a.getTicker() + ":" + a.getName())
		                		   .collect(Collectors.toList());
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
 		  model.addObject("index_data", indexListData);	
 		  model.addObject("exchanges", exchanges);	
		    }
        model.setViewName("accounting");
              }catch (Exception e) {
			    model.setViewName("denied");
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
														    Statement statement = fetchStatementData(company, type, period, cncodes.get(country), exchange);
															model.addObject("statement", statement);
															setStatement(statement);
															    {
															LinkedList<String> keys = new LinkedList<String>();    	
															for(String key : statement.getPeriodList().get(0).getData().keySet()){
																                            keys.add(key);      
															                          }
															model.addObject("keys", keys);
															    }
															model.addObject("country", country);  
															model.addObject("exchanges", exchanges); 
															model.addObject("exchange", exchange); 
															model.addObject("company", company);
															model.addObject("type", type);
															model.addObject("period", period);
															if(type.equals("income")){
																				   {
																				List<String> list = new LinkedList<String>();    	
																				List<String> list_organised = new LinkedList<String>();
																				for(Period perrt : statement.getPeriodList()){
																					          if(perrt.getData().get("Gross Income") != null)
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
																					          if(perrt.getData().get("Sales/Revenue") != null)
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
																					          if( perrt.getData().get("Net Income") != null)
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
																					          if( perrt.getData().get("Gross Interest Expense") != null)
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
																					          if( perrt.getData().get("Total Current Assets") != null)
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
																					           if( perrt.getData().get("Total Current Liabilities") != null)
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
																					           if( perrt.getData().get("Common Equity (Total)") != null)
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
																					          if( perrt.getData().get("Accounts Receivables, Net") != null)
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
																						          if( perrt.getData().get("Depreciation and Depletion") != null)
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
																						          if( perrt.getData().get("Issuance of Long-Term Debt") != null)
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
																						          if( perrt.getData().get("Free Cash Flow") != null)
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
																						          if( perrt.getData().get("Deferred Taxes & Investment Tax Credit") != null)
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
							model.setViewName("denied");
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
			            	model.setViewName("denied");
			            	e.printStackTrace();
			            }
						 return model;
			              }
	
	     
	     @RequestMapping(value = "/error")
	 	public ModelAndView getErrorPage(){
	 		            ModelAndView model = new ModelAndView();
	 		            setCountry("argentina");
	 		            setExchange("XBUE");
	 		            model.setViewName("denied");
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

	 	
	 	private Statement fetchStatementData(String company, String type, String period, String country, String exchange) throws Exception{
            if(type.equals("balance"))
                            return statementParser.getBalanceSheetStatement(company, period, country, exchange);
            if(type.equals("cash"))
                            return statementParser.getCashFlowStatement(company, period, country, exchange);
            return statementParser.getIncomeStatement(company, period, country, exchange);
            
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
		setCountry("argentina");
         setExchange("XBUE");
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
	
	
	public List<String> getExchanges(String country) throws Exception{
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(new ClassPathResource("files/companies_ex/" + country + "_ex.json").getFile()	,
				     new TypeReference<List<String>>(){});
	        }
	
	
	private List<Action> getActions(String country) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(new ClassPathResource("files/companies/" + country + ".json").getFile()	,
				     new TypeReference<List<Action>>(){});
	  }

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public List<Action> getActions() {
		return actions;
	}

	public void setActions(List<Action> actions) {
		this.actions = actions;
	}

	public void setExchanges(List<String> exchanges) {
		this.exchanges = exchanges;
	}

}