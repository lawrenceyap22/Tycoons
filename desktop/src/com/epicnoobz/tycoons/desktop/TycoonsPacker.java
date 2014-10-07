package com.epicnoobz.tycoons.desktop;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class TycoonsPacker {
	
	public static void main(String[] args){
		TexturePacker.process("images/for_packing", "../android/assets/images", "images-packed");
	}

}
