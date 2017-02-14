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
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import Items.Item;
import Character.Character;
/**
 * Created by Chao on 13/02/2017.
 */

public class EquipmentEditorScreen implements Screen{

    private Game game;
    private Stage stage;
    private SpriteBatch batch;
    private TextButton backwardButton, saveButton;
    private Texture backgroundTexture;

    private ImageButton[] backpackMatrix, equipmentMatrix;
    private Table backpackTable, equipmentTable;
    private Character character;
    private Label equipmentItemInfoLabel, backpackItemInfoLabel;


    public EquipmentEditorScreen (Game game, Character character) {
        this.game = game;
        this.character = character;
    }

    @Override
    public void show() {

        stage = new Stage(new ScreenViewport());
        backgroundTexture = new Texture(Gdx.files.internal("android/assets/EditorBackground.jpg"));
        batch = new SpriteBatch();

        backwardButton = new TextButton("<--", MainMenu.buttonStyle);
        backwardButton.setWidth(Gdx.graphics.getWidth() / 20 );
        backwardButton.setHeight(Gdx.graphics.getHeight() / 10);
        backwardButton.setPosition((Gdx.graphics.getWidth() * 1 /30 ) , (Gdx.graphics.getHeight() * 9 / 10 ) );
        backwardButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new CharacterEditorScreen(game, character));
                return true;
            }
        });
        stage.addActor(backwardButton);

        backpackItemInfoLabel = new Label("", MainMenu.style);
        backpackItemInfoLabel.setPosition((Gdx.graphics.getWidth() * 1 / 2), (Gdx.graphics.getHeight() / 8));
        stage.addActor(backpackItemInfoLabel);

        equipmentItemInfoLabel = new Label("", MainMenu.style);
        equipmentItemInfoLabel.setPosition((Gdx.graphics.getWidth() * 1 / 20), (Gdx.graphics.getHeight() / 8));
        stage.addActor(equipmentItemInfoLabel);

        backpackTable = new Table();
        backpackTable.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("android/assets/backpackBackground.png")))));
        backpackTable.setSize(Gdx.graphics.getWidth() / 2 , Gdx.graphics.getHeight() * 1 / 3);
        backpackTable.setPosition(Gdx.graphics.getWidth() * 3 / 7, Gdx.graphics.getHeight() * 1 / 3);

        backpackMatrix = new ImageButton[PublicParameter.itemBackpackRow * PublicParameter.itemBackpackColumn];
        buildBackpackMatrix();
        addBackpackMatrixListener();

        stage.addActor(backpackTable);


        equipmentTable = new Table();

        equipmentTable.setSize(Gdx.graphics.getWidth() / 4 , Gdx.graphics.getHeight() * 1 / 2);
        equipmentTable.setPosition( Gdx.graphics.getWidth() / 20 , Gdx.graphics.getHeight() * 1 / 3);

        equipmentMatrix = new ImageButton[7];
        buildEquipmentMatrix();
        addEquipmentMatrixListener();

        stage.addActor(equipmentTable);

    }



    private void buildBackpackMatrix() {
        for (int i = 0; i < PublicParameter.itemBackpackRow; i++) {
            for (int j = 0; j < PublicParameter.itemBackpackColumn; j++) {
                if ((i * PublicParameter.itemBackpackColumn) + j < character.getBackpack().size()) {
                    backpackMatrix[(i * PublicParameter.itemBackpackColumn) + j] = new ImageButton(new TextureRegionDrawable(new TextureRegion(character.getBackpack().get(i * PublicParameter.itemBackpackColumn + j).getTexture())));
                } else {
                    backpackMatrix[(i * PublicParameter.itemBackpackColumn) + j] = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("android/assets/items/unknown.png")))));
                }
                ImageButton tempButton = backpackMatrix[(i * PublicParameter.itemBackpackColumn) + j];
                backpackTable.add(tempButton).width(PublicParameter.itemCellWidth).height(PublicParameter.itemCellHeight).space(25);
            }
            backpackTable.row();
        }
    }

    private void buildEquipmentMatrix() {

        equipmentTable.add(new Label("", MainMenu.style)).width(80);
        if(character.getEquipment().get(Item.ItemType.HELMET) != null){
            equipmentMatrix[0] = new ImageButton(new TextureRegionDrawable(new TextureRegion(character.getEquipment().get(Item.ItemType.HELMET).getTexture())));
        }else{
            equipmentMatrix[0] = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("android/assets/items/unknown.png")))));
        }
        equipmentTable.add(equipmentMatrix[0]).width(PublicParameter.itemCellWidth).height(PublicParameter.itemCellHeight);
        equipmentTable.add(new Label("", MainMenu.style)).width(80);
        equipmentTable.row();

        equipmentTable.add(new Label("", MainMenu.style)).width(80);
        if(character.getEquipment().get(Item.ItemType.ARMOR) != null){
            equipmentMatrix[1] = new ImageButton(new TextureRegionDrawable(new TextureRegion(character.getEquipment().get(Item.ItemType.ARMOR).getTexture())));
        }else{
            equipmentMatrix[1] = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("android/assets/items/unknown.png")))));
        }
        equipmentTable.add(equipmentMatrix[1]).width(PublicParameter.itemCellWidth).height(PublicParameter.itemCellHeight);
        equipmentTable.add(new Label("", MainMenu.style)).width(80);
        equipmentTable.row();

        if(character.getEquipment().get(Item.ItemType.WEAPON) != null){
            equipmentMatrix[2] = new ImageButton(new TextureRegionDrawable(new TextureRegion(character.getEquipment().get(Item.ItemType.WEAPON).getTexture())));
        }else{
            equipmentMatrix[2] = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("android/assets/items/unknown.png")))));
        }
        equipmentTable.add(equipmentMatrix[2]).width(PublicParameter.itemCellWidth).height(PublicParameter.itemCellHeight);
        if(character.getEquipment().get(Item.ItemType.BELT) != null){
            equipmentMatrix[3] = new ImageButton(new TextureRegionDrawable(new TextureRegion(character.getEquipment().get(Item.ItemType.BELT).getTexture())));
        }else{
            equipmentMatrix[3] = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("android/assets/items/unknown.png")))));
        }
        equipmentTable.add(equipmentMatrix[3]).width(PublicParameter.itemCellWidth).height(PublicParameter.itemCellHeight);
        if(character.getEquipment().get(Item.ItemType.SHIELD) != null){
            equipmentMatrix[4] = new ImageButton(new TextureRegionDrawable(new TextureRegion(character.getEquipment().get(Item.ItemType.SHIELD).getTexture())));
        }else{
            equipmentMatrix[4] = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("android/assets/items/unknown.png")))));
        }
        equipmentTable.add(equipmentMatrix[4]).width(PublicParameter.itemCellWidth).height(PublicParameter.itemCellHeight);
        equipmentTable.row();

        equipmentTable.add(new Label("", MainMenu.style)).width(80);
        if(character.getEquipment().get(Item.ItemType.BOOTS) != null){
            equipmentMatrix[5] = new ImageButton(new TextureRegionDrawable(new TextureRegion(character.getEquipment().get(Item.ItemType.BOOTS).getTexture())));
        }else{
            equipmentMatrix[5] = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("android/assets/items/unknown.png")))));
        }
        equipmentTable.add(equipmentMatrix[5]).width(PublicParameter.itemCellWidth).height(PublicParameter.itemCellHeight);
        if(character.getEquipment().get(Item.ItemType.RING) != null){
            equipmentMatrix[6] = new ImageButton(new TextureRegionDrawable(new TextureRegion(character.getEquipment().get(Item.ItemType.RING).getTexture())));
        }else{
            equipmentMatrix[6] = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("android/assets/items/unknown.png")))));
        }
        equipmentTable.add(equipmentMatrix[6]).width(PublicParameter.itemCellWidth).height(PublicParameter.itemCellHeight);

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

        //stage.setDebugAll(true);
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



    private void addBackpackMatrixListener() {
        for (int i = 0; i < character.getBackpack().size() ; i++){
            if(i == 0 && i < character.getBackpack().size() ){
                backpackMatrix[i].addListener(new ClickListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        if(character.getBackpack().get(0).getLevel() == character.getLevel()
                                && ! character.getEquipment().containsKey(character.getBackpack().get(0).getItemType()) ) {
                            Item itemtmp = character.getBackpack().remove(0);
                            character.getEquipment().put(itemtmp.getItemType(),itemtmp);
                            equipmentTable.clearChildren();
                            buildEquipmentMatrix();
                            addEquipmentMatrixListener();
                            backpackTable.clearChildren();
                            buildBackpackMatrix();
                            addBackpackMatrixListener();
                        }
                        return true;
                    }

                    @Override
                    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                        backpackItemInfoLabel.setText(character.getBackpack().get(0).toString());
                    }

                    @Override
                    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                        backpackItemInfoLabel.setText("");
                    }
                });
            }
            if(i == 1 && i < character.getBackpack().size() ){
                backpackMatrix[i].addListener(new ClickListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        if(character.getBackpack().get(1).getLevel() == character.getLevel()
                                && ! character.getEquipment().containsKey(character.getBackpack().get(1).getItemType()) ) {
                            Item itemtmp = character.getBackpack().remove(1);
                            character.getEquipment().put(itemtmp.getItemType(),itemtmp);
                            equipmentTable.clearChildren();
                            buildEquipmentMatrix();
                            addEquipmentMatrixListener();
                            backpackTable.clearChildren();
                            buildBackpackMatrix();
                            addBackpackMatrixListener();
                        }
                        return true;
                    }

                    @Override
                    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                        backpackItemInfoLabel.setText(character.getBackpack().get(1).toString());
                    }

                    @Override
                    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                        backpackItemInfoLabel.setText("");
                    }
                });
            }
            if(i == 2 && i < character.getBackpack().size() ){
                backpackMatrix[i].addListener(new ClickListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        if(character.getBackpack().get(2).getLevel() == character.getLevel()
                                && ! character.getEquipment().containsKey(character.getBackpack().get(2).getItemType()) ) {
                            Item itemtmp = character.getBackpack().remove(2);
                            character.getEquipment().put(itemtmp.getItemType(),itemtmp);
                            equipmentTable.clearChildren();
                            buildEquipmentMatrix();
                            addEquipmentMatrixListener();
                            backpackTable.clearChildren();
                            buildBackpackMatrix();
                            addBackpackMatrixListener();
                        }
                        return true;
                    }

                    @Override
                    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                        backpackItemInfoLabel.setText(character.getBackpack().get(2).toString());
                    }

                    @Override
                    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                        backpackItemInfoLabel.setText("");
                    }
                });
            }
            if(i == 3 && i < character.getBackpack().size() ){
                backpackMatrix[i].addListener(new ClickListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        if(character.getBackpack().get(3).getLevel() == character.getLevel()
                                && ! character.getEquipment().containsKey(character.getBackpack().get(3).getItemType()) ) {
                            Item itemtmp = character.getBackpack().remove(3);
                            character.getEquipment().put(itemtmp.getItemType(),itemtmp);
                            equipmentTable.clearChildren();
                            buildEquipmentMatrix();
                            addEquipmentMatrixListener();
                            backpackTable.clearChildren();
                            buildBackpackMatrix();
                            addBackpackMatrixListener();
                        }
                        return true;
                    }

                    @Override
                    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                        backpackItemInfoLabel.setText(character.getBackpack().get(3).toString());
                    }

                    @Override
                    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                        backpackItemInfoLabel.setText("");
                    }
                });
            }
            if(i == 4 && i < character.getBackpack().size() ){
                backpackMatrix[i].addListener(new ClickListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        if(character.getBackpack().get(4).getLevel() == character.getLevel()
                                && ! character.getEquipment().containsKey(character.getBackpack().get(4).getItemType()) ) {
                            Item itemtmp = character.getBackpack().remove(4);
                            character.getEquipment().put(itemtmp.getItemType(),itemtmp);
                            equipmentTable.clearChildren();
                            buildEquipmentMatrix();
                            addEquipmentMatrixListener();
                            backpackTable.clearChildren();
                            buildBackpackMatrix();
                            addBackpackMatrixListener();
                        }
                        return true;
                    }

                    @Override
                    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                        backpackItemInfoLabel.setText(character.getBackpack().get(4).toString());
                    }

                    @Override
                    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                        backpackItemInfoLabel.setText("");
                    }
                });
            }
            if(i == 5 && i < character.getBackpack().size() ){
                backpackMatrix[i].addListener(new ClickListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        if(character.getBackpack().get(5).getLevel() == character.getLevel()
                                && ! character.getEquipment().containsKey(character.getBackpack().get(5).getItemType()) ) {
                            Item itemtmp = character.getBackpack().remove(5);
                            character.getEquipment().put(itemtmp.getItemType(),itemtmp);
                            equipmentTable.clearChildren();
                            buildEquipmentMatrix();
                            addEquipmentMatrixListener();
                            backpackTable.clearChildren();
                            buildBackpackMatrix();
                            addBackpackMatrixListener();
                        }
                        return true;
                    }

                    @Override
                    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                        backpackItemInfoLabel.setText(character.getBackpack().get(5).toString());
                    }

                    @Override
                    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                        backpackItemInfoLabel.setText("");
                    }
                });
            }
        }
    }


    private void addEquipmentMatrixListener() {
        for (int i = 0; i < equipmentMatrix.length ; i++){
            if(i == 0 ){
                equipmentMatrix[i].addListener(new ClickListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        character.getBackpack().add(character.getEquipment().remove(Item.ItemType.HELMET));
                        equipmentTable.clearChildren();
                        buildEquipmentMatrix();
                        addEquipmentMatrixListener();
                        backpackTable.clearChildren();
                        buildBackpackMatrix();
                        addBackpackMatrixListener();
                        return true;
                    }

                    @Override
                    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                        if(character.getEquipment().get(Item.ItemType.HELMET) != null){
                            equipmentItemInfoLabel.setText(character.getEquipment().get(Item.ItemType.HELMET).toString());
                        }
                    }

                    @Override
                    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                        equipmentItemInfoLabel.setText("");
                    }
                });
            }
            if(i == 1 ){
                equipmentMatrix[i].addListener(new ClickListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        character.getBackpack().add(character.getEquipment().remove(Item.ItemType.ARMOR));
                        equipmentTable.clearChildren();
                        buildEquipmentMatrix();
                        addEquipmentMatrixListener();
                        backpackTable.clearChildren();
                        buildBackpackMatrix();
                        addBackpackMatrixListener();
                        return true;
                    }

                    @Override
                    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                        if(character.getEquipment().get(Item.ItemType.ARMOR) != null){
                            equipmentItemInfoLabel.setText(character.getEquipment().get(Item.ItemType.ARMOR).toString());
                        }                    }

                    @Override
                    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                        equipmentItemInfoLabel.setText("");
                    }
                });
            }
            if(i == 2 ){
                equipmentMatrix[i].addListener(new ClickListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        character.getBackpack().add(character.getEquipment().remove(Item.ItemType.WEAPON));
                        equipmentTable.clearChildren();
                        buildEquipmentMatrix();
                        addEquipmentMatrixListener();
                        backpackTable.clearChildren();
                        buildBackpackMatrix();
                        addBackpackMatrixListener();
                        return true;
                    }

                    @Override
                    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                        if(character.getEquipment().get(Item.ItemType.WEAPON) != null){
                            equipmentItemInfoLabel.setText(character.getEquipment().get(Item.ItemType.WEAPON).toString());
                        }                    }

                    @Override
                    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                        equipmentItemInfoLabel.setText("");
                    }
                });
            }
            if(i == 3 ){
                equipmentMatrix[i].addListener(new ClickListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        character.getBackpack().add(character.getEquipment().remove(Item.ItemType.BELT));
                        equipmentTable.clearChildren();
                        buildEquipmentMatrix();
                        addEquipmentMatrixListener();
                        backpackTable.clearChildren();
                        buildBackpackMatrix();
                        addBackpackMatrixListener();
                        return true;
                    }

                    @Override
                    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                        if(character.getEquipment().get(Item.ItemType.BELT) != null){
                            equipmentItemInfoLabel.setText(character.getEquipment().get(Item.ItemType.BELT).toString());
                        }
                    }

                    @Override
                    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                        equipmentItemInfoLabel.setText("");
                    }
                });
            }
            if(i == 4 ){
                equipmentMatrix[i].addListener(new ClickListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        character.getBackpack().add(character.getEquipment().remove(Item.ItemType.SHIELD));
                        equipmentTable.clearChildren();
                        buildEquipmentMatrix();
                        addEquipmentMatrixListener();
                        backpackTable.clearChildren();
                        buildBackpackMatrix();
                        addBackpackMatrixListener();
                        return true;
                    }

                    @Override
                    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                        if(character.getEquipment().get(Item.ItemType.SHIELD) != null){
                            equipmentItemInfoLabel.setText(character.getEquipment().get(Item.ItemType.SHIELD).toString());
                        }
                    }

                    @Override
                    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                        equipmentItemInfoLabel.setText("");
                    }
                });
            }
            if(i == 5  ){
                equipmentMatrix[i].addListener(new ClickListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        character.getBackpack().add(character.getEquipment().remove(Item.ItemType.BOOTS));
                        equipmentTable.clearChildren();
                        buildEquipmentMatrix();
                        addEquipmentMatrixListener();
                        backpackTable.clearChildren();
                        buildBackpackMatrix();
                        addBackpackMatrixListener();
                        return true;
                    }

                    @Override
                    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                        if(character.getEquipment().get(Item.ItemType.BOOTS) != null){
                            equipmentItemInfoLabel.setText(character.getEquipment().get(Item.ItemType.BOOTS).toString());
                        }
                    }

                    @Override
                    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                        equipmentItemInfoLabel.setText("");
                    }
                });
            }
            if(i == 6 ){
                equipmentMatrix[i].addListener(new ClickListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        character.getBackpack().add(character.getEquipment().remove(Item.ItemType.RING));
                        equipmentTable.clearChildren();
                        buildEquipmentMatrix();
                        addEquipmentMatrixListener();
                        backpackTable.clearChildren();
                        buildBackpackMatrix();
                        addBackpackMatrixListener();
                        return true;
                    }

                    @Override
                    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                        if(character.getEquipment().get(Item.ItemType.RING) != null){
                            equipmentItemInfoLabel.setText(character.getEquipment().get(Item.ItemType.RING).toString());
                        }                    }

                    @Override
                    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                        equipmentItemInfoLabel.setText("");
                    }
                });
            }
        }
    }

}
