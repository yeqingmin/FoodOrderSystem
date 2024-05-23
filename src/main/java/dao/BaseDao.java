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
    private static  String driver;
    private static  String url;
    private static String userName;
    private static  String password;

    //静态代码块,在类加载的时候执行
    static{
        init();
    }


    //初始化连接参数,从配置文件里获得
    public static void init(){
        Properties params=new Properties();
        String configFile = "db.properties";
        InputStream is= BaseDao.class.getClassLoader().getResourceAsStream(configFile);
        try {
            params.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver=params.getProperty("driver");
        url=params.getProperty("url");
        userName=params.getProperty("user");
        password=params.getProperty("password");

    }

    /**
     * @return 获取数据库连接
     */
    public static Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, userName, password);
        } catch (Exception e) {
            // TODO Auto-generated catch block
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
