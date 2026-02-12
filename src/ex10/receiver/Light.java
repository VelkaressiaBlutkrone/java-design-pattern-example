package ex10.receiver;

/**
 * 2-a. Receiver - 실제 작업을 수행하는 객체 (조명)
 *
 * Command 객체가 직접 작업을 하지 않고, 이 Receiver에게 실제 동작을 위임한다.
 * Command는 Receiver의 메서드를 호출하는 역할만 담당한다.
 */
public class Light {
    public void on() {
        System.out.println("조명 켜짐");
    }

    public void off() {
        System.out.println("조명 꺼짐");
    }
}
