package ex10.factory;

import ex10.command.Command;
import ex10.command.LightOnCommand;
import ex10.command.MusicPlayCommand;
import ex10.receiver.Light;
import ex10.receiver.Music;

/**
 * 4. CommandFactory - 팩토리 패턴으로 커맨드 객체 생성을 담당
 *
 * 팩토리 패턴과 커맨드 패턴의 결합:
 * - 클라이언트는 구체적인 Command 클래스(LightOnCommand, MusicPlayCommand)를 알 필요가 없다.
 * - 타입 문자열만 전달하면, 팩토리가 적절한 Command 객체를 생성하여 반환한다.
 * - 새로운 Command 추가 시 팩토리의 switch문에 case만 추가하면 된다.
 *
 * 구조:
 *   Client ──▶ CommandFactory.create("light_on") ──▶ LightOnCommand
 *              CommandFactory.create("music_play") ──▶ MusicPlayCommand
 */
public class CommandFactory {
    /** Receiver 객체들 - 팩토리가 Command 생성 시 주입 */
    private Light light = new Light();
    private Music music = new Music();

    /**
     * 타입 문자열에 따라 적절한 Command 객체를 생성하여 반환
     *
     * 클라이언트는 Command 인터페이스만 사용하므로,
     * 구체 Command 클래스의 존재를 알 필요가 없다.
     *
     * @param type 생성할 커맨드 타입 ("light_on", "music_play")
     * @return 생성된 Command 객체
     */
    public Command create(String type) {
        switch (type) {
            case "light_on":
                return new LightOnCommand(light);
            case "music_play":
                return new MusicPlayCommand(music);
            default:
                throw new IllegalArgumentException("알 수 없는 명령: " + type);
        }
    }
}
