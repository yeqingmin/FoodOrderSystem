package dao.BookDao;

import dao.BaseDao;
import pojo.Book;
import pojo.Order;
import pojo.UDReview;
import pojo.UMReview;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao{
    public int addBook(Connection connection, Book book) throws Exception{
        PreparedStatement pstm = null;
        int flag = 0;
        if(null != connection){
            String sql = "INSERT INTO `book` (`userId`, `merchantId`, `bookStartTime`,`bookEndTime`, `bookStatus`) VALUES (?, ?, ?, ?, ?)";
            Object[] params ={book.getUserId(),book.getMerchantId(),book.getBookStartTime(),book.getBookEndTime(),book.getBookStatus()};
            flag = BaseDao.executeAdd(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, null);
        }
        return flag;
    }
    public int deleteBook(Connection connection, Book book)
            throws Exception {
        PreparedStatement pstm = null;
        int flag = 0;
        if(null != connection){
            int id=book.getBookId();
            String sql = "delete from `book` where bookId=?";
            Object[] params = {id};
            flag = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, null);
        }
        return flag;
    }
    public List<Book> getUserOrderHistory(Connection connection, int userId) throws Exception{
        List<Book> books = new ArrayList<>();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        if(connection != null){
            String sql = "SELECT *\n" +
                    "FROM book\n" +
                    "WHERE userId = ? and bookStatus=valid";
            Object[] params ={userId};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            while(rs.next()){
                Book book= new Book();
                book.setBookId(rs.getInt("bookId"));
                book.setUserId(rs.getInt("userId"));
                book.setMerchantId(rs.getInt("merchantId"));
                book.setBookStatus(rs.getBoolean("bookStatus"));
                book.setBookStartTime(rs.getTime("bookStartTime"));
                book.setBookEndTime(rs.getTime("bookEndTime"));
                books.add(book);
            }
            BaseDao.closeResource(null, pstm, rs);
            BaseDao.closeResource(null, pstm, rs);
        }
        return books;
    }

    public int modifyBook(Connection connection, Book book)
            throws Exception {
        int flag = 0;
        int id =book.getBookId();
        PreparedStatement pstm = null;
        if(null != connection){
            String sql = "UPDATE `book` " +
                    "SET `userId` = ?, " +
                    "`merchantId` = ?, " +
                    "`bookEndTime` = ?, " +
                    "`bookStatus` = ?, " +
                    "`bookStartTime` = ? " +
                    "WHERE `bookId` = ?";
            Object[] params = {book.getUserId(),book.getMerchantId(),book.getBookEndTime(),book.getBookStatus(),book.getBookStartTime(),id};
            flag = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, null);
        }
        return flag;
    }
}
