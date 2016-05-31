package org.huic.s;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

/**
 * @author chenhui
 * @date 2016年5月30日
 */
public class GenExcel {
	
	private static final String[] titles = {"t", "np", "ap", "q", "m"};
	
	private static final String[] sns = {"000001"};
	
	public static void main(String[]args) throws Exception {
        Workbook wb = new HSSFWorkbook();
        
        for (String sn : sns) {
        	Sheet sheet = wb.createSheet(sn);
        	genSheet(sheet, sn);
        }
        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("531.xls");
        wb.write(fileOut);
        fileOut.close();
	}
	
	static void genSheet(Sheet sheet, String sn) throws IOException {
		// Create a row and put some cells in it. Rows are 0 based.
        Row row = sheet.createRow((short)0);
        // Create a cell and put a value in it.
        
        for (int c = 0; c < titles.length; c++) {
        	row.createCell(c).setCellValue(titles[c]);
        }
        
        List<MinuteInfo> infoList = getMinuteInfoList(sn);
        int rowId = 1;
        for (MinuteInfo mi : infoList) {
        	Row r = sheet.createRow(rowId++);
        	r.createCell(0).setCellValue(mi.getTime());
        	r.createCell(1).setCellValue(mi.getNowPrice());
        	r.createCell(2).setCellValue(mi.getAvgPrice());
        	r.createCell(3).setCellValue(mi.getQuantity());
        	r.createCell(4).setCellValue(mi.getMargin());
        }
	}
	
	static List<MinuteInfo> getMinuteInfoList(String sn) throws IOException {
		
		String path = "F:/phantomjs/bin/" + sn + ".txt";
		
		List<MinuteInfo> retList = new ArrayList<MinuteInfo>();
		List<String> ll = FileUtils.readLines(new File(path));
		
		boolean isData = false;
		MinuteInfo dataBean = null;
		List<String> colLst = new ArrayList<String>();
		for (String s : ll) {
			if (s.startsWith("@@@@@@@")) {
				isData = true;
				continue;
			}
			if (!isData) continue;
			if (s.startsWith("+++++++")) {
				dataBean = new MinuteInfo();
				colLst.clear();

			} else if (s.startsWith("-------")){
				
				if (colLst.size() >= 20) {
					dataBean.setAvgPrice(getString(colLst.get(colLst.size() - 5)));
					dataBean.setMargin(getString( colLst.get(colLst.size() - 1)));
					dataBean.setNowPrice(getString(colLst.get(colLst.size() - 7)));
					dataBean.setQuantity(getString(colLst.get(colLst.size() - 3)));
					dataBean.setTime(getString(colLst.get(colLst.size() - 9)));
					retList.add(dataBean);
				}
			} else {
				colLst.add(s);
			}
			
		}
		return retList;
	}
	
	private static String getString(String srcString) {
		int idSharp = srcString.indexOf('#');
		if (idSharp < 0) return "";
		
		String idStr = srcString.substring(0, idSharp);
		
		return srcString.substring(idSharp + 1, idSharp + Integer.parseInt(idStr) + 1);
	}
}
