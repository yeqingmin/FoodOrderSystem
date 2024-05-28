package pojo;

public class BookMessage {

    private Integer messageId; // 消息ID
    private Integer userId; // 用户ID
    private String bookStatusMessage; // 预订确认信息
    private Integer bookId; // 预订ID
    private Boolean isDelete; // 是否删除

    public BookMessage() {
    }

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

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }
}