package com.app.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.app.model.Uom;

@Component
public class UomUtil {
	public  Map<String,String> getUomTypes() {
		Map<String,String> uomTypes=new LinkedHashMap<String,String>();
		uomTypes.put("PACK", "Packing");
		uomTypes.put("NOPACK", "No Packing");
		uomTypes.put("NA", "NA");
		return uomTypes;
	}
	public List<Uom> replaceWithValues(List<Uom> uomList) {
			Iterator<Uom> uomItr=uomList.iterator();
			while(uomItr.hasNext()) {
				Uom uom=(Uom)uomItr.next();
				uom.setUomType(getUomTypes().get(uom.getUomType()));
			}
			return uomList;
	}
	
	public List<Uom> processUomImport(MultipartFile file) {
		List<Uom> uomList=null;
		try {
			uomList=new ArrayList<Uom>();
			InputStream is=file.getInputStream();
			XSSFWorkbook book=new XSSFWorkbook(is);
			Sheet sheet=book.getSheet("UOMs");
			Iterator<Row> rowItr=sheet.iterator();
			while(rowItr.hasNext()) {
				Row row=rowItr.next();
				//skip row zero
				if(row.getRowNum()==0) continue;
				uomList.add(new Uom(row.getCell(0).getStringCellValue(),row.getCell(1).getStringCellValue(),row.getCell(2).getStringCellValue(),new Date()));
			}//while loop end
			book.close();
		} catch(Exception e) {
			System.out.println(e);
		}//catch
		return uomList;
	}//if statement
	public List<Uom> getUomsFromSheet(MultipartFile file){
		List<Uom> uomList=null;
		if(file!=null){

			try {
				XSSFWorkbook book=new XSSFWorkbook(file.getInputStream());
				XSSFSheet sheet=book.getSheet("UOMs");
				if(sheet!=null){
					Iterator<Row> rows=sheet.iterator();
					uomList=new ArrayList<Uom>();
					while (rows.hasNext()) {
						Row row = (Row) rows.next();
						if(row.getRowNum()==0) continue;
						uomList.add(
								new Uom(row.getCell(0)!=null?row.getCell(0).getStringCellValue():"",
										row.getCell(1)!=null?row.getCell(1).getStringCellValue():"",
										row.getCell(2)!=null?row.getCell(2).getStringCellValue():"",
										new Date()
										)
								);
					}
				}
				book.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return uomList;
	}
	
}//class
