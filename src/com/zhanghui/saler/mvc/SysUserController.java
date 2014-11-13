package com.zhanghui.saler.mvc;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DirectFieldBindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zhanghui.saler.common.LoginContext;
import com.zhanghui.saler.common.Query.NameQuery;
import com.zhanghui.saler.common.RemarkForm;
import com.zhanghui.saler.common.SecurityUtils;
import com.zhanghui.saler.domain.SysUser;
import com.zhanghui.saler.domain.UserRole;
import com.zhanghui.saler.service.SysUserService;
import com.zhanghui.saler.service.UserRoleService;

/**
 * 系统用户操作入口
 * @author <a href="pangkunyi@gmail.com">Calvin Pang</a>
 *
 */
@Controller
@RequestMapping("/sysuser")
public class SysUserController extends CRUDController<SysUser, SysUserService, com.zhanghui.saler.mvc.SysUserController.SysUserForm, NameQuery>{
	protected final String DISTRIBUTE_AREA;
	public SysUserController() {
		super("sysuser");
		DISTRIBUTE_AREA=String.format("/%s/distribute", "sysuser");
	}
	@Resource
	public void setSysUserService(SysUserService sysUserService){
		this.service=sysUserService;
	}
	@Resource
	private UserRoleService userRoleService;
	@RequestMapping("/index")
	public String index(){
		if(LoginContext.isAdmin()){
			return REDIRECT_LIST_PAGE;
		}else{
			return "redirect:/sysuser/info_modify/"+LoginContext.getUid();
		}
	}
	
	@RequestMapping(value="/info_save",method=RequestMethod.POST)
	public String infoSave(@Valid SysUserForm form,BindingResult errors, Model model) throws NoSuchAlgorithmException{
		model.addAttribute("errors", errors);
		model.addAttribute("form", form);
		model.addAttribute("isUnderUserInfo", true);
		if(!errors.hasErrors()){
			SysUser sysUser=form.toObj();
			if( sysUser.getId()==null || sysUser.getId()<=0 || sysUser.getId().intValue()!=LoginContext.getUid().intValue()){
				errors.addError(new ObjectError("false", "没有权限修改！"));
			}else{
				//检测密码的正确性
				String passwd = form.getPasswd();
				if(passwd!=null &&passwd.length()>0 && (passwd.length()<4 || passwd.length()>20)){
					errors.addError(new ObjectError("passwd", "密码应为4~20个字符！"));
					model.addAttribute("errors", errors);
					return CREATE_PAGE;
				}
				service.update(sysUser);
				//修改成功,不跳转用户列表
				errors.addError(new ObjectError("success", "修改成功！"));
			}
		}
		return CREATE_PAGE;
	}
	

	@Override
	public void innerSave(SysUserForm form, BindingResult errors, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		SysUser sysUser=form.toObj();
		//检测密码的正确性
		String passwd = form.getPasswd();
		boolean isModify=sysUser.getId()!=null && sysUser.getId()>0;
		if(passwd==null||passwd.length()<1){
			if(!isModify){
				errors.addError(new ObjectError("passwd", "密码不能为空！"));
				model.addAttribute("errors", errors);
				return;
			}
		}else if(passwd.length()<6){
			errors.addError(new ObjectError("passwd", "密码不能少于6个字符个字符！"));
			model.addAttribute("errors", errors);
			return;
		}
		//执行更新或保存的操作
		try{
			if(isModify){
				service.update(sysUser);
			}else{
				sysUser.setStatus((byte)1);
				service.save(sysUser);
			}
		}catch(DuplicateKeyException e){
			errors.addError(new ObjectError("database", "名称已经存在！"));
			model.addAttribute("errors", errors);
		}
	}
	
	@RequestMapping("/info_modify/{id}")
	public String infoModify(@PathVariable("id") int id,Model model){
		model.addAttribute("isUnderUserInfo", true);
		if(!LoginContext.isAdmin()&&LoginContext.getUid().intValue()!=id){
			DirectFieldBindingResult errors=new DirectFieldBindingResult("id","id");
			errors.addError(new ObjectError("false", "没有权限修改！"));
			model.addAttribute("errors", errors);
			return CREATE_PAGE;
		}
		SysUser sysUser = service.get(id);
		model.addAttribute("form", sysUser);
		return CREATE_PAGE;
	}
	
