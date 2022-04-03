package BattleShip;

import java.io.FileNotFoundException;

import javafx.fxml.FXML;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class GameController {

	
	private Game game;
	private Game gameAI;
	
	@FXML
	Pane board;
	@FXML
	Pane boardAI;
	@FXML
	DialogPane rules;
	@FXML
	DialogPane errors;
	@FXML
	ToggleButton HorVertButton;
	@FXML
	ToggleButton Save;
	@FXML
	ToggleButton Load;
	
	
	
	
	@FXML
	private void initialize() {
		game = new Game();
		game.setRandomShips();
		gameAI = new Game();

		setRuleText();
		resetErrorText();
		createBoard(board);
		createBoardAI();
		drawBoardAI();
	}
	
	private void setRuleText() {
		rules.setHeaderText(""
				+ "1. Start med å velge hvor du ønsker at skipene dine skal stå.\n"
				+ "Dette gjøres ved å trykke på det blå brettet og du kan bruke knappen "
				+ "Hor/Vert for å velge om det skipet du plasserer skal stå vertikalt eller horisontal."
				+ "\n"
				+ "Computer sine skip har blitt plassert tilfeldig.\n"
				+ "\n"
				+ "HUSK! To skip kan ikke stå oppå hverandre og skip kan ikke stå utenfor brettet.\n"
				+ "\n"
				+ "2. Klikk i hvite feltene for å skyte motstanders skip. Et treff blir markert med rødt mens en bom blir blått.\n"
				+ "\n"
				+ "CPU vil da skyte på ditt brett automatisk. Her vil treff bli markert grønn og bom blir gult."
				+ "Den som først har truffet alle motstanderens skip har vunnet. LYKKE TIL");
		rules.setStyle("-fx-font-size: 12px");
	}
	
	@FXML
	private void onHorVert() {
		if (gameAI.getHorVert()) {
			gameAI.setHor();
		}else {
			gameAI.setVert();
		}
	}
	
	@FXML
	private void onRestart() {
		initialize();
	}
	
	@FXML
	private void onSave() {
		new SaveHandler().save(game, gameAI);
	}
	
	@FXML
	private void onLoad() throws FileNotFoundException {
		game = new SaveHandler().loadGame();
		gameAI = new SaveHandler().loadGameAI();
		drawBoardAI();
		drawBoard();
	}
	

	private void resetErrorText() {
		errors.setHeaderText("");
	}
	
	
	
	
	private void createBoard(Pane board) {
		board.getChildren().clear();
		for (int y = 0; y < game.getHeight(); y++) {
			for (int x = 0; x < game.getWidth(); x++) {
				int a = x;
				int b = y;
				Pane tile = new Pane();
				tile.setPrefHeight(20);
				tile.setPrefWidth(20);
				tile.setTranslateX(x*20);
				tile.setTranslateY(y*20);
				tile.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
				board.getChildren().add(tile);
					tile.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
						if(game.isGameWon() == false && gameAI.isGameWonAI() == false && gameAI.isGameStarted() == true && game.getTile(a, b).isHit() == false && game.getTile(a, b).isMiss() == false) {

							game.playGame(game.getTile(a, b));
							gameAI.playGameAI(gameAI.randomTile());
						}
						drawBoardAI();
						drawBoard();
					});

					tile.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
						if(game.isGameWon() == false && gameAI.isGameWonAI() == false && gameAI.isGameStarted() == true && game.getTile(a, b).isHit() == false && game.getTile(a, b).isMiss() == false) {
							game.playGame(game.getTile(a, b));
							gameAI.playGameAI(gameAI.randomTile());
						}
					drawBoardAI();
					drawBoard();
					});

			}
		}
	}
	
	
	private void createBoardAI() {
		boardAI.getChildren().clear();
		for (int y = 0; y < gameAI.getHeight(); y++) {
			for (int x = 0; x < gameAI.getWidth(); x++) {
				int a = x;
				int b = y;
				Pane tile = new Pane();
				tile.setPrefHeight(20);
				tile.setPrefWidth(20);
				tile.setTranslateX(x*20);
				tile.setTranslateY(y*20);
				tile.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
				boardAI.getChildren().add(tile);
				tile.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
					if (gameAI.numberOfShips() == 0) {
						try {
							gameAI.setShip(gameAI.getHorVert(), game.getTile(a, b), 5);
							drawBoardAI();
							} catch (IllegalArgumentException r) {
								errors.setHeaderText("OBS! Skipet må stå innenfor rutenettet \n"
										+ "og kan ikke stå oppå et annet");
							}
					}
					else if (gameAI.numberOfShips() == 5) {
						try {
							gameAI.setShip(gameAI.getHorVert(), game.getTile(a, b), 4);
							drawBoardAI();
							} catch (IllegalArgumentException r) {
								errors.setHeaderText("OBS! Skipet må stå innenfor rutenettet \n"
										+ "og kan ikke stå oppå et annet");
							}
					}
					else if (gameAI.numberOfShips() == 9) {
						try {
							gameAI.setShip(gameAI.getHorVert(), game.getTile(a, b), 3);
							drawBoardAI();
							} catch (IllegalArgumentException r) {
								errors.setHeaderText("OBS! Skipet må stå innenfor rutenettet \n"
										+ "og kan ikke stå oppå et annet");
							}
					}
					else if (gameAI.numberOfShips() == 12) {
						try {
							gameAI.setShip(gameAI.getHorVert(), game.getTile(a, b), 2);
							drawBoardAI();
							} catch (IllegalArgumentException r) {
								errors.setHeaderText("OBS! Skipet må stå innenfor rutenettet \n"
										+ "og kan ikke stå oppå et annet");
							}
					}
					else if (gameAI.numberOfShips() == 14) {
						try {
							gameAI.setShip(gameAI.getHorVert(), game.getTile(a, b), 1);
							drawBoardAI();
							} catch (IllegalArgumentException r) {
								errors.setHeaderText("OBS! Skipet må stå innenfor rutenettet \n"
										+ "og kan ikke stå oppå et annet");
							}
					}
				});
			}			
		}
	}

	

	private void drawBoardAI() {
		for (int y = 0; y < gameAI.getHeight(); y++) {
			for (int x = 0; x < gameAI.getWidth(); x++) {
				boardAI.getChildren().get(y*gameAI.getWidth() + x).setStyle("-fx-background-color: " + getTileColorAI(gameAI.getTile(x, y)) + ";" + "-fx-border-color: black; -fx-border-width: 1px;");
			}
		}
		if (game.isGameWon() && gameAI.isGameWonAI()) {
			Text winOrLoseText = new Text();
			winOrLoseText.setText("DRAW");
			winOrLoseText.setStyle("-fx-font-size: 40px");
			winOrLoseText.setFill(Color.WHITE);
			winOrLoseText.setTranslateX(40);
			winOrLoseText.setTranslateY(110);
			winOrLoseText.setStroke(Color.BLACK);
			boardAI.getChildren().add(winOrLoseText);
		}
			else if (gameAI.isGameWonAI()) {
			Text winOrLoseText = new Text();
			winOrLoseText.setText("YOU WIN");
			winOrLoseText.setStyle("-fx-font-size: 40px");
			winOrLoseText.setFill(Color.GREEN);
			winOrLoseText.setTranslateX(20);
			winOrLoseText.setTranslateY(110);
			winOrLoseText.setStroke(Color.BLACK);
			boardAI.getChildren().add(winOrLoseText);
		} else if (game.isGameWon()) {
			Text winOrLoseText = new Text();
			winOrLoseText.setText("YOU LOSE");
			winOrLoseText.setStyle("-fx-font-size: 40px");
			winOrLoseText.setFill(Color.RED);
			winOrLoseText.setTranslateX(5);
			winOrLoseText.setTranslateY(110);
			winOrLoseText.setStroke(Color.BLACK);
			boardAI.getChildren().add(winOrLoseText);	
		} 
	}
	
	private void drawBoard() {
		for (int y = 0; y < game.getHeight(); y++) {
			for (int x = 0; x < game.getWidth(); x++) {
				board.getChildren().get(y*game.getWidth() + x).setStyle("-fx-background-color: " + getTileColor(game.getTile(x, y)) + ";" + "-fx-border-color: black; -fx-border-width: 1px;");
			}
		}
		if (game.isGameWon() && gameAI.isGameWonAI()) {
			Text winOrLoseText = new Text();
			winOrLoseText.setText("DRAW");
			winOrLoseText.setStyle("-fx-font-size: 40px");
			winOrLoseText.setFill(Color.WHITE);
			winOrLoseText.setTranslateX(40);
			winOrLoseText.setTranslateY(110);
			winOrLoseText.setStroke(Color.BLACK);
			board.getChildren().add(winOrLoseText);
		}
		else if (game.isGameWon()) {
			Text winOrLoseText = new Text();
			winOrLoseText.setText("YOU WIN");
			winOrLoseText.setStyle("-fx-font-size: 40px");
			winOrLoseText.setFill(Color.GREEN);
			winOrLoseText.setTranslateX(20);
			winOrLoseText.setTranslateY(110);
			winOrLoseText.setStroke(Color.BLACK);
			board.getChildren().add(winOrLoseText);
		} else if (gameAI.isGameWonAI()) {
			Text winOrLoseText = new Text();
			winOrLoseText.setText("YOU LOSE");
			winOrLoseText.setStyle("-fx-font-size: 40px");
			winOrLoseText.setFill(Color.RED);
			winOrLoseText.setTranslateX(5);
			winOrLoseText.setTranslateY(110);
			winOrLoseText.setStroke(Color.BLACK);
			board.getChildren().add(winOrLoseText);	
		}
	}
	
	
	
	private String getTileColorAI(Tile tile) {
		if (tile.isSea()) {
			return "#0975E8";
		}
		else if (tile.isShip()) {
			return "#636363";
		} else if (tile.isMiss()) {
			return "#FFFF00";
		} else {
			return "#00ff00";
		}

	}
	
	private String getTileColor(Tile tile) {
		if (tile.isSea()) {
			return "#FFFFFF";
		}
		else if (tile.isShip()) {
			return "#FFFFFF";
		} else if (tile.isMiss()) {
			return "#3653F3";
		} else {
			return "#F33836";
		}

	}
}
