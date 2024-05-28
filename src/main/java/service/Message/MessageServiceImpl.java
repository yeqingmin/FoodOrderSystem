package service.Message;

import dao.BaseDao;
import dao.BookMessageDao.BookMessageDao;
import dao.BookMessageDao.BookMessageDaoImpl;
import dao.OrderMessageDao.OrderMessageDao;
import dao.OrderMessageDao.OrderMessageDaoImpl;
import pojo.BookMessage;
import pojo.OrderMessage;

import java.sql.Connection;
import java.sql.SQLException;

public class MessageServiceImpl implements MessageService {
    BookMessageDao bookMessageDao=new BookMessageDaoImpl();
    OrderMessageDao orderMessageDao=new OrderMessageDaoImpl();

    public void addBookMessage(int userId , int bookId ,String bookMessage){
        Connection connection=null;
        BookMessage bookMessage1=new BookMessage();
        bookMessage1.setUserId(userId);
        bookMessage1.setBookId(bookId);
        bookMessage1.setBookStatusMessage(bookMessage);
        try{
            connection= BaseDao.getConnection();
            bookMessageDao.add(connection,bookMessage1);
        }catch (Exception e){
            e.printStackTrace();
            try {
                System.out.println("rollback==================");
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return ;
    }

    public void addOrderMessage(int userId , int orderId,String orderMessage){
        Connection connection=null;
        OrderMessage orderMessage1=new OrderMessage();
        orderMessage1.setUserId(userId);
    }
}
