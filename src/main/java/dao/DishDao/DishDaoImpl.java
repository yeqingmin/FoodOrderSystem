package dao.DishDao;

import dao.BaseDao;
import pojo.Dish;
import pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DishDaoImpl implements DishDao{
    public User getDishById(Connection connection, int id) throws Exception {
        Dish dish = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        if(null != connection){
            String sql = "SELECT * FROM users WHERE id = ?";
            pstm = connection.prepareStatement(sql);
            Object[] params = {id};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            if(rs.next()){
                dish = new Dish();
                dish.setDishId(rs.getInt("id"));
                dish.setDishName(rs.getString("dishName"));
                dish.setUserGender(rs.getString("gender"));
                if(rs.getBoolean("isDelete")){
                    user=null;
                }
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return user;
    }
}
