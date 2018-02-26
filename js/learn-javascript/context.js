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
// ?
assert.equal(({} && []) == true, false);

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
console.log(+{});


