import java.math.BigInteger;
import org.web3j.crypto.Credentials;
import org.web3j.model.Greeter;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.StaticGasProvider;

public class Web3JSmartContractTest {


  public static void main(String[] args) throws Exception {
    var client = Web3j.build(new HttpService("HTTP://127.0.0.1:8545\n"));
    var creds = Credentials.create("8f2a55949038a9610f50fb23b5883af3b4ecb3c3bb792cbcefbd1542c692be63");  // private key

    var gasProvider = new StaticGasProvider(BigInteger.valueOf(1000), BigInteger.valueOf(1000000));
    var greeter = Greeter.deploy(client, creds, gasProvider).send();

    System.out.println(greeter.getContractAddress());

    System.out.println(greeter.greet().send());

    greeter.store("foo").send();

    System.out.println(greeter.greet().send());
  }
}
