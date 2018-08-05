package cn.johnyu.jdbc.util;

import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.beanutils.BeanUtils;
/**
 * 一个通用的,结合JDBC元数据及BeanUtils的，可以进行自动更新及查询的工具类
 * @author john
 *
 */
public class CommonDaoUtil {
	public static <T> List<T> query(String sql, Object[] values, Class<T> clazz) {
		Connection conn = null;
		PreparedStatement sta = null;
		ResultSet res = null;

		try {
			// 获取连接池
			DataSource pool = null;
			// 获取连接
			conn = pool.getConnection();
			// 获取statement对象,预编译
			sta = conn.prepareStatement(sql);

			// 利用参数的元数据给预编译的SQL语句赋值
			ParameterMetaData pmd = sta.getParameterMetaData();
			// 获得参数个数
			int pcount = pmd.getParameterCount();

			// 赋值
			if (values != null) {
				for (int i = 1; i <= pcount; i++) {
					sta.setObject(i, values[i - 1]);
				}
			}

			// 执行
			res = sta.executeQuery();

			// 获得结果集元数据
			ResultSetMetaData rsmd = res.getMetaData();
			// 创建存储对象的集合
			List<T> list = new ArrayList<T>();
			// 获取列的数量
			int colcount = rsmd.getColumnCount();
			// 封装对象
			while (res.next()) {
				// 生成要封装的对象的实例
				Object obj = clazz.newInstance();
				for (int i = 1; i <= colcount; i++) {
					// 获得列名
					String colname = rsmd.getColumnName(i);
					// 获得列值
					Object colvalue = res.getObject(i);
					// 封装
					BeanUtils.setProperty(obj, colname, colvalue);
				}
				// 把封装好的对象放入集合
				list.add((T) obj);
			}
			return (List<T>) list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			// 释放资源

		}
	}

	public static void update(String sql, Object[] values) {
		Connection conn = null;
		PreparedStatement sta = null;

		try {

			// 获取连接池
			DataSource pool = null;
			// 获取连接
			conn = pool.getConnection();
			// 预编译
			sta = conn.prepareStatement(sql);

			// 获取参数的元数据
			ParameterMetaData pmt = sta.getParameterMetaData();

			// 获取参数的个数
			int pcount = pmt.getParameterCount();
			// 参数赋值
			for (int i = 1; i <= pcount; i++) {
				sta.setObject(i, values[i - 1]);
			}

			// 执行更新
			sta.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
