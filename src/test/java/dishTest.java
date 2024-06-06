import org.junit.Test;
import pojo.Dish;
import pojo.DishPrice;
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

    @Test
    public void testDsihPriceAdd(){
        DishService dishService=new DishServiceImpl();
        Dish dish=new Dish();
        dish.setDishId(2);
        dish.setMerchantId(1);
        dish.setDishName("薯条");
        dish.setDishCategory("主食");
        dish.setDishPrice((float)19.0);
        dishService.modifyDishById(dish);
    }

    @Test
    public void testDishHistory(){
        DishService dishService=new DishServiceImpl();
        ArrayList<DishPrice> dishPrices=new ArrayList<>();
        dishPrices=dishService.getDishHistoryPriceByDishId(2);
        System.out.println(dishPrices.size());
    }
}
