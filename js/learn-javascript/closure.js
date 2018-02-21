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

  buffer.clear = function() {
    string = "";
  };

  return buffer;

}

var buffer = makeBuffer();
buffer('Closures');
buffer(' usage');
buffer(' is needed!');
assert.equal(buffer(), "Closures usage is needed!" );
buffer.clear();
assert.equal(buffer(), "" );


// task 1

function sum(a) {
  var closure = a;

  function innerSum(b) {
    return closure + b;
  }

  return innerSum;
}

// or more simple:
/*
function sum(a) {
  return function(b) {
    return a + b;
  };
}
*/

assert.equal(sum(1)(2), 3);
assert.equal(sum(5)(-1), 4);

// task 4. Filtration using function
/*
 Создайте функцию filter(arr, func), которая получает массив arr и возвращает новый, в который входят только те элементы arr, для которых func возвращает true.
 Создайте набор «готовых фильтров»: inBetween(a,b) – «между a,b», inArray([...]) – "в массиве [...]". Использование должно быть таким:
 filter(arr, inBetween(3,6)) – выберет только числа от 3 до 6,
 filter(arr, inArray([1,2,3])) – выберет только элементы, совпадающие с одним из значений массива.
 */

function filter(arr, func) {
  return arr.filter(function (arrElement) {
    return func(arrElement);
  });
}

function inBetween(left, right) {
  return function(input) {
    return input >= left && input <= right;
  }
}

function inArray(arr) {
  return function(input) {
    return arr.filter(function(arrElement){
      return arrElement === input;
    }).length > 0;
  }
}

assert.deepEqual(filter([1, 2, 3, 4, 5, 6], function(element) {return element < 3}), [1, 2]);
assert.deepEqual(filter([1, 2, 3, 4, 5, 6], inBetween(3, 5)), [3, 4, 5]);
assert.deepEqual(filter([1, 2, 3, 4, 5, 6], inArray([3, 5, 6])), [3, 5, 6]);

// Task 5. Functions army
function makeArmy() {

  var shooters = [];

  for (var i = 0; i < 10; i++) {
    (function() {
      var funcIndex = i;
      var shooter = function() {
        return funcIndex;
      };
      shooters.push(shooter);
    }());
  }

  return shooters;
}

var army = makeArmy();
assert.equal(army[0](), 0);
assert.equal(army[3](), 3);
assert.equal(army[9](), 9);
