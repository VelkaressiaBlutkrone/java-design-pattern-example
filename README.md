## 프로젝트 구조

```
src/
├── ex00/          # 메모리 구조 & 다형성 기초
├── ex01/          # 전략 패턴 (Strategy)
├── ex02/          # 프록시 패턴 (Proxy)
├── ex03/          # 어댑터 패턴 (Adapter)
├── ex04/          # 싱글톤 패턴 (Singleton)
├── ex05/          # 템플릿 메서드 패턴 (Template Method)
├── ex06/          # 데코레이터 패턴 (Decorator)
├── ex07/          # 팩토리 패턴 (Factory)
├── ex08/          # 옵저버 패턴 (Observer)
│   ├── polling/   #   - 폴링 방식 (Pull)
│   └── push/      #   - 푸시 방식 (Push)
│       ├── pub/   #     - 발행자 (Publisher)
│       └── sub/   #     - 구독자 (Subscriber)
├── ex09/          # 위임 패턴 (Delegation)
├── ex10/          # 커맨드 + 팩토리 패턴 (Command + Factory)
└── mock/          # 목 객체 패턴 (Mock Object)
```

---

## 패턴별 개념 설명

### ex01 - 전략 패턴 (Strategy)

**목적:** 알고리즘(행위)을 캡슐화하여 런타임에 교체할 수 있게 한다.

- 동일한 문제를 해결하는 여러 알고리즘을 인터페이스로 추상화한다.
- 클라이언트는 구체적인 알고리즘을 몰라도, 인터페이스를 통해 실행할 수 있다.
- `if-else` / `switch` 분기를 제거하고, 새로운 전략을 추가해도 기존 코드를 수정하지 않는다. (OCP)

```
Context ──has-a──▶ <<interface>> Strategy
                        ▲
              ┌─────────┼─────────┐
        ConcreteA   ConcreteB   ConcreteC
```

**예제 코드:**

```java
// 1. 전략 인터페이스 - 모든 정렬 알고리즘이 구현해야 할 계약
interface SortStrategy {
    void sort(int[] data);
}

// 2. 구체 전략 A - 버블 정렬
class BubbleSort implements SortStrategy {
    @Override
    public void sort(int[] data) {
        System.out.println("버블 정렬 수행");
        // 버블 정렬 로직...
    }
}

// 3. 구체 전략 B - 퀵 정렬
class QuickSort implements SortStrategy {
    @Override
    public void sort(int[] data) {
        System.out.println("퀵 정렬 수행");
        // 퀵 정렬 로직...
    }
}

// 4. Context - 전략을 사용하는 클래스. 어떤 정렬인지 몰라도 된다.
class Sorter {
    private SortStrategy strategy; // 전략을 합성(has-a)으로 보유

    public Sorter(SortStrategy strategy) {
        this.strategy = strategy;
    }

    // 런타임에 전략 교체 가능
    public void setStrategy(SortStrategy strategy) {
        this.strategy = strategy;
    }

    public void doSort(int[] data) {
        strategy.sort(data); // 위임: 실제 정렬은 전략 객체가 수행
    }
}

// 5. 사용 예시
Sorter sorter = new Sorter(new BubbleSort()); // 버블 정렬로 시작
sorter.doSort(data);

sorter.setStrategy(new QuickSort());           // 런타임에 퀵 정렬로 교체
sorter.doSort(data);
```

> **핵심:** `Sorter`는 `SortStrategy` 인터페이스에만 의존하므로, 새로운 정렬 알고리즘을 추가해도 `Sorter` 코드를 수정할 필요가 없다.

---

### ex02 - 프록시 패턴 (Proxy)

**목적:** 실제 객체에 대한 접근을 제어하는 대리 객체를 둔다.

- 프록시는 실제 객체와 같은 인터페이스를 구현하여, 클라이언트는 프록시인지 실제 객체인지 구분하지 못한다.
- **접근 제어**(권한 검사), **지연 초기화**(Lazy Loading), **로깅/캐싱** 등에 활용한다.
- 실제 객체의 생성·호출 전후에 부가 로직을 삽입할 수 있다.

```
Client ──▶ <<interface>> Subject
                 ▲
           ┌─────┴─────┐
         Proxy ──▶ RealSubject
```

**예제 코드:**

