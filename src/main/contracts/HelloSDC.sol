// SPDX-License-Identifier: MIT
pragma solidity 0.7.1;

contract Greeter {
    string greeting;

    constructor() {
        greeting = "Hello SDC";
    }

    function greet() public view returns (string memory){
        return greeting;
    }

    function store(string calldata str) public {
        greeting = str;
    }
}