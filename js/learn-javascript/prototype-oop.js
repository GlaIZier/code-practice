var assert = require('assert');

// https://learn.javascript.ru/prototype
// Task 2
var animal = {
  eat: function() {
    this.full = true;
  }
};
var rabbit = {
  __proto__: animal
};
rabbit.eat();
assert.equal(rabbit.hasOwnProperty('full'), true);

// Task 3
var head = {
  glasses: 1
};
var table = {
  pen: 3
};
var bed = {
  sheet: 1,
  pillow: 2
};
var pockets = {
  money: 2000
};
table.__proto__ = head;
bed.__proto__ = table;
pockets.__proto__ = bed;

assert.equal(pockets.pen, 3);
assert.equal(bed.glasses, 1);
assert.equal(table.money, undefined);