```java
// 1. 공통 인터페이스 - 프록시와 실제 객체 모두 이 인터페이스를 구현
interface Image {
    void display();
}

// 2. 실제 객체 - 생성 비용이 큰 고해상도 이미지
class RealImage implements Image {
    private String filename;

    public RealImage(String filename) {
        this.filename = filename;
        loadFromDisk(); // 생성 시 무거운 작업 수행
    }

    private void loadFromDisk() {
        System.out.println("[무거운 작업] " + filename + " 디스크에서 로딩...");
    }

    @Override
    public void display() {
        System.out.println(filename + " 화면에 표시");
    }
}

// 3. 프록시 - 실제 객체의 생성을 지연(Lazy Loading)시키는 대리 객체
class ProxyImage implements Image {
    private String filename;
    private RealImage realImage; // 실제 객체 참조 (처음엔 null)

    public ProxyImage(String filename) {
        this.filename = filename;
        // 여기서는 RealImage를 생성하지 않음 → 지연 초기화
    }

    @Override
    public void display() {
        // 최초 호출 시에만 실제 객체 생성
        if (realImage == null) {
            realImage = new RealImage(filename);
        }
        realImage.display(); // 실제 객체에게 위임
    }
}

// 4. 사용 예시
Image img = new ProxyImage("photo.jpg"); // 아직 디스크 로딩 안 함
img.display(); // 이 시점에 RealImage 생성 + 로딩 + 표시
img.display(); // 이미 로딩됨, 바로 표시
```

> **핵심:** 클라이언트는 `Image` 인터페이스만 사용하므로, `ProxyImage`인지 `RealImage`인지 구분하지 못한다. 프록시가 접근 제어·지연 로딩 등 부가 기능을 투명하게 제공한다.

---

### ex03 - 어댑터 패턴 (Adapter)

**목적:** 호환되지 않는 인터페이스를 가진 클래스를 함께 사용할 수 있게 변환한다.

- 기존 클래스(Adaptee)의 인터페이스를 클라이언트가 기대하는 인터페이스(Target)로 변환한다.
- 기존 코드를 수정하지 않고, 어댑터 클래스만 추가하여 호환성을 확보한다.
- **클래스 어댑터**(상속 이용)와 **객체 어댑터**(합성 이용) 두 가지 방식이 있다.

```
Client ──▶ <<interface>> Target
                 ▲
              Adapter ──has-a──▶ Adaptee
```

**예제 코드:**

```java
// 1. Target 인터페이스 - 클라이언트가 기대하는 인터페이스
interface MediaPlayer {
    void play(String filename);
}

// 2. Adaptee - 이미 존재하는 클래스, 다른 인터페이스를 가짐
class VlcPlayer {
    public void playVlc(String filename) {
        System.out.println("VLC로 재생: " + filename);
    }
}

// 3. Adapter - Adaptee를 Target 인터페이스에 맞게 변환
class VlcAdapter implements MediaPlayer {
    private VlcPlayer vlcPlayer; // Adaptee를 합성으로 보유

    public VlcAdapter() {
        this.vlcPlayer = new VlcPlayer();
    }

    @Override
    public void play(String filename) {
        // Target의 play() 호출을 Adaptee의 playVlc()로 변환
        vlcPlayer.playVlc(filename);
    }
}

// 4. 사용 예시
MediaPlayer player = new VlcAdapter();
player.play("movie.vlc"); // 클라이언트는 MediaPlayer 인터페이스만 알면 된다
```

> **핵심:** `VlcPlayer`의 코드를 수정하지 않고, `VlcAdapter`만 추가하여 `MediaPlayer` 인터페이스에 맞게 변환했다. 기존 코드 변경 없이 호환성을 확보하는 것이 어댑터의 핵심이다.

---

### ex04 - 싱글톤 패턴 (Singleton)

**목적:** 클래스의 인스턴스가 오직 하나만 존재하도록 보장하고, 전역 접근점을 제공한다.

- 생성자를 `private`으로 막고, 정적 메서드(`getInstance()`)를 통해 유일한 인스턴스를 반환한다.
- 설정 관리, 로깅, 커넥션 풀 등 하나의 인스턴스만 필요한 자원에 사용한다.
- 멀티스레드 환경에서는 동기화 처리(`synchronized`, `enum`, `static inner class`)가 필요하다.

```
Singleton
 - static instance: Singleton
 - private Singleton()
 + static getInstance(): Singleton
```

**예제 코드:**

