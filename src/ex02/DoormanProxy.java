package ex02;

/**
 * [Proxy 역할] DoormanProxy - Doorman(RealSubject)에 대한 대리 객체
 *
 * 프록시 패턴에서 Proxy에 해당한다.
 * - Doorman을 상속하여 동일한 인터페이스(chaseOut)를 제공
 *   → 클라이언트는 DoormanProxy인지 Doorman인지 구분하지 못한다
 * - super.chaseOut()으로 실제 객체(Doorman)에게 작업을 위임
 * - 위임 전후에 부가 로직(접근 제어, 로깅, 캐싱 등)을 삽입할 수 있다
 *
 * 구조:
 *   Client ──▶ DoormanProxy.chaseOut()
 *                    │
 *                    ├── [부가 로직 삽입 가능 - 전처리]
 *                    ├── super.chaseOut(a)  ← 실제 Doorman에게 위임
 *                    └── [부가 로직 삽입 가능 - 후처리]
 *
 * 활용 예시:
 *   - 접근 제어: 특정 동물만 쫓아낼 수 있도록 권한 검사
 *   - 로깅: 쫓아낸 기록을 남김
 *   - 캐싱: 이미 쫓아낸 동물은 중복 처리 방지
 */
public class DoormanProxy extends Doorman {
    /**
     * 프록시 메서드 - 실제 Doorman의 chaseOut()을 대리 호출
     * - 현재는 단순 위임만 수행하지만,
     *   super.chaseOut(a) 전후에 부가 로직을 추가할 수 있는 구조
     *
     * 예) if (a instanceof Tiger) { log("호랑이 접근"); }  ← 전처리
     *     super.chaseOut(a);                               ← 실제 작업
     *     log("처리 완료");                                  ← 후처리
     */
    @Override
    public void chaseOut(Animal a){
        super.chaseOut(a); // RealSubject(Doorman)에게 위임
    }
}
