package ex01;

/**
 * [클라이언트] App - 전략 패턴(다형성) 동작을 확인하는 실행 클래스
 *
 * 실행 흐름:
 *   1. 구체 전략(Tiger) 생성
 *   2. Context(Doorman) 생성
 *   3. Doorman.chaseOut(Animal)에 Tiger를 전달
 *   4. 런타임에 Tiger.getName()이 호출됨 → "호랑이 chase out!!"
 *
 * 포인트:
 *   - Tiger를 Mouse로 바꿔도 Doorman 코드는 변경 없이 동작
 *   - 이것이 전략 패턴의 핵심: 구체 전략 교체 시 Context 수정 불필요
 */
public class App {
    public static void main(String[] args) {
        Tiger t = new Tiger();   // 구체 전략 생성 (Tiger)
        Doorman d = new Doorman(); // Context 생성
        d.chaseOut(t);             // Animal 타입으로 받아 처리 → "호랑이 chase out!!"

        // Mouse로 교체해도 Doorman은 수정 불필요 (OCP)
        // Mouse m = new Mouse();
        // d.chaseOut(m);          // → "쥐 chase out!!"
    }
}
