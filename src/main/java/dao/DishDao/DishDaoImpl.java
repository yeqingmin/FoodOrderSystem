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

public class DishDaoImpl implements DishDao {
    public Dish getDishByName(Connection connection, String name) throws Exception {
        Dish dish = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        if (null != connection) {
            String sql = "SELECT * FROM dish WHERE dishName= ?";
            pstm = connection.prepareStatement(sql);
            Object[] params = {name};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            if (rs.next()) {
                dish = new Dish();
                dish.setDishId(rs.getInt("dishId"));
                dish.setDishName(rs.getString("dishName"));
                dish.setDishPrice(rs.getFloat("dishPrice"));
                dish.setDishCategory(rs.getString("dishCategory"));
                dish.setDishDescription(rs.getString("dishDescription"));
                dish.setDishImage(rs.getBytes("dishImage"));
                dish.setMerchantId(rs.getInt("merchantId"));
                dish.setIsDelete(rs.getBoolean("isDeleted"));
                if (rs.getBoolean("isDelete")) {
                    dish = null;
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
        if (null != connection) {
            String sql = "SELECT * FROM dish WHERE dishId=?";
            pstm = connection.prepareStatement(sql);
            Object[] params = {id};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            while (rs.next()) {
                dish = new Dish();
                dish.setDishId(rs.getInt("dishId"));
                dish.setDishName(rs.getString("dishName"));
                dish.setDishPrice(rs.getFloat("dishPrice"));
                dish.setDishCategory(rs.getString("dishCategory"));
                dish.setDishDescription(rs.getString("dishDescription"));
                dish.setDishAllergens(rs.getString("dishAllergens"));
                dish.setDishIngredients(rs.getString("dishIngredients"));
                dish.setDishNutrition(rs.getString("dishNutrition"));
                dish.setDishFavourNumber(rs.getInt("dishFavourNumber"));
                dish.setMerchantId(rs.getInt("merchantId"));

            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return dish;
    }

    public int add(Connection connection, Dish dish)
            throws Exception {
        PreparedStatement pstm = null;
        int flag = 0;
        if (null != connection) {
            String sql = "INSERT INTO dish (dishName, dishPrice, dishCategory, dishDescription, dishAllergens,dishIngredients,dishNutrition, merchantId) VALUES (?, ?, ?,?,?,?,?,?)";
            Object[] params = {dish.getDishName(), dish.getDishPrice(), dish.getDishCategory(), dish.getDishDescription(), dish.getDishAllergens(), dish.getDishIngredients(),dish.getDishNutrition(),dish.getMerchantId()};
            flag = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, null);
        }
        return flag;
    }

    public int modifyPrice(Connection connection, int id, float price)
            throws Exception {
        int flag = 0;
        PreparedStatement pstm = null;
        if (null != connection) {
            String sql = "update dish set dishPrice=?" +
                    "where dishId=? ";
            Object[] params = {price, id};
            flag = BaseDao.execute(connection, pstm, sql, params);
            //todo 这段逻辑应该放在service里面来完成
//            String sql2 = "INSERT INTO DishPrice (isDelete, dishId, price, validStartTime) VALUES (0, ?, ?, ?)";
//            Object[] params2 = {id,price, Calendar.getInstance().getTime()};
//            BaseDao.execute(connection, pstm, sql2, params2);
            BaseDao.closeResource(null, pstm, null);
        }
        return flag;
    }

    public int modifyCategory(Connection connection, int id, String dishCategory)
            throws Exception {
        int flag = 0;
        PreparedStatement pstm = null;
        if (null != connection) {
            String sql = "update dish set dishCategory=?" +
                    "where dishId=? ";
            Object[] params = {dishCategory, id};
            flag = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, null);
        }
        return flag;
    }

    public int logicDeleteDishById(Connection connection, int id)
            throws Exception {
        PreparedStatement pstm = null;
        int flag = 0;
        if (null != connection) {
            String sql = "delete from dish where isDelete=0 and dishId=?";
            Object[] params = {id};
            flag = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, null);
        }
        return flag;
    }

    /**
     * 获取对应merchant的菜单
     *
     * @param connection
     * @param merchantId
     * @return
     * @throws Exception
     */
    @Override
    public ArrayList<Dish> getDishByMerchantId(Connection connection, int merchantId) throws Exception {
        ArrayList<Dish> menu = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        if (null != connection) {
            String sql = "select * from dish where merchantId=?";
            Object[] params = {merchantId};
            rs = BaseDao.execute(connection, preparedStatement, rs, sql, params);
            while (rs.next()) {
                Dish dish = new Dish();
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
            BaseDao.closeResource(connection, preparedStatement, rs);
        }
        return menu;

    }

    public Dish getDishByNameAndMerchant(Connection connection, String name, int id) throws Exception {
        Dish dish = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        if (null != connection) {
            String sql = "SELECT * FROM dish WHERE dishName= ? and merchantId=?";
            pstm = connection.prepareStatement(sql);
            Object[] params = {name, id};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            while (rs.next()) {
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


    @Override
    public int getDishTotalCountByMerchantId(Connection connection, int merchantId) throws Exception {
        int count = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        if (null != connection) {
            String sql = "select count(*) from dish where merchantId=?";
            pstm = connection.prepareStatement(sql);
            Object[] params = {merchantId};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            if (rs.next()) {
                count = rs.getInt(1);
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return count;
    }

    @Override
    public ArrayList<Dish> getDishListByMerchantId(Connection connection, int merchantId, int currentPageNo, int pageSize) throws Exception {
        //分页查询的代码

        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<Dish> dishList = new ArrayList<Dish>();
        if (connection != null) {
            String sql = "select * from dish where isDelete = 0 and merchantId=? order by dishId limit ?,?";
            currentPageNo = (currentPageNo - 1) * pageSize;

            Object[] params = {merchantId, currentPageNo, pageSize};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            while (rs.next()) {
                Dish dish = new Dish();
                dish.setDishId(rs.getInt("dishId"));
                dish.setDishName(rs.getString("dishName"));
                dish.setDishPrice(rs.getFloat("dishPrice"));
                dish.setDishCategory(rs.getString("dishCategory"));
                dish.setDishDescription(rs.getString("dishDescription"));
                dish.setDishAllergens(rs.getString("dishAllergens"));
                dish.setDishIngredients(rs.getString("dishIngredients"));
                dish.setDishNutrition(rs.getString("dishNutrition"));
                dish.setMerchantId(rs.getInt("merchantId"));
                dishList.add(dish);
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return dishList;
    }

    @Override
    public int modifyDishById(Connection connection, Dish dish) throws Exception {
        int flag = 0;
        PreparedStatement pstm = null;
        if (null != connection) {
            String sql = "update dish set dishName=?, dishPrice=?, dishCategory=?, dishDescription=?, dishAllergens=?,dishIngredients=?,dishNutrition=? where dishId=?";
            Object[] params = {dish.getDishName(), dish.getDishPrice(), dish.getDishCategory(), dish.getDishDescription(), dish.getDishAllergens(), dish.getDishIngredients(), dish.getDishNutrition(), dish.getDishId()};
            flag = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, null);
        }
        return flag;
    }
    public void increaseFavourNumber(Connection connection , Dish dish) throws Exception{
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int flag;
        if(null != connection){
            String sql = "UPDATE dish SET dishFavourNumber=? WHERE dishId=?";

            pstm = connection.prepareStatement(sql);
            int number=dish.getDishFavourNumber()+1;
            Object[] params = {number,dish.getDishId()};
            flag = BaseDao.execute(connection, pstm, sql, params);
            }
            BaseDao.closeResource(null, pstm, rs);
        }


}
