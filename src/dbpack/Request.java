package dbpack;

public class Request {
    private String request_id;
    private String request_name;
    private String user_id;
    private String status;

    // Constructors
    public Request() {

    }

    public Request(String user_id, String status) {
        this.user_id = user_id;
        this.status = status;
    }

    // Getter and Setter methods
    public String getRequestId() {
        return request_id;
    }
    public void setRequestId(String request_id) {
        this.request_id = request_id;
    }
    public String getRequestName() {
        return request_name;
    }
    public void setRequestName(String request_name) {
        this.request_name = request_name;
    }
    public String getUserId() {
        return user_id;
    }
    public void setUserId(String user_id) {
        this.user_id = user_id;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}



// <select name="dropdown" id="dropdown">
// 			<% 
// 				String[] request_options = request.getParameter("request_options");
// 				for (String request_option : request_options) {
// 			%>
// 				<option value="<%= request_option %>"><%= request_option %></option>
// 			<%
// 				}
// 			%>
// 		</select>
