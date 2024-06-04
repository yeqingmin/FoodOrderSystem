import org.junit.Test;
import pojo.Dish;
import pojo.OrderDetail;
import service.Dish.DishService;
import service.Dish.DishServiceImpl;
import service.Order.OrderService;
import service.Order.OrderServiceImpl;

import java.util.ArrayList;

public class dishTest {
    @Test
    public void testGetOrderedDish(){
        OrderService orderService=new OrderServiceImpl();
        DishService dishService=new DishServiceImpl();
        ArrayList<OrderDetail> details=orderService.getDetailsByOrderId(34);
        ArrayList<Dish> orderedDishes=new ArrayList<>();
        Dish prevDish=null;
        for(OrderDetail detail:details){
            int dishId=detail.getDishId();
            Dish dish=dishService.getDishById(dishId);
            dish.setTotalCount(dishService.countDishQuantity(dishId,34));
            if(prevDish!=null&&prevDish.getDishName().equals(dish.getDishName())){
                continue;
            }else{
                orderedDishes.add(dish);
            }
            prevDish=dish;
        }
        for(Dish dish:orderedDishes){
            System.out.println(dish);
        }
    }
}
