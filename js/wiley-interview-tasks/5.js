// Task: Review the code and fix the error in commit function. Context and Validation parts are read-only.


//--- context
function rndBool() {
  return Math.random() >= 0.5;
}

var countryChanged = rndBool();
var adressUpdCounter = 0;

self = { state: { updateProfileData: false, countryChanged: countryChanged} };
self.addModalDialog = function (args) {};
self.updateAddress = function (addr, countryChanged) {
  adressUpdCounter++;
  self.state.addr = addr;
  return rndBool();
};



//--- test task part ---
function commit(addresses) {

  if (countryChanged) {
    self.state.updateProfileData = self.state.updateProfileData || self.updateAddress(addresses, true);
    self.addModalDialog({
      type: 'info',
      message: "You have changed country details."
    });
  } else {
    self.state.updateProfileData =  self.state.updateProfileData || self.updateAddress(addresses);
  }

  return self.state.updateProfileData;
}
// ----

// Solution
function myCommit(addresses) {
  var addressUpdeted;
  if (countryChanged) {
    addressUpdeted = self.updateAddress(addresses, true);
    self.addModalDialog({
      type: 'info',
      message: "You have changed country details."
    });
  } else {
    addressUpdeted = self.updateAddress(addresses);
  }
  self.state.updateProfileData = self.state.updateProfileData || addressUpdeted;
  return self.state.updateProfileData;
}


//--- validation
console.log('--- validation');
for (var i = 1; i < 5; i++) {
  console.log('commit addr attempt - ' + i);
  var addr = { data: Math.random() }
  var updp = myCommit(addr);

  // console.log('adressUpdCounter = ' + (adressUpdCounter === i));
  // console.log('addr = ' + (self.state.addr === addr));
  // console.log('updp = ' + (updp === self.state.updateProfileData));
  var isExpectedResults = (adressUpdCounter === i) && (self.state.addr === addr) && (updp === self.state.updateProfileData);
  console.log('expected = ' + isExpectedResults);
}
