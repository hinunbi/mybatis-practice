package com.brm.mybatis;

import com.brm.mybatis.transaction.MyBatisTransactionManager;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/mybatis.xml" })
public class MybatisSupportTest extends MyBatisSupport {

	@Test
	public void testProgramacTraction() throws SQLException {

		MyBatisTransactionManager transaction = getTransactionManager();
		try {
			transaction.start();

			List results = sqlSession.selectList("test.select");
			System.out.println("selected = " + results);
			int cnt = sqlSession.update("test.insert", results.get(0));
			System.out.println("inserted = " + cnt);

			transaction.commit();

		} finally {
			transaction.end();
		}
	}
}
