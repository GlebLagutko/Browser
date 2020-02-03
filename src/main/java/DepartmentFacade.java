import javax.sql.DataSource;
import java.sql.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DepartmentFacade{


    public void insertDepartment(Department department) {
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            String sql = "INSERT INTO new_schema.departments VALUES (?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, department.getName());
                preparedStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException ignored) {
        }
    }

    public List<Department> findDepartmentByName(String name) {
        List<Department> department = null;
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            String sql = "SELECT * FROM new_schema.departments WHERE name = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, name);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next())
                    department.add(new Department(resultSet.getString(2)));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException ex) {
        }

        return department;
    }

    public void deleteDepartmentByName(String name) {
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            String sql = "DELETE FROM new_schema.departments WHERE name = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, name);
                preparedStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException ex) {
        }
    }

    public Set<Department> getAllDepartments() {
        Set<Department> departments = new HashSet<>();
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            String sql = "SELECT * FROM new_schema.departments";
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);
                if (resultSet.next())
                    departments.add(new Department(resultSet.getString(2)));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException ex) {
        }
        return departments;
    }


}
