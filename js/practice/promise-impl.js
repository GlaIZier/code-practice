const assert = require('assert');

function Future(f) {
  this.state = Future.prototype.PENDING;
  // bind to this to be able to call this.resolve and this.reject within this function
  this.f = f.bind(this);
  // console.log(this);
  // console.log(this.f);
  this.f();
}

Future.prototype.PENDING  = 'pending';
Future.prototype.RESOLVED  = 'resolved';
Future.prototype.REJECTED  = 'rejected';

Future.prototype.then = function (thenFunc) {
  this.thenFunc = thenFunc;
  return this;
};

Future.prototype.catch = function (catchFunc) {
  this.catchFunc = catchFunc;
  return this;
};

Future.prototype.resolve = function (data) {
  this.state = Future.prototype.RESOLVED;
  this.thenFunc(data);
  return data;
};

Future.prototype.reject = function (error) {
  this.state = Future.prototype.REJECTED;
  this.catchFunc(error);
  return error;
};

let futureDelayAndReturn = (result, delayInMillis) => {
  return new Future(function () {
    setTimeout(() => {
      // console.log(this);
      this.resolve(result);
    }, delayInMillis);
  });
};

let futureDelayAndThrowError = (error, delayInMillis) => {
  return new Future(function () {
    setTimeout(() => {
      // console.log(this);
      this.reject(error);
    }, delayInMillis);
  });
};

futureDelayAndReturn("futureData", 10).then(console.log);
futureDelayAndReturn("futureData", 20)
  .then((data) => {
    assert.equal(data, "futureData")
  });
futureDelayAndThrowError("futureError", 30)
  .then((data) => {
    // never gonna happen
    assert.equal(data, "futureData");
  })
  .catch((error) => {
    assert.equal(error, "futureError");
  });