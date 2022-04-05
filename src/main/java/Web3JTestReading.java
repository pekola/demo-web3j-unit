import java.io.IOException;
import java.math.BigInteger;
import java.util.Optional;
import java.util.stream.IntStream;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterNumber;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

public class Web3JTestReading {

  public static void main(String[] args) throws IOException {
    Web3j client = Web3j.build(new HttpService("HTTP://127.0.0.1:7545"));

    // synchroner request
    Web3ClientVersion version = client.web3ClientVersion().send();

    System.out.println(version.getWeb3ClientVersion());

    BigInteger blockNumber = client.ethBlockNumber().send().getBlockNumber();

    System.out.println(blockNumber);

    // alle Transaktionen in den Blöcken
    int sum = IntStream.range(0, blockNumber.intValue() + 1).mapToObj(i -> {
          try {
            EthBlock block = client.ethGetBlockByNumber(new DefaultBlockParameterNumber(i), false)
                .send();
            return Optional.of(block);
          } catch (IOException e) {
            return Optional.empty();
          }
        })
        .flatMap(Optional::stream)
        .mapToInt(block -> ((EthBlock) block).getBlock().getTransactions().size())
        .sum();

    System.out.println(sum);
  }

}
