package domain;

/**
 * 자동차 경주 게임에 사용되는 자동차 객체
 * @version 1.00 19.03.31
 * @author ep1stas1s
 */
public class Car {
	private static final int LIMIT = 4;
	private static final int MAX_RANDOM_VALUE = 9;
	
	private final String name;
	private int position = 0;

	public Car(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getPosition() {
		return position;
	}

	/**
	 * random 값이 LIMIT(4) 이상일 경우 position이 1증가
	 */
	public void move() {
		int random = makeRandomValue();
		if (random >= LIMIT) {
			position++;
		}
	}

	public int makeRandomValue() {
		return (int)(Math.random() * MAX_RANDOM_VALUE);
	}

	/**
	 * 현재 Car의 position을 막대바('-')로 출력
	 */
	public void printPosition() {
		StringBuilder positionToHypen = new StringBuilder();
		for (int i = 0; i < position; i++) {
			positionToHypen.append("-");
		}
		System.out.println(positionToHypen.toString());
	}
}