	public static class SysUserForm extends RemarkForm<SysUser>{
		@NotNull(message="用户名不能为空")
		@Size(min=1,max=20,message="用户名长度应该在1-20个字符")
		private String username;
		@Size(min=6,message="密码长度不少于6个字符")
		private String passwd;
		private String realname;
		private String phonenum;
		private String email;
		private String department;
		private String position;
		private Integer privilege;
        @Override
        public void populateObj(SysUser sysUser) {
            sysUser.setUsername(username);
            if(passwd!=null&&passwd.length()>0){
                sysUser.setPasswd(SecurityUtils.md5Encode(passwd, username));
            }
            sysUser.setDepartment(department);
            sysUser.setEmail(email);
            sysUser.setPhonenum(phonenum);
            sysUser.setPosition(position);
            sysUser.setRealname(realname);
            sysUser.setPrivilege(privilege);
        }

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPasswd() {
			return passwd;
		}

		public void setPasswd(String passwd) {
			this.passwd = passwd==null?null:passwd.trim();
		}
		
        public String getRealname() {
			return realname;
		}

		public void setRealname(String realname) {
			this.realname = realname;
		}

		public String getPhonenum() {
			return phonenum;
		}

		public void setPhonenum(String phonenum) {
			this.phonenum = phonenum;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getDepartment() {
			return department;
		}

		public void setDepartment(String department) {
			this.department = department;
		}

		public String getPosition() {
			return position;
		}

		public void setPosition(String position) {
			this.position = position;
		}
		
		public Integer getPrivilege() {
			return privilege;
		}

		public void setPrivilege(Integer privilege) {
			this.privilege = privilege;
		}

		@Override
        public SysUser newObj() {
            return new SysUser();
        }
    }
	@RequestMapping(value="/distribute/{id}")
	public String distribute(@PathVariable("id") int id,Model model){
			SysUser sysUser = service.get(id);
			UserRole userRole = userRoleService.getByUserId(id);
			model.addAttribute("privilege",LoginContext.get().privilege);
			model.addAttribute("userId",id);
			model.addAttribute("sysUser",sysUser);
			model.addAttribute("userRole",userRole);
			return DISTRIBUTE_AREA;
		}
	@RequestMapping(value="/distributeSave",method=RequestMethod.POST)
	public String distributeSave(@Valid DistributeSaveForm form,BindingResult errors, Model model, HttpServletRequest request, HttpServletResponse response)throws UnsupportedEncodingException{
		UserRole userRole=new UserRole();
		if (!errors.hasErrors()) {
			try{
				userRole.setId(form.getId());
				userRole.setUserId(form.getUserId());
				userRole.setDataEntry(form.getDataEntry());
				userRole.setDataManage(form.getDataManage());
				userRole.setSaleQuery(form.getSaleQuery());
				userRole.setImeiQuery(form.getImeiQuery());
				userRole.setUserManage(form.getUserManage());
				userRole.setRemark(form.getRemark());
				userRoleService.saveOrUpdate(userRole);
			}catch(DuplicateKeyException e){
				errors.addError(new ObjectError("database", "代号已经存在！"));
			} 
		if (errors.hasErrors()) {
			model.addAttribute("errors", errors);
			return DISTRIBUTE_AREA;
		}
	}
		return REDIRECT_LIST_PAGE;
}
	
	public static class DistributeSaveForm {
		
		private Integer id;
		private Integer userId;
		private Integer dataEntry;
		private Integer dataManage;
		private Integer saleQuery;
		private Integer userManage;
		private Integer imeiQuery;
		private String remark;
		
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public Integer getUserId() {
			return userId;
		}
		public void setUserId(Integer userId) {
			this.userId = userId;
		}
		public Integer getDataEntry() {
			return dataEntry;
		}
		public void setDataEntry(Integer dataEntry) {
			this.dataEntry = dataEntry;
		}
		public Integer getDataManage() {
			return dataManage;
		}
		public void setDataManage(Integer dataManage) {
			this.dataManage = dataManage;
		}
		public Integer getSaleQuery() {
			return saleQuery;
		}
		public void setSaleQuery(Integer saleQuery) {
			this.saleQuery = saleQuery;
		}
		public Integer getUserManage() {
			return userManage;
		}
		public void setUserManage(Integer userManage) {
			this.userManage = userManage;
		}
		public Integer getImeiQuery() {
			return imeiQuery;
		}
		public void setImeiQuery(Integer imeiQuery) {
			this.imeiQuery = imeiQuery;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		
	}
}
