package webapp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String updatedValue = req.getParameter("updatedValue");
        String parameter = req.getParameter("updateParameter");
        
        
        ArrayList<User> userArray = updateRecord(email, updatedValue, parameter);
        
        req.setAttribute("userArray", userArray);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("show.jsp");
        requestDispatcher.forward(req, resp);
    }
    
    public ArrayList<User> updateRecord(String email, String upadtedValue, String parameter) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<User> data = new ArrayList<User>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/userdata", "root", "root");
            
            // Delete query
            String deleteQuery = "Update userinfo set " + parameter + "= ? where email = ?";
            preparedStatement = connection.prepareStatement(deleteQuery);
            
            preparedStatement.setString(1, upadtedValue);
            preparedStatement.setString(2, email);
            preparedStatement.executeUpdate();
            
            // Retrieve the updated list
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

