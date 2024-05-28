package service.Message;

import pojo.BookMessage;
import pojo.OrderMessage;

import java.util.ArrayList;

public interface MessageService {
    public void addBookMessage(int userId , int bookId,String bookMessage);
    public void addOrderMessage(int userId , int orderId,String orderMessage);
    public ArrayList<BookMessage> getBookMessage(int userId);
    public ArrayList<OrderMessage> getOrderMessage(int userId);
}
