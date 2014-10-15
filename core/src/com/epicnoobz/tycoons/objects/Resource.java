package com.epicnoobz.tycoons.objects;

public enum Resource {
	ELECTRICITY(0), WATER(1), COAL(2), OIL(3), URANIUM(4), METAL(5), MICROCHIPS(6), FABRIC(7), CLOTHING(8), AUTOMOBILE(
			9), WHEAT(10), FRUITS(11), CORN(12), COTTON(13), COFFEEBEAN(14), MEAT(15), BEER(16), WINE(17), FLOUR(18), BREAD(
			19);

	private int index;

	private Resource(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}
}
