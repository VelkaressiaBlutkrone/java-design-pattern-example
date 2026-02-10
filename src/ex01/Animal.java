package ex01;

/**
 * [전략 인터페이스 역할] Animal - 모든 동물이 구현해야 할 추상 클래스
 *
 * 전략 패턴에서 Strategy 인터페이스에 해당한다.
 * - 구체적인 동물(Tiger, Mouse)이 각자 getName()을 구현하여 서로 다른 행위를 제공
 * - Doorman(Context)은 이 추상 타입에만 의존하므로, 어떤 동물이든 동일하게 처리 가능
 *
 * 구조: Doorman ──uses──▶ Animal(추상) ◀── Tiger, Mouse (구체 전략)
 */
public abstract class Animal {
    /**
     * 동물의 이름을 반환하는 추상 메서드
     * - 각 구체 클래스(Tiger, Mouse)가 자신만의 이름을 반환하도록 오버라이딩
     */
    public abstract String getName();
}
