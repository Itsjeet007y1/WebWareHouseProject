package com.app.controller.view;

import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.app.model.PurchaseOrder;
import com.app.model.PurchaseOrderDetails;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class VendorInvoicePdfView extends AbstractPdfView {
	@Override
	protected void buildPdfDocument(Map<String, Object> model, com.lowagie.text.Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
PurchaseOrder po=(PurchaseOrder) model.get("po");
		
		List<PurchaseOrderDetails> poDtls=po.getDetails();
		double finalCost=0.0;
		for(PurchaseOrderDetails dtl:poDtls){
			finalCost+=dtl.getItemDetails().getItemCost()*dtl.getItemQty();
	}
		
		document.add(new Paragraph("VENDOR INVOICE CODE:VEN-"+po.getOrderCode()));
        document.addTitle(po.getOrderCode());
		// define font for table header row
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        // define table header cell
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.ORANGE);
        cell.setPadding(5);
        
		PdfPTable heading=new PdfPTable(4);
		heading.setWidthPercentage(100.0f);
		heading.setWidths(new float[]{2.5f,2.5f,2.5f,2.5f});
		heading.setSpacingBefore(10);
		
		cell.setPhrase(new Phrase("Vendor Code", font));
		heading.addCell(cell);
		heading.addCell(po.getVendor().getWhUserCode());
		
		cell.setPhrase(new Phrase("Order Code", font));
		heading.addCell(cell);
		heading.addCell(po.getOrderCode());
		
		cell.setPhrase(new Phrase("Final Cost", font));
		heading.addCell(cell);
		heading.addCell(new BigDecimal(finalCost).setScale(3, RoundingMode.CEILING).toString());
		
		cell.setPhrase(new Phrase("Shipment Type", font));
		heading.addCell(cell);
		heading.addCell(po.getShipmentMode().getShipmentCode());
		
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100.0f);
        table.setWidths(new float[] {1.0f, 3.0f, 2.0f, 2.0f, 2.0f});
        table.setSpacingBefore(10);
         
        
         
        // write table header
        cell.setPhrase(new Phrase("Sl No", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Item", font));
        table.addCell(cell);
 
        cell.setPhrase(new Phrase("Cost", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Quantity", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Total", font));
        table.addCell(cell);
         
        // write table row data
        for (PurchaseOrderDetails details : poDtls) {
            table.addCell(String.valueOf(details.getSlno()));
            table.addCell(details.getItemDetails().getItemCode());
            table.addCell(String.valueOf(details.getItemDetails().getItemCost()));
            table.addCell(String.valueOf(details.getItemQty()));
            table.addCell(String.valueOf(details.getItemDetails().getItemCost()*details.getItemQty()));
        }
         
        document.add(heading);
        document.add(table);
        document.add(new Paragraph("Generated On:"+new Date()));
		
	}
}
