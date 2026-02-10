package ex02;

/**
 * [클라이언트] App - 프록시 패턴 동작을 확인하는 실행 클래스
 *
 * 실행 흐름:
 *   1. Tiger(데이터 객체) 생성
 *   2. DoormanProxy(Proxy) 생성 ← Doorman(RealSubject)이 아닌 프록시를 사용
 *   3. DoormanProxy.chaseOut(Tiger) 호출
 *   4. 프록시 내부에서 super.chaseOut(Tiger) → Doorman의 실제 로직 실행
 *   5. 출력: "호랑이 chase out!!"
 *
 * 포인트:
 *   - 클라이언트는 Doorman 대신 DoormanProxy를 사용하지만, 동일한 chaseOut() 호출
 *   - DoormanProxy가 Doorman을 상속하므로 타입 호환 (is-a 관계)
 *   - 프록시에서 전/후처리 로직을 추가해도 클라이언트 코드는 변경 불필요
 */
public class App {
    public static void main(String[] args) {
        Tiger t = new Tiger();           // 처리할 데이터 객체
        DoormanProxy d = new DoormanProxy(); // Proxy 사용 (Doorman이 아닌 프록시)
        d.chaseOut(t);                   // 프록시 → super.chaseOut() → "호랑이 chase out!!"
    }
}
