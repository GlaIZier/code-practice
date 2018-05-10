const assert = require('assert');


function Future(f) {
  this.state = Future.PENDING;
  this.f = f.bind(this);

  console.log(this.f);
  console.log(this);

  this.resolve = function (data) {
    this.state = Future.RESOLVED;
    this.thenFunc(data);
    return data;
  };

  this.reject = function (error) {
    this.state = Future.REJECTED;
    return error;
  };

  this.then = function (thenFunc) {
    this.thenFunc = thenFunc;
  };

  this.start = function (f) {
    f();
    return this;
  };

  this.f();
}

Future.PENDING  = 'pending';
Future.RESOLVED  = 'resolved';
Future.REJECTED  = 'rejected';
Future.resolve = function (future, data) {
  this.state = Future.RESOLVED;
  future.thenFunc(data);
};
Future.reject = function (future, error) {
  future.state = Future.REJECTED;
  future.catch(error);
};

let futureDelayAndReturn = (result, delayInMillis) => {
  return new Future(function () {
    setTimeout(() => {
      console.log(this);
      this.resolve(result);
    }, delayInMillis);
  });
};

let delayAndReturn = (result, delayInMillis) => {
  return new Promise(resolve => {
    setTimeout(() => {
      resolve(result);
    }, delayInMillis);
  });
};

futureDelayAndReturn("futureData", 50).then(console.log);