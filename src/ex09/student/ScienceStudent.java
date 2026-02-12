package ex09.student;

/**
 * 2-b. 구체 Delegate - 과학 숙제를 위임받아 수행하는 객체
 *
 * README의 FilePrinter에 해당한다.
 * Student 인터페이스를 구현하여, Delegator가 과학 숙제를 이 객체에게 위임할 수 있다.
 */
public class ScienceStudent implements Student {
    /** 담당 과목명 */
    private final String HWN = "SCIENCE";

    /** 위임받은 과학 숙제를 실제로 수행 */
    @Override
    public void doHomework(){
        System.out.println("과학 숙제를 합니다.");
    }

    /** 입력값이 "science"(대소문자 무관)인지 확인 */
    @Override
    public boolean isSameHomework(String keyboard) {
        return HWN.equals(keyboard.toUpperCase());
    }
}
