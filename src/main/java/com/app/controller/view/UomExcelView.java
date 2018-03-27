package com.app.controller.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.app.model.Uom;

public class UomExcelView extends AbstractXlsxView {
	protected void buildExcelDocument(Map<String,Object> map, Workbook book, HttpServletRequest req, HttpServletResponse res) {
		//file naming setting.. to give the name of the file
		res.addHeader("content-Disposition", "attachment;filename=\"UOMsData.xlsx\"");
		@SuppressWarnings("unchecked")
		List<Uom> uomList=(List<Uom>)map.get("uomList");
		Sheet sheet=book.createSheet("UOMs");
		setHead(sheet);
		setBody(sheet,uomList);
	}
	private void setHead(Sheet sheet) {
		Row row=sheet.createRow(0);
		row.createCell(0).setCellValue("Uom ID");
		row.createCell(1).setCellValue("Uom Type");
		row.createCell(2).setCellValue("Uom Model");
		row.createCell(3).setCellValue("Uom Created");
		row.createCell(4).setCellValue("Uom Modified");
		row.createCell(5).setCellValue("Description");
	}
	private void setBody(Sheet sheet, List<Uom> uomList) {
		int rowNum=1;
		for(Uom uom:uomList) {
			Row row=sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(uom.getUomId());
			row.createCell(1).setCellValue(uom.getUomType());
			row.createCell(2).setCellValue(uom.getUomModel());
			row.createCell(3).setCellValue(uom.getCreatedDate().toString());
			row.createCell(4).setCellValue(uom.getLastModifiedDate()!=null?uom.getLastModifiedDate().toString():"--NA--");
			row.createCell(5).setCellValue(uom.getDescription());
		}
	}
}
