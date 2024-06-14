import dao.BaseDao;
import org.junit.Test;
import pojo.Dish;
import service.Dish.DishService;
import service.Dish.DishServiceImpl;

import java.sql.Connection;
import java.util.ArrayList;

public class AdvancedQueryTest {
    @Test
    public void dishAnalysisTest(){
//        Connection connection= BaseDao.getConnection();
        DishService dishService=new DishServiceImpl();
        ArrayList<Dish> dishes=dishService.getDishWithSalesAndUserListByMerchantId(1,1,5);
        for(Dish dish:dishes){
            System.out.println(dish);
        }
    }
}
