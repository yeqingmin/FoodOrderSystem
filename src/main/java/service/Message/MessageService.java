package service.Message;

import com.google.protobuf.Message;
import pojo.BookMessage;
import pojo.OrderMessage;

import java.util.ArrayList;

public interface MessageService {
    public void addBookMessage(int userId , int bookId,String bookMessage);
    public void addOrderMessage(int userId , int orderId,String orderMessage);
    public ArrayList<BookMessage> getBookMessage(int userId);
    public ArrayList<OrderMessage> getOrderMessage(int userId);
    public ArrayList<OrderMessage> getOrderMessageListByUserId(Integer userId, int currentPageNo, int pageSize);
    public int getOrderMessageTotalCountByUserId(Integer UserId);
    public ArrayList<BookMessage> getBookMessageListByUserId(Integer userId, int currentPageNo, int pageSize);
    public int getBookMessageTotalCountByUserId(Integer UserId);
}
