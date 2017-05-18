package com.iif.common.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.hxjz.common.utils.DBUtil;

@Transactional
public class QuartzUtil{
	
	protected static final Logger logger = Logger.getLogger(QuartzUtil.class) ;
	
	public final static String strSec = "0";//  秒标志
	public final static String strMin = "1";//分钟标志
	public final static String strHou = "2";//小时标志
	
	/**
	 * 服务器调度同步类，使集群中所有机器的相同quartz任务只有一个机器可以执行成功。
	 * 注意：由于oracle的无阻塞读的副作用，此类需要约束不同job的启动时间不能相同
	 * @param taskName 调度服务名称
	 * @param dTime 间隔时间（单位：秒、分、小时）
	 * @param dFlag 时间类型（"0"：秒、"1"：分、"2"：小时、其他：天）
	 * @throws SQLException 
	 */
	public static Boolean getThreadFlg(String taskName, int dTime, String dStrFlag) throws SQLException {
		
		ResultSet rs = null ;
		Connection conn = DBUtil.makeConnection("dataSource");
		Statement statemt = conn.createStatement() ;
		int maxNo = 0;//获得要插入新记录的ID
		String maxNoSql = "SELECT MAX(FID) FROM TSYSQUARTZQUEUE";
		rs = statemt.executeQuery(maxNoSql);
		
		if(rs == null){
			maxNo = 1;
		}else{
			while(rs.next()){
				maxNo = rs.getInt(1)+1;
			}
		}
		
		String ip = null;
		try{
			InetAddress addr = InetAddress.getLocalHost();
			ip = (String)addr.getHostAddress();
		}catch(UnknownHostException e) {
			ip = "0.0.0.0";
		}
		
		String insertSql = "INSERT INTO TSYSQUARTZQUEUE VALUES("+maxNo+",'"+taskName+"', now(),'"+ip+"')";//插入语句
		
		StringBuffer selectSql = new StringBuffer();
		selectSql.append("SELECT COUNT(*) FROM TSYSQUARTZQUEUE WHERE FQUARTZID = '")
				 .append(taskName)
				 .append("' AND FQUARTZBEGINTIME >= SYSDATE");
		
		StringBuffer chkSql = new StringBuffer();
		chkSql.append(" BEGIN ")
			  .append("   DECLARE ")
			  .append("   v_output VARCHAR(50); ")
			  .append("   v_cnt INT; ")
			  .append(" SELECT count(*) INTO v_cnt FROM TSYSQUARTZQUEUE WHERE FQUARTZID = '")
			  .append(taskName)
		      .append("' AND FQUARTZBEGINTIME >= now() ");
		      
		StringBuffer appendSql = new StringBuffer();
		appendSql.append(";  IF v_cnt > 0 THEN ")
				 .append(" v_output := 'false';  ")
				 .append("  ELSE ")
				 .append(insertSql).append(";")
				 .append(" v_output := 'true';  ")
				 .append("  END IF; ")
				 .append(" set param1 := v_output ; ")
	    	  	 .append(" END ; ");
		
		if(strSec.equals(dStrFlag)) {
			selectSql.append(" -' ")
					 .append(dTime)
					 .append(" '/ ")
					 .append(24*60*60);//秒
			
			chkSql.append(" -' ")
		          .append(dTime)
		          .append(" '/ ")
		          .append(24*60*60)//秒
		          .append(appendSql);
		}else if(strMin.equals(dStrFlag)) {
			selectSql.append(" -' ")
					 .append(dTime)
					 .append(" '/ ")
					 .append(24*60);//分钟
			
			chkSql.append(" -' ")
	          	  .append(dTime)
	          	  .append(" '/ ")
	          	  .append(24*60)//分钟
	          	  .append(appendSql);
			
		}else if(strHou.equals(dStrFlag)) {
			selectSql.append(" -' ")
			 		 .append(dTime)
			 		 .append(" '/ ")
			 		 .append(24);//小时
			
			chkSql.append(" -' ")
	          	  .append(dTime)
	          	  .append(" '/ ")
	          	  .append(24)//小时
	          	  .append(appendSql);
			
		}else {
			selectSql.append(" - ")
			 		 .append(dTime);//天
			
			chkSql.append(" - ")//天
	          	  .append(dTime)
	          	  .append(appendSql);		
		}
		
		String res = "true";	
		try{
			statemt.executeUpdate(insertSql);
			/*CallableStatement cst = null;
			cst = conn.prepareCall(new String(chkSql));//执行存储过程
			cst.registerOutParameter(1, Types.VARCHAR); //为存储过程设定返回值
			int i = cst.executeUpdate();//得到预编译语句更新记录或删除操作的结果
			res = cst.getString(1); //得到返回值*/		
		}catch(Exception e){
			logger.error("exception is :",e);
			//当ID被占用时校验该ID是否被本任务占用
			res = "false";
			/*if(e.toString().indexOf("ORA-06512")>=0) {
				String sql = "select FQUARTZID from TSYSQUARTZQUEUE where Fid = " + maxNo;
				rs = statemt.executeQuery(sql);
				String quartzId = null;
				if(res!=null){
					//当其他任务占用此ID时重新调用排重方法
					while(rs.next()){
						quartzId = rs.getString("FQUARTZID");
						if(quartzId!=null&&!quartzId.equals(taskName)){
							res = QuartzUtil.getThreadFlg(taskName , dTime , dStrFlag).toString();
						}
					}
				}
			}*/
		}
		
		DBUtil.closeConnection(conn , statemt , rs);
		
		if(res.equals("true")){
			return true;
		}else{
			return false;
		}

	}
}
