import org.junit.Test;
import pojo.Merchant;
import pojo.Order;
import service.Dish.DishService;
import service.Dish.DishServiceImpl;
import service.Order.OrderService;
import service.Order.OrderServiceImpl;

import java.util.ArrayList;

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
    @Test
    public void getOrderList(){
        OrderService orderService=new OrderServiceImpl();
        ArrayList<Order> Orders=orderService.getOrderListByUserId(2,1,5);
        for(Order order:Orders){
            System.out.println(order);
        }
    }
    @Test
    public void getOrderTotalCountByUserIdTest(){
        OrderService orderService=new OrderServiceImpl();
        int result=orderService.getOrderTotalCountById(2);
        System.out.println(result);
    }
}
