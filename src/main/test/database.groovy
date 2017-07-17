import com.junqiang.www.college.service.ClazzBiz
import org.springframework.context.support.ClassPathXmlApplicationContext

ClassPathXmlApplicationContext context = null;

/**
 * Created by liujian on 17/7/17.
 */
context = new ClassPathXmlApplicationContext("classpath:spring.xml");
context.start();
ClazzBiz clazzBiz = context.getBean("clazzBizImpl")
List<Class> classList = clazzBiz.findAll()
println(classList.size())
