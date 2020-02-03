import javax.sql.DataSource;
import java.sql.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserFacade {

    public void insertUser(User user) {
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            String sql = "INSERT INTO new_schema.users(name,surname,department_id) VALUES (?,?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2,user.getSurname());
                preparedStatement.setInt(3,user.getDepartnmentID());
                preparedStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException ignored) {
        }
    }

    public List<User> findDepartmentByName(String name) {
        List<User> department = null;
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            String sql = "SELECT * FROM new_schema.users WHERE name = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, name);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next())
                    department.add(new User(resultSet.getString(2),resultSet.getString(3),resultSet.getInt(4)));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException ex) {
        }

        return department;
    }

    public void deleteDepartmentByName(String name) {
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            String sql = "DELETE FROM new_schema.users WHERE name = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, name);
                preparedStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException ex) {
        }
    }

    public Set<User> getAllDepartments() {
        Set<User> users = new HashSet<>();
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            String sql = "SELECT * FROM new_schema.users";
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);
                if (resultSet.next())
                    users.add(new User(resultSet.getString(2),resultSet.getString(3),resultSet.getInt(4)));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException ex) {
        }
        return users;
    }


}
