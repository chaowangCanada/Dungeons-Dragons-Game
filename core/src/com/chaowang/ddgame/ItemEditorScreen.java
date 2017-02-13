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

import Items.Item;
import Items.ItemInventory;
import sun.applet.Main;

public class ItemEditorScreen implements Screen {
    private Game game;
    private Stage stage;
    private SpriteBatch batch;
	private Texture backgroundTexture;
	private Table editorTable, inventoryTable;
	private TextButton itemLeftButton, itemRightButton, abilityLeftButton, abilityRightButton, itemSaveButton, mainPageButton;
	private Item item;
	private Label itemLabel, abilityLabel, itemInfoLabel;
	private TextField nameText, levelText;
	private Image itemImage;


	private ImageButton[] inventoryMatrix;

	public ItemEditorScreen(Game game) {
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


		item = new Item();
		nameText = new TextField(item.getName(), MainMenu.skin);
		levelText = new TextField("Integer 1-5", MainMenu.skin);
		itemLabel = new Label(item.getItemType().toString(), MainMenu.style);
		abilityLabel = new Label(item.getEnchantedAbility().toString(), MainMenu.style);
		itemImage = new Image(item.getTexture());

		itemLeftButton = new TextButton("<", MainMenu.buttonStyle);
		itemLeftButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				item.previousItem();
				itemLabel.setText(item.getItemType().toString());
				abilityLabel.setText(item.getEnchantedAbility().toString());
				itemImage.setDrawable(new SpriteDrawable(new Sprite(item.getTexture())));
				return true;
			}
		});
		itemRightButton = new TextButton(">", MainMenu.buttonStyle);
		itemRightButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				item.nextItem();
				itemLabel.setText(item.getItemType().toString());
				abilityLabel.setText(item.getEnchantedAbility().toString());
				itemImage.setDrawable(new SpriteDrawable(new Sprite(item.getTexture())));
				return true;
			}
		});

		abilityLeftButton = new TextButton("<", MainMenu.buttonStyle);
		abilityLeftButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				item.previousAbility();
				abilityLabel.setText(item.getEnchantedAbility().toString());
				return true;
			}
		});

		abilityRightButton = new TextButton(">", MainMenu.buttonStyle);
		abilityRightButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				item.nextAbility();
				abilityLabel.setText(item.getEnchantedAbility().toString());
				return true;
			}
		});

		editorTable = new Table();
		editorTable.setSize(Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() / 2);
		editorTable.setPosition(Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() * 1 / 4);
		//editorTable.setBounds(Gdx.graphics.getWidth() / 48, 0, Gdx.graphics.getWidth() - ( Gdx.graphics.getWidth() / 24), Gdx.graphics.getHeight());

		editorTable.add(new Label("", MainMenu.style)).width(80);
		editorTable.add(itemImage).maxSize(200, 200).center();
		editorTable.row();
		editorTable.add(new Label("Name", MainMenu.style));
		editorTable.add(nameText);
		editorTable.row();
		editorTable.add(new Label("Level", MainMenu.style));
		editorTable.add(levelText);
		editorTable.row();
		editorTable.add(itemLeftButton).size(50, 50);
		editorTable.add(itemLabel).center();
		editorTable.add(itemRightButton).size(50, 50).expandX();
		editorTable.row();
		editorTable.add(abilityLeftButton).size(50, 50);
		editorTable.add(abilityLabel).center();
		editorTable.add(abilityRightButton).size(50, 50).expandX();

		stage.addActor(editorTable);

		itemSaveButton = new TextButton("SAVE", MainMenu.buttonStyle);
		itemSaveButton.setWidth(Gdx.graphics.getWidth() / 9);
		itemSaveButton.setHeight(Gdx.graphics.getHeight() / 9);
		itemSaveButton.setPosition((Gdx.graphics.getWidth() * 1 / 4) - itemSaveButton.getWidth() / 2, (Gdx.graphics.getHeight() / 8));
		itemSaveButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				if (levelText.getText().matches("^[1-5]$")) {
					item.setLevel(Integer.parseInt(levelText.getText()));
					item.setName(nameText.getText());
					MainMenu.itemInventory.addToInventory(item);
					MainMenu.itemInventory.saveToFile();
					inventoryTable.clearChildren();
					buildInventoryMatrix();
					addInventoryMatrixListener();
					item = new Item();
					initialEditorItem();
				}
				return true;
			}
		});
		stage.addActor(itemSaveButton);

		// Right hand side
		itemInfoLabel = new Label("", MainMenu.style);
		itemInfoLabel.setPosition((Gdx.graphics.getWidth() * 1 / 2), (Gdx.graphics.getHeight() / 8));
		stage.addActor(itemInfoLabel);

		inventoryTable = new Table();

		inventoryTable.setSize(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() * 4 / 5);
		inventoryTable.setPosition(Gdx.graphics.getWidth() / 2 - 20, Gdx.graphics.getHeight() * 1 / 10);
		//inventoryTable.setBounds(Gdx.graphics.getWidth() / 48, 0, Gdx.graphics.getWidth() - ( Gdx.graphics.getWidth() / 24), Gdx.graphics.getHeight());

		inventoryMatrix = new ImageButton[PublicParameter.itemInventoryRow * PublicParameter.itemInventoryColumn];
		buildInventoryMatrix();
		addInventoryMatrixListener();

		stage.addActor(inventoryTable);
	}

	private void initialEditorItem() {
		itemLabel.setText(item.getItemType().toString());
		abilityLabel.setText(item.getEnchantedAbility().toString());
		itemImage.setDrawable(new SpriteDrawable(new Sprite(item.getTexture())));
		levelText.setText(Integer.toString(item.getLevel()));
		nameText.setText(item.getName());
	}

	private void buildInventoryMatrix() {
		for (int i = 0; i < PublicParameter.itemInventoryRow; i++) {
			for (int j = 0; j < PublicParameter.itemInventoryColumn; j++) {
				if ((i * PublicParameter.itemInventoryColumn) + j < MainMenu.itemInventory.getItemPack().size() ) {
					inventoryMatrix[(i * PublicParameter.itemInventoryColumn) + j] = new ImageButton(new TextureRegionDrawable(new TextureRegion(MainMenu.itemInventory.getItemPack().get(i * PublicParameter.itemInventoryColumn + j).getTexture())));
				} else {
					inventoryMatrix[(i * PublicParameter.itemInventoryColumn) + j] = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("android/assets/items/unknown.png")))));
				}
				ImageButton tempButton = inventoryMatrix[(i * PublicParameter.itemInventoryColumn) + j];
				inventoryTable.add(tempButton).width(PublicParameter.itemCellWidth).height(PublicParameter.itemCellHeight).space(15);
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

		batch.enableBlending();
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
		for (int i = 0; i < MainMenu.itemInventory.getItemPack().size() ; i++){
			if(i == 0 && i < MainMenu.itemInventory.getItemPack().size() ){
				inventoryMatrix[i].addListener(new ClickListener() {
					@Override
					public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
						item = new Item(MainMenu.itemInventory.getItemPack().get(0).getItemType(),
								MainMenu.itemInventory.getItemPack().get(0).getName(),
								MainMenu.itemInventory.getItemPack().get(0).getLevel(),
								MainMenu.itemInventory.getItemPack().get(0).getEnchantedAbility());
						initialEditorItem();
						MainMenu.itemInventory.getItemPack().remove(0);
						MainMenu.itemInventory.saveToFile();
						inventoryTable.clearChildren();
						buildInventoryMatrix();
						addInventoryMatrixListener();
						return true;
					}

					@Override
					public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
						itemInfoLabel.setText(MainMenu.itemInventory.getItemPack().get(0).toString());
					}

					@Override
					public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
						itemInfoLabel.setText("");
					}
				});
			}
			if(i == 1 && i < MainMenu.itemInventory.getItemPack().size() ){
				inventoryMatrix[i].addListener(new ClickListener() {
					@Override
					public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
						item = new Item(MainMenu.itemInventory.getItemPack().get(1).getItemType(),
								MainMenu.itemInventory.getItemPack().get(1).getName(),
								MainMenu.itemInventory.getItemPack().get(1).getLevel(),
								MainMenu.itemInventory.getItemPack().get(1).getEnchantedAbility());
						initialEditorItem();
						MainMenu.itemInventory.getItemPack().remove(1);
						MainMenu.itemInventory.saveToFile();
						inventoryTable.clearChildren();
						buildInventoryMatrix();
						addInventoryMatrixListener();
						return true;
					}

					@Override
					public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
						itemInfoLabel.setText(MainMenu.itemInventory.getItemPack().get(1).toString());
					}

					@Override
					public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
						itemInfoLabel.setText("");
					}
				});
			}
			if(i == 2 && i < MainMenu.itemInventory.getItemPack().size() ){
				inventoryMatrix[i].addListener(new ClickListener() {
					@Override
					public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
						item = new Item(MainMenu.itemInventory.getItemPack().get(2).getItemType(),
								MainMenu.itemInventory.getItemPack().get(2).getName(),
								MainMenu.itemInventory.getItemPack().get(2).getLevel(),
								MainMenu.itemInventory.getItemPack().get(2).getEnchantedAbility());
						initialEditorItem();
						MainMenu.itemInventory.getItemPack().remove(2);
						MainMenu.itemInventory.saveToFile();
						inventoryTable.clearChildren();
						buildInventoryMatrix();
						addInventoryMatrixListener();
						return true;
					}

					@Override
					public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
						itemInfoLabel.setText(MainMenu.itemInventory.getItemPack().get(2).toString());
					}

					@Override
					public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
						itemInfoLabel.setText("");
					}
				});
			}
			if(i == 3 && i < MainMenu.itemInventory.getItemPack().size() ){
				inventoryMatrix[i].addListener(new ClickListener() {
					@Override
					public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
						item = new Item(MainMenu.itemInventory.getItemPack().get(3).getItemType(),
								MainMenu.itemInventory.getItemPack().get(3).getName(),
								MainMenu.itemInventory.getItemPack().get(3).getLevel(),
								MainMenu.itemInventory.getItemPack().get(3).getEnchantedAbility());
						initialEditorItem();
						MainMenu.itemInventory.getItemPack().remove(3);
						MainMenu.itemInventory.saveToFile();
						inventoryTable.clearChildren();
						buildInventoryMatrix();
						addInventoryMatrixListener();
						return true;
					}

					@Override
					public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
						itemInfoLabel.setText(MainMenu.itemInventory.getItemPack().get(3).toString());
					}

					@Override
					public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
						itemInfoLabel.setText("");
					}
				});
			}
			if(i == 4 && i < MainMenu.itemInventory.getItemPack().size() ){
				inventoryMatrix[i].addListener(new ClickListener() {
					@Override
					public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
						item = new Item(MainMenu.itemInventory.getItemPack().get(4).getItemType(),
								MainMenu.itemInventory.getItemPack().get(4).getName(),
								MainMenu.itemInventory.getItemPack().get(4).getLevel(),
								MainMenu.itemInventory.getItemPack().get(4).getEnchantedAbility());
						initialEditorItem();
						MainMenu.itemInventory.getItemPack().remove(4);
						MainMenu.itemInventory.saveToFile();
						inventoryTable.clearChildren();
						buildInventoryMatrix();
						addInventoryMatrixListener();
						return true;
					}

					@Override
					public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
						itemInfoLabel.setText(MainMenu.itemInventory.getItemPack().get(4).toString());
					}

					@Override
					public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
						itemInfoLabel.setText("");
					}
				});
			}
			if(i == 5 && i < MainMenu.itemInventory.getItemPack().size() ){
				inventoryMatrix[i].addListener(new ClickListener() {
					@Override
					public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
						item = new Item(MainMenu.itemInventory.getItemPack().get(5).getItemType(),
								MainMenu.itemInventory.getItemPack().get(5).getName(),
								MainMenu.itemInventory.getItemPack().get(5).getLevel(),
								MainMenu.itemInventory.getItemPack().get(5).getEnchantedAbility());
						initialEditorItem();
						MainMenu.itemInventory.getItemPack().remove(5);
						MainMenu.itemInventory.saveToFile();
						inventoryTable.clearChildren();
						buildInventoryMatrix();
						addInventoryMatrixListener();
						return true;
					}

					@Override
					public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
						itemInfoLabel.setText(MainMenu.itemInventory.getItemPack().get(5).toString());
					}

					@Override
					public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
						itemInfoLabel.setText("");
					}
				});
			}
		}
	}


}
