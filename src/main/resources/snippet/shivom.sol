pragma solidity ^0.4.4;
contract shivom {
    struct  user{
        address  useraddress;
        uint256  amount;    }
 struct  fileDownloadPermissions{
        address  useraddress;
        uint256  amount; 
    }
mapping(address => user) users;
mapping(address => uint[]) userToFileHash;

mapping(uint => address) fileHashToUserMapping;
mapping(uint => fileDownloadPermissions[]) allowAddressToFileMapping;
 function login(address to, uint256 value) returns (bool success) {
      var _user=user(to,value);
      users[to]=_user;
      return true;
    }
 function getUserInfo(address to)constant returns (address,uint256) {
      var _user= users[to];
      return (_user.useraddress,_user.amount);
    }
 function fileUpload(address to, uint fileHash) returns (bool success) {
       fileHashToUserMapping[fileHash]=to;
       userToFileHash[to].push(fileHash);
       return true;
    }
function download(uint fileHash,uint256 amount,address downloaderAddress) returns (bool success) {
       var downloaderDetails=users[downloaderAddress];
      if(downloaderDetails.amount >=amount && amount !=0){
      var userAddress= fileHashToUserMapping[fileHash];
      var userDetails=users[userAddress];
      userDetails.amount+=amount;
      downloaderDetails.amount-=amount;
      var addDownloder=fileDownloadPermissions(downloaderAddress,amount);
      allowAddressToFileMapping[fileHash].push(addDownloder);
                return true;
    }else{
             return false;
         }
    }
function getTokens(uint256 amount,address userAddress) returns (bool success) {
         var userDetails=users[userAddress];
         userDetails.amount+=amount;
         return true;
    }
function getFileUserList(uint fileHash)constant returns (address[],uint[]) {
        var userDetails= allowAddressToFileMapping[fileHash]; 
        uint l=userDetails.length;
        address[] memory fileUserAddress=new  address[](l);
        uint256[] memory amount=new  uint256[](l);
       for(uint i=0;i<l;i++)
       {
           var userDetail=userDetails[i];
           fileUserAddress[i]=(userDetail.useraddress);
           amount[i]=(userDetail.amount);
       }
         return (fileUserAddress,amount);
    }
    
    function getUplodedFileUserList(address to)constant returns (uint[]) {
        var userFileDetails= userToFileHash[to]; 
        uint l=userFileDetails.length;
        uint[] memory fileHash=new  uint[](l);
       for(uint i=0;i<l;i++)
       {
           fileHash[i]=userFileDetails[i];
       }
         return (fileHash);
    }
    
    
}