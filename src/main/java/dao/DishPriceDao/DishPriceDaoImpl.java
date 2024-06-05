package dao.DishPriceDao;

import dao.BaseDao;
import dao.DishDao.DishDao;
import pojo.Dish;
import pojo.DishPrice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DishPriceDaoImpl implements DishPriceDao {
    public int addDishOldPrice(Connection connection, DishPrice dishPrice) throws Exception {
        PreparedStatement pstm = null;
        int flag = 0;
        if(null != connection){
            String sql = "INSERT INTO dishprice (dishId, price) VALUES (?, ?)";
            Object[] params ={dishPrice.getDishId(),dishPrice.getPrice()};
            flag = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, null);
        }
        return flag;
    }

    public ArrayList<DishPrice> getHistoryDishPriceById(Connection connection, int dishId) throws SQLException {
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        ArrayList<DishPrice> historyPrice=new ArrayList<>();
        if(null != connection){
            String sql= "select * from dishprice where dishId=?";
            Object[] params={dishId};
            resultSet=BaseDao.execute(connection,preparedStatement,resultSet,sql,params);
            while(resultSet.next()){
                DishPrice history=new DishPrice();
                history.setPrice(resultSet.getFloat("price"));
                history.setValidTime(resultSet.getDate("validTime"));
                history.setDishId(dishId);
                historyPrice.add(history);
            }
            BaseDao.closeResource(connection,preparedStatement,resultSet);
        }
        return historyPrice;
    }
}
