
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.web3j.EVMTest;
import org.web3j.NodeType;
import org.web3j.model.Greeter;
import org.web3j.protocol.Web3j;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

import static org.junit.jupiter.api.Assertions.assertEquals;

@EVMTest(type = NodeType.EMBEDDED)
public class HelloSDCTest {

    Greeter greeter;

    @BeforeEach
    public void greeterDeploys(Web3j web3j, TransactionManager transactionManager, ContractGasProvider gasProvider) throws Exception {
        this.greeter = Greeter.deploy(web3j, transactionManager, gasProvider).send();
    }

    @Test
    public void greetingTest() throws Exception{
        String greeting = greeter.greet().send();//.send();
        assertEquals("Hello SDC", greeting);
    }
    @Test
    public void greetingSDC() throws Exception{
        greeter.store("Lets test the full live cycle").send();
        String greeting = greeter.greet().send();
        assertEquals("Lets test the full live cycle", greeting);
    }


}
