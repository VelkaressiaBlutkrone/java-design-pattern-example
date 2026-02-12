package ex09.student;

/**
 * 2-a. 구체 Delegate - 수학 숙제를 위임받아 수행하는 객체
 *
 * README의 ConsolePrinter에 해당한다.
 * Student 인터페이스를 구현하여, Delegator가 수학 숙제를 이 객체에게 위임할 수 있다.
 * Delegator는 Student 인터페이스에만 의존하므로, MathStudent의 구체적인 구현을 알 필요가 없다.
 */
public class MathStudent implements Student {
    /** 담당 과목명 - 위임 요청 시 매칭에 사용 */
    private final String HWN = "MATH";

    /** 위임받은 수학 숙제를 실제로 수행 (= README의 ConsolePrinter.print()) */
    @Override
    public void doHomework(){
        System.out.println("수학 숙제를 합니다.");
    }

    /** 입력값이 "math"(대소문자 무관)인지 확인하여 자신이 처리할 작업인지 판별 */
    @Override
    public boolean isSameHomework(String keyboard) {
        return HWN.equals(keyboard.toUpperCase());
    }
}
