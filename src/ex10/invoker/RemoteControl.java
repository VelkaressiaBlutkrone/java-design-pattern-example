package ex10.invoker;

import ex10.command.Command;

import java.util.ArrayList;
import java.util.List;

/**
 * 5. Invoker - 커맨드를 실행하고 이력을 관리하는 호출자
 *
 * Invoker는 Command 인터페이스에만 의존하며, 구체적인 명령이 무엇인지 알지 못한다.
 * 실행된 커맨드를 이력(history)에 저장하여, 나중에 실행 취소(Undo)를 가능하게 한다.
 *
 * 커맨드 패턴의 장점이 드러나는 핵심 클래스:
 * - 매개변수화: 어떤 Command든 press()에 전달 가능
 * - 큐잉/로깅: history 리스트에 실행 이력 저장
 * - 실행 취소: undoLast()로 마지막 명령의 반대 동작 수행
 */
public class RemoteControl {
    /** 실행 이력 - undo를 위해 실행된 커맨드를 순서대로 저장 */
    private List<Command> history = new ArrayList<>();

    /**
     * 커맨드를 실행하고 이력에 저장
     *
     * @param cmd 실행할 Command 객체
     */
    public void press(Command cmd) {
        cmd.execute();
        history.add(cmd);   // 실행 이력 저장 → undo 가능
    }

    /**
     * 마지막으로 실행한 커맨드를 취소 (Undo)
     *
     * 이력에서 가장 최근 커맨드를 꺼내 undo()를 호출한다.
     * 예: LightOnCommand.undo() → light.off() (반대 동작)
     */
    public void undoLast() {
        if (!history.isEmpty()) {
            Command last = history.remove(history.size() - 1);
            last.undo();
        }
    }
}
