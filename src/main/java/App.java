import java.util.LinkedList;
import java.util.List;
import org.apache.commons.codec.digest.DigestUtils;

public class App {

  public static void main(String[] args) {

    Blockchain blockchain = new Blockchain();

    blockchain.mineTransactionIntoBlock("foo");
    blockchain.mineTransactionIntoBlock("bar");
    blockchain.mineTransactionIntoBlock("baz");

    System.out.println(blockchain);
  }

  private static class Blockchain {

    private final List<Block> blocks = new LinkedList<>();

    public static final String DIFFICULTY = "00000";

    public Blockchain() {
      blocks.add(new Block("genesis", "0"));
    }

    public void mineTransactionIntoBlock(String payload) {
      Block block = new Block(payload, blocks.get(blocks.size() - 1).calculateHash());
      block.mine(DIFFICULTY);
      blocks.add(block);
    }

    @Override
    public String toString() {
      return "Blockchain{" + "blocks=" + blocks + '}';
    }
  }

  private static class Block {

    private final String data;
    private final String previousHash;
    private long nonce = 0;

    public Block(String data, String previousHash) {

      this.data = data;
      this.previousHash = previousHash;
    }

    public String calculateHash() {
      return DigestUtils.sha256Hex(previousHash + data + nonce);
    }

    @Override
    public String toString() {
      return "Block{" + "data='" + data + '\'' + ", previousHash='" + previousHash + '\''
          + ", nonce=" + nonce + '}';
    }

    public void mine(String difficulty) {
      while (!calculateHash().startsWith(difficulty)) {
        nonce++;
      }
    }
  }
}
