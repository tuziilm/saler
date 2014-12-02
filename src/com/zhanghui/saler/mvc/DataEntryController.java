package com.zhanghui.saler.mvc;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hslf.model.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.zhanghui.saler.common.Paginator;
import com.zhanghui.saler.common.Query;
import com.zhanghui.saler.common.Query.NameQuery;
import com.zhanghui.saler.domain.FactoryInfo;
import com.zhanghui.saler.service.FactoryInfoService;

//匹配广告分类管理界面
@Controller
@RequestMapping("/data_manage")
public class DataEntryController extends ListController<FactoryInfo, FactoryInfoService, NameQuery> {
	private final static Logger log = LoggerFactory
			.getLogger(DataEntryController.class);
	
	public DataEntryController() {
		super("data_manage");
	}

	@Resource
	public void setFactoryInfoService(FactoryInfoService factoryInfoService) {
		this.service = factoryInfoService;
	}
	
	public static class Form {
		private MultipartFile excel;

		public MultipartFile getExcel() {
			return excel;
		}

		public void setExcel(MultipartFile excel) {
			this.excel = excel;
		}
	
	}
	 @RequestMapping(value="/entry",method=RequestMethod.POST)
	 public String entry(Query.NameQuery query, Form form,BindingResult errors, Model model,HttpServletRequest request, HttpServletResponse response){
		//用于存放excel解析的数据对象
		List<FactoryInfo> list = readExcel(form);
		for(FactoryInfo f :list){
			System.out.println(f.getImei()+"::::::"+f.getDay()+"::::::"+f.getOperator()+"::::::"+f.getArea()+"::::::"+f.getGuarantee()+"::::::"+f.getRemark()+"::::::"+f.getLine());
		}
//		return list(query, errors, model, request);
		return null;
	 }
	 public List<FactoryInfo> readExcel(Form form){
		 List<FactoryInfo> list = new ArrayList<FactoryInfo>();
		 try {
				// 将传入的File构造为FileInputStream;  
				InputStream in = form.getExcel().getInputStream();
				// 得到工作表  
				XSSFWorkbook book = new XSSFWorkbook(in);
				// 得到第一页  
				XSSFSheet sheet = book.getSheetAt(0);  
				// 得到第一面的所有行  
				Iterator<Row> allRows = sheet.rowIterator(); 
				// 标题解析 
				// 得到第一行，也就是标题行  
				Row title = allRows.next();  
				// 得到第一行的所有列  
				Iterator<Cell> cellTitle = title.cellIterator();  
				 // 将标题的文字内容放入到一个map中。  
	            Map titleMap = new HashMap();  
	            int i=0;  
	            while (cellTitle.hasNext()) {  
	                Cell cell = cellTitle.next();  
	                String value = cell.getStringCellValue();  
	                titleMap.put(i, value);  
	                i++;  
	            }  
	            int k = 0;//列数  
	            while(allRows.hasNext()){  
	            	// 标题下的第一行  
	            	Row nextRow = allRows.next();  
	            	// 得到传入类的实例  
	            	FactoryInfo factoryInfo = new FactoryInfo();
	            	// 遍历一行的列,每行开始初始化列数  
	            	String[] typeString = new String[titleMap.size()];	
	            	for (k=0;k<titleMap.size();k++){  
	            		// 列取值  
	            		Cell cell = nextRow.getCell(k);  
	            		// 取得参数的类型保存到一个字符串里面  
	            		String str = "";  
	            		//当前单元格不为空  
	            		if(null != cell){
	            			if(cell.getCellType() == cell.CELL_TYPE_NUMERIC){  
	            				java.text.DecimalFormat formatter = new DecimalFormat("#");  
	            				str = formatter.format(cell.getNumericCellValue());  
	            			}else if(cell.getCellType() == cell.CELL_TYPE_STRING){  
	            				str = String.valueOf(cell.getStringCellValue());  
	            			}else if(cell.getCellType() == cell.CELL_TYPE_FORMULA){  
	            				str = String.valueOf(cell.getDateCellValue());  
	            			}else if(cell.getCellType() == cell.CELL_TYPE_BLANK){  
	            				str = String.valueOf(cell.getStringCellValue());  
	            			}else if(cell.getCellType() == cell.CELL_TYPE_ERROR){  
	            				str = "";  
	            			}  
	            		}
	            		typeString[k]=str;
	            	}  
	            	factoryInfo.setImei(typeString[0]);
	            	factoryInfo.setDay(typeString[1]);
	            	factoryInfo.setModule(typeString[2]);
	            	factoryInfo.setLine(typeString[3]);
	            	factoryInfo.setArea(typeString[4]);
	            	factoryInfo.setGuarantee(typeString[5]);
	            	factoryInfo.setOperator(typeString[6]);
	            	factoryInfo.setRemark(typeString[7]);
	            	list.add(factoryInfo);
	            }  
			} catch (Exception e) {
				e.printStackTrace();
			}
		 return list;
	 }
	 
	 @Override
	 protected boolean preList(int page, Paginator paginator, NameQuery query, Model model) {
	       paginator.setNeedTotal(true);//分页
	       return super.preList(page, paginator, query, model);
	   }

}
