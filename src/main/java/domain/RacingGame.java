package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RacingGame {
	private static Scanner SCANNER = new Scanner(System.in);
	private static final int MAX_NAME_LENGTH = 5;
	private static final int MIN_NAME_LENGTH = 1;

	private List<Car> cars;
	private int count;
	private int maxPosition = 0;

	public List<Car> getCars() {
		return cars;
	}

	public int getCount() {
		return count;
	}

	public int getMaxPosition() {
		return maxPosition;
	}

	public void runProgram() {
		setUserName();
		setCount();
		startRace();
		printWinner();
		SCANNER.close();
	}

	//	setUserName : User name을 입력 받고 List에 추가
	private void setUserName() {
		String inputtedUserName = "";
		do {
			inputtedUserName = inputUserName();
		}while(Validator.isInvalidUserName(inputtedUserName));

		String[] names = inputtedUserName.split(",");
		cars = new ArrayList<>();
		for (String name : names) {
			name = name.trim();
			cars.add(new Car(name));
		}
	}
	
	private String inputUserName() {
		System.out.println("경주할 자동차 이름을 입력하세요. (이름은 쉼표(,) 기준을 구분, 이름은 최대 5자까지 가능합니다.)");
		return SCANNER.nextLine();
	}

	//	setCount : 시도할 회수를 입력 받음
	private void setCount() {
		System.out.println("시도할 회수는 몇회인가요? (최대 100회까지 가능)");
		String countInput = SCANNER.nextLine();

		// isValidCount

		count = Integer.parseInt(countInput);
	}

	//	isValidCount : 사용 가능한 count인지

	// startRace : 레이스 시작 (move -> printResult)
	// TODO overloading 을 통해 print 설정 (boolean printFlag) true-> 현재 상황 출력
	private void startRace() {
		for (int i = 0; i < count; i++) {
			moveCars();
			printCurrentSituation();
		}
	}

	private void moveCars() {
		for (Car car : cars) {
			car.move();
			updateMaxPosition(car.getPosition());
		}
	}
	
	private void updateMaxPosition(int position) {
		maxPosition = Math.max(position, maxPosition);
	}

	//	printResult : 각 회수의 실행 결과 출력
	private void printCurrentSituation() {
		for (Car car : cars) {
			car.printPosition();
		}
		System.out.println();
	}

	//	printWinner : 우승자 출력
	public void printWinner() {
		String result = "";
		for (Car car : cars) {
			if(car.getPosition() == maxPosition) {
				result += car.getName() + ", ";
			}
		}
		System.out.println(result.substring(0, result.length() - 2) + "가 최종 우승했습니다.");
	}
}
