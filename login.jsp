<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title> treecutting </title>
	<link rel="stylesheet" href="/css/index.css"/>
</head>
<body>
	<div align="center">
		<h1> Welcome to treecutting </h1>
		<p> ${loginErrorString} </p>
		<form action="handleLogin" method="post">
			<table border="1" cellpadding="5">
				<tr>
					<th> Email: </th>
					<td>
						<input type="text" name="email" size="45" autofocus>
					</td>
				</tr>
				<tr>
					<th> Password: </th>
					<td> 
						<input type="password" name="password" size="45">
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="Login"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>