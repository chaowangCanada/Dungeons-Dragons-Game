package com.chaowang.ddgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import Items.Item;
import Character.Character;
import Map.Map;

public class MapEditorScreen implements Screen{

	private Game game;
	private Stage stage;
	private SpriteBatch batch;
	private TextButton backwardButton, saveButton, confirmButton;
	private Texture backgroundTexture, imageTexture;
	private TextField nameField, sizeField, levelField;

	private ImageButton[] mapMatrix, elementList;
	private Table mapTable, elementTable, inputTable;
	private SelectBox<Item> itemSelectBox;
	private SelectBox<Character> characterSelectBox;

	private Map map;

	public MapEditorScreen (Game game) {
		this.game = game;
	}

	@Override
	public void show() {

		stage = new Stage(new ScreenViewport());
		backgroundTexture = new Texture(Gdx.files.internal("android/assets/EditorBackground.jpg"));
		batch = new SpriteBatch();

		map = new Map();

		backwardButton = new TextButton("<--", MainMenu.buttonStyle);
		backwardButton.setWidth(Gdx.graphics.getWidth() / 20 );
		backwardButton.setHeight(Gdx.graphics.getHeight() / 15);

		backwardButton.setPosition(Gdx.graphics.getWidth() * 7 / 10 , (Gdx.graphics.getHeight() * 28 / 30 ) );
		backwardButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				game.setScreen(new MainMenu(game));
				return true;
			}
		});
		stage.addActor(backwardButton);

		mapTable = new Table();
		mapTable.setSize(Gdx.graphics.getWidth() * 2 / 3 , Gdx.graphics.getWidth() * 2 / 3);
		mapTable.setPosition(5, 5);

		stage.addActor(mapTable);

		inputTable = new Table();
		inputTable.setSize(Gdx.graphics.getWidth() / 4 , Gdx.graphics.getHeight() * 1 / 6);
		inputTable.setPosition( Gdx.graphics.getWidth() * 7 / 10 , Gdx.graphics.getHeight() * 3 / 4);
		inputTable.add(new Label("size (2 - 9)", MainMenu.style));
		sizeField = new TextField("", MainMenu.skin);
		inputTable.add(sizeField);
		inputTable.row();
		inputTable.add(new Label("level (1 - 9)", MainMenu.style));
		levelField = new TextField("", MainMenu.skin);
		inputTable.add(levelField);
		inputTable.row();
		inputTable.add(new Label("name", MainMenu.style));
		nameField = new TextField("", MainMenu.skin);
		inputTable.add(nameField);
		stage.addActor(inputTable);

		confirmButton = new TextButton("CONFIRM", MainMenu.buttonStyle);
		confirmButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				if (//sizeField.getText().matches("^[2-9]$") &&
						levelField.getText().matches("^[1-9]$") &&
						( ! nameField.getText().equals(""))) {
					mapMatrix = new ImageButton[Integer.parseInt(sizeField.getText()) * Integer.parseInt(sizeField.getText())];
					map.setSize(Integer.parseInt(sizeField.getText()));
					map.setLevel(Integer.parseInt(levelField.getText()));
					map.setName(nameField.getText());
					buildMapMatrix();
					//addMapMatrixListener();
					sizeField.setDisabled(true);
					levelField.setDisabled(true);
					nameField.setDisabled(true);
					confirmButton.setTouchable(Touchable.disabled);
				}
				return true;
			}
		});
		confirmButton.setWidth(Gdx.graphics.getWidth() / 9);
		confirmButton.setHeight(Gdx.graphics.getHeight() / 12);
		confirmButton.setPosition( Gdx.graphics.getWidth() * 7 / 10 , Gdx.graphics.getHeight() * 3 / 4 - confirmButton.getHeight() * 3 / 4);
		stage.addActor(confirmButton);

		elementTable = new Table();
		elementTable.setSize(Gdx.graphics.getWidth() / 4 , Gdx.graphics.getHeight() * 1 / 2);
		elementTable.setPosition( Gdx.graphics.getWidth() * 7 / 10 , Gdx.graphics.getHeight() * 1 / 7);

		elementList = new ImageButton[PublicParameter.mapPixelType];
		buildElementList();
		//addElementListListener();

		stage.addActor(elementTable);

		saveButton = new TextButton("SAVE", MainMenu.buttonStyle);
		saveButton.setWidth(Gdx.graphics.getWidth() / 9);
		saveButton.setHeight(Gdx.graphics.getHeight() / 12);
		saveButton.setPosition((Gdx.graphics.getWidth() * 8 / 10) - saveButton.getWidth() / 2, (Gdx.graphics.getHeight() / 15));
		saveButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				if (sizeField.getText().matches("^[1-9]$")) {

				}
				return true;
			}
		});
		stage.addActor(saveButton);
	}



	private void buildMapMatrix() {
		int size = map.getSize();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				switch (map.getLocationMatrix()[i][j]){
					case 1:
						mapMatrix[(i * size) + j] = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("android/assets/map/wall.png")))));
						break;
					case 2:
						mapMatrix[(i * size) + j] = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("android/assets/map/entryDoor.png")))));
						break;
					case 3:
						mapMatrix[(i * size) + j] = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("android/assets/map/exitDoor.png")))));
						break;
					default:
						mapMatrix[(i * size) + j] = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("android/assets/map/pave.png")))));
						break;
				}
				mapTable.add(mapMatrix[(i * size) + j]).fill();
			}
			mapTable.row();
		}
	}

	private void buildElementList() {

		for (int i = 0 ; i < elementList.length ; i++){
			switch (i){
				case 1:
					elementList[i] = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("android/assets/map/wall.png")))));
					break;
				case 2:
					elementList[i] = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("android/assets/map/entryDoor.png")))));
					break;
				case 3:
					elementList[i] = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("android/assets/map/exitDoor.png")))));
					break;
				default:
					elementList[i] = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("android/assets/map/pave.png")))));
					break;
			}
			elementTable.add(elementList[i]).fill().expandX();
		}
		elementTable.row();
		elementTable.add(new Label("Character ",MainMenu.style));
		characterSelectBox = new SelectBox<Character>(MainMenu.skin);
		characterSelectBox.setDisabled(true);
		elementTable.add(characterSelectBox).colspan(3);
		elementTable.row();
		elementTable.add(new Label("Item", MainMenu.style));
		itemSelectBox = new SelectBox<Item>(MainMenu.skin);
		itemSelectBox.setDisabled(true);
		elementTable.add(itemSelectBox).colspan(3);

	}

	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		Gdx.input.setInputProcessor(stage);
		stage.act();

		batch.begin();

		stage.getBatch().begin();
		stage.getBatch().draw(backgroundTexture, 0, 0,Gdx.graphics.getWidth() , Gdx.graphics.getHeight());
		stage.getBatch().end();

		stage.draw();

		stage.setDebugAll(true);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {
		stage.dispose();
	}

