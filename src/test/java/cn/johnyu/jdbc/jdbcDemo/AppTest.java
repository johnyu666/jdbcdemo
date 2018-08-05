package cn.johnyu.jdbc.jdbcDemo;

import org.apache.commons.dbcp.BasicDataSource;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
    
    public static void main(String[] args) {
//		BasicDataSource dataSource=new BasicDataSource();
//		dataSource.setUrl(url);
//		dataSource.setUsername(username);
//		dataSource.setPassword(password);
//		dataSource.setDriverClassName(driverClassName);
//		dataSource.setInitialSize(10);
//		dataSource.sete
//		dataSource.setMinIdle(100);
//		dataSource.setMaxActive(100);
	}
}
