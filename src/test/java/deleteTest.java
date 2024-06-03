import org.junit.Test;
import service.Merchant.MerchantService;
import service.Merchant.MerchantServiceImpl;

public class deleteTest {
    @Test
    public void deleteMerchantByIdTest(){
        MerchantService merchantService=new MerchantServiceImpl();
        boolean result=merchantService.deleteMerchantById(1);
        System.out.println(result);
    }
}
