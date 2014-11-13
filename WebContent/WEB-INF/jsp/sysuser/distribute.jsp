<%@page import="org.springframework.validation.BindingResult"%>
<%@include file="../include/common.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<c:set var="_pageTitle" value="分配用户权限" scope="request"/>
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
		<div class="page-header"><h1>分配用户权限</h1></div>
		<div id="pageContent">
			<c:import url="../theme/${_theme}/errors.jsp"></c:import>
			<form action="${basePath}sysuser/distributeSave" method="post" class="form-horizontal">
				<input name="id" type="hidden" value="${userRole.id}">
				<input name="userId" type="hidden" value="${userId}">
				<c:if test="${privilege==0}">
					<div class="control-group required-field">
					  <label class="control-label">数据录入权限:</label>
					  <div class="controls">
					  	<select id="dataEntry_sel" name="dataEntry" class="input-large">
					  		<option value="0">无</option>
					  		<option value="1">有</option>
					  	</select>
					  </div>
					</div>
					<script type="text/javascript">
						document.getElementById("dataEntry_sel").value='${saler:defVal(userRole.dataEntry,0)}';
					</script>
					<div class="control-group required-field">
					  <label class="control-label">数据管理权限:</label>
					  <div class="controls">
					  	<select id="dataManage_sel" name="dataManage" class="input-large">
					  		<option value="0">无</option>
					  		<option value="1">有</option>
					  	</select>
					  </div>
					</div>
					<script type="text/javascript">
						document.getElementById("dataManage_sel").value='${saler:defVal(userRole.dataManage,0)}';
					</script>
					<div class="control-group required-field">
					  <label class="control-label">销售查询权限:</label>
					  <div class="controls">
					  	<select id="saleQuery_sel" name="saleQuery" class="input-large">
					  		<option value="0">无</option>
					  		<option value="1">有</option>
					  	</select>
					  </div>
					</div>
					<script type="text/javascript">
						document.getElementById("saleQuery_sel").value='${saler:defVal(userRole.saleQuery,0)}';
					</script>
					<div class="control-group required-field">
					  <label class="control-label">用户管理权限:</label>
					  <div class="controls">
					  	<select id="userManage_sel" name="userManage" class="input-large">
					  		<option value="0">无</option>
					  		<option value="1">有</option>
					  	</select>
					  </div>
					</div>
					<script type="text/javascript">
						document.getElementById("userManage_sel").value='${saler:defVal(userRole.userManage,0)}';
					</script>
					<div class="control-group required-field">
					  <label class="control-label">imei查询权限:</label>
					  <div class="controls">
					  	<select id="imeiQuery_sel" name="imeiQuery" class="input-large">
					  		<option value="0">无</option>
					  		<option value="1">有</option>
					  	</select>
					  </div>
					</div>
					<script type="text/javascript">
						document.getElementById("imeiQuery_sel").value='${saler:defVal(userRole.imeiQuery,0)}';
					</script>
				</c:if>
				<div class="control-group required-field">
				  <label class="control-label">备注:</label>
				  <div class="controls">
				    <input name="remark" value="${fn:escapeXml(userRole.remark)}" type="text" class="input-large">
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
