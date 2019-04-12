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


public class LastHourList extends HttpServlet  {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("output", GetLastHour());
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("reports/First.jsp");
        requestDispatcher.forward(req, resp);
    }


    protected Model GetLastHour(){
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "120340")) {

            System.out.println("Connected to PostgreSQL database!");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM public.\"Smart\" Where (ts) >= (SELECT MAX(ts) FROM public.\"Smart\") -3600 ");

            Model model = Model.getInstance();
            Output out;

            while (resultSet.next()) {

                out = new Output.builder()
                                            .ssoid(resultSet.getString("ssoid"))
                                            .ts(resultSet.getInt("ts"))
                                            .grp(resultSet.getString("grp"))
                                            .type(resultSet.getString("type"))
                                            .subtype(resultSet.getString("subtype"))
                                            .url(resultSet.getString("url"))
                                            .orgid(resultSet.getString("orgid"))
                                            .formid(resultSet.getString("formid"))
                                            .code(resultSet.getString("code"))
                                            .ltpa(resultSet.getString("ltpa"))
                                            .sudirresponse(resultSet.getString("sudirresponse"))
                                            .ymdh(resultSet.getString("ymdh")).build();
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
