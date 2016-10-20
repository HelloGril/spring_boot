package ch2.el


import org.apache.commons.io.IOUtils
import org.springframework.beans.factory.annotation.{Autowired, Value}
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration, PropertySource}
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer
import org.springframework.core.env.Environment
import org.springframework.core.io.Resource

import scala.beans.BeanProperty

/**
  * Created by mike on 2016/10/18.
  */
@Configuration
@ComponentScan(Array("ch2.el"))
@PropertySource(Array("classpath:ch2/el/test.properties"))
class ElConfig {

  @Value("I love you so much!")
  var normal: String = ""

  @Value("#{systemProperties['os.name']}")
  var osName: String = ""

  @Value("#{T(java.lang.Math).random() * 100.0}")
  var randomNum: Double = 0.0

  //  @Value("#{demoService.another}")
  // error
  var another: String = ""

  @Value("classpath:ch2\\el\\test.txt")
  // error
  var testTxt: Resource = null

  @Value("${book.name}")
  var bookName: String = ""

  @Autowired
  var environment: Environment = null

  @Bean
  var propertyConfigure: PropertySourcesPlaceholderConfigurer = null

  override def toString = osName + ",  \n" + randomNum + ",  \n" + IOUtils.toString(testTxt.getInputStream) +
    ", \n" + bookName + ",  \n" + environment.getProperty("book.author")
}