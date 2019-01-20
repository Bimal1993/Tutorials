/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tutorial.db;

//import CrsCde.CODE.Common.Utils.JSONUtil;
import CrsCde.CODE.Common.Utils.EnumUtil;
import CrsCde.CODE.Common.Utils.ReflUtils;
import com.tutorial.app.AppConst;
import com.tutorial.enums.EN;
import java.lang.reflect.Field;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.sql.ConnectionPoolDataSource;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Manoj
 * @since 24 Nov, 2018
 */
public class DBManager
{

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected final String _dbUrl, _dbSrvr, _dbName, _dbUser, _dbPwd;
    protected final Integer _dbPort;
    protected static Connection _connection;

    public DBManager(String _dbSrvr, Integer _dbPort, String _dbName, String _dbUser, String _dbPwd) throws Exception
    {
        this._dbSrvr = _dbSrvr;
        this._dbName = _dbName;
        this._dbUser = _dbUser;
        this._dbPwd = _dbPwd;
        this._dbPort = _dbPort;
        this._dbUrl = "jdbc:mysql://" + _dbSrvr + ":" + _dbPort + "/" + _dbName;

        Class.forName(AppConst.DB_Driver);
        _connection = DriverManager.getConnection(_dbUrl, _dbUser, _dbPwd);
    }

    public Connection getConnection() throws Exception
    {
        DBManager dbM = new DBManager(AppConst.DB_Server, AppConst.DB_Port, AppConst.DB_Name, AppConst.DB_User, AppConst.DB_Pwd);
        dbM._connection = DriverManager.getConnection(dbM._dbUrl, dbM._dbUser, dbM._dbPwd);
        return dbM._connection;
    }

    public void CloseDBConnection(Statement st, ResultSet rs, Connection dbconn)
    {
        try
        {
            if (st != null)
            {
                st.close();
            }
            if (rs != null)
            {
                rs.close();
            }
            if (dbconn != null)
            {
                dbconn.close();
            }
        }
        catch (Exception ex)
        {
            //for testing only. otherwise we need to comment this.
            logger.error(ex.getMessage(), ex);
        }

    }

    public ArrayList<String> FetchTableColumns(String tablename) throws Exception
    {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        ArrayList<String> arrColms = new ArrayList<>();
        try
        {
            con = getConnection();
            String qry = "SHOW COLUMNS FROM " + tablename;
            logger.debug(qry);
            //execute query
            st = con.prepareStatement(qry);
            rs = st.executeQuery();
            while (rs.next())
            {
                arrColms.add(rs.getString("Field"));
            }

        }
        catch (Exception ex)
        {
            throw ex;
        }
        finally
        {
            CloseDBConnection(st, rs, con);
        }
        return arrColms;
    }

    public JSONArray Fetch(String tablename) throws Exception
    {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        JSONArray jobj = null;
        try
        {
            con = getConnection();
            String sql = "Select * From " + tablename;
            logger.debug(sql);

            st = con.prepareStatement(sql);
            rs = st.executeQuery();
            jobj = getJsonFromResult(rs);
        }
        catch (Exception ex)
        {
            throw ex;
        }
        finally
        {
            CloseDBConnection(st, rs, con);
        }
        return jobj;
    }
    public void Insert(Object obj)
    {
        Connection con = null;
        Statement st = null;
        String tblName =AppConst.DB_Name + "."+obj.getClass().getSimpleName().toLowerCase();
        
        try
        {
//            "INSERT INTO system.src_stat (Time,Age,Name,DOJ) values(?,?,?,?)";
            HashMap<String,String> hmValues = getValuesForInsert(obj);
            StringBuilder keys = new StringBuilder("(");
            StringBuilder values = new StringBuilder("(");
            for(String key : hmValues.keySet())
            {
                keys.append(key).append(",");
                values.append(hmValues.get(key)).append(",");
            }
            keys.append(")");
            values.append(")");
            String query = "Insert into "+tblName+ " "+keys.toString()+ "values "+ values.toString();
            System.out.println("Inser query :- "+query);
            con = getConnection();
            st = con.createStatement(); 
            st.execute(query);
        }
        catch(Exception ex)
        {
            logger.error(ex.getMessage());
            ex.getMessage();
        }
    }
    /**
     * Delete table from database.
     *
     * @param tablename
     * @throws Exception
     */
    protected void DropTable(String tablename) throws Exception
    {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try
        {
            con = getConnection();
            String sql = "DROP Table IF EXISTS " + tablename;
            logger.debug(sql);

            st = con.prepareStatement(sql);
            st.execute();
        }
        catch (Exception ex)
        {
            throw ex;
        }
        finally
        {
            CloseDBConnection(st, rs, con);
        }
    }

   /*
    * Convert ResultSet to a common JSON Object array
    * Result is like: [{"ID":"1","NAME":"Tom","AGE":"24"}, {"ID":"2","NAME":"Bob","AGE":"26"}, ...]
    */
    public static JSONArray getJsonFromResult(ResultSet rs)
    {
//        List<JSONObject> resList = new ArrayList<>();
        JSONArray resArr = new JSONArray();
        try
        {
            // get column names
            ResultSetMetaData rsMeta = rs.getMetaData();
            int columnCnt = rsMeta.getColumnCount();
            List<String> columnNames = new ArrayList<>();
            for (int i = 1; i <= columnCnt; i++)
            {
                columnNames.add(rsMeta.getColumnName(i).toUpperCase());
            }

            while (rs.next())
            { // convert each object to an human readable JSON object
                JSONObject obj = new JSONObject();
                for (int i = 1; i <= columnCnt; i++)
                {
                    String key = columnNames.get(i - 1);
                    String value = rs.getString(i);
                    obj.put(key, value);
                }
                resArr.put(obj);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                rs.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return resArr;
    }
    private HashMap<String,String> getValuesForInsert(Object obj) throws Exception
    {
        HashMap<String,String> hmValues = new HashMap<>();
        
        Field[] arrFlds = obj.getClass().getDeclaredFields();
        for (Field fld : arrFlds)
        {
            if (!fld.getName().equals("Id"))
            {
                try
                {
                    Object value = ReflUtils.InvokeGetter(obj, fld);

                    if (fld.getType().isEnum())
                    {
                        value = EnumUtil.Name(value);
                    }

                    hmValues.put(fld.getName(),value.toString());
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        return hmValues;
    }
}
