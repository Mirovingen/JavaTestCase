import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.util.Scanner;


public class Engine {

    public static String ssoid,ts,grp,type,subtype,url,orgid,formid,code,ltpa,sudirresponse,ymdh;
    static int insertions;
    static String format = "%-36s %-11s %-18s %-25s %-10s %-90s %-10s %-10s %-5s %-5s %-5s %-14s %n";
    public static void main(String[] args) throws ParseException {

        Connect();

    }

    public static void Connect() throws ParseException {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "120340")) {

            System.out.println("Connected to PostgreSQL database!");

            String sqlQuery = "INSERT INTO public.\"Smart\" (ssoid,ts, grp, type, subtype, url, orgid, formid, code, ltpa, sudirresponse,ymdh) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement preparedInsert = connection.prepareStatement(sqlQuery);
            Statement statement = connection.createStatement();

            //parseFile("test_case.csv", preparedInsert);
            //test_print(statement);

        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
    }

    public static void parseFile(String filename, PreparedStatement preparedInsert) throws SQLException, ParseException {

        try(FileInputStream fin = new FileInputStream(filename)){
            Scanner scan = new Scanner(fin);

            scan.useDelimiter(";");

            System.out.println(scan.nextLine());
            System.out.printf(format, "ssoid","ts", "grp", "type", "subtype", "url", "orgid", "formid", "code", "ltpa", "sudirresponse","ymdh");


            while (scan.hasNextLine())
            {
                parseLine(scan.nextLine());
                System.out.printf(format, ssoid, ts, grp, type, subtype, url, orgid, formid, code, ltpa, sudirresponse, ymdh);

                preparedInsert.setString(1, ssoid);
                preparedInsert.setInt(2, Integer.parseInt(ts));
                preparedInsert.setString(3, grp);
                preparedInsert.setString(4, type);
                preparedInsert.setString(5, subtype);
                preparedInsert.setString(6, url);
                preparedInsert.setString(7, orgid);
                preparedInsert.setString(8, formid);
                preparedInsert.setString(9, code);
                preparedInsert.setString(10, ltpa);
                preparedInsert.setString(11, sudirresponse);
                java.sql.Timestamp ds = new java.sql.Timestamp(new java.text.SimpleDateFormat("yyyy-MM-dd-HH").parse(ymdh).getTime());
                preparedInsert.setTimestamp(12, ds);

                insertions += preparedInsert.executeUpdate();


            }
            System.out.println(insertions);
        }catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void parseLine(String s)
    {

        int pos = 0;
        ssoid           = s.split(";")[pos++];
        ts              = s.split(";")[pos++];
        grp             = s.split(";")[pos++];
        type            = s.split(";")[pos++];
        subtype         = s.split(";")[pos++];
        url             = s.split(";")[pos++];
        if (url.startsWith("\"")){
            while (!url.endsWith("\""))
                url += s.split(";")[pos++];
            System.out.println(url);
        }
        orgid           = s.split(";")[pos++];
        formid          = s.split(";")[pos++];
        code            = s.split(";")[pos++];
        ltpa            = s.split(";")[pos++];
        sudirresponse   = s.split(";")[pos++];
        ymdh            = s.split(";")[pos++];
    }

    public static void test_print(Statement statement) throws SQLException {
        System.out.println("Reading records...");
        System.out.printf(format, "ssoid","ts", "grp", "type", "subtype", "url", "orgid", "formid", "code", "ltpa", "sudirresponse","ymdh");
       // ResultSet resultSet = statement.executeQuery("SELECT formid, count(*) AS cnt FROM public.\"Smart\" Group BY formid ORDER BY cnt DESC LIMIT(5)") ;
        ResultSet resultSet = statement.executeQuery("SELECT * FROM public.\"Smart\" Where (ts) >= (SELECT MAX(ts) FROM public.\"Smart\") -3600 ") ;
        int count = 0;
            while (resultSet.next()) {
                System.out.println(resultSet.getString("formid") + " : " +resultSet.getInt("cnt"));
                System.out.printf(format,   resultSet.getString("ssoid"),
                                            resultSet.getInt("ts"),
                                            resultSet.getString("grp"),
                                            resultSet.getString("type"),
                                            resultSet.getString("subtype"),
                                            resultSet.getString("url"),
                                            resultSet.getString("orgid"),
                                            resultSet.getString("formid"),
                                            resultSet.getString("code"),
                                            resultSet.getString("ltpa"),
                                            resultSet.getString("sudirresponse"),
                                            resultSet.getString("ymdh"));
                count++;
            }
        long epoch = 1499763594;
        System.out.println(epoch);
        String date = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new java.util.Date (epoch*1000));
        System.out.println(date);
    }


}
