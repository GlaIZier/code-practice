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

// https://learn.javascript.ru/logical-ops
assert.equal(1 && null && 2, null);
assert.equal(1 && null && 2, null);
assert.equal( null || 2 && 3 || 4 , 3);

// casting

assert.equal("" + 1 + 0, "10");
assert.equal("" - 1 + 0, -1);
assert.equal(true + false, 1);
assert.equal(6 / "3", 2);
assert.equal("2" * "3", 6);
assert.equal(4 + 5 + "px", "9px");
assert.equal("$" + 4 + 5, "$45");
assert.equal("4" - 2, 2);
assert.equal(isNaN("4px" - 2), true);
assert.equal(7 / 0, Infinity);
assert.equal("  -9\n" + 5, "  -9\n5");
assert.equal("  -9\n" - 5, -14);
assert.equal(5 && 2, 2);
assert.equal(2 && 5, 5);
assert.equal(5 || 0, 5);
assert.equal(0 || 5, 5);
assert.equal(null + 1, 1);
assert.equal(isNaN(undefined + 1), true);
assert.equal(null == "\n0\n", false);
assert.equal(+null == +"\n0\n", true);
assert.equal(null >= 0, true);
assert.equal(null == 0, false);


// loops
var j = 0;
for (var i = 0; i < 5; ++i) j = i
assert.equal(j, 4);

for (var i = 0; i < 5; i++) j = i;
assert.equal(j, 4);

// functions
function checkAge(age) {
  age = age || -1;
  return (age > 18) || console.log('Родители разрешили?');
}

assert.equal(checkAge(10), undefined);
assert.equal(checkAge(21), true);

var ff= (function g() { return 1; }); // function g in parenthesis is considered as named functional expression