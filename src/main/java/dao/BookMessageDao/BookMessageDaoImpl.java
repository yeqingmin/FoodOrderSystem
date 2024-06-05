package dao.BookMessageDao;

import dao.BaseDao;
import pojo.BookMessage;
import pojo.UMFavor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookMessageDaoImpl implements BookMessageDao{
    public int add(Connection connection, BookMessage bookMessage) throws Exception {
        PreparedStatement pstm = null;
        int flag = 0;
        if(null != connection){
            String sql = "INSERT INTO bookmessage (userId, bookStatusMessage, bookId) VALUES (?, ?, ?)";
            Object[] params ={bookMessage.getUserId(),bookMessage.getBookStatusMessage(),bookMessage.getBookId()};
            flag = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, null);
        }
        return flag;
    }
    public ArrayList<BookMessage> getBookMessage(Connection connection, int userId) throws Exception{
        PreparedStatement preparedStatement=null;
        ArrayList<BookMessage> bookMessageList = new ArrayList<>();
        ResultSet rs=null;
        if(null != connection){
            String sql="select * from bookmessage where userId=?";
            Object[] params ={userId};
            rs=BaseDao.execute(connection,preparedStatement,rs,sql,params);
            while(rs.next()){
                BookMessage bookMessage=new BookMessage();
                bookMessage.setBookId(rs.getInt("bookId"));
                bookMessage.setMessageId(rs.getInt("messageId"));
                bookMessage.setUserId(rs.getInt("userId"));
                bookMessage.setBookStatusMessage(rs.getString("bookStatusMessage"));
                bookMessage.setCreateTime(rs.getDate("createTime"));
                bookMessageList.add(bookMessage);
            }
            BaseDao.closeResource(connection,preparedStatement,rs);
        }
        return bookMessageList;
    }
    public ArrayList<BookMessage> getBookMessageByUserId(Connection connection, int userId, int currentPageNo, int pageSize) throws Exception{
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<BookMessage> bookMessageList = new ArrayList<>();
        if (connection != null) {
            String sql="select * from `bookmessage` where userId= ?  limit ?,?";
            currentPageNo = (currentPageNo - 1) * pageSize;

            Object[] params = {userId, currentPageNo,pageSize};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            while (rs.next()) {
                BookMessage bookMessage=new BookMessage();
                bookMessage.setBookStatusMessage(rs.getString("bookStatusMessage"));
                bookMessage.setCreateTime(rs.getDate("createTime"));
                bookMessage.setBookId(rs.getInt("bookId"));
                bookMessageList.add(bookMessage);
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return bookMessageList;
    }
    public int getBookMessageCountByUserId(Connection connection,int userId) throws SQLException {
        int count=0;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        if (null != connection) {
            String sql = "select count(*) from `bookmessage` where userId=?";
            pstm = connection.prepareStatement(sql);
            Object[] params = {userId};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            if(rs.next()){
                count = rs.getInt(1);
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return count;
    }
}
