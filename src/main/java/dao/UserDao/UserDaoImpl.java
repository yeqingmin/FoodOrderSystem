package dao.UserDao;

import dao.BaseDao;
import pojo.User;

import java.sql.*;
import java.util.ArrayList;

public class UserDaoImpl implements UserDao {
    public User getUserById(Connection connection, int id) throws Exception {
        User user = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        if (null != connection) {
            String sql = "SELECT * FROM user WHERE userId = ?";
            pstm = connection.prepareStatement(sql);
            Object[] params = {id};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("userId"));
                user.setUserName(rs.getString("userName"));
                user.setUserGender(rs.getString("userGender"));
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return user;
    }

    public int add(Connection connection, User user)
            throws Exception {
        PreparedStatement pstm = null;
        int flag = 0;
        if (null != connection) {
            String sql = "INSERT INTO User (userId ,username, gender, isDelete) VALUES (?, ?, ?,?)";
            Object[] params = {user.getUserId(), user.getUserName(), user.getUserGender(),
                    user.isDelete()};
            flag = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, null);
        }
        return flag;
    }

    public int deleteUserById(Connection connection, int id)
            throws Exception {
        // TODO Auto-generated method stub
        PreparedStatement pstm = null;
        int flag = 0;
        if (null != connection) {
            String sql = "delete from User where userId=?";
            Object[] params = {id};
            flag = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, null);
        }
        return flag;
    }

    public int modify(Connection connection, User user)
            throws Exception {
        // TODO Auto-generated method stub
        int flag = 0;
        PreparedStatement pstm = null;
        if (null != connection) {
            String sql = "update User set userId=?,userName=?,userGender=?," +
                    "userIsDelete=? where userId=? ";
            Object[] params = {user.getUserId(), user.getUserName(), user.getUserGender(),
                    user.isDelete(), user.getUserId()};
            flag = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, null);
        }
        return flag;
    }

    @Override
    public ArrayList<User> getUserList(Connection connection) throws SQLException {
        ArrayList<User> userList = new ArrayList<>();
        PreparedStatement pstm = null;
        if (null != connection) {
            String sql = "select * from `user`";
            //因为这里没有参数，所以不用预编译
            // 执行SQL语句
            try (Statement statement = connection.createStatement();
                 ResultSet rs = statement.executeQuery(sql)) {
                // 遍历结果集
                while (rs.next()) {
                    User user = new User();
                    user = new User();
                    user.setUserId(rs.getInt("userId"));
                    user.setUserName(rs.getString("userName"));
                    user.setUserGender(rs.getString("userGender"));
                    userList.add(user);
                }
                BaseDao.closeResource(null, pstm, rs);
            }
        }
        return userList;
    }
}
