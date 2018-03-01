var assert = require('assert');

// https://learn.javascript.ru/object-methods
var calculator = (function () {
  var _oper1, _oper2;

  return {
    read: function (oper1, oper2) {
      _oper1 = oper1;
      _oper2 = oper2;
    },
    sum: function () {
      return _oper1 + _oper2;
    }
  };

})();

calculator.read(1, 2);
assert.equal(calculator.sum(), 3);

var ladder = {
  step: 0,
  up: function () { // вверх по лестнице
    this.step++;
    return this;
  },
  down: function () { // вниз по лестнице
    this.step--;
    return this;
  },
  showStep: function () { // вывести текущую ступеньку
    return this.step;
  }
};

assert.equal(ladder.up().up().down().up().down().showStep(), 1);
assert.equal(!!({} && []), true);
assert.equal(isNaN(+{}), true);
assert.equal(+[], 0);
assert.equal({} && [], 0);
// as I understand {} casts to NaN, [] to 0, so the result of this is 0 => 0 == false
assert.equal(({} && []) == false, true);

var obj = {
  valueOf: function () {
    return 42;
  },
  toString: function () {
    return "42";
  }
};
assert.equal(obj + "", "42");
assert.equal(+obj, 42);
delete obj.valueOf;
assert.equal(+obj, "42");

// braces are the block of code here. It's equal to [0]
{
}
[0]
// braces are the block of code here. It's equal to + {} == NaN
{
}
+{}

var foo = {
  toString: function () {
    return 'foo';
  },
  valueOf: function () {
    return 2;
  }
};

assert.equal(foo.toString(), "foo");
// cast to the number
assert.equal(foo + 1, 3);
assert.equal(foo + "3", "23");

// Two objects are equal when they are the same
assert.equal([] == [], false);
assert.equal({} == {}, false);
// ![] = false => 0, [] => '' => 0
assert.equal([] == ![], true);

assert.equal(new Date(0) - 0, 0);
assert.equal(new Array(1)[0] + "", "undefined");
assert.equal(({})[0], undefined);
assert.equal([1] + 1, "11");
assert.equal([1, 2] + [3, 4], "1,23,4");
assert.equal([] + null + 1, "null1");
assert.equal([[0]][0][0], 0);
assert.equal(isNaN({} + {}), true);

// sum of parenthesis of any length
// my solution
var sum = function (n) {
  if (!sum.result)
    sum.result = 0;

  sum.valueOf = function () {
    var result = sum.result;
    // when it's called we need to clear result
    sum.result = undefined;
    return result;
  };

  sum.result += n;
  return sum;
};

assert.equal(sum(1) == 1, true);
assert.equal(sum(1)(2)(3) == 6, true);
assert.equal(sum(1)(2)(3)(-1) == 5, true);

// solution from the website
var sum1 = function (a) {

  var f = function (b) {
    a += b;
    return f;
  };

  f.valueOf = function () {
    return a;
  };

  return f;
};

// assert.equal(sum1(1) == 1, true);
assert.equal(sum1(1)(2)(3) == 6, true);
assert.equal(sum1(1)(2)(3)(-1) == 5, true);

// my solution without valueOf but with empty argument call in the end
var sum2 = function (n) {
  if (!sum2.result)
    sum2.result = 0;

  if (n) {
    sum2.result += n;
    return sum2;
  }
  else {
    var result = sum2.result;
    sum2.result = undefined;
    return result;
  }
};

assert.equal(sum2(1)(), 1);
assert.equal(sum2(1)(2)(3)(), 6);
assert.equal(sum2(1)(2)(3)(-1)(), 5);

// https://learn.javascript.ru/constructor-new
// return the same object by different constructors
var o = {};
function A() { return o; }
function B() { return o; }
assert.equal(new A, new B);

// calculator
// Todo finish it
Calculator = function() {

  this.calculate = function(opActionOp) {
    opActionOp = opActionOp.trim();
    var operands = opActionOp.split(" ");
    console.log(operands);
  }

};