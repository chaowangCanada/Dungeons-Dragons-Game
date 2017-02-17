package com.chaowang.ddgame;

import com.badlogic.gdx.Gdx;

import Items.Item;


public interface PublicParameter {

    int characterInventoryRow = 5, characterInventoryColumn = 5;
    int characterCellWidth = Gdx.graphics.getHeight() * 3 / 5 / characterInventoryRow;
    int characterCellHeight = Gdx.graphics.getHeight() * 3 / 5 / characterInventoryColumn;
    int itemInventoryRow = 7, itemInventoryColumn = 7;
    int itemCellWidth = Gdx.graphics.getHeight() * 5 / 8 / itemInventoryRow;
    int itemCellHeight = Gdx.graphics.getHeight() * 5 / 8 / itemInventoryColumn;
    int itemBackpackRow = 2, itemBackpackColumn = 5;
    int itemTypeCount = Item.ItemType.values().length;

}
