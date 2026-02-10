package ex02;

/**
 * Tiger - "호랑이" 구체 동물 클래스
 *
 * 프록시 패턴에서 클라이언트가 전달하는 데이터 객체 역할.
 * Doorman(RealSubject)과 DoormanProxy(Proxy) 모두 이 객체를 동일하게 처리한다.
 */
public class Tiger extends Animal {
    private String name = "호랑이";

    @Override
    public String getName() {
        return name;
    }
}
