import java.math.BigInteger;
import org.web3j.crypto.Credentials;
import org.web3j.model.Greeter;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.StaticGasProvider;

public class Web3JSmartContractTest {


  public static void main(String[] args) throws Exception {
    var client = Web3j.build(new HttpService("HTTP://127.0.0.1:7545\n"));
    var creds = Credentials.create("c8837169cfb0e1a6d8c517ae8d3df82658a4f273c67505b64a08fba1576bb6e8");  // private key

    var gasProvider = new StaticGasProvider(BigInteger.valueOf(1000), BigInteger.valueOf(1000000));
    var greeter = Greeter.deploy(client, creds, gasProvider).send();

    System.out.println(greeter.getContractAddress());

    System.out.println(greeter.greet().send());

    greeter.store("foo").send();

    System.out.println(greeter.greet().send());
  }
}
