<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Registration</title>
	<link rel="stylesheet" href="/css/index.css"/>
</head>
<body>
	<div align="center">
        <p> ${registerErrorTaken} </p>
        <p> ${registerErrorConfirm} </p>
		<form action="handleRegister">
			<table border="1" cellpadding="5">
				<tr>
					<th>UserName: </th>
					<td align="center" colspan="3">
						<input type="text" name="username" size="45" onfocus="this.value=''" required>
					</td>
				</tr>
				<tr>
					<th>Email: </th>
					<td align="center" colspan="3">
						<input type="text" name="email" size="45"  value="example@gmail.com" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>First Name: </th>
					<td align="center" colspan="3"> 
						<input type="text" name="firstName" size="45" value="FirstName" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Last Name: </th>
					<td align="center" colspan="3">
						<input type="text" name="lastName" size="45" value="LastName" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Birthday: </th>
					<td align="center" colspan="3">
						<input type="text" name="birthday" size="45" value="YYYY-MM-DD" onfocus="this.value=''">
					</td>
	
				</tr>
				<tr>
					<th>Password: </th>
					<td align="center" colspan="3"> 
						<input type="password" name="password" size="45" value="password" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Password Confirmation: </th>
					<td align="center" colspan="3">
						<input type="password" name="confirmation" size="45" value="password" onfocus="this.value=''">
					</td>
				
				</tr>
				<tr>
					<th>Credit Card Number</th>
					<td align="center" colspan="3">
						<input type="text" name="CreditCardNumber" size="45" value="CreditCardNumber" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Card Expiration Date</th>
					<td align="center" colspan="3">
						<input type="text" name="CreditCardExpirationDate"  value="CreditCardExpirationDate" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Security Code</th>
					<td align="center" colspan="3">
						<input type="text" name="CreditCardSecurityCode" size="45" value="CreditCardSecurityCode" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<td align="center" colspan="5">
						<input type="submit" value="Register"/>
					</td>
				</tr>
			</table>
			<a href="login.jsp" target="_self">Return to Login Page</a>
		</form>
	</div>
</body>