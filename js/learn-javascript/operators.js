// https://learn.javascript.ru/operators
var assert = require('assert');
var a = 2;

var x = 1 + (a *= 2);

assert.equal(x, 5);