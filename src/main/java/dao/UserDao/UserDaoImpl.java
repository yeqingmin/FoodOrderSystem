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
            String sql = "SELECT * FROM user WHERE isDelete=0 and userId = ?";
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
            String sql = "INSERT INTO user (userName, userGender) VALUES (?, ?)";
            Object[] params = {user.getUserName(), user.getUserGender()};
            flag = BaseDao.executeAdd(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, null);
        }
        return flag;
    }

    public int logicDeleteUserById(Connection connection, int id)
            throws Exception {
        // TODO Auto-generated method stub
        PreparedStatement pstm = null;
        int flag = 0;
        if (null != connection) {
            String sql = "update user set isDelete=1 where userId=?";
            Object[] params = {id};
            flag = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, null);
        }
        return flag;
    }

    public int modify(Connection connection, User user)
            throws Exception {
        int flag = 0;
        PreparedStatement pstm = null;
        if (null != connection) {
            String sql = "update user set userName=?,userGender=? where userId=? ";
            Object[] params = {user.getUserName(), user.getUserGender(), user.getUserId()};
            flag = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, null);
        }
        return flag;
    }

    @Override
    public ArrayList<User> getUserList(Connection connection, int currentPageNo, int pageSize) throws SQLException {
        //todo 原来的代码
        //分页查询的代码

        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<User> userList = new ArrayList<User>();
        if (connection != null) {
            String sql = "select * from user where isDelete=0 order by userId limit ?,?";
            currentPageNo = (currentPageNo - 1) * pageSize;

            Object[] params = {currentPageNo, pageSize};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("userId"));
                user.setUserName(rs.getString("userName"));
                user.setUserGender(rs.getString("userGender"));
                userList.add(user);
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return userList;
    }

    @Override
    public int getUserTotalCount(Connection connection) throws SQLException {
        int count = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        if (null != connection) {
            String sql = "select count(*) from user where isDelete=0";
            pstm = connection.prepareStatement(sql);
            Object[] params = {};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            if (rs.next()) {
                count = rs.getInt(1);
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return count;
    }

}

