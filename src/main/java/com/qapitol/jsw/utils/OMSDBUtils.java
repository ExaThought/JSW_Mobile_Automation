package com.qapitol.jsw.utils;

import com.qapitol.sauron.common.configuration.Config;
import com.qapitol.sauron.logging.SauronLogger;
import com.qapitol.sauron.logging.SimpleLogger;
import org.testng.Assert;

import java.io.InputStream;
import java.sql.*;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Logger;

public class OMSDBUtils {
	static final String JDBC_DRIVER = "org.postgresql.Driver";
	private String DB_URL;
	private String schemaName;
	private String userId;
	private String password;
	private static final SimpleLogger log = SauronLogger.getLogger();
	public static Connection conn = null;
	public static Statement stmt = null;
	private static OMSDBUtils omsdbUtil;

	public OMSDBUtils() {
		this.DB_URL = Config.getConfigProperty("OMS_URL");
		this.schemaName = Config.getConfigProperty("OMS_SCHEMA_NAME");
		this.userId = Config.getConfigProperty("OMS_USERID");
		this.password = Config.getConfigProperty("OMS_PWD");
		this.setDBConnection();
	}

	public static OMSDBUtils getOmsdbUtilsInstance() {
		if (omsdbUtil == null)
			return new OMSDBUtils();
		else
			return omsdbUtil;
	}

	private void setDBConnection() {
		String databaseURL = this.DB_URL + "/" + this.schemaName;
		log.info("DBURL-->" + databaseURL);

		try {
			if (conn == null) {
				Class.forName(JDBC_DRIVER).newInstance();
				conn = DriverManager.getConnection(databaseURL, this.userId, this.password);
				log.info("Database Connection is successful");
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException var3) {
			log.info("Connection Failed due to " + var3.getMessage());
			var3.printStackTrace();
			Assert.fail("Connection Failed", var3);
		}
	}

	public void closeDBConnection() {
		try {
			conn.close();
			conn = null;
			log.info("Database Connection is closed");
		} catch (SQLException var2) {
			log.info("Connection Failed due to " + var2.getMessage());
			var2.printStackTrace();
		}

	}

	public HashMap<String, Object> getCategory(String orderNumber) {
		ResultSet rs;
		int count = 0;
		String sqlSelectStatement = "SELECT id, category FROM public.customer_order_rtab where ord_number ='"+orderNumber+"'";
		log.info(sqlSelectStatement);
		HashMap<String, Object> map = new HashMap<>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sqlSelectStatement);
			while (rs.next()) {
				count++;
				map.put("id", String.valueOf(rs.getObject("id")));
				map.put("category", String.valueOf(rs.getObject("category")));
				map.put("count", String.valueOf(count));
			}
			stmt.close();
		}catch (SQLException var4) {
			log.info("SQL Query Not executed due to SQL Exception" + var4.getMessage());
		}
		return map;
	}

	public HashMap<String, Object> updateSalesForceOpportunity(String orderNumber) {
		String sqlUpdateStatement = "Update customer_order_rtab \n" +
				//"SET salesforce_opportunity_id='006Hz00000V0pPhIAJ'\n" +
				"SET salesforce_opportunity_id='006Ik000002rf0YIAQ'\n" +
				"Where ord_number ='"+orderNumber+"'";
		log.info(sqlUpdateStatement);
		HashMap<String, Object> map = new HashMap<>();
		try {
			stmt = conn.createStatement();
			stmt.executeQuery(sqlUpdateStatement);
			stmt.close();
		}catch (SQLException var4) {
			log.info("SQL Query Not executed due to SQL Exception" + var4.getMessage());
		}
		return map;
	}

}
