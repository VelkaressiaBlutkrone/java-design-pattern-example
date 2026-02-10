package ex03;

import ex03.lib.OuterRabbit;

/**
 * [클라이언트] App - 어댑터 패턴 동작을 확인하는 실행 클래스
 *
 * 실행 흐름:
 *   1. OuterRabbit(Adaptee) 생성 - 외부 라이브러리 객체 (getFullName() 사용)
 *   2. RabbitAdaptor(Adapter) 생성 - OuterRabbit을 감싸서 Animal 인터페이스로 변환
 *   3. adaptor.getName() 호출
 *      → 내부에서 outerRabbit.getFullName() 호출 → "토끼" 반환
 *
 * 포인트:
 *   - OuterRabbit은 Animal이 아니지만, RabbitAdaptor 덕분에 Animal처럼 사용 가능
 *   - OuterRabbit 코드를 수정하지 않고 어댑터만 추가하여 호환성 확보
 *   - Doorman.chaseOut(adaptor) 호출도 가능 → "토끼 chase out!!"
 */
public class App {
    public static void main(String[] args) {
        // Adaptee(외부 객체)를 Adapter로 감싸서 Target(Animal) 인터페이스로 변환
        RabbitAdaptor adaptor = new RabbitAdaptor(new OuterRabbit());
        adaptor.getName(); // getName() → 내부에서 getFullName()으로 변환 → "토끼"
    }
}
