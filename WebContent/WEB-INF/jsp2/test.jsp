<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<select name="age">
		<script type="text/javascript">
			for(var i=10;i<70;i++){
				document.write('<option value="'+i+'">'+i+'歳</option>');
			}
		</script>
	</select>
</body>
</html>