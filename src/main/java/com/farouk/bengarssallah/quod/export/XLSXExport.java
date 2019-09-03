package com.farouk.bengarssallah.quod.export;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.farouk.bengarssallah.quod.model.Period;
import com.farouk.bengarssallah.quod.model.Statement;
import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLSXExport extends AbstractXlsxView  {

	@Override
	protected void buildExcelDocument(Map model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception  {

		
		Statement statement = (Statement) model.get("statement");
        XSSFWorkbook workboook  = new XSSFWorkbook(); 
		XSSFSheet sheet = workboook.createSheet(statement.getClass().getSimpleName());
				
		XSSFRow edited = sheet.createRow(0);
		edited.createCell(5).setCellValue("Edited on " + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()));
		
		XSSFRow spacerow0 = sheet.createRow(1);
		spacerow0.createCell(0).setCellValue("  ");
		
		XSSFRow company = sheet.createRow(2);
		company.createCell(0).setCellValue("COMPANY");
		
		XSSFRow companytitle = sheet.createRow(3);
		companytitle.createCell(0).setCellValue("Company");
		companytitle.createCell(1).setCellValue(statement.getCompany());
		
		XSSFRow companysymbol = sheet.createRow(4);
		companysymbol.createCell(0).setCellValue("Symbol");
		companysymbol.createCell(1).setCellValue(statement.getCompanySymbol());
		
		XSSFRow companyreference = sheet.createRow(5);
		companyreference.createCell(0).setCellValue("Reference");
		companyreference.createCell(1).setCellValue(statement.getReference());
		
		XSSFRow companyunit = sheet.createRow(6);
		companyunit.createCell(0).setCellValue("Unit");
		companyunit.createCell(1).setCellValue(statement.getUnit());
		
		XSSFRow companyexchange = sheet.createRow(7);
		companyexchange.createCell(0).setCellValue("Exchange");
		companyexchange.createCell(1).setCellValue(statement.getExchange());
		
		XSSFRow spacerow2 = sheet.createRow(8);
		spacerow2.createCell(0).setCellValue("  ");
	
		LinkedList<String> keys = new LinkedList<String>();    	
		for(String key : statement.getPeriodList().get(0).getData().keySet()){
			                            keys.add(key);      
		                          }
		XSSFRow titles = sheet.createRow(9);
		titles.createCell(0).setCellValue("field");
		int ji = 0;
		for(Period period : statement.getPeriodList()){
	           ji++;
	           titles.createCell(ji).setCellValue(period.getTitle());
              }
		    {
		int i = 9;
		for (String key : keys) {
			    i++;
			    int j = 0;
			    XSSFRow row = sheet.createRow(i);
			    row.createCell(j).setCellValue(key);
			    for(Period period : statement.getPeriodList()){
			    	           j++;
			    	           row.createCell(j).setCellValue(period.getData().get(key));
                                 }
		         }
		    }
		
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment;filename=" + statement.getClass().
					      getSimpleName() + "_" + statement.getCompanySymbol() +  ".xlsx");
			
			workboook.write(response.getOutputStream());

	}


}