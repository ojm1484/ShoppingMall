<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function regist(){
	form1.action="/admin/product/regist";
	form1.method="post";
	form1.encoding="multipart/form-data";
	form1.submit();
}
</script>
</head>
<body>
	<form name="form1">
		<input type="text" name="category_id" value="1">
		<input type="text" name="product_name">
		<input type="text" name="price">
		<input type="text" name="introduce">
		<input type="text" name="detail">
		<input type="file" name="imgFiles" multiple>
	</form>
	<button onClick="regist()">전송</button>
</body>
</html>
