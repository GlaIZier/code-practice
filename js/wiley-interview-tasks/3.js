var assert = require('assert');

var promise1 = new Promise(function (resolve, reject) {
  resolve("p1");
});

var promise2 = new Promise(function (resolve, reject) {
  resolve("p2");
});

var promise3 = new Promise(function (resolve, reject) {
  resolve("p3");
});

//Task: Use the promises above to log ‘p1p2p3’ to console. Avoid usage of Promise.all, Promise.join methods:
Promise.resolve()
  .then()
  .then()
  .then()
  .then(console.log); //p1p2p3



var accumulatePromise = function (prevResult, promise) {
  return new Promise(function (resolve, reject) {
    promise.then(result => resolve(prevResult + result));
  })
};


Promise.resolve()
  .then(result => promise1)
  .then(p1 => accumulatePromise(p1, promise2))
  .then(p1p2 => accumulatePromise(p1p2, promise3))
  .then(console.log); //p1p2p3