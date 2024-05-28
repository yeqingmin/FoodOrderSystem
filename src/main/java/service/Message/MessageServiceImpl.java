package service.Message;

import dao.BaseDao;
import dao.BookMessageDao.BookMessageDao;
import dao.BookMessageDao.BookMessageDaoImpl;
import dao.OrderMessageDao.OrderMessageDao;
import dao.OrderMessageDao.OrderMessageDaoImpl;
import pojo.BookMessage;
import pojo.OrderMessage;
import pojo.UMReview;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    }

    public void addOrderMessage(int userId , int orderId,String orderMessage){
        Connection connection=null;
        OrderMessage orderMessage1=new OrderMessage();
        orderMessage1.setUserId(userId);
        orderMessage1.setOrderId(orderId);
        orderMessage1.setOrderStatusMessage(orderMessage);
        try{
            connection= BaseDao.getConnection();
            orderMessageDao.add(connection,orderMessage1);
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
    }

    public ArrayList<BookMessage> getBookMessage(int userId){
        Connection connection=null;
        ArrayList<BookMessage> bookMessages=new ArrayList<>();
        try{
            connection= BaseDao.getConnection();
            bookMessages= bookMessageDao.getBookMessage(connection,userId);
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
        return bookMessages;
    }

    public ArrayList<OrderMessage> getOrderMessage(int userId){
        Connection connection=null;
        ArrayList<OrderMessage> orderMessages=new ArrayList<>();
        try{
            connection= BaseDao.getConnection();
            orderMessages= orderMessageDao.getOrderMessage(connection,userId);
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
        return orderMessages;
    }
}
