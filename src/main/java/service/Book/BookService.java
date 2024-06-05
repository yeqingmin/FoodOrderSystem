package service.Book;

import dao.BaseDao;
import dao.BookDao.BookDao;
import dao.BookDao.BookDaoImpl;
import pojo.Book;

import java.sql.Connection;
import java.sql.SQLException;

public class BookService {
    BookDao bookDao=new BookDaoImpl();
    public int addBook(Book book){
        Connection connection=null;
        int flag=0;
        try{
            connection= BaseDao.getConnection();
            flag=bookDao.addBook(connection,book);
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
        return flag;
    }

}
