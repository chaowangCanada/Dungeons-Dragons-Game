package com.chaowang.ddgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import Character.Character;
import Items.Item;


public class CharacterEditorScreen implements Screen {
	final int INVENTORYROW = 5, INVENTORYCOLUMN = 5;
	final int cellWidth = Gdx.graphics.getHeight() * 3 / 5 / INVENTORYROW;
	final int cellHeight = Gdx.graphics.getHeight() * 3 / 5 / INVENTORYCOLUMN;
	private Game game;
	private Stage stage;
	private SpriteBatch batch;
	private Texture backgroundTexture;
	private Table editorTable, inventoryTable;
	private TextButton raceLeftButton, raceRightButton, characterSaveButton, mainPageButton, equipmentPageButton;
	private Character character;
	private Label classLabel, raceLabel;
	private Label hitpointLabel, attackBonusLabel, damageBonusLaber, armorClassLabel,
			strengthLabel, dexterityLabel, constitutionLabel, wisdomLabel, intellegenceLabel, chrismaLabel, characterInfoLabel;
	private TextField nameText, levelText;
	private Image characterImage, diceImage;


	private ImageButton[] inventoryMatrix;

	public CharacterEditorScreen(Game game) {
		this.game = game;
	}

	@Override
	public void show() {

		stage = new Stage(new ScreenViewport());
		backgroundTexture = new Texture(Gdx.files.internal("android/assets/EditorBackground.jpg"));
		batch = new SpriteBatch();

		mainPageButton = new TextButton("<--", MainMenu.buttonStyle);
		mainPageButton.setWidth(Gdx.graphics.getWidth() / 20 );
		mainPageButton.setHeight(Gdx.graphics.getHeight() / 10);
		mainPageButton.setPosition((Gdx.graphics.getWidth() * 1 /30 ) , (Gdx.graphics.getHeight() * 9 / 10 ) );
		mainPageButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				stage.clear();
				game.setScreen(new MainMenu(game));
				return true;
			}
		});
		stage.addActor(mainPageButton);


		character = new Character();
		nameText = new TextField(character.getName(), MainMenu.skin);
		levelText = new TextField("Integer 1-9", MainMenu.skin);
		raceLabel = new Label(character.getRaceType().toString(), MainMenu.style);
		characterImage = new Image(character.getTexture());

		raceLeftButton = new TextButton("<", MainMenu.buttonStyle);
		raceLeftButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				character.previousRace();
				raceLabel.setText(character.getRaceType().toString());
				characterImage.setDrawable(new SpriteDrawable(new Sprite(character.getTexture())));
				return true;
			}
		});

		raceRightButton = new TextButton(">", MainMenu.buttonStyle);
		raceRightButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				character.nextRace();
				raceLabel.setText(character.getRaceType().toString());
				characterImage.setDrawable(new SpriteDrawable(new Sprite(character.getTexture())));
				return true;
			}
		});

		classLabel = new Label("Fighter",MainMenu.style);
		strengthLabel = new Label("",MainMenu.style);
		dexterityLabel = new Label("",MainMenu.style);
		constitutionLabel = new Label("",MainMenu.style);
		wisdomLabel = new Label("",MainMenu.style);
		intellegenceLabel = new Label("",MainMenu.style);
		chrismaLabel = new Label("",MainMenu.style);
		hitpointLabel = new Label("",MainMenu.style);
		attackBonusLabel  = new Label("",MainMenu.style);
		damageBonusLaber  = new Label("",MainMenu.style);
		armorClassLabel  = new Label("",MainMenu.style);


		editorTable = new Table();
		editorTable.setDebug(true);

		editorTable.setSize(Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() );
		editorTable.setPosition(Gdx.graphics.getWidth() / 10, 2);


		editorTable.add(new Label("", MainMenu.style)).width(80);
		editorTable.add(characterImage).maxSize(250, 250).center();
		editorTable.row();
		editorTable.add(new Label("Name", MainMenu.style));
		editorTable.add(nameText);
		editorTable.row();
		editorTable.add(new Label("Level", MainMenu.style));
		editorTable.add(levelText);
		editorTable.row();
		editorTable.add(raceLeftButton).size(50, 30);
		editorTable.add(raceLabel).center();
		editorTable.add(raceRightButton).size(50, 30).expandX();
		editorTable.row();
		editorTable.add(new Label("class", MainMenu.style));
		editorTable.add(classLabel);
		editorTable.row();
		editorTable.add(new Label("strength", MainMenu.style));
		editorTable.add(strengthLabel);
		editorTable.row();
		editorTable.add(new Label("dexterity", MainMenu.style));
		editorTable.add(dexterityLabel);
		editorTable.row();
		editorTable.add(new Label("constitution", MainMenu.style));
		editorTable.add(constitutionLabel);
		editorTable.row();
		editorTable.add(new Label("wisdom", MainMenu.style));
		editorTable.add(wisdomLabel);
		editorTable.row();
		editorTable.add(new Label("intelligence", MainMenu.style));
		editorTable.add(intellegenceLabel);
		editorTable.row();
		editorTable.add(new Label("charisma", MainMenu.style));
		editorTable.add(chrismaLabel);
		editorTable.row();
		editorTable.add(new Label("hit point", MainMenu.style));
		editorTable.add(hitpointLabel);
		editorTable.row();
		editorTable.add(new Label("attach bonus", MainMenu.style));
		editorTable.add(attackBonusLabel);
		editorTable.row();
		editorTable.add(new Label("damage bonus", MainMenu.style));
		editorTable.add(damageBonusLaber);
		editorTable.row();
		editorTable.add(new Label("armor class", MainMenu.style));
		editorTable.add(armorClassLabel);
		editorTable.row();

		stage.addActor(editorTable);

		characterSaveButton = new TextButton("SAVE", MainMenu.buttonStyle);
		characterSaveButton.setWidth(Gdx.graphics.getWidth() / 9);
		characterSaveButton.setHeight(Gdx.graphics.getHeight() / 9);
		characterSaveButton.setPosition((Gdx.graphics.getWidth() * 1 / 2) , (Gdx.graphics.getHeight() / 20));
		characterSaveButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				if (levelText.getText().matches("^[1-9]$")) {
					character.setLevel(Integer.parseInt(levelText.getText()));
					character.setName(nameText.getText());
					MainMenu.characterInventory.addToInventory(character);
					MainMenu.characterInventory.saveToFile();
					inventoryTable.clearChildren();
					buildInventoryMatrix();
					addInventoryMatrixListener();
					character = new Character();
					initialEditorItem();
				}
				return true;
			}
		});
		stage.addActor(characterSaveButton);

		// Right hand side
		characterInfoLabel = new Label("", MainMenu.style);
		characterInfoLabel.setPosition((Gdx.graphics.getWidth() * 3 / 7), (Gdx.graphics.getHeight() * 1 / 5));
		stage.addActor(characterInfoLabel);

		inventoryTable = new Table();
		inventoryTable.setDebug(true);

		inventoryTable.setSize(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() * 4 / 5);
		inventoryTable.setPosition(Gdx.graphics.getWidth() / 2 - 20, Gdx.graphics.getHeight() * 1 / 6);
		//inventoryTable.setBounds(Gdx.graphics.getWidth() / 48, 0, Gdx.graphics.getWidth() - ( Gdx.graphics.getWidth() / 24), Gdx.graphics.getHeight());

		inventoryMatrix = new ImageButton[INVENTORYROW * INVENTORYCOLUMN];
		buildInventoryMatrix();
		addInventoryMatrixListener();

		stage.addActor(inventoryTable);
	}

	private void initialEditorItem() {
		raceLabel.setText(character.getRaceType().toString());
		characterImage.setDrawable(new SpriteDrawable(new Sprite(character.getTexture())));
		levelText.setText(Integer.toString(character.getLevel()));
		nameText.setText(character.getName());
	}

	private void buildInventoryMatrix() {
		for (int i = 0; i < INVENTORYROW; i++) {
			for (int j = 0; j < INVENTORYCOLUMN; j++) {
				if ((i * INVENTORYROW) + j < MainMenu.characterInventory.getChatacterPack().size() ) {
					inventoryMatrix[(i * INVENTORYROW) + j] = new ImageButton(new TextureRegionDrawable(new TextureRegion(MainMenu.characterInventory.getChatacterPack().get(i * INVENTORYROW + j).getTexture())));
				} else {
					inventoryMatrix[(i * INVENTORYROW) + j] = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("android/assets/items/unknown.jpg")))));
				}
				ImageButton tempButton = inventoryMatrix[(i * INVENTORYROW) + j];
				inventoryTable.add(tempButton).width(cellWidth).height(cellHeight).space(15);
			}
			inventoryTable.row();
		}
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		stage.dispose();

	}


	private void addInventoryMatrixListener() {
		for (int i = 0; i < MainMenu.characterInventory.getChatacterPack().size() ; i++){
			if(i == 0 && i < MainMenu.characterInventory.getChatacterPack().size() ){
				inventoryMatrix[i].addListener(new ClickListener() {
					@Override
					public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
						character = new Character(
								MainMenu.characterInventory.getChatacterPack().get(0).getName(),
								MainMenu.characterInventory.getChatacterPack().get(0).getLevel(),
								MainMenu.characterInventory.getChatacterPack().get(0).getRaceType());
						initialEditorItem();
						MainMenu.characterInventory.getChatacterPack().remove(0);
						MainMenu.characterInventory.saveToFile();
						inventoryTable.clearChildren();
						buildInventoryMatrix();
						addInventoryMatrixListener();
						return true;
					}

					@Override
					public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
						characterInfoLabel.setText(MainMenu.characterInventory.getChatacterPack().get(0).toString());
					}

					@Override
					public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
						characterInfoLabel.setText("");
					}
				});
			}
			if(i == 1 && i <  MainMenu.characterInventory.getChatacterPack().size() ){
				inventoryMatrix[i].addListener(new ClickListener() {
					@Override
					public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
						character = new Character(
								MainMenu.characterInventory.getChatacterPack().get(1).getName(),
								MainMenu.characterInventory.getChatacterPack().get(1).getLevel(),
								MainMenu.characterInventory.getChatacterPack().get(1).getRaceType());
						initialEditorItem();
						MainMenu.characterInventory.getChatacterPack().remove(1);
						MainMenu.characterInventory.saveToFile();
						inventoryTable.clearChildren();
						buildInventoryMatrix();
						addInventoryMatrixListener();
						return true;
					}

					@Override
					public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
						characterInfoLabel.setText(MainMenu.characterInventory.getChatacterPack().get(1).toString());
					}

					@Override
					public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
						characterInfoLabel.setText("");
					}
				});
			}
			if(i == 2 && i <  MainMenu.characterInventory.getChatacterPack().size() ){
				inventoryMatrix[i].addListener(new ClickListener() {
					@Override
					public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
						character = new Character(
								MainMenu.characterInventory.getChatacterPack().get(2).getName(),
								MainMenu.characterInventory.getChatacterPack().get(2).getLevel(),
								MainMenu.characterInventory.getChatacterPack().get(2).getRaceType());
						initialEditorItem();
						MainMenu.characterInventory.getChatacterPack().remove(2);
						MainMenu.characterInventory.saveToFile();
						inventoryTable.clearChildren();
						buildInventoryMatrix();
						addInventoryMatrixListener();
						return true;
					}

					@Override
					public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
						characterInfoLabel.setText(MainMenu.characterInventory.getChatacterPack().get(2).toString());
					}

					@Override
					public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
						characterInfoLabel.setText("");
					}
				});
			}
			if(i == 3 && i <  MainMenu.characterInventory.getChatacterPack().size() ){
				inventoryMatrix[i].addListener(new ClickListener() {
					@Override
					public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
						character = new Character(
								MainMenu.characterInventory.getChatacterPack().get(3).getName(),
								MainMenu.characterInventory.getChatacterPack().get(3).getLevel(),
								MainMenu.characterInventory.getChatacterPack().get(3).getRaceType());
						initialEditorItem();
						MainMenu.characterInventory.getChatacterPack().remove(3);
						MainMenu.characterInventory.saveToFile();
						inventoryTable.clearChildren();
						buildInventoryMatrix();
						addInventoryMatrixListener();
						return true;
					}

					@Override
					public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
						characterInfoLabel.setText(MainMenu.characterInventory.getChatacterPack().get(3).toString());
					}

					@Override
					public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
						characterInfoLabel.setText("");
					}
				});
			}
			if(i == 4 && i <  MainMenu.characterInventory.getChatacterPack().size() ){
				inventoryMatrix[i].addListener(new ClickListener() {
					@Override
					public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
						character = new Character(
								MainMenu.characterInventory.getChatacterPack().get(4).getName(),
								MainMenu.characterInventory.getChatacterPack().get(4).getLevel(),
								MainMenu.characterInventory.getChatacterPack().get(4).getRaceType());
						initialEditorItem();
						MainMenu.characterInventory.getChatacterPack().remove(4);
						MainMenu.characterInventory.saveToFile();
						inventoryTable.clearChildren();
						buildInventoryMatrix();
						addInventoryMatrixListener();
						return true;
					}

					@Override
					public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
						characterInfoLabel.setText(MainMenu.characterInventory.getChatacterPack().get(4).toString());
					}

					@Override
					public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
						characterInfoLabel.setText("");
					}
				});
			}
			if(i == 5 && i <  MainMenu.characterInventory.getChatacterPack().size() ){
				inventoryMatrix[i].addListener(new ClickListener() {
					@Override
					public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
						character = new Character(
								MainMenu.characterInventory.getChatacterPack().get(5).getName(),
								MainMenu.characterInventory.getChatacterPack().get(5).getLevel(),
								MainMenu.characterInventory.getChatacterPack().get(5).getRaceType());
						initialEditorItem();
						MainMenu.characterInventory.getChatacterPack().remove(5);
						MainMenu.characterInventory.saveToFile();
						inventoryTable.clearChildren();
						buildInventoryMatrix();
						addInventoryMatrixListener();
						return true;
					}

					@Override
					public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
						characterInfoLabel.setText(MainMenu.characterInventory.getChatacterPack().get(5).toString());
					}

					@Override
					public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
						characterInfoLabel.setText("");
					}
				});
			}
		}
	}


}
