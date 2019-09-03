package com.farouk.bengarssallah.quod.export;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.farouk.bengarssallah.quod.model.Period;
import com.farouk.bengarssallah.quod.model.Statement;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PDFExport extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest request,
		                            HttpServletResponse response) throws Exception {

		Statement statement = (Statement) model.get("statement");
		
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Paragraph date = new Paragraph("Edited on " + simpleDateFormat.format(new Date()));
		date.setAlignment(Element.ALIGN_RIGHT);
		date.setSpacingAfter(10);
		date.setSpacingBefore(10);
		document.add(date);
		
		Font titleFont = new Font(Font.TIMES_ROMAN, 25, Font.BOLD);
		Paragraph title = new Paragraph(statement.getClass().getSimpleName().replace("Statement", 
				     "") + " Statement", titleFont);
		title.setAlignment(Element.ALIGN_CENTER);
		title.setSpacingAfter(10);
		title.setSpacingBefore(10);
		document.add(title);
		
		Font companyfont = new Font(Font.TIMES_ROMAN, 17, Font.BOLD);
		Paragraph COMPANY = new Paragraph("COMPANY", companyfont);
		COMPANY.setAlignment(Element.ALIGN_LEFT);
		COMPANY.setSpacingAfter(10);
		COMPANY.setSpacingBefore(15);
		document.add(COMPANY);
		
		String company = "Company    :     " +  statement.getCompany() + " \n" +
		         "Symbol     :     " + statement.getCompanySymbol() + "\n" +
				 "Reference  :     " + statement.getReference() + "\n" +
		         "Unit       :     " + statement.getUnit() + "\n" +
				 "Exchange   :     " + statement.getExchange(); 
		
		Paragraph COMPANYDETAILS = new Paragraph(company, new Font(Font.TIMES_ROMAN, 12, Font.ITALIC));
		COMPANYDETAILS.setAlignment(Element.ALIGN_LEFT);
		COMPANYDETAILS.setSpacingAfter(10);
		COMPANYDETAILS.setSpacingBefore(10);
		document.add(COMPANYDETAILS);
		
		Paragraph SPACE = new Paragraph("                   ", new Font(Font.TIMES_ROMAN, 12, Font.ITALIC));
		SPACE.setAlignment(Element.ALIGN_LEFT);
		SPACE.setSpacingAfter(10);
		SPACE.setSpacingBefore(10);
		document.add(SPACE);
		
		
		
		PdfPTable table = new PdfPTable(statement.getPeriodList().size() + 1);
		Paragraph IDVAL = new Paragraph("    field/val");
		PdfPCell cell = new PdfPCell(IDVAL);
        table.addCell(cell);

        for(Period pr : statement.getPeriodList()){
        	Paragraph PRTITLE = new Paragraph("      " + pr.getTitle());
    		PdfPCell cellPRTITLE = new PdfPCell(PRTITLE);
			table.addCell(cellPRTITLE);
		   }
        
        

		LinkedList<String> keys = new LinkedList<String>();    	
		for(String key : statement.getPeriodList().get(0).getData().keySet()){
			                            keys.add(key);      
		                          }
		for (String key : keys) {
			    table.addCell(key);
			    for(Period period : statement.getPeriodList()){
			    	           Paragraph cellPRVALUEPR = new Paragraph("      " + period.getData().get(key));
		    		           date.setAlignment(Element.ALIGN_LEFT);
			    	           PdfPCell cellPRVALUE = new PdfPCell(cellPRVALUEPR);
			    	           table.addCell(cellPRVALUE);    
                         }
			
		}
		
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment;filename=" + statement.getClass().
				      getSimpleName() + "_" + statement.getCompanySymbol() + ".pdf");
		
		

		document.add(table);
		document.close();
	}
}