```java
// 싱글톤 클래스 - 인스턴스가 오직 1개만 존재
class DatabaseConnection {
    // 1. 유일한 인스턴스를 저장하는 정적 변수
    private static DatabaseConnection instance;

    private String url;

    // 2. 생성자를 private으로 막아 외부에서 new 불가
    private DatabaseConnection() {
        this.url = "jdbc:mysql://localhost:3306/mydb";
        System.out.println("DB 커넥션 생성");
    }

    // 3. 전역 접근점 - 인스턴스가 없으면 생성, 있으면 기존 것 반환
    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection(); // 최초 1회만 생성
        }
        return instance;
    }

    public void query(String sql) {
        System.out.println("[" + url + "] 쿼리 실행: " + sql);
    }
}

// 4. 사용 예시
DatabaseConnection db1 = DatabaseConnection.getInstance();
DatabaseConnection db2 = DatabaseConnection.getInstance();
System.out.println(db1 == db2); // true → 같은 인스턴스
db1.query("SELECT * FROM users");
```

> **핵심:** `private` 생성자 + `static getInstance()`로 인스턴스를 1개로 제한한다. `synchronized`로 멀티스레드 안전성을 확보한다.

---

### ex05 - 템플릿 메서드 패턴 (Template Method)

**목적:** 알고리즘의 골격(템플릿)을 상위 클래스에서 정의하고, 세부 단계를 하위 클래스에서 구현한다.

- 상위 클래스가 `final` 템플릿 메서드로 실행 순서를 고정한다.
- 하위 클래스는 추상 메서드(hook)를 오버라이딩하여 각 단계의 구체적인 동작을 결정한다.
- **제어의 역전(IoC)**: 상위 클래스가 하위 클래스를 호출하는 구조이다. ("Don't call us, we'll call you")

```
AbstractClass
 + templateMethod()  ← final (골격 정의)
 # step1()           ← abstract
 # step2()           ← abstract
       ▲
 ConcreteClass
 # step1() { ... }
 # step2() { ... }
```

**예제 코드:**

```java
// 1. 추상 클래스 - 알고리즘의 골격(템플릿)을 정의
abstract class Beverage {
    // 템플릿 메서드 - 실행 순서를 고정 (final로 오버라이딩 방지)
    public final void prepare() {
        boilWater();    // 공통 단계
        brew();         // 하위 클래스마다 다른 단계
        pourInCup();    // 공통 단계
        addCondiments();// 하위 클래스마다 다른 단계
    }

    private void boilWater() {
        System.out.println("물 끓이기");
    }

    private void pourInCup() {
        System.out.println("컵에 따르기");
    }

    // 하위 클래스가 구현해야 할 추상 메서드 (hook)
    protected abstract void brew();
    protected abstract void addCondiments();
}

// 2. 구체 클래스 A - 커피 만들기
class Coffee extends Beverage {
    @Override
    protected void brew() {
        System.out.println("필터로 커피 우려내기");
    }

    @Override
    protected void addCondiments() {
        System.out.println("설탕과 우유 추가");
    }
}

// 3. 구체 클래스 B - 차 만들기
class Tea extends Beverage {
    @Override
    protected void brew() {
        System.out.println("찻잎을 우려내기");
    }

    @Override
    protected void addCondiments() {
        System.out.println("레몬 추가");
    }
}

// 4. 사용 예시
Beverage coffee = new Coffee();
coffee.prepare();
// 출력: 물 끓이기 → 필터로 커피 우려내기 → 컵에 따르기 → 설탕과 우유 추가

Beverage tea = new Tea();
tea.prepare();
// 출력: 물 끓이기 → 찻잎을 우려내기 → 컵에 따르기 → 레몬 추가
```

> **핵심:** `prepare()`가 실행 순서를 고정하고, 하위 클래스는 `brew()`와 `addCondiments()`만 구현한다. 상위 클래스가 하위 클래스를 호출하는 **제어의 역전(IoC)** 구조이다.

---

### ex06 - 데코레이터 패턴 (Decorator)

**목적:** 객체에 동적으로 새로운 책임(기능)을 추가한다. 상속 대신 합성을 사용한다.

- 데코레이터는 감싸는 객체와 동일한 인터페이스를 구현하여 투명하게 기능을 확장한다.
- 여러 데코레이터를 겹겹이 감싸서(체이닝) 기능을 조합할 수 있다.
- 상속으로 인한 클래스 폭발 문제를 해결한다.

