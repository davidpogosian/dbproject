<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="dbpack.Tree" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1" />
	<title>treecutting</title>
	<link rel="stylesheet" href="/css/index.css"/>
</head>
<body>
	<div align="center">
		<h2>trees in this request:</h2>
		<table border="1">
			<tr>
				<th>Tree ID</th>
				<th>Request ID</th>
				<th>Height</th>
				<th>Distance</th>
				<th>Address</th>
				<th>Size</th>
			</tr>
			<%
				Tree[] trees = (Tree[]) request.getAttribute("trees");
				for(Tree tree : trees) {
			%>
			<tr>
				<td><%=tree.getTreeId() %></td>
				<td><%=tree.getRequestId() %></td>
				<td><%=tree.getHeight() %></td>
				<td><%=tree.getDistance() %></td>
				<td><%=tree.getAddress() %></td>
				<td><%=tree.getSize() %></td>
			</tr>
			<% } %>
		</table>

		<h2>Add a new tree to this request</h2>
		<form action="addTree" method="post">
			<!-- hidden input field for request_id -->
    		<input type="hidden" name="request_id" value="<%=request.getParameter("request_id")%>">
			<input 
				type="number" 
				name="size" 
				size="45" 
				placeholder="Size" 
				autofocus 
			/>
			<input
				type="number"
				name="height"
				size="45"
				placeholder="Height"
			/>
			<br/><br/>
			<input 
				type="text" 
				name="address" 
				size="100" 
				placeholder="Address" 
			/>
			<br/>
			<input
				type="number"
				name="distance"
				size="45"
				placeholder="Distance from home1"
				autofocus
			/>
			<h3>Picture: 1</h3>
			<input 
				type="file" 
				name="image1" 
				accept="image/*" 
			/>
			<h3>Picture: 2</h3>
			<input 
				type="file" 
				name="image2" 
				accept="image/*"
			/>
			<br/>
			<h3>Picture: 3</h3>
			<input 
				type="file" 
				name="image3" 
				accept="image/*"
			/>
			<br />
			<input
				type="submit"
				value="AddTree"
				style="padding: 5px; margin-top: 9px"
			/>
		</form>
	</div>
</body>
</html>

