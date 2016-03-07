<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/plugins/webuploader/webuploader.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/addEditView.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/button.css" />
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.form.js"></script>
<script src="${pageContext.request.contextPath}/resources/layer/layer.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/webuploader/webuploader.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/webuploader/csystem/patyexcelupload.js"></script>

<table>
	<tr style="height: 60px;">
		<td>
		<a href="${pageContext.request.contextPath}/common/paty/downloadPatyExcel"><input type="button" id="excelDownload" class="button button-primary button-rounded button-small" value="模板下载"/></a>  
		</td>
	</tr>
  	<tr style="height: 60px;">
		<td>
			<div id="thelist">
				仅支持xls类型文件，请下载模板后上传</br></br>
				主意：导入过程中，将以学号为准，若学号错误，则导入失败
			</div>	
		</td>
	</tr>
  	<tr style="height: 60px;">
		<td>
			<div id="uploader">
			    <div class="btns">
			        <div id="picker">选择文件</div>
			    </div>
			</div>
		</td>
	</tr>
	<tr style="height: 60px;">
		<td>
			<input type="button" id="ctlBtn" class="button button-primary button-rounded button-small" value="开始上传"/>
		</td>
	</tr> 
</table>


<script>


</script>