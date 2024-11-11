import java.sql.*;

public class Task {
    private String name;
    private boolean isComplete;

    public Task(String name, boolean isComplete){
        this.name = name;
        this.isComplete = isComplete;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public void insertTask(){
        try {
            Connection dbConnection = DBConnection.getInstance().getConnection();
            Statement stmt = dbConnection.createStatement();
            PreparedStatement insertStmt =
                    dbConnection.prepareStatement("INSERT INTO todo (task, status) VALUES (?, ?);");
            insertStmt.setString(1, this.name);
            insertStmt.setInt(2, (this.isComplete ? 1: 0));
            int rows = insertStmt.executeUpdate();
            System.out.println("Rows affected: " + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void retrieveTasks(){
        try {
            Connection dbConnection = DBConnection.getInstance().getConnection();
            Statement stmt = dbConnection.createStatement();
            String query = "SELECT id, task, status FROM todo";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                //Display values
                String row = "ID: " + rs.getInt("id") +
                        " Task: " + rs.getString("task") +
                        " Status: " + rs.getInt("status") + "\n";
                System.out.print(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // public void updateTask(){
    //
    //}

    public String toString(){
        return "Task: " + this.name + "\nStatus: " + (this.isComplete ? "1": "0");
    }
}