```
<<interface>> Component
       ▲
 ┌─────┴──────────┐
Concrete      Decorator ──has-a──▶ Component
Component        ▲
           ┌─────┴─────┐
       DecoratorA   DecoratorB
```

**예제 코드:**

```java
// 1. Component 인터페이스 - 기본 기능 정의
interface Coffee {
    String getDescription();
    int getCost();
}

// 2. 기본 구현 - 장식 대상이 되는 원본 객체
class BasicCoffee implements Coffee {
    @Override
    public String getDescription() { return "기본 커피"; }

    @Override
    public int getCost() { return 3000; }
}

// 3. 데코레이터 추상 클래스 - Component를 감싸는 래퍼
abstract class CoffeeDecorator implements Coffee {
    protected Coffee coffee; // 감싸는 대상 (합성)

    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }
}

// 4. 구체 데코레이터 A - 우유 추가
class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + " + 우유"; // 기존 설명에 추가
    }

    @Override
    public int getCost() {
        return coffee.getCost() + 500; // 기존 가격에 추가
    }
}

// 5. 구체 데코레이터 B - 시럽 추가
class SyrupDecorator extends CoffeeDecorator {
    public SyrupDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + " + 시럽";
    }

    @Override
    public int getCost() {
        return coffee.getCost() + 300;
    }
}

// 6. 사용 예시 - 데코레이터를 겹겹이 감싸서 기능 조합
Coffee order = new BasicCoffee();                  // 기본 커피 3000원
order = new MilkDecorator(order);                  // + 우유 → 3500원
order = new SyrupDecorator(order);                 // + 시럽 → 3800원
System.out.println(order.getDescription());        // "기본 커피 + 우유 + 시럽"
System.out.println(order.getCost() + "원");         // "3800원"
```

> **핵심:** 상속 없이 객체를 감싸는 방식으로 기능을 동적으로 조합한다. `MilkDecorator(SyrupDecorator(BasicCoffee()))` 처럼 체이닝이 자유롭다.

---

### ex07 - 팩토리 패턴 (Factory)

**목적:** 객체 생성 로직을 별도의 팩토리에 위임하여, 클라이언트가 구체 클래스를 직접 `new`하지 않게 한다.

- 클라이언트는 팩토리에게 "무엇을 만들어줘"라고 요청만 하고, 구체적인 생성 방법은 모른다.
- **팩토리 메서드**: 하위 클래스가 생성할 객체의 타입을 결정한다.
- **추상 팩토리**: 관련 있는 객체군을 한꺼번에 생성하는 인터페이스를 제공한다.

```
Client ──▶ Factory.create(type)
                │
         ┌──────┼──────┐
     ProductA  ProductB  ProductC
```

**예제 코드:**

```java
// 1. Product 인터페이스 - 팩토리가 생성할 객체의 공통 타입
interface Shape {
    void draw();
}

// 2. 구체 Product들
class Circle implements Shape {
    @Override
    public void draw() { System.out.println("원 그리기"); }
}

class Rectangle implements Shape {
    @Override
    public void draw() { System.out.println("사각형 그리기"); }
}

class Triangle implements Shape {
    @Override
    public void draw() { System.out.println("삼각형 그리기"); }
}

// 3. 팩토리 - 타입에 따라 적절한 객체를 생성하여 반환
class ShapeFactory {
    // 클라이언트는 구체 클래스를 몰라도 됨 → 인터페이스(Shape)만 사용
    public static Shape create(String type) {
        switch (type) {
            case "circle":    return new Circle();
            case "rectangle": return new Rectangle();
            case "triangle":  return new Triangle();
            default: throw new IllegalArgumentException("알 수 없는 도형: " + type);
        }
    }
}

// 4. 사용 예시
Shape shape1 = ShapeFactory.create("circle");    // new Circle()을 직접 쓰지 않음
Shape shape2 = ShapeFactory.create("rectangle");
shape1.draw(); // "원 그리기"
shape2.draw(); // "사각형 그리기"
```

> **핵심:** 클라이언트는 `ShapeFactory.create("circle")`만 호출하고, `Circle` 클래스의 존재를 알 필요가 없다. 새로운 도형 추가 시 팩토리만 수정하면 된다.

---

### ex08 - 옵저버 패턴 (Observer)

