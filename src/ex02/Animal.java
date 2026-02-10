package ex02;

/**
 * Animal - 모든 동물의 추상 클래스
 *
 * 프록시 패턴에서 직접적인 역할은 아니지만,
 * Doorman과 DoormanProxy가 처리하는 대상 객체의 공통 타입을 정의한다.
 */
public abstract class Animal {
    /** 동물의 이름을 반환하는 추상 메서드 */
    public abstract String getName();
}
