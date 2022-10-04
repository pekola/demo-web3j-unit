## Demo for web3 based unit tests
This very little demo project shows how you can
interact against a solidity contract in java with standard unit test tool

1. Run ``mvn clean install``
2. The Web3 compiler plugin ẁill be downloaded
3. Add your contracts.sol under `src/main/contracts`
3. Run `mvn web3j:generate-sources` - contracts will be compiled into java code and occur in folder java/org.web3.model
4. Run `mvn clean install`
4. Run tests in test folder