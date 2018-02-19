var assert = require('assert');

/* error. No ; after 5. So, 5 is a function name => errorœœ
var a = 5
(function() {
  alert(a)
})();
  */


// https://learn.javascript.ru/closures-usage

// encapsulation using closures
var counterEncaps = function() {
  var current = 1;

  return {
    getCount: function () {
      return current;
    },

    count: function () {
      return ++current;
    },

    reset: function () {
      current = 1;
      return current;
    }
  };
};

// encapsulation using closures and fast access to the main function
function makeCounter() {
  var current = 1;

  function count() {
    return current++;
  }

  count.reset = function () {
    current = 1;
    return current;
  };

  return count;
}

var counter = makeCounter();
assert.equal(counter(), 1);
assert.equal(counter(), 2);
assert.equal(counter.reset(), 1);

// https://learn.javascript.ru/closures-usage
// task2

function makeBuffer() {
  var string = "";

  function buffer(append) {
    if (arguments.length == 0)
      return string;

    string += append;
  }

  return buffer;

}

var buffer = makeBuffer();
buffer('Closures');
buffer(' usage');
buffer(' is needed!');
assert.equal(buffer(), "Closures usage is needed!" );