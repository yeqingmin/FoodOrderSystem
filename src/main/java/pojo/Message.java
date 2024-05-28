package pojo;
public class Message {

    private Integer messageId; // 消息ID

    private Integer userId; // 用户ID

    private String bookStatusMessage; // 预订确认信息

    private String orderStatusMessage; // 订单状态信息

    private boolean isDelete; // 是否删除

    public Message() {
    }

    // Getters and Setters
    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getBookStatusMessage() {
        return bookStatusMessage;
    }

    public void setBookStatusMessage(String bookStatusMessage) {
        this.bookStatusMessage = bookStatusMessage;
    }

    public String getOrderStatusMessage() {
        return orderStatusMessage;
    }

    public void setOrderStatusMessage(String orderStatusMessage) {
        this.orderStatusMessage = orderStatusMessage;
    }

    public boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }
}
