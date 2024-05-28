package dao.BookMessageDao;

import dao.BaseDao;
import pojo.BookMessage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BookMessageDaoImpl implements BookMessageDao{
    public int add(Connection connection, BookMessage bookMessage) throws Exception {
        PreparedStatement pstm = null;
        int flag = 0;
        if(null != connection){
            String sql = "INSERT INTO bookmessage (messageId, userId, bookStatusMessage, bookId) VALUES (?, ?, ?, ?,)";
            Object[] params ={bookMessage.getMessageId(),bookMessage.getUserId(),bookMessage.getBookStatusMessage(),bookMessage.getBookId()};
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
                bookMessageList.add(bookMessage);
            }
            BaseDao.closeResource(connection,preparedStatement,rs);
        }
        return bookMessageList;
    }
}
