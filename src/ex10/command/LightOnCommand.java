package ex10.command;

import ex10.receiver.Light;

/**
 * 3-a. 구체 Command - "조명 켜기" 요청을 객체로 캡슐화
 *
 * 커맨드 패턴의 핵심: 요청(명령)을 객체로 만들어서
 * 매개변수화, 큐잉, 로깅, 실행 취소(Undo)를 가능하게 한다.
 *
 * - execute() : Receiver(Light)의 on() 호출 → 조명 켜기
 * - undo()    : Receiver(Light)의 off() 호출 → 실행 취소 = 반대 동작
 */
public class LightOnCommand implements Command {
    /** Receiver - 실제 작업을 수행하는 대상 */
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    /** 명령 실행: Receiver에게 "조명 켜기" 위임 */
    @Override
    public void execute() {
        light.on();
    }

    /** 실행 취소: 반대 동작인 "조명 끄기" 수행 */
    @Override
    public void undo() {
        light.off();
    }
}
