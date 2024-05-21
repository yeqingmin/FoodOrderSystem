package pojo;

public class Login {
    private String password;
    private String name;
    private Integer correspondingID;
    private String role;
    private Boolean isDelete;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCorrespondingID() {
        return correspondingID;
    }

    public void setCorrespondingID(Integer correspondingID) {
        this.correspondingID = correspondingID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }
}
