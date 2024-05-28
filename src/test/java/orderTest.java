import org.junit.Test;
import service.Dish.DishService;
import service.Dish.DishServiceImpl;

public class orderTest {
    @Test
    public void addDishToOrderTest(){
        DishService dishService=new DishServiceImpl();
        int quantity = dishService.addDishToOrder(1, 1);
        System.out.println(quantity);
    }
    @Test
    public void deleteDishFromOrderTest(){
        DishService dishService=new DishServiceImpl();
        int quantity = dishService.deleteDishFromOrder(1, 1);
        System.out.println(quantity);
    }
}
