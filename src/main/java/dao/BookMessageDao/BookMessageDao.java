package dao.BookMessageDao;

import pojo.BookMessage;


import java.sql.Connection;
import java.util.ArrayList;

public interface BookMessageDao {
    public int add(Connection connection, BookMessage bookMessage)throws Exception;
    public ArrayList<BookMessage> getBookMessage(Connection connection, int userId) throws Exception;

}
