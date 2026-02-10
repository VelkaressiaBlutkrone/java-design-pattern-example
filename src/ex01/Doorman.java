package ex01;

/**
 * [Context 역할] Doorman - 동물을 쫓아내는 문지기
 *
 * 전략 패턴에서 Context에 해당한다.
 * - Animal 추상 타입에만 의존 → Tiger인지 Mouse인지 알 필요 없음
 * - 어떤 Animal이 전달되든 동일한 방식(chaseOut)으로 처리 (다형성 활용)
 * - 새로운 Animal 구현체가 추가되어도 이 클래스는 수정 불필요 (OCP)
 */
public class Doorman {
    /**
     * 전달받은 동물을 쫓아내는 메서드
     * @param a Animal 추상 타입 - 구체 타입(Tiger, Mouse 등)을 몰라도 동작
     *          → 런타임에 전달된 객체의 실제 타입에 따라 getName() 결과가 달라진다 (동적 바인딩)
     */
    public void chaseOut(Animal a){
        System.out.println(a.getName() + " chase out!!");
    }
}
