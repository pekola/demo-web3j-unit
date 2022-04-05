import java.io.File;
import java.math.BigDecimal;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert.Unit;

public class Web3jWallets {

  public static void main(String[] args)
      throws Exception {

    Web3j client = Web3j.build(new HttpService("HTTP://127.0.0.1:7545"));

    String fileName = WalletUtils.generateNewWalletFile("secret", new File("."));

    Credentials creds = WalletUtils.loadCredentials("secret", new File(fileName));

    System.out.println(creds.getAddress());
    System.out.println(creds.getEcKeyPair().getPublicKey().toString(16));
    System.out.println(creds.getEcKeyPair().getPrivateKey().toString(16));


    Credentials credsFromGanache = Credentials.create("c8837169cfb0e1a6d8c517ae8d3df82658a4f273c67505b64a08fba1576bb6e8");

    System.out.println(credsFromGanache.getAddress());

    TransactionReceipt receipt = Transfer.sendFunds(client, credsFromGanache, creds.getAddress(), BigDecimal.TEN, Unit.ETHER).send();

    System.out.println(receipt.getBlockNumber());

    EthGetBalance balance = client.ethGetBalance(creds.getAddress(), DefaultBlockParameterName.LATEST).send();
    System.out.println(balance.getBalance());
  }
}
