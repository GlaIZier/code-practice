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

// https://learn.javascript.ru/native-prototypes
// Task 1
Function.prototype.defer = function (timeout) {
  setTimeout(this, timeout);
};
function f() {
  console.log("Hi from defer!");
}
f.defer(10);

// Task 2
Function.prototype.defer2 = function (timeout) {
  var self = this;
  return function () {
    var args = arguments;
    var context = this;
    setTimeout(function () {
      self.apply(context, args);
    }, timeout)
  };
  setTimeout(this, timeout);
};
function f1(a, b) {
  console.log(a + b);
}

f1.defer2(10)(1, 2); // выведет 3

// https://learn.javascript.ru/classes
// Task 1
function CoffeeMachine(power) {
  this._waterAmount = 0;
  this._power = power;
}
CoffeeMachine.prototype._WATER_HEAT_CAPACITY = 4200;
CoffeeMachine.prototype.getTimeToBoil = function () {
  return this._waterAmount * this._WATER_HEAT_CAPACITY * 80 / this._power;
};
CoffeeMachine.prototype.run = function () {
  setTimeout(function() {
    console.log('Кофе готов!');
  }, this.getTimeToBoil());
};
CoffeeMachine.prototype.setWaterAmount = function (amount) {
  this._waterAmount = amount;
};

var coffeeMachine = new CoffeeMachine(100000);
coffeeMachine.setWaterAmount(50);
coffeeMachine.run();

// Task 2
function Hamster() {
  this.food = [];
}
Hamster.prototype.found = function(something) {
  this.food.push(something);
};
// Создаём двух хомяков и кормим первого
var speedy = new Hamster();
var lazy = new Hamster();
speedy.found("яблоко");
speedy.found("орех");
assert.equal(speedy.food.length, 2);
assert.equal(lazy.food.length, 0);

// https://learn.javascript.ru/class-inheritance
// Right class inheritance using prototypes
// 1. Конструктор Animal
function Animal(name) {
  this.name = name;
  this.speed = 0;
}
// 1.1. Методы -- в прототип
Animal.prototype.stop = function() {
  this.speed = 0;
  return "animal stopped"
};
Animal.prototype.run = function(speed) {
  this.speed += speed;
  return "animal is running with " + this.speed;
};

// 2. Конструктор Rabbit1
function Rabbit1(name) {
  Animal.apply(this, arguments); // call constructor of Animal
  this.speed = 0;
}
// 2.1. Наследование
Rabbit1.prototype = Object.create(Animal.prototype);
Rabbit1.prototype.constructor = Rabbit1;
// or just (IE10+)
Rabbit1.prototype.__proto__ = Animal.prototype;
// 2.2. Методы Rabbit1
Rabbit1.prototype.jump = function() {
  this.speed++;
  return "rabbit is jumping with " + this.speed;
};
// override method
Rabbit1.prototype.run = function() {
  // вызвать метод родителя, передав ему текущие аргументы
  Animal.prototype.run.apply(this, arguments); // Animal.prototype.run() will cause this = Animal.prototype
  return this.jump();
};

var r = new Rabbit1("r");
assert.equal(r.run(1), "rabbit is jumping with 2");

// Task 3
function Clock(options) {
  this._template = options.template;
  this._name = options.name;
  this._timer = undefined;
}

Clock.prototype.render = function () {
  var date = new Date();

  var hours = date.getHours();
  if (hours < 10) hours = '0' + hours;

  var min = date.getMinutes();
  if (min < 10) min = '0' + min;

  var sec = date.getSeconds();
  if (sec < 10) sec = '0' + sec;

  var output = this._template.replace('h', hours).replace('m', min).replace('s', sec) + " " + this._name;

  console.log(output);
};
Clock.prototype.stop = function () {
  clearInterval(this._timer);
};
Clock.prototype.start = function () {
  this.render();
  var self = this;
  this._timer = setInterval(function () { self.render()}, 1000);
};

// Task 4
function ExtendedClock(options, name) {
  Clock.apply(this, arguments);
}

ExtendedClock.prototype = Object.create(Clock.prototype);
ExtendedClock.prototype.constructor = ExtendedClock;

ExtendedClock.prototype.start = function (precision) {
  this.render();
  var self = this;
  this._timer = setInterval(function () { self.render()}, precision);
};
var clock = new Clock({
  template : 'h:m:s',
  name: 'clock'
});
clock.start();
setTimeout(function() {clock.stop()}, 2000);

var extendedClock = new ExtendedClock({
  template : 'h:m:s',
  name: 'extendedClock'
});
extendedClock.start(500);
setTimeout(function() {extendedClock.stop()}, 1000);

// Task 5
function Menu1(state) {
  this._state = state || Menu1.STATE_CLOSED;
}
Menu1.STATE_OPEN = 1;
Menu1.STATE_CLOSED = 0;
Menu1.prototype.open = function() {
  this._state = Menu1.STATE_OPEN;
};
Menu1.prototype.close = function() {
  this._state = Menu1.STATE_CLOSED;
};
Menu1.prototype._stateAsString = function() {
  switch (this._state) {
    case Menu1.STATE_OPEN:
      return 'opened';

    case Menu1.STATE_CLOSED:
      return 'closed';
  }
};
Menu1.prototype.showState = function() {
  return this._stateAsString();
};

function AnimatedMenu(state) {
  Menu1.apply(this, arguments);
}
AnimatedMenu.STATE_ANIMATED = 2;
AnimatedMenu.prototype = Object.create(Menu1.prototype);
AnimatedMenu.prototype.constructor = AnimatedMenu;
AnimatedMenu.prototype.open = function() {
  clearTimeout(this._timer);
  var self = this;
  this._state = AnimatedMenu.STATE_ANIMATED;
  this._timer = setTimeout(function () {
    Menu1.prototype.open.apply(self, arguments);
  }, 200);
};
AnimatedMenu.prototype.close = function() {
  clearTimeout(this._timer);
  Menu1.prototype.close.apply(this, arguments);
};
AnimatedMenu.prototype.showState = function() {
  if (this._state === AnimatedMenu.STATE_ANIMATED)
    return 'animation';
  return Menu1.prototype.showState.apply(this, arguments);
};

var aMenu = new AnimatedMenu();
assert.equal(aMenu.showState(), "closed");
aMenu.open();
assert.equal(aMenu.showState(), "animation");
setTimeout(function () {
  assert.equal(aMenu.showState(), "opened");
  aMenu.close();
  assert.equal(aMenu.showState(), "closed");
}, 210);