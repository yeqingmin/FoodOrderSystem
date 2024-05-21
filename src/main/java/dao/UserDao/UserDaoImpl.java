package dao.UserDao;

import dao.BaseDao;
import pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDaoImpl implements UserDao{
    public User getUserById(Connection connection, int id) throws Exception {
        User user = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        if(null != connection){
            String sql = "SELECT * FROM users WHERE id = ?";
            pstm = connection.prepareStatement(sql);
            Object[] params = {id};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            if(rs.next()){
                user = new User();
                user.setUserId(rs.getInt("id"));
                user.setUserName(rs.getString("username"));
                user.setUserGender(rs.getString("gender"));
                if(rs.getBoolean("isDelete")){
                    user=null;
                }
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return user;
    }
    public int add(Connection connection, User user)
            throws Exception {
        PreparedStatement pstm = null;
        int flag = 0;
        if(null != connection){
            String sql = "INSERT INTO users (userId ,username, gender, isDelete) VALUES (?, ?, ?,?)";
            Object[] params = {user.getUserId(),user.getUserName(),user.getUserGender(),
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
        if(null != connection){
            String sql = "delete from users where id=?";
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
        if(null != connection){
            String sql = "update user set userId=?,userName=?,userGender=?," +
                    "userIsDelete=? where id=? ";
            Object[] params = {user.getUserId(),user.getUserName(),user.getUserGender(),
                    user.isDelete(),user.getUserId()};
            flag = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, null);
        }
        return flag;
    }
}
