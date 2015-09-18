package eurasia;

import java.io.InputStream;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.threem.eurasia.sample.Eurasia;
import com.threem.eurasia.sample.EurasiaMapper;

@Slf4j
public class MyBatisTest {

	//MyBatis XML 설정 값 자바로 직접 연결해서 사용해 보기 !! 
	@Test
	public void testMybatis_sqlSession_직접연결사용해보기() throws Exception {
		
		String resource = "spring/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession session = sqlSessionFactory.openSession();
		EurasiaMapper mapper = session.getMapper(EurasiaMapper.class);
		
		String sampleResult = mapper.selectEurasia();
		
		log.debug(sampleResult);
		
		session.close();
		
	}
	
	//MyBatis XML 설정 값 자바로 직접 연결해서 사용해 보기 !! 
	@Test
	public void testMybatis_sqlSession_직접연결사용해보기2() throws Exception {
		
		String resource = "spring/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession session = sqlSessionFactory.openSession();

		String sampleResult = (String)session.selectOne("com.threem.eurasia.sample.EurasiaMapper.selectEurasia");
		
		log.debug(sampleResult);
		
		session.close();
		
	}
	
	//MyBatis XML 설정 값 자바로 직접 연결해서 사용해 보기 !! 
	@Test
	public void testMybatis_sqlSession_직접연결사용해보기_annotation이용() throws Exception {
		
		String resource = "spring/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession session = sqlSessionFactory.openSession();

		String sampleResult = (String)session.selectOne("com.threem.eurasia.sample.EurasiaMapper.selectAnnotationEurasia");
		
		session.close();
		
		log.debug(sampleResult);
		
	}
	
	//MyBatis XML 설정 값 자바로 직접 연결시 properties 직접 설정하기 !! 
	@Test
	public void testMybatis_sqlSession_직접연결사용해보기_properteis사용하기() throws Exception {
		
		String resource = "spring/mybatis-config.xml";
		String propertiesFile = "properties/jdbc.properties";
		
		InputStream inputStream = Resources.getResourceAsStream(resource);

		//프로퍼티 파일 가져오는 부분
		InputStream propertiesStream = Resources.getResourceAsStream(propertiesFile);
		//프로퍼티를 가져오는 또다른 방법
		//InputStream propertiesStream = this.getClass().getClassLoader().getResourceAsStream(propertiesFile);
		
		Properties properties = new Properties();
		properties.load(propertiesStream);
		
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, properties);
		
		SqlSession session = sqlSessionFactory.openSession();

		String sampleResult = (String)session.selectOne("com.threem.eurasia.sample.EurasiaMapper.selectEurasia");
		
		log.debug(sampleResult);
		
		session.close();
		
	}
	
	//Mapper설정의 lazyLoading  test !! 
	@Test
	public void testMybatis_sqlSession_직접연결사용해보기_lazyLoading설정테스트() throws Exception {
		
		String resource = "spring/mybatis-config.xml";
		String propertiesFile = "properties/jdbc.properties";
		
		InputStream inputStream = Resources.getResourceAsStream(resource);

		//프로퍼티 파일 가져오는 부분
		InputStream propertiesStream = Resources.getResourceAsStream(propertiesFile);
		
		Properties properties = new Properties();
		properties.load(propertiesStream);
		
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, properties);
		SqlSession session = sqlSessionFactory.openSession();

		Eurasia eurasia = (Eurasia)session.selectOne("com.threem.eurasia.sample.EurasiaMapper.selectLazyLoadingTestA");
		
		System.out.println("lazy loading 시작 !!");
		
		eurasia.getEurasiaSub();
		
		session.close();
		
	}
	
}
