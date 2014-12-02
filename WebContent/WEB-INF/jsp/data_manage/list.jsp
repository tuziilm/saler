<%@include file="../include/common.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<c:set var="_pageTitle" value="数据录入" scope="request"/>
<c:set var="_underDataEntry" value="active" scope="request"/>
<c:set var="_activeDataManage" value="active" scope="request"/>
<c:set var="_module" value="data" scope="request"/>
<c:import url="../theme/${_theme}/header.jsp"></c:import>
<!-- main content -->
	<div class="page-header"><h1>数据录入</h1></div>
	<c:import url="../theme/${_theme}/name_search.jsp">
		<c:param name="action">${basePath}sysuser/list</c:param>
	</c:import>
		<div id="list">
			<table class="table table-bordered table-striped">
				<c:choose>
					<c:when test="${not hasDatas}">
						<tr>
							<td>没有记录!</td>
						</tr>
					</c:when>
					<c:otherwise>
						<tr>
							<th></th>
							<td>ID</td>
							<th>IMEI</th>
							<th>出厂日期</th>
							<th>型号</th>
							<th>生产线</th>
							<th>产地</th>
							<th>售保时间</th>
							<th>录入员</th>
							<th>备注</th>
						</tr>
						<c:forEach var="data" items="${datas}">
							<tr>
								<td class="checkbox_td"><input type="checkbox" name="ids" value="${data.id}"/></td>
								<td>${data.id }</td>
								<td>${fn:escapeXml(data.imei)}</td>
								<td>${fn:escapeXml(data.day)}</td>
								<td>${fn:escapeXml(data.module)}</td>
								<td>${fn:escapeXml(data.line)}</td>
								<td>${fn:escapeXml(data.area)}</td>
								<td>${fn:escapeXml(data.guarantee)}</td>
								<td>${fn:escapeXml(data.operator)}</td>
								<td>${fn:escapeXml(data.remark)}</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</table>
		</div>
		<div class="row-fluid">
			<form action="${basePath}data_manage/entry" method="post" class="form-horizontal" enctype="multipart/form-data">
				<div class="control-group required-field">
				  <label class="control-label">excel文件:</label>
				  <div class="controls">
				    <input type="file" name="excel" class="input-large">
					  <input class="btn btn-primary" type="submit" value="导入">
				  </div>
				</div>
			</form>
			<div class="span8 paginator">
				<c:import url="../theme/${_theme}/paginator.jsp"></c:import>
			</div>
		</div>
<!-- end main content -->
<c:import url="../theme/${_theme}/footer.jsp"></c:import>