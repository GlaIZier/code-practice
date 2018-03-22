var assert = require('assert');
// https://learn.javascript.ru/internal-external-interface

function CoffeeMachine(power) {
// public object var
  this.waterAmount = 0;
  // private method
  function getBoilTime() {
    return 50;
  }
  // private method
  function onReady() {
    console.log("Coffee is ready!");
  }
// public method. It has an access to the object through the closure
  this.run = function() {
    setTimeout(onReady, getBoilTime());
  };
}
var coffeeMachine = new CoffeeMachine(100);
coffeeMachine.waterAmount = 200;
coffeeMachine.run();

// it is almost equal to:

function CoffeeMachineInterpretation(power) {
  // private method
  function getBoilTime() {
    return 50;
  }
  // private method
  function onReady() {
    console.log("Coffee is ready from interpretation!");
  }

  return {
    waterAmount: 0,
    run: function () {
      setTimeout(onReady, getBoilTime());
    }
  };
}
var coffeeMachineInterpretation = CoffeeMachineInterpretation(100);
// or with new. Doesn't matter
// var coffeeMachineInterpretation = new CoffeeMachineInterpretation(100);
coffeeMachineInterpretation.waterAmount = 200;
coffeeMachineInterpretation.run();

// Task 1
function CoffeeMachine1(power) {
  this.waterAmount = 0;
  var WATER_HEAT_CAPACITY = 4200;
  var self = this;
  var timerId = null;

  function getBoilTime() {
    return self.waterAmount * WATER_HEAT_CAPACITY * 80 / power;
  }

  function onReady() {
    console.log('Coffee is ready!');
  }

  this.run = function () {
    timerId = setTimeout(onReady, getBoilTime());
  };

  this.stop = function () {
    console.log('Coffee machine was stopped!');
    clearTimeout(timerId);
  }

}

coffeeMachine = new CoffeeMachine1(50000);
coffeeMachine.waterAmount = 200;

coffeeMachine.run();
coffeeMachine.stop(); // кофе приготовлен не будет


// https://learn.javascript.ru/getters-setters
// Task 4, 5. OnReady setter, is Running
function CoffeeMachine2(power, capacity) {
  var waterAmount = 0;

  var WATER_HEAT_CAPACITY = 4200;

  var timerId = null;

  function getTimeToBoil() {
    return waterAmount * WATER_HEAT_CAPACITY * 80 / power;
  }

  var onReady = function () {
    console.log('Кофе готов2!');
  };

  this.setWaterAmount = function (amount) {
    if (amount < 0) {
      throw new Error("Значение должно быть положительным");
    }
    if (amount > capacity) {
      throw new Error("Нельзя залить больше, чем " + capacity);
    }

    waterAmount = amount;
  };

  this.getWaterAmount = function (amount) {
    return waterAmount;
  };

  this.setOnReady = function (func) {
    onReady = func;
  };

  this.run = function () {
    timerId = setTimeout(
      function () {
        timerId = null;
        onReady()
      },
      getTimeToBoil())
  };

  this.isRunning = function () {
    return !!timerId;
  }

}

coffeeMachine2 = new CoffeeMachine2(100000, 400);
coffeeMachine2.setWaterAmount(200);
coffeeMachine2.setOnReady(function () {
  var amount = coffeeMachine2.getWaterAmount();
  console.log('Готов кофе2: ' + amount + 'мл');
});
coffeeMachine2.run();
assert.equal(coffeeMachine2.isRunning(), true);

// https://learn.javascript.ru/functional-inheritance
// Task 3, 4, 5. Fridge

function Machine(power) {
  this._enabled = false;
  var self = this;

  this.enable = function() {
    self._enabled = true;
  };

  this.disable = function() {
    self._enabled = false;
  };
}

function Fridge(power) {
  Machine.apply(this, arguments);

  var food = [];

  var self = this;

  this.isFull = function () {
    return food.length === Math.floor(power / 100);
  };

  this.addFood = function () {
    if (!this._enabled)
      throw new Error("The fridge is turned off");

    arguments.forEach = [].forEach;
    arguments.forEach(function (item) {
      if (self.isFull())
        throw new Error("The fridge is full. Can't add more!");
      food.push(item);
    })
  };

  this.getFilteredFood = function (filter) {
    // filter returns copy of the initial array with filtered elements only
    return food.filter(filter);
  };

  this.removeFood = function (foodItem) {
    food = food.filter(function (item) {
      var isEqual = JSON.stringify(item) === JSON.stringify(foodItem);
      return !isEqual;
    });
  };

  this.getFood = function () {
    // return the whole copy of this array
    return food.slice();
  };

}

// Task 3 checks
var fridge = new Fridge(200);
assert.throws(function() {fridge.addFood("котлета");}); // ошибка, холодильник выключен

fridge = new Fridge(510);
fridge.enable();
fridge.addFood("котлета");
fridge.addFood("сок", "зелень");
assert.throws(function() {fridge.addFood("варенье", "пирог", "торт");}); // ошибка, слишком много еды
assert.equal(fridge.isFull(), true);

fridge = new Fridge(500);
fridge.enable();
fridge.addFood("котлета");
fridge.addFood("сок", "варенье");

var fridgeFood = fridge.getFood();
assert.deepEqual(fridgeFood, ["котлета", "сок", "варенье"] );

// добавление элементов не влияет на еду в холодильнике
fridgeFood.push("вилка", "ложка");
assert.deepEqual(fridgeFood, ["котлета", "сок", "варенье", "вилка", "ложка"]);

assert.deepEqual(fridge.getFood(), ["котлета", "сок", "варенье"] );

// Task 4 checks

fridge = new Fridge(500);
fridge.enable();
fridge.addFood({
  title: "котлета",
  calories: 100
});
fridge.addFood({
  title: "сок",
  calories: 30
});
fridge.addFood({
  title: "зелень",
  calories: 10
});
fridge.addFood({
  title: "варенье",
  calories: 150
});

fridge.removeFood("нет такой еды"); // без эффекта
assert.equal(fridge.getFood().length, 4); // 4

var dietItems = fridge.getFilteredFood(function(item) {
  return item.calories < 50;
});

dietItems.forEach(function(item) {
  fridge.removeFood(item);
});

assert.equal(fridge.getFood().length, 2); // 2