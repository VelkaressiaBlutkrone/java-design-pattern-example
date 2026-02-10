package ex01;

/**
 * [구체 전략 B] Mouse - Animal을 구현한 쥐 클래스
 *
 * 전략 패턴에서 ConcreteStrategy에 해당한다.
 * - Animal 추상 클래스를 상속받아 getName()을 "쥐"로 구현
 * - 새로운 동물을 추가해도 Doorman 코드는 수정할 필요 없다 (OCP: 개방-폐쇄 원칙)
 */
public class Mouse extends Animal {
    /** Animal의 추상 메서드를 구현 - 쥐 고유의 이름 반환 */
    @Override
    public String getName() {
        return name;
    }

    private String name = "쥐";
}
