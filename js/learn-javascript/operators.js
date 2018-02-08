// https://learn.javascript.ru/operators
var assert = require('assert');
var a = 2;

var x = 1 + (a *= 2);

assert.equal(x, 5);

// https://learn.javascript.ru/bitwise-operators

function isInteger(x) {
  return (x ^ 0) === x; // ^ cast to integer implicitly
}

assert.equal(isInteger(12.5), false);
assert.equal(isInteger(0), true);
assert.equal(isInteger(12), true);
assert.equal(isInteger(-12.4), false);