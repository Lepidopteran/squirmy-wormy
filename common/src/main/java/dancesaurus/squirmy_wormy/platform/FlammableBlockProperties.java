package dancesaurus.squirmy_wormy.platform;

/**
 * A record representing properties of flammable blocks, used to define how easily a block can catch fire and the chance it spreads fire to adjacent blocks.
 *
 * @param spreadChance The probability (as a percentage) that this block will spread fire to neighboring blocks when ignited.
 * @param flammability The ease/speed with which this block can catch fire from an external source, such as a flame or lava.
 */
public record FlammableBlockProperties(int spreadChance, int flammability) {}
