var assert = require('assert');

// https://learn.javascript.ru/object-methods
var calculator = (function() {
  var _oper1, _oper2;

  return {
    read: function (oper1, oper2) {
      _oper1 = oper1;
      _oper2 = oper2;
    },
    sum: function() {
      return _oper1 + _oper2;
    }
  };

})();

calculator.read(1, 2);
assert.equal(calculator.sum(), 3);

var ladder = {
  step: 0,
  up: function() { // вверх по лестнице
    this.step++;
    return this;
  },
  down: function() { // вниз по лестнице
    this.step--;
    return this;
  },
  showStep: function() { // вывести текущую ступеньку
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
{}[0]
// braces are the block of code here. It's equal to + {} == NaN
{} + {}

var foo = {
  toString: function() {
    return 'foo';
  },
  valueOf: function() {
    return 2;
  }
};

assert.equal(foo.toString(), "foo");
// cast to the number
assert.equal(foo + 1, 3);
assert.equal(foo + "3", "23");

assert.equal([] == [], false);
// ![] = false => 0, [] => '' => 0
assert.equal([] == ![], true);