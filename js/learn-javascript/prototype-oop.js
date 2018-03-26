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

// https://learn.javascript.ru/new-prototype
// Task 2
function Menu(options) {
  options = Object.create(options);
  assert.equal(options.width, 100);
  options.width = 300;

  assert.equal(options.width, 300);
  assert.equal(options.height, 200);
}
var options = {
  width: 100,
  height: 200
};
var menu = new Menu(options);

// Task 3
function Rabbit(name) {
  this.name = name;
}
Rabbit.prototype.sayHi = function() {
  return this.name;
};
rabbit = new Rabbit("Rabbit");

assert.equal(rabbit.sayHi(), "Rabbit");
assert.equal(Rabbit.prototype.sayHi(), undefined);
assert.equal(Object.getPrototypeOf(rabbit).sayHi(), undefined);
assert.equal(rabbit.__proto__.sayHi(), undefined);

// Task 4
var User = function (name) {
  this.name = name;
};
User.prototype.age = 18;
var obj = new User("uuu");
var obj2 = new obj.constructor("ttt");
assert.equal(obj2.name, "ttt");
assert.equal(obj2.age, 18);