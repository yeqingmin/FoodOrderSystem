package dao.BookDao;

import pojo.Book;
import pojo.Order;
import pojo.UMReview;

import java.sql.Connection;
import java.util.List;

public interface BookDao {

    int addBook(Connection connection, Book book) throws Exception;

    int deleteBook(Connection connection, Book book) throws Exception;

    List<Book> getUserOrderHistory(Connection connection,int userId) throws Exception;

    public int modifyBook(Connection connection, Book book) throws Exception;
}
