package ex09.student;

/**
 * 2-c. 구체 Delegate - 역사 숙제를 위임받아 수행하는 객체
 *
 * MathStudent, ScienceStudent와 동일한 구조로 Student 인터페이스를 구현한다.
 * 새로운 과목이 추가되더라도 Student를 구현하는 새 클래스만 만들면 되므로,
 * 기존 코드를 수정하지 않고 확장할 수 있다. (OCP - 개방/폐쇄 원칙)
 */
public class HistoryStudent implements Student{
    /** 담당 과목명 */
    private final String HWN = "HISTORY";

    /** 위임받은 역사 숙제를 실제로 수행 */
    @Override
    public void doHomework() {
        System.out.println("역사 숙제를 합니다.");
    }

    /** 입력값이 "history"(대소문자 무관)인지 확인 */
    @Override
    public boolean isSameHomework(String keyboard) {
        return HWN.equals(keyboard.toUpperCase());
    }
}
