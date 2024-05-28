package dao.LoginDao;

import dao.BaseDao;
import pojo.Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LoginDaoImpl implements LoginDao{
    @Override
    public int addLogin(Connection connection, Login login) throws Exception {
        PreparedStatement pstm = null;
        int updateRows = 0;
        if(null != connection){
            String sql = "insert into login (password, name, correspondingID, role) VALUES (?, ?, ?, ?)";
            Object[] params = {login.getPassword(),login.getName(),login.getCorrespondingID(),login.getRole()};
            updateRows = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, null);
        }
        return updateRows;
    }

    @Override
    public Login getLoginUser(Connection connection, String name,String password)
            throws Exception {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Login login = null;
        if(null != connection){
            String sql = "select * from login where name=? and password=?";
            Object[] params = {name,password};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            if(rs.next()){
                login = new Login();
                login.setRole(rs.getString("role"));
                login.setCorrespondingID((rs.getInt("correspondingId")));
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return login;
    }
    @Override
    public int deleteUserById(Connection connection,String name , String password) throws Exception {
        PreparedStatement pstm = null;
        int flag = 0;
        if (null != connection) {
            String sql = "delete from login where password=? and name = ?";
            Object[] params = {password,name};
            flag = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, null);
        }
        return flag;
    }
}
