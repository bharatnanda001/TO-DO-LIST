import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/todolist";  // Change to your database details
    private static final String USER = "root"; // Your MySQL username
    private static final String PASSWORD = ""; // Your MySQL password

    private Connection connection;

    // Establish connection to the database
    public void connect() throws SQLException {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Failed to connect to the database.");
        }
    }

    // Close the database connection
    public void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    // Insert a new task into the database
    public void addTask(Task task) throws SQLException {
        String query = "INSERT INTO tasks (name, due_date, is_completed) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, task.getName());
            statement.setString(2, task.getDueDate());
            statement.setBoolean(3, task.isCompleted());
            statement.executeUpdate();
        }
    }

    // Retrieve all tasks from the database
    public List<Task> getTasks() throws SQLException {
        List<Task> tasks = new ArrayList<>();
        String query = "SELECT * FROM tasks";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String dueDate = resultSet.getString("due_date");
                boolean isCompleted = resultSet.getBoolean("is_completed");
                tasks.add(new Task(name, dueDate, isCompleted));
            }
        }
        return tasks;
    }

    // Update task status to completed
    public void markTaskAsCompleted(String taskName) throws SQLException {
        String query = "UPDATE tasks SET is_completed = ? WHERE name = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setBoolean(1, true);
            statement.setString(2, taskName);
            statement.executeUpdate();
        }
    }

    // Remove a task from the database
    public void removeTask(String taskName) throws SQLException {
        String query = "DELETE FROM tasks WHERE name = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, taskName);
            statement.executeUpdate();
        }
    }
}