**목적:** 한 객체의 상태 변화를 관찰하는 다수의 객체에게 자동으로 알린다. (1:N 의존 관계)

#### 폴링 방식 (Pull)
- 옵저버가 주기적으로 주체(Subject)의 상태를 직접 확인한다.
- 구현이 단순하지만, 불필요한 확인이 반복되어 비효율적이다.

#### 푸시 방식 (Push)
- 주체의 상태가 변하면 등록된 모든 구독자에게 즉시 통지한다.
- **Publisher(발행자)**: 상태를 관리하고, 변경 시 구독자에게 알린다.
- **Subscriber(구독자)**: 알림을 받아 자신의 상태를 갱신한다.

```
Subject (Publisher)
 - observers: List<Observer>
 + subscribe(observer)
 + unsubscribe(observer)
 + notify()
       │
       ▼
<<interface>> Observer (Subscriber)
 + update(data)
```

**예제 코드 (Push 방식):**

```java
import java.util.ArrayList;
import java.util.List;

// 1. Observer(Subscriber) 인터페이스 - 알림을 받을 객체의 계약
interface Subscriber {
    void update(String news);
}

// 2. Subject(Publisher) - 상태를 관리하고 구독자에게 알림을 보냄
class NewsPublisher {
    private List<Subscriber> subscribers = new ArrayList<>();
    private String latestNews;

    public void subscribe(Subscriber sub) {
        subscribers.add(sub);
    }

    public void unsubscribe(Subscriber sub) {
        subscribers.remove(sub);
    }

    // 상태 변경 시 모든 구독자에게 push
    public void publish(String news) {
        this.latestNews = news;
        notifySubscribers();
    }

    private void notifySubscribers() {
        for (Subscriber sub : subscribers) {
            sub.update(latestNews); // 데이터를 직접 전달 (Push)
        }
    }
}

// 3. 구체 Subscriber들
class EmailSubscriber implements Subscriber {
    private String name;

    public EmailSubscriber(String name) { this.name = name; }

    @Override
    public void update(String news) {
        System.out.println("[이메일-" + name + "] 새 뉴스: " + news);
    }
}

class SmsSubscriber implements Subscriber {
    private String phone;

    public SmsSubscriber(String phone) { this.phone = phone; }

    @Override
    public void update(String news) {
        System.out.println("[SMS-" + phone + "] 새 뉴스: " + news);
    }
}

// 4. 사용 예시
NewsPublisher publisher = new NewsPublisher();

Subscriber email = new EmailSubscriber("홍길동");
Subscriber sms = new SmsSubscriber("010-1234");

publisher.subscribe(email);
publisher.subscribe(sms);

publisher.publish("디자인 패턴 시험 내일!");
// 출력:
// [이메일-홍길동] 새 뉴스: 디자인 패턴 시험 내일!
// [SMS-010-1234] 새 뉴스: 디자인 패턴 시험 내일!

publisher.unsubscribe(sms);
publisher.publish("시험 범위 변경");
// 출력:
// [이메일-홍길동] 새 뉴스: 시험 범위 변경
```

**예제 코드 (Polling 방식):**

```java
// Polling - 옵저버가 주기적으로 상태를 직접 확인 (Pull)
class NewsBoard {
    private String latestNews;

    public void setNews(String news) { this.latestNews = news; }
    public String getNews() { return latestNews; } // getter만 제공
}

class PollingReader {
    private NewsBoard board;
    private String lastRead;

    public PollingReader(NewsBoard board) { this.board = board; }

    // 주기적으로 호출하여 변경 여부를 직접 확인
    public void checkForUpdate() {
        String current = board.getNews(); // Pull: 직접 가져옴
        if (current != null && !current.equals(lastRead)) {
            System.out.println("새 뉴스 발견: " + current);
            lastRead = current;
        } else {
            System.out.println("변경 없음");
        }
    }
}
```

> **핵심:** Push 방식은 발행자가 변경 즉시 구독자에게 알려 효율적이고, Polling 방식은 옵저버가 직접 확인하므로 단순하지만 비효율적이다.

---

### ex09 - 위임 패턴 (Delegation)

**목적:** 객체가 특정 작업을 직접 수행하지 않고, 다른 객체(delegate)에게 위임한다.

