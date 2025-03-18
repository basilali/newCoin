import java.util.Date;

/**
 * A simple implementation for a Block as part of a BLockChain
 */
public class Block {

    public String hash;
    public String previousHash;
    private final String data;
    private final long timeStamp; //as number of milliseconds since 1/1/1970.
    private int nonce;

    public Block(String data,String previousHash ) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    /**
     * Calculates the hash of a block
     * @return The block hash
     */
    public String calculateHash() {
        return StringUtil.applySha256(
                previousHash +
                        timeStamp +
                        nonce +
                        data
        );
    }

    /**
     * Mines a block based on difficulty.
     * Difficulty is an integer number denoting how many digits the miner needs to solve for in order to mine a block.
     * @param difficulty An integer number
     */
    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0'); // Create a string with difficulty * "0"
        while(!hash.substring( 0, difficulty).equals(target)) {
            nonce ++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash);
    }
}