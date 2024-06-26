package service.Dish;

import dao.BaseDao;
import dao.DishDao.DishDao;
import dao.DishDao.DishDaoImpl;
import dao.DishPriceDao.DishPriceDao;
import dao.DishPriceDao.DishPriceDaoImpl;
import dao.MerchantDao.MerchantDao;
import dao.MerchantDao.MerchantDaoImpl;
import dao.OrderDao.OrderDao;
import dao.OrderDao.OrderDaoImpl;
import dao.OrderDetailDao.OrderDetailDao;
import dao.OrderDetailDao.OrderDetailDaoImpl;
import dao.UserDao.UserDao;
import dao.UserDao.UserDaoImpl;
import pojo.Dish;
import pojo.DishPrice;
import pojo.MerchantDetail;
import pojo.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class DishServiceImpl implements DishService {
    private final DishDao dishDao = new DishDaoImpl();
    private final OrderDetailDao orderDetailDao = new OrderDetailDaoImpl();
    private final DishPriceDao dishPriceDao = new DishPriceDaoImpl();

    @Override
    public Dish getDishByNameAndMerchant(String dishName, int merchantId) {
        Connection connection = null;
        Dish dish = null;
        try {
            connection = BaseDao.getConnection();
            dish = dishDao.getDishByNameAndMerchant(connection, dishName, merchantId);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                System.out.println("rollback==================");
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return dish;
    }

    @Override
    public Dish getDishById(int id) {
        Connection connection = null;
        Dish dish = null;
        try {
            connection = BaseDao.getConnection();
            dish = dishDao.getDishById(connection, id);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                System.out.println("rollback==================");
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return dish;
    }

    @Override
    public ArrayList<Dish> getDishByMerchantId(int merchantId) {
        Connection connection = null;
        ArrayList<Dish> menu = null;
        try {
            connection = BaseDao.getConnection();
            menu = dishDao.getDishByMerchantId(connection, merchantId);
            ;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                System.out.println("rollback==================");
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return menu;
    }

    /**
     * 当前方法不仅向orderDetail表里面插入了一条对应dishId，orderId的数据，同时调用countDishQuantity的方法，返回当前该菜的个数
     *
     * @param dishId
     * @param orderId
     * @return 返回值为当前订单当前菜品的数量
     */
    @Override
    public int addDishToOrder(Integer dishId, Integer orderId) {
        Connection connection = null;
        int quantity = 0;
        try {
            connection = BaseDao.getConnection();
            orderDetailDao.addDishToOrder(connection, dishId, orderId);
            quantity = orderDetailDao.countDishInOrderQuantities(connection, dishId, orderId);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                System.out.println("rollback==================");
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return quantity;
    }

    @Override
    public int countQuantityByDishId(int dishId,int orderId) {
        Connection connection = null;
        int quantity = 0;
        try {
            connection = BaseDao.getConnection();
            quantity = orderDetailDao.countDishInOrderQuantities(connection, dishId, orderId);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                System.out.println("rollback==================");
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return quantity;
    }

    @Override
    public int deleteDishFromOrder(Integer dishId, Integer orderId) {
        Connection connection = null;
        int quantity = 0;
        try {
            connection = BaseDao.getConnection();
            orderDetailDao.deleteDishFromOrder(connection, dishId, orderId);
            quantity = orderDetailDao.countDishInOrderQuantities(connection, dishId, orderId);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                System.out.println("rollback==================");
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return quantity;
    }

    @Override
    public int countDishQuantity(Integer dishId, Integer orderId) {
        Connection connection = null;
        int quantity = 0;
        try {
            connection = BaseDao.getConnection();
            quantity = orderDetailDao.countDishInOrderQuantities(connection, dishId, orderId);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                System.out.println("rollback==================");
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return quantity;
    }

    @Override
    public int getDishTotalCountByMerchantId(int merchantId) {
        Connection connection = null;
        int quantity = 0;
        try {
            connection = BaseDao.getConnection();
            quantity = dishDao.getDishTotalCountByMerchantId(connection,merchantId);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                System.out.println("rollback==================");
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return quantity;
    }

    @Override
    public ArrayList<Dish> getDishListByMerchantId(int merchantId, int currentPageNo, int pageSize) {
        Connection connection = null;
        ArrayList<Dish> menu = null;
        try {
            connection = BaseDao.getConnection();
            menu = dishDao.getDishListByMerchantId(connection, merchantId,currentPageNo,pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                System.out.println("rollback==================");
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return menu;
    }

    /**
     * 当前方法需要实现复杂的逻辑，获得当前菜品的线上和线下销量，并且返回购买该菜品最多的那个人
     * @param merchantId
     * @param currentPageNo
     * @param pageSize
     * @return
     */
    @Override
    public ArrayList<Dish> getDishWithSalesAndUserListByMerchantId(int merchantId, int currentPageNo, int pageSize) {
        //需要调用多个Dao层
        MerchantDao merchantDao=new MerchantDaoImpl();
        OrderDao orderDao=new OrderDaoImpl();
        UserDao userDao=new UserDaoImpl();

        Connection connection = null;
        ArrayList<Dish> menu = null;
        try {
            connection = BaseDao.getConnection();
            menu = dishDao.getDishListByMerchantId(connection, merchantId,currentPageNo,pageSize);
            //获得menu之后需要调用orderDao得到该菜品的线上线下销量
            for(Dish dish:menu){
                int id=dish.getDishId();
                int onlineSales=orderDao.getDishOnlineNumber(connection,id);
                int offlineSales=orderDao.getDisOfflineNumber(connection,id);
                int biggestBuyerId=orderDetailDao.biggestBuyerOfDish(connection,id);
                System.out.println("biggestBuyerId"+biggestBuyerId);
                User buyer=userDao.getUserById(connection,biggestBuyerId);
                String biggestBuyer="编号为"+biggestBuyerId+"的用户："+buyer.getUserName();
                dish.setOnlineSales(onlineSales);
                dish.setOfflineSales(offlineSales);
                dish.setMostUser(biggestBuyer);
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                System.out.println("rollback==================");
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return menu;
    }

    @Override
    public int modifyDishById(Dish dish) {
        //todo 这个dish参数是前端更新数据之后传过来的，在这里判断一下dish的price改了没，如果改了，调用dishPriceDao给dishPrice表加一条历史数据
        Connection connection = null;
        int quantity = 0;
        try {
            connection = BaseDao.getConnection();
            //在这之前判断
            int dishId=dish.getDishId();
            Dish oldDish=dishDao.getDishById(connection, dishId);
            if(!Objects.equals(dish.getDishPrice(), oldDish.getDishPrice())) {
                Date now = new Date();
                DishPrice dishPrice = new DishPrice();
                dishPrice.setDishId(dishId);
                dishPrice.setPrice(oldDish.getDishPrice());
                //dishPrice.setValidTime(now);
                dishPriceDao.addDishOldPrice(connection,dishPrice);
            }
            quantity = dishDao.modifyDishById(connection,dish);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                System.out.println("rollback==================");
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return quantity;
    }

    @Override
    public int deleteDishById(int dishId) {
        Connection connection = null;
        int quantity = 0;
        try {
            connection = BaseDao.getConnection();
            quantity = dishDao.logicDeleteDishById(connection,dishId);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                System.out.println("rollback==================");
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return quantity;
    }

    public ArrayList<DishPrice> getDishHistoryPriceByDishId(int dishId){
        Connection connection = null;
        ArrayList<DishPrice> historyPrice = null;
        try {
            connection = BaseDao.getConnection();
            historyPrice = dishPriceDao.getHistoryDishPriceById(connection,dishId);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                System.out.println("rollback==================");
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return historyPrice;
    }

    @Override
    public int addDish(Dish dish) {
        Connection connection = null;
        int flag=0;
        try {
            connection = BaseDao.getConnection();
            flag=dishDao.add(connection,dish);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                System.out.println("rollback==================");
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return flag;
    }

}
