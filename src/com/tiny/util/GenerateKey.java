package com.tiny.util;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentityGenerator;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.proxy.HibernateProxy;

/**
 * @author arjun_s
 *
 */
public class GenerateKey extends IdentityGenerator { 
	
	private static Logger LOGGER=Logger.getLogger(GenerateKey.class);	
   
    @Override
    public Serializable generate(SessionImplementor session, Object entity) throws HibernateException {
        if (entity == null) throw new HibernateException(new NullPointerException()) ;       
        String tableName=null;
		if(entity instanceof HibernateProxy){
			tableName=((HibernateProxy) entity).getHibernateLazyInitializer().getImplementation().getClass().getAnnotation(Entity.class).name();
		}else {			
			for (Annotation annotation : Arrays.asList(entity.getClass().getDeclaredAnnotations())){
		        if (annotation instanceof Table){
		        	tableName=((Table) annotation).name();
		        }
			}
		}  
        return getKey(tableName, session);
    }
    
    /**
	 * Gets the DB service.
	 *
	 * @param <T> the generic type
	 * @param serviceName the service name
	 * @return the DB service
	 */
	public Long getKey(String tableName, SessionImplementor session){
		int size = 100;
		CallableStatement callStmt = null;
		Connection conn = null;
		Long key = null;
		try{
			conn = getConnection((Session)session);
			callStmt = conn.prepareCall("{call  key_generator(?,?,?,?)}");
			callStmt.setString(1, tableName);
			callStmt.setInt(2, size);
			callStmt.registerOutParameter(3, java.sql.Types.NUMERIC);
			callStmt.registerOutParameter(4, java.sql.Types.VARCHAR);
			callStmt.executeUpdate();		
			key = callStmt.getLong(3);
			//String errorMsg = callStmt.getString(4);
			callStmt.close();		
		}catch(SQLException e){
			LOGGER.error(e);
		}finally {
			try{
				 if (callStmt != null) {
					 callStmt.close();
				 }
				 if (conn != null) {
					 conn.close();
				 }
			}catch(SQLException e){
				LOGGER.error(e);
			}
		}	
		return key;
	}
	
	/**
	 * @param ses
	 * @return
	 */
	public Connection getConnection(Session ses) {
		Connection connection = null;
        SessionFactoryImpl sessionFactory = (SessionFactoryImpl) ses.getSessionFactory();
        try{
        	connection = sessionFactory.getConnectionProvider().getConnection();
        }catch(SQLException e){
        	LOGGER.error(e);
        }
        return connection;
    }
}