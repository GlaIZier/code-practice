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

// it is alomost equal to:

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
