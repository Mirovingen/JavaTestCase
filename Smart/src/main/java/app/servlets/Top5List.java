package app.servlets;

import app.model.Model;
import app.model.Output;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;


public class Top5List extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Model model = GetTop5();
        req.setAttribute("top5", model);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("reports/Second.jsp");
        requestDispatcher.forward(req, resp);

    }

    protected Model GetTop5(){
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "120340")) {

            System.out.println("Connected to PostgreSQL database!");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT formid, count(*) AS cnt FROM public.\"Smart\" Group BY formid ORDER BY cnt DESC LIMIT(5)");

            Model model = Model.getInstance();
            Output out;

            while (resultSet.next()) {
                out = new Output.builder()
                        .formid(resultSet.getString("formid")).build();
                model.add(out);
            }


            return model;

        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
            return null;
        }

    }
}
