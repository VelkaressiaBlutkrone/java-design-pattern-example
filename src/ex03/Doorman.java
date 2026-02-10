package ex03;

/**
 * [Client 역할] Doorman - Animal(Target) 인터페이스만 사용하는 클라이언트
 *
 * - Animal.getName()만 호출하므로, OuterRabbit의 존재를 전혀 모른다
 * - RabbitAdaptor 덕분에 OuterRabbit도 Animal처럼 처리할 수 있다
 */
public class Doorman {
    public void chaseOut(Animal a){
        System.out.println(a.getName() + " chase out!!");
    }
}
