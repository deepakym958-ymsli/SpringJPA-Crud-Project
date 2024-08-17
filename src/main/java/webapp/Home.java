package webapp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.ldap.Rdn;
import javax.sound.midi.Soundbank;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/submit")
public class Home extends HttpServlet {
	
	@Override
	public void init() throws ServletException {
		super.init();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String message = req.getParameter("message");
		System.out.println(name + email + message);
		String data = name + " " + email + " " + message;
		
		ArrayList<User> userArray = insert(name, email, message);
		req.setAttribute("data", data);
		req.setAttribute("userArray", userArray);
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("show.jsp");
		requestDispatcher.forward(req, resp);
//		requestDispatcher.include(req, resp);
		//redirects and adds params to the url
//		HttpSession session = req.getSession();
//		session.setAttribute("data", data);
//		session.setMaxInactiveInterval(2); //in sec
//		resp.sendRedirect("show.jsp");
	}
	
	@Override
	public void destroy() {
		super.destroy();
	}
	
	public ArrayList<User> insert(String name, String email, String message) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<User> data = new ArrayList<User>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/userdata", "root", "root");            
            String insertQuery = "INSERT INTO userinfo (name, email, message) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email); 
            preparedStatement.setString(3, message);
            preparedStatement.executeUpdate();
            String selectQuery = "SELECT * FROM userinfo";
            preparedStatement = connection.prepareStatement(selectQuery);
            resultSet = preparedStatement.executeQuery();            
            while (resultSet.next()) {
                User user = new User();
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setMessage(resultSet.getString("message"));
                data.add(user);
                System.out.println(user);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return data;
    }

}

