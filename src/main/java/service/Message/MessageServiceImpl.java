package service.Message;

import dao.BaseDao;
import dao.BookMessageDao.BookMessageDao;
import dao.BookMessageDao.BookMessageDaoImpl;
import dao.OrderDao.OrderDao;
import dao.OrderDao.OrderDaoImpl;
import dao.OrderMessageDao.OrderMessageDao;
import dao.OrderMessageDao.OrderMessageDaoImpl;
import pojo.BookMessage;
import pojo.OrderMessage;
import pojo.UDReview;
import pojo.UMReview;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageServiceImpl implements MessageService {
    BookMessageDao bookMessageDao = new BookMessageDaoImpl();
    OrderMessageDao orderMessageDao = new OrderMessageDaoImpl();

    public void addBookMessage(int userId, int bookId, String bookMessage) {
        //添加信息需要调用orderDao查看订单状态等拼接信息
        Connection connection = null;
        BookMessage bookMessage1 = new BookMessage();
        bookMessage1.setUserId(userId);
        bookMessage1.setBookId(bookId);
        bookMessage1.setBookStatusMessage(bookMessage);
        try {
            connection = BaseDao.getConnection();
            bookMessageDao.add(connection, bookMessage1);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                System.out.println("rollback==================");
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
    }

    public void addOrderMessage(int userId, int orderId, String orderMessage) {
        Connection connection = null;
        OrderMessage orderMessage1 = new OrderMessage();
        orderMessage1.setUserId(userId);
        orderMessage1.setOrderId(orderId);
        orderMessage1.setOrderStatusMessage(orderMessage);
        try {
            connection = BaseDao.getConnection();
            orderMessageDao.add(connection, orderMessage1);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                System.out.println("rollback==================");
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
    }

    public ArrayList<BookMessage> getBookMessage(int userId) {
        Connection connection = null;
        ArrayList<BookMessage> bookMessages = new ArrayList<>();
        try {
            connection = BaseDao.getConnection();
            bookMessages = bookMessageDao.getBookMessage(connection, userId);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                System.out.println("rollback==================");
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return bookMessages;
    }

    public ArrayList<OrderMessage> getOrderMessage(int userId) {
        Connection connection = null;
        ArrayList<OrderMessage> orderMessages = new ArrayList<>();
        try {
            connection = BaseDao.getConnection();
            orderMessages = orderMessageDao.getOrderMessage(connection, userId);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                System.out.println("rollback==================");
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return orderMessages;
    }


    public ArrayList<OrderMessage> getOrderMessageListByUserId(Integer userId, int currentPageNo, int pageSize) {
        Connection connection = null;
        ArrayList<OrderMessage> orderMessages = new ArrayList<>();
        try {
            connection = BaseDao.getConnection();
            orderMessages = orderMessageDao.getOrderMessageByUserId(connection, userId, currentPageNo, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                System.out.println("rollback==================");
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return orderMessages;

    }

    public int getOrderMessageTotalCountByUserId(Integer UserId) {
        Connection connection = null;
        int count = 0;
        try {
            connection = BaseDao.getConnection();
            count = orderMessageDao.getOrderMessageCountByUserId(connection, UserId);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                System.out.println("rollback==================");
                connection.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return count;
    }

    public ArrayList<BookMessage> getBookMessageListByUserId(Integer userId, int currentPageNo, int pageSize) {
        Connection connection = null;
        ArrayList<BookMessage> BookMessages = new ArrayList<>();
        try {
            connection = BaseDao.getConnection();
            BookMessages = bookMessageDao.getBookMessageByUserId(connection, userId, currentPageNo, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                System.out.println("rollback==================");
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return BookMessages;

    }

    public int getBookMessageTotalCountByUserId(Integer UserId) {
        Connection connection = null;
        int count = 0;
        try {
            connection = BaseDao.getConnection();
            count = bookMessageDao.getBookMessageCountByUserId(connection, UserId);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                System.out.println("rollback==================");
                connection.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return count;
    }
}
