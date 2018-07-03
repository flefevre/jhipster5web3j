pragma solidity ^0.4.17;

contract MySmartContract{

    event EventTx(address sender, string data);
    
    function registerTx(string data ) public {
        EventTx(msg.sender, data);
    }
    
}
