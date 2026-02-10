package ex01;

/**
 * [구체 전략 A] Tiger - Animal을 구현한 호랑이 클래스
 *
 * 전략 패턴에서 ConcreteStrategy에 해당한다.
 * - Animal 추상 클래스를 상속받아 getName()을 "호랑이"로 구현
 * - Doorman은 Tiger인지 모르고, Animal 타입으로만 다룬다 (다형성)
 */
public class Tiger extends Animal {
    private String name = "호랑이";

    /** Animal의 추상 메서드를 구현 - 호랑이 고유의 이름 반환 */
    @Override
    public String getName() {
        return name;
    }
}
