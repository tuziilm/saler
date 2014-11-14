package com.zhanghui.saler.mvc;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.zhanghui.saler.common.Paginator;
import com.zhanghui.saler.common.Query.NameQuery;
import com.zhanghui.saler.common.RemarkForm;
import com.zhanghui.saler.common.RemarkStatusForm;
import com.zhanghui.saler.domain.FactoryInfo;
import com.zhanghui.saler.service.FactoryInfoService;

//匹配广告分类管理界面
@Controller
@RequestMapping("/data_manage")
public class DataEntryController
		extends
		CRUDController<FactoryInfo, FactoryInfoService, DataEntryController.Form, NameQuery> {
	private final static Logger log = LoggerFactory
			.getLogger(DataEntryController.class);
	
	public DataEntryController() {
		super("data_manage");
	}

	@Resource
	public void setFactoryInfoService(FactoryInfoService factoryInfoService) {
		this.service = factoryInfoService;
	}
	
	public static class Form extends RemarkForm<FactoryInfo> {
		private MultipartFile excel;
		@Override
		public FactoryInfo newObj() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void populateObj(FactoryInfo obj) {
			// TODO Auto-generated method stub
			
		}
	
	}

	 @Override
	 protected boolean preList(int page, Paginator paginator, NameQuery query, Model model) {
	       paginator.setNeedTotal(true);//分页
	       return super.preList(page, paginator, query, model);
	   }

	@Override
	public void innerSave(Form form, BindingResult errors, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}	
}