- 상속 대신 합성을 사용하여 코드를 재사용하는 기법이다.
- 위임받는 객체를 런타임에 교체할 수 있어 유연성이 높다.
- 전략 패턴, 프록시 패턴 등 다른 패턴의 기반이 되는 기본 원리이다.

```
Delegator ──has-a──▶ Delegate
 + doWork()           + doWork()
   → delegate.doWork()
```

**예제 코드:**

```java
// 1. Delegate 인터페이스 - 위임받을 객체의 계약
interface Printer {
    void print(String message);
}

// 2. 구체 Delegate들
class ConsolePrinter implements Printer {
    @Override
    public void print(String message) {
        System.out.println("[콘솔] " + message);
    }
}

class FilePrinter implements Printer {
    @Override
    public void print(String message) {
        System.out.println("[파일] " + message + " → file.txt에 저장");
    }
}

// 3. Delegator - 직접 일하지 않고 delegate에게 위임
class PrintService {
    private Printer delegate; // 위임 대상

    public PrintService(Printer delegate) {
        this.delegate = delegate;
    }

    // 런타임에 위임 대상 교체 가능
    public void setDelegate(Printer delegate) {
        this.delegate = delegate;
    }

    public void printMessage(String message) {
        // 자신이 직접 처리하지 않고 delegate에게 위임
        delegate.print(message);
    }
}

// 4. 사용 예시
PrintService service = new PrintService(new ConsolePrinter());
service.printMessage("Hello");          // [콘솔] Hello

service.setDelegate(new FilePrinter()); // 런타임에 위임 대상 교체
service.printMessage("World");          // [파일] World → file.txt에 저장
```

> **핵심:** `PrintService`는 출력 로직을 직접 구현하지 않고, `Printer` 인터페이스를 구현한 객체에게 위임한다. 전략 패턴과 유사하지만, 위임은 더 일반적인 원리로 다양한 패턴의 기반이 된다.

---

### ex10 - 커맨드 + 팩토리 패턴 (Command + Factory)

**목적:** 요청(명령)을 객체로 캡슐화하고, 팩토리가 적절한 커맨드 객체를 생성한다.

- **커맨드 패턴**: 실행할 작업을 객체로 만들어 매개변수화, 큐잉, 로깅, 실행 취소(Undo)를 가능하게 한다.
- **팩토리 패턴과 결합**: 커맨드 객체의 생성을 팩토리가 담당하여, 클라이언트는 구체적인 커맨드 타입을 몰라도 된다.

```
Client ──▶ CommandFactory.create(type) ──▶ <<interface>> Command
                                                 + execute()
                                                 + undo()
                                                    ▲
                                           ┌────────┼────────┐
                                       CommandA  CommandB  CommandC
```

**예제 코드:**

```java
// 1. Command 인터페이스 - 모든 명령의 공통 계약
interface Command {
    void execute();
    void undo();
}

// 2. Receiver - 실제 작업을 수행하는 객체
class Light {
    public void on()  { System.out.println("조명 켜짐"); }
    public void off() { System.out.println("조명 꺼짐"); }
}

class Music {
    public void play() { System.out.println("음악 재생"); }
    public void stop() { System.out.println("음악 정지"); }
}

// 3. 구체 Command들 - 요청을 객체로 캡슐화
class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) { this.light = light; }

    @Override
    public void execute() { light.on(); }

    @Override
    public void undo() { light.off(); } // 실행 취소 = 반대 동작
}

class MusicPlayCommand implements Command {
    private Music music;

    public MusicPlayCommand(Music music) { this.music = music; }

    @Override
    public void execute() { music.play(); }

    @Override
    public void undo() { music.stop(); }
}

// 4. CommandFactory - 타입 문자열로 적절한 커맨드 객체 생성
class CommandFactory {
    private Light light = new Light();
    private Music music = new Music();

    public Command create(String type) {
        switch (type) {
            case "light_on":   return new LightOnCommand(light);
            case "music_play": return new MusicPlayCommand(music);
            default: throw new IllegalArgumentException("알 수 없는 명령: " + type);
        }
    }
}

// 5. Invoker - 커맨드를 실행하고 이력을 관리
class RemoteControl {
    private List<Command> history = new ArrayList<>();

    public void press(Command cmd) {
        cmd.execute();
        history.add(cmd); // 실행 이력 저장 → undo 가능
    }

    public void undoLast() {
        if (!history.isEmpty()) {
            Command last = history.remove(history.size() - 1);
            last.undo();
        }
    }
}

// 6. 사용 예시
CommandFactory factory = new CommandFactory();

Command cmd1 = factory.create("light_on");    // 팩토리로 커맨드 생성
Command cmd2 = factory.create("music_play");

RemoteControl remote = new RemoteControl();
remote.press(cmd1); // "조명 켜짐"
remote.press(cmd2); // "음악 재생"
remote.undoLast();  // "음악 정지" (undo)
remote.undoLast();  // "조명 꺼짐" (undo)
```

