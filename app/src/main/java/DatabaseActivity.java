//import android.os.Bundle;
//import android.util.Log;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//public class DatabaseActivity extends AppCompatActivity {
//
//    private static final String TAG = "DatabaseActivity";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_database);
//
//        connectToDatabase();
//    }
//
//    //???????????????
//
//    private void connectToDatabase() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Connection connect = null;
//                try {
//
//                    // load postgreSQL driver
//                    Class.forName("org.postgresql.Driver");
//
//                    // Database connection details
//                    String url = "jdbc:postgresql://your-database-endpoint:5432/your-database-name";
//                    String user = "your-username";
//                    String password = "your-password";
//
//                    // Connect to the database
//                    connect = DriverManager.getConnection(url, user, password);
//
//                    // Create a statement
//                    Statement statement = connect.createStatement();
//
//                    // Execute a query
//                    ResultSet resultSet = statement.executeQuery("SELECT id, name, age, desc, height FROM your_table");
//
//                    // Iterate through the result set
//                    while (resultSet.next()) {
//                        int id = resultSet.getInt("id");
//                        String name = resultSet.getString("name");
//                        int age = resultSet.getInt("age");
//                        String desc = resultSet.getString("desc");
//                        float height = resultSet.getFloat("height");
//
//                        // Log the fetched data
//                        Log.d(TAG, "Row fetched: ID = " + id + ", Name = " + name + ", Age = " + age + ", Description = " + desc + ", Height = " + height);
//                    }
//                } catch (ClassNotFoundException e) {
//                    Log.e(TAG, "PostgreSQL JDBC Driver not found", e);
//                } catch (SQLException e) {
//                    Log.e(TAG, "Connection Failed", e);
//                } finally {
//                    if (connect != null) {
//                        try {
//                            connect.close();
//                        } catch (SQLException e) {
//                            Log.e(TAG, "SQLException", e);
//                        }
//                    }
//                }
//            }
//        }).start();
//    }
//}
