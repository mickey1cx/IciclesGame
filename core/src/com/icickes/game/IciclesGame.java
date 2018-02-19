package com.icickes.game;

import com.badlogic.gdx.Game;
import com.icickes.game.Constants.Difficulty;

public class IciclesGame extends Game {

	@Override
	public void create () {

		showDifficultyScreen();

	}

	public void showDifficultyScreen() {

		setScreen(new DifficultyScreen(this));

	}

	public void showIciclesScreen(Difficulty difficulty){

		setScreen(new IciclesScreen(this, difficulty));

	}

}
