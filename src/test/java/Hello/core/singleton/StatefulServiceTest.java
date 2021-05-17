package Hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {


    @Test
    void statefulServiceSingleton() {
        AnnotationConfigApplicationContext ac= new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);
        //ThreadA사용자가 만원을 주문
        int userAPrice = statefulService1.order("userA", 10000);
        //ThreadB사용자가 이만원을 주문
        int userBPrice = statefulService2.order("userB", 20000);

        //ThreadA사용자의 주문 금액 조회
       // int price = statefulService1.getPrice();
        System.out.println("price= " + userAPrice);

     //   Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}