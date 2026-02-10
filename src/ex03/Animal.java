package ex03;

/**
 * [Target 역할] Animal - 클라이언트(Doorman)가 기대하는 인터페이스
 *
 * 어댑터 패턴에서 Target에 해당한다.
 * - 시스템 내부에서 사용하는 공통 인터페이스: getName()
 * - OuterRabbit은 getFullName()을 사용하므로 직접 호환되지 않음
 * - RabbitAdaptor가 Animal을 상속하여 OuterRabbit을 이 인터페이스에 맞게 변환
 *
 * 구조: Doorman ──uses──▶ Animal(Target)
 *                            ▲
 *                   RabbitAdaptor(Adapter) ──has-a──▶ OuterRabbit(Adaptee)
 */
public abstract class Animal {
    /** 시스템이 기대하는 표준 메서드 - 모든 동물은 이 메서드를 구현해야 한다 */
    public abstract String getName();
}
