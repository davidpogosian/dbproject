import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import java.sql.SQLException;
import java.io.IOException;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import dbpack.RequestDAO;
import dbpack.UserDAO;
import dbpack.TreeDAO;
import dbpack.QuoteDAO;
import dbpack.Request;
import dbpack.Quote;
import dbpack.User;
import dbpack.Tree;

public class MyServlet extends HttpServlet {
	RequestDAO requestDAO = new RequestDAO();
	UserDAO userDAO = new UserDAO();
	TreeDAO treeDAO = new TreeDAO();
	QuoteDAO quoteDAO = new QuoteDAO();
	User user = null;

	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		try {
			doGet(req, res);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        switch(req.getServletPath()) {
			case "/login":
				req.getRequestDispatcher("login.jsp").forward(req, res);
				break;
			case "/handleNewQuote":
				handleNewQuote(req, res);
				break;
			case "/requestView":
				requestView(req, res);
				break;
			case "/handleLogin":
				try {
					handleLogin(req, res);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case "/handleRegister":
				handleRegister(req, res);
				break;
			case "/addTree":
				addTree(req, res);
				break;
			case "/handleNewRequest":
				handleNewRequest(req, res);
				break;
			case "/daveView":
				try {
					daveView(req, res);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case "/handleDatabaseReset":
				try {
					userDAO.resetDatabase();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				res.sendRedirect("rootView.jsp");
				break;
		}
	}

	public void handleRegister(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		User newUser = new User();
		newUser.setUsername(req.getParameter("username"));
		newUser.setEmail(req.getParameter("email"));
		newUser.setFirstName(req.getParameter("firstName"));
		newUser.setLastName(req.getParameter("lastName"));
		newUser.setPassword(req.getParameter("password"));
		newUser.setBirthday(req.getParameter("birthday"));
		newUser.setAddressStreetNum(req.getParameter("adress_street_num"));
		newUser.setAddressStreet(req.getParameter("adress_street"));
		newUser.setAddressCity(req.getParameter("adress_city"));
		newUser.setAddressState(req.getParameter("adress_state"));
		newUser.setAddressZipCode(req.getParameter("adress_zip_code"));
		newUser.setCreditCardNumber(req.getParameter("credit_cart_number"));
		newUser.setCreditCardExpirationDate(req.getParameter("credit_card_expiration_date"));
		newUser.setCreditCardSecurityCode(req.getParameter("credit_card_security_code"));
		String confirm = req.getParameter("confirmation");

		if (!newUser.getPassword().equals(confirm)) {
			System.out.println("Password and Password Confirmation do not match");
			req.setAttribute("registerErrorConfirm","Registration failed: Password and Password Confirmation do not match.");
			req.getRequestDispatcher("register.jsp").forward(req, res);
		}

		try {
			userDAO.addUser(newUser);
			System.out.println("Registration Successful! Added to database");
			res.sendRedirect("login.jsp");
		} catch (SQLException e) {
			System.out.println("Username or email taken, please enter new username or email");
			req.setAttribute("registerErrorTaken","Registration failed: Username or email taken, please enter a new username or email.");
			req.getRequestDispatcher("register.jsp").forward(req, res);
			e.printStackTrace();
		}
	}

	public void handleLogin(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, SQLException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		try {
			user = userDAO.authenticate(email, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (user == null) {
			req.setAttribute("loginErrorString","Login Failed: Please check your credentials.");
			// Can't use "res.sendRedirect("login.jsp");" because attribute don't stick
			req.getRequestDispatcher("login.jsp").forward(req, res);
			return;
		}
		switch (user.getUserId()) {
			case "1": // root
				req.getRequestDispatcher("rootView.jsp").forward(req, res);
				break;
			case "2": // dave
				daveView(req, res);
				break;
			default:
				userView(req, res);
				break;
		}
	}

	public void handleNewRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Request newRequest = new Request();
		newRequest.setRequestName(req.getParameter("request_name"));
		newRequest.setUserId(user.getUserId());
		newRequest.setStatus("pending");
		try {
			requestDAO.addRequest(newRequest);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		userView(req, res);
	}

	public void handleNewQuote(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Quote newQuote = new Quote();
		newQuote.setRequestId(Integer.parseInt(req.getParameter("request_id")));
		newQuote.setPrice(Double.parseDouble(req.getParameter("price")));
		newQuote.setStartDate(convertStringToTimestamp(req.getParameter("start_date")));
		newQuote.setEndDate(convertStringToTimestamp(req.getParameter("end_date")));
		newQuote.setStatus("pending");
		try {
			quoteDAO.addQuote(newQuote);
			daveView(req, res);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addTree(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Tree newTree = new Tree();
		newTree.setRequestId(Integer.parseInt(req.getParameter("request_id")));
		newTree.setHeight(Double.parseDouble(req.getParameter("height")));
		newTree.setDistance(Double.parseDouble(req.getParameter("distance")));
		newTree.setAddress(req.getParameter("address"));
		newTree.setSize(Double.parseDouble(req.getParameter("size")));
		try {
			treeDAO.addTree(newTree);
			System.out.println("Tree added to database");
			requestView(req, res);
		} catch (SQLException e) {
			System.out.println("Tree not added to database");
			e.printStackTrace();
		}
	}
	
	public void userView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setAttribute("first_name", user.getFirstName());
		try {
			req.setAttribute("requests", requestDAO.getRequestsByUserId(user.getUserId()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		req.getRequestDispatcher("userView.jsp").forward(req, res);
	}

	public void requestView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String request_id = req.getParameter("request_id");
		try {
			req.setAttribute("trees", treeDAO.getTreesByRequestId(request_id));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		req.getRequestDispatcher("requestView.jsp").forward(req, res);
	}

	public void daveView(HttpServletRequest req, HttpServletResponse res) throws SQLException, ServletException, IOException {
		req.setAttribute("requests", requestDAO.getAllPendingRequests());
		req.getRequestDispatcher("daveView.jsp").forward(req, res);
	}

	// utility
	private Timestamp convertStringToTimestamp(String dateString) {
        if (dateString != null && !dateString.isEmpty()) {
            try {
                // Define the date format based on the format used in the form
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                // Parse the string and convert it to a Timestamp
                java.util.Date parsedDate = dateFormat.parse(dateString);
                return new Timestamp(parsedDate.getTime());
            } catch (ParseException e) {
                // Handle the parsing exception (invalid date format)
                e.printStackTrace(); // Log the exception or handle it appropriately
            }
        }
        return null; // Return null if the dateString is null or empty
    }
}


