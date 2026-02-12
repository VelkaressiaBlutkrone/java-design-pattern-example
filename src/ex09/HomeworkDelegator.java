package ex09;

import ex09.student.HistoryStudent;
import ex09.student.MathStudent;
import ex09.student.ScienceStudent;
import ex09.student.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * 3. Delegator - 직접 숙제를 수행하지 않고 적절한 Student(Delegate)에게 위임하는 클래스
 *
 * README의 PrintService에 해당한다.
 * - PrintService가 Printer(delegate)에게 출력을 위임하듯,
 *   HomeworkDelegator는 Student(delegate)에게 숙제 수행을 위임한다.
 *
 * 핵심 원리:
 * - 상속 대신 합성(has-a)을 사용하여 코드를 재사용한다.
 *   → HomeworkDelegator는 Student 리스트를 합성으로 보유
 * - 위임 대상을 런타임에 교체/추가할 수 있어 유연성이 높다.
 *   → 새로운 과목 학생 클래스를 추가해도 이 클래스를 수정할 필요가 없다.
 *
 * 구조:
 *   HomeworkDelegator ──has-a──▶ List<Student>
 *    + delegateHomework()          + doHomework()     ← 위임 호출
 *      → student.doHomework()
 */
public class HomeworkDelegator {
    /** Delegate 목록 - 위임할 학생 객체들을 합성(has-a)으로 보유 */
    List<Student> studentList = new ArrayList<>();

    /** 생성자 - 위임 대상이 되는 구체 Delegate 객체들을 등록 */
    public HomeworkDelegator() {
        studentList.add(new MathStudent());
        studentList.add(new MathStudent());
        studentList.add(new ScienceStudent());
        studentList.add(new ScienceStudent());
        studentList.add(new HistoryStudent());
        studentList.add(new HistoryStudent());
    }

    /**
     * 위임 메서드 - 자신이 직접 숙제를 처리하지 않고 Delegate에게 위임
     *
     * 1) 입력된 과목명(keyboard)과 일치하는 학생을 필터링 (isSameHomework)
     * 2) 해당 학생에게 숙제 수행을 위임 (doHomework)
     *
     * README 대응: PrintService.printMessage() → delegate.print() 위임 호출과 동일한 구조
     */
    public void delegateHomework(String keyboard){
        studentList.stream().filter(s-> s.isSameHomework(keyboard)).forEach(s->s.doHomework());
    }
}
