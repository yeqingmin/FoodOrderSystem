package pojo;

public class Login {
    private String password;
    private String name;
    private Integer correspondingID;
    private String role;
    private Boolean isDelete;

    // Constructors, Getters, and Setters
    public Login() {}

    public Login(String password, String name, Integer correspondingID, String role, Boolean isDelete) {
        this.password = password;
        this.name = name;
        this.correspondingID = correspondingID;
        this.role = role;
        this.isDelete = isDelete;
    }

    // Getters and Setters
    // ...
}
