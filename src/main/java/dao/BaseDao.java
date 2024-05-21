package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author wangqingyi
 * 该类实现一些基本的数据库操作，连接数据库和基础的增删改查包括关闭资源
 */
public class BaseDao {
    private static final String driver;
    private static final String url;
    private static final String userName;
    private static final String password;

    /*
     * 静态代码块，读取配置文件
     */
    static {
        //读取二进制文件
        //先读取配置文件
        Properties params=new Properties();
        String configFile="db.properties";
        InputStream inputStream= BaseDao.class.getResourceAsStream(configFile);
        try{
            params.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        driver=params.getProperty("driver");
        url=params.getProperty("url");
        userName=params.getProperty("userName");
        password=params.getProperty("password");
    }

    /**
     * @return 获取数据库连接
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url,userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 获取查找结果
     * @param connection
     * @param pstm
     * @param rs
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public static ResultSet execute(Connection connection, PreparedStatement pstm, ResultSet rs,
                                    String sql, Object[] params) throws SQLException {
        //将参数注入pstm与编译后的sql
        pstm=connection.prepareStatement(sql);
        for(int i=0;i<params.length;i++){
            pstm.setObject(i+1,params[i]);
        }
        rs= pstm.executeQuery();
        return rs;
    }

    /**
     * 更新操作
     * @param connection
     * @param pstm
     * @param sql
     * @param params
     * @return 返回更新的行数
     * @throws SQLException
     */
    public static int execute(Connection connection,PreparedStatement pstm,
                              String sql,Object[] params) throws Exception{
        int updateRows = 0;
        pstm = connection.prepareStatement(sql);
        for(int i = 0; i < params.length; i++){
            pstm.setObject(i+1, params[i]);
        }
        updateRows = pstm.executeUpdate();
        return updateRows;
    }

    public static boolean closeResource(Connection connection,PreparedStatement pstm,ResultSet resultSet){
        boolean flag = true;
        if(resultSet != null){
            try {
                resultSet.close();
                resultSet = null;//GC回收
            } catch (SQLException e) {
                e.printStackTrace();
                flag = false;
            }
        }
        if(pstm != null){
            try {
                pstm.close();
                pstm = null;//GC回收
            } catch (SQLException e) {
                e.printStackTrace();
                flag = false;
            }
        }
        if(connection != null){
            try {
                connection.close();
                connection = null;//GC回收
            } catch (SQLException e) {
                e.printStackTrace();
                flag = false;
            }
        }
        return flag;
    }
}
