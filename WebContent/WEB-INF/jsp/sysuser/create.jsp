<%@page import="org.springframework.validation.BindingResult"%>
<%@page import="com.zhanghui.saler.common.LoginContext"%>
<%@include file="../include/common.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<c:set var="_pageTitle" value="创建系统用户" scope="request"/>
<c:choose>
	<c:when test="${isUnderUserInfo}">
		<c:set var="_underUserInfo" value="active" scope="request"/>
	</c:when>
	<c:otherwise>
		<c:set var="_underSysUser" value="active" scope="request"/>
	</c:otherwise>
</c:choose>
<c:set var="_activeSystem" value="active" scope="request"/>
<c:set var="_module" value="system" scope="request"/>
<c:import url="../theme/${_theme}/header.jsp"></c:import>
<!-- main content -->
		<div class="page-header"><h1>创建/修改系统用户</h1></div>
		<div id="pageContent">
			<c:import url="../theme/${_theme}/errors.jsp"></c:import>
			<form action="${basePath}sysuser/${isUnderUserInfo?'info_save':'save'}" method="post" class="form-horizontal">
				<input name="id" type="hidden" value="${form.id}">
				<input name="_queryString" type="hidden" value="${param.queryString}">
				<div class="control-group required-field">
				  <label class="control-label">用户名:</label>
				  <div class="controls">
				    <input name="username" value="${fn:escapeXml(form.username)}" type="text" class="input-large">
				  </div>
				</div>
				<div class="control-group required-field">
				  <label class="control-label">密码:</label>
				  <div class="controls">
				    <input name="passwd" value="" type="password" class="input-large">
				  </div>
				</div>
				<div class="control-group required-field">
				  <label class="control-label">真实姓名:</label>
				  <div class="controls">
				    <input name="realname" value="" type="realname" class="input-large">
				  </div>
				</div>
				<div class="control-group required-field">
				  <label class="control-label">电话:</label>
				  <div class="controls">
				    <input name="phonenum" value="" type="phonenum" class="input-large">
				  </div>
				</div>
				<div class="control-group required-field">
				  <label class="control-label">邮箱:</label>
				  <div class="controls">
				    <input name="email" value="" type="email" class="input-large">
				  </div>
				</div>
				<div class="control-group required-field">
				  <label class="control-label">部门:</label>
				  <div class="controls">
				    <input name="department" value="" type="department" class="input-large">
				  </div>
				</div>
				<div class="control-group required-field">
				  <label class="control-label">岗位:</label>
				  <div class="controls">
				    <input name="position" value="" type="position" class="input-large">
				  </div>
				</div>
				<%if(LoginContext.get().privilege==0) {%>
					<div class="control-group required-field">
					  <label class="control-label">用户类型:</label>
					  <div class="controls">
					  	<select id="privilege_sel" name="privilege" class="input-large">
					  		<option value="1">普通用户</option>
					  		<option value="0">系统管理员</option>
					  	</select>
					  </div>
					</div>
                <%} %>
				<div class="control-group required-field">
				  <label class="control-label">备注:</label>
				  <div class="controls">
				    <input name="remark" value="${fn:escapeXml(form.remark)}" type="text" class="input-large">
				  </div>
				</div>
				<div class="form-actions">
				  <input class="btn btn-primary" type="submit" value="保存">
				  <button type="button" class="btn" onclick="javascript:history.go(-1)">取消</button>
				</div>
			</form>
        </div>
<!-- end main content -->
<c:import url="../theme/${_theme}/footer.jsp"></c:import>
