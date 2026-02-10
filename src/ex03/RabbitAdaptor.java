package ex03;

import ex01.Animal;
import ex03.lib.OuterRabbit;

/**
 * [Adapter 역할] RabbitAdaptor - OuterRabbit을 Animal 인터페이스로 변환하는 어댑터
 *
 * 어댑터 패턴에서 Adapter에 해당한다. (객체 어댑터 방식 - 합성 사용)
 * - Animal(Target)을 상속하여 시스템이 기대하는 getName()을 제공
 * - OuterRabbit(Adaptee)을 합성(has-a)으로 보유하여 실제 데이터를 가져옴
 * - getName() 호출 → outerRabbit.getFullName()으로 변환하여 인터페이스 차이를 해소
 *
 * 변환 흐름:
 *   Doorman.chaseOut(animal)
 *     → animal.getName()          ← Animal(Target)의 표준 메서드
 *       → outerRabbit.getFullName() ← OuterRabbit(Adaptee)의 고유 메서드로 변환
 *
 * 핵심: OuterRabbit의 코드를 수정하지 않고, 어댑터만 추가하여 호환성 확보
 */
public class RabbitAdaptor extends Animal {

    /** Adaptee - 외부 라이브러리 객체를 합성(has-a)으로 보유 */
    private final OuterRabbit outerRabbit;

    /**
     * 생성자 - 변환할 외부 객체(Adaptee)를 주입받음
     * @param outerRabbit 외부 라이브러리의 토끼 객체
     */
    public RabbitAdaptor(OuterRabbit outerRabbit) {
        this.outerRabbit = outerRabbit;
    }

    /**
     * 인터페이스 변환의 핵심
     * - Target(Animal)의 getName()을 구현
     * - 내부에서 Adaptee(OuterRabbit)의 getFullName()을 호출하여 변환
     */
    @Override
    public String getName() {
        return outerRabbit.getFullName(); // getFullName() → getName()으로 변환
    }
}
