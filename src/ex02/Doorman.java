package ex02;

/**
 * [RealSubject 역할] Doorman - 실제 작업을 수행하는 진짜 객체
 *
 * 프록시 패턴에서 RealSubject에 해당한다.
 * - 동물을 쫓아내는 실제 비즈니스 로직을 담당
 * - DoormanProxy가 이 클래스를 상속하여, 클라이언트 대신 접근을 제어한다
 *
 * 구조: Client ──▶ DoormanProxy(Proxy) ──super──▶ Doorman(RealSubject)
 */
public class Doorman {
    /**
     * 실제 작업: 동물을 쫓아내는 핵심 로직
     * - DoormanProxy가 이 메서드를 super.chaseOut()으로 호출하여 위임한다
     */
    public void chaseOut(Animal a){
        System.out.println(a.getName() + " chase out!!");
    }
}
