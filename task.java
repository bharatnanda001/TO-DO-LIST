public class Task {
    private String name;
    private String dueDate;
    private boolean isCompleted;

    public Task(String name, String dueDate, boolean isCompleted) {
        this.name = name;
        this.dueDate = dueDate;
        this.isCompleted = isCompleted;
    }

    public String getName() {
        return name;
    }

    public String getDueDate() {
        return dueDate;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", isCompleted=" + isCompleted +
                '}';
    }
}