> **핵심:** 커맨드 패턴은 요청을 객체로 만들어 실행/취소/큐잉을 가능하게 하고, 팩토리 패턴이 커맨드 객체 생성을 담당하여 클라이언트는 구체 커맨드 클래스를 알 필요가 없다.

---

### mock - 목 객체 패턴 (Mock Object)

**목적:** 테스트 시 실제 의존 객체를 가짜(Mock) 객체로 대체하여 단위 테스트를 격리한다.

- 외부 시스템(DB, 네트워크, 파일 등)에 의존하지 않고 테스트할 수 있다.
- 목 객체는 기대되는 호출과 반환값을 미리 설정(stub)하고, 호출 여부를 검증(verify)한다.
- **테스트 더블** 종류: Dummy, Stub, Spy, Mock, Fake

```
[테스트 코드] ──▶ [테스트 대상] ──▶ <<interface>> Dependency
                                          ▲
                                    ┌─────┴─────┐
                               RealImpl     MockImpl (테스트용)
```

**예제 코드:**

```java
// 1. 의존 인터페이스 - 실제/Mock 모두 이 인터페이스를 구현
interface UserRepository {
    String findNameById(int id);
    void save(String name);
}

// 2. 실제 구현 - DB에 접근 (테스트 시 사용하기 어려움)
class RealUserRepository implements UserRepository {
    @Override
    public String findNameById(int id) {
        // 실제 DB 쿼리 수행
        return "DB에서 조회한 이름";
    }

    @Override
    public void save(String name) {
        // 실제 DB에 저장
    }
}

// 3. Mock 구현 - DB 없이 테스트 가능한 가짜 객체
class MockUserRepository implements UserRepository {
    // Stub: 미리 정해둔 반환값
    private String stubbedName;
    // Verify: 메서드 호출 여부 기록
    private boolean saveCalled = false;
    private String lastSavedName;

    // 테스트 전에 반환값을 설정 (Stubbing)
    public void setStubbedName(String name) {
        this.stubbedName = name;
    }

    @Override
    public String findNameById(int id) {
        return stubbedName; // DB 대신 미리 설정한 값 반환
    }

    @Override
    public void save(String name) {
        this.saveCalled = true;       // 호출 사실 기록
        this.lastSavedName = name;    // 전달된 인자 기록
    }

    // 검증용 메서드들
    public boolean isSaveCalled() { return saveCalled; }
    public String getLastSavedName() { return lastSavedName; }
}

// 4. 테스트 대상 - Repository에 의존하는 서비스
class UserService {
    private UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository; // 의존성 주입 (DI)
    }

    public String getGreeting(int userId) {
        String name = repository.findNameById(userId);
        return "안녕하세요, " + name + "님!";
    }

    public void registerUser(String name) {
        repository.save(name);
    }
}

// 5. 테스트 코드 - Mock을 주입하여 DB 없이 테스트
MockUserRepository mockRepo = new MockUserRepository();
mockRepo.setStubbedName("홍길동");          // Stub: 반환값 설정

UserService service = new UserService(mockRepo); // Mock 주입

// Stub 검증 - 기대한 값이 반환되는지 확인
String greeting = service.getGreeting(1);
assert greeting.equals("안녕하세요, 홍길동님!"); // ✅ 통과

// Mock 검증 - 메서드가 올바르게 호출되었는지 확인
service.registerUser("이순신");
assert mockRepo.isSaveCalled();                  // ✅ save()가 호출됨
assert mockRepo.getLastSavedName().equals("이순신"); // ✅ 올바른 인자로 호출됨
```

> **핵심:** Mock 객체는 실제 의존성(DB 등)을 대체하여 테스트를 격리한다. **Stub**(미리 정한 값 반환)과 **Verify**(호출 여부 검증)가 핵심 기법이며, 의존성 주입(DI)을 통해 실제/Mock 객체를 교체한다.