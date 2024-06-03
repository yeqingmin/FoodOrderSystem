import org.junit.Test;
import pojo.Merchant;
import pojo.User;
import service.Merchant.MerchantService;
import service.Merchant.MerchantServiceImpl;
import service.User.UserService;
import service.User.UserServiceImpl;

public class modifyTest {
    @Test
    public void modifyUserByIdTest(){
        User user= new User();
        user.setUserGender("male");
        user.setUserId(7);
        user.setUserName("admin");
        UserService userService = new UserServiceImpl();
        userService.modify(user);
    }

    @Test
    public void modifyMerchantByIdTest(){
        Merchant merchant= new Merchant();
        merchant.setMerchantName("xxxxx");
        merchant.setMerchantAddr("xsdwdqew");
        merchant.setMerchantId(8);
        MerchantService merchantService = new MerchantServiceImpl();
        merchantService.modifyMerchantById(merchant);
    }
}
