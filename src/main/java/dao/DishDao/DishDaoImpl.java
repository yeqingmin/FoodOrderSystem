package dao.DishDao;

import dao.BaseDao;
import pojo.Dish;
import pojo.Merchant;
import pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

public class DishDaoImpl implements DishDao{
    public Dish getDishByName(Connection connection, String name) throws Exception {
        Dish dish = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        if(null != connection){
            String sql = "SELECT * FROM Dish WHERE dishName= ?";
            pstm = connection.prepareStatement(sql);
            Object[] params = {name};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            if(rs.next()){
                dish = new Dish();
                dish.setDishId(rs.getInt("dishId"));
                dish.setDishName(rs.getString("dishName"));
                dish.setDishPrice(rs.getFloat("dishPrice"));
                dish.setDishCategory(rs.getString("dishCategory"));
                dish.setDishDescription(rs.getString("dishDescription"));
                dish.setDishImage(rs.getBytes("dishImage"));
                dish.setMerchantId(rs.getInt("merchantId"));
                dish.setIsDelete(rs.getBoolean("isDeleted"));
                if(rs.getBoolean("isDelete")){
                    dish=null;
                }
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return dish;
    }

    @Override
    public Dish getDishById(Connection connection, Integer id) throws SQLException {
        Dish dish = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        if(null != connection){
            String sql = "SELECT * FROM dish WHERE dishId=?";
            pstm = connection.prepareStatement(sql);
            Object[] params = {id};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            while(rs.next()){
                dish = new Dish();
                dish.setDishId(rs.getInt("dishId"));
                dish.setDishName(rs.getString("dishName"));
                dish.setDishPrice(rs.getFloat("dishPrice"));
                dish.setDishCategory(rs.getString("dishCategory"));
                dish.setDishDescription(rs.getString("dishDescription"));
                dish.setDishAllergens(rs.getString("dishAllergens"));
                dish.setDishIngredients(rs.getString("dishIngredients"));
                dish.setDishNutrition(rs.getString("dishNutrition"));
//                dish.setDishImage(rs.getBytes("dishImage"));
                dish.setMerchantId(rs.getInt("merchantId"));
//                dish.setIsDelete(rs.getBoolean("isDeleted"));
//                if(rs.getBoolean("isDelete")){
//                    dish=null;
//                }
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return dish;
    }

    public int add(Connection connection, Dish dish)
            throws Exception {
        PreparedStatement pstm = null;
        int flag = 0;
        if(null != connection){
            String sql = "INSERT INTO Dish (dishId,dishName, dishPrice, dishCategory, dishDescription, dishImage, merchantId, isDelete) VALUES (?,?, ?, ?,?,?,?,?)";
            Object[] params ={dish.getDishId(),dish.getDishName(),dish.getDishPrice(),dish.getDishCategory(),dish.getDishDescription(),dish.getDishImage(),dish.getMerchantId(),dish.getIsDelete()};
            flag = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, null);
        }
        return flag;
    }
    public int modifyPrice(Connection connection, int id , float price)
            throws Exception {
        int flag = 0;
        PreparedStatement pstm = null;
        if(null != connection){
            String sql = "update Dish set dishPrice=?" +
                    "where dishId=? ";
            Object[] params = {price,id};
            flag = BaseDao.execute(connection, pstm, sql, params);
            //todo 这段逻辑应该放在service里面来完成
//            String sql2 = "INSERT INTO DishPrice (isDelete, dishId, price, validStartTime) VALUES (0, ?, ?, ?)";
//            Object[] params2 = {id,price, Calendar.getInstance().getTime()};
//            BaseDao.execute(connection, pstm, sql2, params2);
            BaseDao.closeResource(null, pstm, null);
        }
        return flag;
    }
    public int modifyCategory(Connection connection, int id , String dishCategory)
            throws Exception {
        int flag = 0;
        PreparedStatement pstm = null;
        if(null != connection){
            String sql = "update Dish set dishCategory=?" +
                    "where dishId=? ";
            Object[] params = {dishCategory,id};
            flag = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, null);
        }
        return flag;
    }
    public int deleteDishById(Connection connection, int id)
            throws Exception {
        PreparedStatement pstm = null;
        int flag = 0;
        if(null != connection){
            String sql = "delete from Dish where dishId=?";
            Object[] params = {id};
            flag = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, null);
        }
        return flag;
    }

    /**
     * 获取对应merchant的菜单
     * @param connection
     * @param merchantId
     * @return
     * @throws Exception
     */
    @Override
    public ArrayList<Dish> getDishByMerchantId(Connection connection, int merchantId) throws Exception {
        ArrayList<Dish> menu=new ArrayList<>();
        PreparedStatement preparedStatement=null;
        ResultSet rs=null;
        if(null != connection){
            String sql="select * from dish where merchantId=?";
            Object[] params ={merchantId};
            rs=BaseDao.execute(connection,preparedStatement,rs,sql,params);
            while(rs.next()){
                Dish dish=new Dish();
                dish.setDishId(rs.getInt("dishId"));
                dish.setDishName(rs.getString("dishName"));
                dish.setDishPrice(rs.getFloat("dishPrice"));
                dish.setDishCategory(rs.getString("dishCategory"));
                dish.setDishDescription(rs.getString("dishDescription"));
//                dish.setDishImage(rs.getBytes("dishImage"));
//                dish.setMerchantId(rs.getInt("merchantId"));
//                dish.setIsDelete(rs.getBoolean("isDeleted"));
                menu.add(dish);
            }
            BaseDao.closeResource(connection,preparedStatement,rs);
        }
        return menu;

    }
    public Dish getDishByNameAndMerchant(Connection connection, String name,int id)throws Exception{
        Dish dish = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        if(null != connection){
            String sql = "SELECT * FROM dish WHERE dishName= ? and merchantId=?";
            pstm = connection.prepareStatement(sql);
            Object[] params = {name,id};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            while(rs.next()){
                dish = new Dish();
                dish.setDishId(rs.getInt("dishId"));
                dish.setDishName(rs.getString("dishName"));
                dish.setDishPrice(rs.getFloat("dishPrice"));
                dish.setDishCategory(rs.getString("dishCategory"));
                dish.setDishDescription(rs.getString("dishDescription"));
//                dish.setDishImage(rs.getBytes("dishImage"));
                dish.setMerchantId(rs.getInt("merchantId"));
//                dish.setIsDelete(rs.getBoolean("isDeleted"));
//                if(rs.getBoolean("isDelete")){
//                    dish=null;
//                }
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return dish;

    }

}
