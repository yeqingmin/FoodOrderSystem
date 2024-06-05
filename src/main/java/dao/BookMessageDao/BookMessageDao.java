package dao.BookMessageDao;

import pojo.BookMessage;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface BookMessageDao {
    public int add(Connection connection, BookMessage bookMessage)throws Exception;
    public ArrayList<BookMessage> getBookMessage(Connection connection, int userId) throws Exception;
    public ArrayList<BookMessage> getBookMessageByUserId(Connection connection, int userId, int currentPageNo, int pageSize) throws Exception;
    public int getBookMessageCountByUserId(Connection connection,int userId) throws SQLException;

}