//
//
//	private void addBackpackMatrixListener() {
//		for (int i = 0; i < character.getBackpack().size() ; i++) {
//			backpackMatrix[i].addListener(new ClickListener(i) {
//				@Override
//				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//					if (character.getBackpack().get(button).getLevel() == character.getLevel()
//							&& !character.getEquipment().containsKey(character.getBackpack().get(getButton()).getItemType())) {
//						Item itemtmp = character.getBackpack().remove(getButton());
//						character.loadEquipment(itemtmp);
//						equipmentTable.clearChildren();
//						buildEquipmentMatrix();
//						addEquipmentMatrixListener();
//						backpackTable.clearChildren();
//						buildBackpackMatrix();
//						addBackpackMatrixListener();
//					}
//					return true;
//				}
//
//				public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
//					backpackItemInfoLabel.setText(character.getBackpack().get(getButton()).toString());
//				}
//
//				@Override
//				public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
//					backpackItemInfoLabel.setText("");
//				}
//			});
//		}
//	}
//
//
//	private void addEquipmentMatrixListener() {
//		for (int i = 0; i < equipmentMatrix.length ; i++){
//			equipmentMatrix[i].addListener(new ClickListener(i) {
//				@Override
//				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//					character.getBackpack().add(character.removeEquipment(Item.ItemType.getItemType(getButton())));
//					equipmentTable.clearChildren();
//					buildEquipmentMatrix();
//					addEquipmentMatrixListener();
//					backpackTable.clearChildren();
//					buildBackpackMatrix();
//					addBackpackMatrixListener();
//					return true;
//				}
//
//				@Override
//				public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
//					if(character.getEquipment().get(Item.ItemType.getItemType(getButton())) != null){
//						equipmentItemInfoLabel.setText(character.getEquipment().get(Item.ItemType.getItemType(getButton())).toString());
//					}
//				}
//
//				@Override
//				public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
//					equipmentItemInfoLabel.setText("");
//				}
//			});
//		}
//	}

}
