import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class TodoListApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DatabaseManager dbManager = new DatabaseManager();
        boolean running = true;

        try {
            dbManager.connect();
            System.out.println("Connected to the database.");
            
            while (running) {
                System.out.println("1. Add Task");
                System.out.println("2. Remove Task");
                System.out.println("3. Mark Task as Completed");
                System.out.println("4. Show Tasks");
                System.out.println("5. Exit");

                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter task name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter due date (yyyy-mm-dd): ");
                        String dueDate = scanner.nextLine();
                        Task newTask = new Task(name, dueDate, false);
                        dbManager.addTask(newTask);
                        break;
                    case 2:
                        System.out.print("Enter task name to remove: ");
                        String taskToRemove = scanner.nextLine();
                        dbManager.removeTask(taskToRemove);
                        break;
                    case 3:
                        System.out.print("Enter task name to mark as completed: ");
                        String taskToComplete = scanner.nextLine();
                        dbManager.markTaskAsCompleted(taskToComplete);
                        break;
                    case 4:
                        List<Task> tasks = dbManager.getTasks();
                        for (Task task : tasks) {
                            System.out.println(task);
                        }
                        break;
                    case 5:
                        running = false;
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                dbManager.disconnect();
                System.out.println("Disconnected from the database.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
