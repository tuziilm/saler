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

//ƥ�������������
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
		//���ڴ��excel���������ݶ���
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
				// �������File����ΪFileInputStream;  
				InputStream in = form.getExcel().getInputStream();
				// �õ�������  
				XSSFWorkbook book = new XSSFWorkbook(in);
				// �õ���һҳ  
				XSSFSheet sheet = book.getSheetAt(0);  
				// �õ���һ���������  
				Iterator<Row> allRows = sheet.rowIterator(); 
				// ������� 
				// �õ���һ�У�Ҳ���Ǳ�����  
				Row title = allRows.next();  
				// �õ���һ�е�������  
				Iterator<Cell> cellTitle = title.cellIterator();  
				 // ��������������ݷ��뵽һ��map�С�  
	            Map titleMap = new HashMap();  
	            int i=0;  
	            while (cellTitle.hasNext()) {  
	                Cell cell = cellTitle.next();  
	                String value = cell.getStringCellValue();  
	                titleMap.put(i, value);  
	                i++;  
	            }  
	            int k = 0;//����  
	            while(allRows.hasNext()){  
	            	// �����µĵ�һ��  
	            	Row nextRow = allRows.next();  
	            	// �õ��������ʵ��  
	            	FactoryInfo factoryInfo = new FactoryInfo();
	            	// ����һ�е���,ÿ�п�ʼ��ʼ������  
	            	String[] typeString = new String[titleMap.size()];	
	            	for (k=0;k<titleMap.size();k++){  
	            		// ��ȡֵ  
	            		Cell cell = nextRow.getCell(k);  
	            		// ȡ�ò��������ͱ��浽һ���ַ�������  
	            		String str = "";  
	            		//��ǰ��Ԫ��Ϊ��  
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
	       paginator.setNeedTotal(true);//��ҳ
	       return super.preList(page, paginator, query, model);
	   }

}
